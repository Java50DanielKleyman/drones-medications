package telran.drones.dto;

import static telran.drones.api.ValidationConstants.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record MedicationDto(
		@NotEmpty(message = MISSING_MEDICATION_CODE) @Pattern(regexp = MEDICATION_CODE_REGEXP, message = WRONG_MEDICATION_CODE) String code, 
		@NotEmpty(message = MISSING_MEDICATION_NAME) @Pattern(regexp = MEDICATION_NAME_REGEXP, message = WRONG_MEDICATION_NAME)String name, 
		@NotEmpty(message = MISSING_MEDICATION_WEIGHT) int weight) {

}
