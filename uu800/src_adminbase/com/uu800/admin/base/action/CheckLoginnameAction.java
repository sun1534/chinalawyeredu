package com.uu800.admin.base.action;

import com.uu800.admin.base.AbstractAdminAction;


/**
 * 验证登录名是否重复
 * 
 * @author 华锋
 * Mar 2, 20119:35:08 PM
 *
 */
public class CheckLoginnameAction extends AbstractAdminAction{

	private String loginname;
	private boolean isrepeat;
	public boolean getIsrepeat() {
		return isrepeat;
	}
	public void setLoginname(String loginname){
		this.loginname=loginname;
	}
	public String getLoginname() {
		return loginname;
	}
	
	public String execute()  {
//		SysUserService service = (SysUserService) this.getBean("sysUserService");
//		SysUser sysUser=service.getSysUserByLoginname(loginname);
//		isrepeat=false;
//		if(sysUser!=null)
//			isrepeat=true;	
		return "success_ajax";
	}

}
