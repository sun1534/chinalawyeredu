package com.sxit.users.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sxit.common.action.AbstractAction;
import com.sxit.models.users.CoreUser;

/**
 * 
 * @author 华锋 Jul 9, 2009 11:18:05 PM
 * 
 */

public class UsersCreateAction extends AbstractAction {

	public UsersCreateAction() {
		user = new CoreUser();
	}

	public String go() throws Exception {

		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);

		user.setModifyTime(new java.sql.Timestamp(System.currentTimeMillis()));
		user.setRegTime(user.getModifyTime());
		user.setRegtype((short)2);
		user.setRegIp(request.getRemoteAddr());
user.setStatus((short)0);
		basicService.save(user);

		if (user.getUserRole() == 1) {// 1是家庭2是企业
			nextPage = "homeUsersList.action";
			this.message = "家庭用户新增成功";
		} else {
			nextPage = "corpUsersList.action";
			this.message = "企业用户新增成功";
		}
		return SUCCESS;
	}

	private CoreUser user;

	public CoreUser getUser() {
		return this.user;
	}

	public String input() throws Exception {
	
		user.setUserRole(userRole);
		return "input";
	}

	private short userRole;

	public short getUserRole() {
		return userRole;
	}

	public void setUserRole(short userRole) {
		this.userRole = userRole;
	}

}
