/**
 * TSysUserAddAction.java
 */
package com.changpeng.system.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.system.service.SysRoleService;

/**
 * 
 * 用户信息删除
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysRoleDeleteAction extends AbstractAction {

	
    private int roleid;
	/**
	 * @param roleid the roleid to set
	 */
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}



	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		
		SysRoleService service=(SysRoleService)this.getBean("sysRoleService");
		//	int i=service.deleteRoles(check);
			
			int i=service.deleteRole(roleid);
			if(i==-1)
			{
				message="有属于该角色的用户,不能删除";
				this.opResult = "管理员" + super.getLoginUser().getLoginname() + "删除角色"+roleid+"但因为有用户关联未成功";
			}	
			else
			{
				message="删除1个角色信息成功";
				this.opResult = "管理员" + super.getLoginUser().getLoginname() + "删除角色"+roleid+"信息";
			}
			   
			this.nextPage="sysRoleList.action";
			// TODO Auto-generated method stub
			return SUCCESS;
	}
	@Override
	public String input() throws Exception {
		return INPUT;
	}
}
