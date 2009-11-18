package com.sxit.wait.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.BasicService;
import com.sxit.common.Globals;
import com.sxit.common.action.AbstractListAction;

/**
 * 
 * 显示我的待办任务
 * 
 * @author 华锋 Jul 6, 2009 1:22:59 AM
 * 
 */
public class ListWaitAction extends AbstractListAction {

	public String go() throws org.hibernate.HibernateException {
		BasicService basicService = (BasicService) Globals.getBean("basicService");

		DetachedCriteria dc = DetachedCriteria.forClass(com.sxit.models.wait.TwatWait.class);
		dc.add(Restrictions.in("userid", new Object[]{this.getLoginUser().getUserid(),0}));
		dc.add(Restrictions.eq("status", status));
		dc.addOrder(Order.desc("waitid"));
		this.page = basicService.findPageByCriteria(dc, pageSize, pageNo);

		return SUCCESS;
	}

	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
