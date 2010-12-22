/**
 * 
 */
package com.sxit.netquality.models;


/**
 * @author 华锋
 * Nov 4, 2009-10:03:12 PM
 *
 */
public class HWChrFlowidErrorStat {

	private String statdate;
	private String stattime;
	private String statflag; //1按天0按小时
	private String innerreason;
	private String outreason;
	private String flowid;
	private int errorcount;
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
	 * @return the innerreason
	 */
	public String getInnerreason() {
		return innerreason;
	}
	/**
	 * @param innerreason the innerreason to set
	 */
	public void setInnerreason(String innerreason) {
		this.innerreason = innerreason;
	}
	/**
	 * @return the outreason
	 */
	public String getOutreason() {
		return outreason;
	}
	/**
	 * @param outreason the outreason to set
	 */
	public void setOutreason(String outreason) {
		this.outreason = outreason;
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
