/**
 * TSysUserAddAction.java
 */

package com.sxit.system.action;

import java.util.List;

import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysGroup;
import com.sxit.system.service.SysGroupService;

/**
 * 
 * 新增用户信息,新增成功后,转到信息提示页面吧
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysGroupCreateAction extends AbstractAction {

	private SysGroupService service;
	private SysGroup sysGroup;

	public SysGroup getSysGroup() {
		return this.sysGroup;
	}



	public SysGroupCreateAction() {
		this.rightCode = "sysGroupCreate";
		this.sysGroup = new SysGroup();
		this.service = (SysGroupService) getBean("sysGroupService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		// int parentid=sysGroup.getParentid();

		

		SysGroup parent = (SysGroup) service.get(sysGroup.getParentid());
		short grouplevel = 1;
		if (parent != null) {
			grouplevel = (short) (parent.getGrouplevel() + 1);
		}

		sysGroup.setCreateuser(getLoginUser().getLoginname());
		sysGroup.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		sysGroup.setGrouplevel(grouplevel);
	

		service.addGroup(sysGroup);

		// this.nextPage="sysGroupTree.pl?upgroupid="+parentid;//这里要刷新父亲的树列表
		this.nextPage = "sysGroupList.action";
		this.message = "部门信息新增成功";

		
		
		// return "toparent";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		BasicService bs = (BasicService) this.getBean("basicService");
		parentList = bs.findAll(SysGroup.class);

		return INPUT;
	}

	private List parentList;

	public List getParentList() {
		return parentList;
	}
}
