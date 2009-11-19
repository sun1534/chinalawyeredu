package com.changpeng.core.user.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;

public class VerifyApplyAction extends AbstractAction{

	protected String go() throws Exception {
		UserService userService = (UserService)this.getBean("userService");
		CoreUser user=userService.getUserById(currentUserid);
		
		CoreUserDetail up=userService.getUserDetailById(currentUserid);
		
		if(user.getUserName()==null||user.getUserName().equals("")){
			this.message="请先填写姓名并保存";
			this.redirectURL="../user/userbaseview.action";
		}else if(up.getUserAddress()==null||up.getUserAddress().equals("")){
			this.message="请先填写地址并保存";
			this.redirectURL="../user/userbaseview.action";
		}else if(user.getMobile()==null||user.getMobile().equals("")){
			this.message="请先填写地址并保存";
			this.redirectURL="../user/userbaseview.action";
		}else if(user.getCardno()==null||user.getCardno().equals("")){
			if(this.currentRole==1)
				this.message="请先填写身份证号码并保存";
			else
				this.message="请先填写营业执照号码并保存";
			this.redirectURL="../user/userbaseview.action";
		}
		else 
		if(user.getStatus().intValue()==0){
			this.message="您已经通过了身份认证";
		}else if(user.getStatus().intValue()==2){
			this.message="您的身份认证正在审核中";
		}else{
			return "input";
		}
		return SUCCESS;
	}
	
}
