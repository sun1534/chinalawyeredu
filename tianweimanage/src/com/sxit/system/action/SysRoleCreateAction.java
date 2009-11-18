/**
 * TSysUserAddAction.java
 */
package com.sxit.system.action;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysRole;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysRoleService;

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
	
	}
	
	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
	
		
		SysRoleService service=(SysRoleService)this.getBean("sysRoleService");
		sysRole.setCreateuser(this.getLoginUser().getLoginname());
		sysRole.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		
		service.addRole(sysRole);
		this.nextPage="sysRoleList.action";
		this.message="角色创建成功";
		this.opResult = "管理员" + super.getLoginUser().getLoginname() + "新增了角色"+sysRole.getRolename()+"信息";
		return SUCCESS;
	}
//	@Override
//	public String input() throws Exception {
//		return INPUT;
//	}
}
