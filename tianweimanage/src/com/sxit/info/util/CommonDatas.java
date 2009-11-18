/**
 * 
 */
package com.sxit.info.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.sxit.common.BasicService;
import com.sxit.common.Globals;
import com.sxit.common.exception.ServiceException;
import com.sxit.models.info.TinfType;
import com.sxit.models.users.CoreUser;

/**
 * @author 华锋 Jul 8, 2009 11:59:31 PM
 * 
 */
public class CommonDatas {
	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(CommonDatas.class);
	private static BasicService basicService = (BasicService) Globals.getBean("basicService");

	public static Map<Integer, String> INFOTYPES = new LinkedHashMap<Integer, String>();

	public static Map<Integer, String> INFOSTATUS = new LinkedHashMap<Integer, String>();
	static {

		// INFOTYPES.put(1, "热点推荐");
		// INFOTYPES.put(2, "活动");
		// INFOTYPES.put(3, "网站新闻");
		// INFOTYPES.put(4, "会员实惠");
		// INFOTYPES.put(100, "系统公告");
		// 1:草稿，2:待批，3:通过审批，4:未通过审批，0:发布，-1:冻结
		INFOSTATUS.put(0, "发布");
		INFOSTATUS.put(-1, "冻结");
		INFOSTATUS.put(1, "草稿");
		INFOSTATUS.put(2, "待批");
		INFOSTATUS.put(3, "通过审批");
		INFOSTATUS.put(3, "未通过审批");
	}

	public static void getAllTypes() {
		long now = System.currentTimeMillis();

		try {

			List diarylist = basicService.findAll(TinfType.class);

			synchronized (INFOTYPES) {
				INFOTYPES.clear();
				for (int i = 0; diarylist != null && i < diarylist.size(); i++) {
					TinfType type = (TinfType) diarylist.get(i);
					INFOTYPES.put(type.getTypeid(), type.getName());
				}
			}
		} catch (ServiceException e) {
			LOG.error("getAllUsers::" + e);
		}

	}
}
