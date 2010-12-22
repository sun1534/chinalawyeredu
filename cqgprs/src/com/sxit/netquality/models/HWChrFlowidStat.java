/**
 * 
 */
package com.sxit.netquality.models;

import java.sql.Timestamp;

/**
 * @author 华锋
 * Nov 4, 2009-10:03:12 PM
 *
 */
public class HWChrFlowidStat {

	private String statdate;
	private String stattime;
	private String statflag; //1按天0按小时
	private String sgsnid;
	private String flowid;
	private String cellid;
	private String kuang;
	private String cao;
	private int flowcount;
	/**
	 * @return the statdate
	 */
	public String getStatdate() {
		return statdate;
	}
	/**
	 * @param statdate the statdate to set
	 */
	public void setStatdate(String statdate) {
		this.statdate = statdate;
	}
	/**
	 * @return the stattime
	 */
	public String getStattime() {
		return stattime;
	}
	/**
	 * @param stattime the stattime to set
	 */
	public void setStattime(String stattime) {
		this.stattime = stattime;
	}
	/**
	 * @return the statflag
	 */
	public String getStatflag() {
		return statflag;
	}
	/**
	 * @param statflag the statflag to set
	 */
	public void setStatflag(String statflag) {
		this.statflag = statflag;
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
	 * @return the flowid
	 */
	public String getFlowid() {
		return flowid;
	}
	/**
	 * @param flowid the flowid to set
	 */
	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}
	/**
	 * @return the cellid
	 */
	public String getCellid() {
		return cellid;
	}
	/**
	 * @param cellid the cellid to set
	 */
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}
	/**
	 * @return the kuang
	 */
	public String getKuang() {
		return kuang;
	}
	/**
	 * @param kuang the kuang to set
	 */
	public void setKuang(String kuang) {
		this.kuang = kuang;
	}
	/**
	 * @return the cao
	 */
	public String getCao() {
		return cao;
	}
	/**
	 * @param cao the cao to set
	 */
	public void setCao(String cao) {
		this.cao = cao;
	}
	/**
	 * @return the statcount
	 */
	public int getFlowcount() {
		return flowcount;
	}
	/**
	 * @param statcount the statcount to set
	 */
	public void setFlowcount(int flowcount) {
		this.flowcount = flowcount;
	}
}
