package com.changpeng.core.home;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CoreShichuang;
import com.changpeng.core.user.model.CoreUser;

public class ShichuangAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(ShichuangAction.class);
	
	CoreUser user;
	
	public ShichuangAction() {
		this.rightCode="PUBLIC";
	}

	@Override
	protected String go() throws Exception {

		
		BasicService service=(BasicService)this.getBean("basicService");
		DetachedCriteria dc=DetachedCriteria.forClass(CoreShichuang.class);
		dc.add(Restrictions.eq("type","sc"));
		dc.addOrder(Order.desc("id"));
		shichuangs=service.findByCriteria(dc);
		if(shichuangs==null)
		{
			shichuangs=new ArrayList();
		}
		
		
		return SUCCESS;

	}


	
	private List shichuangs;
	public List getShichuangs(){
		return shichuangs;
	}
	

}
