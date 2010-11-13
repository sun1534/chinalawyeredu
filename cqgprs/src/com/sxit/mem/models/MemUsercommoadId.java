package com.sxit.mem.models;

/**
 * MemUsercommoadId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemUsercommoadId implements java.io.Serializable {

	// Fields

	private Long userid;
	private Long commandid;

	// Constructors

	/** default constructor */
	public MemUsercommoadId() {
	}

	/** full constructor */
	public MemUsercommoadId(Long userid, Long commandid) {
		this.userid = userid;
		this.commandid = commandid;
	}

	// Property accessors

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getCommandid() {
		return this.commandid;
	}

	public void setCommandid(Long commandid) {
		this.commandid = commandid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MemUsercommoadId))
			return false;
		MemUsercommoadId castOther = (MemUsercommoadId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this.getUserid() != null
				&& castOther.getUserid() != null && this.getUserid().equals(castOther.getUserid())))
				&& ((this.getCommandid() == castOther.getCommandid()) || (this.getCommandid() != null
						&& castOther.getCommandid() != null && this.getCommandid().equals(castOther.getCommandid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result + (getCommandid() == null ? 0 : this.getCommandid().hashCode());
		return result;
	}

}