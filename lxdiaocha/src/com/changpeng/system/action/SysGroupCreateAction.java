/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.util.ArrayList;

import com.changpeng.common.Constants;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysGroupService;
import com.changpeng.system.service.SysUserService;

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

	private int parentid;

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int getParentid() {
		return this.parentid;
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
		
		if(this.getLoginUser().getSysGroup()!=null)
			this.parentid=this.getLoginUser().getSysGroup().getGroupid();
		else
			this.parentid=Constants.ROOT_GROUP;
		
		SysGroup parent = (SysGroup) service.get(parentid);
		short grouplevel = 1;
		if (parent != null) {
			grouplevel = (short) (parent.getGrouplevel() + 1);
		}

		sysGroup.setCreateuser(getLoginUser().getLoginname());
		sysGroup.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		sysGroup.setGrouplevel(grouplevel);
		sysGroup.setParentid(parentid);

		service.addGroup(sysGroup);
		if (!(adminer == null || adminer.equals(""))) {// 添加这个事务所的管理员

			SysUser sysUser = new SysUser();
			sysUser.setStatus((byte) 0);
			sysUser.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			sysUser.setCreateuser(this.getLoginUser().getLoginname());
			sysUser.setDelflag(false);
			sysUser.setLoginname(adminer);
			sysUser.setPassword("123456");
			sysUser.setSysGroup(sysGroup);
			SysUserService userService = (SysUserService) this.getBean("sysUserService");

//			userService.addUser(sysUser);
//			java.util.List roleids = new ArrayList();
//			roleids.add(2);// 分配事务所管理员的角色权限给他
//			userService.assignRoles(sysUser.getUserid(), roleids);
			
			userService.addGroupManager(sysUser);
		}

		// this.nextPage="sysGroupTree.pl?upgroupid="+parentid;//这里要刷新父亲的树列表
		this.nextPage = "sysGroupList.pl";
		this.message = "部门信息新增成功";

		// return "toparent";
		return SUCCESS;
	}

	private String adminer;

	public void setAdminer(String adminer) {
		this.adminer = adminer;
	}

	private String parentname;

	public String getParentname() {
		return this.parentname;
	}

	@Override
	public String input() throws Exception {
//		if (parentid == 0) {
//			this.parentname = Constants.COMPANY_NAME;
//		}
//		else {
//			SysGroup group = (SysGroup) service.get(parentid);
//			this.parentname = group.getGroupname();
//		}
		
	if(this.getLoginUser().getSysGroup()!=null)
		this.parentname=this.getLoginUser().getSysGroup().getGroupname();
	else
		this.parentname=Constants.COMPANY_NAME;
		
		return INPUT;
	}
}
