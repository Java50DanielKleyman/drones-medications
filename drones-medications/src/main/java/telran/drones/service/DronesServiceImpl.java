package telran.drones.service;

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
import telran.drones.exceptions.DroneIllegalStateException;
import telran.drones.model.Drone;
import telran.drones.model.EventLog;
import telran.drones.model.Medication;
import telran.drones.repo.DroneModelRepo;
import telran.drones.repo.DroneRepo;
import telran.drones.repo.EventLogRepo;
import telran.drones.repo.MedicationRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class DronesServiceImpl implements DronesService {
	final DroneRepo droneRepo;
	final DroneModelRepo droneModelRepo;
	final EventLogRepo ventLogRepo;
	final MedicationRepo medicationRepo;

	@Override
	@Transactional
	public DroneDto registerDrone(DroneDto droneDto) {
		if(droneRepo.existsById(droneDto.number())) {
			throw new DroneIllegalStateException();
		}
		Drone drone = Drone.of(droneDto);
		droneRepo.save(drone);
		log.debug("Drone {} has been registered", drone);
		return droneDto;
	}

	@Override
	public DroneMedication loadDrone(DroneMedication droneMedication) {
		// TODO Auto-generated method stub
		return null;
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
