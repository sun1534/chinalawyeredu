/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysRole;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 用户信息修改
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysUserEditAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(SysUserEditAction.class);
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	private SysUserService service;

	private SysUser sysUser;
	private int userid;
	
//	private SysUser loginUser;
	private boolean isadminer; //是否为系统管理员
	
	public boolean getIsadminer(){
		return isadminer;
	}
//	public SysUser getLoginUser(){
//		return loginUser;
//	}
//	
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public SysUser getSysUser() {
		if (sysUser == null)
			sysUser = (SysUser) get("sysUser");
		return sysUser;

	}

	public SysUserEditAction() {
		service = (SysUserService) this.getBean("sysUserService");
		this.rightCode = "sysUserEdit";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		// sysUser.setComments(getLoginUser().getLoginname()+"最新修改于"+ df.format(new Date()));
		// _LOG.debug("=============sysUser:::"+sysUser);
		// _LOG.debug("=============service:::"+service);

		
		BasicService basicService=(BasicService) this.getBean("basicService");
		SysGroup sysGroup=null;
	
		if(groupid!=0)
			sysGroup=(SysGroup)basicService.get(SysGroup.class, groupid);
	
		sysUser.setSysGroup(sysGroup);
		
		service.updateUser(sysUser);

		this.message = "用户信息修改成功,请确认";
		if (addtype != null && this.addtype.equals("groupmanager")) {

			this.nextPage = "sysGroupManager.pl?groupid=" + groupid;
		}
		else
			this.nextPage = "sysUserList.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		this.sysUser = service.getUser(userid);

		set("sysUser", sysUser);
		
		SysUser loginUser=super.getLoginUser();
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
		if(sysUser.getSysGroup()!=null){
			this.groupid=sysUser.getSysGroup().getGroupid();
		    this.groupname = sysUser.getSysGroup().getGroupname();
		}else{
			this.groupname = "该用户没有对应的部门";
		}
		return INPUT;
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

	private int groupid;
	private String groupname;
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
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
}
