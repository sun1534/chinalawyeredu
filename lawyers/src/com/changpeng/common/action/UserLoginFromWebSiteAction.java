/**
 * UserLoginAction.java
 */

package com.changpeng.common.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.Constants;
import com.changpeng.lawyers.service.LawyerLoginLogService;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysUser;
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
		LawyersService userService = (LawyersService) getBean("lawyersService");

		Lawyers lawyers = userService.getLawyerbyLawyerno(lawyerno, guangxi, 0);
//191399
		if (lawyers == null) {
			message = "该执业帐号在培训系统中不存在,请确认";
			return Constants.ACTION_MESSAGE;
		}

		String password = lawyers.getPasswd();
		String md5 = com.changpeng.common.util.MD5.md5( password);

		if (!md5.equalsIgnoreCase(sid)) {
			message = "系统登录验证失败,请联系管理员";
			return Constants.ACTION_MESSAGE;
		}
		//
		// if (pasivelogin != null && pasivelogin.equals("true")) {
		// LOG.debug("系统被动登录成功.............");
		// int loginid = loginService.insertLoginLog(_remoteAddr, lawyers,
		// contextid, "会话退出重新登录");
		// lawyers.setLoginId(loginid); // 绑定本次登录的登录id
		// this.set(Constants.LOGIN_USER, lawyers);
		// return "pasiveloginsuccess";
		// } else {

		LawyerLoginLogService loginService = (LawyerLoginLogService) getBean("lawyerLoginLogService");
		lawyers.setLoginCount(loginService.getLoginCountByUserId(lawyers.getLawyerid()));
		lawyers.setLastLoginTime(loginService.getLastLoginTime(lawyers.getLawyerid()));

		int loginid = loginService.insertLoginLog(this.getIpAddr(), lawyers, contextid, "从网站登录成功");
		lawyers.setLoginId(loginid); // 绑定本次登录的登录id
		LOG.debug("系统正常登录成功.............");
		this.set(Constants.LOGIN_USER, lawyers);
		return SUCCESS;
		// }

	}

}