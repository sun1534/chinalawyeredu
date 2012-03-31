package com.changpeng.shopping.service;

import java.sql.Timestamp;
import java.text.DateFormat;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.models.ShopCart;
import com.changpeng.models.ShopFavorites;
import com.changpeng.shopping.dao.ShopCartDAO;
import com.changpeng.shopping.dao.ShopFavoritesDAO;

public class ShopFavoritesService extends BasicService{
	private ShopFavoritesDAO shopFavoritesDAO;

	public ShopFavoritesDAO getShopFavoritesDAO() {
		return shopFavoritesDAO;
	}
	public void setShopFavoritesDAO(ShopFavoritesDAO shopFavoritesDAO) {
		this.shopFavoritesDAO = shopFavoritesDAO;
	}
	
	
	private static final DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
	public com.changpeng.common.PaginationSupport getPages(int lawyerid,int pageSize, int pageNo) {
		DetachedCriteria dc = DetachedCriteria.forClass(ShopFavorites.class);
		if(lawyerid!=0){
			dc.add(Restrictions.eq("lawyerid", lawyerid));
		}
		
		PaginationSupport page = shopFavoritesDAO.findPageByCriteria(dc, pageSize, pageNo);
		return page;
	}

	
}
