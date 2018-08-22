/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库服务器
Source Server Version : 50535
Source Host           : 127.0.0.1:3306
Source Database       : healthweb

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2018-07-24 10:02:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `account_id` int(11) NOT NULL COMMENT '主键id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '登录名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(512) DEFAULT NULL COMMENT '真实姓名',
  `create_date` date DEFAULT NULL COMMENT '账号注册或创建的时间',
  `last_login_date` date DEFAULT NULL COMMENT '上一次登录时间',
  `last_login_ip` varchar(32) DEFAULT NULL COMMENT '上一次登录IP',
  `login_qty` int(11) DEFAULT NULL COMMENT '登录次数',
  `enable` int(1) DEFAULT '1' COMMENT '状态:0冻结、1正常，默认为正常状态',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `org_id` varchar(8) DEFAULT NULL COMMENT '机构',
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_account
-- ----------------------------

-- ----------------------------
-- Table structure for tb_account_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_account_role`;
CREATE TABLE `tb_account_role` (
  `account_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_account_role
-- ----------------------------

-- ----------------------------
-- Table structure for tb_base_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_base_info`;
CREATE TABLE `tb_base_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcard` varchar(20) DEFAULT NULL COMMENT '身份证号（必填）',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名（必填）',
  `sex` int(2) DEFAULT '0' COMMENT '性别（选择，默认为男）',
  `birthday` date DEFAULT NULL COMMENT '出生日期（身份证号填入后会自动生成）',
  `tel` varchar(20) DEFAULT NULL COMMENT '手机号（必填）',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭住址',
  `guardian` varchar(15) DEFAULT NULL COMMENT '监护人/联系人',
  `guardianphone` varchar(20) DEFAULT NULL COMMENT '监护人手机号',
  `hospital` varchar(255) DEFAULT NULL COMMENT '所来自医院（选择，或根据登录的帐号自动关联）',
  `doctor` varchar(50) DEFAULT NULL COMMENT '主诊医生（根据登录帐号自动关联）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT NULL COMMENT '生成时间',
  `createid` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_base_info
-- ----------------------------
INSERT INTO `tb_base_info` VALUES ('1', '350322198802116811', '霖霖', '0', '1988-02-11', '18850707911', '', '', '', '', '', '', '2018-05-23 16:15:39', '4');
INSERT INTO `tb_base_info` VALUES ('2', '350322198802132311', '林立', '0', '1988-02-13', '18850707911', '', '', '', '', '', '', '2018-06-01 10:30:42', '4');
INSERT INTO `tb_base_info` VALUES ('3', '350322198802131234', '林立', '0', '1988-02-13', '18850707911', '', '', '', '', '', '', '2018-06-01 10:49:07', '4');
INSERT INTO `tb_base_info` VALUES ('4', '350322198802131234', '林好', '0', '1988-02-13', '18850707911', '', '', '', '', '', '', '2018-06-01 11:24:13', '4');
INSERT INTO `tb_base_info` VALUES ('5', '350322198801341234', '你好', '0', '1988-02-03', '18850707911', '', '', '', '', '', '', '2018-06-01 15:44:12', '4');
INSERT INTO `tb_base_info` VALUES ('6', '350322191101231234', '测试', '0', '1911-01-23', '18850707933', '', '', '', '', '', '', '2018-06-04 17:29:45', '4');
INSERT INTO `tb_base_info` VALUES ('7', '', '测试二', '0', null, '18850707911', '', '', '', '', '', '', '2018-06-06 15:05:54', '4');
INSERT INTO `tb_base_info` VALUES ('8', '', '测试一', '0', null, '18850707911', '', '', '', '', '', '', '2018-06-06 15:24:45', '4');
INSERT INTO `tb_base_info` VALUES ('9', '', '测试一', '0', null, '18850707911', '', '', '', '', '', '', '2018-06-06 15:31:43', '4');
INSERT INTO `tb_base_info` VALUES ('10', '', '测试二', '0', null, '18850707911', '', '', '', '', '', '', '2018-06-06 16:12:47', '4');
INSERT INTO `tb_base_info` VALUES ('11', '350322199902131234', '测试三', '0', '1999-02-13', '18860808911', '', '', '', '', '', '', '2018-06-06 17:29:30', '4');
INSERT INTO `tb_base_info` VALUES ('12', '350322197801231234', '测试四', '0', '1978-01-23', '18850808911', '', '', '', '', '', '', '2018-06-06 18:03:47', '4');
INSERT INTO `tb_base_info` VALUES ('13', '350322197702131234', '测试五', '0', '1977-02-13', '18850707911', '', '', '', '', '', '', '2018-06-07 11:34:28', '4');
INSERT INTO `tb_base_info` VALUES ('14', '', '林林', '0', null, '18850707911', '', '', '', '', '', '', '2018-06-12 15:43:20', '4');

-- ----------------------------
-- Table structure for tb_circle_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_circle_info`;
CREATE TABLE `tb_circle_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reportid` int(11) DEFAULT NULL COMMENT 'report的ID',
  `centerx_left1` double(12,5) DEFAULT NULL COMMENT '左眼图片圆的X1',
  `centery_left1` double(12,5) DEFAULT NULL COMMENT '左眼图片圆的Y1',
  `radius_left1` double(12,5) DEFAULT NULL COMMENT '左眼图片圆的半径1',
  `centerx_right1` double(12,5) DEFAULT NULL COMMENT '右眼图片圆的X1',
  `centery_right1` double(12,5) DEFAULT NULL COMMENT '右眼图片圆的Y1',
  `radius_right1` double(12,5) DEFAULT NULL COMMENT '右眼图片圆的半径1',
  `centerx_left2` double(12,5) DEFAULT NULL COMMENT '左眼图片圆的X2',
  `centery_left2` double(12,5) DEFAULT NULL COMMENT '左眼图片圆的Y2',
  `radius_left2` double(12,5) DEFAULT NULL COMMENT '左眼图片圆的半径2',
  `centerx_right2` double(12,5) DEFAULT NULL COMMENT '右眼图片圆的X2',
  `centery_right2` double(12,5) DEFAULT NULL COMMENT '右眼图片圆的Y2',
  `radius_right2` double(12,5) DEFAULT NULL COMMENT '右眼图片圆的半径2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_circle_info
-- ----------------------------
INSERT INTO `tb_circle_info` VALUES ('1', '1', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000');
INSERT INTO `tb_circle_info` VALUES ('2', '2', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000');
INSERT INTO `tb_circle_info` VALUES ('3', '3', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000');
INSERT INTO `tb_circle_info` VALUES ('4', '4', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000');
INSERT INTO `tb_circle_info` VALUES ('5', '5', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000');
INSERT INTO `tb_circle_info` VALUES ('6', '6', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000');
INSERT INTO `tb_circle_info` VALUES ('7', '7', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000');
INSERT INTO `tb_circle_info` VALUES ('8', '8', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000');
INSERT INTO `tb_circle_info` VALUES ('9', '9', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000');
INSERT INTO `tb_circle_info` VALUES ('10', '10', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000');
INSERT INTO `tb_circle_info` VALUES ('11', '11', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000', '0.00000');

-- ----------------------------
-- Table structure for tb_client_exam
-- ----------------------------
DROP TABLE IF EXISTS `tb_client_exam`;
CREATE TABLE `tb_client_exam` (
  `exam_time` date NOT NULL COMMENT '检查时间',
  `exam_no` varchar(4) NOT NULL COMMENT '检查登记号',
  `trml_id` varchar(8) NOT NULL COMMENT '设备编号',
  `patient_id` varchar(18) DEFAULT NULL COMMENT '客户ID',
  `left_vision` float(255,0) DEFAULT NULL COMMENT '左眼日常视力',
  `right_vision` float(255,0) DEFAULT NULL COMMENT '右眼日常视力',
  `vision_remark` varchar(12) DEFAULT NULL COMMENT '日常视力备注',
  `microscopy_result` varchar(50) DEFAULT NULL COMMENT '显微镜检查结果',
  `pic_qty` tinyint(2) DEFAULT NULL COMMENT '本次拍照数量',
  `operator_id` varchar(6) DEFAULT NULL COMMENT '拍摄工作人员的id',
  `left_pic_no` varchar(21) DEFAULT NULL COMMENT '左眼图片NO',
  `left_pic_path` varchar(100) DEFAULT NULL COMMENT '左眼图像相对路径',
  `right_pic_no` varchar(21) DEFAULT NULL COMMENT '右眼图片NO',
  `right_pic_path` varchar(100) DEFAULT NULL COMMENT '右眼图像相对路径',
  `dr` tinyint(1) DEFAULT NULL COMMENT 'DR分级:0:无、1:1级、2:2级、3:3级',
  `dme` tinyint(1) DEFAULT NULL COMMENT 'DME分级:0无、1轻度、2中度、3重度',
  `dn` tinyint(1) DEFAULT NULL COMMENT '有无糖尿病肾病:0没有、1有',
  `glaucoma` tinyint(1) DEFAULT NULL COMMENT '是否青光眼:0不是、1是',
  `cataract` tinyint(1) DEFAULT NULL COMMENT '是否白内障:0不是、1是',
  `diagnosis` varchar(120) DEFAULT NULL COMMENT '其他诊断结果',
  `suggest` tinyint(1) DEFAULT NULL COMMENT '下一步建议:0半年后复查、1:1年后复查、2转诊',
  `diagnostician` varchar(6) DEFAULT NULL COMMENT '判读者ID:判读人员的userid',
  `diagnosistime` date DEFAULT NULL COMMENT '诊断时间',
  `state` tinyint(1) DEFAULT NULL COMMENT '本次检查当前状态:0未拍照、1未上传照片、2已上传，待接收、3合格接收，待判读、4已完成自动判读、5已完成自动修正、6已完成人工修正、7已完成人工确认，判读结束',
  `report` tinyint(1) DEFAULT NULL COMMENT '报告状态:0报告未生成、1报告待审核、2报告驳回，修改、3合格报告可打印',
  PRIMARY KEY (`exam_time`,`exam_no`,`trml_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_client_exam
-- ----------------------------

-- ----------------------------
-- Table structure for tb_dict
-- ----------------------------
DROP TABLE IF EXISTS `tb_dict`;
CREATE TABLE `tb_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典主键 无逻辑意义',
  `parentId` int(11) DEFAULT NULL COMMENT '父id',
  `code` varchar(150) DEFAULT NULL COMMENT '字典代码',
  `name` varchar(150) DEFAULT NULL COMMENT '字典名称',
  `sequence` int(11) DEFAULT NULL COMMENT '排序',
  `status` int(2) DEFAULT '0' COMMENT '状态：0-启用；1-停用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_dict
-- ----------------------------
INSERT INTO `tb_dict` VALUES ('1', '0', 'status', '状态', null, '1', '2017-04-18 17:04:21', '2017-04-18 17:04:21');
INSERT INTO `tb_dict` VALUES ('2', '1', 'on', '启用', null, '0', '2017-04-19 11:06:46', '2017-04-19 11:06:46');
INSERT INTO `tb_dict` VALUES ('3', '1', 'off', '停用', null, '0', '2017-04-18 17:04:41', '2017-04-18 17:04:41');
INSERT INTO `tb_dict` VALUES ('4', '0', 'tf', '是否', null, '0', '2017-04-19 11:08:06', '2017-04-19 11:08:06');
INSERT INTO `tb_dict` VALUES ('5', '4', 'true', '是', null, '0', '2017-05-03 11:26:38', '2017-05-03 11:26:38');
INSERT INTO `tb_dict` VALUES ('6', '4', 'false', '否', null, '1', '2017-06-08 17:24:40', '2017-06-08 17:24:40');
INSERT INTO `tb_dict` VALUES ('7', '0', 'p00', '职位', null, '0', '2017-07-16 11:24:21', '2017-07-16 11:24:21');
INSERT INTO `tb_dict` VALUES ('8', '7', 'p01', '医生', null, '0', '2017-07-16 11:23:13', null);
INSERT INTO `tb_dict` VALUES ('9', '7', 'p02', '护士', null, '0', '2017-07-16 11:23:36', null);
INSERT INTO `tb_dict` VALUES ('10', '7', 'p03', '患者或亲属', null, '0', '2017-07-16 11:23:47', null);
INSERT INTO `tb_dict` VALUES ('11', '0', 'feature', '特征', null, '0', '2018-02-04 20:59:00', null);
INSERT INTO `tb_dict` VALUES ('12', '11', 'f01', '微血管瘤', null, '0', '2018-02-04 21:00:02', null);
INSERT INTO `tb_dict` VALUES ('13', '11', 'f02', '出血点', null, '0', '2018-02-04 21:08:41', null);
INSERT INTO `tb_dict` VALUES ('14', '11', 'f03', '硬性渗出及到黄斑中心距离', null, '0', '2018-02-04 21:08:52', null);
INSERT INTO `tb_dict` VALUES ('15', '11', 'f04', '棉絮斑', null, '0', '2018-02-04 21:09:02', null);
INSERT INTO `tb_dict` VALUES ('16', '11', 'f05', '弥漫性视网膜动脉缩窄', null, '0', '2018-02-04 21:09:13', null);
INSERT INTO `tb_dict` VALUES ('17', '11', 'f06', '动静脉交叉压迫症', null, '0', '2018-02-04 21:09:23', null);
INSERT INTO `tb_dict` VALUES ('18', '11', 'f07', '动脉管理浑浊，即铜丝样或银丝样改变', null, '0', '2018-02-04 21:09:34', null);
INSERT INTO `tb_dict` VALUES ('19', '11', 'f08', '局限性动脉管径缩窄', null, '0', '2018-02-04 21:09:45', null);
INSERT INTO `tb_dict` VALUES ('20', '11', 'f09', '局限性视网膜神经纤维层缺损', null, '0', '2018-02-04 21:09:54', null);
INSERT INTO `tb_dict` VALUES ('21', '11', 'f10', '静脉串珠改变', null, '0', '2018-02-04 21:10:04', null);
INSERT INTO `tb_dict` VALUES ('22', '0', 'result', '诊断结果', null, '0', '2018-02-04 21:14:44', null);
INSERT INTO `tb_dict` VALUES ('23', '22', 'r01', '未见异常', null, '0', '2018-02-04 21:15:15', null);
INSERT INTO `tb_dict` VALUES ('24', '22', 'r02', 'NPDR Ⅰ期', null, '0', '2018-02-04 21:15:37', null);
INSERT INTO `tb_dict` VALUES ('25', '22', 'r03', 'NPDR Ⅱ期', null, '0', '2018-02-04 21:16:04', null);
INSERT INTO `tb_dict` VALUES ('26', '22', 'r04', 'NPDR Ⅲ期', null, '0', '2018-02-04 21:16:14', null);
INSERT INTO `tb_dict` VALUES ('27', '22', 'r05', 'NPDR Ⅳ期', null, '0', '2018-02-04 21:16:25', null);
INSERT INTO `tb_dict` VALUES ('28', '22', 'r06', 'NPDR Ⅴ期', null, '0', '2018-02-04 21:16:35', null);
INSERT INTO `tb_dict` VALUES ('29', '22', 'r07', 'NPDR Ⅵ期', null, '0', '2018-02-04 21:16:45', null);
INSERT INTO `tb_dict` VALUES ('30', '22', 'r08', '黄斑水肿', null, '0', '2018-02-04 21:17:00', null);
INSERT INTO `tb_dict` VALUES ('31', '22', 'r09', '黄斑病变待排除', null, '0', '2018-02-04 21:17:12', null);
INSERT INTO `tb_dict` VALUES ('32', '22', 'r10', '轻度血管改变', null, '0', '2018-02-04 21:17:21', null);
INSERT INTO `tb_dict` VALUES ('33', '22', 'r11', '中度血管改变', null, '0', '2018-02-04 21:17:33', null);
INSERT INTO `tb_dict` VALUES ('34', '22', 'r12', '重度血管改变', null, '0', '2018-02-04 21:17:43', null);
INSERT INTO `tb_dict` VALUES ('35', '22', 'r13', '青光眼待排除', null, '0', '2018-02-04 21:17:53', null);
INSERT INTO `tb_dict` VALUES ('36', '22', 'r14', '其他', null, '0', '2018-02-04 21:18:05', null);
INSERT INTO `tb_dict` VALUES ('37', '0', 'suggest', '医生建议', null, '0', '2018-02-04 21:20:20', null);
INSERT INTO `tb_dict` VALUES ('38', '37', 's01', '定期复查', null, '0', '2018-02-04 21:20:33', null);
INSERT INTO `tb_dict` VALUES ('39', '37', 's02', '6个月复查，建议使用促进微循环药物', null, '0', '2018-02-04 21:20:46', null);
INSERT INTO `tb_dict` VALUES ('40', '37', 's03', '建议控制饮食，加强运动，减少体重', null, '0', '2018-02-04 21:21:02', null);
INSERT INTO `tb_dict` VALUES ('41', '37', 's04', '建议到眼科就诊或FAA复诊', null, '0', '2018-02-04 21:21:14', null);
INSERT INTO `tb_dict` VALUES ('42', '37', 's05', '建议眼底激光或药物注射治疗', null, '0', '2018-02-04 21:21:24', null);
INSERT INTO `tb_dict` VALUES ('43', '37', 's06', '建议进入脑心血管疾病的一级或二级预防治疗', null, '0', '2018-02-04 21:21:33', null);
INSERT INTO `tb_dict` VALUES ('44', '37', 's07', '其他', null, '0', '2018-02-04 21:21:42', null);
INSERT INTO `tb_dict` VALUES ('45', '0', 'gender', '性别', null, '0', '2018-03-21 09:25:53', null);
INSERT INTO `tb_dict` VALUES ('46', '45', '0', '男', null, '0', '2018-03-21 09:26:12', null);
INSERT INTO `tb_dict` VALUES ('47', '45', '1', '女', null, '0', '2018-03-21 09:26:21', null);
INSERT INTO `tb_dict` VALUES ('48', '7', 'p04', '管理员', null, '0', '2017-07-16 11:23:36', null);
INSERT INTO `tb_dict` VALUES ('49', '7', 'p05', '基层', null, '0', '2018-04-10 10:03:52', null);
INSERT INTO `tb_dict` VALUES ('50', '7', 'p06', '远程会诊中心', null, '0', '2018-04-10 10:04:12', null);

-- ----------------------------
-- Table structure for tb_family_member
-- ----------------------------
DROP TABLE IF EXISTS `tb_family_member`;
CREATE TABLE `tb_family_member` (
  `patient_id` varchar(18) NOT NULL COMMENT '客户系统ID',
  `relationship` int(255) NOT NULL COMMENT '亲属关系:1配偶、2父母 、3子女 、4其他亲属、 5朋友、 6其他',
  `member_name` varchar(20) DEFAULT NULL COMMENT '亲属姓名',
  `member_sex` tinyint(1) DEFAULT NULL COMMENT '亲属性别:1男 、2女',
  `member_birth` date DEFAULT NULL COMMENT '亲属出生年月',
  `member_phone` varchar(15) DEFAULT NULL COMMENT '亲属电话',
  `member_address` varchar(30) DEFAULT NULL COMMENT '地址',
  `member_height` float DEFAULT NULL COMMENT '亲属身高(以cm为单位)',
  `member_weight` float DEFAULT NULL COMMENT '亲属体重(以kg为单位)',
  `member_health` varchar(60) DEFAULT NULL COMMENT '亲属健康状况',
  `member_remark` varchar(60) DEFAULT NULL COMMENT '亲属备注',
  PRIMARY KEY (`patient_id`,`relationship`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_family_member
-- ----------------------------

-- ----------------------------
-- Table structure for tb_health_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `tb_health_evaluation`;
CREATE TABLE `tb_health_evaluation` (
  `exam_time` date NOT NULL COMMENT '检查时间',
  `exam_no` varchar(4) NOT NULL COMMENT '检查登记号',
  `trml_id` varchar(8) NOT NULL COMMENT '设备编号',
  `patient_id` varchar(18) DEFAULT NULL COMMENT '客户ID',
  `hypertension` int(1) DEFAULT NULL COMMENT '高血压分层:0—3，参照标准分级',
  `stroke_risk` int(1) DEFAULT NULL COMMENT '五年脑卒中发病风险:0低风险、1中风险、2高风险',
  `stoke_risk_value` float(255,0) DEFAULT NULL COMMENT '脑卒中风险评估值:上一属性定性，本属性定量',
  PRIMARY KEY (`exam_time`,`exam_no`,`trml_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_health_evaluation
-- ----------------------------

-- ----------------------------
-- Table structure for tb_health_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_health_info`;
CREATE TABLE `tb_health_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `height` int(11) DEFAULT NULL COMMENT '身高（cm为单位，录入 ，必填）',
  `weight` int(11) DEFAULT NULL COMMENT '体重（kg为单位，录入，必填）',
  `bmi` varchar(11) DEFAULT NULL COMMENT '体重/身高(米平方)',
  `waist` varchar(11) DEFAULT NULL COMMENT '腰围（cm，录入）',
  `menopause` int(2) DEFAULT NULL COMMENT '性别（若为女性，需选择是否绝经）',
  `bloodpressure` int(2) DEFAULT '0' COMMENT '血压（高压/低压格式输入）',
  `pressure` varchar(11) DEFAULT NULL COMMENT '血压值',
  `state` int(2) DEFAULT '0' COMMENT '目前健康状况（选择）：良好、一般、较差',
  `labour` int(2) DEFAULT '0' COMMENT '体力劳动或体育运动锻炼（选择）：基本不运动，每月1-2次，一周1-2次，每天运动',
  `fruit` int(2) DEFAULT '0' COMMENT '水果蔬菜摄入情况（选择）：无、偶尔吃、经常吃、每天都吃',
  `drink` int(2) DEFAULT '0' COMMENT '是否经常性饮酒（选择）：从不、偶尔、一周1-2次、每天饮超过1瓶啤酒',
  `diabetes` int(2) DEFAULT NULL COMMENT '糖尿病史：默认为空',
  `diagnose1` date DEFAULT NULL COMMENT '若选择有，则可以选择确诊年份（可选）',
  `relatives` int(2) DEFAULT NULL COMMENT '若选择有，则选择直系亲属或亲戚是否有糖尿病（必选）',
  `glaucoma` int(2) DEFAULT NULL COMMENT '青光眼：默认为空',
  `diagnose2` date DEFAULT NULL COMMENT '若选择有，可选择确诊年份',
  `hypertension` int(2) DEFAULT NULL COMMENT '高血压病史：默认为空',
  `diagnose3` date DEFAULT NULL COMMENT '若选择有，可选择确诊年份',
  `cholesterol` int(11) DEFAULT NULL COMMENT '若选择有，可输入总胆固醇数值',
  `medicine` int(2) DEFAULT NULL COMMENT '若选择有，可选择否曾经定期服用过抗高血压药物',
  `symptom` varchar(255) DEFAULT NULL COMMENT '主诉症状：默认为空，可录入',
  `leftvision` varchar(11) DEFAULT NULL COMMENT '左眼视力',
  `rightvision` varchar(11) DEFAULT NULL COMMENT '右眼视力',
  `leftpic` varchar(255) DEFAULT NULL COMMENT '左眼图片',
  `rightpic` varchar(255) DEFAULT NULL COMMENT '右眼图片',
  `leftaid` varchar(20) DEFAULT NULL,
  `rightaid` varchar(20) DEFAULT NULL,
  `baseinfoid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_health_info
-- ----------------------------
INSERT INTO `tb_health_info` VALUES ('1', '170', '65', '22.49', '', null, '2', '', '1', '1', '1', '1', '0', null, null, '0', null, '0', null, null, null, '', '', '', '/static/eye_image/1527063339142/left/微信图片_20180314102127.jpg', '/static/eye_image/1527063339142/right/微信图片_20180314102217.jpg', null, null, '1');
INSERT INTO `tb_health_info` VALUES ('2', '170', '70', '24.22', '', null, '1', '', '1', '1', '1', '1', '0', null, null, '0', null, '0', null, null, null, '', '', '', '/static/eye_image/1527821347146/left/微信图片_20180314102127.jpg', '/static/eye_image/1527821347146/right/微信图片_20180314102217.jpg', '57741', '57742', '3');
INSERT INTO `tb_health_info` VALUES ('3', '180', '70', '21.60', '', null, '2', '', '1', '1', '1', '1', '0', null, null, '0', null, '0', null, null, null, '', '', '', '/static/eye_image/1527823453573/left/微信图片_20180314102127.jpg', '/static/eye_image/1527823453573/right/微信图片_20180314102217.jpg', '57743', '57744', '4');
INSERT INTO `tb_health_info` VALUES ('4', '180', '80', '24.69', '', null, '2', '', '1', '1', '1', '1', '0', null, null, '0', null, '0', null, null, null, '', '', '', '/static/eye_image/1527839052794/left/微信图片_reverse_20180314102217.jpg', '/static/eye_image/1527839052794/right/微信图片_20180314102217.jpg', '57745', '57746', '5');
INSERT INTO `tb_health_info` VALUES ('5', '180', '50', '15.43', '', null, '2', '', '1', '1', '1', '1', '1', null, null, '0', null, '0', null, null, null, '', '', '', '/static/eye_image/1528104585728/left/微信图片_20180314102127.jpg', '/static/eye_image/1528104585728/right/微信图片_reverse_20180314102217.jpg', '57758', '57759', '6');
INSERT INTO `tb_health_info` VALUES ('6', '170', '65', '22.49', '', null, '2', '', '1', '1', '1', '1', '0', null, null, '0', null, '0', null, null, null, '', '', '', '/static/eye_image/1528270303320/left/微信图片_20180314102127.jpg', '/static/eye_image/1528270303320/right/微信图片_20180314102217.jpg', '57775', '57776', '9');
INSERT INTO `tb_health_info` VALUES ('7', '170', '65', '22.49', '', null, '2', '', '1', '1', '1', '1', '0', null, null, '0', null, '0', null, null, null, '', '', '', '/static/eye_image/1528272767775/left/微信图片_20180314102127.jpg', '/static/eye_image/1528272767775/right/', '57777', null, '10');
INSERT INTO `tb_health_info` VALUES ('8', '190', '80', '22.16', '', null, '2', '', '1', '1', '1', '1', '0', null, null, '0', null, '0', null, null, null, '', '', '', '/static/eye_image/1528277370467/left/', '/static/eye_image/1528277370467/right/微信图片_20180314102217.jpg', null, null, '11');
INSERT INTO `tb_health_info` VALUES ('9', '150', '50', '22.22', '', null, '2', '', '1', '1', '1', '1', '0', null, null, '0', null, '0', null, null, null, '', '', '', '/static/eye_image/1528279427142/left/微信图片_reverse_20180314102217.jpg', '/static/eye_image/1528279427142/right/微信图片_20180314102127.jpg', '57779', '57780', '12');
INSERT INTO `tb_health_info` VALUES ('10', '170', '65', '22.49', '', null, '2', '', '1', '1', '1', '1', '0', null, null, '0', null, '0', null, null, null, '', '', '', '/static/eye_image/1528342468172/left/微信图片_reverse_20180314102217.jpg', '/static/eye_image/1528342468172/right/微信图片_20180314102217.jpg', '57783', '57784', '13');
INSERT INTO `tb_health_info` VALUES ('11', '170', '65', '22.49', '', null, '2', '', '1', '1', '1', '1', '0', null, null, '0', null, '0', null, null, null, '', '', '', '/static/eye_image/1528789400534/left/微信图片_reverse_20180314102217.jpg', '/static/eye_image/1528789400534/right/微信图片_20180314102217.jpg', null, null, '14');

-- ----------------------------
-- Table structure for tb_hemorrhage
-- ----------------------------
DROP TABLE IF EXISTS `tb_hemorrhage`;
CREATE TABLE `tb_hemorrhage` (
  `pic_no` varchar(21) CHARACTER SET utf8 NOT NULL COMMENT '图片no，是图片id的简化:检查时间（8位）+设备ID（5）+检查登记号(4)+流水号(3)+左右眼(1位，0表示左，1表示右)',
  `no` tinyint(4) NOT NULL COMMENT '出血点在其所属图片中的编号',
  `he_size` tinyint(4) DEFAULT NULL COMMENT '出血点的面积:用像素点个数来表示（或是考虑像素点个数比例）',
  `point0_point11_x` float(255,0) DEFAULT NULL COMMENT '标记出血点边缘的点坐标:最多12个点，每个点都有x、y坐标',
  `point0_point11_y` float(255,0) DEFAULT NULL COMMENT '标记出血点边缘的点坐标:最多12个点，每个点都有x、y坐标',
  PRIMARY KEY (`pic_no`,`no`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tb_hemorrhage
-- ----------------------------

-- ----------------------------
-- Table structure for tb_hes
-- ----------------------------
DROP TABLE IF EXISTS `tb_hes`;
CREATE TABLE `tb_hes` (
  `pic_no` varchar(21) NOT NULL COMMENT '图片no，是图片id的简化:检查时间（8位）+设备ID（5）+检查登记号(4)+流水号(3)+左右眼(1位，0表示左，1表示右)',
  `no` tinyint(4) NOT NULL COMMENT '硬性渗出在其所属图片中的编号',
  `point0_point11_x` float(255,0) DEFAULT NULL COMMENT '标记硬性渗出边缘的点坐标:最多12个点，每个点都有x、y坐标',
  `point0_point11_y` float(255,0) DEFAULT NULL COMMENT '标记硬性渗出边缘的点坐标:最多12个点，每个点都有x、y坐标',
  `distance` float DEFAULT NULL COMMENT '硬性渗出与黄斑中心凹的最小距离',
  PRIMARY KEY (`pic_no`,`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_hes
-- ----------------------------

-- ----------------------------
-- Table structure for tb_mas
-- ----------------------------
DROP TABLE IF EXISTS `tb_mas`;
CREATE TABLE `tb_mas` (
  `pic_no` varchar(21) NOT NULL COMMENT '图片no，是图片id的简化:检查时间（8位）+设备ID（5）+检查登记号(4)+流水号(3)+左右眼(1位，0表示左，1表示右)',
  `ma_no` tinyint(4) NOT NULL COMMENT '微血管瘤在其所属图片中的编号',
  `ma_x` float(255,0) DEFAULT NULL COMMENT '微血管瘤的x坐标',
  `ma_y` float(255,0) DEFAULT NULL COMMENT '微血管瘤的Y坐标',
  `ma_angle` float(255,0) DEFAULT NULL COMMENT '角度:黄斑坐标系状态下，与x正方向的夹角',
  `distance` float(255,0) DEFAULT NULL COMMENT '在坐标系中的相对距离:用微血管瘤到坐标系的直线距离/黄斑视盘距离',
  PRIMARY KEY (`pic_no`,`ma_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_mas
-- ----------------------------

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parentId` int(11) DEFAULT NULL COMMENT '父资源',
  `name` varchar(150) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL COMMENT '序列号',
  `status` int(2) DEFAULT '0' COMMENT '状态：0-启用；1-停用',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '0', '系统管理', '#', 'fa-wrench', '0', '0', '2017-06-10 10:41:31', '2017-06-10 10:41:31');
INSERT INTO `tb_menu` VALUES ('2', '1', '数据词典', '/page/sys/dict/list.jsp', '', '1', '0', '2018-07-17 10:48:51', '2018-07-17 10:48:51');
INSERT INTO `tb_menu` VALUES ('3', '1', '角色权限', '/page/sys/role/list.jsp', '', '1', '0', '2017-04-13 22:10:33', '2017-04-13 22:10:33');
INSERT INTO `tb_menu` VALUES ('4', '1', '菜单管理', '/page/sys/menu/list.jsp', '', '1', '0', null, null);
INSERT INTO `tb_menu` VALUES ('5', '1', '用户管理', '/page/sys/user/list.jsp', '', '1', '0', '2017-06-08 17:06:17', '2017-06-08 17:06:17');
INSERT INTO `tb_menu` VALUES ('6', '1', 'SQL监控', '/page/sys/druid/index.jsp', '', '1', '0', '2017-06-08 17:07:13', '2017-06-08 17:07:13');
INSERT INTO `tb_menu` VALUES ('7', '0', '会诊中心', '#', 'fa-headphones', null, '0', '2018-07-06 21:10:29', '2018-07-06 21:10:29');
INSERT INTO `tb_menu` VALUES ('8', '7', '远程判读', '/page/health/homepage/index.jsp', '', null, '0', '2018-01-30 19:33:35', null);
INSERT INTO `tb_menu` VALUES ('9', '1', '机构管理', '/page/sys/organization/list.jsp', '', null, '0', '2018-07-10 14:09:05', null);
INSERT INTO `tb_menu` VALUES ('10', '1', '设备管理', '/page/sys/terminal/list.jsp', '', null, '0', '2018-07-11 18:01:08', null);
INSERT INTO `tb_menu` VALUES ('11', '1', '日志管理', '/page/sys/log/list.jsp', '', null, '0', '2018-07-12 11:13:26', null);

-- ----------------------------
-- Table structure for tb_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_operate_log`;
CREATE TABLE `tb_operate_log` (
  `log_id` int(18) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_name` varchar(32) DEFAULT NULL COMMENT '登录名',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `ip` varchar(30) DEFAULT NULL COMMENT '登录IP',
  `result` varchar(4) DEFAULT NULL COMMENT '登录结果:0成功、1密码错误、2用户不存在、3用户状态异常',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `operate_content` varchar(255) DEFAULT NULL COMMENT '操作内容',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_operate_log
-- ----------------------------
INSERT INTO `tb_operate_log` VALUES ('4', 'admin', '管理员', '192.168.3.8', '0', null, '管理员于[2018-07-12 16:02:54]删除了一台设备[佳能 c100]', '2018-07-12 16:02:54');
INSERT INTO `tb_operate_log` VALUES ('5', 'admin', '管理员', '192.168.3.8', '0', null, '管理员于[2018-07-12 16:04:35]新增了一台设备[佳能 c300]', '2018-07-12 16:04:35');
INSERT INTO `tb_operate_log` VALUES ('6', 'admin', '管理员', '192.168.3.8', '0', null, '管理员于[2018-07-12 16:08:22]删除了一个机构[机构34]', '2018-07-12 16:08:22');
INSERT INTO `tb_operate_log` VALUES ('7', 'admin', '管理员', '192.168.3.8', '0', null, '管理员于[2018-07-12 18:21:02]更新了一个机构[机构12]', '2018-07-12 18:21:02');
INSERT INTO `tb_operate_log` VALUES ('8', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 15:29:43]登录系统', '2018-07-16 15:29:43');
INSERT INTO `tb_operate_log` VALUES ('9', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 15:30:37]退出系统', '2018-07-16 15:30:37');
INSERT INTO `tb_operate_log` VALUES ('10', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 15:33:15]登录系统', '2018-07-16 15:33:15');
INSERT INTO `tb_operate_log` VALUES ('11', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 15:34:40]退出系统', '2018-07-16 15:34:40');
INSERT INTO `tb_operate_log` VALUES ('12', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 15:39:22]登录系统', '2018-07-16 15:39:22');
INSERT INTO `tb_operate_log` VALUES ('13', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 15:39:49]退出系统', '2018-07-16 15:39:49');
INSERT INTO `tb_operate_log` VALUES ('14', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 15:40:05]登录系统', '2018-07-16 15:40:05');
INSERT INTO `tb_operate_log` VALUES ('15', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 15:45:49]登录系统', '2018-07-16 15:45:49');
INSERT INTO `tb_operate_log` VALUES ('16', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 15:51:54]登录系统', '2018-07-16 15:51:54');
INSERT INTO `tb_operate_log` VALUES ('17', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 15:55:30]登录系统', '2018-07-16 15:55:30');
INSERT INTO `tb_operate_log` VALUES ('18', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 16:01:30]登录系统', '2018-07-16 16:01:30');
INSERT INTO `tb_operate_log` VALUES ('21', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 16:24:49]登录系统', '2018-07-16 16:24:49');
INSERT INTO `tb_operate_log` VALUES ('22', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 16:28:34]登录系统', '2018-07-16 16:28:34');
INSERT INTO `tb_operate_log` VALUES ('23', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 16:31:10]登录系统', '2018-07-16 16:31:10');
INSERT INTO `tb_operate_log` VALUES ('24', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 16:34:12]登录系统', '2018-07-16 16:34:12');
INSERT INTO `tb_operate_log` VALUES ('25', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 16:37:09]登录系统', '2018-07-16 16:37:09');
INSERT INTO `tb_operate_log` VALUES ('26', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 16:44:55]登录系统', '2018-07-16 16:44:55');
INSERT INTO `tb_operate_log` VALUES ('27', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 16:51:12]登录系统', '2018-07-16 16:51:12');
INSERT INTO `tb_operate_log` VALUES ('28', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 16:54:16]更新了一个机构[机构12]', '2018-07-16 16:54:16');
INSERT INTO `tb_operate_log` VALUES ('29', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 17:09:35]登录系统', '2018-07-16 17:09:35');
INSERT INTO `tb_operate_log` VALUES ('30', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 17:14:12]更新了一位用户[管理员]', '2018-07-16 17:14:12');
INSERT INTO `tb_operate_log` VALUES ('31', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 17:15:18]更新了一位用户[医生01]', '2018-07-16 17:15:18');
INSERT INTO `tb_operate_log` VALUES ('32', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 17:15:28]更新了一位用户[管理员]', '2018-07-16 17:15:28');
INSERT INTO `tb_operate_log` VALUES ('33', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 17:15:35]更新了一位用户[基层01]', '2018-07-16 17:15:35');
INSERT INTO `tb_operate_log` VALUES ('34', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 17:15:50]更新了一位用户[远程会诊01]', '2018-07-16 17:15:50');
INSERT INTO `tb_operate_log` VALUES ('35', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 17:20:22]登录系统', '2018-07-16 17:20:22');
INSERT INTO `tb_operate_log` VALUES ('36', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 18:03:14]登录系统', '2018-07-16 18:03:14');
INSERT INTO `tb_operate_log` VALUES ('37', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 18:06:11]登录系统', '2018-07-16 18:06:11');
INSERT INTO `tb_operate_log` VALUES ('38', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 18:25:23]新增了一位用户[张三]', '2018-07-16 18:25:23');
INSERT INTO `tb_operate_log` VALUES ('39', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 18:27:49]更新了一位用户[张三]', '2018-07-16 18:27:49');
INSERT INTO `tb_operate_log` VALUES ('40', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 18:28:03]更新了一位用户[医生01]', '2018-07-16 18:28:03');
INSERT INTO `tb_operate_log` VALUES ('41', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 18:29:59]登录系统', '2018-07-16 18:29:59');
INSERT INTO `tb_operate_log` VALUES ('42', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-16 18:34:50]登录系统', '2018-07-16 18:34:50');
INSERT INTO `tb_operate_log` VALUES ('43', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 09:44:33]登录系统', '2018-07-17 09:44:33');
INSERT INTO `tb_operate_log` VALUES ('44', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 09:50:11]登录系统', '2018-07-17 09:50:11');
INSERT INTO `tb_operate_log` VALUES ('45', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 10:01:26]登录系统', '2018-07-17 10:01:26');
INSERT INTO `tb_operate_log` VALUES ('46', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 10:12:29]登录系统', '2018-07-17 10:12:29');
INSERT INTO `tb_operate_log` VALUES ('47', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 10:47:54]登录系统', '2018-07-17 10:47:54');
INSERT INTO `tb_operate_log` VALUES ('48', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 10:48:21]更新了一个菜单[数据词典]', '2018-07-17 10:48:21');
INSERT INTO `tb_operate_log` VALUES ('49', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 10:48:51]更新了一个菜单[数据词典]', '2018-07-17 10:48:51');
INSERT INTO `tb_operate_log` VALUES ('50', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 11:32:16]登录系统', '2018-07-17 11:32:16');
INSERT INTO `tb_operate_log` VALUES ('51', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 11:33:01]登录系统', '2018-07-17 11:33:01');
INSERT INTO `tb_operate_log` VALUES ('52', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 11:37:11]新增了一个角色[测试角色]', '2018-07-17 11:37:11');
INSERT INTO `tb_operate_log` VALUES ('53', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 11:39:34]编辑了一个角色[测试角色]', '2018-07-17 11:39:34');
INSERT INTO `tb_operate_log` VALUES ('54', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 11:40:59]删除了一个角色[测试角色]', '2018-07-17 11:40:59');
INSERT INTO `tb_operate_log` VALUES ('55', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-17 14:28:08]登录系统', '2018-07-17 14:28:08');
INSERT INTO `tb_operate_log` VALUES ('56', 'admin', '管理员', '192.168.2.66', '0', null, '管理员于[2018-07-17 14:52:24]登录系统', '2018-07-17 14:52:24');
INSERT INTO `tb_operate_log` VALUES ('57', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 10:00:03]登录系统', '2018-07-18 10:00:03');
INSERT INTO `tb_operate_log` VALUES ('58', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 10:36:26]登录系统', '2018-07-18 10:36:26');
INSERT INTO `tb_operate_log` VALUES ('59', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 10:38:05]登录系统', '2018-07-18 10:38:05');
INSERT INTO `tb_operate_log` VALUES ('60', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:05:28]登录系统', '2018-07-18 11:05:28');
INSERT INTO `tb_operate_log` VALUES ('61', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:08:11]更新了一位用户[张三]', '2018-07-18 11:08:11');
INSERT INTO `tb_operate_log` VALUES ('62', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:09:11]更新了一位用户[张三]', '2018-07-18 11:09:11');
INSERT INTO `tb_operate_log` VALUES ('63', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:27:15]登录系统', '2018-07-18 11:27:15');
INSERT INTO `tb_operate_log` VALUES ('64', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:28:34]登录系统', '2018-07-18 11:28:34');
INSERT INTO `tb_operate_log` VALUES ('65', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:30:09]登录系统', '2018-07-18 11:30:09');
INSERT INTO `tb_operate_log` VALUES ('66', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:44:14]登录系统', '2018-07-18 11:44:14');
INSERT INTO `tb_operate_log` VALUES ('67', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:45:31]登录系统', '2018-07-18 11:45:31');
INSERT INTO `tb_operate_log` VALUES ('68', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:50:15]登录系统', '2018-07-18 11:50:15');
INSERT INTO `tb_operate_log` VALUES ('69', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:55:24]新增了一台设备[松下 s5003]', '2018-07-18 11:55:24');
INSERT INTO `tb_operate_log` VALUES ('70', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:57:08]新增了一位用户[李四]', '2018-07-18 11:57:08');
INSERT INTO `tb_operate_log` VALUES ('71', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:57:22]更新了一位用户[李四]', '2018-07-18 11:57:22');
INSERT INTO `tb_operate_log` VALUES ('72', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 11:58:01]登录系统', '2018-07-18 11:58:01');
INSERT INTO `tb_operate_log` VALUES ('73', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 15:02:01]登录系统', '2018-07-18 15:02:01');
INSERT INTO `tb_operate_log` VALUES ('74', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 15:46:47]登录系统', '2018-07-18 15:46:47');
INSERT INTO `tb_operate_log` VALUES ('75', 'admin', '管理员', '192.168.3.37', '0', null, '管理员于[2018-07-18 15:48:50]登录系统', '2018-07-18 15:48:50');
INSERT INTO `tb_operate_log` VALUES ('76', 'admin', '管理员', '192.168.2.66', '0', null, '管理员于[2018-07-19 08:47:26]登录系统', '2018-07-19 08:47:26');
INSERT INTO `tb_operate_log` VALUES ('77', 'admin', '管理员', '192.168.2.66', '0', null, '管理员于[2018-07-19 08:48:26]更新了一位用户[李四]', '2018-07-19 08:48:26');
INSERT INTO `tb_operate_log` VALUES ('78', 'admin', '管理员', '192.168.2.66', '0', null, '管理员于[2018-07-19 08:58:03]编辑了一个角色[管理]', '2018-07-19 08:58:03');
INSERT INTO `tb_operate_log` VALUES ('79', 'admin', '管理员', '192.168.2.66', '0', null, '管理员于[2018-07-19 08:58:07]退出系统', '2018-07-19 08:58:07');
INSERT INTO `tb_operate_log` VALUES ('80', 'admin', '管理员', '192.168.2.66', '0', null, '管理员于[2018-07-19 08:58:11]登录系统', '2018-07-19 08:58:11');
INSERT INTO `tb_operate_log` VALUES ('81', 'admin', '管理员', '192.168.2.66', '0', null, '管理员于[2018-07-19 08:58:45]编辑了一个角色[管理]', '2018-07-19 08:58:45');
INSERT INTO `tb_operate_log` VALUES ('82', 'admin', '管理员', '192.168.2.66', '0', null, '管理员于[2018-07-19 08:58:59]退出系统', '2018-07-19 08:58:59');
INSERT INTO `tb_operate_log` VALUES ('83', 'admin', '管理员', '192.168.2.66', '0', null, '管理员于[2018-07-19 08:59:04]登录系统', '2018-07-19 08:59:04');
INSERT INTO `tb_operate_log` VALUES ('84', 'admin', '管理员', '192.168.100.143', '0', null, '管理员于[2018-07-23 16:11:41]登录系统', '2018-07-23 16:11:41');
INSERT INTO `tb_operate_log` VALUES ('85', 'admin', '管理员', '192.168.100.143', '0', null, '管理员于[2018-07-23 16:43:29]登录系统', '2018-07-23 16:43:29');
INSERT INTO `tb_operate_log` VALUES ('86', 'admin', '管理员', '192.168.100.143', '0', null, '管理员于[2018-07-23 17:32:49]登录系统', '2018-07-23 17:32:49');

-- ----------------------------
-- Table structure for tb_organization
-- ----------------------------
DROP TABLE IF EXISTS `tb_organization`;
CREATE TABLE `tb_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表记录唯一标识,无业务逻辑意义',
  `org_id` varchar(8) NOT NULL COMMENT '机构编号  全0表示会诊中心',
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 1有效、2无效',
  `org_address` varchar(200) DEFAULT NULL COMMENT '机构详细地址',
  `fixed_telephone` varchar(30) DEFAULT NULL COMMENT '固定电话',
  `org_phone` varchar(15) NOT NULL COMMENT '机构电话',
  `linkman` varchar(50) DEFAULT NULL COMMENT '联系人',
  `provice_code` varchar(6) DEFAULT NULL COMMENT '机构所在省',
  `city_code` varchar(6) DEFAULT NULL COMMENT '机构所在市',
  `county_code` varchar(6) DEFAULT NULL COMMENT '机构所在区（县）',
  `insert_date` date DEFAULT NULL COMMENT '登记时间',
  `update_date` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_organization
-- ----------------------------
INSERT INTO `tb_organization` VALUES ('4', '1001', '机构1', '1', '恒锋信息', '0591-7297859', '18850707911', '张三', null, null, null, '2018-07-10', '2018-07-12');
INSERT INTO `tb_organization` VALUES ('5', '1002', '机构12', '1', 'xwrj', '18850707922', '18850707933', '李四', null, null, null, '2018-07-10', '2018-07-16');

-- ----------------------------
-- Table structure for tb_organization_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_organization_user`;
CREATE TABLE `tb_organization_user` (
  `org_id` varchar(8) DEFAULT NULL COMMENT '机构编号',
  `org_user_id` varchar(14) CHARACTER SET gb2312 DEFAULT NULL COMMENT '机构人员编号:机构编号+6位流水号',
  `account_id` int(11) DEFAULT NULL COMMENT '用户ID:-1表示没有关联具体账号',
  `user_sex` int(1) NOT NULL COMMENT '用户性别:1男、2女',
  `user_birth` varchar(6) NOT NULL COMMENT '用户出生年月:前4位表示年，后2位表示月',
  `user_job` varchar(8) DEFAULT NULL COMMENT '用户职务',
  `user_title` varchar(6) DEFAULT NULL COMMENT '用户职称',
  `user_phone` varchar(32) NOT NULL COMMENT '联系电话:加密保存',
  `identity_type` varchar(5) NOT NULL COMMENT '用户证件类型',
  `identity_no` varchar(50) DEFAULT NULL COMMENT '用户证件号:身份证号，加密保存',
  `remark` varchar(6) DEFAULT NULL COMMENT '用户备注:可以记录用户（医生、专家）的专长'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_organization_user
-- ----------------------------

-- ----------------------------
-- Table structure for tb_patient_basic_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_patient_basic_info`;
CREATE TABLE `tb_patient_basic_info` (
  `patient_id` varchar(18) NOT NULL COMMENT '客户系统ID',
  `patient_type` int(1) DEFAULT '1' COMMENT '客户类型:0普通、1VIP',
  `patient_nationality` int(255) DEFAULT NULL COMMENT '客户国籍',
  `patient_identity_type` int(255) DEFAULT NULL COMMENT '客户证件类型',
  `patient_identity_no` varchar(18) NOT NULL COMMENT '客户证件号',
  `patient_name` varchar(20) NOT NULL COMMENT '客户姓名',
  `patient_sex` int(1) DEFAULT NULL COMMENT '客户性别:1男、2女',
  `patient_ethnic` int(255) DEFAULT NULL COMMENT '客户民族',
  `patient_marriage` int(1) DEFAULT NULL COMMENT '婚姻状况:1未婚、2已婚',
  `patient_birth` date NOT NULL COMMENT '客户出生年月日',
  `patient_company` varchar(30) DEFAULT NULL COMMENT '客户工作单位',
  `patient_job` varchar(4) NOT NULL COMMENT '客户职业',
  `provice_code` varchar(6) DEFAULT NULL COMMENT '客户所在省',
  `city_code` varchar(6) DEFAULT NULL COMMENT '客户所在市',
  `county_code` varchar(6) DEFAULT NULL COMMENT '客户所在区（县）',
  `patient_address` varchar(200) NOT NULL COMMENT '客户详细住址',
  `patient_email` varchar(50) DEFAULT NULL COMMENT '客户邮箱',
  `patient_code` varchar(6) DEFAULT NULL COMMENT '客户邮编',
  `patient_phone` varchar(15) NOT NULL COMMENT '客户联系电话',
  `has_guardian` int(1) DEFAULT NULL COMMENT '有无监护人:1有 2无',
  `relation_ship` int(1) DEFAULT NULL COMMENT '亲属关系:1配偶、2父母 、3子女 、4其他亲属、 5朋友、 6其他',
  `guardian_name` varchar(20) DEFAULT NULL COMMENT '监护人姓名',
  `guardian_phone` varchar(15) DEFAULT NULL COMMENT '监护人电话',
  `guardian_address` varchar(200) DEFAULT NULL COMMENT '监护人住址',
  `last_exm_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最近检查时间',
  `last_org_id` varchar(8) DEFAULT NULL COMMENT '最近检查所在机构编号',
  `patient_remark` varchar(200) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_patient_basic_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_patient_exm
-- ----------------------------
DROP TABLE IF EXISTS `tb_patient_exm`;
CREATE TABLE `tb_patient_exm` (
  `patient_id` varchar(18) NOT NULL COMMENT '客户ID',
  `exm_date` date NOT NULL COMMENT '检查日期',
  `symptoms` varchar(200) DEFAULT NULL COMMENT '主诉症状',
  `diastolic_pressure` tinyint(255) DEFAULT NULL COMMENT '舒张压',
  `systolic_pressure` tinyint(255) DEFAULT NULL COMMENT '收缩压',
  `left_sight` float(255,0) DEFAULT NULL COMMENT '左眼裸眼视力',
  `right_sight` float(255,0) DEFAULT NULL COMMENT 'right_sight',
  `fpg` float(255,0) DEFAULT NULL COMMENT '空腹毛细血管血糖',
  `pg2h` float(255,0) DEFAULT NULL COMMENT '口服75g葡萄糖2小时血糖',
  `hba1c` float(255,0) DEFAULT NULL COMMENT '糖化血红蛋白',
  `tg` float(255,0) DEFAULT NULL COMMENT '血脂',
  `tc` float(255,0) DEFAULT NULL COMMENT '总胆固醇',
  `pro` float(255,0) DEFAULT NULL COMMENT '尿蛋白',
  `malb` float(255,0) DEFAULT NULL COMMENT '尿微量白蛋白',
  `remark` varchar(400) DEFAULT NULL COMMENT '眼压、外眼和前节裂隙灯检查结果',
  PRIMARY KEY (`patient_id`,`exm_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_patient_exm
-- ----------------------------

-- ----------------------------
-- Table structure for tb_patient_health_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_patient_health_info`;
CREATE TABLE `tb_patient_health_info` (
  `patient_id` varchar(18) DEFAULT NULL COMMENT '客户系统ID',
  `general_health` int(1) DEFAULT NULL COMMENT '基本健康状况:0较差、1一般、2良好',
  `height` float DEFAULT NULL COMMENT '客户身高(以cm为单位)',
  `weight` float DEFAULT NULL COMMENT '客户体重(以kg为单位)',
  `waist` float DEFAULT NULL COMMENT '腰围(以cm为单位)',
  `has_diabetes` tinyint(1) DEFAULT NULL COMMENT '是否患有糖尿病:0无、1有',
  `heredity_dia` tinyint(1) DEFAULT NULL COMMENT '家族遗传糖尿病:0无、1有',
  `dia_time` date DEFAULT NULL COMMENT '确诊糖尿病时间:可以只精确到年，默认月、日',
  `dia_state` tinyint(1) DEFAULT NULL COMMENT '目前糖尿病控制情况:0较差、1一般、2较好',
  `has_hypertension` tinyint(1) DEFAULT NULL COMMENT '是否患有高血压:0无、1有',
  `heredity_hyp` tinyint(1) DEFAULT NULL COMMENT '家族遗传高血压:0无、1有',
  `hyp_time` date DEFAULT NULL COMMENT '确诊高血压时间:可以只精确到年，默认月、日',
  `hyp_state` tinyint(1) DEFAULT NULL COMMENT '目前高血压控制情况:0较差、1一般、2较好',
  `has_cvd` tinyint(1) DEFAULT NULL COMMENT '是否患有心血管病:0无、1有',
  `cvd_time` date DEFAULT NULL COMMENT '确诊心血管病时间:可以只精确到年，默认月、日',
  `drug_state` tinyint(1) DEFAULT NULL COMMENT '曾用药情况:0未用药、1使用控糖药、2使用控压药、3使用心血管病相关药、4使用以上多种',
  `has_drug_allergy` tinyint(1) DEFAULT NULL COMMENT '是否药物过敏::0无、1有',
  `drug_allergy` varchar(100) DEFAULT NULL COMMENT '过敏药物名称',
  `smok` tinyint(1) DEFAULT NULL COMMENT '吸烟情况:0:从不吸烟、1:5年以下、2:5—10年、3:10年以上',
  `drink` tinyint(1) DEFAULT NULL COMMENT '是否经常饮酒:0从不饮酒、1偶尔饮酒、2经常饮酒',
  `sport` tinyint(1) DEFAULT NULL COMMENT '运动习惯:0基本不运动、1偶尔运动、2每月1-2次、3一周1-2次、4每天运动',
  `food` tinyint(1) DEFAULT NULL COMMENT '蔬菜水果摄入:0极少、1较少、2一般、3经常',
  `remark` varchar(120) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_patient_health_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_patient_login_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_patient_login_info`;
CREATE TABLE `tb_patient_login_info` (
  `patient_id` varchar(18) DEFAULT NULL COMMENT '客户系统ID',
  `login_name` varchar(20) DEFAULT NULL COMMENT '客户登录名',
  `login_pwd` varchar(30) DEFAULT NULL COMMENT '客户登录密码:加密',
  `phone` varchar(15) NOT NULL COMMENT '客户联系电话',
  `last_login_time` date DEFAULT NULL COMMENT '最近登录时间',
  `login_ip` varchar(19) DEFAULT NULL COMMENT '最近登录IP'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_patient_login_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_picture_feature
-- ----------------------------
DROP TABLE IF EXISTS `tb_picture_feature`;
CREATE TABLE `tb_picture_feature` (
  `pic_no` varchar(21) NOT NULL COMMENT '图片no，是图片id的简化:检查时间（8位）+设备ID（5）+检查登记号(4)+流水号(3)+左右眼(1位，0表示左，1表示右)',
  `pic_id` varchar(60) DEFAULT NULL COMMENT '图片id',
  `odx` tinyint(255) DEFAULT NULL COMMENT '视盘坐标x',
  `ody` tinyint(255) DEFAULT NULL COMMENT '视盘坐标y',
  `dd` tinyint(255) DEFAULT NULL COMMENT '视盘直径：以像素点个数计算',
  `macular_x` tinyint(255) DEFAULT NULL COMMENT '黄斑中心凹坐标x',
  `macular_y` tinyint(255) DEFAULT NULL COMMENT '黄斑中心凹坐标y',
  `md` tinyint(255) DEFAULT NULL COMMENT '黄斑视盘中心距离：以像素点个数计算',
  `hes_flag` int(1) DEFAULT NULL COMMENT '有无硬性渗出：0没有、1有',
  `sight_threaten` int(1) DEFAULT NULL COMMENT '是否威胁视力：0远离黄斑区域、1逼近黄斑区域、2影响视力（黄斑区域内）',
  `macular_edema_flag` int(1) DEFAULT NULL COMMENT '有无黄斑水肿：0没有、1有',
  `hemorrhage_qty` int(11) DEFAULT '0' COMMENT '出血点个数',
  `mas_qty` int(11) DEFAULT '0' COMMENT '微动脉瘤个数',
  `glaucoma_flag` int(1) DEFAULT NULL COMMENT '是否青光眼：0不是、1是',
  `cataract_flag` int(1) DEFAULT NULL COMMENT '是否白内障：0不是、1是',
  `silver_artery_flag` int(1) DEFAULT NULL COMMENT '是否银丝动脉：0不是、1是',
  `copper_artery_flag` int(1) DEFAULT NULL COMMENT '是否铜丝动脉：0不是、1是',
  `dran_flag` int(1) DEFAULT NULL COMMENT '弥漫性视网膜动脉缩窄：0不是、1是',
  `lran_flag` int(1) DEFAULT NULL COMMENT '局限性视网膜动脉缩窄：0不是、1是',
  `venous_dilatation_flag` int(1) DEFAULT NULL COMMENT '是否有静脉扩张：0不是、1是',
  `ses_flag` int(1) DEFAULT NULL COMMENT '是否有软性渗出：0不是、1是',
  `care` float(255,0) DEFAULT NULL COMMENT '视网膜中央等效动脉直径（CRAE）：以mm为单位',
  `cave` float(255,0) DEFAULT NULL COMMENT '视网膜中央等效静脉直径（CRVE）：以mm为单位',
  `avr` float(255,0) DEFAULT NULL COMMENT '动静脉比值（AVR）：以mm为单位',
  `rfnld` tinyint(1) DEFAULT NULL COMMENT '神经纤维层缺损情况：0无、1楔形缺损、2弥漫性缺损',
  `disc_area` tinyint(255) DEFAULT NULL COMMENT '视盘面积:用像素点个数描述',
  `cup_area` tinyint(255) DEFAULT NULL COMMENT '视杯面积：用像素点个数描述',
  `cdr` float(255,0) DEFAULT NULL COMMENT '杯盘比',
  PRIMARY KEY (`pic_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_picture_feature
-- ----------------------------

-- ----------------------------
-- Table structure for tb_picture_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_picture_info`;
CREATE TABLE `tb_picture_info` (
  `pic_no` varchar(21) NOT NULL COMMENT '图片no，是图片id的简化:检查时间（8位）+设备ID（5）+检查登记号(4)+流水号(3)+左右眼(1位，0表示左，1表示右)',
  `trml_id` varchar(8) DEFAULT NULL COMMENT '设备编号',
  `pic_id` varchar(60) DEFAULT NULL COMMENT '图片id',
  `swift_no` varchar(3) NOT NULL COMMENT '流水号',
  `org_user_id` varchar(14) DEFAULT NULL COMMENT '机构人员编号:机构编号+6位流水号',
  `exam_time` date DEFAULT NULL COMMENT '检查时间',
  `pic_qty` int(2) NOT NULL COMMENT '拍照数量',
  `l_or_r` int(1) NOT NULL COMMENT '左右眼标识:0左眼、1右眼',
  `fixation_point` int(255) DEFAULT NULL COMMENT '固始点位置',
  `upload_flag` int(255) DEFAULT NULL COMMENT '上传标识:0没有、1已上传',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  `receive_status` int(1) DEFAULT NULL COMMENT '确认接收状态:0未确认、1已确认合格,接受、2已确认不合格，需要重传',
  `receive_time` datetime DEFAULT NULL COMMENT '接收时间',
  `interpreta_flag` int(1) DEFAULT NULL COMMENT '判读标识:0没有、1已判读',
  `interpreta_time` datetime DEFAULT NULL COMMENT '判读时间',
  `pic_desc` varchar(100) DEFAULT NULL COMMENT '图片备注',
  `patient_id` varchar(18) DEFAULT NULL COMMENT '客户系统ID',
  `pic_path` varchar(100) DEFAULT NULL COMMENT '原始图片存储相对路径',
  `auto_pic_path` varchar(100) DEFAULT NULL COMMENT '肽积木处理后返回的图像地址',
  `auto_mod_pic_path` varchar(100) DEFAULT NULL COMMENT '自动修正后的图像地址',
  `manuel_pic_path` varchar(100) DEFAULT NULL COMMENT '人工修正后的图像地址',
  `final_pic_path` varchar(100) DEFAULT NULL COMMENT '人工确认后的最终标记图像地址',
  `dr` tinyint(1) DEFAULT NULL COMMENT 'DR分级:0:无、1:1级、2:2级、3:3级',
  `dme` tinyint(1) DEFAULT NULL COMMENT 'DME分级:0无、1轻度、2中度、3重度',
  `vessel_change_flag` int(1) DEFAULT NULL COMMENT '有无血管改变:0无、1有',
  `new_vessel_flag` int(1) DEFAULT NULL COMMENT '有无新生血管:0无、1有',
  `analysis_status` int(1) DEFAULT NULL COMMENT '图像分析的状态:0（初始值）成功接收质量合格图像，未进行处理、1图像已通过胎积木api自动处理完成、2图像已完成自动修正、3图像已完成人工修正、4图像已完成人工确认',
  PRIMARY KEY (`pic_no`,`swift_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_picture_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_pic_server
-- ----------------------------
DROP TABLE IF EXISTS `tb_pic_server`;
CREATE TABLE `tb_pic_server` (
  `s_id` varchar(4) CHARACTER SET gb2312 NOT NULL COMMENT '服务器编号',
  `s_name` varchar(15) DEFAULT NULL COMMENT '服务器名称',
  `s_ip` varchar(19) DEFAULT NULL COMMENT '服务器IP地址',
  `s_dmame` varchar(20) DEFAULT NULL COMMENT '服务器域名',
  `s_port` int(255) DEFAULT NULL COMMENT '服务器端口号',
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_pic_server
-- ----------------------------

-- ----------------------------
-- Table structure for tb_privilege
-- ----------------------------
DROP TABLE IF EXISTS `tb_privilege`;
CREATE TABLE `tb_privilege` (
  `id` int(11) NOT NULL COMMENT '主键id',
  `type` int(1) DEFAULT NULL COMMENT '权限的类型:1URI 、2MENU 、3BUTTON 、4SQL',
  `parent_id` int(11) DEFAULT NULL COMMENT '父资源',
  `rank` int(11) DEFAULT NULL COMMENT '等级',
  `method` varchar(32) DEFAULT NULL COMMENT '方法',
  `page_url` varchar(64) DEFAULT NULL COMMENT '页面路径',
  `class_name` varchar(64) CHARACTER SET gb2312 DEFAULT NULL COMMENT '类名称',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `uri` varchar(64) DEFAULT NULL COMMENT '标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for tb_report
-- ----------------------------
DROP TABLE IF EXISTS `tb_report`;
CREATE TABLE `tb_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `leftsignpic` varchar(255) DEFAULT NULL COMMENT '左眼带标记图片',
  `rightsignpic` varchar(255) DEFAULT NULL COMMENT '右眼带标记图片',
  `initstatus` int(2) DEFAULT NULL COMMENT '机器诊断状态',
  `status` int(2) DEFAULT NULL COMMENT '诊断状态',
  `normal` int(2) DEFAULT NULL COMMENT '筛查结果',
  `leftfeature` varchar(150) DEFAULT NULL COMMENT '左眼特征',
  `rightfeature` varchar(150) DEFAULT NULL COMMENT '右眼特征',
  `leftresultopt` varchar(150) DEFAULT NULL COMMENT '左眼诊断结果（选项）',
  `leftresult` varchar(255) DEFAULT NULL COMMENT '左眼诊断结果（其他）',
  `rightresultopt` varchar(150) DEFAULT NULL COMMENT '右眼诊断结果（选项）',
  `rightresult` varchar(255) DEFAULT NULL COMMENT '右眼诊断结果（其他）',
  `doctorresultopt` varchar(150) DEFAULT NULL COMMENT '医生诊断结果（选项）',
  `doctorresult` varchar(255) DEFAULT NULL COMMENT '医生诊断结果（其他）',
  `doctorId` int(11) DEFAULT NULL COMMENT '医生id',
  `createtime` datetime DEFAULT NULL COMMENT '生成时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `healthinfoid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_report
-- ----------------------------
INSERT INTO `tb_report` VALUES ('1', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, '2018-05-23 16:15:39', '2018-07-23 18:21:00', '1');
INSERT INTO `tb_report` VALUES ('2', '/static/upload_image/1527821345187_focus_result.png', '/static/upload_image/1527821352298_focus_result.png', null, '1', null, null, null, null, '[\"图片可用性：可用\\n\\n眼底彩照: 未见微血管瘤；未见硬性渗出；未见眼底出血；\\n印象: 该区域眼底整体印象健康，未见异常\",\"\\n建议: 建议每年进行一次眼底复查\"]', null, '[\"图片可用性：可用\\n眼底彩照:可见多量硬性渗出；视网膜多量微血管瘤可见； 多量眼底出血可见；\\n印象: 该区域眼底疑似糖尿病性视网膜增殖性病变\",\"请确认\",\"\\n建议: 您的眼底可能出现增殖性病变，如您确认无糖尿病史，可能存在其他原因\",\"建议尽快进行全面眼底散瞳检查\"]', null, null, null, '2018-06-01 10:49:07', '2018-06-01 11:24:21', '2');
INSERT INTO `tb_report` VALUES ('3', '/static/upload_image/1527823453853_focus_result.png', '/static/upload_image/1527823469287_focus_result.png', null, '1', null, null, null, null, '[\"图片可用性：可用\\n\\n眼底彩照: 未见微血管瘤；未见硬性渗出；未见眼底出血；\\n印象: 该区域眼底整体印象健康，未见异常\",\"\\n建议: 建议每年进行一次眼底复查\"]', null, '[\"图片可用性：可用\\n眼底彩照:可见多量硬性渗出；视网膜多量微血管瘤可见； 多量眼底出血可见；\\n印象: 该区域眼底疑似糖尿病性视网膜增殖性病变\",\"请确认\",\"\\n建议: 您的眼底可能出现增殖性病变，如您确认无糖尿病史，可能存在其他原因\",\"建议尽快进行全面眼底散瞳检查\"]', null, null, null, '2018-06-01 11:24:14', '2018-06-01 11:33:13', '3');
INSERT INTO `tb_report` VALUES ('4', '/static/upload_image/1527839055853_focus_result.png', '/static/upload_image/1527839060692_focus_result.png', null, '2', null, 'f02', 'f01', null, '图片可用性：可用；眼底彩照:可见多量硬性渗出；视网膜多量微血管瘤可见； 多量眼底出血可见；印象: 该区域眼底疑似糖尿病性视网膜增殖性病变,请确认；建议: 您的眼底可能出现增殖性病变，如您确认无糖尿病史，可能存在其他原因,建议尽快进行全面眼底散瞳检查', null, '图片可用性：可用；眼底彩照:可见多量硬性渗出；视网膜多量微血管瘤可见； 多量眼底出血可见；印象: 该区域眼底疑似糖尿病性视网膜增殖性病变,请确认；建议: 您的眼底可能出现增殖性病变，如您确认无糖尿病史，可能存在其他原因,建议尽快进行全面眼底散瞳检查', null, 'aaa', null, '2018-06-01 15:44:12', '2018-06-01 16:08:11', '4');
INSERT INTO `tb_report` VALUES ('5', '/static/upload_image/1528104581313_focus_result.png', '/static/upload_image/1528104586059_focus_result.png', null, '1', null, null, null, null, '[\"图片可用性：可用\\n\\n眼底彩照: 未见微血管瘤；未见硬性渗出；未见眼底出血；\\n印象: 该区域眼底整体印象健康，未见异常\",\"\\n建议: 建议每年进行一次眼底复查\"]', null, '[\"图片可用性：可用\\n眼底彩照:可见多量硬性渗出；视网膜多量微血管瘤可见； 多量眼底出血可见；\\n印象: 该区域眼底疑似糖尿病性视网膜增殖性病变\",\"请确认\",\"\\n建议: 您的眼底可能出现增殖性病变，如您确认无糖尿病史，可能存在其他原因\",\"建议尽快进行全面眼底散瞳检查\"]', null, null, null, '2018-06-04 17:29:45', '2018-06-04 17:39:10', '5');
INSERT INTO `tb_report` VALUES ('6', '/static/upload_image/1528270303559_focus_result.png', '/static/upload_image/1528270309725_focus_result.png', null, '1', null, null, null, null, '[\"图片可用性：可用\\n\\n眼底彩照: 未见微血管瘤；未见硬性渗出；未见眼底出血；\\n印象: 该区域眼底整体印象健康，未见异常\",\"\\n建议: 建议每年进行一次眼底复查\"]', null, '[\"图片可用性：可用\\n眼底彩照:可见多量硬性渗出；视网膜多量微血管瘤可见； 多量眼底出血可见；\\n印象: 该区域眼底疑似糖尿病性视网膜增殖性病变\",\"请确认\",\"\\n建议: 您的眼底可能出现增殖性病变，如您确认无糖尿病史，可能存在其他原因\",\"建议尽快进行全面眼底散瞳检查\"]', null, null, null, '2018-06-06 15:31:43', '2018-06-06 15:39:13', '6');
INSERT INTO `tb_report` VALUES ('7', '/static/upload_image/1528272762528_focus_result.png', null, null, '0', null, null, null, null, '[\"图片可用性：可用\\n\\n眼底彩照: 未见微血管瘤；未见硬性渗出；未见眼底出血；\\n印象: 该区域眼底整体印象健康，未见异常\",\"\\n建议: 建议每年进行一次眼底复查\"]', null, null, null, null, null, '2018-06-06 16:12:47', '2018-07-23 18:21:00', '7');
INSERT INTO `tb_report` VALUES ('8', null, null, null, '0', null, null, null, null, '', null, '', null, '', null, '2018-06-06 17:29:30', '2018-07-23 18:21:00', '8');
INSERT INTO `tb_report` VALUES ('9', '/static/upload_image/1528279425105_focus_result.png', '/static/upload_image/1528279427386_focus_result.png', null, '2', null, 'f01', null, null, '图片可用性：可用；眼底彩照:可见多量硬性渗出；视网膜多量微血管瘤可见； 多量眼底出血可见；印象: 该区域眼底疑似糖尿病性视网膜增殖性病变,请确认；建议: 您的眼底可能出现增殖性病变，如您确认无糖尿病史，可能存在其他原因,建议尽快进行全面眼底散瞳检查', null, '图片可用性：可用；眼底彩照: 未见微血管瘤；未见硬性渗出；未见眼底出血；印象: 该区域眼底整体印象健康，未见异常；建议: 建议每年进行一次眼底复查', null, '', null, '2018-06-06 18:03:47', '2018-06-07 08:46:07', '9');
INSERT INTO `tb_report` VALUES ('10', '/static/upload_image/1528342469863_focus_result.png', '/static/upload_image/1528342474490_focus_result.png', null, '1', null, null, null, null, '[\"图片可用性：可用\\n眼底彩照:可见多量硬性渗出；视网膜多量微血管瘤可见； 多量眼底出血可见；\\n印象: 该区域眼底疑似糖尿病性视网膜增殖性病变\",\"请确认\",\"\\n建议: 您的眼底可能出现增殖性病变，如您确认无糖尿病史，可能存在其他原因\",\"建议尽快进行全面眼底散瞳检查\"]', null, '[\"图片可用性：可用\\n眼底彩照:可见多量硬性渗出；视网膜多量微血管瘤可见； 多量眼底出血可见；\\n印象: 该区域眼底疑似糖尿病性视网膜增殖性病变\",\"请确认\",\"\\n建议: 您的眼底可能出现增殖性病变，如您确认无糖尿病史，可能存在其他原因\",\"建议尽快进行全面眼底散瞳检查\"]', null, null, null, '2018-06-07 11:34:28', '2018-06-07 12:09:07', '10');
INSERT INTO `tb_report` VALUES ('11', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, '2018-06-12 15:43:20', '2018-07-23 18:21:00', '11');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '角色名',
  `role_code` varchar(255) DEFAULT NULL COMMENT '角色编号',
  `menuIds` text COMMENT '权限ID',
  `status` int(2) DEFAULT '0' COMMENT '状态：0-启用；1-停用',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='后台角色权限表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '管理', '', '1,2,3,4,5,9,10,11', '0', '', '2018-07-19 08:58:45', '2018-07-19 08:58:45');
INSERT INTO `tb_role` VALUES ('2', '普通', null, '7,8', '0', null, '2018-02-04 20:49:57', '2018-02-04 20:49:57');
INSERT INTO `tb_role` VALUES ('4', '基层', null, '7,8', '0', null, '2018-05-24 10:13:05', '2018-05-24 10:13:05');
INSERT INTO `tb_role` VALUES ('5', '远程会诊中心', null, '7,8', '0', null, '2018-05-24 10:13:18', '2018-05-24 10:13:18');

-- ----------------------------
-- Table structure for tb_role_new
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_new`;
CREATE TABLE `tb_role_new` (
  `role_id` int(11) NOT NULL COMMENT '主键id',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父id:父亲，子对象继承父对象的权限',
  `description` varchar(64) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_new
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_privilege`;
CREATE TABLE `tb_role_privilege` (
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `privilege_id` int(11) DEFAULT NULL COMMENT '资源ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for tb_terminal_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_terminal_info`;
CREATE TABLE `tb_terminal_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表记录唯一标识,无业务逻辑意义',
  `trml_id` varchar(8) NOT NULL COMMENT '终端编号',
  `org_id` varchar(8) NOT NULL COMMENT '所属机构编号',
  `camera_brand` varchar(10) NOT NULL COMMENT '相机品牌',
  `camera_model` varchar(10) NOT NULL COMMENT '相机型号',
  `camera_producer` varchar(5) DEFAULT NULL COMMENT '生产地',
  `camera_produce_time` date DEFAULT NULL COMMENT '生产时间',
  `camera_buy_time` date DEFAULT NULL COMMENT '购置时间',
  `central_resolution` int(255) NOT NULL COMMENT '中心分辨率',
  `camera_angle` tinyint(255) DEFAULT NULL COMMENT '视角',
  `ccd_resolution` int(255) NOT NULL COMMENT 'CCD分辨率',
  `trml_ip` varchar(19) DEFAULT NULL COMMENT '终端IP',
  `trml_port` int(255) DEFAULT NULL COMMENT '终端端口',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_terminal_info
-- ----------------------------
INSERT INTO `tb_terminal_info` VALUES ('2', '2002', '1001', '海康', 'h200', '仙游', '2018-07-03', '2018-07-06', '5000', '45', '3000', '192.168.0.2', '8002');
INSERT INTO `tb_terminal_info` VALUES ('3', '2003', '1002', '佳能', 'c300', '福州', '2018-07-10', '2018-07-12', '2000', '10', '3000', '192.168.0.3', '2018');
INSERT INTO `tb_terminal_info` VALUES ('4', '3000', '1001', '松下', 's5003', '莆田', '2018-07-10', '2018-07-12', '2000', '23', '3000', '192.168.0.4', '2001');

-- ----------------------------
-- Table structure for tb_terminal_user_rel
-- ----------------------------
DROP TABLE IF EXISTS `tb_terminal_user_rel`;
CREATE TABLE `tb_terminal_user_rel` (
  `trml_id` varchar(8) DEFAULT NULL COMMENT '终端编号',
  `org_user_id` varchar(14) DEFAULT NULL COMMENT '机构人员编号:机构编号+6位流水号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_terminal_user_rel
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `loginName` varchar(32) DEFAULT NULL COMMENT '帐号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `realName` varchar(32) DEFAULT NULL COMMENT '正式名称',
  `position` varchar(32) DEFAULT NULL COMMENT '职位',
  `roleIds` text COMMENT '角色ID',
  `status` int(2) DEFAULT '0' COMMENT '状态：0-启用；1-停用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '用户密码修改时间',
  `islogin` int(1) unsigned zerofill DEFAULT '0' COMMENT '登陆状态（0：不在线 1：在线）',
  `last_login_time` datetime DEFAULT NULL COMMENT '上一次登录时间',
  `last_login_ip` varchar(32) DEFAULT NULL COMMENT '上一次登录IP',
  `login_qty` int(5) DEFAULT '0' COMMENT '登录次数',
  `enable` int(1) DEFAULT '1' COMMENT '状态，0：冻结、1：正常，默认为正常状态',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `org_id` varchar(8) DEFAULT NULL COMMENT '机构',
  `terminalIds` text COMMENT '终端id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='后台用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'yisheng01', '224036cb2907e4eb840f307c11360580', '医生01', 'p01', '2', '1', '2018-07-16 18:28:03', '2018-07-16 18:28:03', '0', null, null, '0', null, '18850707911', '659975208@qq.com', '1001', null);
INSERT INTO `tb_user` VALUES ('2', 'hushi01', '224036cb2907e4eb840f307c11360580', '护士01', 'p02', '2', '0', '2018-03-21 08:51:05', '2018-03-21 08:51:05', '0', null, null, '0', null, '18850707912', '659975209@qq.com', '1001', null);
INSERT INTO `tb_user` VALUES ('3', 'admin', '224036cb2907e4eb840f307c11360580', '管理员', 'p01', '1', '0', '2018-07-16 17:15:28', '2018-07-16 17:15:28', '1', '2018-07-23 17:32:49', '192.168.100.143', '52', null, '18850707913', '659975210@qq.com', '1002', null);
INSERT INTO `tb_user` VALUES ('4', 'jc01', '224036cb2907e4eb840f307c11360580', '基层01', 'p05', '4', '0', '2018-07-16 17:15:35', '2018-07-16 17:15:35', '0', null, null, '0', null, '18850707914', '659975211@qq.com', '1001', null);
INSERT INTO `tb_user` VALUES ('5', 'ychz01', '224036cb2907e4eb840f307c11360580', '远程会诊01', 'p06', '5', '0', '2018-07-16 17:15:50', '2018-07-16 17:15:50', '0', null, null, '0', null, '18850707915', '659975212@qq.com', '1001', null);
INSERT INTO `tb_user` VALUES ('15', 'zhangsan', '224036cb2907e4eb840f307c11360580', '张三', null, '1', '0', '2018-07-18 11:09:11', '2018-07-18 11:09:11', null, null, null, null, null, '18850707999', '', '1001', '2');
INSERT INTO `tb_user` VALUES ('16', 'lisi', '224036cb2907e4eb840f307c11360580', '李四', null, '1', '0', '2018-07-19 08:48:26', '2018-07-19 08:48:26', null, null, null, null, null, '18850707922', '659975219@qq.com', '1001', '4');
