/*
 Navicat MySQL Data Transfer

 Source Server         : Health
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : health

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 31/07/2021 04:00:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authentication
-- ----------------------------
DROP TABLE IF EXISTS `authentication`;
CREATE TABLE `authentication` (
  `id` int NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `last_check_time` datetime DEFAULT NULL,
  `login_expire_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of authentication
-- ----------------------------
BEGIN;
INSERT INTO `authentication` VALUES (3, 'hWyvY9VgoC7xWVeO', '2021-07-28 15:53:51', '2021-07-08 22:51:04');
COMMIT;

-- ----------------------------
-- Table structure for date
-- ----------------------------
DROP TABLE IF EXISTS `date`;
CREATE TABLE `date` (
  `date_id` int NOT NULL AUTO_INCREMENT,
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `location_id` int DEFAULT NULL,
  `morning_spot` int NOT NULL DEFAULT '255',
  `afternoon_spot` int NOT NULL DEFAULT '255',
  `location_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`date_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of date
-- ----------------------------
BEGIN;
INSERT INTO `date` VALUES (1, '2021-07-31', 1, 253, 255, 'UVA University Hospital');
INSERT INTO `date` VALUES (2, '2021-07-31', 2, 255, 255, 'UVA CVS');
INSERT INTO `date` VALUES (3, '2021-07-31', 3, 255, 255, 'Kroger');
INSERT INTO `date` VALUES (4, '2021-07-31', 4, 255, 255, 'Charlottesville Health Department');
INSERT INTO `date` VALUES (5, '2021-07-31', 5, 255, 255, 'Costco Wholesale');
INSERT INTO `date` VALUES (6, '2021-08-01', 1, 255, 255, 'UVA University Hospital');
INSERT INTO `date` VALUES (7, '2021-08-01', 2, 255, 255, 'UVA CVS');
INSERT INTO `date` VALUES (8, '2021-08-01', 3, 255, 255, 'Kroger');
INSERT INTO `date` VALUES (9, '2021-08-01', 4, 255, 255, 'Charlottesville Health Department');
INSERT INTO `date` VALUES (10, '2021-08-01', 5, 255, 255, 'Costco Wholesale');
COMMIT;

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `location_id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of location
-- ----------------------------
BEGIN;
INSERT INTO `location` VALUES (1, 'UVA University Hospital', '1215 Lee St, Charlottesville, VA 22903');
INSERT INTO `location` VALUES (2, 'UVA CVS', '1417 25 University Ave, Charlottesville, VA 22903');
INSERT INTO `location` VALUES (3, 'Kroger', '1159 Emmet St N, Charlottesville, VA 22901');
INSERT INTO `location` VALUES (4, 'Charlottesville Health Department', '1138 Rose Hill Dr, Charlottesville, VA 22903');
INSERT INTO `location` VALUES (5, 'Costco Wholesale', '3171 District Ave, Charlottesville, VA 22901');
COMMIT;

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `reserve_id` int NOT NULL AUTO_INCREMENT,
  `user_ssn` varchar(255) DEFAULT NULL,
  `location_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `time` int DEFAULT NULL COMMENT '0-morning\n1-afternoon',
  PRIMARY KEY (`reserve_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of reservation
-- ----------------------------
BEGIN;
INSERT INTO `reservation` VALUES (4, '123-12-1234', 'UVA University Hospital', '2021-07-31', 0);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ssn` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `citizenship` varchar(255) DEFAULT NULL,
  `vaccinated_status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, '123-12-1234', 'zyf20010627', 'Yufei', 'Zhou', NULL, 0, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `users` VALUES (2, '123-12-1235', 'zyf20010627', 'Tuke', 'Tian', NULL, 0, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `users` VALUES (3, '123-12-1236', 'zyf20010627', 'Tongjing', 'Tao', NULL, 0, NULL, NULL, NULL, NULL, NULL, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
