package com.xjdzjc.model;

import java.util.Date;



public class Dzjc {

	public boolean weizhang;

	public Dzjc() {
		this.setWeizhang(false);
	}

	public int id;

	/**
	 * 车牌
	 */
	public String chepai;
	
	public int chepai_type;
	/**
	 * 时间
	 */
	public Date date;
	/**
	 * 手机号
	 */
	public String mobile;
	/**
	 * 违章地点
	 */
	public String address;
	/**
	 * 所扣分数
	 */
	public String score;
	/**
	 * 罚款金额
	 */
	public String money;
	/**
	 * 
	 * 违章原因
	 */
	public String cause;
	/**
	 * 处理地点
	 */
	public String dealaddress;
	/**
	 * 车牌违章条数
	 */
	public int count;
	
	/**
	 * 用户发送时间
	 * @return
	 */
	public Date sendDate;
	
	/**
	 * 用户发送标志
	 * 0表示详细 1表示简单统计
	 * @return
	 */
	public int sendFlag;  
	
	/**
	 * 发送条数  是指在发送详细与简单统计之间的切换条数
	 * @return
	 */
	public int sendCount;
	
	/**
	 * 用户业务代码
	 */
	public String service_id;
	
	/**
	 * 短信发送标志位
	 * @return
	 */
	public int memberflag;
	
	public String banner ;
	
	 public int mt_flag;    //状态，0:下发,1:不下发
	
	

	public String getBanner() {
		if(this.banner==null||this.banner.equals(""))
		{
			return "尊敬的【12580移动警务】会员";
		}
		return banner;
	}

	public void setBanner(String banner) {
		if(banner!=null&&!banner.equals(""))
		{
			if(!banner.endsWith(":")&&!banner.endsWith("："))
			  this.banner=banner+":";
			else
			 this.banner=banner;
		}else {
			this.banner="尊敬的【12580移动警务】会员：";
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChepai() {
		return chepai;
	}

	public void setChepai(String chepai) {
		this.chepai = chepai;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getDealaddress() {
		return dealaddress;
	}

	public void setDealaddress(String dealaddress) {
		this.dealaddress = dealaddress;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isWeizhang() {
		return weizhang;
	}

	public void setWeizhang(boolean weizhang) {
		this.weizhang = weizhang;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public int getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(int sendFlag) {
		this.sendFlag = sendFlag;
	}

	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String serviceId) {
		service_id = serviceId;
	}

	public int getMemberflag() {
		return memberflag;
	}

	public void setMemberflag(int memberflag) {
		this.memberflag = memberflag;
	}

	public int getMt_flag() {
		return mt_flag;
	}

	public int getChepai_type() {
		return chepai_type;
	}

	public void setChepai_type(int chepaiType) {
		chepai_type = chepaiType;
	}
	
	

}
