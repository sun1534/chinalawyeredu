/**
 * MobileApnState.java
 */
package com.sxit.query.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sxit.netquality.models.Cell;
import com.sxit.query.util.RauUtil;

/**
 * @author 华锋 Jun 15, 20107:52:40 PM
 * 
 */
public class EricssonTraceLog {

	private static DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String theTime;
	private String eventName;
	private String eventDetails;
	private String causeValue;
	private String imsi;
	private String msisdn;
	private String rai;
	private String cellid;
	private String nsapi;
	private String accessType;
	
	public String getCellname(){
		if(cellid==null||cellid.equals(""))
			return "";
		
		String cellidtrim=cellid.replace("460-0-", "");
		
		Cell cell = com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(cellidtrim);
		if (cell == null)
			return cellid + "<br/>(无对应小区信息)";
		else
			return cellid+"<br/>("+cell.getCellname()+")";
	}
	
	/**
	 * @return the theTime
	 */
	public String getTheTime() {
		return theTime;
	}
	/**
	 * @param theTime the theTime to set
	 */
	public void setTheTime(String theTime) {
		this.theTime = theTime;
	}
	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}
	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}
	/**
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}
	/**
	 * @return the causeValue
	 */
	public String getCauseValue() {
		return causeValue;
	}
	/**
	 * @param causeValue the causeValue to set
	 */
	public void setCauseValue(String causeValue) {
		this.causeValue = causeValue;
	}
	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi;
	}
	/**
	 * @param imsi the imsi to set
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}
	/**
	 * @param msisdn the msisdn to set
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	/**
	 * @return the rai
	 */
	public String getRai() {
		return rai;
	}
	/**
	 * @param rai the rai to set
	 */
	public void setRai(String rai) {
		this.rai = rai;
	}
	/**
	 * @return the cellid
	 */
	public String getCellid() {
		return cellid;
	}
	/**
	 * @param cellid the cellid to set
	 */
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}
	/**
	 * @return the nsapi
	 */
	public String getNsapi() {
		return nsapi;
	}
	/**
	 * @param nsapi the nsapi to set
	 */
	public void setNsapi(String nsapi) {
		this.nsapi = nsapi;
	}
	/**
	 * @return the accessType
	 */
	public String getAccessType() {
		return accessType;
	}
	/**
	 * @param accessType the accessType to set
	 */
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	
	
}
