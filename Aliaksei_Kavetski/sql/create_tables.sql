use mydb;
create table if not exists requests
(
    id          bigint auto_increment
        primary key,
    description varchar(250) null
);

create table if not exists roles
(
    id   int auto_increment
        primary key,
    name varchar(100) not null
);

create table if not exists routes
(
    id          bigint auto_increment
        primary key,
    status      varchar(200) null,
    description varchar(200) null,
    start_date  varchar(200) null,
    end_date    varchar(200) null
);

create table if not exists users
(
    id         int auto_increment
        primary key,
    username   varchar(45) not null,
    password   varchar(45) not null,
    first_name varchar(45) not null,
    last_name  varchar(45) not null,
    role       varchar(45) null
);

create table if not exists cars
(
    id          bigint auto_increment
        primary key,
    car_model   varchar(200) null,
    description varchar(200) null,
    car_status  varchar(200) null,
    user_id     int          not null,
    constraint user_id_fk
        foreign key (user_id) references users (id)
            on update cascade
);

create table if not exists user_roles
(
    user_id int not null,
    role_id int not null,
    constraint user_roles_ibfk_1
        foreign key (user_id) references users (id),
    constraint user_roles_ibfk_2
        foreign key (role_id) references roles (id)
);

create index role_id
    on user_roles (role_id);

create index user_id
    on user_roles (user_id);

