/**
 * SubscriberData.java
 */
package com.sxit.query.model;

/**
 * @author 华锋
 * Jun 15, 20109:02:13 PM
 *
 */
public class SubscribedPDP {
//	Subscribed PDP
//	----------------------------------------------------------------------
//	Id                                          : 2
	private String id;
	//华为的正在使用的pdp的contextId
	private boolean isused;
//	Type                                        : IPv4
	private String iptype;
//	Address                                     : Dynamic
	private String address;
//	Quality of service                          : 
//	    allocation/retention priority           : level2
//	    delay class                             : class1
//	    reliability class                       : Unack: GTP,LLC. Ack: RLC. Protected data
//	    peak throughput (octet/s)               : up to 256000
//	    precedence class                        : normal priority
//	    mean throughput (octet/h)               : best effort
//	    traffic class                           : interactive
//	    delivery order                          : yes
//	    delivery of erroneous SDU               : no
//	    maximum SDU size (octets)               : 1500
//	    maximum bit rate for uplink (kbps)      : 2048
//	    maximum bit rate for downlink (kbps)    : 2048
//	    residual BER                            : 1E-5
//	    SDU error ratio                         : 1E-4
//	    transfer delay (ms)                     : 0
//	    traffic handling priority               : level1
//	    guaranteed bit rate for uplink (kbps)   : 0
//	    guaranteed bit rate for downlink (kbps) : 0
//	VPLMN allowed                               : false
//	APN                                         : cmmm
	private String subapn;
//	PDP Charging Characteristics                : 
//	PDP Charging Characteristics Profile        :
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the iptype
	 */
	public String getIptype() {
		return iptype;
	}
	/**
	 * @param iptype the iptype to set
	 */
	public void setIptype(String iptype) {
		this.iptype = iptype;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the apn
	 */
	public String getSubapn() {
		return this.subapn;
	}
	/**
	 * @param apn the apn to set
	 */
	public void setSubapn(String apn) {
		this.subapn = apn;
	}
	/**
	 * @return the usedId
	 */
	public boolean getIsused() {
		return isused;
	}
	/**
	 * @param usedId the usedId to set
	 */
	public void setIsused(boolean isused) {
		this.isused = isused;
	}
}
