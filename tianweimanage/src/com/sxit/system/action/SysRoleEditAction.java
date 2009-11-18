/**
 * SysRoleAddAction.java
 */
package com.sxit.system.action;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysRole;
import com.sxit.system.service.SysRoleService;

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
	}
	
	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		
		service.updateRole(sysRole);
		
		// TODO Auto-generated method stub
		this.nextPage="sysRoleList.action";
		this.opResult = "管理员" + super.getLoginUser().getLoginname() + "修改了角色"+sysRole.getRolename()+"的信息";
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
