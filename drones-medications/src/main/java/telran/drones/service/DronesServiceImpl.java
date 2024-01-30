package telran.drones.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.drones.dto.DroneDto;
import telran.drones.dto.DroneMedication;
import telran.drones.dto.MedicationDto;
import telran.drones.dto.State;
import telran.drones.exceptions.*;
import telran.drones.model.*;
import telran.drones.repo.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class DronesServiceImpl implements DronesService {
	final DroneRepo droneRepo;
	final DroneModelRepo droneModelRepo;
	final EventLogRepo eventLogRepo;
	final MedicationRepo medicationRepo;

	@Override
	@Transactional
	public DroneDto registerDrone(DroneDto droneDto) {
		if (droneRepo.existsById(droneDto.number())) {
			throw new DroneIllegalStateException();
		}
		Drone drone = Drone.of(droneDto);
		DroneModel model = droneModelRepo.findById(droneDto.modelType())
				.orElseThrow(() -> new DroneModelNotFoundException());
		drone.setModel(model);
		droneRepo.save(drone);
		log.debug("Drone {} has been registered", drone);
		return droneDto;
	}

	@Override
	public DroneMedication loadDrone(DroneMedication droneMedication) {
		if (!droneRepo.existsById(droneMedication.droneNumber())) {
			throw new DroneNotFoundException();
		}
		if (!medicationRepo.existsById(droneMedication.medicationCode())) {
			throw new MedicationNotFoundException();
		}
		Drone drone = droneRepo.findById(droneMedication.droneNumber()).orElseThrow(() -> new DroneNotFoundException());
		if (drone.getBatteryCapacity() < 25) {
			throw new BatteryIllegalStateException();
		}
		if (!drone.getState().equals(State.IDLE)) {
			throw new StateIllegalStateException();
		}
		drone.setState(State.LOADING);
		EventLog eventLog = new EventLog(LocalDateTime.now(), drone.getNumber(), drone.getState(),
				drone.getBatteryCapacity());
		droneRepo.save(drone);
		eventLogRepo.save(eventLog);
		log.debug("Event log {} created, drone {} state updated to LOADING", eventLog, drone);
		return droneMedication;

	}

	@Override
	public MedicationDto addMedication(MedicationDto medicationDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medication> checkLoadedMedicationItems(Drone drone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Drone> checkAvailableDrones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int checkDroneBatteryLevel(Drone drone) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EventLog checkEventLog(Drone drone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Long> checkAmountOfMedicationForEachDrone(List<DroneMedication> droneMedicationList) {
//		  Map<String, Long> medicationCountByDrone = droneMedicationList.stream()
//	                .collect(Collectors.groupingBy(DroneMedication::getDroneNumber, Collectors.counting()));
//
//	        // Sorting the map by the count in descending order
//	        medicationCountByDrone = medicationCountByDrone.entrySet().stream()
//	                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
//	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		return null;
	}

}
