/**
 * TSysUserAddAction.java
 */

package com.sxit.system.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractAction;
import com.sxit.common.util.MD5;
import com.sxit.models.system.SysGroup;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysUserService;

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

	//fields
	private SysUserService service;
	private SysUser sysUser;
	private int userid;
	private String password;



	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		// BasicService basicService = (BasicService)
		// this.getBean("basicService");
		if(null!=password&&!"".equalsIgnoreCase(password)){
			String md5pass = MD5.md5(password);
			sysUser.setPassword(md5pass);
		}
		service.updateUser(sysUser);

		this.message = "用户信息修改成功,请确认";
		this.opResult = "管理员" + super.getLoginUser().getLoginname() + "修改了用户"+sysUser.getLoginname()+"信息";
		this.nextPage = "sysUserList.action";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		this.sysUser = service.getUser(userid);
		set("sysUser", sysUser);
		BasicService bs = (BasicService) this.getBean("basicService");
		parentList = bs.findAll(SysGroup.class);

		return INPUT;
	}
	

	// private Set<SysRole> roles;
	//
	// public Set<SysRole> getRoles() {
	// return this.roles;
	// }

	// private Set<Integer> roleids = new HashSet<Integer>();
	//
	// public Set<Integer> getRoleids() {
	// return this.roleids;
	// }

	
	//constructor
	
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
	
	


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	private List parentList;

	public List getParentList() {
		return parentList;
	}
}
