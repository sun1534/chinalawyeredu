/**
 * 
 */
package com.sxit.stat.models;

import com.sxit.netquality.models.Apn;

/**
 * @author 华锋 Oct 19, 2009-10:37:32 PM
 * 
 */
public class ApnStatModel extends StatModel {
	private String apnid;



	/**
	 * 某天的数据
	 */
	private String date;
	/**
	 * 某天-时的数据
	 */
	private String datetime;

	public Apn getApn(){
		return com.sxit.netquality.service.BasicSetService.ALL_APNS.get(apnid);
	}

	

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the datetime
	 */
	public String getDatetime() {
		return datetime;
	}

	/**
	 * @param datetime
	 *            the datetime to set
	 */
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	/**
	 * @return the apnid
	 */
	public String getApnid() {
		return apnid;
	}

	/**
	 * @param apnid
	 *            the apnid to set
	 */
	public void setApnid(String apnid) {
		this.apnid = apnid;
	}
}
