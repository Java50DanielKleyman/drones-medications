package telran.drones.exceptions;

import telran.drones.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class StateIllegalStateException extends IllegalStateException {
	public StateIllegalStateException() {
		super(ServiceExceptionMessages.STATE_NOT_IDLE);
	}
}
