SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_car`
-- ----------------------------
DROP TABLE IF EXISTS `t_car`;
CREATE TABLE `t_car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `model` varchar(100) NOT NULL,
  `plate_number` varchar(255) NOT NULL,
  `price_per_day` decimal(19,2) DEFAULT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_plate_number` (`plate_number`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_car
-- ----------------------------
INSERT INTO `t_car` VALUES ('1', 'Toyota Camry', 'A10000', '10.00', '2023-01-05 15:52:54');
INSERT INTO `t_car` VALUES ('2', 'Toyota Camry', 'A10001', '60.00', '2023-01-05 15:53:03');
INSERT INTO `t_car` VALUES ('3', 'BMW 650', 'B10000', '80.00', '2023-01-05 15:53:27');
INSERT INTO `t_car` VALUES ('4', 'BMW 650', 'B10001', '150.00', '2023-01-05 15:53:36');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_from` date NOT NULL,
  `date_to` date NOT NULL,
  `car_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpx3tk5mgvgsv17hv2g4xsqan0` (`car_id`),
  KEY `FKpr2wacfs0vxh9v61dni9ugonn` (`user_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_order_ibfk_2` FOREIGN KEY (`car_id`) REFERENCES `t_car` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('27', '2021-05-19', '2021-05-20', '3', '5', '2023-01-05 15:56:13');
INSERT INTO `t_order` VALUES ('28', '2023-01-10', '2023-01-11', '3', '6', '2023-01-05 21:59:08');
INSERT INTO `t_order` VALUES ('29', '2023-01-11', '2023-01-11', '4', '6', '2023-01-05 22:03:36');
INSERT INTO `t_order` VALUES ('30', '2023-01-11', '2023-01-13', '1', '6', '2023-01-05 22:03:58');
INSERT INTO `t_order` VALUES ('31', '2023-01-10', '2023-01-11', '2', '6', '2023-01-05 22:04:17');
INSERT INTO `t_order` VALUES ('32', '2023-01-12', '2023-01-12', '4', '6', '2023-01-05 22:17:11');
INSERT INTO `t_order` VALUES ('33', '2023-01-12', '2023-01-12', '3', '6', '2023-01-05 22:21:59');
INSERT INTO `t_order` VALUES ('34', '2023-01-10', '2023-01-10', '4', '22', '2023-01-05 22:26:39');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('5', 'tiger', 'tiger@foxmail.com', '2023-01-05 15:53:58');
INSERT INTO `t_user` VALUES ('6', 'hu', 'hu@foxmail.com', '2023-01-05 15:55:21');
INSERT INTO `t_user` VALUES ('21', 'tt', 'tiger.tsai@foxmail.com', '2023-01-05 17:32:09');
INSERT INTO `t_user` VALUES ('22', 'tiger', 'a@ff', '2023-01-05 22:26:10');
