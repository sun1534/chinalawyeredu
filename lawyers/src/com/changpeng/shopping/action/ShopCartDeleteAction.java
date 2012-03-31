package com.changpeng.shopping.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Articles;
import com.changpeng.models.Lawyers;
import com.changpeng.models.LawyersOfficeChangeApply;
import com.changpeng.models.Lessons;
import com.changpeng.models.ShopCart;

public class ShopCartDeleteAction extends AbstractAction{
	private int cartid;
	
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}



	public int getCartid() {
		return cartid;
	}



	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	
	
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		ShopCart shopCart=(ShopCart)basicService.get(ShopCart.class, cartid);
		
		basicService.delete(shopCart);
		//this.message="删除成功";
		//this.nextPage="shopcartList.pl";

		return SUCCESS;
	}

}
