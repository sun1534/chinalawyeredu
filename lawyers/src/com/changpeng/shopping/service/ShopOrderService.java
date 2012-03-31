package com.changpeng.shopping.service;

import java.sql.Timestamp;
import java.text.DateFormat;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.jifen.util.Jifenstatics;
import com.changpeng.models.ShopCart;
import com.changpeng.shopping.dao.ShopCartDAO;

public class ShopOrderService extends BasicService{
	private ShopCartDAO shopCartDAO;

	public ShopCartDAO getShopCartDAO() {
		return shopCartDAO;
	}

	public void setShopCartDAO(ShopCartDAO shopCartDAO) {
		this.shopCartDAO = shopCartDAO;
	}
	
	
	private static final DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
	
	public com.changpeng.common.PaginationSupport getPages(int lawyerid,int pageSize, int pageNo) {
		DetachedCriteria dc = DetachedCriteria.forClass(ShopCart.class);
		if(lawyerid!=0){
			dc.add(Restrictions.eq("lawyerid", lawyerid));
		}
		
		PaginationSupport page = shopCartDAO.findPageByCriteria(dc, pageSize, pageNo);
		return page;
	}
	
	
	public PaginationSupport getMyOrderLesson(String sql, int pageNo,int pageSize)
			throws ServiceException {
		try {			
				return shopCartDAO.getMyOrderLesson(sql, pageNo, pageSize);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}
	
}
