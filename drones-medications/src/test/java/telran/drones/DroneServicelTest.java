package telran.drones;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import telran.drones.dto.DroneDto;
import telran.drones.dto.ModelType;
import telran.drones.exceptions.DroneIllegalStateException;
import telran.drones.model.Drone;
import telran.drones.repo.DroneRepo;
import telran.drones.service.DronesService;

@SpringBootTest

class DroneServicelTest {
	@Autowired
	DronesService dronesService;
	@Autowired
	DroneRepo droneRepo;
	DroneDto droneDto = new DroneDto("number6", ModelType.Lightweight);

	@Test
	void registerDroneTest() {
		assertEquals(droneDto, dronesService.registerDrone(droneDto));
		assertThrowsExactly(DroneIllegalStateException.class, () -> dronesService.registerDrone(droneDto));
		Drone drone = droneRepo.findById(droneDto.number()).orElse(null);
		assertEquals(droneDto, drone.build());
	}

}
