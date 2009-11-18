package com.sxit.users.action.ajax;

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
		
		String hql="from CoreUser user where user.loginName='"+loginname+"'";
		java.util.List list=basicService.find(hql);
		isrepeat=false;
		if(list!=null&&list.size()!=0)
			isrepeat=true;	
		return SUCCESS;
	}

}
