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

 Date: 15/06/2021 10:40:55
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
-- Table structure for rank_3_tags
-- ----------------------------
DROP TABLE IF EXISTS `rank_3_tags`;
CREATE TABLE `rank_3_tags`  (
  `t3_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '三级标签主键',
  `t3_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '三级标签名字',
  `process_status` int(255) NOT NULL COMMENT '进度状态（申请中、开发中、开发完成、已上线、已下线、已禁用）',
  `audit_status` int(255) NOT NULL COMMENT '审核状态（未通过、已通过、未处理）',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签描述',
  PRIMARY KEY (`t3_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rank_3_tags
-- ----------------------------
INSERT INTO `rank_3_tags` VALUES (1, '人口属性', 3, 1, '用户特征');
INSERT INTO `rank_3_tags` VALUES (2, '商业属性', 3, 1, '消费特征');
INSERT INTO `rank_3_tags` VALUES (3, '行为属性', 3, 1, '兴趣特征');
INSERT INTO `rank_3_tags` VALUES (4, '用户价值', 3, 1, '用户价值');

-- ----------------------------
-- Table structure for rank_4_tags
-- ----------------------------
DROP TABLE IF EXISTS `rank_4_tags`;
CREATE TABLE `rank_4_tags`  (
  `t4_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '四级标签主键',
  `t4_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '四级标签名字',
  `t3_id` bigint(20) NOT NULL COMMENT '三级标签id',
  `process_status` int(255) NOT NULL COMMENT '进度状态（申请中、开发中、开发完成、已上线、已下线、已禁用）',
  `audit_status` int(255) NOT NULL COMMENT '审核状态（未通过、已通过、未处理）',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签描述',
  PRIMARY KEY (`t4_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rank_4_tags
-- ----------------------------
INSERT INTO `rank_4_tags` VALUES (1, '性别', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (2, '年龄段', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (3, '身高', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (4, '民族', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (5, '籍贯', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (6, '政治面貌', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (7, '婚姻状况', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (8, '学历', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (9, '就业状况', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (10, '星座', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (11, '所在商圈', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (12, '国籍', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (13, '消费周期', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (23, '消费能力', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (24, '客单价', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (25, '支付方式', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (26, '单笔最高', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (27, '购买频率', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (28, '退货率', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (29, '换货率', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (30, '有券必买', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (31, '客服咨询频率', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (32, '最近登录', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (33, '浏览页面', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (34, '浏览时长', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (35, '访问频率', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (36, '设备类型', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (37, '浏览时段', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (38, '登录频率', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (39, '浏览商品', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (40, '购买商品', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (41, '商品偏好', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (42, '品类偏好	', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (43, '品牌偏好', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (44, '房产', 4, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (45, '房产价值', 4, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (46, '车产', 4, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (47, '车产价值', 4, 3, 1, NULL);

-- ----------------------------
-- Table structure for rank_5_tags
-- ----------------------------
DROP TABLE IF EXISTS `rank_5_tags`;
CREATE TABLE `rank_5_tags`  (
  `t5_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '五级标签主键',
  `t5_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '五级标签名字',
  `t4_id` bigint(20) NOT NULL COMMENT '四级标签id',
  `process_status` int(255) NOT NULL COMMENT '进度状态（申请中、开发中、开发完成、已上线、已下线、已禁用）',
  `audit_status` int(255) NOT NULL COMMENT '审核状态（未通过、已通过、未处理）',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签描述',
  PRIMARY KEY (`t5_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rank_5_tags
-- ----------------------------

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
INSERT INTO `role` VALUES (1, '超级管理员', 'root', 0, '2021-06-13 20:52:28');
INSERT INTO `role` VALUES (2, '角色管理员', '角色`[增删查改]`', 0, '2021-06-14 10:58:47');
INSERT INTO `role` VALUES (3, '用户管理员', '用户`[增删查改]`', 0, '2021-06-14 10:58:59');
INSERT INTO `role` VALUES (4, '标签开发员', '（领取开发任务，提交开发结果）', 0, '2021-06-11 13:08:42');
INSERT INTO `role` VALUES (5, '标签维护员', '（审核，上下线`[增，逻辑删除]`，禁用`[逻辑删除]`,修改）', 0, '2021-06-11 13:08:45');
INSERT INTO `role` VALUES (6, '普通用户', '（使用标签`[查]`，提交申请）', 0, '2021-06-11 13:08:49');
INSERT INTO `role` VALUES (59191841274335232, 'testRole1', 'test1', 1, '2021-06-14 10:19:03');
INSERT INTO `role` VALUES (59466961922428928, 'testRole2', 'testRole2', 0, '2021-06-14 10:20:29');

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
  UNIQUE INDEX `user_name_unique`(`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '兰鑫', '2971529737@qq.com', '19981481120', 'admin', '2021-06-11 13:08:04');
INSERT INTO `user` VALUES (59091910668718080, 'admin3', '姚远', '2512466133@qq.com', '19912341234', '036c8c6aada989607e81318778b0b007', '2021-06-13 09:30:09');
INSERT INTO `user` VALUES (59119916342710272, 'admin4', '张志成', '2512466133@qq.com', '19912341234', '200820e3227815ed1756a6b531e7e0d2', '2021-06-13 11:21:26');
INSERT INTO `user` VALUES (59158606452494336, 'admin5', '曾派', '1023479515@qq.com', '19943214321', '4617837e97e175dfae31d78d9a106ce5', '2021-06-13 13:55:11');
INSERT INTO `user` VALUES (59161125849272320, 'test2', '李鑫洋', '2512466133@qq.com', '19912341234', '6005545589ffb607bc7f9f90ce44e31d', '2021-06-13 14:05:12');
INSERT INTO `user` VALUES (59165037641732096, 'admin6', '王新龙', '2912312213@qq.com', '19911112222', '46f94c8de14fb36680850768ff1b7f2a', '2021-06-13 14:20:44');

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
INSERT INTO `user_role` VALUES (59091910668718080, 2);
INSERT INTO `user_role` VALUES (59119916342710272, 3);

SET FOREIGN_KEY_CHECKS = 1;
