package com.changpeng.sns.photo.model;

import java.sql.Timestamp;

/**
 * SnsPhotoDelLog entity. @author MyEclipse Persistence Tools
 */

public class SnsPhotoDelLog implements java.io.Serializable {

	// Fields

	private Integer photodelid;
	private Integer photoid;
	private Integer albumid;
	private Integer userid;
	private String createIp;
	private Timestamp createTime;
	private String photoName;
	private String url;
	private String smallUrl;
	private String description;
	private Timestamp deltime;

	// Constructors

	/** default constructor */
	public SnsPhotoDelLog() {
	}

	/** minimal constructor */
	public SnsPhotoDelLog(Integer photoid, Integer albumid, Integer userid,
			String createIp, Timestamp createTime, String photoName,
			String url, String smallUrl, Timestamp deltime) {
		this.photoid = photoid;
		this.albumid = albumid;
		this.userid = userid;
		this.createIp = createIp;
		this.createTime = createTime;
		this.photoName = photoName;
		this.url = url;
		this.smallUrl = smallUrl;
		this.deltime = deltime;
	}

	/** full constructor */
	public SnsPhotoDelLog(Integer photoid, Integer albumid, Integer userid,
			String createIp, Timestamp createTime, String photoName,
			String url, String smallUrl, String description, Timestamp deltime) {
		this.photoid = photoid;
		this.albumid = albumid;
		this.userid = userid;
		this.createIp = createIp;
		this.createTime = createTime;
		this.photoName = photoName;
		this.url = url;
		this.smallUrl = smallUrl;
		this.description = description;
		this.deltime = deltime;
	}

	// Property accessors

	public Integer getPhotodelid() {
		return this.photodelid;
	}

	public void setPhotodelid(Integer photodelid) {
		this.photodelid = photodelid;
	}

	public Integer getPhotoid() {
		return this.photoid;
	}

	public void setPhotoid(Integer photoid) {
		this.photoid = photoid;
	}

	public Integer getAlbumid() {
		return this.albumid;
	}

	public void setAlbumid(Integer albumid) {
		this.albumid = albumid;
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

	public String getPhotoName() {
		return this.photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
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

	public Timestamp getDeltime() {
		return this.deltime;
	}

	public void setDeltime(Timestamp deltime) {
		this.deltime = deltime;
	}

}