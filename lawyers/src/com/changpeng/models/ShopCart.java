package com.changpeng.models;

import java.sql.Timestamp;

public class ShopCart implements java.io.Serializable{
	private int cartid;
	private int lawyerid;
	private int lessonid;
	private String title;
	private String teachers;
	private Float xuefen=0f;
	private Float price=0f;
	private Timestamp createtime;
	private int state;
	private String ordernoid;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public String getOrdernoid() {
		return ordernoid;
	}
	public void setOrdernoid(String ordernoid) {
		this.ordernoid = ordernoid;
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public int getLessonid() {
		return lessonid;
	}
	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}
	public String getTitle() {
		return title;
	}
	
	public String getTitleTrim() {
		if(this.title.length()>18)
			return this.title.substring(0,18)+"...";
			return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTeachers() {
		return teachers;
	}
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}
	public Float getXuefen() {
		return xuefen;
	}
	public void setXuefen(Float xuefen) {
		this.xuefen = xuefen;
	}

	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public int getLawyerid() {
		return lawyerid;
	}
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}
}	
