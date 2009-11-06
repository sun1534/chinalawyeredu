package com.changpeng.core.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.core.model.CoreProduct;
import com.changpeng.core.model.TinfInfo;

public class InfoService  extends BasicService {
	/**
	 * 产品列表
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginationSupport getProductlist(int pageSize,int pageNo,int producttype){
		DetachedCriteria dc = DetachedCriteria.forClass(CoreProduct.class, "u");
		dc.add(Restrictions.eq("u.status", (short)0));
		if(producttype==1||producttype==2){
			dc.add(Restrictions.eq("u.producttype", producttype));
		}
		return this.findPageOnPageNo(dc, pageSize, pageNo);
	}
	public PaginationSupport searchProduct(int pageSize,int pageNo,int producttype,String name){
		DetachedCriteria dc = DetachedCriteria.forClass(CoreProduct.class, "u");
		dc.add(Restrictions.eq("u.status", (short)0));
		dc.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
		if(producttype==1||producttype==2){
			dc.add(Restrictions.eq("u.producttype", producttype));
		}
		return this.findPageOnPageNo(dc, pageSize, pageNo);
	}
	
	
	/**
	 * 得到首页要显示的新闻
	 * @param provinceid
	 * @return
	 */
	public List getNewsindex(){
		DetachedCriteria dc = DetachedCriteria.forClass(TinfInfo.class, "u");
		dc.add(Restrictions.eq("typeid", 3));
		dc.add(Restrictions.eq("statusid", 0));
		dc.addOrder(Order.asc("infoid"));
		return this.findByCriteria(dc, 10);
	}
	
	
	/**
	 * 得到首页要显示的产品
	 * @param provinceid
	 * @return
	 */
	public List getProductindex(){
		DetachedCriteria dc = DetachedCriteria.forClass(CoreProduct.class, "u");
		dc.add(Restrictions.eq("status", (short)0));
//		dc.addOrder(Order.desc("id"));
		return this.findByCriteria(dc, 10);
	}
	
	
	/**
	 * 得到首页要显示的活动
	 * @param provinceid
	 * @return
	 */
	public List getHuodongindex(){
		DetachedCriteria dc = DetachedCriteria.forClass(TinfInfo.class, "u");
		dc.add(Restrictions.eq("statusid", 0));
		dc.add(Restrictions.eq("typeid", 2));
		return this.findByCriteria(dc, 10);
	}
	
	/**
	 * 得到首页要显示的活动
	 * @param provinceid
	 * @return
	 */
	public List getShihuiindex(){
		DetachedCriteria dc = DetachedCriteria.forClass(TinfInfo.class, "u");
		dc.add(Restrictions.eq("statusid", 0));
		dc.add(Restrictions.eq("typeid", 4));
		dc.addOrder(Order.asc("infoid"));
		return this.findByCriteria(dc, 10);
	}
	/**
	 * 得到首页要显示的热点信息
	 * @param provinceid
	 * @return
	 */
	public List getRedianindex(){
		DetachedCriteria dc = DetachedCriteria.forClass(TinfInfo.class, "u");
		dc.add(Restrictions.eq("statusid", 0));
		dc.add(Restrictions.eq("typeid", 1));
		return this.findByCriteria(dc, 30);
	}
	
	
	/**
	 * 新闻公告等。。列表
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginationSupport getInfolist(int pageSize,int pageNo,int type){
		DetachedCriteria dc = DetachedCriteria.forClass(TinfInfo.class, "u");
		dc.add(Restrictions.eq("u.statusid", 0));
		dc.add(Restrictions.eq("u.typeid", type));
		
		return this.findPageOnPageNo(dc, pageSize, pageNo);
	}
}

