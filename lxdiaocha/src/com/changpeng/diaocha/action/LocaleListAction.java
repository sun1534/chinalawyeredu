package com.changpeng.diaocha.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.*;

public class LocaleListAction extends AbstractListAction{

	public LocaleListAction(){	
//		this.rightCode="diaochareply";
	}
	@Override
	protected String go() throws Exception {
	
		BasicService service=(BasicService) this.getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Diaocha.class);
		detachedCriteria.add(Restrictions.eq("state", 2));
		
		detachedCriteria.addOrder(Order.desc("diaochaid"));

		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}
	

}
