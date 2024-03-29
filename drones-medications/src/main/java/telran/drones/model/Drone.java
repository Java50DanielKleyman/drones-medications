package telran.drones.model;

import jakarta.persistence.*;
import lombok.*;
import telran.drones.dto.DroneDto;
import telran.drones.dto.State;

@Entity
@Table(name = "drones")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Drone {
	@Id
	@Column(length = 100, name = "drone_number")
	String number;
	@ManyToOne
	@JoinColumn(name = "model_name")
	@Setter
	DroneModel model;	
	@Column(name = "battery_capacity")
	@Setter
	int batteryCapacity;
	@Enumerated(EnumType.STRING)
	@Column(name="drone_state")
	@Setter
	State state;

	static public Drone of(DroneDto droneDto) {
		return new Drone(droneDto.number(), null, 100, State.IDLE);
	}

	public DroneDto build() {
		return new DroneDto(number, model.getModel_name());
	}
	@Override
	public String toString() {
		return "Drone [number=" + number + ", model=" + model.getModel_name() + ", batteryCapacity=" + batteryCapacity + ", state=" + state + "]";
	}
}