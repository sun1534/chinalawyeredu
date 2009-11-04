/**
 * 
 */
package com.sxit.stat.models;

/**
 * @author 华锋 Oct 16, 2009-5:03:50 PM
 * 
 */
public class TotalStatModel extends StatModel {

	private String date;

	/**
	 * 网络类型,td,gsm等
	 */
	private String nettype;

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
	 * @return the nettype
	 */
	public String getNettype() {
		if (nettype == null || nettype.equals(""))
			return "未知";
		if (nettype.equals("1")) {
			return "WLAN";
		} else if (nettype.equals("2")) {
			return "GSM";

		} else if (nettype.equals("3")) {
			return "TD";
		} else
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
