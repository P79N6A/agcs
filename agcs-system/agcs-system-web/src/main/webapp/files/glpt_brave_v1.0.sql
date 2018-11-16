/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50546
Source Host           : 127.0.0.1:3306
Source Database       : glpt_brave_v1.0

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2016-02-02 10:27:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `glpt_action_log`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_action_log`;
CREATE TABLE `glpt_action_log` (
  `ID` varchar(30) NOT NULL COMMENT '日志id',
  `CLASSNAME` varchar(100) DEFAULT NULL COMMENT '操作类名',
  `TABLENAME` varchar(50) DEFAULT NULL COMMENT '表名',
  `INPUTPAGRAM` text COMMENT '输入参数',
  `OUTRESULT` text COMMENT '输出结果',
  `METHOD` varchar(100) DEFAULT NULL COMMENT '操作方法名',
  `URL` varchar(500) DEFAULT NULL COMMENT '请求地址',
  `KSSJ` datetime DEFAULT NULL COMMENT '开始时间',
  `JSSJ` datetime DEFAULT NULL COMMENT '结束时间',
  `OPNAME` varchar(500) DEFAULT NULL COMMENT '操作事项',
  `STATUS` varchar(10) DEFAULT NULL COMMENT '状态',
  `CZR` varchar(50) DEFAULT NULL COMMENT '操作人',
  `CZRXM` varchar(100) DEFAULT NULL COMMENT '操作人姓名',
  `CZRBM` varchar(50) DEFAULT NULL COMMENT '操作人部门',
  `CZSJ` datetime DEFAULT NULL COMMENT '操作时间',
  `CZNR` varchar(500) DEFAULT NULL COMMENT '操作内容',
  `CZIP` varchar(50) DEFAULT NULL COMMENT '操作IP',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统操作日志表';

-- ----------------------------
-- Records of glpt_action_log
-- ----------------------------

-- ----------------------------
-- Table structure for `glpt_dept`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_dept`;
CREATE TABLE `glpt_dept` (
  `ID` varchar(35) NOT NULL COMMENT '序列，自动增长',
  `DEPT_CODE` varchar(50) NOT NULL COMMENT '部门代号',
  `DEPT_NAME` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `SUP_DEPT_CODE` varchar(50) DEFAULT NULL COMMENT '上级部门',
  `STATUS` varchar(5) DEFAULT NULL COMMENT '状态',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '描述',
  `LRR` varchar(30) DEFAULT NULL COMMENT '操作人',
  `LRRXM` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `LRRBM` varchar(30) DEFAULT NULL COMMENT '操作人部门',
  `LRSJ` datetime DEFAULT NULL COMMENT '操作时间',
  `LRIP` varchar(30) DEFAULT NULL COMMENT '操作IP',
  `LRMAC` varchar(30) DEFAULT NULL COMMENT '操作MAC',
  `LAST_UPDATE` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`DEPT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门信息';

-- ----------------------------
-- Records of glpt_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `glpt_node`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_node`;
CREATE TABLE `glpt_node` (
  `ID` varchar(35) NOT NULL COMMENT 'id,自增序列',
  `NODE_CODE` varchar(30) NOT NULL COMMENT '节点代号',
  `NODE_NAME` varchar(100) DEFAULT NULL COMMENT '节点名称',
  `NODE_URL` varchar(200) DEFAULT NULL COMMENT '节点url',
  `NODE_ICON` varchar(50) DEFAULT NULL COMMENT '节点图标',
  `SUP_NODE_CODE` varchar(30) DEFAULT NULL COMMENT '上级节点',
  `STATUS` varchar(10) DEFAULT NULL COMMENT '状态',
  `TYPE` varchar(10) DEFAULT NULL COMMENT '节点类型',
  `SYSTEM` varchar(10) DEFAULT NULL COMMENT '所属系统',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `REMARK` varchar(500) DEFAULT NULL COMMENT '描述',
  `LRR` varchar(30) DEFAULT NULL COMMENT '操作人',
  `LRRXM` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `LRRBM` varchar(30) DEFAULT NULL COMMENT '操作人部门',
  `LRSJ` datetime DEFAULT NULL COMMENT '操作时间',
  `LRIP` varchar(30) DEFAULT NULL COMMENT '操作IP',
  `LRMAC` varchar(30) DEFAULT NULL COMMENT '操作MAC',
  `LAST_UPDATE` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`NODE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单节点信息表';

-- ----------------------------
-- Records of glpt_node
-- ----------------------------
INSERT INTO `glpt_node` VALUES ('1', 'glpt', '系统管理', null, 'icon-sys', '0', 'T', 'node', 'GLPT', '1', null, null, null, null, null, null, null, null);
INSERT INTO `glpt_node` VALUES ('2', 'usermanager', '用户管理', 'glptUserController.do?test', 'icon-sys', 'glpt', 'T', 'node', 'GLPT', '2', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `glpt_resource_info`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_resource_info`;
CREATE TABLE `glpt_resource_info` (
  `ID` varchar(35) NOT NULL COMMENT '资源ID',
  `ZY_NAME` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `ZY_TABNAME` varchar(50) DEFAULT NULL COMMENT '资源表名',
  `ZY_ZJ` varchar(30) DEFAULT NULL COMMENT '主键',
  `ZY_CXTJ_OUT` varchar(1000) DEFAULT NULL COMMENT '查询条件（对外），多个条件用逗号，分割',
  `ZY_CXTJ_IN` varchar(1000) DEFAULT NULL COMMENT '查询条件（对内），多个条件用逗号，号分割',
  `ZY_TYPE` varchar(10) DEFAULT NULL COMMENT '资源类型，1普通业务',
  `ZY_STATE` varchar(10) DEFAULT NULL COMMENT '资源状态 T启用，F停用',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `ZY_REMARK` varchar(500) DEFAULT NULL COMMENT '描述',
  `LRR` varchar(30) DEFAULT NULL COMMENT '录入人',
  `LRRXM` varchar(50) DEFAULT NULL COMMENT '录入人姓名',
  `LRRBM` varchar(30) DEFAULT NULL COMMENT '录入人部门',
  `LRSJ` datetime DEFAULT NULL COMMENT '录入时间',
  `LRIP` varchar(30) DEFAULT NULL COMMENT '录入IP',
  `LRRMAC` varchar(30) DEFAULT NULL COMMENT '录入MAC',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源信息表';

-- ----------------------------
-- Records of glpt_resource_info
-- ----------------------------

-- ----------------------------
-- Table structure for `glpt_resource_mx`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_resource_mx`;
CREATE TABLE `glpt_resource_mx` (
  `ID` varchar(35) NOT NULL COMMENT '明细id',
  `MX_NAME` varchar(50) DEFAULT NULL COMMENT '明细名称（字段名称）',
  `MX_MS` varchar(50) DEFAULT NULL COMMENT '明细中文描述',
  `ZY_ID` int(11) DEFAULT NULL COMMENT '资源id',
  `DATE_TYPE` varchar(20) DEFAULT NULL COMMENT '数据类型',
  `STATUS` varchar(5) DEFAULT NULL COMMENT '状态 T启用，F停用',
  `IS_PIC` varchar(5) DEFAULT NULL COMMENT '是否是图片',
  `IS_SHOW` varchar(5) DEFAULT NULL COMMENT '是否显示在列表上 0-不显示，不导出  1-只在页面展示  2展示和导出',
  `IS_JM` varchar(5) DEFAULT NULL COMMENT '是否需要解密',
  `ZD_DX` varchar(30) DEFAULT NULL COMMENT '字典对象名,字典表名',
  `ZD_TEXT` varchar(30) DEFAULT NULL COMMENT '界面显示的中文信息',
  `ZD_VALUE` varchar(30) DEFAULT NULL COMMENT '翻译的字典信息',
  `ZD_KEY_ADD` varchar(300) DEFAULT NULL COMMENT '翻译附加条件',
  `SOURCE_VALUE` varchar(30) DEFAULT NULL COMMENT '明细字段',
  `ZD_ORDER` int(11) DEFAULT NULL COMMENT '资源显示顺序',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源明细表';

-- ----------------------------
-- Records of glpt_resource_mx
-- ----------------------------

-- ----------------------------
-- Table structure for `glpt_resource_relation`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_resource_relation`;
CREATE TABLE `glpt_resource_relation` (
  `ID` varchar(35) NOT NULL COMMENT 'ID，自增',
  `MAIN_SOURCE` varchar(50) DEFAULT NULL COMMENT '主资源名称（表名）',
  `MAIN_SOURCE_COL` varchar(30) DEFAULT NULL COMMENT '主资源字段名',
  `MAIN_ID` int(11) DEFAULT NULL COMMENT '主资源ID',
  `SUB_SOURCE` varchar(50) DEFAULT NULL COMMENT '子资源名称（表名）',
  `SUB_SOURCE_COL` varchar(30) DEFAULT NULL COMMENT '子资源字段名',
  `SUB_ID` int(11) DEFAULT NULL COMMENT '子资源ID',
  `RELATION` varchar(50) DEFAULT NULL COMMENT '关系',
  `ADDKEY` varchar(200) DEFAULT NULL COMMENT '附加条件',
  `STATUS` varchar(5) DEFAULT NULL COMMENT '状态',
  `TYPE` varchar(10) DEFAULT NULL COMMENT '类型',
  `ADDTIME` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源关系信息';

-- ----------------------------
-- Records of glpt_resource_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `glpt_resource_where`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_resource_where`;
CREATE TABLE `glpt_resource_where` (
  `ID` varchar(35) NOT NULL COMMENT 'ID',
  `ZY_ID` int(11) DEFAULT NULL COMMENT '资源ID',
  `QUERY_ZD` varchar(30) DEFAULT NULL COMMENT '查询字段名',
  `QUERY_ZDNAME` varchar(50) DEFAULT NULL COMMENT '查询名称（条件展示名称）',
  `ZD_DATATYPE` varchar(30) DEFAULT NULL COMMENT '字段数据类型',
  `LABEL_TYPE` varchar(30) DEFAULT NULL COMMENT '界面标签名',
  `LABEL_SQL` varchar(300) DEFAULT NULL COMMENT '标签数据sql',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `STATUS` varchar(5) DEFAULT NULL COMMENT '状态',
  `QUERY_METHOD` varchar(30) DEFAULT NULL COMMENT '查询方式（如：''='', ''like''）',
  `ADDTIME` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源查询条件';

-- ----------------------------
-- Records of glpt_resource_where
-- ----------------------------

-- ----------------------------
-- Table structure for `glpt_role`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_role`;
CREATE TABLE `glpt_role` (
  `ID` varchar(35) NOT NULL COMMENT 'ID',
  `ROLE_CODE` varchar(30) NOT NULL COMMENT '角色代号',
  `ROLE_NAME` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `STATE` varchar(5) DEFAULT NULL COMMENT '状态(有效1、无效0)',
  `REMAEK` varchar(300) DEFAULT NULL COMMENT '描述',
  `LRR` varchar(30) DEFAULT NULL COMMENT '操作人',
  `LRRXM` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `LRSJ` datetime DEFAULT NULL COMMENT '操作时间',
  `LRRBM` varchar(30) DEFAULT NULL COMMENT '操作人部门',
  `LRIP` varchar(30) DEFAULT NULL COMMENT '操作IP',
  `LRMAC` varchar(30) DEFAULT NULL COMMENT '操作MAC',
  `LAST_UPDATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息';

-- ----------------------------
-- Records of glpt_role
-- ----------------------------
INSERT INTO `glpt_role` VALUES ('1', 'admin', '超级管理员', 'T', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `glpt_role_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_role_privilege`;
CREATE TABLE `glpt_role_privilege` (
  `ID` varchar(35) NOT NULL COMMENT 'ID',
  `ROLE_CODE` varchar(30) DEFAULT NULL COMMENT '角色代号',
  `NODE_CODE` varchar(30) DEFAULT NULL COMMENT '节点代号',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '描述',
  `LRR` varchar(30) DEFAULT NULL COMMENT '录入人',
  `LRRXM` varchar(50) DEFAULT NULL COMMENT '录入人姓名',
  `LRSJ` datetime DEFAULT NULL COMMENT '录入时间',
  `LRRBM` varchar(30) DEFAULT NULL COMMENT '录入人部门',
  `LRIP` varchar(30) DEFAULT NULL COMMENT '录入ip',
  `LRMAC` varchar(30) DEFAULT NULL COMMENT '录入mac',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of glpt_role_privilege
-- ----------------------------
INSERT INTO `glpt_role_privilege` VALUES ('1', 'admin', 'glpt', null, null, null, null, null, null, null);
INSERT INTO `glpt_role_privilege` VALUES ('2', 'admin', 'usermanager', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `glpt_user`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_user`;
CREATE TABLE `glpt_user` (
  `ID` varchar(35) NOT NULL COMMENT 'ID',
  `USER_CODE` varchar(30) NOT NULL COMMENT '用户代号',
  `USER_NAME` varchar(30) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `REAL_NAME` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `SEX` varchar(2) DEFAULT NULL COMMENT '性别',
  `AGE` int(11) DEFAULT NULL COMMENT '年龄',
  `BIRTHDAY` datetime DEFAULT NULL COMMENT '生日',
  `MOBILE_PHONE` varchar(30) DEFAULT NULL COMMENT '手机号',
  `TELPHONE` varchar(30) DEFAULT NULL COMMENT '电话',
  `CAED_ID` varchar(30) DEFAULT NULL COMMENT '身份证明号码（身份证、军官证、护照等等）',
  `ADDRESS` varchar(300) DEFAULT NULL COMMENT '地址',
  `STATUS` varchar(5) DEFAULT NULL COMMENT '状态',
  `INLINE` varchar(5) DEFAULT NULL COMMENT '在线状态（在线，隐身、忙碌、离开、离线）',
  `EDUCATION` varchar(50) DEFAULT NULL COMMENT '学历',
  `EMAIL` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `QQ` varchar(30) DEFAULT NULL COMMENT 'QQ号码',
  `REMAEK` varchar(500) DEFAULT NULL COMMENT '描述',
  `SIGNATURE` varchar(100) DEFAULT NULL COMMENT '签名',
  `LRR` varchar(30) DEFAULT NULL COMMENT '操作人',
  `LRRXM` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `LRSJ` datetime DEFAULT NULL COMMENT '操作时间',
  `LRRBM` varchar(30) DEFAULT NULL COMMENT '操作人部门',
  `LRIP` varchar(30) DEFAULT NULL COMMENT '操作IP',
  `LRMAC` varchar(30) DEFAULT NULL COMMENT '操作MAC',
  `LAST_UPDATE` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of glpt_user
-- ----------------------------
INSERT INTO `glpt_user` VALUES ('1', 'admin', '超级管理员', '53F139E187E60F8376343990737B8FEC', '超级管理员', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `glpt_user_dept`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_user_dept`;
CREATE TABLE `glpt_user_dept` (
  `ID` varchar(35) NOT NULL COMMENT 'ID',
  `USER_CODE` varchar(30) DEFAULT NULL COMMENT '用户代号',
  `DEPT_CODE` varchar(30) DEFAULT NULL COMMENT '部门代号',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '描述',
  `LRR` varchar(30) DEFAULT NULL COMMENT '录入人',
  `LRRXM` varchar(50) DEFAULT NULL COMMENT '录入人姓名',
  `LRSJ` datetime DEFAULT NULL COMMENT '录入时间',
  `LRRBM` varchar(30) DEFAULT NULL COMMENT '录入人部门',
  `LRIP` varchar(30) DEFAULT NULL COMMENT '录入ip',
  `LRMAC` varchar(30) DEFAULT NULL COMMENT '录入mac',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户部门信息表';

-- ----------------------------
-- Records of glpt_user_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `glpt_user_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_user_privilege`;
CREATE TABLE `glpt_user_privilege` (
  `ID` varchar(35) NOT NULL COMMENT 'ID',
  `USER_CODE` varchar(30) DEFAULT NULL COMMENT '用户代号',
  `NODE_CODE` varchar(30) DEFAULT NULL COMMENT '节点代号',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '描述',
  `LRR` varchar(30) DEFAULT NULL COMMENT '录入人',
  `LRRXM` varchar(50) DEFAULT NULL COMMENT '录入人姓名',
  `LRSJ` datetime DEFAULT NULL COMMENT '录入时间',
  `LRRBM` varchar(30) DEFAULT NULL COMMENT '录入人部门',
  `LRIP` varchar(30) DEFAULT NULL COMMENT '录入ip',
  `LRMAC` varchar(30) DEFAULT NULL COMMENT '录入mac',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- ----------------------------
-- Records of glpt_user_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for `glpt_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `glpt_user_role`;
CREATE TABLE `glpt_user_role` (
  `ID` varchar(35) NOT NULL COMMENT 'ID',
  `USER_CODE` varchar(30) DEFAULT NULL COMMENT '用户代号',
  `ROLE_CODE` varchar(30) DEFAULT NULL COMMENT '角色代号',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '描述',
  `LRR` varchar(30) DEFAULT NULL COMMENT '录入人',
  `LRRXM` varchar(50) DEFAULT NULL COMMENT '录入人姓名',
  `LRSJ` datetime DEFAULT NULL COMMENT '录入时间',
  `LRRBM` varchar(30) DEFAULT NULL COMMENT '录入人部门',
  `LRIP` varchar(30) DEFAULT NULL COMMENT '录入ip',
  `LRMAC` varchar(30) DEFAULT NULL COMMENT '录入mac',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色信息表';

-- ----------------------------
-- Records of glpt_user_role
-- ----------------------------
INSERT INTO `glpt_user_role` VALUES ('1', 'admin', 'admin', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sys_dynform_field`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dynform_field`;
CREATE TABLE `sys_dynform_field` (
  `id` varchar(35) NOT NULL COMMENT '属性id',
  `head_id` varchar(50) DEFAULT NULL COMMENT '表单Id',
  `field_code` varchar(50) NOT NULL COMMENT '属性代号',
  `field_name` varchar(50) DEFAULT NULL COMMENT '属性名称',
  `field_type` varchar(30) DEFAULT NULL COMMENT '属性类型',
  `field_length` int(11) DEFAULT NULL COMMENT '字段长度',
  `precisions` int(11) DEFAULT NULL COMMENT '精度',
  `field_default` varchar(50) DEFAULT NULL COMMENT '默认值',
  `field_valid` varchar(500) DEFAULT NULL COMMENT '属性验证',
  `is_pk` char(10) DEFAULT NULL COMMENT '是否主键',
  `is_null` varchar(5) DEFAULT NULL COMMENT '是否为空',
  `is_pic` varchar(5) DEFAULT NULL COMMENT '是否图片（0：否   1：是）',
  `is_encrypt` varchar(5) DEFAULT NULL COMMENT '是否加密（0：否   1：是）',
  `is_show` varchar(5) DEFAULT NULL COMMENT '是否表单显示（0：否   1：是）',
  `is_show_list` varchar(5) DEFAULT NULL COMMENT '是否在列表显示（0：否   1：是）',
  `is_query` varchar(5) DEFAULT NULL COMMENT '是否查询条件',
  `dict_table` varchar(50) DEFAULT NULL COMMENT '字典表名',
  `dic_text` varchar(50) DEFAULT NULL COMMENT '字典显示名',
  `dic_field` varchar(50) DEFAULT NULL COMMENT '字典属性名',
  `dic_accessory` varchar(200) DEFAULT NULL COMMENT '字典附件条件',
  `control_type` varchar(50) DEFAULT NULL COMMENT '控件类型',
  `commont` varchar(200) DEFAULT NULL COMMENT '描述',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `oprate_ip` varchar(50) DEFAULT NULL COMMENT '操作IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态表单属性';

-- ----------------------------
-- Records of sys_dynform_field
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_dynform_head`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dynform_head`;
CREATE TABLE `sys_dynform_head` (
  `id` varchar(35) NOT NULL COMMENT '动态表id',
  `head_tabname` varchar(30) NOT NULL COMMENT '表单名',
  `head_type` varchar(5) DEFAULT NULL COMMENT '表单类型（1：单表  2：主表  3：字表）',
  `head_pk_type` varchar(10) DEFAULT NULL COMMENT '主键类型（0001： 序列   0002：uuid， 0003：自增长）',
  `head_pk_sequnece` varchar(30) DEFAULT NULL COMMENT '序列名',
  `head_content` varchar(300) DEFAULT NULL COMMENT '表单描述',
  `head_sub_tabname` varchar(500) DEFAULT NULL COMMENT '子表（多个字表用英文逗号隔开）',
  `head_order` int(11) DEFAULT NULL COMMENT '顺序',
  `is_pagination` varchar(5) DEFAULT NULL COMMENT '是否分页(0：否   1：是)',
  `is_tree` varchar(5) DEFAULT NULL COMMENT '是否树（0：否  1：是）',
  `is_db_synchro` varchar(10) DEFAULT NULL COMMENT '是否数据库同步(0：否   1：是)',
  `synchro_date` datetime DEFAULT NULL COMMENT '同步时间',
  `version` varchar(10) DEFAULT NULL COMMENT '版本',
  `query_mode` varchar(30) DEFAULT NULL COMMENT ' 查询模式（0001：单表查询   0002：组合查询）',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `update_name` varchar(100) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `operate_ip` varchar(30) DEFAULT NULL COMMENT '操作IP',
  `Column_22` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态表信息';

-- ----------------------------
-- Records of sys_dynform_head
-- ----------------------------
