/**
 * 
 */
package com.sxit.netquality.models;

import java.util.Date;

/**
 * @author 华锋
 * Oct 21, 2009-10:18:01 PM
 *
 */
public class BscRnc {
	private int nsvcount;
	
/**
 * 编号
 */
	private String bscrncid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * td还是gsm
	 */
	private String nettype;
	/**
	 * 属于哪个sgsn
	 */
	private String sgsnid;
	
	/**
	 * 最近1次拿数据的时间
	 */
	private Date lastupdate;
	/**
	 * 最近的这次操作是什么?新增?删除?修改
	 */
	private String lastopt;
	/**
	 * @return the bscrncid
	 */
	public String getBscrncid() {
		return bscrncid;
	}
	/**
	 * @param bscrncid the bscrncid to set
	 */
	public void setBscrncid(String bscrncid) {
		this.bscrncid = bscrncid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the nettype
	 */
	public String getNettype() {
		return nettype;
	}
	/**
	 * @param nettype the nettype to set
	 */
	public void setNettype(String nettype) {
		this.nettype = nettype;
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
	 * @return the lastupdate
	 */
	public Date getLastupdate() {
		return lastupdate;
	}
	/**
	 * @param lastupdate the lastupdate to set
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
	 * @param lastopt the lastopt to set
	 */
	public void setLastopt(String lastopt) {
		this.lastopt = lastopt;
	}
	/**
	 * @return the nsvcount
	 */
	public int getNsvcount() {
		return nsvcount;
	}
	/**
	 * @param nsvcount the nsvcount to set
	 */
	public void setNsvcount(int nsvcount) {
		this.nsvcount = nsvcount;
	}
}
