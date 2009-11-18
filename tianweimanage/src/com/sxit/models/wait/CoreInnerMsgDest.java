package com.sxit.models.wait;

/**
 * CoreInnerMsgDest entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CoreInnerMsgDest implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer destUserid;
	private Integer msgId;
	private Short delflag;

	// Constructors

	/** default constructor */
	public CoreInnerMsgDest() {
	}

	/** full constructor */
	public CoreInnerMsgDest(Integer destUserid, Integer msgId, Short delflag) {
		this.destUserid = destUserid;
		this.msgId = msgId;
		this.delflag = delflag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDestUserid() {
		return this.destUserid;
	}

	public void setDestUserid(Integer destUserid) {
		this.destUserid = destUserid;
	}

	public Integer getMsgId() {
		return this.msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public Short getDelflag() {
		return this.delflag;
	}

	public void setDelflag(Short delflag) {
		this.delflag = delflag;
	}

}