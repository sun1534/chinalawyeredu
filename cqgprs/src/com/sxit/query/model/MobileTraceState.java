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
public class MobileTraceState {

	private static DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	

	private boolean hasexception;
	private String exception;
	private String sgsnid;
	private String mobile;
	private String random;
	private int status;// 1是追踪2是停止追踪

	private List<EricssonTraceLog> traceLogs=new ArrayList<EricssonTraceLog>();



	/**
	 * @return the hasexception
	 */
	public boolean getHasexception() {
		return hasexception;
	}




	/**
	 * @param hasexception the hasexception to set
	 */
	public void setHasexception(boolean hasexception) {
		this.hasexception = hasexception;
	}




	/**
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}




	/**
	 * @param exception the exception to set
	 */
	public void setException(String exception) {
		this.exception = exception;
	}




	/**
	 * @return the sgsnid
	 */
	public String getSgsnid() {
		return sgsnid;
	}




	/**
	 * @param sgsnid the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
	}




	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}




	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}




	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}




	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}




	public String getWebexception() {
		if (exception != null && !exception.equals(""))
			return exception.replaceAll("\n", "<br/>");
		return "";
	}




	/**
	 * @return the random
	 */
	public String getRandom() {
		return random;
	}




	/**
	 * @param random the random to set
	 */
	public void setRandom(String random) {
		this.random = random;
	}

	/**
	 * @return the traceLogs
	 */
	public List<EricssonTraceLog> getTraceLogs() {
		return traceLogs;
	}

	/**
	 * @param traceLogs the traceLogs to set
	 */
	public void addTraceLogs(EricssonTraceLog traceLog) {
		this.traceLogs.add(traceLog);
	}
}