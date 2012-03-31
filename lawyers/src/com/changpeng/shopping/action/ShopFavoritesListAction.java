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
import com.changpeng.models.ShopFavorites;

public class ShopFavoritesListAction extends AbstractListAction{
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}
	
	public ShopFavoritesListAction() {
		
	}
	
	private int viewtype;
	
	private Lawyers lawyer;
	public Lawyers getLawyer() {
		return lawyer;
	}
	
	protected String go() throws Exception {
		int lawyerid = this.getLoginUser().getLawyerid();
		this.lawyer = this.getLoginUser();
		System.out.println(lawyerid);
		BasicService basicService = (BasicService) getBean("basicService");
		DetachedCriteria detachedCriteria =  DetachedCriteria.forClass(ShopFavorites.class);;
		if(lawyerid!=0){
			detachedCriteria.add(Restrictions.eq("lawyerid", lawyerid));
		}
		
		System.out.println("viewtype::"+viewtype);
		if(viewtype==1){
			detachedCriteria.add(Restrictions.ne("state", 2));
			//detachedCriteria.add(Restrictions.eq("state",1));
		}else if(viewtype==2){
			detachedCriteria.add(Restrictions.eq("state", 2));
		}
		detachedCriteria.addOrder(Order.desc("createdate"));
		this.page = basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		return SUCCESS;
	}

	public int getViewtype() {
		return viewtype;
	}

	public void setViewtype(int viewtype) {
		this.viewtype = viewtype;
	}
	
}
