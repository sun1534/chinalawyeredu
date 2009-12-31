/**
 * CommonDatas.java
 */

package com.sxit.system.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.Globals;
import com.sxit.common.exception.ServiceException;
import com.sxit.log.service.SysLoginLogService;
import com.sxit.models.system.SysRole;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysGroupService;
import com.sxit.system.service.SysUserService;

/**
 * 公共数据
 * 
 * @author 华锋 2008-3-10 下午01:50:30
 * 
 */
public class CommonDatas {
	private static Log LOG = LogFactory.getLog(SysLoginLogService.class);
//	private static Map users;
//	private static Map groups;

	private static long userInteractive = 0;
	private static long groupInteractive = 0;
	private static Globals globals = new Globals();
	private static SysUserService userService;
	private static SysGroupService groupService;
	public static Map loginnameMap=new HashMap();
	public static Map usernameMap=new HashMap();
	public static Map groupsMap=new HashMap();
	
	static {

		userService = (SysUserService) globals.getBean("sysUserService");
		 groupService= (SysGroupService) globals.getBean("sysGroupService");
	}

	public static Map<Short,String> getRoleIdsByUserId(String userid){
		SysUser sysUser = userService.getUser(Short.parseShort(userid));
		Map<Short,String> map = new HashMap<Short,String>();
//		System.out.println("SysRole:------------->"+sysUser.getSysRoles().size());
		for(SysRole role:sysUser.getSysRoles()){
			map.put(role.getRoleid(), role.getRolename());
		}
		return map;
	}
	public static void getUsers(){
		long now = System.currentTimeMillis();
		if (now - userInteractive > 5 * 60 * 1000) {
			try {
				loginnameMap.clear();
				usernameMap.clear();
				userService.getAllUsersMap(loginnameMap,usernameMap);
			}
			catch (ServiceException e) {
				LOG.error(e);
				
			}
			userInteractive=now;
		}	
	}
	public static void getGroups(){
		
		long now = System.currentTimeMillis();
		if (now - groupInteractive > 5 * 60 * 1000) {
			try {
				groupsMap.clear();
				groupsMap=groupService.getAllGroups();
			}
			catch (ServiceException e) {
				LOG.error(e);
			}
			groupInteractive=now;
		}	
	}

	public static String getLoginNameByUserId(String userid) {
		long now = System.currentTimeMillis();
//		System.out.println("获取UserId::"+userid+"--"+(now - userInteractive ));
		
		if (now - userInteractive > 5 * 60 * 1000) {
			try {
				LOG.info("每5分钟刷新1次Users数据,现在重新获取");
				
				getUsers();
			}
			catch (ServiceException e) {
				LOG.error(e);
			}
			userInteractive=now;
		}
		if(loginnameMap.get(Integer.parseInt(userid))!=null)
			return loginnameMap.get(Integer.parseInt(userid)).toString();
		else
			return "该用户已被删除";
	}
	public static String getUserNameByUserId(String userid) {
		long now = System.currentTimeMillis();
//		System.out.println("获取UserId::"+userid+"--"+(now - userInteractive ));
		
		if (now - userInteractive > 5 * 60 * 1000) {
			try {
				LOG.info("每5分钟刷新1次Users数据,现在重新获取");
				getUsers();
			}
			catch (ServiceException e) {
				LOG.error(e);
			}
			userInteractive=now;
		}
		if(usernameMap.get(Integer.parseInt(userid))!=null)
			return usernameMap.get(Integer.parseInt(userid)).toString();
		else
			return "";
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