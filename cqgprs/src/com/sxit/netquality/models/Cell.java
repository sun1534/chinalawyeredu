/**
 * 
 */
package com.sxit.netquality.models;

import java.util.Date;

/**
 * 
 * 小区信息
 * 
 * @author 华锋 Oct 21, 2009-10:17:45 PM
 * 
 */
public class Cell extends Volumes {

	private String cellkey;
	private String cellid;
	private String lac;
	private String cellname;
	private String bscrncid;
//	private String subarea;
	private String subareaid;

	/**
	 * 最近1次拿数据的时间
	 */
	private Date lastupdate;
	/**
	 * 最近的这次操作是什么?新增?删除?修改
	 */
	private String lastopt;

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
	 * @return the cellname
	 */
	public String getCellname() {
		return cellname;
	}

	/**
	 * @param cellname
	 *            the cellname to set
	 */
	public void setCellname(String cellname) {
		this.cellname = cellname;
	}

	/**
	 * @return the bscrncid
	 */
	public String getBscrncid() {
		return bscrncid;
	}

	/**
	 * @param bscrncid
	 *            the bscrncid to set
	 */
	public void setBscrncid(String bscrncid) {
		this.bscrncid = bscrncid;
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

	public String getSgsnid() {
		return com.sxit.netquality.service.BasicSetService.BSC_SGSN.get(bscrncid);
	}

	/**
	 * @return the subarea
	 */
	public String getSubarea() {
		return com.sxit.netquality.service.BasicSetService.ALL_SUBS.get(subareaid);
	}

	/**
	 * @param subarea
	 *            the subarea to set
	 */
//	public void setSubarea(String subarea) {
//		this.subarea = subarea;
//	}

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

	private boolean hasset;

	public String getCellkey() {
		if (!hasset) {
			if (lac == null || lac.equals(""))
				this.cellkey = cellid;
			this.cellkey = this.lac + "-" + this.cellid;
		}
		return this.cellkey;
	}

	public void setCellkey(String cellkey) {
		hasset = true;
		this.cellkey = cellkey;
	}

	/**
	 * @return the subareaid
	 */
	public String getSubareaid() {
		return subareaid;
	}

	/**
	 * @param subareaid the subareaid to set
	 */
	public void setSubareaid(String subareaid) {
		this.subareaid = subareaid;
	}
}
