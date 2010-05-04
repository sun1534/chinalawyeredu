package com.changpeng.models;

/**
 * SysUnionparams entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysUnionparams implements java.io.Serializable {

	// Fields

	private int groupid;
	private SysGroup sysGroup;
	private String nianshen;
	private String sysname;
	private String indexpic;
	private String topbarpic;
	/**
	 * 达标分是多少
	 */
	private float dabiaofen;
	/**
	 * 是否有本地课程
	 */
	private boolean havelocal;
	//对应groupid的访问url
	private String domain;
	//图片logo的来源等
	private String logopath;
	

private boolean isloglast;
	/** default constructor */
	public SysUnionparams() {
	}

	// Property accessors

	public int getGroupid() {
		return this.groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public SysGroup getSysGroup() {
		return this.sysGroup;
	}

	public void setSysGroup(SysGroup sysGroup) {
		this.sysGroup = sysGroup;
	}

	public String getNianshen() {
		return this.nianshen;
	}

	public void setNianshen(String nianshen) {
		this.nianshen = nianshen;
	}

	public String getSysname() {
		return this.sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public String getTopbarpic() {
		return this.topbarpic;
	}

	public void setTopbarpic(String topbarpic) {
		this.topbarpic = topbarpic;
	}

	public float getDabiaofen() {
		return this.dabiaofen;
	}

	public void setDabiaofen(float dabiaofen) {
		this.dabiaofen = dabiaofen;
	}

	public boolean getHavelocal() {
		return this.havelocal;
	}

	public void setHavelocal(boolean havelocal) {
		this.havelocal = havelocal;
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
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the isloglast
	 */
	public boolean getIsloglast() {
		return isloglast;
	}

	/**
	 * @param isloglast the isloglast to set
	 */
	public void setIsloglast(boolean isloglast) {
		this.isloglast = isloglast;
	}

}