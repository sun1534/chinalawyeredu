/**
 * TSysUserAddAction.java
 */

package com.sxit.system.action;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.service.SysGroupService;

/**
 * 
 * 用户信息删除
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysGroupDeleteAction extends AbstractAction {

	private int groupid;

	public SysGroupDeleteAction() {
		this.rightCode = "sysGroupDelete";
	}
	/**
	 * 选中的checkbox的名称
	 * 
	 * @param check
	 */
	public void setGroupid(int check) {
		this.groupid = check;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		SysGroupService service = (SysGroupService) this.getBean("sysGroupService");
//		int i = service.deleteGroupLogic(groupid);
		int i=service.deleteGroup(groupid);
		if (i == -1) {
			message = "有属于此部门的人员,不能删除";
		}
		else if (i == -2) {
			message = "此部门有下级部门,不能删除";
		}
		else {
			message = "删除1个部门信息成功";
		}
		this.opResult=message;
//		this.nextPage="sysGroupTree.pl?upgroupid="+parentid;//这里要刷新父亲的树列表
//		this.message="部门信息新增成功";
		
//		return "toparent";
		
		this.nextPage="sysGroupList.action";
		//this.message="部门信息删除成功";
		
		return SUCCESS;
	}
	
	public int parentid;

	/**
	 * @return the parentid
	 */
	public int getParentid() {
		return parentid;
	}
	/**
	 * @param parentid the parentid to set
	 */
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	
	// @Override
	// public String input() throws Exception {
	// return INPUT;
	// }
}
