package com.sxit.memdevice.command;

public interface Command {
	String getresult(String orgstr);
	String getresult(String ip,String username,String pwd,String devicename);
}
