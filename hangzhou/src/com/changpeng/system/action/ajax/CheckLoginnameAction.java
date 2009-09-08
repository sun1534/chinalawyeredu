package com.changpeng.system.action.ajax;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;
//import com.changpeng.models.system.*;
public class CheckLoginnameAction extends AbstractAction{

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
	@Override
	protected String go() throws Exception {
		SysUserService service = (SysUserService) this.getBean("sysUserService");
		SysUser sysUser=service.getSysUserByLoginname(loginname);
		isrepeat=false;
		if(sysUser!=null)
			isrepeat=true;	
		return SUCCESS;
	}

}
