/**
 * 
 */
package com.sxit.service.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.service.CoreShichuang;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class ShichuangListAction extends AbstractListAction {

	

	protected String go() throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CoreShichuang.class);
		
		dc.add(Restrictions.eq("type","sc"));
		dc.addOrder(Order.desc("id"));
		
		this.page=basicService.findPageByCriteria(dc, pageSize, pageNo);
		

		return SUCCESS;
	}
}
