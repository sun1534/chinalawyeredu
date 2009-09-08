/**
 * CommonDatas.java
 */

package com.changpeng.system.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.context.Globals;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.system.service.SysGroupService;
import com.changpeng.system.service.SysLoginLogService;
import com.changpeng.system.service.SysUserService;

/**
 * 公共数据
 * 
 * @author 华锋 2008-3-10 下午01:50:30
 * 
 */
public class CommonDatas {
	private static Log LOG = LogFactory.getLog(SysLoginLogService.class);
	private static Map users;
	private static Map groups;

	private static long userInteractive = 0;
	private static long groupInteractive = 0;
	private static Globals globals = new Globals();
	private static SysUserService userService;
	private static SysGroupService groupService;
	static {

		userService = (SysUserService) globals.getBean("sysUserService");
		groupService = (SysGroupService) globals.getBean("sysGroupService");

	}
	public static Map getUsers(){
		long now = System.currentTimeMillis();
		if (now - userInteractive > 3 * 60 * 60 * 1000) {
			try {
				users = userService.getAllUsersMap();
			}
			catch (ServiceException e) {
				LOG.error(e);
				
			}
			userInteractive=now;
		}	
		return users;
	}
//	public static Map getGroups(){
//		long now = System.currentTimeMillis();
//		if (now - userInteractive > 3 * 60 * 60 * 1000) {
//			try {
//				groups = groupService.getAllGroupsMap();
//			}
//			catch (ServiceException e) {
//				LOG.error(e);
//				
//			}
//			userInteractive=now;
//		}
//		return groups;
//	}

	public static String getLoginNameByUserId(String userid) {
		long now = System.currentTimeMillis();
		if (now - userInteractive > 3 * 60 * 60 * 1000) {
			try {
				users = userService.getAllUsersMap();
			}
			catch (ServiceException e) {
				LOG.error(e);
				
			}
			userInteractive=now;
		}

		return users.get(Integer.parseInt(userid)).toString();

	}

//	public static String getGroupNameByGroupId(String groupid) {
//		long now = System.currentTimeMillis();
//		if (now - userInteractive > 3 * 60 * 60 * 1000) {
//			try {
//				groups = groupService.getAllGroupsMap();
//			}
//			catch (ServiceException e) {
//				LOG.error(e);
//			}
//			groupInteractive=now;
//		}
//		return groups.get(Integer.parseInt(groupid)).toString();
//	}
}