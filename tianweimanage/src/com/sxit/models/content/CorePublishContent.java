package com.sxit.models.content;

/**
 * CorePublishContent entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CorePublishContent implements java.io.Serializable {

	// Fields
//	2 照片  3 文字内容  4 音频视频
	private Integer id;
	private Integer publishid;
	
	private Integer serviceid;
	private Integer contentid;
	//对这个图片的祝福语
	private String remarks;
	//审核状态 0 待审核  1 审核通过  2 审核部通过
	private int statusid;

	// Constructors

	/** default constructor */
	public CorePublishContent() {
	}

	/** full constructor */
	public CorePublishContent(Integer publishid, Integer serviceid, Integer contentid) {
		this.publishid = publishid;
		this.serviceid = serviceid;
		this.contentid = contentid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPublishid() {
		return this.publishid;
	}

	public void setPublishid(Integer publishid) {
		this.publishid = publishid;
	}

	public Integer getServiceid() {
		return this.serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	public Integer getContentid() {
		return this.contentid;
	}

	public void setContentid(Integer contentid) {
		this.contentid = contentid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getStatusid() {
		return statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

}