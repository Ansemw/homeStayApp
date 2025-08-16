INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' Jan 13 ','Uttarayani','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' March 15 ','Fooldei','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' March 30 ','Chaitra Navaratri','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' Nov 1 ','Igas bagwal','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
  VALUES (' Nov 9 ','Uttarakhand Divas','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' Jan 17 ','Kitchen Maintenance','MAINTENANCE',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' July 4 ','Trek Route Check','MAINTENANCE',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' Sep 5 ','Water Supply Maintenance','MAINTENANCE',CURDATE(),'DBA');

INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
  VALUES ('ADMIN',CURDATE(),'DBA');

INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
  VALUES ('GUEST',CURDATE(),'DBA');


INSERT INTO `person` VALUES
(1,'Anurag','anurag@g.com','9761594808','54321',2,NULL,NULL,'2025-01-04 11:48:24','anonymousUser',NULL,NULL),
(2,'Admin','admin@homestay.com','3443434343','$2a$12$1WeFpeOZ9sc5mQpLoN8aFeoU1r0MktA9Td4Hw8QXw1Lqt2TLbKACu',1,NULL,NULL,'2025-01-07 18:30:00','DBA',NULL,NULL);

INSERT INTO `room` (`room_no`,`floor`,`desc`,`created_at`, `created_by`)
 VALUES (1,'Ground','A river facing room that can house upto four people',CURDATE(),'DBA');

INSERT INTO `room` (`room_no`,`floor`,`desc`,`created_at`, `created_by`)
 VALUES (2,'Ground','A river facing room that can house upto two people',CURDATE(),'DBA');

INSERT INTO `room` (`room_no`,`floor`,`desc`,`created_at`, `created_by`)
 VALUES (3,'Ground','A valley facing room that can house upto two people',CURDATE(),'DBA');

INSERT INTO `room` (`room_no`,`floor`,`desc`,`created_at`, `created_by`)
 VALUES (4,'Ground','A valley facing room that can house upto four people',CURDATE(),'DBA');

 INSERT INTO `room` (`room_no`,`floor`,`desc`,`created_at`, `created_by`)
  VALUES (5,'First','A river facing room that can house upto four people',CURDATE(),'DBA');

 INSERT INTO `room` (`room_no`,`floor`,`desc`,`created_at`, `created_by`)
  VALUES (6,'First','A river facing room that can house upto two people',CURDATE(),'DBA');

 INSERT INTO `room` (`room_no`,`floor`,`desc`,`created_at`, `created_by`)
  VALUES (7,'First','A valley facing room that can house upto two people',CURDATE(),'DBA');

 INSERT INTO `room` (`room_no`,`floor`,`desc`,`created_at`, `created_by`)
  VALUES (8,'First','A valley facing room that can house upto four people',CURDATE(),'DBA');

 insert into `trek`(`trek_id`,`trek_name`,`trek_distance`,`trek_difficulty`,`trek_image`,`status`,`created_at`,`created_by`)
 values(1,'Fegu','5','Intermediate','assets/images/IMG-20250322-WA0026.jpg','INACTIVE',CURDATE(),'DBA');

 insert into `trek`(`trek_id`,`trek_name`,`trek_distance`,`trek_difficulty`,`trek_image`,`status`,`created_at`,`created_by`)
 values(2,'Baurari','4.5','Intermediate','assets/images/IMG-20250322-WA0025.jpg','INACTIVE',CURDATE(),'DBA');

 insert into `trek`(`trek_id`,`trek_name`,`trek_distance`,`trek_difficulty`,`trek_image`,`status`,`created_at`,`created_by`)
 values(3,'Surkanda Devi','2','Basic','assets/images/IMG-20250322-WA0067.jpg','INACTIVE',CURDATE(),'DBA');

 insert into `trek`(`trek_id`,`trek_name`,`trek_distance`,`trek_difficulty`,`trek_image`,`status`,`created_at`,`created_by`)
 values(4,'Sabdarkhal','8','Advanced','assets/images/IMG-20250322-WA0013.jpg','INACTIVE',CURDATE(),'DBA');

 insert into `trek`(`trek_id`,`trek_name`,`trek_distance`,`trek_difficulty`,`trek_image`,`status`,`created_at`,`created_by`)
 values(5,'Goljyu Devta','10','Advanced','assets/images/IMG-20250322-WA0032.jpg','INACTIVE',CURDATE(),'DBA');

 insert into `trek`(`trek_id`,`trek_name`,`trek_distance`,`trek_difficulty`,`trek_image`,`status`,`created_at`,`created_by`)
 values(6,'Narsingh Devta','3','Basic','assets/images/IMG-20250322-WA0053.jpg','INACTIVE',CURDATE(),'DBA');