/**
 * TSysUserAddAction.java
 */
package com.changpeng.system.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 删除律师
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class LawyerDeleteAction extends AbstractAction {

	
    private int[] check;
    public LawyerDeleteAction() {
        this.rightCode = "lawyerDelete";
       
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
		
		
		message="删除"+i+"个律师信息成功";
		this.nextPage="lawyerList.pl";
		// TODO Auto-generated method stub
		return SUCCESS;
	}
//	@Override
//	public String input() throws Exception {
//		return INPUT;
//	}
}
