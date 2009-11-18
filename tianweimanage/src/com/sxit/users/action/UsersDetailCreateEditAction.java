package com.sxit.users.action;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.users.CoreUser;
import com.sxit.models.users.CoreUserDetail;

/**
 * 详细资料的新增修改
 * 
 * @author 华锋 Jul 9, 2009 11:18:05 PM
 * 
 */

public class UsersDetailCreateEditAction extends AbstractAction {

	public UsersDetailCreateEditAction() {

	}

	public String go() throws Exception {

		if(exist==1){
			basicService.update(userdetail);
		}else{
			basicService.save(userdetail);
		}
		CoreUser user = (CoreUser) basicService.get(CoreUser.class, userid);
		if (user.getUserRole() == 1) {// 1是家庭2是企业
			nextPage = "homeUsersList.action";
			this.message = "家庭用户详细信息保存成功";
		} else {
			nextPage = "corpUsersList.action";
			this.message = "企业用户详细信息保存成功";
		}
		return SUCCESS;
	}

	public String input() throws Exception {
		userdetail = (CoreUserDetail) basicService.get(CoreUserDetail.class, userid);
		user= (CoreUser) basicService.get(CoreUser.class, userid);
		if (userdetail == null) {
			userdetail = new CoreUserDetail();
			userdetail.setUserid(userid);
			exist=0;
		}else{
			exist=1;
		}
set("userdetail",userdetail);
		return INPUT;
	}

	private CoreUserDetail userdetail;

	private int userid;

	public CoreUserDetail getUserdetail() {
		if(userdetail==null)
			userdetail=(CoreUserDetail)get("userdetail");
		return userdetail;
	}


private CoreUser user;
public CoreUser getUser(){
	return user;
}
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	private int exist;

	public int getExist() {
		return exist;
	}

	public void setExist(int exist) {
		this.exist = exist;
	}
	
}
