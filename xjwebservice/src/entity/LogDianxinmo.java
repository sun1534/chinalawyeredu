package entity;

import java.sql.Timestamp;

/**
 * LogDianxinmo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LogDianxinmo implements java.io.Serializable {
//	 `id` int(10) NOT NULL AUTO_INCREMENT,                            
//  `mobile` varchar(20) NOT NULL COMMENT '发送手机',            
//  `destnumber` varchar(30) NOT NULL COMMENT '接收号码',        
//  `content` varchar(500) DEFAULT NULL COMMENT '内容',            
//  `create_time` datetime NOT NULL COMMENT '接收到mo的时间',  
//  `do_time` datetime DEFAULT NULL COMMENT '处理时间',          
//  `remarks` varchar(200) DEFAULT NULL COMMENT '备注信息',   
	// Fields

	private Integer id;
	private String mobile;
	private String destnumber;
	private String content;
	private Timestamp createTime;
	private Timestamp doTime;
	private String result;
	private String remarks;

	// Constructors

	/** default constructor */
	public LogDianxinmo() {
	}

	/** minimal constructor */
	public LogDianxinmo(Integer id, String mobile, String destnumber, Timestamp createTime) {
		this.id = id;
		this.mobile = mobile;
		this.destnumber = destnumber;
		this.createTime = createTime;
	}

	/** full constructor */
	public LogDianxinmo(Integer id, String mobile, String destnumber, String content, Timestamp createTime, Timestamp doTime,
			String remarks) {
		this.id = id;
		this.mobile = mobile;
		this.destnumber = destnumber;
		this.content = content;
		this.createTime = createTime;
		this.doTime = doTime;
		this.remarks = remarks;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDestnumber() {
		return this.destnumber;
	}

	public void setDestnumber(String destnumber) {
		this.destnumber = destnumber;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getDoTime() {
		return this.doTime;
	}

	public void setDoTime(Timestamp doTime) {
		this.doTime = doTime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

}