package com.sxit.users.action;

import java.text.DateFormat;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.users.CoreUser;

/**
 * 1,家庭 2企业',
 * 
 * 根据姓名，注册的起始终止时间，状态以及身份证号码进行查询
 * 
 * @author 华锋 Jul 9, 2009 11:16:21 PM
 * 
 */

public class CorpUsersListAction extends AbstractListAction {

	public CorpUsersListAction() {

	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public String go() throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CoreUser.class);
		detachedCriteria.add(Restrictions.eq("userRole", (short) 2));
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
		if (entno != null && !entno.equals("")) {
			detachedCriteria.add(Restrictions.like("entno", entno, MatchMode.START));
		}
		if (status != -1) {
			detachedCriteria.add(Restrictions.like("status", status));
		}

		detachedCriteria.addOrder(Order.desc("id"));
		this.page = basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}

	private short status = -1;
	private String userName;
	private String start;
	private String end;

	private String entno;

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

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

	public String getEntno() {
		return entno;
	}

	public void setEntno(String entno) {
		this.entno = entno;
	}



}
