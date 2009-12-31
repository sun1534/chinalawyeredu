/**
 * 
 */
package com.sxit.netquality.models;

import java.util.Date;

/**
 * 
 * 
 * @author 华锋
 * Oct 21, 2009-10:17:54 PM
 *
 */
public class Apn extends Volumes{

	private int orderby;
	/**
	 * 编号
	 */
	private String apnid;
	/**
	 * 名称
	 */
	private String apnname;
	/**
	 * 使用单位
	 */
	private String usercorp;
	/**
	 * 联系电话
	 */
	private String userphone;
	/**
	 * 最近1次拿数据的时间
	 */
	private Date lastupdate;
	/**
	 * 最近的这次操作是什么?新增?删除?修改
	 */
	private String lastopt;
	/**
	 * @return the apnid
	 */
	public String getApnid() {
		return apnid;
	}
	/**
	 * @param apnid the apnid to set
	 */
	public void setApnid(String apnid) {
		this.apnid = apnid;
	}
	/**
	 * @return the apnname
	 */
	public String getApnname() {
		return apnname;
	}
	/**
	 * @param apnname the apnname to set
	 */
	public void setApnname(String apnname) {
		this.apnname = apnname;
	}
	/**
	 * @return the usercorp
	 */
	public String getUsercorp() {
		return usercorp;
	}
	/**
	 * @param usercorp the usercorp to set
	 */
	public void setUsercorp(String usercorp) {
		this.usercorp = usercorp;
	}
	/**
	 * @return the userphone
	 */
	public String getUserphone() {
		return userphone;
	}
	/**
	 * @param userphone the userphone to set
	 */
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	/**
	 * @return the lastupdate
	 */
	public Date getLastupdate() {
		return lastupdate;
	}
	/**
	 * @param lastupdate the lastupdate to set
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
	 * @param lastopt the lastopt to set
	 */
	public void setLastopt(String lastopt) {
		this.lastopt = lastopt;
	}
	/**
	 * @return the orderby
	 */
	public int getOrderby() {
		return orderby;
	}
	/**
	 * @param orderby the orderby to set
	 */
	public void setOrderby(int orderby) {
		this.orderby = orderby;
	}
	
}
