/**
 * SysLoginLogList.java
 */

package com.changpeng.system.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.system.SysLoginLog;
import com.changpeng.system.service.SysLoginLogService;

/**
 * 
 * 显示所有登录的日志,提供根据用户名和登录时间进行查询的功能
 * 
 * 查询的话，根据登录的用户来查询疾
 * 
 * @author 华锋 2008-3-3 上午11:11:29
 * 
 */
public class SysLoginLogListAction extends AbstractListAction {
	private static Log _LOG = LogFactory.getLog(SysLoginLogListAction.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public SysLoginLogListAction() {
		this.rightCode = "sysLoginLogList";
	}

	private int userid;

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getUserid() {
		return this.userid;
	}

	private String loginTime;

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginTime() {
		return this.loginTime;
	}
	
	private Map allUsers;
	public Map getAllUsers(){
//	   allUsers=CommonDatas.getUsers();
		allUsers=new HashMap();
	   _LOG.debug(allUsers);
	   return this.allUsers;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysLoginLog.class);
		if (userid != 0) {
			detachedCriteria.add(Restrictions.eq("userid", userid));
		}
		if (loginTime != null && !loginTime.equals("")) {
			java.sql.Timestamp begin = new Timestamp(df.parse(loginTime + " 00:00:00").getTime());
			java.sql.Timestamp end = new Timestamp(df.parse(loginTime + " 23:59:59").getTime());
			detachedCriteria.add(Restrictions.between("loginTime", begin, end));
		}
		detachedCriteria.addOrder(Order.desc("loginid"));
		SysLoginLogService service = (SysLoginLogService) getBean("sysLoginLogService");
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}

}
