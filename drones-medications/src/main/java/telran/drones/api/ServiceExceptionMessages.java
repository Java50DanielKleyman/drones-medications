package telran.drones.api;

public interface ServiceExceptionMessages {

	String DRONE_NOT_FOUND = "Drone not found";
	String MEDICATION_NOT_FOUND = "Medication not found";
	String DRONE_ALREADY_EXISTS = "Drone with a given number already exists ";
	String MEDICATION_NOT_EXISTS = "Medication does not exist ";
	String STATE_NOT_IDLE = "Drone is not in idle state now";
	String BATTERY_CAPACITY_STATE = "Battery capacity is lower then 25%";
	String DRONE_MODEL_NOT_FOUND = "Drone model not found";
}