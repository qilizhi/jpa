/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50542
Source Host           : localhost:3306
Source Database       : mlx_jpa

Target Server Type    : MYSQL
Target Server Version : 50542
File Encoding         : 65001

Date: 2016-07-08 18:07:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_city
-- ----------------------------
DROP TABLE IF EXISTS `s_city`;
CREATE TABLE `s_city` (
  `CityID` int(11) NOT NULL,
  `CityName` varchar(255) DEFAULT NULL,
  `ProvinceID` int(11) DEFAULT NULL,
  `ZipCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CityID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of s_city
-- ----------------------------

-- ----------------------------
-- Table structure for s_district
-- ----------------------------
DROP TABLE IF EXISTS `s_district`;
CREATE TABLE `s_district` (
  `DistrictID` int(11) NOT NULL,
  `CityId` int(11) DEFAULT NULL,
  `DistrictName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DistrictID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of s_district
-- ----------------------------

-- ----------------------------
-- Table structure for s_province
-- ----------------------------
DROP TABLE IF EXISTS `s_province`;
CREATE TABLE `s_province` (
  `ProvinceID` int(11) NOT NULL,
  `ProvinceName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ProvinceID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of s_province
-- ----------------------------
