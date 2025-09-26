create database Garage;

use Garage;

create table customer(
  id int auto_increment primary key,
  cust_name varchar(25) not null,
  phone varchar(30)
);

show tables;
insert into customer(id, cust_name, phone) values(101, 'mahesh', 8329904289);
insert into customer(custd_idst_name, phone) values('rahul', 8329904289);
	select * from customer;

create table vehicle(
  id int auto_increment primary key,
  cust_id int,
  number_plate varchar(20) not null,
  model varchar(15),
  foreign key(cust_id) references customer(id)
);



select * from vehicle;

create table services(
  id int auto_increment primary key,
  description varchar(30),
  cost double
);

show tables;

insert into services(description, cost) values 	
('oil change', 1500),
('Engine repair', 5000),
('Tyre replacement', 3000),
('Washing', 500);

select * from services;

create table invoice(
   id int auto_increment primary key,
   cust_id int,
   services_id int,
   vehicle_id int,
   date timestamp default current_timestamp,
   foreign key(cust_id) references customer(id),
   foreign key(services_id) references services(id),
   foreign key(vehicle_id) references vehicle(id)
);

select * from invoice;