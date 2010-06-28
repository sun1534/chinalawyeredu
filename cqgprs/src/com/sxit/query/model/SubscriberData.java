/**
 * SubscriberData.java
 */
package com.sxit.query.model;

/**
 * @author 华锋 Jun 15, 20109:02:13 PM
 * 
 */
public class SubscriberData {
	// Subscriber Data
	// ----------------------------------------------------------------------
	// IMSI : 460008386013215
	// Mobile Subscriber ISDN No. : 8613608364826
	// IMEI : 351246005587200
	// Roaming Status : Home
	// HLR Address : 861399600000
	// Home PLMN APN Operator Id : mnc000.mcc460.gprs
	// Subscribed Teleservices : No SMS
	// Network Access Mode : Packet/Circuit Switched
	// Radio Access Technology : GSM
	// Mobility Management State : Ready
	// Paging Proceed Flag : Set
	// Routing Area [RAI] : 460-00-13093-0
	// Cell [CGI] : 460-00-13093-26511
	// P-TMSI : 3362787912 (#C8701248)
	// MSC/VLR Address : Not Gs connected
	// Location Confirmed in HLR : true
	// Data Confirmed by HLR : true
	// Charging Characteristics :
	// Charging Characteristics Profile :

	private String imsi;
	private String msisdn;
	private String imei;
	private String roaminStatus;
	private String hlrAddress;
	private String operatorId;
	private String subscribedTeleservices;
	private String mobilityState; // idle,ready,standby等
	private String rai;
	private String cellcgi;

	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi;
	}

	/**
	 * @param imsi
	 *            the imsi to set
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	/**
	 * @return the imei
	 */
	public String getImei() {
		if (imei != null) {

			if (imei.startsWith("860129"))
				return imei + "(OPhone)";
			else if (imei.startsWith("011713"))
				return imei + "(IPhone)";
			else
				return imei;
		}
		return imei;
	}

	/**
	 * @param imei
	 *            the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return the roaminStatus
	 */
	public String getRoaminStatus() {
		return roaminStatus;
	}

	/**
	 * @param roaminStatus
	 *            the roaminStatus to set
	 */
	public void setRoaminStatus(String roaminStatus) {
		this.roaminStatus = roaminStatus;
	}

	/**
	 * @return the hlrAddress
	 */
	public String getHlrAddress() {
		return hlrAddress;
	}

	/**
	 * @param hlrAddress
	 *            the hlrAddress to set
	 */
	public void setHlrAddress(String hlrAddress) {
		this.hlrAddress = hlrAddress;
	}

	/**
	 * @return the operatorId
	 */
	public String getOperatorId() {
		return operatorId;
	}

	/**
	 * @param operatorId
	 *            the operatorId to set
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * @return the subscribedTeleservices
	 */
	public String getSubscribedTeleservices() {
		return subscribedTeleservices;
	}

	/**
	 * @param subscribedTeleservices
	 *            the subscribedTeleservices to set
	 */
	public void setSubscribedTeleservices(String subscribedTeleservices) {
		this.subscribedTeleservices = subscribedTeleservices;
	}

	/**
	 * @return the mobilityState
	 */
	public String getMobilityState() {
		return mobilityState;
	}

	/**
	 * @param mobilityState
	 *            the mobilityState to set
	 */
	public void setMobilityState(String mobilityState) {
		this.mobilityState = mobilityState;
	}

	/**
	 * @return the rai
	 */
	public String getRai() {
		return rai;
	}

	/**
	 * @param rai
	 *            the rai to set
	 */
	public void setRai(String rai) {
		this.rai = rai;
	}

	/**
	 * @return the cellcgi
	 */
	public String getCellcgi() {
		return cellcgi;
	}

	/**
	 * @param cellcgi
	 *            the cellcgi to set
	 */
	public void setCellcgi(String cellcgi) {
		this.cellcgi = cellcgi;
	}

	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}

	/**
	 * @param msisdn
	 *            the msisdn to set
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

}
