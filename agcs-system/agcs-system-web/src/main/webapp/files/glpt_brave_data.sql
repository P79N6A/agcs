INSERT INTO `glpt_node` VALUES ('1', 'glpt', '系统管理', null, 'icon-sys', '0', 'T', 'node', 'GLPT', '1', null, null, null, null, null, null, null, null);
INSERT INTO `glpt_node` VALUES ('4', 'logmanager', '日志管理', 'glptActionLogController.do?glptActionLog', 'icon-sys', 'glpt', 'T', 'node', 'GLPT', '4', 'sdfds', 'sdfds', 'dsfs', 'fsdf', '2016-04-08 06:25:32', '192.168.0.72', '', null);
INSERT INTO `glpt_node` VALUES ('3', 'nodemanager', '菜单管理', 'glptNodeController.do?glptNode', 'icon-sys', 'glpt', 'T', 'node', 'GLPT', '3', null, null, null, null, null, null, null, null);
INSERT INTO `glpt_node` VALUES ('402880c854425d0c0154425e78560002', 'roleprivilegemanager', '角色权限管理', 'glptRolePrivilegeController.do?glptRolePrivilege', 'icon-sys', 'glpt', 'T', 'node', 'GLPT', '5', '', '', '', '', null, '', '', null);
INSERT INTO `glpt_node` VALUES ('2', 'usermanager', '用户管理', 'glptUserController.do?user', 'icon-sys', 'glpt', 'T', 'node', 'GLPT', '2', null, null, null, null, null, null, null, null);

INSERT INTO `glpt_role` VALUES ('1', 'admin', '超级管理员', 'T', null, null, null, null, null, null, null, null);

INSERT INTO `glpt_role_privilege` VALUES ('1', 'admin', 'glpt', null, null, null, null, null, null, null);
INSERT INTO `glpt_role_privilege` VALUES ('2', 'admin', 'usermanager', null, null, null, null, null, null, null);
INSERT INTO `glpt_role_privilege` VALUES ('3', 'admin', 'nodemanager', null, null, null, null, null, null, null);
INSERT INTO `glpt_role_privilege` VALUES ('4', 'admin', 'logmanager', null, null, null, null, null, null, null);
INSERT INTO `glpt_role_privilege` VALUES ('5', 'admin', 'roleprivilegemanager', null, null, null, null, null, null, null);

INSERT INTO `glpt_user` VALUES ('1', 'admin', '超级管理员', '53F139E187E60F8376343990737B8FEC', '超级管理员', '', null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '2016-04-08 18:01:57', '', '', '', null);

INSERT INTO `glpt_user_role` VALUES ('1', 'admin', 'admin', null, null, null, null, null, null, null);