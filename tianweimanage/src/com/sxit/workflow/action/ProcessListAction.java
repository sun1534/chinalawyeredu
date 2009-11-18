package com.sxit.workflow.action;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.workflow.TwflProcess;

/**
 * 
 * @author 华锋 Jul 9, 2009 3:46:18 PM
 * 
 */

public class ProcessListAction extends AbstractListAction {

	public String go() throws Exception {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TwflProcess.class);
		detachedCriteria.addOrder(Order.desc("processid"));
		this.page = basicService.findPageByCriteria(detachedCriteria, this.pageSize, this.pageNo);
		return SUCCESS;
	}
}
