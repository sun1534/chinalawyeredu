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

public class ShopOrderCreateAction extends AbstractAction{
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}
	public ShopOrderCreateAction(){
		shopOrder=new ShopOrder();
	}
	
	private ShopOrder shopOrder;
	
	private List<Integer> chkcartid = new ArrayList<Integer>();

	public List<Integer> getChkcartid() {
		return chkcartid;
	}

	public void setChkcartid(List<Integer> chkcartid) {
		this.chkcartid = chkcartid;
	}
	
	private String orderno;
	
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	protected String go() throws Exception {
		System.out.println(chkcartid.size());
		BasicService basicService = (BasicService) getBean("basicService");
		
		//1、生成订单
		int lawyerid = this.getLoginUser().getLawyerid();
		orderno=NumberUtil.RandomOrderno();
		
		System.out.println(chkcartid == null && chkcartid.size()==0);
		if(chkcartid == null || chkcartid.size()==0){
			this.message = "购买失败，您没有选择任何课件！";
			this.nextPage = "../shopping/shopcartList.pl";
			return "message";
		}else{
			String ss="";
			Integer[] st=new Integer[chkcartid.size()];
			
			
			for(int i=0;chkcartid != null && i < chkcartid.size(); i++){
				System.out.println(chkcartid.get(i).toString());
				st[i]=Integer.parseInt(chkcartid.get(i).toString()); 
	
				
				ss+=chkcartid.get(i).toString();
				if(i!=(chkcartid.size()-1)){
					ss+=",";
				}			
			}
			System.out.println(st);
	
			
			String hql="select sum(price)  from ShopCart where lawyerid="+lawyerid+" and cartid in ("+ss+") ";
			System.out.println(hql);		
			String totalprice=String.valueOf(basicService.getSumByQuery(hql));
			System.out.println(totalprice);
			float totalmoney=0;
			if(totalprice!="" && totalprice!=null){
				totalmoney=Float.valueOf(totalprice);
			}
			System.out.println(totalmoney);
	
			
	
			
			
	//		DetachedCriteria detachedCriteria =  DetachedCriteria.forClass(ShopCart.class);
	//		if(lawyerid!=0){
	//			detachedCriteria.add(Restrictions.eq("lawyerid", lawyerid));
	//		}
	//		detachedCriteria.add(Restrictions.in("cartid", st));
	//		
	//		detachedCriteria.addOrder(Order.desc("createtime"));
	//		this.page = basicService.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, pageNo);
			
			
			//保存订单		
			
			shopOrder.setOrderno(orderno);
			shopOrder.setLawyerid(lawyerid);
			shopOrder.setState(1);
			shopOrder.setTotalmoney(totalmoney);
			shopOrder.setCreatedate(new java.sql.Timestamp(System.currentTimeMillis()));
			shopOrder.setTradedate(null);
			basicService.save(shopOrder);
			
			for(int i=0;chkcartid != null && i < chkcartid.size(); i++){
				ShopCart shopCart=(ShopCart) basicService.get(ShopCart.class, Integer.parseInt(chkcartid.get(i).toString()));
				shopCart.setCartid(Integer.parseInt(chkcartid.get(i).toString()));
				shopCart.setState(1);
				shopCart.setOrdernoid(orderno);
				basicService.update(shopCart);
				basicService.execute("update ShopFavorites set state=1,ordernoid="+orderno+"  where lessonid="+shopCart.getLessonid()+" and  lawyerid="+lawyerid+" ");
			}
			System.out.println(orderno);
			this.nextPage = "shopOrderCartList.pl?orderno="+ orderno;
			System.out.println("添加成功了；；；；；；；；");
			return SUCCESS;
		}
	}

	public ShopOrder getShopOrder() {
		return shopOrder;
	}

	public void setShopOrder(ShopOrder shopOrder) {
		this.shopOrder = shopOrder;
	}

}
