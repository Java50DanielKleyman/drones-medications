package telran.drones.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.drones.model.*;

public interface EventLogRepo extends JpaRepository<EventLog, Long> {
	@Query("""
			select medicationCode
			from EventLog
			where droneNumber= :droneNumber
			and state="loading"
			group by medicationCode
			order by count(*) desc

			""")
	List<String> findMedicationsByDroneNumber(String droneNumber);
}