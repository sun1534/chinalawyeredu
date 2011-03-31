package com.xjdzjc.model;

import java.util.Date;

public class MtBean {

	public int id;
	public String city;
	public String service_id;
	public String chepai;
	public String chepai_type;
	public String dst_id;
	public String src_id;
	public String content;
	public String gateway_id;
	public String handler;

	/**
	 * 时间
	 */
	public Date date;

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
	 * 
	 * @return
	 */
	public Date sendDate;

	/**
	 * 用户发送标志 0表示详细 1表示简单统计 3表示无违章
	 * 
	 * @return
	 */
	public int sendFlag;

	/**
	 * 发送条数 是指在发送详细与简单统计之间的切换条数
	 * 
	 * @return
	 */
	public int sendCount;

	/**
	 * 短信发送标志位
	 * 
	 * @return
	 */
	public int memberflag;

	public String banner;

	public int mt_flag; // 状态，0:下发,1:不下发

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String serviceId) {
		service_id = serviceId;
	}

	public String getDst_id() {
		return dst_id;
	}

	public void setDst_id(String dstId) {
		dst_id = dstId;
	}

	public String getSrc_id() {
		return src_id;
	}

	public void setSrc_id(String srcId) {
		src_id = srcId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGateway_id() {
		return gateway_id;
	}

	public void setGateway_id(String gatewayId) {
		gateway_id = gatewayId;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getChepai() {
		return chepai;
	}

	public void setChepai(String chepai) {
		this.chepai = chepai;
	}

	public String getChepai_type() {
		return chepai_type;
	}

	public void setChepai_type(String chepaiType) {
		chepai_type = chepaiType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public int getMemberflag() {
		return memberflag;
	}

	public void setMemberflag(int memberflag) {
		this.memberflag = memberflag;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public int getMt_flag() {
		return mt_flag;
	}

	public void setMt_flag(int mtFlag) {
		mt_flag = mtFlag;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
