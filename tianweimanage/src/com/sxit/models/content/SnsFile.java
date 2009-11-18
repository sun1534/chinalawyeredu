package com.sxit.models.content;

import java.sql.Timestamp;

/**
 * SnsFile entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SnsFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer dirid;
	//1是视频2是音频
	private Integer typeid;
	private Integer userid;
	private String createIp;
	private Timestamp createTime;
	private String fileName;
	private String url;
	private String smallUrl;
	private String description;
	private Short statusid;

	public String getTypename(){
		if(typeid==1)
			return "视频";
		else if(typeid==2)
			return "音频";
		else if(typeid==3)
			return "文档";
		return "其他";
	}
	// Constructors

	/** default constructor */
	public SnsFile() {
	}

	/** minimal constructor */
	public SnsFile(Integer dirid, Integer typeid, Integer userid, String createIp, Timestamp createTime, String fileName, String url, String smallUrl,
			Short statusid) {
		this.dirid = dirid;
		this.typeid = typeid;
		this.userid = userid;
		this.createIp = createIp;
		this.createTime = createTime;
		this.fileName = fileName;
		this.url = url;
		this.smallUrl = smallUrl;
		this.statusid = statusid;
	}

	/** full constructor */
	public SnsFile(Integer dirid, Integer typeid, Integer userid, String createIp, Timestamp createTime, String fileName, String url, String smallUrl,
			String description, Short statusid) {
		this.dirid = dirid;
		this.typeid = typeid;
		this.userid = userid;
		this.createIp = createIp;
		this.createTime = createTime;
		this.fileName = fileName;
		this.url = url;
		this.smallUrl = smallUrl;
		this.description = description;
		this.statusid = statusid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDirid() {
		return this.dirid;
	}

	public void setDirid(Integer dirid) {
		this.dirid = dirid;
	}

	public Integer getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getCreateIp() {
		return this.createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSmallUrl() {
		return this.smallUrl;
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getStatusid() {
		return this.statusid;
	}

	public void setStatusid(Short statusid) {
		this.statusid = statusid;
	}

}