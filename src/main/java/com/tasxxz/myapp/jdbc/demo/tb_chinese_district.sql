/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50147
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50147
File Encoding         : 65001

Date: 2016-09-17 11:24:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_chinese_district
-- ----------------------------
DROP TABLE IF EXISTS `tb_chinese_district`;
CREATE TABLE `tb_chinese_district` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '行政区代码',
  `name` varchar(128) DEFAULT '' COMMENT '行政区名称',
  `parentCode` char(32) DEFAULT NULL COMMENT '上一级行政区代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='行政区域代码';
