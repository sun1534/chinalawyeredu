package com.sxit.models.content;

import java.sql.Timestamp;

/**
 * SnsPhoto entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SnsPhoto implements java.io.Serializable {

	// Fields

	private Integer photoid;
	private Integer albumid;
	private Integer userid;
	private String createIp;
	private Timestamp createTime;
	private String photoName;
	private String url;
	private String smallUrl;
	private String description;
	private Short statusid;

	// Constructors

	/** default constructor */
	public SnsPhoto() {
	}

	/** minimal constructor */
	public SnsPhoto(Integer albumid, Integer userid, String createIp, Timestamp createTime, String photoName, String url, String smallUrl, Short statusid) {
		this.albumid = albumid;
		this.userid = userid;
		this.createIp = createIp;
		this.createTime = createTime;
		this.photoName = photoName;
		this.url = url;
		this.smallUrl = smallUrl;
		this.statusid = statusid;
	}

	/** full constructor */
	public SnsPhoto(Integer albumid, Integer userid, String createIp, Timestamp createTime, String photoName, String url, String smallUrl,
			String description, Short statusid) {
		this.albumid = albumid;
		this.userid = userid;
		this.createIp = createIp;
		this.createTime = createTime;
		this.photoName = photoName;
		this.url = url;
		this.smallUrl = smallUrl;
		this.description = description;
		this.statusid = statusid;
	}

	// Property accessors

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

	public String getPic(){
		StringBuffer sb=new StringBuffer(com.sxit.common.sysdata.CommonData.DEFAULT_PIC_URL);
		if(url==null||url.equals(""))
			sb.append(com.sxit.common.sysdata.CommonData.DEFAULT_ALBUM_PIC);
		else if(url.startsWith("default"))
			sb.append(url);
		else
		    sb.append(com.sxit.common.util.FileDirUtil.getDirBySeq(albumid)).append(url);
		return sb.toString();
	}
	public String getSmallPic(){
		StringBuffer sb=new StringBuffer(com.sxit.common.sysdata.CommonData.DEFAULT_PIC_URL);
		if(smallUrl==null||smallUrl.equals(""))
			sb.append(com.sxit.common.sysdata.CommonData.DEFAULT_ALBUM_PIC);
		else if(smallUrl.startsWith("default"))
			sb.append(smallUrl);
		else
		    sb.append(com.sxit.common.util.FileDirUtil.getDirBySeq(albumid)).append(smallUrl);
		return sb.toString();

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