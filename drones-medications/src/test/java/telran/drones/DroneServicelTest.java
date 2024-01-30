package telran.drones;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import telran.drones.dto.DroneDto;
import telran.drones.dto.DroneMedication;
import telran.drones.dto.ModelType;
import telran.drones.dto.State;
import telran.drones.exceptions.BatteryIllegalStateException;
import telran.drones.exceptions.DroneIllegalStateException;
import telran.drones.exceptions.DroneModelNotFoundException;
import telran.drones.exceptions.DroneNotFoundException;
import telran.drones.exceptions.MedicationNotFoundException;
import telran.drones.exceptions.StateIllegalStateException;
import telran.drones.model.Drone;
import telran.drones.repo.DroneRepo;
import telran.drones.service.DronesService;

@SpringBootTest
@Sql(scripts = { "classpath:test_data.sql" })
class DroneServicelTest {
	@Autowired
	DronesService dronesService;
	@Autowired
	DroneRepo droneRepo;

	DroneDto droneDto = new DroneDto("number6", ModelType.Lightweight);
	Drone drone = Drone.of(droneDto);
	DroneMedication droneMedication = new DroneMedication("number1", "CODE1");
	DroneMedication droneMedication1 = new DroneMedication("number10", "CODE1");
	DroneMedication droneMedication2 = new DroneMedication("number1", "CODE10");

	@Test
	void registerDroneTest() {
		assertEquals(droneDto, dronesService.registerDrone(droneDto));
		assertThrowsExactly(DroneIllegalStateException.class, () -> dronesService.registerDrone(droneDto));
		Drone drone = droneRepo.findById(droneDto.number()).orElse(null);
		assertEquals(droneDto, drone.build());
	}

	@Test
	void loadDroneTest() {
		assertEquals(droneMedication, dronesService.loadDrone(droneMedication));
		Drone testDrone = droneRepo.findById(droneMedication.droneNumber())
				.orElseThrow(() -> new DroneNotFoundException());
		assertEquals(State.LOADING, testDrone.getState());
		assertThrowsExactly(DroneNotFoundException.class, () -> dronesService.loadDrone(droneMedication1));
		assertThrowsExactly(MedicationNotFoundException.class, () -> dronesService.loadDrone(droneMedication2));
		Drone drone11 = droneRepo.findById("number2").orElseThrow(() -> new DroneNotFoundException());
		drone11.setBatteryCapacity(15);
		droneRepo.save(drone11);
		DroneMedication droneMedication3 = new DroneMedication("number2", "CODE1");
		assertThrowsExactly(BatteryIllegalStateException.class, () -> dronesService.loadDrone(droneMedication3));
		Drone drone12 = droneRepo.findById("number3").orElseThrow(() -> new DroneModelNotFoundException());
		drone12.setState(State.DELIVERING);
		droneRepo.save(drone12);
		DroneMedication droneMedication4 = new DroneMedication("number3", "CODE1");
		assertThrowsExactly(StateIllegalStateException.class, () -> dronesService.loadDrone(droneMedication4));
	}
}
