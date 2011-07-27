package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * LogUnicomOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LogUnicomOrder implements java.io.Serializable {

	// Fields

	private Integer id;
	private String recordseq;
	private Integer userIdType;
	private String userId;
	private String serviceType;
	private String spId;
	private String productId;
	private Integer updateType;
	private String updateTime;
	private String updateDesc;
	private String linkId;
	private String content;
	private String effectiveDate;
	private String expireDate;
	private String timeStamp;
	private String encodeStr;
	private java.sql.Timestamp createTime;
	private java.sql.Timestamp handleTime;
	private String result;
	private String remarks;

	// Constructors

	/** default constructor */
	public LogUnicomOrder() {
	}

	/** minimal constructor */
	public LogUnicomOrder(Integer id, Timestamp createTime) {
		this.id = id;
		this.createTime = createTime;
	}

	/** full constructor */
	public LogUnicomOrder(Integer id, String recordseq, Integer userIdType, String userId, String serviceType,
			String spId, String productId, Integer updateType, String updateTime, String updateDesc, String linkId,
			String content, String effectiveDate, String expireDate, String timeStamp, String encodeStr,
			Timestamp createTime, String result, String remarks) {
		this.id = id;
		this.recordseq = recordseq;
		this.userIdType = userIdType;
		this.userId = userId;
		this.serviceType = serviceType;
		this.spId = spId;
		this.productId = productId;
		this.updateType = updateType;
		this.updateTime = updateTime;
		this.updateDesc = updateDesc;
		this.linkId = linkId;
		this.content = content;
		this.effectiveDate = effectiveDate;
		this.expireDate = expireDate;
		this.timeStamp = timeStamp;
		this.encodeStr = encodeStr;
		this.createTime = createTime;
		this.result = result;
		this.remarks = remarks;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecordseq() {
		return this.recordseq;
	}

	public void setRecordseq(String recordseq) {
		this.recordseq = recordseq;
	}

	public Integer getUserIdType() {
		return this.userIdType;
	}

	public void setUserIdType(Integer userIdType) {
		this.userIdType = userIdType;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getSpId() {
		return this.spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getUpdateType() {
		return this.updateType;
	}

	public void setUpdateType(Integer updateType) {
		this.updateType = updateType;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateDesc() {
		return this.updateDesc;
	}

	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}

	public String getLinkId() {
		return this.linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getEncodeStr() {
		return this.encodeStr;
	}

	public void setEncodeStr(String encodeStr) {
		this.encodeStr = encodeStr;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	/**
	 * @return the handleTime
	 */
	public Timestamp getHandleTime() {
		return handleTime;
	}

	/**
	 * @param handleTime the handleTime to set
	 */
	public void setHandleTime(Timestamp handleTime) {
		this.handleTime = handleTime;
	}

}