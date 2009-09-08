/**
 * SysRoleAddAction.java
 */
package com.changpeng.system.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.SysRole;
import com.changpeng.system.service.SysRoleService;

/**
 * 
 * 用户信息修改
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysRoleEditAction extends AbstractAction {

	private SysRoleService service;
	
	private SysRole sysRole;
	private int roleid;
	
	public void setRoleid(int roleid){
		this.roleid=roleid;
	}
	
	public SysRole getSysRole(){
		if(sysRole==null)
			sysRole= (SysRole)get("sysRole");
		return sysRole;
		
	}

	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		service=(SysRoleService)this.getBean("sysRoleService");
		service.updateRole(sysRole);
		
		// TODO Auto-generated method stub
		this.nextPage="sysRoleList.pl";
		this.message="角色信息修改成功";
		return SUCCESS;
	}
	
	@Override
	public String input() throws Exception {
		service=(SysRoleService)this.getBean("sysRoleService");
		SysRole sysRole=service.getRole(roleid);
		this.set("sysRole", sysRole);
		return INPUT;
	}
}
