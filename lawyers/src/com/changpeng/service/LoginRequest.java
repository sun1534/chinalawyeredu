/**
 * LoginRequest.java
 */

package com.changpeng.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 华锋 2008-5-12 下午03:12:13
 * 
 */
public class LoginRequest extends ElearningRequests {
	private static final Log LOG = LogFactory.getLog(LoginRequest.class);

	
	private int groupid;
	public LoginRequest(int groupid){
		this.groupid=groupid;
	}
	
	public String requestService(org.dom4j.Element method) {
		StringBuilder result = new StringBuilder("");
		result.append("<response>");
		try {
			String loginName = method.elementText("loginname");
			String password = method.elementText("password");
			LOG.debug("loginname=" + loginName + ",password=" + password+",groupid="+groupid);
//			SysUserService userservice = (SysUserService) globals.getBean("sysUserService");
//			
//			LOG.debug("====="+userservice);
//			
//			int s = userservice.userLogin(loginName, password);
//			String msg = "";
//			if (s == -1) {
//				msg = "帐号不存在";
//			}
//			else if (s == -2) {
//				msg = "帐号被禁用";
//			}
//			else if (s == -3) {
//				msg = "密码错误";
//			}
//			else {
//				msg = "登录成功";
//			}
			int s=10000;
			String msg="登录成功";
			result.append("<respcode>").append(s).append("</respcode>");
			result.append("<respmsg>").append(msg).append("</respmsg>");
		}
		catch (Exception e) {
			result.append("<respcode>").append(-4).append("</respcode>");
			result.append("<respmsg>登录异常:").append(e.getMessage()).append("</respmsg>");
		}
		result.append("</response>");
		return result.toString();
//		return null;
	}
}