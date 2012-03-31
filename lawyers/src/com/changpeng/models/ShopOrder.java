package com.changpeng.models;

import java.sql.Timestamp;

public class ShopOrder implements java.io.Serializable{
	private String orderno;
	private int lawyerid;
	private int state;
	private Float totalmoney=0f;
	private Timestamp createdate;
	private Timestamp tradedate;
	
	
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public int getLawyerid() {
		return lawyerid;
	}
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Float getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(Float totalmoney) {
		this.totalmoney = totalmoney;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public Timestamp getTradedate() {
		return tradedate;
	}
	public void setTradedate(Timestamp tradedate) {
		this.tradedate = tradedate;
	}
	
	
	
}	
