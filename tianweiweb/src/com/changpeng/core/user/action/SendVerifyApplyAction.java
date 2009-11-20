package com.changpeng.core.user.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.util.WaitWork;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;

public class SendVerifyApplyAction extends AbstractAction{
	private int approve_type=0;
	
	protected String go() throws Exception {
		
		UserService userService = (UserService)this.getBean("userService");
		CoreUser user=userService.getUserById(currentUserid);
		
		CoreUserDetail up=userService.getUserDetailById(currentUserid);
		
		if(user.getUserName()==null||user.getUserName().equals("")){
		
			if(this.currentRole==1)
				this.message="请先填写姓名并保存";
			else
				this.message="请先填写法人代表并保存";
		}else if(up.getUserAddress()==null||up.getUserAddress().equals("")){
			this.message="请先填写地址并保存";
		}else if(user.getMobile()==null||user.getMobile().equals("")){
			this.message="请先填写好您的联系电话再保存";
		}else if(user.getCardno()==null||user.getCardno().equals("")){
			if(this.currentRole==1)
				this.message="请先填写身份证号码并保存";
			else
				this.message="请先填写营业执照号码并保存";
		}
		else if(user.getStatus().intValue()!=0){
			user.setStatus((short)2);
			user.setApproveType(approve_type);
			
			
			int waitid=WaitWork.Sendwait(currentUserid, "用户认证", userService);
			user.setWaitid(waitid);
			
			userService.update(user);
//			this.message="ok";
			this.message="您的身份认证提交成功,请点\"关闭\"关闭,或者点\"确定\"进入认证管理页面";
			this.redirectURL="../user/userverify.action";
		}
		return "success";
	}

	public void setApprove_type(int approve_type) {
		this.approve_type = approve_type;
	}
	
}
