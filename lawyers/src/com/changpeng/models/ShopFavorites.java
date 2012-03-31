package com.changpeng.models;

import java.sql.Timestamp;

public class ShopFavorites implements java.io.Serializable{
	private int favoritesid;
	private int lawyerid;
	private int lessonid;
	private String title;
	private String teachers;
	private Float xuefen=0f;
	private Float price=0f;
	private Timestamp createdate;
	private int state;
	private String ordernoid;
	public String getOrdernoid() {
		return ordernoid;
	}
	public void setOrdernoid(String ordernoid) {
		this.ordernoid = ordernoid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getLawyerid() {
		return lawyerid;
	}
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

	public int getFavoritesid() {
		return favoritesid;
	}
	public void setFavoritesid(int favoritesid) {
		this.favoritesid = favoritesid;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
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

}	
