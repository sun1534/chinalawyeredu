/**
 * 
 */
package com.changpeng.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;

import com.changpeng.common.context.Globals;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.LawyerLoginlog;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUnionparams;

/**
 * @author 华锋
 *
 */
public class CommonDatas {
	
	private static Log LOG = LogFactory.getLog(CommonDatas.class);
	
	/**
	 * 当前在线的用户列表
	 */
	public static Map<Integer,LawyerLoginlog> ONLINE_USERS=new HashMap<Integer,LawyerLoginlog>();
	
	// 存储areaid和上级areaid的数据
	public static Map<Integer,Integer> AreasIdParent = new LinkedHashMap<Integer,Integer>();
	// 存储areaid和areaname的数据
	public static Map<Integer,String> AreasIdName = new LinkedHashMap<Integer,String>();
	/**
	 * key是url，value是对应的对象
	 */
	public static Map<String,SysUnionparams> SysUnionparams=new HashMap<String,SysUnionparams>();
	
	public static Map<String,String> SysParameter = new LinkedHashMap<String,String>();
	
	public static Map<Integer, String> groups = new LinkedHashMap<Integer, String>();;
	
	public static Map AllLawyers=new HashMap();
	
	public static Map<Integer,String> ForumType=new LinkedHashMap<Integer,String>();
	
	
	static {
	
		
		//意见和建议、积分申诉、补卡申请、产品使用咨询
		ForumType.put(1, "意见和建议");
		ForumType.put(2, "积分申诉");
		ForumType.put(3, "补卡申请");
		ForumType.put(4, "产品使用咨询");
	}		
	
	
	
	public static void getGroups() {
		long now = System.currentTimeMillis();
//		if (now - groupInteractive > 60 * 60 * 60 * 1000) { // 每60分钟
			try {
				BasicService service = (BasicService)Globals.getBean("basicService");
				synchronized (groups) {
					int size=groups.size();
				DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class);
				List list = service.findAllByCriteria(dc);
				int len = list == null ? 0 : list.size();
				for (int i = 0; i < len; i++) {
					SysGroup sysgroup = (SysGroup) list.get(i);
					groups.put(sysgroup.getGroupid(), sysgroup.getGroupname());
				}
				groups.put(0,"系统发布");
				LOG.info("部门更新成功!::前:"+size+"后:"+groups.size());
				}
			} catch (ServiceException e) {
				LOG.error("获取所有部门失败" + e);
			}
			groupInteractive = now;
//		}
	}
	
	private static long groupInteractive = 0;
}	
