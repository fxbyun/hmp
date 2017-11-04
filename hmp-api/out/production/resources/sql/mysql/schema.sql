/*
Navicat MySQL Data Transfer

Source Server         : mysql-local
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : outpatient

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-10-08 17:22:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administrativeoffice
-- ----------------------------
DROP TABLE IF EXISTS `administrativeoffice`;
CREATE TABLE `administrativeoffice` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '科室名称',
  `category` varchar(50) DEFAULT NULL COMMENT '科室类别',
  `intro` varchar(255) DEFAULT NULL COMMENT '科室简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of administrativeoffice
-- ----------------------------

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(30) DEFAULT NULL,
  `module` varchar(30) DEFAULT NULL,
  `methods` varchar(30) DEFAULT NULL,
  `action_time` varchar(30) DEFAULT NULL,
  `user_ip` varchar(30) DEFAULT NULL,
  `oper_time` timestamp NULL DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `id` bigint(11) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `uid` varchar(30) DEFAULT NULL COMMENT '患者ID卡号，身份识别码，医生可用于直接调去用户数据',
  `sf_id` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `wx_id` varchar(30) DEFAULT NULL COMMENT '微信号',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `addrees` varchar(50) DEFAULT NULL COMMENT '地址',
  `brithday` date DEFAULT NULL COMMENT '出生年月',
  `tags` varchar(100) DEFAULT NULL COMMENT '健康标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patient
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '管理员', 'admin', '管理员');
INSERT INTO `roles` VALUES ('2', '医生', 'doctor', '医生');
INSERT INTO `roles` VALUES ('3', '药剂师', 'pharmacist', '药剂师');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `type` varchar(1) NOT NULL COMMENT '标签类型：A-主诉标签;B-诊断结果标签;C-安全用药标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------

-- ----------------------------
-- Table structure for tagmapping
-- ----------------------------
DROP TABLE IF EXISTS `tagmapping`;
CREATE TABLE `tagmapping` (
  `doctor_id` bigint(11) NOT NULL,
  `administrative_office_id` bigint(11) NOT NULL,
  `tag_id` bigint(11) NOT NULL,
  `hit` int(11) DEFAULT NULL COMMENT '使用次数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tagmapping
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `account` varchar(20) NOT NULL COMMENT '帐号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `credentials_salt` varchar(100) DEFAULT NULL,
  `create_time` date DEFAULT NULL COMMENT '注册时间',
  `locked` varchar(20) DEFAULT NULL COMMENT '锁',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `outpatient_service` varchar(50) DEFAULT NULL COMMENT '门诊名称',
  `business_license` varchar(50) DEFAULT NULL COMMENT '营业执照号码',
  `business_phone` varchar(20) DEFAULT NULL COMMENT '门疹地址',
  `business_add` varchar(100) DEFAULT NULL COMMENT '门诊地址',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `graduate_date` date DEFAULT NULL COMMENT '毕业时间',
  `integration` int(11) DEFAULT NULL COMMENT '积分',
  `intro` varchar(255) DEFAULT NULL COMMENT '简介',
  `localism` varchar(50) DEFAULT NULL COMMENT '方言',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `specialty` varchar(255) DEFAULT NULL COMMENT '专长',
  `star_level` int(11) DEFAULT NULL COMMENT '星级',
  `wealth` varchar(50) DEFAULT NULL COMMENT '财富',
  `wx_id` varchar(50) DEFAULT NULL COMMENT '微信号',
  PRIMARY KEY (`id`),
  KEY `FK_role` (`role_id`),
  CONSTRAINT `FK_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '管理员', 'admin', 'dda711a8108e72d3c682bd5e0f835c46', '96129374108fe2c893876d219d8609e5', '2015-10-08', 'N', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `login_time` timestamp NULL DEFAULT NULL,
  `login_ip` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user` (`user_id`),
  CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_login
-- ----------------------------
