package com.sxit.memdevice.plugin;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginProcess {
	private static final Log log = LogFactory.getLog(LoginProcess.class);
	public LoginProcess(){	
	}	
	public String process(HttpServletRequest request){
		String result="false";	
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		try{
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}