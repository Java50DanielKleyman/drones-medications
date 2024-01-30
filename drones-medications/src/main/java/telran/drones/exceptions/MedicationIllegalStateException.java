package telran.drones.exceptions;

import telran.drones.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class MedicationIllegalStateException extends IllegalStateException {
	public MedicationIllegalStateException() {
		super(ServiceExceptionMessages.MEDICATION_ALREADY_EXISTS);
	}
}
