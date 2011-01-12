package com.changpeng.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Lessons entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lessontype implements java.io.Serializable {

	// Fields

	private int typeid;
	private int parentid;
	private String typename;
	private int lessoncnt;
	private Timestamp createtime;
	private Timestamp stattime;
	private List<Lessontype> children=new ArrayList<Lessontype>();
	private boolean haschild=false;
	/**
	 * @return the typeid
	 */
	public int getTypeid() {
		return typeid;
	}
	/**
	 * @param typeid the typeid to set
	 */
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	/**
	 * @return the parentid
	 */
	public int getParentid() {
		return parentid;
	}
	/**
	 * @param parentid the parentid to set
	 */
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	/**
	 * @return the typename
	 */
	public String getTypename() {
		return typename;
	}
	/**
	 * @param typename the typename to set
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}
	/**
	 * @return the lessoncnt
	 */
	public int getLessoncnt() {
		return lessoncnt;
	}
	/**
	 * @param lessoncnt the lessoncnt to set
	 */
	public void setLessoncnt(int lessoncnt) {
		this.lessoncnt = lessoncnt;
	}
	/**
	 * @return the createtime
	 */
	public Timestamp getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	/**
	 * @return the stattime
	 */
	public Timestamp getStattime() {
		return stattime;
	}
	/**
	 * @param stattime the stattime to set
	 */
	public void setStattime(Timestamp stattime) {
		this.stattime = stattime;
	}
	/**
	 * @return the children
	 */
	public List<Lessontype> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void addChild(Lessontype child) {
		haschild=true;
		this.children.add(child);
	}
	/**
	 * @return the haschild
	 */
	public boolean getHaschild() {
		return haschild;
	}
	/**
	 * @param haschild the haschild to set
	 */
	public void setHaschild(boolean haschild) {
		this.haschild = haschild;
	}

}