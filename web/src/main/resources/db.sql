-- 2018-08-21 张恒: 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	id VARCHAR(32) PRIMARY KEY,
	`name` VARCHAR(20),
  	address VARCHAR(100),
  	age INT,
  	sex INT,
	birthday DATE
);