package com.sxit.workflow.action;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.workflow.TwflBusiness;

/**
 * 业务列表
 * 
 * @author 华锋 Jul 9, 2009 3:44:50 PM
 * 
 */

public class BusinessListAction extends AbstractListAction {

	public BusinessListAction() {

	}

	public String go() throws Exception {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TwflBusiness.class);
		detachedCriteria.addOrder(Order.desc("businessid"));
		this.page = basicService.findPageByCriteria(detachedCriteria, this.pageSize, this.pageNo);
		return SUCCESS;
	}

}
