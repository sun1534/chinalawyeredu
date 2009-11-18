/**
 * 
 */
package com.sxit.info.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.info.TinfType;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class InfoTypeListAction extends AbstractListAction {

	

	protected String go() throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(TinfType.class);
		

		dc.addOrder(Order.desc("typeid"));
		
		this.page=basicService.findPageByCriteria(dc, pageSize, pageNo);
		

		return SUCCESS;
	}
}
