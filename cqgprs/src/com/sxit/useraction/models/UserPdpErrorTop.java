/**
 * 
 */
package com.sxit.useraction.models;

/**
 * @author 华锋
 * Nov 9, 2009-11:01:03 PM
 *
 */
public class UserPdpErrorTop {

	private String imsi;
	private String apn;
	private int errorcount;
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
	 * @return the apn
	 */
	public String getApn() {
		return apn;
	}
	/**
	 * @param apn the apn to set
	 */
	public void setApn(String apn) {
		this.apn = apn;
	}
	/**
	 * @return the errorcount
	 */
	public int getErrorcount() {
		return errorcount;
	}
	/**
	 * @param errorcount the errorcount to set
	 */
	public void setErrorcount(int errorcount) {
		this.errorcount = errorcount;
	}
}
