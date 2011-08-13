package com.changpeng.lawcase.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.changpeng.lawcase.model.JiXiaos;
import com.sxit.system.model.TsysUser;
import com.sxit.workflow.model.TwflAction;
import com.sxit.workflow.model.TwflNode;

public class CommanDatas {
/**
 * 台帐管理员的角色
 */
	public static int TAIZHANGROLE=401;
	
	public static Map<Integer, String> CASE_BANKS = new LinkedHashMap<Integer, String>();
	public static Map<String, Integer> CASE_BANKS_ID = new LinkedHashMap<String, Integer>();

	public static Map<Long, String> USER_ID_NAME = new LinkedHashMap<Long, String>();
	public static Map<Long, TsysUser> ALLUSERS = new LinkedHashMap<Long, TsysUser>();
	public static Map<String, Long> USER_NAME_ID = new LinkedHashMap<String, Long>();

	/**
	 * 所有的案件处理动作
	 */
	public static Map<Integer, TwflAction> ALLACTIONS = new LinkedHashMap<Integer, TwflAction>();
/**
 * 所有的nodes
 */
	public static Map<Integer, TwflNode> ALLNODES = new LinkedHashMap<Integer, TwflNode>();

	/**
	 * 存储对应的状态
	 */
	public static Map<Integer, String> CASE_STATUS;

	public static Map<Integer, String> CASE_STATUS_TAB;
	/**
	 * 案件的一些节点
	 */
	public static Map<Integer, JiXiaos> ALLJIXIAOS = new LinkedHashMap<Integer, JiXiaos>();;



	static {
		CASE_BANKS = new LinkedHashMap<Integer, String>();
		CASE_BANKS.put(1, "工商银行");
		CASE_BANKS.put(2, "建设银行");
		CASE_BANKS.put(3, "中国银行");
		CASE_BANKS.put(4, "招商银行");
		CASE_BANKS.put(5, "兴业银行");
		CASE_BANKS.put(6, "浦发银行");

		CASE_BANKS_ID = new LinkedHashMap<String, Integer>();
		CASE_BANKS_ID.put("工商银行", 1);
		CASE_BANKS_ID.put("建设银行", 2);
		CASE_BANKS_ID.put("中国银行", 3);
		CASE_BANKS_ID.put("招商银行", 4);
		CASE_BANKS_ID.put("兴业银行", 5);
		CASE_BANKS_ID.put("浦发银行", 6);

		CASE_STATUS = new LinkedHashMap<Integer, String>();
		CASE_STATUS.put(-1, "新导入案件");
		CASE_STATUS.put(1, "入口案件");
		CASE_STATUS.put(10, "存量案件");
		CASE_STATUS.put(11, "存量案件(阶段性已结)");
		CASE_STATUS.put(20, "结案中案件");
		CASE_STATUS.put(30, "出口案件");
		CASE_STATUS.put(40, "异常案件");
		

		CASE_STATUS_TAB = new LinkedHashMap<Integer, String>();
		// CASE_STATUS_TAB.put(0, "新增案件");
		CASE_STATUS_TAB.put(1, "入口案件");
		CASE_STATUS_TAB.put(10, "存量案件");
		CASE_STATUS_TAB.put(11, "阶段性已结案件");
		CASE_STATUS_TAB.put(20, "结案中案件");
		CASE_STATUS_TAB.put(30, "出口案件");

		
	}

	/**
	 * 得到用户名等
	 * 
	 * @param session
	 */
	public static void getAllActions(Session session) {
		long now = System.currentTimeMillis();
		if (now - actioninterval > 3 * 60 * 60 * 1000) {
			List list = session.createQuery("from TwflAction a").list();
			ALLACTIONS.clear();
			for (int i = 0; list != null && i < list.size(); i++) {
				TwflAction action = (TwflAction) list.get(i);
				ALLACTIONS.put(action.getActionid(), action);
			}
			System.out.println(ALLACTIONS);
			list = session.createQuery("from TwflNode a").list();
			ALLNODES.clear();
			for (int i = 0; list != null && i < list.size(); i++) {
				TwflNode action = (TwflNode) list.get(i);
				ALLNODES.put(action.getNodeid(), action);
			}
			System.out.println(ALLNODES);
			list = session.createQuery("from JiXiaos a").list();
			ALLJIXIAOS.clear();
			for (int i = 0; list != null && i < list.size(); i++) {
				JiXiaos action = (JiXiaos) list.get(i);
				ALLJIXIAOS.put(action.getJixiaoid(), action);
			}
			actioninterval=now;
			System.out.println(ALLJIXIAOS);
		} else {
			System.out.println("从内存里面拿字典数据");
		}
	}

	private static long interval = 0;
	private static long actioninterval = 0;

	/**
	 * 得到所有的用户名密码等
	 * 
	 * @param session
	 */
	public static void getUserNames(Session session) {
		long now = System.currentTimeMillis();
		if (now -interval > 1 * 60 * 60 * 1000) {
			String sql = "from TsysUser where statusid=1";
			org.hibernate.Query query = session.createQuery(sql);
			List list = query.list();
			USER_ID_NAME.clear();
			USER_NAME_ID.clear();
			for (int i = 0; i < list.size(); i++) {
				TsysUser user = (TsysUser) list.get(i);
				USER_ID_NAME.put(user.getUserid(),user.getUsername());
				USER_NAME_ID.put(user.getUsername(),user.getUserid());
				ALLUSERS.put(user.getUserid(), user);
			}
			interval = now;
			System.out.println(USER_ID_NAME);
		} else {
			System.out.println("从内存里面拿用户数据");
		}
	}
}
