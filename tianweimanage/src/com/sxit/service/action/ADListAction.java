/**
 * 
 */
package com.sxit.service.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.service.CoreAdvtisment;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class ADListAction extends AbstractListAction {



	protected String go() throws Exception {
		this.pageSize=4;
		DetachedCriteria dc = DetachedCriteria.forClass(CoreAdvtisment.class);

		dc.addOrder(Order.desc("pos"));
		
		this.page=basicService.findPageByCriteria(dc, pageSize, pageNo);
		

		return SUCCESS;
	}
}
