/**
 * CommonDatas.java
 */

package com.changpeng.system.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUnionparams;
import com.changpeng.models.SysUser;

/**
 * 公共数据
 * 
 * @author 华锋 2008-3-10 下午01:50:30
 * 
 */
public class CommonDatas {
	private static Log LOG = LogFactory.getLog(CommonDatas.class);
	public static Map<Integer, String> users = new LinkedHashMap<Integer, String>();
	public static Map<Integer, String> groups = new LinkedHashMap<Integer, String>();

	private static long userInteractive = 0;
	private static long groupInteractive = 0;

	public static Map<String, String> MONTHS = new LinkedHashMap<String, String>();
	public static Map<String, String> DAYS = new LinkedHashMap<String, String>();

	static {
		MONTHS.put("01", "1");
		MONTHS.put("02", "2");
		MONTHS.put("03", "3");
		MONTHS.put("04", "4");
		MONTHS.put("05", "5");
		MONTHS.put("06", "6");
		MONTHS.put("07", "7");
		MONTHS.put("08", "8");
		MONTHS.put("09", "9");
		MONTHS.put("10", "10");
		MONTHS.put("11", "11");
		MONTHS.put("12", "12");

		for (int i = 1; i <= 31; i++)
			DAYS.put(day2str(i), i + "");

	}

	private static String day2str(int day) {
		if (day < 10)
			return "0" + day;
		return day + "";
	}

	public static void getUsers() {
		long now = System.currentTimeMillis();
//		if (now - userInteractive > 5 * 60 * 60 * 1000) { // 每5分钟
			try {
				BasicService service = (BasicService) Globals.getBean("basicService");
				synchronized (users) {
					int size=users.size();
				
				users.clear();
				DetachedCriteria dc = DetachedCriteria.forClass(SysUser.class);
				List list = service.findAllByCriteria(dc);
				int len = list == null ? 0 : list.size();
				for (int i = 0; i < len; i++) {
					SysUser sysuser = (SysUser) list.get(i);
					users.put(sysuser.getUserid(), sysuser.getUsername());
				}
				LOG.info("管理员更新成功!::前:"+size+"后:"+groups.size());
				}
			} catch (ServiceException e) {
				LOG.error("获取所有用户失败" + e);
			}
			userInteractive = now;
//		}
	}

	public static void getGroups() {
		long now = System.currentTimeMillis();
		// if (now - groupInteractive > 60 * 60 * 60 * 1000) { // 每60分钟
		try {
			BasicService service = (BasicService) Globals.getBean("basicService");
			synchronized (groups) {
				int size=groups.size();
				groups.clear();
				DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class);
				List list = service.findAllByCriteria(dc);
				int len = list == null ? 0 : list.size();
				for (int i = 0; i < len; i++) {
					SysGroup sysgroup = (SysGroup) list.get(i);
					groups.put(sysgroup.getGroupid(), sysgroup.getGroupname());
				}
				groups.put(0, "系统发布");
				LOG.info("部门更新成功!::前:"+size+"后:"+groups.size());
			}
		} catch (ServiceException e) {
			LOG.error("获取所有部门失败" + e);
		}
		groupInteractive = now;
		// }
	}

	/**
	 * 以url为主得到数据信息
	 */
	public static void getGroupParamsUrl() {
		BasicService service = (BasicService) Globals.getBean("basicService");
		try {
			List list = service.findAll(SysUnionparams.class);
			int length = list == null ? 0 : list.size();
			synchronized (com.changpeng.common.CommonDatas.SysUnionparams) {
				com.changpeng.common.CommonDatas.SysUnionparams.clear();
				for (int i = 0; i < length; i++) {
					SysUnionparams area = (SysUnionparams) list.get(i);
					if (area.getDomain() != null && !area.getDomain().equals("")) {
						com.changpeng.common.CommonDatas.SysUnionparams.put(area.getDomain(), area);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("获取URL参数失败:", e);
		}
	}
}