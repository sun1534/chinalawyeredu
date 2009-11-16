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
	private String sgsnip;
	private int bsccount;
	private int slotcount;
	private double capacity;
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
	
		 return sgsntype;
	}

	/**
	 * @param snsgtype
	 *            the snsgtype to set
	 */
	public void setSgsntype(String sgsntype) {
		this.sgsntype = sgsntype;
	}

	/**
	 * @return the sgsnip
	 */
	public String getSgsnip() {
		return sgsnip;
	}

	/**
	 * @param sgsnip the sgsnip to set
	 */
	public void setSgsnip(String sgsnip) {
		this.sgsnip = sgsnip;
	}

	/**
	 * @return the bsccount
	 */
	public int getBsccount() {
		return bsccount;
	}

	/**
	 * @param bsccount the bsccount to set
	 */
	public void setBsccount(int bsccount) {
		this.bsccount = bsccount;
	}

	/**
	 * @return the slotcount
	 */
	public int getSlotcount() {
		return slotcount;
	}

	/**
	 * @param slotcount the slotcount to set
	 */
	public void setSlotcount(int slotcount) {
		this.slotcount = slotcount;
	}

	/**
	 * @return the capacity
	 */
	public double getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
}