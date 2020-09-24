/*
Navicat MySQL Data Transfer

Source Server         : mapland
Source Server Version : 50726
Source Host           : 47.98.204.96:3306
Source Database       : web

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-09-14 22:19:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for log_entry
-- ----------------------------
DROP TABLE IF EXISTS `log_entry`;
CREATE TABLE `log_entry` (
  `entry` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_entry
-- ----------------------------
INSERT INTO `log_entry` VALUES ('AIDA');
INSERT INTO `log_entry` VALUES ('SYSTEM');
INSERT INTO `log_entry` VALUES ('DATABASE');
INSERT INTO `log_entry` VALUES ('WEB');
