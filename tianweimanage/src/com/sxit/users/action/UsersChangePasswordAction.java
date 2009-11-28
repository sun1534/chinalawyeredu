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

public class UsersChangePasswordAction extends AbstractAction {

	private String newpass;
	private String newpassagain;
	
	private int userid;
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public UsersChangePasswordAction() {
		
	}

	public String go() throws Exception {

		if(newpass==null||newpass.equals("")){
			this.message="输入的密码不能为空,请重新输入";
			return "message";
		}
		
		if(newpassagain==null||!newpass.equals(newpassagain)){
			this.message="两次输入密码不一致,请重新输入";
			return "message";
		}
		
		CoreUser user = (CoreUser) basicService.get(CoreUser.class, userid);
		
		basicService.executeSql("update core_user set pwd='"+newpass+"' where id="+userid);
		
		this.message="您为用户\""+user.getUserName()+"\"修改密码成功";
		
		return SUCCESS;
	}





	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getNewpassagain() {
		return newpassagain;
	}

	public void setNewpassagain(String newpassagain) {
		this.newpassagain = newpassagain;
	}



}
