/**
 * TSysUserAddAction.java
 */
package com.changpeng.system.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 管理员信息删除
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysUserDeleteAction extends AbstractAction {

	
    private int[] check;
    public SysUserDeleteAction() {
//        this.rightCode = "sysUserDelete";
       
    }
    
    private int groupid;
    

   

	/**
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	/**
     * 选中的checkbox的名称
     * @param check
     */
    public void setCheck(int[] check) {
        this.check = check;
    }
	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		
		SysUserService service=(SysUserService)this.getBean("sysUserService");
		int i=service.deleteUsers(check);
		
		message="删除"+i+"个管理员信息成功";
	
	if(groupid!=0){
		this.nextPage="sysGroupManager.pl?groupid="+groupid;
			
		}else
			
		this.nextPage="sysUserList.pl";
		// TODO Auto-generated method stub
		return SUCCESS;
	}
}
