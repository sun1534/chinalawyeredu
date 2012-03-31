package com.changpeng.shopping.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessons;
import com.changpeng.models.ShopCart;
import com.changpeng.models.ShopFavorites;
import com.changpeng.shopping.service.ShopCartService;

public class ShopFavoritesCreateAction extends AbstractAction{
	private int lessonid;
	private Lessons lesson;
	private Lawyers lawyers;
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}
	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}
	public Lessons getLesson() {
		return lesson;
	}
	public Lawyers getLawyers() {
		if (lawyers == null)
			// lawyers = (Lawyers) this.get("lawyers");
			lawyers = this.getLoginUser();
		return this.lawyers;
	}
	
	private String typefont;
	
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");
		lesson = (Lessons) basicService.get(Lessons.class, lessonid);
		lawyers = this.getLoginUser();
		//查询该课件是否已经收藏
		ShopFavorites Shopfavorites=service.getShopFavorites(lawyers.getLawyerid(), lessonid);
		if(Shopfavorites==null){//没有收藏。则收藏			
			System.out.println(lawyers.getLawyername());
			ShopFavorites shopFavorites=new ShopFavorites();
			shopFavorites.setLessonid(lessonid);
			shopFavorites.setLawyerid(lawyers.getLawyerid());
			shopFavorites.setTitle(lesson.getTitle());
			shopFavorites.setTeachers(lesson.getTeachers());
			shopFavorites.setPrice(lesson.getPrice());
			shopFavorites.setXuefen(lesson.getXuefen());
			shopFavorites.setCreatedate(new java.sql.Timestamp(System.currentTimeMillis()));
			//int state=service.getCountByQuery("seelct count(*) from ");
			//service.execute("select state from ShopCart where lawyerid="+lawyers.getLawyerid()+" and lessonid="+lessonid+" ");
			
			//查询该课件是否已经在购物车中
			ShopCart shopcart=service.getShopCart(lawyers.getLawyerid(), lessonid);
			if(shopcart!=null){//该课件已存在购物车
				shopFavorites.setState(shopcart.getState());
				shopFavorites.setOrdernoid(shopcart.getOrdernoid());
			}else{//不存在
				shopFavorites.setState(0);
				shopFavorites.setOrdernoid(null);
			}
			//System.out.println("AAAAAAAAAA");
			
			service.save(shopFavorites);
			
			this.message = "收藏课件成功";
			this.nextPage = "../shopping/shopfavoritesList.pl";
			this.typefont="进入收藏夹";
			return "shopmessage";
		}else{//已经收藏
			
			//查询该课件是否已经在购物车中
			ShopCart shopcart=service.getShopCart(lawyers.getLawyerid(), lessonid);
			if(shopcart!=null){//该课件已存在购物车
				String hql="update ShopFavorites set state='"+shopcart.getState()+"',ordernoid='"+shopcart.getOrdernoid()+"'  where lessonid='"+lessonid+"' and  lawyerid='"+lawyers.getLawyerid()+"' ";
				System.out.println("hql:"+hql);
				service.execute(hql);
				System.out.println("修改成功");
			}
			
			this.message = "该课件已经在您的收藏夹！";
			this.nextPage = "../shopping/shopfavoritesList.pl";
			this.typefont="进入收藏夹";
			return "shopmessage";
		}

	}
	public String getTypefont() {
		return typefont;
	}
	public void setTypefont(String typefont) {
		this.typefont = typefont;
	}

}
