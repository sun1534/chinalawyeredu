package com.changpeng.jifen.action;


/**
 * Jifenbudeng entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class JifenbudengBatch implements java.io.Serializable {

	// Fields

	private String title;
	private String budengdate;
	private String lawyerno;
private int excelline;
	private String xuefen;

	private String theyear;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the budengdate
	 */
	public String getBudengdate() {
		return budengdate;
	}

	/**
	 * @param budengdate the budengdate to set
	 */
	public void setBudengdate(String budengdate) {
		this.budengdate = budengdate;
	}

	/**
	 * @return the lawyerno
	 */
	public String getLawyerno() {
		return lawyerno;
	}

	/**
	 * @param lawyerno the lawyerno to set
	 */
	public void setLawyerno(String lawyerno) {
		this.lawyerno = lawyerno;
	}

	/**
	 * @return the xuefen
	 */
	public String getXuefen() {
		return xuefen;
	}

	/**
	 * @param xuefen the xuefen to set
	 */
	public void setXuefen(String xuefen) {
		this.xuefen = xuefen;
	}

	/**
	 * @return the theyear
	 */
	public String getTheyear() {
		return theyear;
	}

	/**
	 * @param theyear the theyear to set
	 */
	public void setTheyear(String theyear) {
		this.theyear = theyear;
	}

	/**
	 * @return the excelline
	 */
	public int getExcelline() {
		return excelline;
	}

	/**
	 * @param excelline the excelline to set
	 */
	public void setExcelline(int excelline) {
		this.excelline = excelline;
	}

}