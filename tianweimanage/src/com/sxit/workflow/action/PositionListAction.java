package com.sxit.workflow.action;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.workflow.TwflPosition;

/**
 * 业务列表
 * 
 * @author 华锋 Jul 9, 2009 3:44:50 PM
 * 
 */

public class PositionListAction extends AbstractListAction {

	public PositionListAction() {

	}

	public String go() throws Exception {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TwflPosition.class);
		detachedCriteria.addOrder(Order.desc("positionid"));
		this.page = basicService.findPageByCriteria(detachedCriteria, this.pageSize, this.pageNo);
		return SUCCESS;
	}

}
