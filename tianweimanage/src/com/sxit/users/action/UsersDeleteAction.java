package com.sxit.users.action;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.users.CoreUser;

/**
 * 
 * @author 华锋 Jul 9, 2009 11:23:13 PM
 * 
 */
public class UsersDeleteAction extends AbstractAction {

	public UsersDeleteAction() {

	}

	public String go() throws Exception {

		CoreUser user = (CoreUser) basicService.get(CoreUser.class, userid);
		basicService.delete(user);
		if (user.getUserRole() == 1) {// 1是家庭2是企业
			nextPage = "homeUsersList.action";
			this.message = "家庭用户信息删除成功";
		} else {
			nextPage = "corpUsersList.action";
			this.message = "企业用户信息删除成功";
		}
		return SUCCESS;
	}

	private int userid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
