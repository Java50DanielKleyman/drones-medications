package telran.drones.service;

import java.util.List;
import java.util.Map;

import telran.drones.dto.DroneDto;
import telran.drones.dto.DroneMedication;
import telran.drones.dto.MedicationDto;
import telran.drones.model.Drone;
import telran.drones.model.EventLog;
import telran.drones.model.Medication;

public interface DronesService {
   /**
    * adds new Drone into Database
    * @param droneDto
    * @return DroneDto for success
    * @throws DroneIllegalStateException (drone with a given number already exists)
    */
	DroneDto registerDrone(DroneDto droneDto);
	/************************************************************/
	/**
	 * checks whether a given drone available for loading (state IDLE,
	 *  battery capacity >= 25%, weight match)
	 *  checks whether a given medication exists
	 *  checks whether a given drone model exists
	 *  creates event log with the state LOADING and current battery capcity
	 * @param droneMedication
	 * @return DroneMedication for success
	 * @throws appropriate exception in accordance with the required checks
	 */
   DroneMedication loadDrone(DroneMedication droneMedication); 
   MedicationDto addMedication(MedicationDto medicationDto);
   List<Medication> checkLoadedMedicationItems(Drone drone);
   List<Drone> checkAvailableDrones();
   int checkDroneBatteryLevel(Drone drone);
   EventLog checkEventLog(Drone drone);
   Map<String, Long> checkAmountOfMedicationForEachDrone (List<DroneMedication> droneMedicationList);
}