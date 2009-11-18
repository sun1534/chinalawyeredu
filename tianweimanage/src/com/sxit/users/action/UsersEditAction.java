package com.sxit.users.action;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.users.CoreUser;

/**
 * 
 * @author 华锋 Jul 9, 2009 11:30:31 PM
 * 
 */

public class UsersEditAction extends AbstractAction {

	public UsersEditAction() {

	}

	public String go() throws Exception {

		user.setModifyTime(new java.sql.Timestamp(System.currentTimeMillis()));

		basicService.update(user);

		if (user.getUserRole() == 1) {// 1是家庭2是企业
			nextPage = "homeUsersList.action";
			this.message = "家庭用户修改成功";
		} else {
			nextPage = "corpUsersList.action";
			this.message = "企业用户修改成功";
		}
		return SUCCESS;
	}

	private CoreUser user;

	public CoreUser getUser() {
		if (user == null)
			user = (CoreUser) get("user");
		return user;
	}

	public String input() throws Exception {
		CoreUser user = (CoreUser) basicService.get(CoreUser.class, userid);
		set("user", user);
		return "input";
	}

	private int userid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
