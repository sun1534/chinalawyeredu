package com.changpeng.sns.photo.model;

import java.sql.Timestamp;

/**
 * SnsPhotoAlbum entity. @author MyEclipse Persistence Tools
 */

public class SnsPhotoAlbum implements java.io.Serializable {

	// Fields

	private Integer albumid;
	private Integer userid;
	private Integer photoCount;
	private String privateFlag;
	private Timestamp createTime;
	private String albumName;
	private String url;
	private String remark;

	// Constructors

	/** default constructor */
	public SnsPhotoAlbum() {
	}

	/** minimal constructor */
	public SnsPhotoAlbum(Integer userid, Integer photoCount,
			String privateFlag, Timestamp createTime, String albumName,
			String url) {
		this.userid = userid;
		this.photoCount = photoCount;
		this.privateFlag = privateFlag;
		this.createTime = createTime;
		this.albumName = albumName;
		this.url = url;
	}

	/** full constructor */
	public SnsPhotoAlbum(Integer userid, Integer photoCount,
			String privateFlag, Timestamp createTime, String albumName,
			String url, String remark) {
		this.userid = userid;
		this.photoCount = photoCount;
		this.privateFlag = privateFlag;
		this.createTime = createTime;
		this.albumName = albumName;
		this.url = url;
		this.remark = remark;
	}

	// Property accessors

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

	public Integer getPhotoCount() {
		return this.photoCount;
	}

	public void setPhotoCount(Integer photoCount) {
		this.photoCount = photoCount;
	}

	public String getPrivateFlag() {
		return this.privateFlag;
	}

	public void setPrivateFlag(String privateFlag) {
		this.privateFlag = privateFlag;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getAlbumName() {
		return this.albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAlbumUrl() {
		StringBuffer sb = new StringBuffer(com.changpeng.common.sysdata.CommonData.DEFAULT_PIC_URL);

		if (url.startsWith("/upload/logo")){
			sb=new StringBuffer();
		}
		if (url == null || url.equals(""))
			sb.append(com.changpeng.common.sysdata.CommonData.DEFAULT_ALBUM_PIC);
		else if (url.startsWith("default"))
			sb.append(url);
		else if (url.startsWith("/upload/logo"))
			sb.append(url);
		
		else {
			// uid_photoid_s.***的形式
			try {
				int idx1 = url.indexOf("_");
				int idx2 = url.lastIndexOf("_");
				//int photoid = Integer.parseInt(url.substring(idx1 + 1, idx2));
				sb.append(com.changpeng.common.util.FileDirUtil.getDirBySeq(this.getAlbumid())).append(url);
			} catch (Exception e) {
				sb.append(com.changpeng.common.sysdata.CommonData.DEFAULT_ALBUM_PIC);
			}
		}
		return sb.toString();
	}

}