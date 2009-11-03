package com.changpeng.diaocha.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Diaocha;

public class DiaochaListAction extends AbstractListAction{

	public DiaochaListAction(){
	
		this.rightCode="diaocha";
	}
	@Override
	protected String go() throws Exception {

		BasicService service=(BasicService) this.getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Diaocha.class);
	
		detachedCriteria.addOrder(Order.desc("diaochaid"));

		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}

}
