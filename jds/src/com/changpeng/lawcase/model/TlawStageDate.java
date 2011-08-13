/**
 * TlawStagetime.java
 */
package com.changpeng.lawcase.model;


/**
 * 各个阶段的日期
 * @author 华锋
 * Jan 13, 20106:25:59 PM
 *
 */
public class TlawStageDate {

//	public static void main(String args[])throws Exception{
//		Class c=Class.forName("com.changpeng.lawcase.model.TlawStagetime");
//		Class partypes[] = new Class[]{java.sql.String.class};
//		Object obj=c.newInstance();
//		Method method=c.getDeclaredMethod("setCreatetime", partypes);
//		Object arglist[] = new Object[]{new java.sql.String(System.currentTimeMillis())};
//		method.invoke(obj, arglist);
//		TlawStagetime stage=(TlawStagetime)obj;
//		System.out.println(stage.getCreatetime());
//		
//		
//		
//	}
	
	private long caseid;
	private TlawLawcase lawcase;
	private String createdate;//新增日期
	private String thedate;//案件委托日期
	private String preassigndate;//提交分配诉讼承办人日期
	private String assigndate;//分配诉讼承办人日期
//	private String toexceptiondate; //转为异常案件日期
	private String susongtijiaodate;//提交诉讼材料日期
	private String susongconfirmdate;//诉讼材料通过日期
	private String liandate;//立案日期
	private String jiaofeidate;//缴费日期
	private String totiaojiedate;//到调解日期,实际就是录入缴费的时间
	private String tiaojiedate;//调解日期

	private String tokaitingdate;//到开庭日期
	private String panjuedate;//录入案件生效信息的日期
	private String kaitingdate;
	private String tozhixingdate;//到执行日期
	private String assignzhixingdate;//分配执行人日期
	/**
	 * @return the caseid
	 */
	public long getCaseid() {
		return caseid;
	}
	/**
	 * @param caseid the caseid to set
	 */
	public void setCaseid(long caseid) {
		this.caseid = caseid;
	}
	/**
	 * @return the lawcase
	 */
	public TlawLawcase getLawcase() {
		return lawcase;
	}
	/**
	 * @param lawcase the lawcase to set
	 */
	public void setLawcase(TlawLawcase lawcase) {
		this.lawcase = lawcase;
	}
	/**
	 * @return the createdate
	 */
	public String getCreatedate() {
		return createdate;
	}
	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	/**
	 * @return the thedate
	 */
	public String getThedate() {
		return thedate;
	}
	/**
	 * @param thedate the thedate to set
	 */
	public void setThedate(String thedate) {
		this.thedate = thedate;
	}
	/**
	 * @return the preassigndate
	 */
	public String getPreassigndate() {
		return preassigndate;
	}
	/**
	 * @param preassigndate the preassigndate to set
	 */
	public void setPreassigndate(String preassigndate) {
		this.preassigndate = preassigndate;
	}
	/**
	 * @return the assigndate
	 */
	public String getAssigndate() {
		return assigndate;
	}
	/**
	 * @param assigndate the assigndate to set
	 */
	public void setAssigndate(String assigndate) {
		this.assigndate = assigndate;
	}
	/**
	 * @return the susongtijiaodate
	 */
	public String getSusongtijiaodate() {
		return susongtijiaodate;
	}
	/**
	 * @param susongtijiaodate the susongtijiaodate to set
	 */
	public void setSusongtijiaodate(String susongtijiaodate) {
		this.susongtijiaodate = susongtijiaodate;
	}
	/**
	 * @return the susongconfirmdate
	 */
	public String getSusongconfirmdate() {
		return susongconfirmdate;
	}
	/**
	 * @param susongconfirmdate the susongconfirmdate to set
	 */
	public void setSusongconfirmdate(String susongconfirmdate) {
		this.susongconfirmdate = susongconfirmdate;
	}
	/**
	 * @return the liandate
	 */
	public String getLiandate() {
		return liandate;
	}
	/**
	 * @param liandate the liandate to set
	 */
	public void setLiandate(String liandate) {
		this.liandate = liandate;
	}
	/**
	 * @return the jiaofeidate
	 */
	public String getJiaofeidate() {
		return jiaofeidate;
	}
	/**
	 * @param jiaofeidate the jiaofeidate to set
	 */
	public void setJiaofeidate(String jiaofeidate) {
		this.jiaofeidate = jiaofeidate;
	}
	/**
	 * @return the totiaojiedate
	 */
	public String getTotiaojiedate() {
		return totiaojiedate;
	}
	/**
	 * @param totiaojiedate the totiaojiedate to set
	 */
	public void setTotiaojiedate(String totiaojiedate) {
		this.totiaojiedate = totiaojiedate;
	}
	/**
	 * @return the tiaojiedate
	 */
	public String getTiaojiedate() {
		return tiaojiedate;
	}
	/**
	 * @param tiaojiedate the tiaojiedate to set
	 */
	public void setTiaojiedate(String tiaojiedate) {
		this.tiaojiedate = tiaojiedate;
	}
	/**
	 * @return the tokaitingdate
	 */
	public String getTokaitingdate() {
		return tokaitingdate;
	}
	/**
	 * @param tokaitingdate the tokaitingdate to set
	 */
	public void setTokaitingdate(String tokaitingdate) {
		this.tokaitingdate = tokaitingdate;
	}
	/**
	 * @return the panjuedate
	 */
	public String getPanjuedate() {
		return panjuedate;
	}
	/**
	 * @param panjuedate the panjuedate to set
	 */
	public void setPanjuedate(String panjuedate) {
		this.panjuedate = panjuedate;
	}
	/**
	 * @return the tozhixingdate
	 */
	public String getTozhixingdate() {
		return tozhixingdate;
	}
	/**
	 * @param tozhixingdate the tozhixingdate to set
	 */
	public void setTozhixingdate(String tozhixingdate) {
		this.tozhixingdate = tozhixingdate;
	}
	/**
	 * @return the assignzhixingdate
	 */
	public String getAssignzhixingdate() {
		return assignzhixingdate;
	}
	/**
	 * @param assignzhixingdate the assignzhixingdate to set
	 */
	public void setAssignzhixingdate(String assignzhixingdate) {
		this.assignzhixingdate = assignzhixingdate;
	}
	/**
	 * @return the kaitingdate
	 */
	public String getKaitingdate() {
		return kaitingdate;
	}
	/**
	 * @param kaitingdate the kaitingdate to set
	 */
	public void setKaitingdate(String kaitingdate) {
		this.kaitingdate = kaitingdate;
	}
}
