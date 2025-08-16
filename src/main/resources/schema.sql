CREATE DATABASE IF NOT EXISTS HOMESTAY;

USE HOMESTAY;

CREATE TABLE IF NOT EXISTS `contact_msg` (
  `contact_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `mobile_num` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `message` varchar(500) NOT NULL,
  `status` varchar(10) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `holidays` (
  `day` varchar(20) NOT NULL,
  `reason` varchar(100) NOT NULL,
  `type` varchar(20) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`role_id`)
);

CREATE TABLE IF NOT EXISTS `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `address1` varchar(200) NOT NULL,
  `address2` varchar(200) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `zip_code` int NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`address_id`)
);

CREATE TABLE IF NOT EXISTS `person` (
  `person_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `role_id` int NOT NULL,
  `address_id` int NULL,
  `trek_id` int NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`person_id`),
   FOREIGN KEY (role_id) REFERENCES roles(role_id),
   FOREIGN KEY (trek_id) REFERENCES trek(trek_id),
   FOREIGN KEY (address_id) REFERENCES address(address_id)
);

CREATE TABLE IF NOT EXISTS `room` (

  `room_no` int(100) NOT NULL,
  `floor` varchar(50) NOT NULL,
  `desc` varchar(500) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`room_no`)
);

CREATE TABLE IF NOT EXISTS `booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `person_id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `guest_email` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `start_date` Date NOT NULL,
  `end_date` Date NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`booking_id`),
   FOREIGN KEY (person_id) REFERENCES person(person_id)

);

CREATE TABLE IF NOT EXISTS `booking_room` (
  `booking_id` int NOT NULL,
  `room_no` int NOT NULL,
  FOREIGN KEY (booking_id) REFERENCES booking(booking_id),
  FOREIGN KEY (room_no) REFERENCES room(room_no),
   PRIMARY KEY (`booking_id`,`room_no`)
);
CREATE TABLE IF NOT EXISTS `trek` (
  `trek_id` int NOT NULL AUTO_INCREMENT,
  `trek_name` varchar(100) NOT NULL,
  `trek_distance` varchar(20) NOT NULL,
  `trek_difficulty` varchar(20) NOT NULL,
  `trek_image` varchar(100) NOT NULL,
  `status` varchar(20) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`trek_id`)

);

CREATE TABLE IF NOT EXISTS trek_details (
    Id INT NOT NULL AUTO_INCREMENT,
    trek_Id INT,
    booking_Id INT,
    participants  INT,
    PRIMARY KEY (Id)
);