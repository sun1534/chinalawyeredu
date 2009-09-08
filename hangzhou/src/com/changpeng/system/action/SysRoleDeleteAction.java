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

	
    private short[] check;
    public SysRoleDeleteAction() {
        this.rightCode = "sysRoleDelete";
       
    }

    /**
     * 选中的checkbox的名称
     * @param check
     */
    public void setCheck(short[] check) {
        this.check = check;
    }
	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		
		SysRoleService service=(SysRoleService)this.getBean("sysRoleService");
		int i=service.deleteRoles(check);
		
		
		message="删除"+i+"个角色信息成功";
		this.nextPage="sysRoleList.pl";
		// TODO Auto-generated method stub
		return SUCCESS;
	}
//	@Override
//	public String input() throws Exception {
//		return INPUT;
//	}
}
