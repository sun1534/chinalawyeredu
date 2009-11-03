/**
 * TSysUserAddAction.java
 */
package com.changpeng.system.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 用户信息删除
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
		
		
		message="删除"+i+"个用户信息成功";
		
		if(addtype!=null&&this.addtype.equals("groupmanager")){
			
			this.nextPage="sysGroupManager.pl?groupid="+groupid;
		}else
			
		this.nextPage="sysUserList.pl";
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	/**
	 * 是否新增事务所管理员
	 */
	private String addtype;

	/**
	 * @return the addtype
	 */
	public String getAddtype() {
		return addtype;
	}

	/**
	 * @param addtype
	 *            the addtype to set
	 */
	public void setAddtype(String addtype) {
		this.addtype = addtype;
	}
	private int groupid = -1;

	/**
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid
	 *            the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
//	@Override
//	public String input() throws Exception {
//		return INPUT;
//	}
}
