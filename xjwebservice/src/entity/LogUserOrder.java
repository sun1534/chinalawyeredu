package entity;

import java.sql.Timestamp;

/**
 * LogUserOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LogUserOrder implements java.io.Serializable {
//	 `id` int(10) NOT NULL AUTO_INCREMENT,                                        
//  `mobile` varchar(20) NOT NULL,                                               
//  `productid` varchar(30) DEFAULT NULL,                                        
//  `optype` int(10) DEFAULT NULL COMMENT '0订购1暂停2暂停恢复3退订',  
//  `useridtype` int(11) DEFAULT NULL,                                           
//  `streamno` varchar(30) DEFAULT NULL,                                         
//  `packageid` varchar(30) DEFAULT NULL,                                        
//  `remarks` varchar(200) DEFAULT NULL,                                         
//  `optime` datetime NOT NULL COMMENT '操作时间',   
	// Fields

	private Integer id;
	private String mobile;
	private String productid;
	private Integer optype;
	private Integer useridtype;
	private String streamno;
	private String packageid;
	private String remarks;
	private Timestamp optime;
	private Timestamp dotime;
	private String result;//订购信息的处理结果

	// Constructors

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



	/** default constructor */
	public LogUserOrder() {
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

	public String getProductid() {
		return this.productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public Integer getOptype() {
		return this.optype;
	}

	public void setOptype(Integer optype) {
		this.optype = optype;
	}

	public Integer getUseridtype() {
		return this.useridtype;
	}

	public void setUseridtype(Integer useridtype) {
		this.useridtype = useridtype;
	}

	public String getStreamno() {
		return this.streamno;
	}

	public void setStreamno(String streamno) {
		this.streamno = streamno;
	}

	public String getPackageid() {
		return this.packageid;
	}

	public void setPackageid(String packageid) {
		this.packageid = packageid;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Timestamp getOptime() {
		return this.optime;
	}

	public void setOptime(Timestamp optime) {
		this.optime = optime;
	}



	/**
	 * @return the dotime
	 */
	public Timestamp getDotime() {
		return dotime;
	}



	/**
	 * @param dotime the dotime to set
	 */
	public void setDotime(Timestamp dotime) {
		this.dotime = dotime;
	}

}