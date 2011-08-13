package com.changpeng.lawcase.model;

import java.sql.Timestamp;


/**
 * 案件的开庭信息
 * 
 * 受理机构，法官、助理等的数据，都在立案信息里，这里对那个数据进行更新即可
 * 
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawKaitinginfo implements java.io.Serializable {

	private long kaitingid;
	// Fields
	private Long caseid;
	/**
	 * 开庭日期
	 */
	private String kaitingdate; 
	private String kaitingtime; 
	
	private String kaitingresult;
	private String jixiaowhy;
	private String kaitingjixiao;
	
	/**
	 * 开庭律师
	 */
	private String kaitinglawyer; 
	/**
	 * 开庭地点
	 */
	private String address;
	
	/**
	 * 案件的判决时间，判决后转交执行
	 */
	private String panjuedate;
	/**
	 * 案件转接时间
	 */
	private String zhuanjiedate;
	/**
	 * 判决生效时间
	 */
	private String panjuevaliddate;
	/**
	 * 诉讼公告费
	 */
	private double susonggonggaofee;
	/**
	 * 公告时间
	 */
	private String gonggaodate;
	/**
	 * @return the caseid
	 */
	public Long getCaseid() {
		return caseid;
	}
	/**
	 * @param caseid the caseid to set
	 */
	public void setCaseid(Long caseid) {
		this.caseid = caseid;
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
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the zhuanjiedate
	 */
	public String getZhuanjiedate() {
		return zhuanjiedate;
	}
	/**
	 * @param zhuanjiedate the zhuanjiedate to set
	 */
	public void setZhuanjiedate(String zhuanjiedate) {
		this.zhuanjiedate = zhuanjiedate;
	}
	/**
	 * @return the panjuevaliddate
	 */
	public String getPanjuevaliddate() {
		return panjuevaliddate;
	}
	/**
	 * @param panjuevaliddate the panjuevaliddate to set
	 */
	public void setPanjuevaliddate(String panjuevaliddate) {
		this.panjuevaliddate = panjuevaliddate;
	}
	/**
	 * @return the susonggonggaofee
	 */
	public double getSusonggonggaofee() {
		return susonggonggaofee;
	}
	/**
	 * @param susonggonggaofee the susonggonggaofee to set
	 */
	public void setSusonggonggaofee(float susonggonggaofee) {
		this.susonggonggaofee = susonggonggaofee;
	}
	/**
	 * @return the gonggaodate
	 */
	public String getGonggaodate() {
		return gonggaodate;
	}
	/**
	 * @param gonggaodate the gonggaodate to set
	 */
	public void setGonggaodate(String gonggaodate) {
		this.gonggaodate = gonggaodate;
	}
	/**
	 * @return the kaitingid
	 */
	public long getKaitingid() {
		return kaitingid;
	}
	/**
	 * @param kaitingid the kaitingid to set
	 */
	public void setKaitingid(long kaitingid) {
		this.kaitingid = kaitingid;
	}
	
	private Timestamp modifytime;
	private long modifyuserid;
	private String modifyusername;
	/**
	 * @return the modifytime
	 */
	public Timestamp getModifytime() {
		return modifytime;
	}
	/**
	 * @param modifytime the modifytime to set
	 */
	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}
	/**
	 * @return the modifyuserid
	 */
	public long getModifyuserid() {
		return modifyuserid;
	}
	/**
	 * @param modifyuserid the modifyuserid to set
	 */
	public void setModifyuserid(long modifyuserid) {
		this.modifyuserid = modifyuserid;
	}
	/**
	 * @return the modifyusername
	 */
	public String getModifyusername() {
		return modifyusername;
	}
	/**
	 * @param modifyusername the modifyusername to set
	 */
	public void setModifyusername(String modifyusername) {
		this.modifyusername = modifyusername;
	}
	/**
	 * @return the kaitingtime
	 */
	public String getKaitingtime() {
		return kaitingtime;
	}
	/**
	 * @param kaitingtime the kaitingtime to set
	 */
	public void setKaitingtime(String kaitingtime) {
		this.kaitingtime = kaitingtime;
	}
	/**
	 * @return the kaitinglawyer
	 */
	public String getKaitinglawyer() {
		return kaitinglawyer;
	}
	/**
	 * @param kaitinglawyer the kaitinglawyer to set
	 */
	public void setKaitinglawyer(String kaitinglawyer) {
		this.kaitinglawyer = kaitinglawyer;
	}
	/**
	 * @param susonggonggaofee the susonggonggaofee to set
	 */
	public void setSusonggonggaofee(double susonggonggaofee) {
		this.susonggonggaofee = susonggonggaofee;
	}
	/**
	 * @return the kaitingresult
	 */
	public String getKaitingresult() {
		return kaitingresult;
	}
	/**
	 * @param kaitingresult the kaitingresult to set
	 */
	public void setKaitingresult(String kaitingresult) {
		this.kaitingresult = kaitingresult;
	}
	/**
	 * @return the jixiaowhy
	 */
	public String getJixiaowhy() {
		return jixiaowhy;
	}
	/**
	 * @param jixiaowhy the jixiaowhy to set
	 */
	public void setJixiaowhy(String jixiaowhy) {
		this.jixiaowhy = jixiaowhy;
	}
	/**
	 * @return the kaitingjixiao
	 */
	public String getKaitingjixiao() {
		return kaitingjixiao;
	}
	/**
	 * @param kaitingjixiao the kaitingjixiao to set
	 */
	public void setKaitingjixiao(String kaitingjixiao) {
		this.kaitingjixiao = kaitingjixiao;
	}

//	private String jigou;       //受理机构
//	private String faguan;     //受理法官
//	private String zhuli;      //助理
//	private String faguanlinkphone;  //法官联系电话
//	private String zhulilinkphone;   //助理联系电话
//	/**
//	 * @return the jigou
//	 */
//	public String getJigou() {
//		return jigou;
//	}
//	/**
//	 * @param jigou the jigou to set
//	 */
//	public void setJigou(String jigou) {
//		this.jigou = jigou;
//	}
//	/**
//	 * @return the faguan
//	 */
//	public String getFaguan() {
//		return faguan;
//	}
//	/**
//	 * @param faguan the faguan to set
//	 */
//	public void setFaguan(String faguan) {
//		this.faguan = faguan;
//	}
//	/**
//	 * @return the zhuli
//	 */
//	public String getZhuli() {
//		return zhuli;
//	}
//	/**
//	 * @param zhuli the zhuli to set
//	 */
//	public void setZhuli(String zhuli) {
//		this.zhuli = zhuli;
//	}
//	/**
//	 * @return the faguanlinkphone
//	 */
//	public String getFaguanlinkphone() {
//		return faguanlinkphone;
//	}
//	/**
//	 * @param faguanlinkphone the faguanlinkphone to set
//	 */
//	public void setFaguanlinkphone(String faguanlinkphone) {
//		this.faguanlinkphone = faguanlinkphone;
//	}
//	/**
//	 * @return the zhulilinkphone
//	 */
//	public String getZhulilinkphone() {
//		return zhulilinkphone;
//	}
//	/**
//	 * @param zhulilinkphone the zhulilinkphone to set
//	 */
//	public void setZhulilinkphone(String zhulilinkphone) {
//		this.zhulilinkphone = zhulilinkphone;
//	}
}