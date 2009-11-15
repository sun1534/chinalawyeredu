/**
 * 
 */
package com.sxit.useraction.models;

import java.util.List;

/**
 * 
 * 各种类型的统计错误
 * 主要是27，29以及38号错误
 * 
 * @author 华锋
 * Nov 8, 2009-8:21:31 PM
 *
 */
public class ErrorCodeStat {
	
	public class Details{
		private String imsi;
		private String apn;
		private int pdperrorcnt;
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
		 * @return the pdperrorcnt
		 */
		public int getPdperrorcnt() {
			return pdperrorcnt;
		}
		/**
		 * @param pdperrorcnt the pdperrorcnt to set
		 */
		public void setPdperrorcnt(int pdperrorcnt) {
			this.pdperrorcnt = pdperrorcnt;
		}
		
	}
	
//错误总数
	private int errorcount;
	//用户总数
	private int usercount;
	
	private List detailist;
	public List getDetaillist(){
		return this.detailist;
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
	 * @return the detailist
	 */
	public List getDetailist() {
		return detailist;
	}
	/**
	 * @param detailist the detailist to set
	 */
	public void setDetailist(List detailist) {
		this.detailist = detailist;
	}
	
}
