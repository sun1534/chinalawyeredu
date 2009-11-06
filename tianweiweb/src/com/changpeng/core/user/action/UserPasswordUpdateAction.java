package com.changpeng.core.user.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;

public class UserPasswordUpdateAction extends AbstractAction{
	private String oldPwd;
	private String newPwd;
	private String confirmPwd;

	@Override
	protected String go() throws Exception {
		message ="修改密码成功！";
		UserService userService = (UserService)this.getBean("userService");
		
		String pwd = null;
		int id=0;
		CoreUser user=userService.getUserById(currentUserid);
		id=user.getId();
		pwd=user.getPwd();
		if(null == oldPwd || oldPwd.equals("")){
			message="旧密码输入不能为空！";
		}else if(null == newPwd || newPwd.equals("")){
			message="新密码输入不能为空！";
		}else if(null == confirmPwd || confirmPwd.equals("")){
			message="确认密码输入不能为空！";
		}else if(!oldPwd.equals(pwd)){
			message="旧密码输入有误！";
		}else if(!newPwd.equals(confirmPwd)){
			message="两次密码输入不一致！";
		}else if(newPwd.length()<6||newPwd.length()>13){
			message="密码长度为6~13位";
		}else{
			String Sql="update core_user set pwd='"+newPwd+"' where id="+id;
			userService.executeSql(Sql);
			message="密码设置成功!";
			this.redirectURL="../user/userpassword.action";
		}
		
		return SUCCESS;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	
}
