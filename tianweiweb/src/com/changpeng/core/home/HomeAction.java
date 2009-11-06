package com.changpeng.core.home;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CoreShichuang;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;

public class HomeAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(HomeAction.class);
	
	CoreUser user;
	
	public HomeAction() {
		this.rightCode="PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		if(currentUserid!=0){
			UserService service = (UserService) this.getBean("userService");
			
			user = service.getUserById(currentUserid);
		}
		
		BasicService service=(BasicService)this.getBean("basicService");
		DetachedCriteria dc=DetachedCriteria.forClass(CoreShichuang.class);
		dc.add(Restrictions.eq("type","dh"));
		dc.addOrder(Order.asc("order"));
		shichuangs=service.findByCriteria(dc);
		if(shichuangs==null)
		{
			shichuangs=new ArrayList();
		}
		
		dc=DetachedCriteria.forClass(CoreShichuang.class);
		dc.add(Restrictions.eq("type","sc"));
		dc.addOrder(Order.desc("id"));
		daohangs=service.findByCriteria(dc);
		if(daohangs==null)
		{
			daohangs=new ArrayList();
		}
		
		dc=DetachedCriteria.forClass(com.changpeng.core.model.TinfInfo.class);
		dc.add(Restrictions.eq("typeid",2));
		Date now=new java.util.Date();
		dc.add(Restrictions.ge("end", now));
		dc.add(Restrictions.le("start", now));
		
		
		dc.addOrder(Order.desc("infoid"));
		huodongs=service.findByCriteria(dc,4);
		if(huodongs==null)
		{
			huodongs=new ArrayList();
		}
		
		
		return SUCCESS;

	}

	public CoreUser getUser() {
		return user;
	}
	
	private List shichuangs;
	public List getShichuangs(){
		return shichuangs;
	}
	private List huodongs;
	public List getHuodongs(){
		return huodongs;
	}
	private List daohangs;

	public List getDaohangs() {
		return daohangs;
	}
	
}
