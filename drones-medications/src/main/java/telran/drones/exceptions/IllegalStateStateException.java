package telran.drones.exceptions;

import telran.drones.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class IllegalStateStateException extends IllegalStateException {
	public IllegalStateStateException() {
		super(ServiceExceptionMessages.STATE_NOT_IDLE);
	}
}
