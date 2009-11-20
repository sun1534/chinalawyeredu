/**
 * IndexAction.java
 */
package com.sxit.common.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sxit.common.Constants;


/**
 * @author 华锋
 * 2009-1-5 下午10:31:14
 *
 */
public class IndexAction extends AbstractAction {
	private String loginname;
	private String password; 

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	public String go() throws Exception {

		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		Cookie[] cookies = request.getCookies();
		int length = cookies == null ? 0 : cookies.length;
		for (int i = 0; i < length; i++) {
			if (cookies[i].getName().equals(Constants.COOKIE_LOGINNAME)) {
//				this.loginname = cookies[i].getValue();
			}
			if (cookies[i].getName().equals(Constants.COOKIE_LOGINNAME)) {
//				this.password = cookies[i].getValue();
			}
		}
		return SUCCESS;
	}

	public String getPassword() {
		return this.password;
	}

	public String getLoginname() {
		return this.loginname;
	}
}