package com.changpeng.shopping.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessons;
import com.changpeng.models.ShopCart;

public class ShopOrderPaymentAction extends AbstractAction{
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
	
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");
		lesson = (Lessons) basicService.get(Lessons.class, lessonid);
		lawyers = this.getLoginUser();
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
		return SUCCESS;
	}

}
