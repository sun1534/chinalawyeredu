/**
 * 
 */
package com.sxit.memdevice.command;

/**
 * @author 华锋
 * Nov 4, 2009-10:03:12 PM
 *
 */
public class ErrorApnsCdr {

	private long lac;
	private String cellid;
	private String sgsnid;
	private String reqapnni;
	private String subapnni;
	private String imsi;
	private String msisdn;
	private int nettype;
	private String errorcode;
	private int opentime;
	private int recordtime;
	private String usedapn;
	private String ggsnaddr;
	private String pdpaddr;
	private String nsei;
	/**
	 * @return the nsei
	 */
	public String getNsei() {
		return nsei;
	}
	/**
	 * @param nsei the nsei to set
	 */
	public void setNsei(String nsei) {
		this.nsei = nsei;
	}
	/**
	 * @return the ggsnaddr
	 */
	public String getGgsnaddr() {
		return ggsnaddr;
	}
	/**
	 * @param ggsnaddr the ggsnaddr to set
	 */
	public void setGgsnaddr(String ggsnaddr) {
		this.ggsnaddr = ggsnaddr;
	}
	/**
	 * @return the pdpaddr
	 */
	public String getPdpaddr() {
		return pdpaddr;
	}
	/**
	 * @param pdpaddr the pdpaddr to set
	 */
	public void setPdpaddr(String pdpaddr) {
		this.pdpaddr = pdpaddr;
	}
	/**
	 * @return the usedapn
	 */
	public String getUsedapn() {
		return usedapn;
	}
	/**
	 * @param usedapn the usedapn to set
	 */
	public void setUsedapn(String usedapn) {
		this.usedapn = usedapn;
	}
	/**
	 * @return the lac
	 */
	public long getLac() {
		return lac;
	}
	/**
	 * @param lac the lac to set
	 */
	public void setLac(long lac) {
		this.lac = lac;
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
	 * @return the reqapnni
	 */
	public String getReqapnni() {
		return reqapnni;
	}
	/**
	 * @param reqapnni the reqapnni to set
	 */
	public void setReqapnni(String reqapnni) {
		this.reqapnni = reqapnni;
	}
	/**
	 * @return the subapnni
	 */
	public String getSubapnni() {
		return subapnni;
	}
	/**
	 * @param subapnni the subapnni to set
	 */
	public void setSubapnni(String subapnni) {
		this.subapnni = subapnni;
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
	 * @return the nettype
	 */
	public int getNettype() {
		return nettype;
	}
	/**
	 * @param nettype the nettype to set
	 */
	public void setNettype(int nettype) {
		this.nettype = nettype;
	}
	/**
	 * @return the errorcode
	 */
	public String getErrorcode() {
		return errorcode;
	}
	/**
	 * @param errorcode the errorcode to set
	 */
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	/**
	 * @return the opentime
	 */
	public int getOpentime() {
		return opentime;
	}
	/**
	 * @param opentime the opentime to set
	 */
	public void setOpentime(int opentime) {
		this.opentime = opentime;
	}
	/**
	 * @return the recordtime
	 */
	public int getRecordtime() {
		return recordtime;
	}
	/**
	 * @param recordtime the recordtime to set
	 */
	public void setRecordtime(int recordtime) {
		this.recordtime = recordtime;
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
}
