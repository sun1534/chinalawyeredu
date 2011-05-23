package com.changpeng.common.mssql;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.context.Globals;
import com.changpeng.common.util.MD5;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysGroupService;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 定时连接到sqlserver2005，获取变动的律师和事务所信息。如果没有对应信息，则执行到mysql数据库的连接
 * 
 * @author 华锋 2008-6-17 上午10:18:18
 * 
 */
public class ScheduleTask extends TimerTask {
	private static Log LOG = LogFactory.getLog(ScheduleTask.class);

	public void run() {
		Globals globals = new Globals();
		BasicService service = (BasicService) globals.getBean("basicService");
		SysUserService userservice = (SysUserService) globals.getBean("sysUserService");
		SysGroupService groupservice = (SysGroupService) globals.getBean("sysGroupService");

		LOG.debug(service + "," + userservice + "," + groupservice);

		try {
			service.findBySqlQuery("select userid from sys_user where 1=2");
			LOG.info("执行定时连接到数据库");
		} catch (Exception e) {
			LOG.error("执行定时连接到数据库异常:" + e);
		}

	}
}