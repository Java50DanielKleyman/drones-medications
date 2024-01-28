package telran.drones;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.drones.dto.DroneDto;
import telran.drones.dto.DroneMedication;
import telran.drones.dto.ModelType;
import telran.drones.service.DronesService;

@WebMvcTest // inserting into Application Context Mock WEB server instead of real WebServer
class DronesControllerTest {
	private static final String DRONE_NOT_FOUND = "Drone not found";
	private static final String MEDICATION_NOT_FOUND = "Medication not found";
	private static final String DRONE_ALREADY_EXISTS = "Drone with a given number already exists ";
	private static final String MEDICATION_NOT_EXISTS = "Medication does not exist ";
	private static final String STATE_NOT_IDLE = "Drone is not in idle state now";
	private static final String BATTERY_CAPACITY_STATE = "Battery capacity is lower then 25%";
	private static final String DRONE_MODEL_NOT_FOUND = "Drone model not found";
	@MockBean // inserting into Application Context Mock instead of real Service
				// implementation
	DronesService dronesService;
	@Autowired // for injection of MockMvc from Application Context
	MockMvc mockMvc;
	@Autowired // for injection of ObjectMapper from Application context
	ObjectMapper mapper; // object for getting JSON from object and object from JSON

	DroneDto droneDto = new DroneDto("A12345", ModelType.Lightweight);
	DroneMedication droneMedication = new DroneMedication("A12345", "CODE1");

	@Test	
	void testRegisterDrone() throws Exception {
		when(dronesService.registerDrone(droneDto)).thenReturn(droneDto);
		String jsonDroneDto = mapper.writeValueAsString(droneDto);
		String actualJSON = mockMvc.perform(post("http://localhost:8080/drones").contentType(MediaType.APPLICATION_JSON)
				.content(jsonDroneDto)).andExpect(status().isOk()).andReturn().getResponse()
		.getContentAsString();
		assertEquals(jsonDroneDto, actualJSON );
	}

	@Test	
	void testLoadDrone() throws Exception{
		when(dronesService.loadDrone(droneMedication)).thenReturn(droneMedication);
		String jsonDroneMedication = mapper.writeValueAsString(droneMedication);
		String actualJSON = mockMvc.perform(post("http://localhost:8080/drones/load").contentType(MediaType.APPLICATION_JSON)
				.content(jsonDroneMedication)).andExpect(status().isOk()).andReturn().getResponse()
		.getContentAsString();
		assertEquals(jsonDroneMedication, actualJSON );
	}
}
