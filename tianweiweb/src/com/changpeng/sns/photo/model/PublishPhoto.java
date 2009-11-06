package com.changpeng.sns.photo.model;

import java.sql.Timestamp;

/**
 * SnsPhoto entity. @author MyEclipse Persistence Tools
 */

public class PublishPhoto implements java.io.Serializable {

	// Fields

	private Integer photoid;
	private Integer albumid;
	private String photoName;
	private String url;
	private String smallUrl;

	private String remark;
	
	private int contentid;
	// Constructors

	/** default constructor */
	public PublishPhoto() {
	}

	

	// Property accessors

	public Integer getPhotoid() {
		return this.photoid;
	}

	public void setPhotoid(Integer photoid) {
		this.photoid = photoid;
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
	
	public String getBigPic(){
		StringBuffer sb=new StringBuffer(com.changpeng.common.sysdata.CommonData.DEFAULT_PIC_URL);
		if(url==null||url.equals(""))
			sb.append(com.changpeng.common.sysdata.CommonData.DEFAULT_ALBUM_PIC);
		else if(url.startsWith("default"))
			sb.append(url);
		else
		    sb.append(com.changpeng.common.util.FileDirUtil.getDirBySeq(albumid)).append(url);
		return sb.toString();
	}
	public String getSmallPic(){
		StringBuffer sb=new StringBuffer(com.changpeng.common.sysdata.CommonData.DEFAULT_PIC_URL);
		if(smallUrl==null||smallUrl.equals(""))
			sb.append(com.changpeng.common.sysdata.CommonData.DEFAULT_ALBUM_PIC);
		else if(smallUrl.startsWith("default"))
			sb.append(smallUrl);
		else
		    sb.append(com.changpeng.common.util.FileDirUtil.getDirBySeq(albumid)).append(smallUrl);
		return sb.toString();
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public int getContentid() {
		return contentid;
	}



	public void setContentid(int contentid) {
		this.contentid = contentid;
	}



	public Integer getAlbumid() {
		return albumid;
	}



	public void setAlbumid(Integer albumid) {
		this.albumid = albumid;
	}
}