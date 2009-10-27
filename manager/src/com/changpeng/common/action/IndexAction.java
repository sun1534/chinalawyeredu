/**
 * IndexAction.java
 */
package com.changpeng.common.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.Constants;
import com.changpeng.models.SysUser;
import com.changpeng.system.service.SysLoginLogService;
import com.changpeng.system.service.SysUserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author 华锋 2008-3-4 下午10:31:14
 * 
 */
public class IndexAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(IndexAction.class);
	private String loginname;
	private String password;
private String action;

	/**
 * @return the action
 */
public String getAction() {
	return action;
}

/**
 * @param action the action to set
 */
public void setAction(String action) {
	this.action = action;
}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);

		Cookie[] cookies = request.getCookies();
		String namepass = "";
		int length = cookies == null ? 0 : cookies.length;
		for (int i = 0; i < length; i++) {
			LOG.debug(cookies[i].getName()+"=>"+cookies[i].getValue()+"=>"+cookies[i].getDomain()+"=>"+cookies[i].getPath());
			if (cookies[i].getName().equals(Constants.COOKIE_LOGINNAME)) {
				this.loginname = cookies[i].getValue();
			}
			if (cookies[i].getName().equals(Constants.COOKIE_PASSWORD)) {
				this.password = cookies[i].getValue();
			}
			if (cookies[i].getName().equals("autologin")) {
				// this.password = cookies[i].getValue();
				namepass = cookies[i].getValue();
			}
		}
		// 解析这个namepass,得到登录名和密码
		
		if(namepass.equals(""))
			return "login";
		if(action!=null&&action.equals("logout"))
			return "login";
		String[] s = namepass.split(",");
		this.loginname = s[0];
		this.password = s[1];

		SysUserService userService = (SysUserService) getBean("sysUserService");
		// 自动实现登录
		int i = userService.userLogin(loginname);
		if (i<=0)
			return "login";
		else {
			SysUser sysUser=userService.getSysUser();
			String _remoteAddr = getIpAddr();

			String contextid = ctx.getApplication().get("CONTEXTID").toString();
			SysLoginLogService loginService = (SysLoginLogService) getBean("sysLoginLogService");
			sysUser.setLoginCount(loginService.getLoginCountByUserId(sysUser.getUserid()));
			sysUser.setLastLoginTime(loginService.getLastLoginTime(sysUser.getUserid()));

			LOG.debug("系统每次启动后的标志ID:" + contextid);

			loginService.insertLoginLog(sysUser, _remoteAddr, contextid, "自动登录");
			sysUser.setLoginId(loginService.getLoginId()); // 绑定本次登录的登录id
			LOG.debug("系统自动登录成功.............");
			this.set(Constants.LOGIN_USER, sysUser);
			return "toindex";

		}
	
		// return SUCCESS;
	}

	public String getPassword() {
		return this.password;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public String getIndexpic() {
		return Constants.INDEX_PIC;
	}
}