package telran.drones.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.drones.dto.State;
import telran.drones.model.*;

public interface DronesRepo extends JpaRepository<Drone, String> {
	@Query("""
			select number from Drone where state= :state
			""")
	List<String> findDronesByState(State state);
	@Query("""
			select batteryCapacity from Drone where number= :number
			""")
	int findBatteryCapacityByNumber(String number);
}