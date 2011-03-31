package com.xjdzjc.model;

import java.util.Date;

public class MemberMtStatus {
	public int id;                      
    public String mobile;  //手机号
    public String chepai;  //车牌          
    public int chepai_type;  //车牌类型
    public int sendCount;  //每次切换条数                 
    public Date sendDate;  //只记录发详细时间                   
    public int sendFlag;	//0表示详细，1表示统计，2表示提示
    public int mt_flag;    //状态，1表示已经发送，0未发送
    public Date last_mt_date;   //最后一次发送时间
    public Date dzjcdate;   //电子警察时间   判断是不是新的

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getChepai() {
		return chepai;
	}
	public void setChepai(String chepai) {
		this.chepai = chepai;
	}
	public int getSendCount() {
		return sendCount;
	}
	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public int getChepai_type() {
		return chepai_type;
	}
	public void setChepai_type(int chepaiType) {
		chepai_type = chepaiType;
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
	public int getMt_flag() {
		return mt_flag;
	}
	public void setMt_flag(int mtFlag) {
		mt_flag = mtFlag;
	}
	public Date getLast_mt_date() {
		return last_mt_date;
	}
	public void setLast_mt_date(Date lastMtDate) {
		last_mt_date = lastMtDate;
	}
	public Date getDzjcdate() {
		return dzjcdate;
	}
	public void setDzjcdate(Date dzjcdate) {
		this.dzjcdate = dzjcdate;
	}
	
	
   
}
