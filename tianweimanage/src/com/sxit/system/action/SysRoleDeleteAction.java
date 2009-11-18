/**
 * TSysUserAddAction.java
 */
package com.sxit.system.action;

import com.sxit.common.action.AbstractAction;
import com.sxit.system.service.SysRoleService;

/**
 * 
 * 角色信息删除
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysRoleDeleteAction extends AbstractAction {

	
   // private short[] check;
    private short roleid;
	public SysRoleDeleteAction() {
       
    }

    /**
     * 选中的checkbox的名称
     * @param check
     */
//    public void setCheck(short[] check) {
//        this.check = check;
//    }
	
	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
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
//	@Override
//	public String input() throws Exception {
//		return INPUT;
//	}

	public void setRoleid(short roleid) {
		this.roleid = roleid;
	}
}
