package telran.drones.model;
//TODO create entity Medication

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import telran.drones.dto.MedicationDto;

@Entity
@Table(name = "medications")
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
	@Id
	@Column(name = "medication_code", nullable = false)
	String code;
	@Column(name = "medication_name", nullable = false)
	String name;
	@Column(name = "medication_weight", nullable = false)
	int weight;

	static public Medication of(MedicationDto medicationDto) {
		return new Medication(medicationDto.code(), medicationDto.name(), medicationDto.weight());
	}

	public MedicationDto build() {
		return new MedicationDto(code, name, weight);
	}
}
