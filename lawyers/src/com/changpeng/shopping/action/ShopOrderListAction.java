package com.changpeng.shopping.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.lawyers.service.SysGroupService;
import com.changpeng.models.Articles;
import com.changpeng.models.Lawyers;
import com.changpeng.models.ShopCart;
import com.changpeng.models.ShopOrder;
import com.changpeng.models.SysOperlog;
import com.changpeng.shopping.util.NumberUtil;

public class ShopOrderListAction extends AbstractListAction{
	
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}
	public ShopOrderListAction() {
		
	}
	
	private int viewtype;
	
	private Lawyers lawyer;
	public Lawyers getLawyer() {
		return lawyer;
	}
	
	private String orderno;

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	protected String go() throws Exception {	
		this.lawyer = this.getLoginUser();
		
		BasicService basicService = (BasicService) getBean("basicService");
		System.out.println("orderno is:"+orderno);
		
		int lawyerid = this.getLoginUser().getLawyerid();
		
		DetachedCriteria detachedCriteria =  DetachedCriteria.forClass(ShopOrder.class);
		if(lawyerid!=0){
			detachedCriteria.add(Restrictions.eq("lawyerid", lawyerid));
		}
		
		System.out.println("viewtype::"+viewtype);
		if(viewtype==1){
			detachedCriteria.add(Restrictions.eq("state", 1));
		}else if(viewtype==2){
			detachedCriteria.add(Restrictions.eq("state", 2));
		}
		//detachedCriteria.add(Restrictions.eq("state", 1));
		//detachedCriteria.add(Restrictions.eq("orderno", orderno));
		
		detachedCriteria.addOrder(Order.desc("tradedate"));
		detachedCriteria.addOrder(Order.desc("createdate"));
		this.page = basicService.findPageByCriteria(detachedCriteria, 3, pageNo);
		
		this.cartlist=basicService.find("from ShopCart where state!=0 and lawyerid="+lawyerid+" ");
		System.out.println("cartlist:::"+cartlist.size());
		//this.page = basicService.findByQuery(hql, pageSize, pageNo, totalCount)
		
		
		System.out.println(Integer.MAX_VALUE);
		System.out.println("到这里再来了");
		
		return SUCCESS;
	}
	
	private List cartlist;
	
	public List getCartlist() {
		return cartlist;
	}

	public void setCartlist(List cartlist) {
		this.cartlist = cartlist;
	}

	public int getViewtype() {
		return viewtype;
	}

	public void setViewtype(int viewtype) {
		this.viewtype = viewtype;
	}
	

	
}
