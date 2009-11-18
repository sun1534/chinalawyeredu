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
public class DaohangListAction extends AbstractListAction {

	

	protected String go() throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CoreShichuang.class);
		
		dc.add(Restrictions.eq("type","dh"));
		dc.addOrder(Order.desc("order"));
		
		this.page=basicService.findPageByCriteria(dc, pageSize, pageNo);
		

		return SUCCESS;
	}
}
