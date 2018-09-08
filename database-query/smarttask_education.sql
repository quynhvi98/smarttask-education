create database smarttask_education;
CREATE TABLE `user` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `email` varchar(255) NOT NULL,
 `password` varchar(60) NOT NULL,
 PRIMARY KEY (`id`)
);

CREATE TABLE `role` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(255) NOT NULL,
 PRIMARY KEY (`id`)
);

create table user_role (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `user_id` int(11) ,
 `user_role` int(11),
 FOREIGN KEY (`user_id`) REFERENCES user(`id`),
 FOREIGN KEY (`user_role`) REFERENCES role(`id`),
 PRIMARY KEY (`id`)
);