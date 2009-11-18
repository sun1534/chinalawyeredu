/**
 * SysLoginLogList.java
 */

package com.sxit.log.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractListAction;
import com.sxit.log.service.SysLoginLogService;
import com.sxit.models.log.SysLoginLog;

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
		// this.rightCode = "sysLoginLogList";
	}

	private String loginTime;

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		BasicService bs = (BasicService) getBean("basicService");

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysLoginLog.class);

		if (username != null && !username.equals("")) {

			detachedCriteria.add(Restrictions.like("username", username, MatchMode.ANYWHERE));

		}
		if (loginTime != null && !loginTime.equals("")) {
			java.sql.Timestamp begin = new Timestamp(df.parse(loginTime + " 00:00:00").getTime());
			java.sql.Timestamp end = new Timestamp(df.parse(loginTime + " 23:59:59").getTime());
			detachedCriteria.add(Restrictions.between("loginTime", begin, end));
		}
		detachedCriteria.addOrder(Order.desc("loginid"));
		SysLoginLogService service = (SysLoginLogService) getBean("sysLoginLogService");
		this.pageSize = 10;

		if (totype != null && totype.equals("excel")) {
			this.page = service.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, 1);
			return "excel";

		} else {
			this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
			return "list";
		}

	}

	private String totype = "list";

	public String getTotype() {
		return totype;
	}

	public void setTotype(String totype) {
		this.totype = totype;
	}
}
