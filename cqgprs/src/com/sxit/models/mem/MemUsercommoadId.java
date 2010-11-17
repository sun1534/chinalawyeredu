package com.sxit.models.mem;

/**
 * MemUsercommoadId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemUsercommoadId implements java.io.Serializable {

	// Fields

	private int userid;
	private int commandid;

	// Constructors

	/** default constructor */
	public MemUsercommoadId() {
	}

	/** full constructor */
	public MemUsercommoadId(int userid, int commandid) {
		this.userid = userid;
		this.commandid = commandid;
	}

	// Property accessors

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getCommandid() {
		return this.commandid;
	}

	public void setCommandid(int commandid) {
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

		return ((this.getUserid() == castOther.getUserid()) || ( this.getUserid()==(castOther.getUserid())))
				&& ((this.getCommandid() == castOther.getCommandid()) || ( this.getCommandid()==(castOther.getCommandid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUserid() == 0 ? 0 : this.getUserid());
		result = 37 * result + (getCommandid() == 0 ? 0 : this.getCommandid());
		return result;
	}

}