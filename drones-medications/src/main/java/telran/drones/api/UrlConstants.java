package telran.drones.api;

public interface UrlConstants {

	String DRONES = "drones";
	String LOAD_DRONE = DRONES + "/load";
	String CHECK_MEDICATION_ITEMS = DRONES + "/checkMedicationItems/{droneNumber}";
	String CHECK_AVAILABLE_DRONES = DRONES + "/checkAvailableDrones";
	String CHECK_BATERY_CAPACITY = DRONES + "/checkBatteryCapacity/{droneNumber}";
	String CHECK_DRONE_LOADED_ITEM_AMOUNT = DRONES + "/checkDroneLoadedItemAmounts";
}