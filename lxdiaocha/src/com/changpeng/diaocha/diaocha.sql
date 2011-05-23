DROP TABLE IF EXISTS `pxxt_shenzhen`.`diaocha`;
CREATE TABLE  `pxxt_shenzhen`.`diaocha` (
  `diaochaid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `createuser` varchar(20) NOT NULL,
  `createtime` datetime NOT NULL,
  `shuoming` text,
  `state` tinyint(3) NOT NULL DEFAULT '0' COMMENT '调查状态 1草稿 2发布 3停止',
  `replycount` int(11) DEFAULT NULL,
  PRIMARY KEY (`diaochaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调查表';

DROP TABLE IF EXISTS `pxxt_shenzhen`.`diaochaoption`;
CREATE TABLE  `pxxt_shenzhen`.`diaochaoption` (
  `optionid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `wentiid` int(10) unsigned NOT NULL,
  `options` varchar(20) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `logicwenti` int(10) unsigned DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `remarks` varchar(400) DEFAULT NULL,
  `replycount` int(10) unsigned NOT NULL DEFAULT '0',
  `others` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`optionid`),
  KEY `FK_diaochaoption_1` (`wentiid`),
  CONSTRAINT `FK_diaochaoption_1` FOREIGN KEY (`wentiid`) REFERENCES `diaochawenti` (`wentiid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `pxxt_shenzhen`.`diaochareply`;
CREATE TABLE  `pxxt_shenzhen`.`diaochareply` (
  `replyid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `wentiid` int(10) unsigned NOT NULL COMMENT '问题ID',
  `replycontent` varchar(500) DEFAULT NULL COMMENT '回复内容',
  `replyuser` varchar(20) NOT NULL,
  `replytime` datetime NOT NULL,
  `others` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`replyid`),
  KEY `FK424A7C41619C8728` (`wentiid`),
  CONSTRAINT `FK424A7C41619C8728` FOREIGN KEY (`wentiid`) REFERENCES `diaochawenti` (`wentiid`) ON DELETE CASCADE,
  CONSTRAINT `FK_diaochareply_1` FOREIGN KEY (`wentiid`) REFERENCES `diaochawenti` (`wentiid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调查回复';

DROP TABLE IF EXISTS `pxxt_shenzhen`.`diaochatype`;
CREATE TABLE  `pxxt_shenzhen`.`diaochatype` (
  `typeid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '类别ID',
  `diaochaid` int(10) unsigned NOT NULL COMMENT '调查ID',
  `typename` varchar(200) NOT NULL COMMENT '类别名称',
  PRIMARY KEY (`typeid`),
  KEY `FK_diaochatype_1` (`diaochaid`),
  CONSTRAINT `FK_diaochatype_1` FOREIGN KEY (`diaochaid`) REFERENCES `diaocha` (`diaochaid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `pxxt_shenzhen`.`diaochawenti`;
CREATE TABLE  `pxxt_shenzhen`.`diaochawenti` (
  `wentiid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `diaochaid` int(10) unsigned NOT NULL COMMENT '对应哪个调查',
  `title` varchar(200) DEFAULT NULL,
  `wentileixing` tinyint(3) NOT NULL COMMENT '问题的类型1单选2多选3单输入框4多输入框5分页符',
  `isbixu` bit(1) DEFAULT NULL COMMENT '是否必答题目',
  `createtime` datetime NOT NULL,
  `createuser` varchar(20) NOT NULL,
  `remarks` varchar(400) DEFAULT NULL,
  `typeid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`wentiid`),
  KEY `FK4290EB2C8EF6E9A1` (`diaochaid`),
  KEY `FK_diaochawenti_3` (`typeid`),
  CONSTRAINT `FK4290EB2C8EF6E9A1` FOREIGN KEY (`diaochaid`) REFERENCES `diaocha` (`diaochaid`) ON DELETE CASCADE,
  CONSTRAINT `FK_diaochawenti_1` FOREIGN KEY (`diaochaid`) REFERENCES `diaocha` (`diaochaid`) ON DELETE CASCADE,
  CONSTRAINT `FK_diaochawenti_3` FOREIGN KEY (`typeid`) REFERENCES `diaochatype` (`typeid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调查的题目表';