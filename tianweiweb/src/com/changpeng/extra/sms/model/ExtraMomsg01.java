package com.changpeng.extra.sms.model;

/**
 * ExtraMomsg01 entity. @author MyEclipse Persistence Tools
 */

public class ExtraMomsg01 implements java.io.Serializable {

	// Fields

	private ExtraMomsg01Id id;

	// Constructors

	/** default constructor */
	public ExtraMomsg01() {
	}

	/** full constructor */
	public ExtraMomsg01(ExtraMomsg01Id id) {
		this.id = id;
	}

	// Property accessors

	public ExtraMomsg01Id getId() {
		return this.id;
	}

	public void setId(ExtraMomsg01Id id) {
		this.id = id;
	}

}