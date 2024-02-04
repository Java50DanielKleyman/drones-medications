package telran.drones;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"telran"})
public class DronesMedicationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DronesMedicationsApplication.class, args);
	}
}