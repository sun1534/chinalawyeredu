package entity;

import java.util.Date;

/**
 * LogMtsend entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LogMtsend implements java.io.Serializable {

	// Fields

	private int id;
	private String mobile;
	private String content;
	private Date sendTime;
	private String result;

	// Constructors

	/** default constructor */
	public LogMtsend() {
	}

	/** minimal constructor */
	public LogMtsend(int id, String mobile, Date sendTime) {
		this.id = id;
		this.mobile = mobile;
		this.sendTime = sendTime;
	}

	/** full constructor */
	public LogMtsend(int id, String mobile, String content, Date sendTime, String result) {
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

}