package entity;

import java.util.Date;

/**
 * LogMtsend entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmpMtsend implements java.io.Serializable {

	// Fields

	private int id;
	private String mobile;
	private String content;
	private Date sendTime;
	private String type;
	private String productId;
	private String result;
	private int sendcount;

	// Constructors

	/**
	 * @return the sendcount
	 */
	public int getSendcount() {
		return sendcount;
	}

	/**
	 * @param sendcount the sendcount to set
	 */
	public void setSendcount(int sendcount) {
		this.sendcount = sendcount;
	}

	/** default constructor */
	public TmpMtsend() {
	}

	/** minimal constructor */
	public TmpMtsend(int id, String mobile, Date sendTime) {
		this.id = id;
		this.mobile = mobile;
		this.sendTime = sendTime;
	}

	/** full constructor */
	public TmpMtsend(int id, String mobile, String content, Date sendTime, String result) {
		this.id = id;
		this.mobile = mobile;
		this.content = content;
		this.sendTime = sendTime;
		this.result = result;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}


}