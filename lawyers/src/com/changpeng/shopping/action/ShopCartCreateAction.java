package com.changpeng.shopping.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessons;
import com.changpeng.models.ShopCart;

public class ShopCartCreateAction extends AbstractAction{
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
	public String getTypefont() {
		return typefont;
	}
	public void setTypefont(String typefont) {
		this.typefont = typefont;
	}
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");
		lawyers = this.getLoginUser();
		
		System.out.println("LLLLLLLL:"+lawyers.getLawyerid());
		System.out.println("LLLLLLLL:"+lessonid);
		ShopCart shopcart1=service.getShopCart(lawyers.getLawyerid(), lessonid);
		if(shopcart1==null){
			lesson = (Lessons) basicService.get(Lessons.class, lessonid);
			
			System.out.println(lawyers.getLawyername());
			ShopCart shopcart=new ShopCart();
			shopcart.setLessonid(lessonid);
			shopcart.setLawyerid(lawyers.getLawyerid());
			shopcart.setTitle(lesson.getTitle());
			shopcart.setTeachers(lesson.getTeachers());
			shopcart.setPrice(lesson.getPrice());
			shopcart.setXuefen(lesson.getXuefen());
			shopcart.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			shopcart.setState(0);
			shopcart.setOrdernoid("");
			service.save(shopcart);
			
			this.message = "课件加入购物车成功！";
			this.nextPage = "../shopping/shopcartList.pl";
			this.typefont="进入购物车";
			return "shopmessage";
		}else{
			System.out.println(shopcart1.getState());
			if(shopcart1.getState()==0){
				this.message = "该课件已经在您的购物车！";
				this.nextPage = "../shopping/shopcartList.pl";
				this.typefont="进入我的购物车";
				
			}else if(shopcart1.getState()==1){
				
				this.message = "该课件已经在您的订单里面！";
				this.nextPage = "../shopping/shopOrderList.pl";
				this.typefont="进入我的订单";
				System.out.println("AAA");
					
			}
			else if(shopcart1.getState()==2){				
				this.message = "该课件您已经购买！";
				this.nextPage = "../shopping/shoppaidList.pl";
				this.typefont="进入我购买的课件";
				System.out.println("AAA");
					
			}
			return "shopmessage";
		}
		//return SUCCESS;		
	}

}
