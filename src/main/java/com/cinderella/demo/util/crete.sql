USE `java_code`;
CREATE TABLE IF NOT EXISTS `users`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `username` VARCHAR(255) NOT NULL,
   `password` VARCHAR(255) NOT NULL,
   `age` INT(6) NOT NULL,
   `phone` VARCHAR(255) NOT NULL,
   `content` VARCHAR(255) NOT NULL,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `users` VALUES (1, 'admin', 'password',20,'18700000000','这是测试数据1');
INSERT INTO `users` VALUES (2, 'Li', 'passw0rd',22,'16600000000','这是测试数据2');
INSERT INTO `users` VALUES (3, 'Cinderella','c1ndere11a',18,'16500000000','这是测试数据3');
INSERT INTO `users` VALUES (4, 'admin01','pass',19,'16900000000','这是测试数据4');
