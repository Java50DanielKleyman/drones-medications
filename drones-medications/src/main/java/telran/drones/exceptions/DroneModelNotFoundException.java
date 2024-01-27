package telran.drones.exceptions;

import telran.drones.api.ServiceExceptionMessages;

@SuppressWarnings("serial")
public class DroneModelNotFoundException extends NotFoundException {
	public DroneModelNotFoundException() {
		super(ServiceExceptionMessages.DRONE_MODEL_NOT_FOUND);
		
	}
}
