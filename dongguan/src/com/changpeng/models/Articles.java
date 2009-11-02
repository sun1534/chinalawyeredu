/**
 * Articles.java
 */

package com.changpeng.models;

import java.sql.Timestamp;

/**
 * @author 华锋 2008-5-8 下午08:51:44
 * 
 */
public class Articles {
	private int articleid;
	private String title;
	private String content;
	// 1是重要通知2是系统帮助
	private int type;
	// 1发布状态
	private int status;
	private String createuser;
	private long createuserid;
	private Timestamp createtime;
	
	private int listorder;
	private Boolean toshouye;
	
	
	/**
	 * @return the order
	 */
	public int getListorder() {
		return listorder;
	}
	/**
	 * @param order the order to set
	 */
	public void setListorder(int order) {
		this.listorder = order;
	}
	/**
	 * @return the toshouye
	 */
	public Boolean getToshouye() {
		return toshouye;
	}
	/**
	 * @param toshouye the toshouye to set
	 */
	public void setToshouye(Boolean toshouye) {
		this.toshouye = toshouye;
	}
	/**
	 * @return the articleid
	 */
	public int getArticleid() {
		return articleid;
	}
	/**
	 * @param articleid the articleid to set
	 */
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the createuser
	 */
	public String getCreateuser() {
		return createuser;
	}
	/**
	 * @param createuser the createuser to set
	 */
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	/**
	 * @return the createuserid
	 */
	public long getCreateuserid() {
		return createuserid;
	}
	/**
	 * @param createuserid the createuserid to set
	 */
	public void setCreateuserid(long createuserid) {
		this.createuserid = createuserid;
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

	
}
