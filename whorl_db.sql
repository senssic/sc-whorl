
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `tid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单表主键,标记唯一列自增',
  `parentId` bigint(20) DEFAULT NULL COMMENT '父级菜单编号.如果没有父级则父级为0',
  `menuUrl` varchar(255) DEFAULT NULL COMMENT '菜单Url',
  `menuName` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `permissionId` bigint(20) DEFAULT NULL COMMENT '关联权限表',
  `status` varchar(255) DEFAULT NULL COMMENT '状态EBL-可用,DBL-不可用',
  `applicationCode` varchar(255) DEFAULT NULL COMMENT '不同应用菜单的区分',
  `createAt` datetime DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `modifyAt` datetime DEFAULT NULL,
  `modifyBy` varchar(255) DEFAULT NULL,
  `orderIndex` int(11) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '4', '/menu/menuManage', '菜单管理', '1', 'EBL', 'WHORL', '2019-01-07 16:55:02', 'admin', '2019-01-07 16:55:14', 'admin', '0');
INSERT INTO `menu` VALUES ('2', '5', '/menu/personManage', '人员管理', '2', 'EBL', 'WHORL', '2019-01-07 17:08:04', 'TEST', '2019-01-07 17:08:09', 'TEST', '0');
INSERT INTO `menu` VALUES ('3', '4', '/menu/systemParam', '系统参数', '3', 'EBL', 'WHORL', '2019-01-07 17:08:45', 'TEST', '2019-01-07 17:08:50', 'TEST', '0');
INSERT INTO `menu` VALUES ('4', '0', null, '系统管理', null, 'EBL', 'WHORL', '2019-01-07 17:13:12', 'TEST', '2019-01-07 17:13:16', 'TEST', '0');
INSERT INTO `menu` VALUES ('5', '0', null, '组织管理', null, 'EBL', 'WHORL', '2019-01-07 17:13:56', 'TEST', '2019-01-07 17:14:00', 'TEST', '0');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `tid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '记录编号,自增',
  `permissionName` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `functionNumber` varchar(255) DEFAULT NULL COMMENT '权限代码',
  `createAt` datetime DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `modifyAt` datetime DEFAULT NULL,
  `modifyBy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tid`),
  UNIQUE KEY `unitcode_functionnumber` (`functionNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '菜单管理', '菜单管理菜单权限', 'MENU_MANAGE', '2019-01-07 16:56:02', 'TEST', '2019-01-07 16:56:10', 'TEST');
INSERT INTO `permission` VALUES ('2', '人员管理', '人员管理菜单权限', 'PERSON_MANAGE', '2019-01-07 17:01:25', 'TEST', '2019-01-07 17:01:29', 'TEST');
INSERT INTO `permission` VALUES ('3', '系统参数', '系统参数菜单权限', 'SYSTEM_PARAM_MANAGE', '2019-01-07 17:06:10', 'TEST', '2019-01-07 17:06:16', 'TEST');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `tid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '记录编号,自增',
  `parentId` bigint(11) DEFAULT NULL COMMENT '父级角色编号',
  `roleName` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `createAt` datetime DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `modifyAt` datetime DEFAULT NULL,
  `modifyBy` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT 'EBL-可用,DBL-不可用',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '0', '系统管理员', '系统管理员最大权限', '2019-01-07 16:56:56', 'TEST', '2019-01-07 16:57:03', 'TEST', 'EBL');

-- ----------------------------
-- Table structure for rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `rolepermission`;
CREATE TABLE `rolepermission` (
  `tid` bigint(11) NOT NULL AUTO_INCREMENT,
  `roleId` bigint(11) DEFAULT NULL COMMENT '角色编号',
  `permissionId` bigint(11) DEFAULT NULL COMMENT '权限Id',
  `status` varchar(255) DEFAULT NULL COMMENT '状态EBL-可用,DBL-不可用',
  `createAt` datetime DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `modifyAt` datetime DEFAULT NULL,
  `modifyBy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolepermission
-- ----------------------------
INSERT INTO `rolepermission` VALUES ('1', '1', '1', 'EBL', '2019-01-07 16:57:24', 'TEST', '2019-01-07 16:57:32', 'TEST');
INSERT INTO `rolepermission` VALUES ('2', '1', '2', 'EBL', '2019-01-07 17:09:55', 'TEST', '2019-01-07 17:10:05', 'TEST');
INSERT INTO `rolepermission` VALUES ('3', '1', '3', 'EBL', '2019-01-07 17:09:58', 'TEST', '2019-01-07 17:10:07', 'TEST');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `tid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '记录标识,自增',
  `loginName` varchar(255) NOT NULL COMMENT '登陆账号,不为空',
  `passWord` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `mobile` varchar(255) DEFAULT NULL COMMENT '用户手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '用户时间',
  `createAt` datetime DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `modifyAt` datetime DEFAULT NULL,
  `modifyBy` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT 'EBL-可用,DBL-不可用',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'senssic', '$2a$10$cwU3KXXThuZiXiyW5Qq9s.VqzzKCfPG6pwD7RW/zZpXk5Ntiv5WYq', null, '17602178369', null, null, null, null, null, 'EBL');

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `tid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户校色主编号,自增',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户编号',
  `roleId` bigint(11) DEFAULT NULL COMMENT '角色编号',
  `status` varchar(255) DEFAULT NULL COMMENT '状态EBL-可用,DBL-不可用',
  `createAt` datetime DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `modifyAt` datetime DEFAULT NULL,
  `modifyBy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userrole
-- ----------------------------
INSERT INTO `userrole` VALUES ('1', '1', '1', 'EBL', '2019-01-07 16:57:53', 'TEST', '2019-01-07 16:58:01', 'TEST');