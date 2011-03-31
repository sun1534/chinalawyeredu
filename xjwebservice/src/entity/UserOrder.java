package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * UserOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class UserOrder implements java.io.Serializable {
//  `id` int(10) NOT NULL AUTO_INCREMENT,                                        
//  `mobile` varchar(20) NOT NULL,                                               
//  `productid` varchar(30) DEFAULT NULL,                                        
//  `status` int(10) DEFAULT NULL COMMENT '0正常状态1暂停状态',  
//  `useridtype` int(11) DEFAULT NULL,                                           
//  `streamno` varchar(30) DEFAULT NULL,                                         
//  `packageid` varchar(30) DEFAULT NULL,                                        
//  `remarks` varchar(200) DEFAULT NULL,   
//`chenpaihao` varchar(100) DEFAULT NULL COMMENT '车牌号',    
//`areacode` varchar(20) DEFAULT NULL COMMENT '区号',   
//`chepaileixing` varchar(50) DEFAULT NULL COMMENT '车牌类型：黄/蓝/黑',  
//		
//  `optime` datetime NOT NULL COMMENT '操作时间', 
	// Fields

	private Integer id;
	private String mobile;
	private String productid;
	private Integer status;
	private Integer useridtype;
	private String streamno;
	private String packageid;
	private String remarks;
	private String chepai;
	private String areacode;
	private String chepaileixing;
	private Timestamp optime;

	// Constructors

	/** default constructor */
	public UserOrder() {
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

	public String getChepai() {
		return this.chepai;
	}

	public void setChepai(String chepai) {
		this.chepai = chepai;
	}

	public String getAreacode() {
		return this.areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getChepaileixing() {
		return this.chepaileixing;
	}

	public void setChepaileixing(String chepaileixing) {
		this.chepaileixing = chepaileixing;
	}

	public Timestamp getOptime() {
		return this.optime;
	}

	public void setOptime(Timestamp optime) {
		this.optime = optime;
	}


	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}