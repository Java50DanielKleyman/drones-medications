delete from drones;
delete from drone_models;
delete from event_logs;
delete from medications;
insert into drones (drone_number, model_name, battery_capacity, drone_state ) values
{'number1', 'Lightweight',  100, 'IDLE'},
{'number2', 'Middleweight',  100, 'IDLE'},
{'number3', 'Middleweight',  100, 'IDLE'},
{'number4', 'Cruiserweight',  100, 'IDLE'},
{'number5', 'Heavyweight',  100, 'IDLE'};
insert into drone_models (modelName, weight) values
{'Lightweight', 100 },
{'Middleweight', 200},
{'Cruiserweight', 300},
{'Heavyweight', 450};
insert into medications (medication_code, medication_name, medication_weight) values
{ 'CODE1', 'medication1', 30},
{ 'CODE2', 'medication2', 50},
{ 'CODE3', 'medication3', 70},
{ 'CODE4', 'medication4', 100},
{ 'CODE5', 'medication5', 150};
insert into event_logs (timestamp, drone_number, state, battery_capacity) values
('2024-01-27 15:30:00', 'number1', 'IDLE', 100),
('2024-01-27 16:45:00', 'number2', 'LOADING', 80),
('2024-01-27 17:20:00', 'number3', 'LOADED', 60),
('2024-01-27 18:10:00', 'number4', 'DELIVERING', 40),
('2024-01-27 19:00:00', 'number5', 'DELIVERED', 20),
('2024-01-27 20:30:00', 'number1', 'RETURNING', 70),
('2024-01-27 21:15:00', 'number2', 'IDLE', 90),
('2024-01-27 22:05:00', 'number3', 'LOADING', 60),
('2024-01-27 23:20:00', 'number4', 'LOADED', 30),
('2024-01-28 00:45:00', 'number5', 'RETURNING', 80);

