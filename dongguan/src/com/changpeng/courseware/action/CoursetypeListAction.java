package com.changpeng.courseware.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Coursetype;
public class CoursetypeListAction extends AbstractListAction{
	public CoursetypeListAction(){
		this.rightCode="courseware";
	}
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService) this.getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Coursetype.class);
	
		detachedCriteria.addOrder(Order.desc("typeid"));

		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}

}
