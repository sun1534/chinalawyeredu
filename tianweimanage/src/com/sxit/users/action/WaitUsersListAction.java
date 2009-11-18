package com.sxit.users.action;

import java.text.DateFormat;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.users.CoreUser;

/**
 * 
 *  需要进行认证的用户列表
 *  1,家庭 2企业',
 * @author 华锋
 * Jul 9, 2009 11:16:21 PM
 *
 */

public class WaitUsersListAction extends AbstractListAction {
	public WaitUsersListAction() {

	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public String go() throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CoreUser.class);
	
		detachedCriteria.add(Restrictions.in("status", new Object[]{(short)2,(short)3}));
		if (userName != null && !userName.equals("")) {
			detachedCriteria.add(Restrictions.like("userName", userName, MatchMode.START));
		}
		if (start != null && !start.equals("")) {
			java.sql.Timestamp starttime = new java.sql.Timestamp(df.parse(start).getTime());
			detachedCriteria.add(Restrictions.ge("regTime", starttime));
		}
		if (end != null && !end.equals("")) {
			java.sql.Timestamp endtime = new java.sql.Timestamp(df.parse(end).getTime());
			detachedCriteria.add(Restrictions.le("regTime", endtime));
		}
		detachedCriteria.add(Restrictions.eq("userRole", userRole));
		
		detachedCriteria.addOrder(Order.desc("id"));
		this.page = basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}


	private short userRole;
	
	
	private String userName;
	private String start;
	private String end;

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public short getUserRole() {
		return userRole;
	}

	public void setUserRole(short userRole) {
		this.userRole = userRole;
	}

}
