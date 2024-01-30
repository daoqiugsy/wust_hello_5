/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : haluo

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 29/01/2024 20:47:37
*/


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


CREATE DATABASE IF NOT EXISTS `haluo`;
use `haluo`;
-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`  (
  `id` bigint NOT NULL COMMENT '打卡记录ID',
  `stu_id` bigint NOT NULL COMMENT '学生ID',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `date` date NOT NULL COMMENT '日期',
  `valid` double NOT NULL COMMENT '有效时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除（0未删除，1已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES (1, 20211001, '2024-01-16 17:56:39', '2024-01-16 17:56:44', '2024-01-16', 1, '2024-01-16 17:56:58', '2024-01-16 17:57:00', 0);
INSERT INTO `card` VALUES (2, 20211003, '2024-01-16 14:57:10', '2024-01-16 17:57:19', '2024-01-16', 2, '2024-01-16 17:57:26', '2024-01-16 17:57:31', 0);

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` bigint NOT NULL COMMENT '学院ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学院名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (100, '计算机科学与技术学院', '2024-01-16 17:29:28', '2024-01-16 17:29:31', 0);

-- ----------------------------
-- Table structure for leisure
-- ----------------------------
DROP TABLE IF EXISTS `leisure`;
CREATE TABLE `leisure`  (
  `id` bigint NOT NULL COMMENT '空闲时间记录ID',
  `time_slot` tinyint UNSIGNED NOT NULL COMMENT '时间段编号',
  `is_leisure` tinyint(1) NOT NULL COMMENT '是否空闲',
  `date` date NOT NULL COMMENT '日期',
  `stu_id` bigint NOT NULL COMMENT '学生ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leisure
-- ----------------------------
INSERT INTO `leisure` VALUES (1, 1, 1, '2024-01-16', 20211001, '2024-01-16 17:56:21', '2024-01-16 17:56:22', 0);

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` bigint NOT NULL COMMENT '专业ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '专业名称',
  `college_id` bigint NOT NULL COMMENT '所属学院ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1001, '计算机科学与技术', 100, '2024-01-16 17:30:02', '2024-01-16 17:30:05', 0);
INSERT INTO `major` VALUES (1002, '软件工程', 100, '2024-01-16 17:30:25', '2024-01-16 17:30:28', 0);
INSERT INTO `major` VALUES (1003, '信息安全', 100, '2024-01-16 17:31:10', '2024-01-16 17:31:13', 0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint NOT NULL COMMENT '学生ID，引用用户id，外键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `college_id` bigint NULL DEFAULT NULL COMMENT '学院ID',
  `major_id` bigint NULL DEFAULT NULL COMMENT '专业ID',
  `grade` int NULL DEFAULT NULL COMMENT '年级',
  `edu` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学历',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (20211001, '张三', 100, 1001, 2021, '本科', '2024-01-16 17:42:50', '2024-01-16 17:42:55', 0);
INSERT INTO `student` VALUES (20211003, '李四', 100, 1002, 2022, '本科', '2024-01-16 17:43:49', '2024-01-16 17:43:51', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL COMMENT '用户ID',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号，唯一',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `role` tinyint UNSIGNED NOT NULL COMMENT '角色码',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (20211001, '18199998888', '$2a$10$JMrgxN2JNPdcQ/leiMSSo.bZPofo5zCerYPsb5CA9yAlTyMKXRJea', 1, '2024-01-16 17:36:47', '2024-01-16 17:36:52', 0);
INSERT INTO `user` VALUES (20211003, '18088889999', '$2a$10$jxADy0LS8v2nOnDKPwooxehGe0SrTkqk1/V3BXBy02HDmbkQKtViS', 1, '2024-01-16 17:37:26', '2024-01-16 17:37:29', 0);
INSERT INTO `user` VALUES (20211006, '18188880000', '$2a$10$CesTUeLJJvBModFjbl.I3un8Onwlg5BwZr3qOpo2HxZCnTO3ZmAyW', 1, '2024-01-16 17:38:18', '2024-01-16 17:38:21', 0);
INSERT INTO `user` VALUES (1751545523061248001, '18199998888', '$2a$10$.G8aJD6aQx3GatOL/vfn6uGuGg4DIgmxR6KhIeso7Cu7Q2Qkdt846', 1, '2024-01-28 17:59:29', '2024-01-28 17:59:29', 0);
INSERT INTO `user` VALUES (1751545733015523330, '18199998888', '$2a$10$gM/kAYdJkbbbYMPD7OaG0uSrJIr5B.SISdOUlR9IDCTo1IJ5yGAHy', 1, '2024-01-28 18:00:20', '2024-01-28 18:00:20', 0);
INSERT INTO `user` VALUES (1751546353441165313, '18199998888', '$2a$10$/SJsSJlnG5juayn8aLDp9eutge4L89Hb4c.I8a95hbPCqBkGLY7Eu', 1, '2024-01-28 18:02:47', '2024-01-28 18:02:47', 0);
INSERT INTO `user` VALUES (1751546427621625858, '18199998888', '$2a$10$Xcmu7Ej8ikHHT/PA2E3tFOfbCx0PGtHVtGkwXEeBuPii70TfQlJ7K', 1, '2024-01-28 18:03:05', '2024-01-28 18:03:05', 0);
INSERT INTO `user` VALUES (1751564630888321025, '18199998888', '$2a$10$YJy36nrURmwQ/Q.YaAZ.DOeDmHk9KAUj2tiQczA9gEzIspxvLFeuy', 1, '2024-01-28 19:15:25', '2024-01-28 19:15:25', 0);
INSERT INTO `user` VALUES (1751577921499770882, '18199998888', '$2a$10$yjEzBTI.ogjvLJjMeQuM6.DhNUgALwmIskanRv0gw2c/Q3UTrk6pi', 1, '2024-01-28 20:08:14', '2024-01-28 20:08:14', 0);

-- ----------------------------
-- Table structure for week
-- ----------------------------
DROP TABLE IF EXISTS `week`;
CREATE TABLE `week`  (
  `id` bigint NOT NULL COMMENT '周报记录ID',
  `stu_id` bigint NOT NULL COMMENT '学生ID',
  `start_time` date NOT NULL COMMENT '开始日期',
  `end_time` date NOT NULL COMMENT '结束日期',
  `current_plan` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '本周计划',
  `next_plan` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '下周计划',
  `is_push` tinyint(1) NOT NULL COMMENT '是否延迟',
  `sentiment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '感悟',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of week
-- ----------------------------
INSERT INTO `week` VALUES (1, 20211003, '2024-01-01', '2024-01-07', '针对算法做一个编程落地', '单测还不太熟悉', 1, 'have', '2024-01-16 17:46:35', '2024-01-16 17:46:38', 0);
INSERT INTO `week` VALUES (2, 20211003, '2024-01-08', '2024-01-14', '今天投入时间较少，主要是上课内容多多多多多多', '不会用IDEA', 0, '今天学习了单测的方法，掌握了如何单测', '2024-01-16 17:54:06', '2024-01-16 17:54:08', 0);
INSERT INTO `week` VALUES (3, 20211001, '2024-01-01', '2024-01-07', '设计核心模块算法', '没风险的', 1, '111111111111', '2024-01-16 17:55:14', '2024-01-16 17:55:17', 0);
INSERT INTO `week` (`id`, `stu_id`, `start_time`, `end_time`, `current_plan`, `next_plan`, `is_push`, `sentiment`, `create_time`, `update_time`, `deleted`)
VALUES
(4, 20211001, '2024-01-15', '2024-01-21', '继续学习算法课程', '深入学习Java多线程', 0, '感觉挺充实的', '2024-01-30 12:00:00', '2024-01-30 12:00:00', 0),
(5, 20211001, '2024-01-22', '2024-01-28', '开始准备下学期的项目', '学习设计模式', 0, '有点忙碌', '2024-01-30 12:00:00', '2024-01-30 12:00:00', 0),
(6, 20211003, '2024-01-15', '2024-01-21', '学习算法和数据结构', '掌握数据结构的基本概念', 0, '感觉有点吃力', '2024-01-30 12:00:00', '2024-01-30 12:00:00', 0),
(7, 20211003, '2024-01-22', '2024-01-28', '继续学习数据库知识', '深入理解SQL优化', 0, '感觉进步很大', '2024-01-30 12:00:00', '2024-01-30 12:00:00', 0),
(8, 20211005, '2024-01-15', '2024-01-21', '开始学习新的编程语言', '深入研究函数式编程', 0, '有点困难', '2024-01-30 12:00:00', '2024-01-30 12:00:00', 0),
(9, 20211005, '2024-01-22', '2024-01-28', '学习前端开发', '尝试构建一个简单的网站', 0, '感觉很兴奋', '2024-01-30 12:00:00', '2024-01-30 12:00:00', 0),
(10, 20211007, '2024-01-15', '2024-01-21', '学习软件工程原理', '尝试写一份软件需求规格说明书', 0, '感觉有点困惑', '2024-01-30 12:00:00', '2024-01-30 12:00:00', 0),
(11, 20211007, '2024-01-22', '2024-01-28', '开始学习网络安全知识', '尝试进行一次简单的渗透测试', 0, '感觉很有趣', '2024-01-30 12:00:00', '2024-01-30 12:00:00', 0),
(12, 20211009, '2024-01-15', '2024-01-21', '学习人工智能基础知识', '尝试编写一个简单的机器学习模型', 0, '感觉很新奇', '2024-01-30 12:00:00', '2024-01-30 12:00:00', 0),
(13, 20211009, '2024-01-22', '2024-01-28', '继续学习操作系统原理', '尝试写一个简单的操作系统内核', 0, '感觉有点吃力', '2024-01-30 12:00:00', '2024-01-30 12:00:00', 0);

SET FOREIGN_KEY_CHECKS = 1;
