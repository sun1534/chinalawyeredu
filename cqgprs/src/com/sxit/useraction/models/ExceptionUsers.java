/**
 * 
 */
package com.sxit.useraction.models;

import java.util.List;

import com.sxit.netquality.models.Apn;

/**
 * @author 华锋
 * Nov 8, 2009-10:23:44 PM
 *
 */
public class ExceptionUsers {

	public class DetailImsi{
		private String imsi;
		private int errcount;
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
		 * @return the errcount
		 */
		public int getErrcount() {
			return errcount;
		}
		/**
		 * @param errcount the errcount to set
		 */
		public void setErrcount(int errcount) {
			this.errcount = errcount;
		}
	}
	
	private String apnni;
	private int usercount;
	public Apn getApn(){
		return com.sxit.netquality.service.BasicSetService.ALL_APNS.get(apnni);
	}
	private List imsilist;

	/**
	 * @return the apnni
	 */
	public String getApnni() {
		return apnni;
	}

	/**
	 * @param apnni the apnni to set
	 */
	public void setApnni(String apnni) {
		this.apnni = apnni;
	}

	/**
	 * @return the usercount
	 */
	public int getUsercount() {
		return usercount;
	}

	/**
	 * @param usercount the usercount to set
	 */
	public void setUsercount(int usercount) {
		this.usercount = usercount;
	}

	/**
	 * @return the imsilist
	 */
	public List getImsilist() {
		return imsilist;
	}

	/**
	 * @param imsilist the imsilist to set
	 */
	public void setImsilist(List imsilist) {
		this.imsilist = imsilist;
	}
}
