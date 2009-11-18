/**
 * 
 */
package com.sxit.users.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.sxit.common.BasicService;
import com.sxit.common.Globals;
import com.sxit.common.exception.ServiceException;
import com.sxit.models.users.CoreUser;

/**
 * @author 华锋 Jul 10, 2009 9:24:43 PM
 * 
 */
public class CommonDatas {
	private static BasicService basicService = (BasicService) Globals.getBean("basicService");
	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(CommonDatas.class);
	public static Map<Short, String> STATUS = new LinkedHashMap<Short, String>();
	public static Map<Integer, String> ALLUSERS = new LinkedHashMap<Integer, String>();
//	public static Map<Integer, String> ALLUSERS = new LinkedHashMap<Integer, String>();
	public static Map<Integer, String> APPROVETYPE = new LinkedHashMap<Integer, String>();
	
	private static long Interactive = 0;
	static {
		STATUS.put((short) 0, "认证通过");
		STATUS.put((short) 1, "新注册");
		STATUS.put((short) 2, "待认证");
		STATUS.put((short) 3, "认证未通过");
//		 1、身份证认证  2、电话认证  3、上门认证               盎然
//		1、身份证认证  2、  3、上门认证
		
		APPROVETYPE.put(1, "证件认证");
		APPROVETYPE.put(2, "电话认证");
		APPROVETYPE.put(3, "上门认证");
		
	}

	public static void getAllUsers() {
		long now = System.currentTimeMillis();
		
		if (now - Interactive > 2 * 60 * 1000) {
			try {
				
				List diarylist = basicService.findAll(CoreUser.class);
			
				synchronized (ALLUSERS) {
					ALLUSERS.clear();
					for (int i = 0; diarylist != null && i < diarylist.size(); i++) {
						CoreUser type = (CoreUser) diarylist.get(i);
						ALLUSERS.put(type.getId(), type.getUserName());
					}
				}
			} catch (ServiceException e) {
				LOG.error("getAllUsers::" + e);
			}
			Interactive = now;
		}
	}
}
