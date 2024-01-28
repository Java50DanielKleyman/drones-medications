package telran.drones.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.drones.dto.DroneDto;
import telran.drones.dto.DroneMedication;
import telran.drones.dto.MedicationDto;
import telran.drones.service.DronesService;

@RestController
@RequestMapping("drones")
@RequiredArgsConstructor
@Slf4j
public class DronesController {
	final DronesService dronesService;

	@PostMapping
	DroneDto registerDrone(@RequestBody @Valid DroneDto droneDto) {
		log.debug("registerDrone received {}", droneDto);
		return dronesService.registerDrone(droneDto);
	}

	@PostMapping("load")
	DroneMedication loadDrone(@RequestBody @Valid DroneMedication droneMedication) {
		log.debug("loadDrone received {}", droneMedication);
		return dronesService.loadDrone(droneMedication);
	};
	@PostMapping("medication")
	MedicationDto addMedication(@RequestBody @Valid MedicationDto medicationDto) {
		log.debug("medicationDto received {}", medicationDto);
		return dronesService.addMedication(medicationDto);
	}
}
