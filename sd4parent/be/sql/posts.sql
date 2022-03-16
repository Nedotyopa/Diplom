create database backend ;
use backend;
create table `post` (
  `id` bigint(20) not null auto_increment,
  `title` varchar(45) default null,
  `description` varchar(255) default null,
  `price` float default null,
  `discount` int default null,
  primary key (`id`),
  unique key `title_unique` (`title`)
) ENGINE=InnoDB ;

create table `user` (
  `id` bigint(20) not null auto_increment,
  `login` varchar(45) default null,
  `password` blob(45) not null,
  `role` varchar(45) not null,
  `email` varchar(45) not null,
  `language` varchar(45) default true, /* language=0 - english, language=1 - russian */
  `last_date_login` timestamp,
  primary key (`id`),
  unique key `login_unique` (`login`)
) ENGINE=InnoDB;

create table `wallet` (
  `id` bigint(20) not null auto_increment,
  `money` float default null,
  primary key (`id`),
  foreign key (`id`) references `user`(`id`)
) ENGINE=InnoDB;

create table `usersposts` (
  `id` bigint(20) not null auto_increment,
  `user_id` bigint(20) not null,
  `post_id` bigint(20) not null,
  `duration` int,
  `date` timestamp,
  primary key (`id`),
  foreign key (`user_id`) references `user`(`id`),
  foreign key (`post_id`) references `post`(`id`)
) ENGINE=InnoDB;

create table `package` (
  `id` bigint(20) not null auto_increment,
  primary key (`id`)
) ENGINE=InnoDB ;

create table `addition` (
  `id` bigint(20) not null auto_increment,
  `title` varchar(45) default null,
  `description` varchar(255) default null,
  `price` float default null,
  primary key (`id`)
) ENGINE=InnoDB ;

create table `additionsposts` (
  `post_id` bigint(20) not null,
  `addition_id` bigint(20)  not null,
  foreign key (`post_id`) references `post`(`id`),
  foreign key (`addition_id`) references `addition`(`id`)
) ENGINE=InnoDB;

create table `transactions` (
  `id` bigint(20) not null auto_increment,
  `wallet_id` bigint(20) not null,
  `action` varchar(45) default null,
  `amount` float default null,
  primary key (`id`),
  foreign key (`wallet_id`) references `wallet`(`id`)
) ENGINE=InnoDB ;

create table `packagesposts` (
  `package_id` bigint(20) not null,
  `post_id` bigint(20) not null,
  foreign key (`package_id`) references `package`(`id`),
  foreign key (`post_id`) references `post`(`id`)
) ENGINE=InnoDB;

create table `comment` (
  `id` bigint(20) not null auto_increment,
  `user_id` bigint(20) not null,
  `post_id` bigint(20) not null,
  `text` text not null,
  `date` timestamp,
  primary key (`id`),
  foreign key (`user_id`) references `user`(`id`),
  foreign key(`post_id`) references `post`(id)
) ENGINE=InnoDB;