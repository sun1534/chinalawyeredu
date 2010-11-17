package com.sxit.models.mem;

/**
 * MemUserdevice entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemUserdevice implements java.io.Serializable {

	// Fields

	private MemUserdeviceId id;

	// Constructors

	/** default constructor */
	public MemUserdevice() {
	}

	/** full constructor */
	public MemUserdevice(MemUserdeviceId id) {
		this.id = id;
	}

	// Property accessors

	public MemUserdeviceId getId() {
		return this.id;
	}

	public void setId(MemUserdeviceId id) {
		this.id = id;
	}

}