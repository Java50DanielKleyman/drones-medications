package telran.drones.exceptions;

import telran.drones.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class IllegalBatteryStateException extends IllegalStateException {
	public IllegalBatteryStateException() {
		super(ServiceExceptionMessages.BATTERY_CAPACITY_STATE);
	}
}
