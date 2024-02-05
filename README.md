# Assumptions
1. Introducing intermediate Drone's states related to unit time of periodic task: (IDLE, LOADING, LOADED, DELIVERING, DELIVERING1, DELIVERING2, DELIVERING3, DELIVERED, RETURNING, RETURNING1, RETURNING2, RETURNING3)
2. Introducing battery capacity changes: in state IDLE battery in charging for 2% every selected period of time, in other states discharging for 2%.
# Running instractions
1. Download ZIP from GitHub repository.
2. Enter the directory with unzipped file and run command mvnw package.
3. As a result there should be created jar file in the subdirectory target.
4. Enter subdirectory target and run command java –jar drones-medications-0.0.1.jar
# API
1. /drones - POST method with DronDto DTO object.
2. /drones/load - POST method with Drone DTO object.
3. /drones/items/ - GET method with DroneNumber string.
4. /drones/available - GET method.
5. /drones/battery/ - GET method with DroneNumber string.
6. /dronesamount/items - GET method.
# SQL scrypt for initial DB population
insert into drone_models (model_name, weight) values<br>
('Lightweight', 100),<br>
('Middleweight', 300),<br>
('Cruiserweight', 400),<br>
('Heavyweight', 500);<br>
insert into drones (drone_number, model_name, battery_capacity, state) values<br>
('Drone-1', 'Middleweight', 100, 'IDLE'),<br>
('Drone-2', 'Middleweight', 100, 'IDLE'),<br>
('Drone-3', 'Cruiserweight', 100, 'IDLE'),<br>
('Drone-4', 'Heavyweight', 100, 'IDLE'),<br>
('Drone-5', 'Lightweight', 100, 'IDLE'),<br>
('Drone-6', 'Heavyweight', 100, 'IDLE'),<br>
('Drone-7', 'Lightweight', 100, 'IDLE'),<br>
('Drone-8', 'Heavyweight', 100, 'IDLE'),<br>
('Drone-9', 'Middleweight', 100, 'IDLE'),v ('Drone-10', 'Cruiserweight', 100, 'IDLE');<br>
insert into medications (code, name, weight) values<br>
('MED_1', 'Medication-1', 200),<br>
('MED_2', 'Medication-2', 200),<br>
('MED_3', 'Medication-3', 200);<br>
