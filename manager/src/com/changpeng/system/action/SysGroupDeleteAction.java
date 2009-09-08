/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.system.service.SysGroupService;

/**
 * 
 * 用户信息删除
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysGroupDeleteAction extends AbstractAction {

	private int groupid;
	private String type;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public SysGroupDeleteAction() {

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
		// int i = service.deleteGroupLogic(groupid);
		int i = service.deleteGroup(groupid);
		if (i == -1) {
			message = "有属于此部门的人员,不能删除";
		} else if (i == -2) {
			message = "此部门有下级部门,不能删除";
		} else {
			message = "删除1个部门信息成功";
		}
		this.opResult = message;

		if (type != null) {

			if (type.equals("1"))
				this.nextPage = "theOfficeList.pl";
			else if (type.equals("2"))
				this.nextPage = "theUnionList.pl";
		} else

			this.nextPage = "sysGroupList.pl";

		return SUCCESS;
	}

	// public int parentid;

	// /**
	// * @return the parentid
	// */
	// public int getParentid() {
	// return parentid;
	// }
	// /**
	// * @param parentid the parentid to set
	// */
	// public void setParentid(int parentid) {
	// this.parentid = parentid;
	// }

	// @Override
	// public String input() throws Exception {
	// return INPUT;
	// }
}
