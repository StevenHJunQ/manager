CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varbinary(50) NOT NULL COMMENT '密码',
  `nick_name` varbinary(30) DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `user`(`user_id`, `name`, `password`, `nick_name`) VALUES (1000000000, 'root', 0x3633613966306561376262393830353037393662363439653835343831383435, 0xE7AEA1E79086E59198);
