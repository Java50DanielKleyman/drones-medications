package telran.drones.exceptions;

import telran.drones.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class IllegalMedicationStateException extends IllegalStateException {
	public IllegalMedicationStateException() {
		super(ServiceExceptionMessages.MEDICATION_ALREADY_EXISTS);
	}
}
