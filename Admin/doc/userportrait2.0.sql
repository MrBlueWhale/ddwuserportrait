/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : userportrait

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 15/06/2021 11:47:38
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rank_4_tags
-- ----------------------------
INSERT INTO `rank_4_tags` VALUES (1, '性别', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (2, '年龄段', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (3, '身高', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (4, '民族', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (5, '籍贯', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (6, '政治面貌', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (7, '职业', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (8, '婚姻状况', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (9, '学历', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (10, '就业状况', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (11, '星座', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (12, '所在商圈', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (13, '国籍', 1, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (23, '消费周期', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (24, '消费能力', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (25, '客单价', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (26, '支付方式', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (27, '单笔最高', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (28, '购买频率', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (29, '退货率', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (30, '换货率', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (31, '有券必买', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (32, '客服咨询频率', 2, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (33, '最近登录', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (34, '浏览页面', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (35, '浏览时长', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (36, '访问频率', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (37, '设备类型', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (38, '浏览时段', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (39, '登录频率', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (40, '浏览商品', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (41, '购买商品', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (42, '商品偏好', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (43, '品类偏好	', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (44, '品牌偏好', 3, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (45, '房产', 4, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (46, '房产价值', 4, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (47, '车产', 4, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (48, '车产价值', 4, 3, 1, NULL);
INSERT INTO `rank_4_tags` VALUES (49, '省钱能手', 2, 3, 1, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 181 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rank_5_tags
-- ----------------------------
INSERT INTO `rank_5_tags` VALUES (1, '男', 1, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (2, '女', 1, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (3, '50后', 2, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (4, '60后', 2, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (5, '70后', 2, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (6, '80后', 2, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (7, '90后', 2, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (8, '00后', 2, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (9, '10后', 2, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (10, '20后', 2, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (11, '150-159', 3, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (12, '160-169', 3, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (13, '170-179', 3, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (14, '180-189', 3, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (15, '190-199', 3, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (16, '200-209', 3, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (17, '210-240', 3, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (19, '汉族', 4, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (20, '蒙古族', 4, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (21, '回族', 4, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (22, '藏族', 4, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (23, '维吾尔族', 4, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (24, '苗族', 4, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (25, '满族', 4, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (26, '北京', 5, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (27, '上海', 5, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (28, '广州', 5, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (29, '深圳', 5, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (30, '杭州', 5, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (31, '苏州', 5, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (32, '群众', 6, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (33, '党员', 6, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (34, '无党派人士', 6, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (35, '学生', 7, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (36, '公务员', 7, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (37, '军人', 7, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (38, '警察', 7, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (39, '教师', 7, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (40, '白领', 7, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (41, '未婚', 8, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (42, '已婚', 8, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (43, '离异', 8, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (44, '小学', 9, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (45, '初中', 9, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (46, '高中', 9, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (47, '大专', 9, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (48, '本科', 9, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (49, '研究生', 9, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (50, '博士', 9, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (51, '事业单位', 10, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (52, '在职', 10, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (53, '待业', 10, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (54, '自主创业', 10, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (55, '白羊座', 11, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (56, '金牛座', 11, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (57, '双子座', 11, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (58, '巨蟹座', 11, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (59, '狮子座', 11, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (60, '处女座', 11, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (61, '天秤座', 11, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (62, '天蝎座', 11, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (63, '射手座', 11, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (64, '摩蝎座', 11, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (65, '水瓶座', 11, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (66, '双鱼座', 11, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (67, '国贸CBD', 12, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (68, '西单', 12, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (69, '王府井', 12, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (70, '朝外', 12, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (71, '三里屯', 12, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (72, '崇文门', 12, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (73, '徐家汇', 12, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (74, '环球港', 12, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (75, '中国大陆', 13, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (76, '中国香港', 13, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (77, '中国澳门', 13, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (78, '中国台湾', 13, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (79, '其他', 13, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (80, '7日', 23, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (81, '2周', 23, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (82, '1月', 23, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (83, '2月', 23, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (84, '3月', 23, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (85, '4月', 23, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (86, '5月', 23, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (87, '6月', 23, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (88, '超高', 24, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (89, '高', 24, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (90, '中上', 24, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (91, '中', 24, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (92, '中下', 24, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (93, '低', 24, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (94, '很低', 24, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (95, '1-999', 25, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (96, '1000-2999', 25, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (97, '3000-4999', 25, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (98, '5000-9999', 25, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (99, '支付宝', 26, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (100, '微信', 26, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (101, '储蓄卡', 26, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (102, '信用卡', 26, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (103, '1-999', 27, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (104, '1000-2999', 27, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (105, '3000-4999', 27, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (106, '5000-9999', 27, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (107, '高', 28, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (108, '中', 28, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (109, '低', 28, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (110, '高', 29, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (111, '中', 29, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (112, '低', 29, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (113, '高', 30, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (114, '中', 30, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (115, '低', 30, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (116, '3折-4折', 49, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (117, '5折-7折', 49, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (118, '8折-9折', 49, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (119, '折扣券', 31, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (120, '活动券', 31, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (121, '积分换购', 31, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (122, '高', 32, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (123, '中', 32, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (124, '低', 32, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (125, '1天内', 33, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (126, '7天内', 33, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (127, '14天内', 33, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (128, '30天内', 33, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (129, '登录页', 34, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (130, '首页', 34, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (131, '分类页', 34, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (132, '商品页', 34, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (133, '我的订单页', 34, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (134, '1分钟以内', 35, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (135, '1-5分钟以内', 35, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (136, '5分钟以上', 35, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (137, '经常', 36, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (138, '从不', 36, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (139, '偶尔', 36, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (140, '很少', 36, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (141, 'Window', 37, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (142, 'Mac', 37, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (143, 'Linux', 37, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (144, 'Android', 37, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (145, 'IOS', 37, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (146, '1点-7点', 38, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (147, '8点-12点', 38, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (148, '13点-17点', 38, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (149, '18点-21点', 38, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (150, '22点-24点', 38, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (151, '无', 39, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (152, '较少', 39, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (153, '一般', 39, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (154, '经常', 39, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (155, '所有商品', 40, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (156, '所有商品', 41, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (157, '所有商品', 42, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (158, '所有品类', 43, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (159, '海尔', 44, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (160, '卡萨帝', 44, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (161, '摩卡', 44, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (162, '小超人', 44, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (163, '统帅', 44, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (164, '有房无贷', 45, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (165, '公积金贷款', 45, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (166, '商业贷款', 45, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (167, '无妨房', 45, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (168, '50W以内', 46, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (169, '50-100W以内', 46, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (170, '100-200W以内', 46, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (171, '200-500W以内', 46, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (172, '500W及以上', 46, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (173, '有车无贷', 47, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (174, '有车有贷', 47, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (175, '无车', 47, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (176, '10W以内', 48, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (177, '10-20W以内', 48, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (178, '20-30W以内', 48, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (179, '30-70W以内', 48, 3, 1, NULL);
INSERT INTO `rank_5_tags` VALUES (180, '70W及以上', 48, 3, 1, NULL);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (59091910668718080, 2);
INSERT INTO `user_role` VALUES (59119916342710272, 3);

SET FOREIGN_KEY_CHECKS = 1;
