/**
 * WebVisitInfo.java
 */
package com.changpeng.common;

/**
 * @author 华锋
 * Jan 1, 20115:32:24 PM
 *
 */
public class WebVisitInfo {
	private String topbarbic;
	private String sysname;
	private String indexpic;
	private String logopath;
	private boolean havelocal;
	private String currentDomain;
	/**
	 * @return the currentDomain
	 */
	public String getCurrentDomain() {
		return currentDomain;
	}
	/**
	 * @param currentDomain the currentDomain to set
	 */
	public void setCurrentDomain(String currentDomain) {
		this.currentDomain = currentDomain;
	}
	/**
	 * @return the topbarbic
	 */
	public String getTopbarbic() {
		return topbarbic;
	}
	/**
	 * @param topbarbic the topbarbic to set
	 */
	public void setTopbarbic(String topbarbic) {
		this.topbarbic = topbarbic;
	}
	/**
	 * @return the sysname
	 */
	public String getSysname() {
		return sysname;
	}
	/**
	 * @param sysname the sysname to set
	 */
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	/**
	 * @return the indexpic
	 */
	public String getIndexpic() {
		return indexpic;
	}
	/**
	 * @param indexpic the indexpic to set
	 */
	public void setIndexpic(String indexpic) {
		this.indexpic = indexpic;
	}
	/**
	 * @return the logopath
	 */
	public String getLogopath() {
		return logopath;
	}
	/**
	 * @param logopath the logopath to set
	 */
	public void setLogopath(String logopath) {
		this.logopath = logopath;
	}
	/**
	 * @return the havelocal
	 */
	public boolean isHavelocal() {
		return havelocal;
	}
	/**
	 * @param havelocal the havelocal to set
	 */
	public void setHavelocal(boolean havelocal) {
		this.havelocal = havelocal;
	}

}
