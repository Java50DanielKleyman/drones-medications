package telran.drones;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.drones.dto.*;
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

	private static String WRONG_DRONE_SERIAL_NUMBER = "Drone's serial number must be maximum of 100 characters";
	private static String WRONG_MEDICATION_NAME = "Medication name must be only letters, numbers, ‘-‘, ‘_’";
	private static String WRONG_MEDICATION_CODE = "Medication code must be only upper case letters, underscore and numbers";
	private static String MISSING_DRONE_NUMBER = "Missing drone serial number";
	private static String MISSING_MEDICATION_NAME = "Missing medication name";
	private static String MISSING_MEDICATION_CODE = "Missing medication code";
	private static String MISSING_MEDICATION_WEIGHT = "Missing medication weight";
	private static String WRONG_DRON_SERIAL_NUMBER = "11111111111111111111111111111111111111111111111111111xvxcvxvxvxcvxvxvxvxvxvcxvxvxvxxvx111111111111111111111111111111111111";
	@MockBean // inserting into Application Context Mock instead of real Service
				// implementation
	DronesService dronesService;
	@Autowired // for injection of MockMvc from Application Context
	MockMvc mockMvc;
	@Autowired // for injection of ObjectMapper from Application context
	ObjectMapper mapper; // object for getting JSON from object and object from JSON

	DroneDto droneDto = new DroneDto("A12345", ModelType.Lightweight);
	DroneDto droneDto1 = new DroneDto(WRONG_DRON_SERIAL_NUMBER, ModelType.Lightweight);
	DroneDto droneDto2 = new DroneDto(null, ModelType.Lightweight);
	DroneMedication droneMedication = new DroneMedication("A12345", "CODE1");
	MedicationDto medicationDto = new MedicationDto("ABC1", "medication", 150);
	MedicationDto medicationDto1 = new MedicationDto("ABhdhdh1", "medication", 150);
	MedicationDto medicationDto2 = new MedicationDto("ABC1", "medica@@@tion", 150);
	MedicationDto medicationDto3 = new MedicationDto(null, null, 0);

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

	@Test	
	void testAddMedication() throws Exception{
		when(dronesService.addMedication(medicationDto)).thenReturn(medicationDto);
		String jsonMedicationDto = mapper.writeValueAsString(medicationDto);
		String actualJSON = mockMvc.perform(post("http://localhost:8080/drones/medication").contentType(MediaType.APPLICATION_JSON)
				.content(jsonMedicationDto)).andExpect(status().isOk()).andReturn().getResponse()
		.getContentAsString();
		assertEquals(jsonMedicationDto, actualJSON );
	}

	/***********
	 * ALternative flows - Validation Exceptions Handling
	 * 
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 *************/
	@Test
	void wrongDroneSerialNumberTest() throws Exception {
		String jsonDroneDto = mapper.writeValueAsString(droneDto1);
		String response = mockMvc
				.perform(post("http://localhost:8080/drones").contentType(MediaType.APPLICATION_JSON)
						.content(jsonDroneDto))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		assertEquals(WRONG_DRONE_SERIAL_NUMBER, response);
	}

	@Test
	void missingDroneSerialNumberTest() throws Exception {
		String jsonDroneDto = mapper.writeValueAsString(droneDto2);
		String response = mockMvc
				.perform(post("http://localhost:8080/drones").contentType(MediaType.APPLICATION_JSON)
						.content(jsonDroneDto))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		assertEquals(MISSING_DRONE_NUMBER, response);
	}

	@Test
	void wrongMedicationCode() throws Exception {
		String jsonDroneDto = mapper.writeValueAsString(medicationDto1);
		String response = mockMvc
				.perform(post("http://localhost:8080/drones/medication").contentType(MediaType.APPLICATION_JSON)
						.content(jsonDroneDto))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		assertEquals(WRONG_MEDICATION_CODE, response);
	}

	@Test
	void wrongMedicationName() throws Exception {
		String jsonDroneDto = mapper.writeValueAsString(medicationDto2);
		String response = mockMvc
				.perform(post("http://localhost:8080/drones/medication").contentType(MediaType.APPLICATION_JSON)
						.content(jsonDroneDto))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		assertEquals(WRONG_MEDICATION_NAME, response);
	}
}
