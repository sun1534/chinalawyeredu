/**
 * ApiQueryResp.java
 */
package com.icbc;

/**
 * @author 华锋
 * May 31, 20105:05:38 PM
 *
 */
public class ApiQueryResp {

//	<tranSerialNum>指令序号<tranSerialNum/>
//	<tranStat>订单处理状态<tranStat/>
//	<bankRem>指令错误信息<bankRem/>
//	<amount>订单总金额<amount/>
//	<currType>支付币种<currType/>
//	<tranTime>返回通知日期时间<tranTime/>
//	<PayeeAcct>收款人账号<PayeeAcct/>
//	<PayeeName>收款人户名<PayeeName/>
//	<JoinFlag>校验联名标志<JoinFlag/>
//	<MerJoinFlag>商城联名标志<MerJoinFlag/>
//	<CustJoinFlag>客户联名标志<CustJoinFlag/>
//	<CustJoinNum>联名会员号<CustJoinNum/>
//	<SellCardNo>卖家收款卡号<SellCardNo/>
//	<SellAcctName>卖家收款人户名<SellAcctName/>
//	<CertID>商户签名证书id<CertID/>
	/**
	 * 指令序号
	 */
	private String tranSerialNum;
	/**
	 * 订单处理状态
	 */
	private String tranStat;
	/**
	 * 指令错误信息
	 */
	private String bankRem;
	/**
	 * 订单总金额
	 */
	private String amount;
	/**
	 * 支付币种
	 */
	private String currType;
	/**
	 * 返回通知日期时间
	 */
	private String tranTime;
	/**
	 * 收款人账号
	 */
//	private String payeeAcct;
	/**
	 * 收款人户名
	 */
//	private String payeeName;
	/**
	 * 校验联名标志
	 */
	private String joinFlag;
	/**
	 * 商城联名标志
	 */
	private String merJoinFlag;
	/**
	 * 客户联名标志
	 */
	private String custJoinFlag;
	/**
	 * 联名会员号
	 */
	private String custJoinNum;
	/**
	 * 卖家收款卡号
	 */
//	private String sellCardNo;
	/**
	 * 卖家收款人户名
	 */
//	private String sellAcctName;
	/**
	 * 商户签名证书id
	 */
	private String certID;
	/**
	 * @return the tranSerialNum
	 */
	public String getTranSerialNum() {
		return tranSerialNum;
	}
	/**
	 * @param tranSerialNum the tranSerialNum to set
	 */
	public void setTranSerialNum(String tranSerialNum) {
		this.tranSerialNum = tranSerialNum;
	}
	/**
	 * @return the tranStat
	 */
	public String getTranStat() {
		return tranStat;
	}
	/**
	 * @param tranStat the tranStat to set
	 */
	public void setTranStat(String tranStat) {
		this.tranStat = tranStat;
	}
	/**
	 * @return the bankRem
	 */
	public String getBankRem() {
		return bankRem;
	}
	/**
	 * @param bankRem the bankRem to set
	 */
	public void setBankRem(String bankRem) {
		this.bankRem = bankRem;
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the currType
	 */
	public String getCurrType() {
		return currType;
	}
	/**
	 * @param currType the currType to set
	 */
	public void setCurrType(String currType) {
		this.currType = currType;
	}
	/**
	 * @return the tranTime
	 */
	public String getTranTime() {
		return tranTime;
	}
	/**
	 * @param tranTime the tranTime to set
	 */
	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}
	/**
	 * @return the joinFlag
	 */
	public String getJoinFlag() {
		return joinFlag;
	}
	/**
	 * @param joinFlag the joinFlag to set
	 */
	public void setJoinFlag(String joinFlag) {
		this.joinFlag = joinFlag;
	}
	/**
	 * @return the merJoinFlag
	 */
	public String getMerJoinFlag() {
		return merJoinFlag;
	}
	/**
	 * @param merJoinFlag the merJoinFlag to set
	 */
	public void setMerJoinFlag(String merJoinFlag) {
		this.merJoinFlag = merJoinFlag;
	}
	/**
	 * @return the custJoinFlag
	 */
	public String getCustJoinFlag() {
		return custJoinFlag;
	}
	/**
	 * @param custJoinFlag the custJoinFlag to set
	 */
	public void setCustJoinFlag(String custJoinFlag) {
		this.custJoinFlag = custJoinFlag;
	}
	/**
	 * @return the custJoinNum
	 */
	public String getCustJoinNum() {
		return custJoinNum;
	}
	/**
	 * @param custJoinNum the custJoinNum to set
	 */
	public void setCustJoinNum(String custJoinNum) {
		this.custJoinNum = custJoinNum;
	}
	/**
	 * @return the certID
	 */
	public String getCertID() {
		return certID;
	}
	/**
	 * @param certID the certID to set
	 */
	public void setCertID(String certID) {
		this.certID = certID;
	}	
}