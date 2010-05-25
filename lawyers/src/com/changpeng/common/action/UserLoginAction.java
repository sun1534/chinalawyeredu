/**
 * UserLoginAction.java
 */

package com.changpeng.common.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.Constants;
import com.changpeng.lawyers.service.LawyerLoginLogService;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Lawyers;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用户登录
 * 
 * @author 华锋 2008-2-22 上午11:13:18
 * 
 */
public class UserLoginAction extends AbstractAction {

	private static Log LOG = LogFactory.getLog(UserLoginAction.class);

	private String loginname;
	private String password;
	private String randnum;

	public UserLoginAction() {
		this.needsession = false;
	}

	/**
	 * 如果是被动登录的话,此值不为空
	 */
	private String pasivelogin;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		this.nextPage = "index.pl";
		
		

		ActionContext ctx = ActionContext.getContext();
		String contextid = ctx.getApplication().get("CONTEXTID").toString();
//		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		String _remoteAddr = super.getIpAddr();
		Object obj = get(Constants.VALIDATE_CODE);
		LOG.debug("===================验证码:::" + obj);
//		if (!_remoteAddr.startsWith("127.0.0")) {
//			// 验证码为空
//			if (obj == null) {
//				message = "验证码已经过期,请返回重新输入";
//				return Constants.ACTION_MESSAGE;
//			}
//			// 输入的验证码不符合要求
//			if (!((String) obj).equals(randnum)) {
//				message = "您输入的验证码错误,请返回重新输入";
//				return Constants.ACTION_MESSAGE;
//			}
//		}
		// 根据loginname得到用户个人信息
		// SysUserDAO dao=new SysUserDAO();
		LawyersService lawyersService = (LawyersService) getBean("lawyersService");

		int loginResult = lawyersService.userLogin(this.loginname.trim(), this.password.trim());
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

		Lawyers lawyers = lawyersService.getLawyers();

		LawyerLoginLogService loginService = (LawyerLoginLogService) getBean("lawyerLoginLogService");
		lawyers.setLoginCount(loginService.getLoginCountByUserId(lawyers.getLawyerid()));
		lawyers.setLastLoginTime(loginService.getLastLoginTime(lawyers.getLawyerid()));

		// return Constants.ACTION_MESSAGE;
		LOG.debug("系统每次启动后的标志ID:" + contextid);

		if (savecookie) {
			Cookie cookie = new Cookie(Constants.COOKIE_LOGINNAME, this.loginname);
			cookie.setMaxAge(Integer.MAX_VALUE);
			Cookie passwd = new Cookie(Constants.COOKIE_PASSWORD, this.password);
			passwd.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
			response.addCookie(passwd);
		}

		if (pasivelogin != null && pasivelogin.equals("true")) {
			LOG.debug("系统被动登录成功.............");
			int loginid = loginService.insertLoginLog(_remoteAddr, lawyers, contextid, "会话退出重新登录");
			lawyers.setLoginId(loginid); // 绑定本次登录的登录id
			this.set(Constants.LOGIN_USER, lawyers);
			return "pasiveloginsuccess";
		} else {

			int loginid = loginService.insertLoginLog(_remoteAddr, lawyers, contextid, "正常登录");
			lawyers.setLoginId(loginid); // 绑定本次登录的登录id
			LOG.debug("系统正常登录成功.............");
			this.set(Constants.LOGIN_USER, lawyers);
			return SUCCESS;
		}

	}

	public void setLoginname(String loginname) {
		this.loginname = loginname.trim();
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public void setRandnum(String randnum) {
		this.randnum = randnum;
	}

	public void setPasivelogin(String login) {
		this.pasivelogin = login.trim();;
	}

	private boolean savecookie;

	public void setSavecookie(boolean savecookie) {
		this.savecookie = savecookie;
	}
	

	public void setLoginRole(String loginRole){
		
	}
}