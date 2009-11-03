CREATE TABLE  `elearning`.`coursetype` (
  `typeid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `typename` varchar(100) NOT NULL,
  `createuser` varchar(45) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `warecount` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE  `elearning`.`courseware` (
  `wareid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `typeid` int(10) unsigned NOT NULL,
  `warename` varchar(100) NOT NULL,
  `warefile` varchar(100) NOT NULL,
  `waretime` float(5,1) NOT NULL DEFAULT '30.0',
  `xuefen` float(5,1) NOT NULL DEFAULT '0.0',
  `isupload` tinyint(1) DEFAULT NULL,
  `createuser` varchar(45) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `readcount` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`wareid`),
  KEY `FK_courseware_1` (`typeid`),
  CONSTRAINT `FK_courseware_1` FOREIGN KEY (`typeid`) REFERENCES `coursetype` (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


alter table lawyerlessonxf add wareid int(10) unsigned default NULL;

alter table lawyerlessonxf modify title varchar(100);