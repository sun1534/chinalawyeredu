package com.changpeng.shopping.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessons;
import com.changpeng.models.ShopCart;
import com.changpeng.models.ShopOrder;
import com.changpeng.shopping.util.NumberUtil;

public class ShopOrderDeleteAction extends AbstractAction{
	

	
	
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}
	private String orderno;
	
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	protected String go() throws Exception {
		BasicService basicService = (BasicService) getBean("basicService");
		try {
			//同时修改该订单的课件为0
			basicService.execute("update ShopCart set state=0 where ordernoid="+orderno+" ");
			//删除订单
			basicService.delete(ShopOrder.class, orderno);
			
			System.out.println("AAAA");
			this.message = "订单删除成功！"; //这里删除律师，都记录下日志吧
			this.nextPage = "../shopping/shopOrderList.pl";
			return "message";
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return SUCCESS;
		
	
	
	}





}
