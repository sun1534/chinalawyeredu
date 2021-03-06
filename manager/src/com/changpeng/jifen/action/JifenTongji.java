/**
 * 
 */
package com.changpeng.jifen.action;

/**
 * @author 华锋
 * Aug 19, 2009-9:35:47 PM
 *
 */
public class JifenTongji {

	private float dabiaofen;
	private float localfen;
	private String name;
	private int lawyerid;
//	private String groupname;
	private int groupid;
	private float xianchang;
	private float video;
	private float doc;
	private float budeng;
	private float koufen;
	private float zongjifen;
	public String getDabiaostr(){
		if(zongjifen==0)
			return "未培训";
		else if(zongjifen<dabiaofen||xianchang<localfen)
			return "未达标";
		return "<font color='red'>已达标</font>";
	}
	
	
	/**
	 * @return the dabiaofen
	 */
	public float getDabiaofen() {
		return dabiaofen;
	}
	/**
	 * @param dabiaofen the dabiaofen to set
	 */
	public void setDabiaofen(float dabiaofen) {
		this.dabiaofen = dabiaofen;
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
	 * @return the lawyerid
	 */
	public int getLawyerid() {
		return lawyerid;
	}
	/**
	 * @param lawyerid the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}
	/**
	 * @return the groupname
	 */
	public String getGroupname() {
		return com.changpeng.system.util.CommonDatas.groups.get(groupid);
	}
	/**
	 * @param groupname the groupname to set
	 */
//	public void setGroupname(String groupname) {
//		this.groupname = groupname;
//	}
	/**
	 * @return the xianchang
	 */
	public float getXianchang() {
		return xianchang;
	}
	/**
	 * @param xianchang the xianchang to set
	 */
	public void setXianchang(float xianchang) {
		this.xianchang = xianchang;
	}
	/**
	 * @return the video
	 */
	public float getVideo() {
		return video;
	}
	/**
	 * @param video the video to set
	 */
	public void setVideo(float video) {
		this.video = video;
	}
	/**
	 * @return the doc
	 */
	public float getDoc() {
		return doc;
	}
	/**
	 * @param doc the doc to set
	 */
	public void setDoc(float doc) {
		this.doc = doc;
	}
	/**
	 * @return the budeng
	 */
	public float getBudeng() {
		return budeng;
	}
	/**
	 * @param budeng the budeng to set
	 */
	public void setBudeng(float budeng) {
		this.budeng = budeng;
	}
	/**
	 * @return the koufen
	 */
	public float getKoufen() {
		return koufen;
	}
	/**
	 * @param koufen the koufen to set
	 */
	public void setKoufen(float koufen) {
		this.koufen = koufen;
	}
	/**
	 * @return the zongjifen
	 */
	public float getZongjifen() {
		return zongjifen;
	}
	/**
	 * @param zongjifen the zongjifen to set
	 */
	public void setZongjifen(float zongjifen) {
		this.zongjifen = zongjifen;
	}
	/**
	 * @return the localfen
	 */
	public float getLocalfen() {
		return localfen;
	}
	/**
	 * @param localfen the localfen to set
	 */
	public void setLocalfen(float localfen) {
		this.localfen = localfen;
	}


	/**
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}


	/**
	 * @param groupid the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	
	
}
