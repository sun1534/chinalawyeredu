package com.sxit.mem.models;

/**
 * MemUserdeviceId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemUserdeviceId implements java.io.Serializable {

	// Fields

	private Long userid;
	private Long deviceid;

	// Constructors

	/** default constructor */
	public MemUserdeviceId() {
	}

	/** full constructor */
	public MemUserdeviceId(Long userid, Long deviceid) {
		this.userid = userid;
		this.deviceid = deviceid;
	}

	// Property accessors

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getDeviceid() {
		return this.deviceid;
	}

	public void setDeviceid(Long deviceid) {
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

		return ((this.getUserid() == castOther.getUserid()) || (this.getUserid() != null
				&& castOther.getUserid() != null && this.getUserid().equals(castOther.getUserid())))
				&& ((this.getDeviceid() == castOther.getDeviceid()) || (this.getDeviceid() != null
						&& castOther.getDeviceid() != null && this.getDeviceid().equals(castOther.getDeviceid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result + (getDeviceid() == null ? 0 : this.getDeviceid().hashCode());
		return result;
	}

}