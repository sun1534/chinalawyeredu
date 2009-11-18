/**
 * 
 */
package com.sxit.info.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.info.TinfInfo;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class NewsListAction extends AbstractListAction {

	private int thetype;

	public int getThetype() {
		return thetype;
	}

	public void setThetype(int thetype) {
		this.thetype = thetype;
	}

	protected String go() throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(TinfInfo.class);
		if (thetype != 0)
			dc.add(Restrictions.eq("typeid", thetype));

		dc.addOrder(Order.desc("infoid"));
		
		this.page=basicService.findPageByCriteria(dc, pageSize, pageNo);
		

		return SUCCESS;
	}
}
