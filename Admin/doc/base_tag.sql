/*
 Navicat Premium Data Transfer

 Source Server         : dalao
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : userportrait

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 19/06/2021 09:57:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_tag
-- ----------------------------
DROP TABLE IF EXISTS `base_tag`;
CREATE TABLE `base_tag`  (
  `bt_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '基础标签表主键 ',
  `bt_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '基础标签名',
  `parent_id` bigint(0) NOT NULL COMMENT '父级标签id',
  `process_status` int(0) NULL DEFAULT NULL COMMENT '进度状态（申请中、开发中、开发完成、已上线、已下线、已禁用）',
  `audit_status` int(0) NULL DEFAULT NULL COMMENT '审核状态（未通过、已通过、未处理）',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`bt_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 479 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_tag
-- ----------------------------
INSERT INTO `base_tag` VALUES (100, '人口属性', 10, 2, 1, '用户特征');
INSERT INTO `base_tag` VALUES (101, '商业属性', 10, 3, 1, '消费特征');
INSERT INTO `base_tag` VALUES (102, '行为属性', 10, 3, 1, '兴趣特征');
INSERT INTO `base_tag` VALUES (103, '用户价值', 10, 2, 1, '用户价值');
INSERT INTO `base_tag` VALUES (200, '性别', 100, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (201, '年龄段', 100, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (202, '身高', 100, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (203, '民族', 100, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (204, '籍贯', 100, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (205, '政治面貌', 100, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (206, '职业', 100, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (207, '婚姻状况', 100, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (208, '学历', 100, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (209, '就业状况', 100, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (210, '星座', 100, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (211, '所在商圈', 100, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (212, '国籍', 100, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (213, '消费周期', 101, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (214, '消费能力', 101, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (215, '客单价', 101, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (216, '支付方式', 101, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (217, '单笔最高', 101, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (218, '购买频率', 101, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (219, '退货率', 101, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (220, '换货率', 101, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (221, '有券必买', 101, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (222, '客服咨询频率', 101, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (223, '最近登录', 102, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (224, '浏览页面', 102, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (225, '浏览时长', 102, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (226, '访问频率', 102, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (227, '设备类型', 102, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (228, '浏览时段', 102, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (229, '登录频率', 102, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (230, '浏览商品', 102, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (231, '购买商品', 102, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (232, '商品偏好', 102, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (233, '品类偏好	', 102, 1, 1, NULL);
INSERT INTO `base_tag` VALUES (234, '品牌偏好', 102, 1, 1, NULL);
INSERT INTO `base_tag` VALUES (235, '房产', 103, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (236, '房产价值', 103, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (237, '车产', 103, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (238, '车产价值', 103, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (300, '男', 200, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (301, '女', 200, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (302, '50后', 201, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (303, '60后', 201, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (304, '70后', 201, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (305, '80后', 201, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (306, '90后', 201, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (307, '00后', 201, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (308, '10后', 201, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (309, '20后', 201, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (310, '150-159', 202, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (311, '160-169', 202, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (312, '170-179', 202, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (313, '180-189', 202, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (314, '190-199', 202, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (315, '200-209', 202, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (316, '210-240', 202, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (317, '汉族', 203, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (318, '蒙古族', 203, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (319, '回族', 203, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (320, '藏族', 203, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (321, '维吾尔族', 203, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (322, '苗族', 203, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (323, '满族', 203, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (324, '北京', 204, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (325, '上海', 204, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (326, '广州', 204, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (327, '深圳', 204, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (328, '杭州', 204, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (329, '苏州', 204, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (330, '群众', 205, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (331, '党员', 205, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (332, '无党派人士', 205, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (333, '学生', 206, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (334, '公务员', 206, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (335, '军人', 206, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (336, '警察', 206, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (337, '教师', 206, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (338, '白领', 206, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (339, '未婚', 207, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (340, '已婚', 207, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (341, '离异', 207, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (342, '小学', 208, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (343, '初中', 208, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (344, '高中', 208, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (345, '大专', 208, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (346, '本科', 208, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (347, '研究生', 208, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (348, '博士', 208, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (349, '事业单位', 209, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (350, '在职', 209, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (351, '待业', 209, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (352, '自主创业', 209, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (353, '白羊座', 210, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (354, '金牛座', 210, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (355, '双子座', 210, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (356, '巨蟹座', 210, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (357, '狮子座', 210, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (358, '处女座', 210, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (359, '天秤座', 210, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (360, '天蝎座', 210, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (361, '射手座', 210, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (362, '摩蝎座', 210, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (363, '水瓶座', 210, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (364, '双鱼座', 210, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (365, '国贸CBD', 211, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (366, '西单', 211, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (367, '王府井', 211, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (368, '朝外', 211, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (369, '三里屯', 211, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (370, '崇文门', 211, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (371, '徐家汇', 211, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (372, '环球港', 211, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (373, '中国大陆', 212, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (374, '中国香港', 212, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (375, '中国澳门', 212, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (376, '中国台湾', 212, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (377, '其他', 212, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (378, '7日', 213, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (379, '2周', 213, 5, 1, NULL);
INSERT INTO `base_tag` VALUES (380, '1月', 213, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (381, '2月', 213, 5, 1, NULL);
INSERT INTO `base_tag` VALUES (382, '3月', 213, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (383, '4月', 213, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (384, '5月', 213, 5, 1, NULL);
INSERT INTO `base_tag` VALUES (385, '6月', 213, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (386, '超高', 214, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (387, '高', 214, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (388, '中上', 214, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (389, '中', 214, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (390, '中下', 214, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (391, '低', 214, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (392, '很低', 214, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (393, '1-999', 215, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (394, '1000-2999', 215, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (395, '3000-4999', 215, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (396, '5000-9999', 215, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (397, '支付宝', 216, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (398, '微信', 216, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (399, '储蓄卡', 216, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (400, '信用卡', 216, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (401, '1-999', 217, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (402, '1000-2999', 217, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (403, '3000-4999', 217, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (404, '5000-9999', 217, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (405, '高', 218, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (406, '中', 218, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (407, '低', 218, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (408, '高', 219, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (409, '中', 219, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (410, '低', 219, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (411, '高', 220, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (412, '中', 220, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (413, '低', 220, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (414, '3折-4折', 49, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (415, '5折-7折', 49, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (416, '8折-9折', 49, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (417, '折扣券', 221, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (418, '活动券', 221, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (419, '积分换购', 221, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (420, '高', 222, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (421, '中', 222, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (422, '低', 222, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (423, '1天内', 223, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (424, '7天内', 223, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (425, '14天内', 223, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (426, '30天内', 223, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (427, '登录页', 224, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (428, '首页', 224, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (429, '分类页', 224, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (430, '商品页', 224, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (431, '我的订单页', 224, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (432, '1分钟以内', 225, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (433, '1-5分钟以内', 225, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (434, '5分钟以上', 225, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (435, '经常', 226, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (436, '从不', 226, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (437, '偶尔', 226, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (438, '很少', 226, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (439, 'Window', 227, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (440, 'Mac', 227, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (441, 'Linux', 227, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (442, 'Android', 227, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (443, 'IOS', 227, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (444, '1点-7点', 228, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (445, '8点-12点', 228, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (446, '13点-17点', 228, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (447, '18点-21点', 228, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (448, '22点-24点', 228, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (449, '无', 229, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (450, '较少', 229, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (451, '一般', 229, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (452, '经常', 229, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (453, '所有商品', 230, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (454, '所有商品', 231, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (455, '所有商品', 232, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (456, '所有品类', 233, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (457, '海尔', 234, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (458, '卡萨帝', 234, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (459, '摩卡', 234, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (460, '小超人', 234, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (461, '统帅', 234, 3, 1, NULL);
INSERT INTO `base_tag` VALUES (462, '有房无贷', 235, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (463, '公积金贷款', 235, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (464, '商业贷款', 235, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (465, '无妨房', 235, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (466, '50W以内', 236, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (467, '50-100W以内', 236, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (468, '100-200W以内', 236, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (469, '200-500W以内', 236, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (470, '500W及以上', 236, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (471, '有车无贷', 237, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (472, '有车有贷', 237, 2, 1, NULL);
INSERT INTO `base_tag` VALUES (473, '无车', 237, 4, 1, NULL);
INSERT INTO `base_tag` VALUES (474, '10W以内', 238, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (475, '10-20W以内', 238, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (476, '20-30W以内', 238, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (477, '30-70W以内', 238, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (478, '70W及以上', 238, 6, 1, NULL);
INSERT INTO `base_tag` VALUES (479, '健康状况', 100, 1, 1, '申请开发');

SET FOREIGN_KEY_CHECKS = 1;
