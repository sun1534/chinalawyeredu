package com.changpeng.models;

import java.sql.Timestamp;

/**
 * SysLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LogClientRequest implements java.io.Serializable {

private int id;
private String method;
private int groupid;
private Timestamp reqtime;
private Timestamp restime;
private String reqcontent;
private String rescontent;
private String cpuid;
/**
 * @return the cpuid
 */
public String getCpuid() {
	return cpuid;
}
/**
 * @param cpuid the cpuid to set
 */
public void setCpuid(String cpuid) {
	this.cpuid = cpuid;
}
/**
 * @return the id
 */
public int getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
/**
 * @return the method
 */
public String getMethod() {
	return method;
}
/**
 * @param method the method to set
 */
public void setMethod(String method) {
	this.method = method;
}
/**
 * @return the groupid
 */
public int getGroupid() {
	return groupid;
}
/**
 * @param groupid the groupid to set
 */
public void setGroupid(int groupid) {
	this.groupid = groupid;
}
/**
 * @return the reqtime
 */
public Timestamp getReqtime() {
	return reqtime;
}
/**
 * @param reqtime the reqtime to set
 */
public void setReqtime(Timestamp reqtime) {
	this.reqtime = reqtime;
}
/**
 * @return the restime
 */
public Timestamp getRestime() {
	return restime;
}
/**
 * @param restime the restime to set
 */
public void setRestime(Timestamp restime) {
	this.restime = restime;
}
/**
 * @return the reqcontent
 */
public String getReqcontent() {
	return reqcontent;
}
/**
 * @param reqcontent the reqcontent to set
 */
public void setReqcontent(String reqcontent) {
	this.reqcontent = reqcontent;
}
/**
 * @return the rescontent
 */
public String getRescontent() {
	return rescontent;
}
/**
 * @param rescontent the rescontent to set
 */
public void setRescontent(String rescontent) {
	this.rescontent = rescontent;
}


}