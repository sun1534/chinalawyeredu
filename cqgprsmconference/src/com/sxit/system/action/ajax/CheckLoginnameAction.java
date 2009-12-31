package com.sxit.system.action.ajax;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysUserService;
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
