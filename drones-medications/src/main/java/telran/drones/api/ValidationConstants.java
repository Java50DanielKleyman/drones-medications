package telran.drones.api;

public interface ValidationConstants {
	int WAX_CHARACTERS_NUMBER = 100;
	String WRONG_DRONE_SERIAL_NUMBER = "Drone's serial number must be maximum of 100 characters";	
	String MEDICATION_NAME_REGEXP = "^[a-zA-Z0-9_-]+$";
	String MEDICATION_CODE_REGEXP = "^[A-Z0-9_]+$";
	String WRONG_MEDICATION_NAME = "Medication name must be only letters, numbers, ‘-‘, ‘_’";
	String WRONG_MEDICATION_CODE = "Medication code must be only upper case letters, underscore and numbers";
	String MISSING_DRONE_NUMBER = "Missing drone serial number";	
	String MISSING_MEDICATION_NAME = "Missing medication name";
	String MISSING_MEDICATION_CODE = "Missing medication code";
	String MISSING_MEDICATION_WEIGHT = "Missing medication weight";
}