package com.sxit.models.mem;

/**
 * MemUserdeviceId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemUserdeviceId implements java.io.Serializable {

	// Fields

	private int userid;
	private int deviceid;

	// Constructors

	/** default constructor */
	public MemUserdeviceId() {
	}

	/** full constructor */
	public MemUserdeviceId(int userid, int deviceid) {
		this.userid = userid;
		this.deviceid = deviceid;
	}

	// Property accessors

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getDeviceid() {
		return this.deviceid;
	}

	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MemUserdeviceId))
			return false;
		MemUserdeviceId castOther = (MemUserdeviceId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this.getUserid()==castOther.getUserid()))
				&& ((this.getDeviceid() == castOther.getDeviceid()) || (  this.getDeviceid()==castOther.getDeviceid()));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUserid() == 0 ? 0 : this.getUserid());
		result = 37 * result + (getDeviceid() == 0 ? 0 : this.getDeviceid());
		return result;
	}

}