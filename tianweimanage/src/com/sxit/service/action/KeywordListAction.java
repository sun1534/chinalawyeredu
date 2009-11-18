/**
 * 
 */
package com.sxit.service.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.service.GlobalKeyword;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class KeywordListAction extends AbstractListAction {

	

	protected String go() throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(GlobalKeyword.class);
		

		dc.addOrder(Order.desc("id"));
		
		this.page=basicService.findPageByCriteria(dc, pageSize, pageNo);
		

		return SUCCESS;
	}
}
