/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.util.Iterator;
import java.util.List;

import com.changpeng.common.BasicService;
import com.changpeng.common.Constants;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysRole;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;
import com.changpeng.system.util.GroupTree;

/**
 * 
 * 新增用户信息,新增成功后,转到信息提示页面吧
 * 
 * 1、选择的group，必须是这个人所属的组以及下属组
 * 
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysUserCreateAction extends AbstractAction {

	private SysUser sysUser;
	
//	private SysUser loginUser;

	// public void setSysUser(SysUser sysUser){
	// this.sysUser=sysUser;
	// }
	private boolean isadminer; //是否为系统管理员
	
	public boolean getIsadminer(){
		return isadminer;
	}
//	public SysUser getLoginUser() {
//		return loginUser;
//	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public SysUserCreateAction() {
//		this.rightCode = "sysUserCreate";
		this.sysUser = new SysUser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		SysUserService service = (SysUserService) this.getBean("sysUserService");
		if(service.getSysUserByLoginname(sysUser.getLoginname())!=null){
			this.message="对不起，您输入的帐号【"+sysUser.getLoginname()+"】已经被他人使用。";
			return "message";
		}
		BasicService basicService = (BasicService) this.getBean("basicService");

		sysUser.setCreateuser(super.getLoginUser().getLoginname());
		sysUser.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		if (this.groupid == -1) {
			if (super.getLoginUser().getSysGroup() != null) {
				//SysGroup group = (SysGroup) basicService.get(SysGroup.class, this.getLoginUser().getSysGroup().getGroupid());
				sysUser.setSysGroup(super.getLoginUser().getSysGroup());
			}
		}
		else {
			SysGroup group = (SysGroup) basicService.get(SysGroup.class, groupid);
			sysUser.setSysGroup(group);
		}
		
		debug("addtype===="+addtype);

		// 新增事务所管理员的话，同时授权角色id为2给他
		if (addtype != null && "groupmanager".equals(addtype)) {
			service.addGroupManager(sysUser);
			this.nextPage = "sysGroupManager.pl?groupid="+groupid;
		}
		else {
			// sysUser.setPassword(password1);
			service.addUser(sysUser);
			this.nextPage = "sysUserList.pl";
		}
	
		this.message = "用户新增成功";
		return SUCCESS;
	}

	/**
	 * 这里要将部门信息放出来,树形结构显示
	 */
	@Override
	public String input() throws Exception {
	SysUser	loginUser=super.getLoginUser();

		if (loginUser.getLoginname().equals("admin"))
			this.isadminer = true;
		else {
			Iterator<SysRole> roles = loginUser.getSysRoles().iterator();
			short maxroleid = 0;
			while (roles.hasNext()) {
				SysRole role = roles.next();
				if (maxroleid <= role.getRoleid())
					maxroleid = role.getRoleid();
			}
			if (maxroleid == 3) {// 3的角色id是律协管理员
				isadminer = true;

			}
		}
		//debug("loginUser.getRoleid():"+loginUser.getRoleid());
		if (this.groupid == -1) {
			if (this.getLoginUser().getSysGroup() != null)
				this.groupname = this.getLoginUser().getSysGroup().getGroupname();
			else
				this.groupname = Constants.COMPANY_NAME;
		}
		else {
			BasicService basicService = (BasicService) this.getBean("basicService");
			SysGroup group = (SysGroup) basicService.get(SysGroup.class, groupid);
			this.groupname = group.getGroupname();
		}
		return INPUT;
	}

	private String passagain;

	public String getPassagain() {
		return this.passagain;
	}

	public void setPassagain(String passagain1) {
		this.passagain = passagain1;
	}

	private String groupname;
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
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

	// private String password1;
	// public String getPassword1(){
	// return this.password1;
	// }
	// public void setPassword1(String password1){
	// this.password1=password1;
	// }

}
