package telran.drones.exceptions;

import telran.drones.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class BatteryIllegalStateException extends IllegalStateException {
	public BatteryIllegalStateException() {
		super(ServiceExceptionMessages.BATTERY_CAPACITY_STATE);
	}
}
