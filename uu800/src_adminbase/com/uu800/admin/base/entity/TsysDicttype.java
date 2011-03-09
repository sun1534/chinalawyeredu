package com.uu800.admin.base.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.List;

public class TsysDicttype implements Serializable
{
	private static final long serialVersionUID = 7223870199782624931L;
	
	// Fields
	private String dictType;
	private long createdBy;
	private Date creationDate;
	private long lastUpdatedBy;
	private Date lastUpdateDate;
	private String enabledFlag;
	private String dictName;
	private String opFlag;
	private Set<TsysDictvalue> tsysDictvalues;
	
	private List<TsysDictvalue> tempValues;

	// Constructors

	/** default constructor */
	public TsysDicttype() {
	}

	/** minimal constructor */
	public TsysDicttype(String dictType, long createdBy,
			Date creationDate, String enabledFlag, String dictName,
			String opFlag) {
		this.dictType = dictType;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.enabledFlag = enabledFlag;
		this.dictName = dictName;
		this.opFlag = opFlag;
	}

	/** full constructor */
	public TsysDicttype(String dictType, long createdBy,
			Date creationDate, long lastUpdatedBy, Date lastUpdateDate,
			String enabledFlag, String dictName, String opFlag) {
		this.dictType = dictType;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.enabledFlag = enabledFlag;
		this.dictName = dictName;
		this.opFlag = opFlag;
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
	 * 是否有效 | Y:有效;N:无效
	 * @return
	 */
	public String getEnabledFlag() {
		return this.enabledFlag;
	}

	/**
	 * 是否有效 | Y:有效;N:无效
	 * @param enabledFlag
	 */
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	/**
	 * 数据字典名称
	 * @return
	 */
	public String getDictName() {
		return this.dictName;
	}

	/**
	 * 数据字典名称
	 * @param dictName
	 */
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	/**
	 * 操作类型 | Y 可维护 N 不可维护
	 * @return
	 */
	public String getOpFlag() {
		return this.opFlag;
	}

	/**
	 * 操作类型 | Y 可维护 N 不可维护
	 * @param opFlag
	 */
	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}

	public List<TsysDictvalue> getTempValues() {
		return tempValues;
	}

	public void setTempValues(List<TsysDictvalue> tempValues) {
		this.tempValues = tempValues;
	}

	public Set<TsysDictvalue> getTsysDictvalues() {
		return tsysDictvalues;
	}

	public void setTsysDictvalues(Set<TsysDictvalue> tsysDictvalues) {
		this.tsysDictvalues = tsysDictvalues;
	}

}
