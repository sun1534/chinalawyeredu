/**
 * 
 */
package com.sxit.netquality.models;

import java.util.Date;

/**
 * @author 华锋 Oct 21, 2009-10:18:05 PM
 * 
 */
public class Sgsn {

	private String sgsnid;
	private String sgsnarea;
	private String sgsntype;
	/**
	 * 最近1次拿数据的时间
	 */
	private Date lastupdate;
	/**
	 * 最近的这次操作是什么?新增?删除?修改
	 */
	private String lastopt;

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
	 * @return the sgsnarea
	 */
	public String getSgsnarea() {
		return sgsnarea;
	}

	/**
	 * @param sgsnarea
	 *            the sgsnarea to set
	 */
	public void setSgsnarea(String sgsnarea) {
		this.sgsnarea = sgsnarea;
	}

	/**
	 * @return the lastupdate
	 */
	public Date getLastupdate() {
		return lastupdate;
	}

	/**
	 * @param lastupdate
	 *            the lastupdate to set
	 */
	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	/**
	 * @return the lastopt
	 */
	public String getLastopt() {
		return lastopt;
	}

	/**
	 * @param lastopt
	 *            the lastopt to set
	 */
	public void setLastopt(String lastopt) {
		this.lastopt = lastopt;
	}

	/**
	 * @return the snsgtype
	 */
	public String getSgsntype() {
		int len = sgsnid.length();
		String seq = sgsnid.substring(len - 1);
		if (Integer.parseInt(seq) > 6) {
			return "华为";
		} else
			return "爱立信";
		// return snsgtype;
	}

	/**
	 * @param snsgtype
	 *            the snsgtype to set
	 */
	public void setSgsntype(String sgsntype) {
		this.sgsntype = sgsntype;
	}
}