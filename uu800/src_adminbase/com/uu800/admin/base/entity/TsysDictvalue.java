package com.uu800.admin.base.entity;

import java.io.Serializable;
import java.util.Date;

public class TsysDictvalue implements Serializable 
{
	private static final long serialVersionUID = 7191797242513048353L;
	
	// Fields
	private long dictId;
	private long createdBy;
	private Date creationDate;
	private long lastUpdatedBy;
	private Date lastUpdateDate;
	private String enabledFlag;
	private String dictCode;
	private String dictName;
	private String dictType;

	// Constructors

	/** default constructor */
	public TsysDictvalue() {
	}

	/** minimal constructor */
	public TsysDictvalue(long dictId, long createdBy,
			Date creationDate, String enabledFlag, String dictCode,
			String dictName) {
		this.dictId = dictId;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.enabledFlag = enabledFlag;
		this.dictCode = dictCode;
		this.dictName = dictName;
	}

	/** full constructor */
	public TsysDictvalue(long dictId, long createdBy,
			Date creationDate, long lastUpdatedBy, Date lastUpdateDate,
			String enabledFlag, String dictCode, String dictName,
			String dictType) {
		this.dictId = dictId;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.enabledFlag = enabledFlag;
		this.dictCode = dictCode;
		this.dictName = dictName;
		this.dictType = dictType;
	}

	/**
	 * 数据字典值ID
	 * @return
	 */
	public long getDictId() {
		return this.dictId;
	}

	/**
	 * 数据字典值ID
	 * @param dictId
	 */
	public void setDictId(long dictId) {
		this.dictId = dictId;
	}

	public long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public long getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * 有效性 | Y:有效;N:无效
	 * @return
	 */
	public String getEnabledFlag() {
		return this.enabledFlag;
	}

	/**
	 * 有效性 | Y:有效;N:无效
	 * @param enabledFlag
	 */
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	/**
	 * 数据字典值代码
	 * @return
	 */
	public String getDictCode() {
		return this.dictCode;
	}

	/**
	 * 数据字典值代码
	 * @param dictCode
	 */
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	/**
	 * 数据字典值名称
	 * @return
	 */
	public String getDictName() {
		return this.dictName;
	}

	/**
	 * 数据字典值名称
	 * @param dictName
	 */
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	/**
	 * 数据字典代码
	 * @return
	 */
	public String getDictType() {
		return this.dictType;
	}

	/**
	 * 数据字典代码
	 * @param dictType
	 */
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

}
