/**
 * GetLessonRequest.java
 */

package com.changpeng.service.szsync;

import java.text.DateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.Constants;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysLoginLogService;
import com.changpeng.system.service.SysUserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class LoginSyncRequest extends ElearningSyncRequests {

	private static final Log LOG = LogFactory.getLog(LoginSyncRequest.class);
	private static final DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public String requestSyncService(org.dom4j.Element rootElement) {

		StringBuilder result = new StringBuilder("");
		result.append("<response>");
	
		
		try {
			SysUserService  sysUserService= (SysUserService) globals.getBean("sysUserService");
		
			String loginName=rootElement.elementText("loginame");
			String password=rootElement.elementText("password");
			
			int loginResult=sysUserService.userLogin(loginName, password);
			
			result.append("<respcode>").append(loginResult).append("</respcode>");
			String message="";
			if (loginResult == -1) {
				message = "输入的帐号在系统中不存在,请确认";
			}
			else if (loginResult == -2) {
				message = "帐号已被禁用,请联系管理员";
			}
			if (loginResult == -3) {
				message = "输入的密码错误,请返回重新输入";
			}else {
				
				SysUser sysUser = sysUserService.getSysUser();
				SysLoginLogService loginService = (SysLoginLogService) globals.getBean("sysLoginLogService");
				ActionContext ctx = ActionContext.getContext();
				HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
				String _remoteAddr = request.getRemoteAddr();
				String contextid = ctx.getApplication().get("CONTEXTID").toString();
				loginService.insertLoginLog(_remoteAddr, sysUser.getUserid(), contextid, "从律协网站正常登录");
				
				
				sysUser.setLoginId(loginService.getLoginId()); // 绑定本次登录的登录id
				
				
				
				message="登录成功";

			}
			result.append("<respmsg>网站登录:").append(message).append("</respmsg>");
		
		}
		catch (Exception e) {
			result.append("<respcode>-1</respcode>");
			result.append("<respmsg>登录异常:").append(e.getMessage()).append("</respmsg>");
		}

		result.append(result);
		result.append("</response>");
		return result.toString();
	}
}