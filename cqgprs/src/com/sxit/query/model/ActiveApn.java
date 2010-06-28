/**
 * SubscriberData.java
 */
package com.sxit.query.model;

/**
 * @author 华锋
 * Jun 15, 20109:02:13 PM
 *
 */
public class ActiveApn {
//	Active PDP
//	----------------------------------------------------------------------
//	Id                                          : 3
	private String id;
//	NSAPI                                       : 5
	private String nsapi;
//	Type requested                              : Information not available
//	Address requested                           : Information not available
//	APN requested                               : Information not available
	private String apnRequest;
//	Addressing nature                           : Dynamic
	private String addressNature;
//	Address in use                              : 10.201.2.57
	private String addressInUse;
//	APN in use                                  : smjt.cq.mnc000.mcc460.gprs
	private String apnInUse;
	private String ggsnInUse;
//	GGSN in use                                 : 221.177.188.35
//	Quality of service requested                : 
//	    allocation/retention priority           : -
//	    delay class                             : 0
//	    reliability class                       : 0
//	    peak throughput (octet/s)               : 0
//	    precedence class                        : 0
//	    mean throughput (octet/h)               : 0
//	    traffic class                           : interactive
//	    delivery order                          : no
//	    delivery of erroneous SDU               : no
//	    maximum SDU size (octets)               : 1500
//	    maximum bit rate for uplink (kbps)      : 0
//	    maximum bit rate for downlink (kbps)    : 0
//	    residual BER                            : 1E-5
//	    SDU error ratio                         : 1E-6
//	    transfer delay (ms)                     : 4000
//	    traffic handling priority               : 0
//	    guaranteed bit rate for uplink (kbps)   : 0
//	    guaranteed bit rate for downlink (kbps) : 0
//	Quality of service negotiated               : 
//	    delay class                             : class3
//	    reliability class                       : Unack: GTP,LLC. Ack: RLC. Protected data
//	    peak throughput (octet/s)               : up to 32000
//	    precedence class                        : normal priority
//	    mean throughput (octet/h)               : best effort
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
	 * @return the nsapi
	 */
	public String getNsapi() {
		return nsapi;
	}
	/**
	 * @param nsapi the nsapi to set
	 */
	public void setNsapi(String nsapi) {
		this.nsapi = nsapi;
	}
	/**
	 * @return the addressNature
	 */
	public String getAddressNature() {
		return addressNature;
	}
	/**
	 * @param addressNature the addressNature to set
	 */
	public void setAddressNature(String addressNature) {
		this.addressNature = addressNature;
	}
	/**
	 * @return the addressInUse
	 */
	public String getAddressInUse() {
		return addressInUse;
	}
	/**
	 * @param addressInUse the addressInUse to set
	 */
	public void setAddressInUse(String addressInUse) {
		this.addressInUse = addressInUse;
	}
	/**
	 * @return the apnInUse
	 */
	public String getApnInUse() {
		return apnInUse;
	}
	/**
	 * @param apnInUse the apnInUse to set
	 */
	public void setApnInUse(String apnInUse) {
		this.apnInUse = apnInUse;
	}
	/**
	 * @return the ggsnInUse
	 */
	public String getGgsnInUse() {
		return ggsnInUse;
	}
	/**
	 * @param ggsnInUse the ggsnInUse to set
	 */
	public void setGgsnInUse(String ggsnInUse) {
		this.ggsnInUse = ggsnInUse;
	}
	/**
	 * @return the apnRequest
	 */
	public String getApnRequest() {
		return apnRequest;
	}
	/**
	 * @param apnRequest the apnRequest to set
	 */
	public void setApnRequest(String apnRequest) {
		this.apnRequest = apnRequest;
	}
}
