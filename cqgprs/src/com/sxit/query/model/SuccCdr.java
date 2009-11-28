/**
 * 
 */
package com.sxit.query.model;

import com.sxit.netquality.models.Cell;

/**
 * @author 华锋 Nov 5, 2009-10:32:51 PM
 * 
 */
public class SuccCdr {
	private static final java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String imsi;
	private String imei;
	private String lac;
	private String cellid;

	public Cell getCell() {
		return com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(getCellkey());
	}

	public String getCellkey() {
		if (lac == null || lac.equals(""))
			return cellid;
		return lac + "-" + cellid;
	}

	private String sgsnid;
	private String apnni;
	private String apnoi;
	private float upvolume;
	private float downvolume;
	private long changetime;
	private long opentime;
	private long recordtime;
	private int duration;
	private String msisdn;
	private String nettype;

	public String getOpentimestr() {
		return df.format(new java.sql.Timestamp(opentime * 1000));
	}

	public String getAllvolume() {
		return com.sxit.system.util.NumberUtil.toMoney(upvolume + downvolume);
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
	 * @return the imei
	 */
	public String getImei() {
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
	 * @return the lac
	 */
	public String getLac() {
		return lac;
	}

	/**
	 * @param lac
	 *            the lac to set
	 */
	public void setLac(String lac) {
		this.lac = lac;
	}

	/**
	 * @return the cellid
	 */
	public String getCellid() {
		return cellid;
	}

	/**
	 * @param cellid
	 *            the cellid to set
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
	 * @param sgsnid
	 *            the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
	}

	/**
	 * @return the apnni
	 */
	public String getApnni() {
		return apnni;
	}

	/**
	 * @param apnni
	 *            the apnni to set
	 */
	public void setApnni(String apnni) {
		this.apnni = apnni;
	}

	/**
	 * @return the apnoi
	 */
	public String getApnoi() {
		return apnoi;
	}

	/**
	 * @param apnoi
	 *            the apnoi to set
	 */
	public void setApnoi(String apnoi) {
		this.apnoi = apnoi;
	}

	/**
	 * @return the upvolume
	 */
	public float getUpvolume() {
		return upvolume;
	}

	/**
	 * @param upvolume
	 *            the upvolume to set
	 */
	public void setUpvolume(float upvolume) {
		this.upvolume = upvolume;
	}

	/**
	 * @return the downvolume
	 */
	public float getDownvolume() {
		return downvolume;
	}

	/**
	 * @param downvolume
	 *            the downvolume to set
	 */
	public void setDownvolume(float downvolume) {
		this.downvolume = downvolume;
	}

	/**
	 * @return the chagetime
	 */
	public long getChangetime() {
		return changetime;
	}

	/**
	 * @param chagetime
	 *            the chagetime to set
	 */
	public void setChangetime(long changetime) {
		this.changetime = changetime;
	}

	/**
	 * @return the opentime
	 */
	public long getOpentime() {
		return opentime;
	}

	/**
	 * @param opentime
	 *            the opentime to set
	 */
	public void setOpentime(long opentime) {
		this.opentime = opentime;
	}

	/**
	 * @return the recordtime
	 */
	public long getRecordtime() {
		return recordtime;
	}

	/**
	 * @param recordtime
	 *            the recordtime to set
	 */
	public void setRecordtime(long recordtime) {
		this.recordtime = recordtime;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
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

	/**
	 * @return the nettype
	 */
	public String getNettype() {
		return nettype;
	}

	/**
	 * @param nettype
	 *            the nettype to set
	 */
	public void setNettype(String nettype) {
		this.nettype = nettype;
	}

}
