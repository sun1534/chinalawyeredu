/**
 * 
 */
package com.sxit.service.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.service.CorePartner;

/**
 * 
 * 合作伙伴列表
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class PartnerListAction extends AbstractListAction {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected String go() throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(CorePartner.class);
		if(name!=null&&!"".equals(name)){
			dc.add(Restrictions.like("name",name,MatchMode.ANYWHERE));
		}

		dc.addOrder(Order.desc("id"));
		
		this.page=basicService.findPageByCriteria(dc, pageSize, pageNo);
		

		return SUCCESS;
	}
}
