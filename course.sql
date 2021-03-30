/*
 Navicat Premium Data Transfer

 Source Server         : benz
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : course

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 31/10/2020 13:00:33
*/

-- ----------------------------
-- Table structure for dept
-- ----------------------------
CREATE TABLE `dept` (
  `id` int(11) NOT NULL COMMENT '编号',
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '部门姓名',
  `type` int(1) NOT NULL COMMENT '0-门诊, 1-住院',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '科室信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
CREATE TABLE `doctor` (
  `id` int(11) NOT NULL COMMENT '编号',
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `dept_id` int(11) NOT NULL COMMENT '科室编号',
  `sex` int(1) NOT NULL COMMENT '0-未知, 1-男, 2-女',
  `password` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '登陆密码',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_doctor_dept` (`dept_id`),
  CONSTRAINT `fk_doctor_dept` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for patient
-- ----------------------------
CREATE TABLE `patient` (
  `id` int(11) NOT NULL COMMENT '编号',
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `sex` int(1) NOT NULL COMMENT '0-未知, 1-男, 2-女',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `password` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '登陆密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for reg_record
-- ----------------------------
CREATE TABLE `reg_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水号（自动递增）',
  `patient_id` int(11) NOT NULL COMMENT '病人编号',
  `dept_id` int(11) NOT NULL COMMENT '科室编号',
  `reg_time` datetime NOT NULL COMMENT '挂号时间',
  `price` decimal(10,2) NOT NULL COMMENT '挂号费用',
  PRIMARY KEY (`id`),
  KEY `fk_reg_user` (`patient_id`),
  KEY `fk_reg_dept` (`dept_id`),
  CONSTRAINT `fk_reg_dept` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`),
  CONSTRAINT `fk_reg_user` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

