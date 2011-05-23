/**
 * TSysUserAddAction.java
 */
package com.changpeng.system.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysRole;
import com.changpeng.system.service.SysRoleService;

/**
 * 
 * 新增用户信息,新增成功后,转到信息提示页面吧
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysRoleCreateAction extends AbstractAction {

	private SysRole sysRole;
	
	public SysRole getSysRole() {
		return sysRole;
	}
	
	public SysRoleCreateAction(){
		this.sysRole=new SysRole();
		this.rightCode="sysRoleCreate";
	}
	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
	
		SysRoleService service=(SysRoleService)this.getBean("sysRoleService");
		sysRole.setCreateuser(this.getLoginUser().getLoginname());
		sysRole.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		
		service.addRole(sysRole);
		this.nextPage="sysRoleList.pl";
		this.message="角色创建成功";
		return SUCCESS;
	}
//	@Override
//	public String input() throws Exception {
//		return INPUT;
//	}
}
