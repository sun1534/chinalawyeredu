/**
 * UserLoginAction.java
 */

package com.changpeng.common.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.Constants;
import com.changpeng.models.SysUser;
import com.changpeng.system.service.SysLoginLogService;
import com.changpeng.system.service.SysUserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用户登录
 * 
 * @author 华锋 2008-2-22 上午11:13:18
 * 
 */
public class UserLoginFromWebSiteAction extends AbstractAction {

	private static Log LOG = LogFactory.getLog(UserLoginFromWebSiteAction.class);
	private String lawyerno;
	private String sid;

	public UserLoginFromWebSiteAction() {
		this.needsession = false;
	}

	/**
	 * @return the lawyerno
	 */
	public String getLawyerno() {
		return lawyerno;
	}

	/**
	 * @param lawyerno
	 *            the lawyerno to set
	 */
	public void setLawyerno(String lawyerno) {
		this.lawyerno = lawyerno;
	}

	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}

	/**
	 * @param sid
	 *            the sid to set
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		this.nextPage = "index.pl";
		int guangxi = 22;
	
		ActionContext ctx = ActionContext.getContext();
		String contextid = ctx.getApplication().get("CONTEXTID").toString();
		SysUserService userService = (SysUserService) getBean("sysUserService");

//		SysUser sysuser = userService.getSysUserByLoginname(lawyerno);
		
		int loginResult = userService.userLogin(this.lawyerno);
		LOG.debug("登录验证结果:::" + loginResult);
		if (loginResult == -1) {
			message = "您输入的帐号在系统中不存在,请确认";
			return Constants.ACTION_MESSAGE;
		}
		if (loginResult == -2) {
			message = "您的帐号已被禁用,请联系管理员";
			return Constants.ACTION_MESSAGE;
		}
		if (loginResult == -3) {
			message = "您输入的密码错误,请返回重新输入";
			return Constants.ACTION_MESSAGE;
		}
		
		SysUser sysuser=userService.getSysUser();
//191399
//		if (sysuser == null) {
//			message = "该管理员帐号在培训系统中不存在,请确认";
//			return Constants.ACTION_MESSAGE;
//		}

//		String password = sysuser.getPassword();
//		String md5 =password;

//		if (!md5.equalsIgnoreCase(sid)) {
//			message = "系统登录验证失败,请联系管理员";
//			return Constants.ACTION_MESSAGE;
//		}
		//
		// if (pasivelogin != null && pasivelogin.equals("true")) {
		// LOG.debug("系统被动登录成功.............");
		// int loginid = loginService.insertLoginLog(_remoteAddr, lawyers,
		// contextid, "会话退出重新登录");
		// lawyers.setLoginId(loginid); // 绑定本次登录的登录id
		// this.set(Constants.LOGIN_USER, lawyers);
		// return "pasiveloginsuccess";
		// } else {

		SysLoginLogService loginService = (SysLoginLogService) getBean("sysLoginLogService");
		sysuser.setLoginCount(loginService.getLoginCountByUserId(sysuser.getUserid()));
		sysuser.setLastLoginTime(loginService.getLastLoginTime(sysuser.getUserid()));

		int loginid = loginService.insertLoginLog(sysuser, this.userIp, contextid, "从网站登录成功");
		sysuser.setLoginId(loginid); // 绑定本次登录的登录id
		LOG.debug("系统正常登录成功.............");
		this.set(Constants.LOGIN_USER, sysuser);
		return SUCCESS;
		// }

	}


}