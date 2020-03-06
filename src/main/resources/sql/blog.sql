/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50622
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50622
 File Encoding         : 65001

 Date: 06/03/2020 11:05:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `appreciation` int(1) NOT NULL DEFAULT 0 COMMENT '是否打开打赏',
  `commentabled` int(1) NOT NULL DEFAULT 0,
  `content` text CHARACTER SET utf8 COLLATE utf8_bin,
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `first_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '首图',
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `published` int(1) NOT NULL DEFAULT 0 COMMENT '是否发表',
  `recommend` int(1) NOT NULL DEFAULT 0 COMMENT '是否推荐',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `views` int(11) DEFAULT NULL,
  `type_id` bigint(18) DEFAULT NULL COMMENT '类型id',
  `user_id` bigint(18) DEFAULT NULL COMMENT '用户id',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '博客描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK292449gwg5yf7ocdlmswv9w4j`(`type_id`) USING BTREE,
  INDEX `FK8ky5rrsxh01nkhctmo7d48p82`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_tags
-- ----------------------------
DROP TABLE IF EXISTS `blog_tags`;
CREATE TABLE `blog_tags`  (
  `blogs_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  INDEX `FK5feau0gb4lq47fdb03uboswm8`(`tags_id`) USING BTREE,
  INDEX `FKh4pacwjwofrugxa9hpwaxg6mr`(`blogs_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKke3uogd04j4jx316m1p51e05u`(`blog_id`) USING BTREE,
  INDEX `FK4jj284r3pb7japogvo6h72q95`(`parent_comment_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
