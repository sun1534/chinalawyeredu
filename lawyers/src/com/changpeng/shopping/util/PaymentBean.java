/**
 * 
 */
package com.changpeng.shopping.util;

/**
 * @author Jackie.Gao
 *
 */
public class PaymentBean {

	private String MerId;
	private String OrdId;
	private String TransAmt;
	private String CuryId;
	private String TransDate;
	private String TransType;
	private String Version;
	private String BgRetUrl;
	private String PageRetUrl;
	private String GateId;
	private String Status;
	private String Priv1;
	private String ClientIP;
	private String ChkValue;
	
	private String TransTime;
	private String CountryId;
	private String TimeZone;
	private String DSTFlag;
	private String ExtFlag;	
	private String Priv2;	
	
	public PaymentBean(){
		
	}
	
	public String toString(){
		return new StringBuffer("{MerId=").append(MerId)
		.append(", OrdId=").append(OrdId)
		.append(", TransAmt=").append(TransAmt)
		.append(", CuryId=").append(CuryId)
		.append(", TransDate=").append(TransDate)
		.append(", TransType=").append(TransType)
		.append(", Version=").append(Version)
		.append(", BgRetUrl=").append(BgRetUrl)
		.append(", PageRetUrl=").append(PageRetUrl)
		.append(", GateId=").append(GateId)
		.append(", Status=").append(Status)
		.append(", Priv1=").append(Priv1)
		.append(", ClientIP=").append(ClientIP)
		.append(", ChkValue=").append(ChkValue)
		.append(", TransTime=").append(TransTime)
		.append(", CountryId=").append(CountryId)
		.append(", TimeZone=").append(TimeZone)
		.append(", DSTFlag=").append(DSTFlag)
		.append(", ExtFlag=").append(ExtFlag)
		.append(", Priv2=").append(Priv2)
		.append("}").toString();
	}
	
	public String getMerId() {
		return MerId;
	}
	public void setMerId(String merId) {
		MerId = merId;
	}
	public String getOrdId() {
		return OrdId;
	}
	public void setOrdId(String ordId) {
		OrdId = ordId;
	}
	public String getTransAmt() {
		return TransAmt;
	}
	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}
	public String getCuryId() {
		return CuryId;
	}
	public void setCuryId(String curyId) {
		CuryId = curyId;
	}
	public String getTransDate() {
		return TransDate;
	}
	public void setTransDate(String transDate) {
		TransDate = transDate;
	}
	public String getTransType() {
		return TransType;
	}
	public void setTransType(String transType) {
		TransType = transType;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getBgRetUrl() {
		return BgRetUrl;
	}
	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}
	public String getPageRetUrl() {
		return PageRetUrl;
	}
	public void setPageRetUrl(String pageRetUrl) {
		PageRetUrl = pageRetUrl;
	}
	public String getGateId() {
		return GateId;
	}
	public void setGateId(String gateId) {
		GateId = gateId;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPriv1() {
		return Priv1;
	}
	public void setPriv1(String priv1) {
		Priv1 = priv1;
	}
	public String getPriv2() {
		return Priv2;
	}
	public void setPriv2(String priv2) {
		Priv2 = priv2;
	}
	public String getClientIP() {
		return ClientIP;
	}
	public void setClientIP(String clientIP) {
		ClientIP = clientIP;
	}
	public String getChkValue() {
		return ChkValue;
	}
	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}
	public String getTransTime() {
		return TransTime;
	}
	public void setTransTime(String transTime) {
		TransTime = transTime;
	}
	public String getCountryId() {
		return CountryId;
	}
	public void setCountryId(String countryId) {
		CountryId = countryId;
	}
	public String getTimeZone() {
		return TimeZone;
	}
	public void setTimeZone(String timeZone) {
		TimeZone = timeZone;
	}
	public String getDSTFlag() {
		return DSTFlag;
	}
	public void setDSTFlag(String dSTFlag) {
		DSTFlag = dSTFlag;
	}
	public String getExtFlag() {
		return ExtFlag;
	}
	public void setExtFlag(String extFlag) {
		ExtFlag = extFlag;
	}

}
