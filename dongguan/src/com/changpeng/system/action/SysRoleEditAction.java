/**
 * SysRoleAddAction.java
 */
package com.changpeng.system.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysRole;
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
	private short roleid;
	
	public void setRoleid(short roleid){
		this.roleid=roleid;
	}
	
	public SysRole getSysRole(){
		if(sysRole==null)
			sysRole= (SysRole)get("sysRole");
		return sysRole;
		
	}
	
	public SysRoleEditAction(){
		service=(SysRoleService)this.getBean("sysRoleService");
		this.rightCode="sysRoleEdit";
	}
	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		
		service.updateRole(sysRole);
		
		// TODO Auto-generated method stub
		this.nextPage="sysRoleList.pl";
		this.message="角色信息修改成功";
		return SUCCESS;
	}
	
	@Override
	public String input() throws Exception {
		SysRole sysRole=service.getRole(roleid);
		this.set("sysRole", sysRole);
		return INPUT;
	}
}
