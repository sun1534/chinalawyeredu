package com.sxit.mem.models;

/**
 * MemUsercommoad entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemUsercommoad implements java.io.Serializable {

	// Fields

	private MemUsercommoadId id;

	// Constructors

	/** default constructor */
	public MemUsercommoad() {
	}

	/** full constructor */
	public MemUsercommoad(MemUsercommoadId id) {
		this.id = id;
	}

	// Property accessors

	public MemUsercommoadId getId() {
		return this.id;
	}

	public void setId(MemUsercommoadId id) {
		this.id = id;
	}

}