package com.cqmm.bean;

public class Command {
	public final static int TYPE_COMMON=1;
	public final static int TYPE_URGENT=2;
	public final static int TYPE_CUSTOMER=3;
	
	
	
	private int id;
	private String commandname;
	private int deviceid;
	private int commandtype;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommandname() {
		return commandname;
	}
	public void setCommandname(String commandname) {
		this.commandname = commandname;
	}
	public int getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}
	public int getCommandtype() {
		return commandtype;
	}
	public void setCommandtype(int commandtype) {
		this.commandtype = commandtype;
	}
	
	
	
}
