package com.changpeng.lawcase.model;

import java.sql.Timestamp;

/**
 * TlawFiles entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawFiles implements java.io.Serializable {

	// Fields

	private Long fileid;
	private String filename;
	private String path;
	private Timestamp uploadtime;
	private Long uploaduserid;
	private String uploadusername;
	private String remarks;
	
	/**
	 * 1是导入的案件模板
	 * 2是诉讼材料对应的文件
	 * 3是执行材料对应的文件
	 * 4其他案件文件
	 */
	private int type;
	/**
	 * 对应着哪个案件的数据
	 */
	private long caseid;
/**
 * 哪个节点新增的
 */
	private int nodeid;
	/**
	 * 哪个动作新增的
	 */
	private int actionid;

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
	 * @return the nodeid
	 */
	public int getNodeid() {
		return nodeid;
	}

	/**
	 * @param nodeid the nodeid to set
	 */
	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}

	/**
	 * @return the actionid
	 */
	public int getActionid() {
		return actionid;
	}

	/**
	 * @param actionid the actionid to set
	 */
	public void setActionid(int actionid) {
		this.actionid = actionid;
	}

	/** default constructor */
	public TlawFiles() {
	}

	/** full constructor */
	public TlawFiles(String filename, String path, Timestamp uploadtime,
			Long uploaduserid, String uploadusername, String remarks) {
		this.filename = filename;
		this.path = path;
		this.uploadtime = uploadtime;
		this.uploaduserid = uploaduserid;
		this.uploadusername = uploadusername;
		this.remarks = remarks;
		
	}

	// Property accessors

	public Long getFileid() {
		return this.fileid;
	}

	public void setFileid(Long fileid) {
		this.fileid = fileid;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Timestamp getUploadtime() {
		return this.uploadtime;
	}

	public void setUploadtime(Timestamp uploadtime) {
		this.uploadtime = uploadtime;
	}

	public Long getUploaduserid() {
		return this.uploaduserid;
	}

	public void setUploaduserid(Long uploaduserid) {
		this.uploaduserid = uploaduserid;
	}

	public String getUploadusername() {
		return this.uploadusername;
	}

	public void setUploadusername(String uploadusername) {
		this.uploadusername = uploadusername;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

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

}