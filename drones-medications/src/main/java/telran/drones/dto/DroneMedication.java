package telran.drones.dto;

import static telran.drones.api.ValidationConstants.MAX_CHARACTERS_NUMBER;
import static telran.drones.api.ValidationConstants.MEDICATION_CODE_REGEXP;
import static telran.drones.api.ValidationConstants.MISSING_DRONE_NUMBER;
import static telran.drones.api.ValidationConstants.MISSING_MEDICATION_CODE;
import static telran.drones.api.ValidationConstants.WRONG_DRONE_SERIAL_NUMBER;
import static telran.drones.api.ValidationConstants.WRONG_MEDICATION_CODE;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//TODO add validation constraints
public record DroneMedication(
		@NotEmpty(message = MISSING_DRONE_NUMBER) @Size(max = MAX_CHARACTERS_NUMBER, message = WRONG_DRONE_SERIAL_NUMBER) String droneNumber,
		@NotEmpty(message = MISSING_MEDICATION_CODE) @Pattern(regexp = MEDICATION_CODE_REGEXP, message = WRONG_MEDICATION_CODE) String medicationCode) {

}