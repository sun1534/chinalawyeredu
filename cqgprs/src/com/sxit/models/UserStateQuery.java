/**
 * UserStateQuery.java
 */
package com.sxit.models;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Timestamp;

/**
 * @author 华锋 Jun 16, 20104:15:10 PM
 * 
 */
public class UserStateQuery {

	private int id;
	private String msisdn;
	private String imsi;
	private String sgsnid;
	private String random;
	private String result;
	private Clob details;
	private int hasexception;
	private String exception;
	private String detailsStr;
	private String execuser;
	public void setDetailsStr(String detailsStr){
		this.detailsStr=detailsStr;
	}
	public String getDetailsStr() {
//		detailsStr=com.sxit.query.util.Clob2String.clob2String(details);
		return detailsStr;
	}

	private Timestamp querydate;

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
	 * @return the sgsnid
	 */
	public String getSgsnid() {
		return sgsnid;
	}

	/**
	 * @param sgsnid
	 *            the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
	}

	/**
	 * @return the random
	 */
	public String getRandom() {
		return random;
	}

	/**
	 * @param random
	 *            the random to set
	 */
	public void setRandom(String random) {
		this.random = random;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result.replaceAll("\r\n", "<br/>");
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the details
	 */
	public Clob getDetails() {
		return details;
	}
	public String getDetailsClob2Str() {
		return com.sxit.query.util.Clob2String.clob2String(details).replaceAll("\r\n", "<br/>");
	}
	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(Clob details) {
		this.details = details;
	}

	/**
	 * @return the querydate
	 */
	public Timestamp getQuerydate() {
		return querydate;
	}

	/**
	 * @param querydate
	 *            the querydate to set
	 */
	public void setQuerydate(Timestamp querydate) {
		this.querydate = querydate;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the hasexception
	 */
	public int getHasexception() {
		return hasexception;
	}
	/**
	 * @param hasexception the hasexception to set
	 */
	public void setHasexception(int hasexception) {
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
	 * @return the execuser
	 */
	public String getExecuser() {
		return execuser;
	}
	/**
	 * @param execuser the execuser to set
	 */
	public void setExecuser(String execuser) {
		this.execuser = execuser;
	}
}
