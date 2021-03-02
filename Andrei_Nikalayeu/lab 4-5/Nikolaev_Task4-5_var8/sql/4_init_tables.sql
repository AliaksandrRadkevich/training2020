INSERT INTO `role` 
(`value`) 
values 
("ADMINISTRATOR");

INSERT INTO `role` 
(`value`) 
values 
("CLIENT");


INSERT INTO `user` 
(`name`,    `surname`,  `email`,            `login`,    `password`, `role_id`) 
values 
("Андрей",  "Николаев", "andrei@hotel.com", "andrei",   "12345an",          1);

INSERT INTO `user` 
(`name`,    `surname`,  `email`,            `login`,    `password`, `role_id`) 
values 
("Админ",   "Новый",    "admin@hotel.com",  "admin",    "12345adm",         1);

INSERT INTO `user` 
(`name`,    `surname`,  `email`,            `login`,    `password`, `role_id`) 
values 
("Максим",  "Иванов",   "maxim@hotel.com",  "maxim",    "12345ma",          2);

INSERT INTO `user` 
(`name`,    `surname`,  `email`,            `login`,    `password`, `role_id`) 
values 
("Александр","Петров",  "alexandr@hotel.com","alex",    "12345al",          2);

INSERT INTO `user` 
(`name`,    `surname`,  `email`,            `login`,    `password`, `role_id`) 
values 
("Ярослав", "Анисимов", "yaroslav@hotel.com","yaroslav","12345go",          2);

INSERT INTO `user` 
(`name`,    `surname`,  `email`,            `login`,    `password`, `role_id`) 
values 
("Денис",   "Голованов","denis@hotel.com",  "denis",    "12345gn",          2);

INSERT INTO `user` 
(`name`,    `surname`,  `email`,            `login`,    `password`, `role_id`) 
values 
("Игорь",   "Григорьев","igor@hotel.com",   "igor",     "12345gn",          2);

INSERT INTO `room_type` 
(`value`) 
values 
("STANDART");

INSERT INTO `room_type` 
(`value`) 
values 
("SUITE");

INSERT INTO `room_type` 
(`value`) 
values 
("LUX");

INSERT INTO `room_type` 
(`value`) 
values 
("APARTMENT");


INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("1",           1,          1,      20.20);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("2",           1,          2,      30.25);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("3",           2,          3,      40.00);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values ("4",    2,          3,      42.00);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("4a",          3,          1,      50.10);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("4б",          3,          2,      70.15);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("5",           4,          5,      122.22);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("6",           4,          6,      142.22);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("7",           4,          3,      80.20);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("8",           2,          2,      35.40);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("8a",          3,          1,      60.00);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("9",           1,          1,      22.22);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("11",          1,          3,      32.22);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("12",          1,          3,      30.12);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("13",          1,          3,      20.25);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("14",          1,          3,      24.62);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("14а",         1,          3,      27.22);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`)
values 
("14б",         3,          3,      105.00);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("15",          1,          3,      42.22);

INSERT INTO `room` 
(`room_number`, `type_id`, `seats`, `price`) 
values 
("16",          1,          3,      37.26);

INSERT INTO `order` 
(`creation_date`,       `user_id`, `room_seats`, `type_id`, `start_date`, `end_date`, `room_id`) 
values 
('2021-01-10 16:46:43', 3,          3,            3,        '2021-01-11', '2021-01-15',18);

INSERT INTO `order` 
(`creation_date`,       `user_id`, `room_seats`, `type_id`, `start_date`, `end_date`, `room_id`) 
values 
('2021-01-10 17:30:40', 4,          3,            1,        '2021-01-15', '2021-01-17',13);

INSERT INTO `order` 
(`creation_date`,       `user_id`, `room_seats`, `type_id`, `start_date`, `end_date`, `room_id`) 
values 
('2021-01-20 10:29:01', 5,          1,            3,        '2021-01-18', '2021-01-21',NULL);

INSERT INTO `order` 
(`creation_date`,       `user_id`, `room_seats`, `type_id`, `start_date`, `end_date`, `room_id`) 
values 
('2021-01-20 10:29:01', 5,          1,            3,        '2021-02-10', '2021-02-15',NULL);

INSERT INTO `order` 
(`creation_date`,       `user_id`, `room_seats`, `type_id`, `start_date`, `end_date`, `room_id`) 
values 
('2021-01-20 10:29:01', 6,          1,            3,        '2021-03-03', '2021-03-17',NULL);

