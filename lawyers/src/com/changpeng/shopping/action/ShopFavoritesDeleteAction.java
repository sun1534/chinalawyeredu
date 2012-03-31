package com.changpeng.shopping.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Articles;
import com.changpeng.models.Lawyers;
import com.changpeng.models.LawyersOfficeChangeApply;
import com.changpeng.models.Lessons;
import com.changpeng.models.ShopCart;
import com.changpeng.models.ShopFavorites;

public class ShopFavoritesDeleteAction extends AbstractAction{
	private int favoritesid;
	
	public int getFavoritesid() {
		return favoritesid;
	}
	public void setFavoritesid(int favoritesid) {
		this.favoritesid = favoritesid;
	}
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}


	protected String go() throws Exception {
		// TODO Auto-generated method stub

		ShopFavorites shopFavorites=(ShopFavorites)basicService.get(ShopFavorites.class, favoritesid);
		
		basicService.delete(shopFavorites);
		//this.message="删除成功";
		//this.nextPage="shopcartList.pl";

		return SUCCESS;
	}

}
