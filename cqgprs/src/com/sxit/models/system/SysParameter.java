/**
 * SysParameter.java
 */
package com.sxit.models.system;

import java.sql.Timestamp;

/**
 * 
 * 系统参数管理
 * 
 * @author 华锋 2008-5-5 下午05:38:24
 * 
 */
public class SysParameter {

	private String paramname;
	private String paramvalue;
	private String comments;
	private Timestamp createtime;
	private int typeid;

	public String getGgsn() {
		String[] s = paramvalue.split(",");
		return s[0];
	}

	public String getApn() {

		String[] s = paramvalue.split(",");
		if (s.length > 1)
			return s[1];
		return "";
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	/**
	 * @return the paramname
	 */
	public String getParamname() {
		return paramname;
	}

	/**
	 * @param paramname
	 *            the paramname to set
	 */
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	/**
	 * @return the paramvalue
	 */
	public String getParamvalue() {
		return paramvalue;
	}

	/**
	 * @param paramvalue
	 *            the paramvalue to set
	 */
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

	/**
	 * @return the remarks
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the createtime
	 */
	public Timestamp getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime
	 *            the createtime to set
	 */
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
}