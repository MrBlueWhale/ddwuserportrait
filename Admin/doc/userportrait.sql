/*
 Navicat Premium Data Transfer

 Source Server         : spark
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : userportrait

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 11/06/2021 13:13:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `aid` bigint(20) NOT NULL COMMENT '权限表主键',
  `authority_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `active` int(11) NOT NULL COMMENT '是否可用（0可用，1禁用）',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1, '添加用户', NULL, 0);
INSERT INTO `authority` VALUES (2, '注销用户', NULL, 0);
INSERT INTO `authority` VALUES (3, '查询用户', NULL, 0);
INSERT INTO `authority` VALUES (4, '修改用户', NULL, 0);
INSERT INTO `authority` VALUES (11, '新增角色', NULL, 0);
INSERT INTO `authority` VALUES (12, '删除角色', NULL, 0);
INSERT INTO `authority` VALUES (13, '查询角色', NULL, 0);
INSERT INTO `authority` VALUES (14, '修改角色', NULL, 0);
INSERT INTO `authority` VALUES (21, '添加标签', NULL, 0);
INSERT INTO `authority` VALUES (22, '查询标签', NULL, 0);
INSERT INTO `authority` VALUES (31, '审核标签', NULL, 0);
INSERT INTO `authority` VALUES (32, '上线标签', NULL, 0);
INSERT INTO `authority` VALUES (33, '下线标签', NULL, 0);
INSERT INTO `authority` VALUES (34, '禁用标签', NULL, 0);

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo
-- ----------------------------
INSERT INTO `demo` VALUES (1, 'test');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` bigint(20) NOT NULL COMMENT '角色表主键',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `active` int(11) NOT NULL COMMENT '是否可用（0可用，1禁用）',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', 'root', 0, '2021-06-11 13:08:34');
INSERT INTO `role` VALUES (2, '角色管理员', '`[增删查改]`', 0, '2021-06-11 13:08:37');
INSERT INTO `role` VALUES (3, '用户管理员', '`[增删查改]`', 0, '2021-06-11 13:08:39');
INSERT INTO `role` VALUES (4, '标签开发员', '（领取开发任务，提交开发结果）', 0, '2021-06-11 13:08:42');
INSERT INTO `role` VALUES (5, '标签维护员', '（审核，上下线`[增，逻辑删除]`，禁用`[逻辑删除]`,修改）', 0, '2021-06-11 13:08:45');
INSERT INTO `role` VALUES (6, '普通用户', '（使用标签`[查]`，提交申请）', 0, '2021-06-11 13:08:49');

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority`  (
  `rid` bigint(20) NOT NULL COMMENT '角色主键',
  `aid` bigint(20) NOT NULL COMMENT '权限主键',
  PRIMARY KEY (`rid`, `aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_authority
-- ----------------------------
INSERT INTO `role_authority` VALUES (1, 1);
INSERT INTO `role_authority` VALUES (1, 2);
INSERT INTO `role_authority` VALUES (1, 3);
INSERT INTO `role_authority` VALUES (1, 4);
INSERT INTO `role_authority` VALUES (1, 11);
INSERT INTO `role_authority` VALUES (1, 12);
INSERT INTO `role_authority` VALUES (1, 13);
INSERT INTO `role_authority` VALUES (1, 14);
INSERT INTO `role_authority` VALUES (1, 21);
INSERT INTO `role_authority` VALUES (1, 22);
INSERT INTO `role_authority` VALUES (1, 31);
INSERT INTO `role_authority` VALUES (1, 32);
INSERT INTO `role_authority` VALUES (1, 33);
INSERT INTO `role_authority` VALUES (1, 34);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` bigint(20) NOT NULL COMMENT '用户表主键',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `tel_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uid`) USING BTREE,
 unique key `user_name_unique` (`user_name`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '兰鑫', '2971529737@qq.com', '19981481120', 'admin', '2021-06-11 13:08:04');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `rid` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`uid`, `rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO `userportrait`.`user` (`uid`, `user_name`, `name`, `email`, `tel_num`, `password`, `create_time`) VALUES (1, 'admin', '兰鑫', '2971529737@qq.com', '19981481120', 'admin', '2021-06-11 13:08:04');
INSERT INTO `userportrait`.`user` (`uid`, `user_name`, `name`, `email`, `tel_num`, `password`, `create_time`) VALUES (59091910668718080, 'admin3', '张志成', '2512466133@qq.com', '19912341234', '036c8c6aada989607e81318778b0b007', '2021-06-13 09:30:09');
INSERT INTO `userportrait`.`user` (`uid`, `user_name`, `name`, `email`, `tel_num`, `password`, `create_time`) VALUES (59119916342710272, 'admin4', '张志成', '2512466133@qq.com', '19912341234', '200820e3227815ed1756a6b531e7e0d2', '2021-06-13 11:21:26');
INSERT INTO `userportrait`.`user` (`uid`, `user_name`, `name`, `email`, `tel_num`, `password`, `create_time`) VALUES (59158606452494336, 'admin5', '曾派', '1023479515@qq.com', '19943214321', '4617837e97e175dfae31d78d9a106ce5', '2021-06-13 13:55:11');
INSERT INTO `userportrait`.`user` (`uid`, `user_name`, `name`, `email`, `tel_num`, `password`, `create_time`) VALUES (59161125849272320, 'test2', '李鑫洋', '2512466133@qq.com', '19912341234', '4b93c6353a04b365433ad4a7fa2d9efc', '2021-06-13 14:05:12');
INSERT INTO `userportrait`.`user` (`uid`, `user_name`, `name`, `email`, `tel_num`, `password`, `create_time`) VALUES (59165037641732096, 'admin6', '王新龙', '2912312213@qq.com', '19911112222', '46f94c8de14fb36680850768ff1b7f2a', '2021-06-13 14:20:44');
