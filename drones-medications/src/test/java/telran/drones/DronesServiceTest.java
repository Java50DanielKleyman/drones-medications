package telran.drones;

import telran.drones.api.*;
import telran.drones.dto.*;
import telran.drones.model.*;
import telran.drones.exceptions.*;

import telran.drones.repo.*;
import telran.drones.service.DronesService;
import telran.drones.service.DronesServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Sql(scripts = "classpath:test_data.sql")
@Slf4j
class DronesServiceTest {
	private static final String DRONE1 = "Drone-1";
	private static final String DRONE2 = "Drone-2";
	private static final String DRONE3 = "Drone-3";
	private static final String DRONE4 = "Drone-4";

	private static final String MED1 = "MED_1";
	private static final String MED2 = "MED_2";
	private static final String MED3 = "MED_3";
	private static final String MED4 = "MED_4";
	private static final String SERVICE_TEST = "Service: ";

	@Autowired
	DronesService dronesService;
	@Autowired
	DronesRepo droneRepo;
	@Autowired
	EventLogRepo logRepo;
	DroneDto droneDto = new DroneDto(DRONE4, ModelType.Cruiserweight);
	DroneDto drone1 = new DroneDto(DRONE1, ModelType.Middleweight);
	Drone drone11 = Drone.of(drone1);

	EventLog[] eventLogs = { new EventLog(LocalDateTime.now(), DRONE1, State.LOADING, 50, MED1),
			new EventLog(LocalDateTime.now(), DRONE1, State.LOADING, 50, MED2),
			new EventLog(LocalDateTime.now(), DRONE1, State.LOADING, 50, MED2),
			new EventLog(LocalDateTime.now(), DRONE1, State.LOADING, 50, MED3),
			new EventLog(LocalDateTime.now(), DRONE1, State.LOADING, 50, MED3),
			new EventLog(LocalDateTime.now(), DRONE1, State.LOADING, 50, MED3),
			new EventLog(LocalDateTime.now(), DRONE2, State.LOADING, 50, MED4),
			new EventLog(LocalDateTime.now(), DRONE3, State.LOADING, 50, MED2) };
	DroneMedication droneMedication1 = new DroneMedication(DRONE1, MED1);
	List<String> expected = Arrays.asList(MED3, MED2, MED1);
	List<String> expected1 = Arrays.asList(DRONE1, DRONE2);

	@Test
	@DisplayName(SERVICE_TEST + TestDisplayNames.LOAD_DRONE_NORMAL)
	void loadDroneNormal() {
		dronesService.loadDrone(droneMedication1);
		List<EventLog> logs = logRepo.findAll();
		assertEquals(1, logs.size());
		EventLog loadingLog = logs.get(0);
		String droneNumber = loadingLog.getDroneNumber();
		State state = loadingLog.getState();
		String medicationCode = loadingLog.getMedicationCode();
		assertEquals(DRONE1, droneNumber);
		assertEquals(State.LOADING, state);
		assertEquals(MED1, medicationCode);
		Drone drone = droneRepo.findById(DRONE1).orElseThrow();
		assertEquals(State.LOADING, drone.getState());
	}

	@Test
	@DisplayName(SERVICE_TEST + TestDisplayNames.LOAD_DRONE_NOT_MATCHING_STATE)
	void loadDroneWrongState() {
		assertThrowsExactly(IllegalDroneStateException.class,
				() -> dronesService.loadDrone(new DroneMedication(DRONE3, MED1)));
	}

	@Test
	@DisplayName(SERVICE_TEST + TestDisplayNames.LOAD_DRONE_MEDICATION_NOT_FOUND)
	void loadDroneMedicationNotFound() {
		assertThrowsExactly(MedicationNotFoundException.class,
				() -> dronesService.loadDrone(new DroneMedication(DRONE1, "KUKU")));
	}

	@Test
	@DisplayName(SERVICE_TEST + TestDisplayNames.LOAD_DRONE_NOT_FOUND)
	void loadDroneNotFound() {
		assertThrowsExactly(DroneNotFoundException.class,
				() -> dronesService.loadDrone(new DroneMedication(DRONE4, MED1)));
	}

	@Test
	@DisplayName(SERVICE_TEST + TestDisplayNames.REGISTER_DRONE_NORMAL)
	void registerDroneNormal() {
		assertEquals(droneDto, dronesService.registerDrone(droneDto));
		assertTrue(droneRepo.existsById(DRONE4));

	}

	@Test
	@DisplayName(SERVICE_TEST + TestDisplayNames.REGISTER_DRONE_ALREADY_EXISTS)
	void registerDroneAlreadyExists() {
		assertThrowsExactly(DroneAlreadyExistException.class, () -> dronesService.registerDrone(drone1));
	}

	@Test
	@DisplayName(SERVICE_TEST + TestDisplayNames.CHECK_MED_ITEMS_NORMAL)
	void checkMedicalItemsNormal() {
		for (EventLog el : eventLogs) {
			logRepo.save(el);
		}
		assertEquals(expected, dronesService.checkMedicationItems(DRONE1));
	}

	@Test
	@DisplayName(SERVICE_TEST + TestDisplayNames.CHECK_MED_ITEMS_DRONE_NOT_FOUND)
	void checkMedicalItemsDroneNotFound() {
		assertThrowsExactly(DroneNotFoundException.class, () -> dronesService.checkMedicationItems(DRONE4));
	}

	@Test
	@DisplayName(SERVICE_TEST + TestDisplayNames.AVAILABLE_DRONES)
	void checkAvailableForLoadingDrones() {
		List<String> actual = dronesService.checkAvailableDrones();
		assertEquals(expected1, actual);
	}

	@Test
	@DisplayName(SERVICE_TEST + TestDisplayNames.CHECK_BATTERY_LEVEL_NORMAL)
	void checkBatteryLevelNormal() {
		assertEquals(drone11.getBatteryCapacity(), dronesService.checkBatteryCapacity(drone11.getNumber()));
		Drone drone = droneRepo.findById(DRONE2).orElseThrow(() -> new ModelNotFoundException());
		assertEquals(drone.getBatteryCapacity(), dronesService.checkBatteryCapacity(drone.getNumber()));
	}

	@Test
	@DisplayName(SERVICE_TEST + TestDisplayNames.CHECK_BATTERY_LEVEL_DRONE_NOT_FOUND)
	void checkBatteryLevelDroneNotFound() {
		assertThrowsExactly(DroneNotFoundException.class, () -> dronesService.checkBatteryCapacity(DRONE4));
	}
	@Test
	@DisplayName(SERVICE_TEST + TestDisplayNames.CHECK_DRONES_ITEMS_AMOUNT) 
	void checkDroneLoadedItemAmounts() {
		
	}
}