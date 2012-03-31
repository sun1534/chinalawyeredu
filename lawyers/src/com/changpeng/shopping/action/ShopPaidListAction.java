package com.changpeng.shopping.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.lawyers.service.SysGroupService;
import com.changpeng.models.Articles;
import com.changpeng.models.Lawyers;
import com.changpeng.models.ShopCart;

public class ShopPaidListAction extends AbstractListAction{
	
	
	public ShopPaidListAction() {
		
	}
	private Lawyers lawyer;
	public Lawyers getLawyer() {
		return lawyer;
	}
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}
	
	protected String go() throws Exception {
		this.lawyer = this.getLoginUser();
		int lawyerid = this.getLoginUser().getLawyerid();
		BasicService basicService = (BasicService) getBean("basicService");
		DetachedCriteria detachedCriteria =  DetachedCriteria.forClass(ShopCart.class);;
		if(lawyerid!=0){
			detachedCriteria.add(Restrictions.eq("lawyerid", lawyerid));
		}
		detachedCriteria.add(Restrictions.eq("state", 2));
		detachedCriteria.addOrder(Order.desc("createtime"));
		this.page = basicService.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, pageNo);
		return SUCCESS;
	}
	
}
