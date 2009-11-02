/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.action.UserLoginAction;
import com.changpeng.models.system.SysRole;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysRoleService;
import com.changpeng.system.service.SysUserService;

/**
 * 
 * 新增用户所属的角色，先显示所有的角色，checkbox选中了的为已有的
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysUserRoleAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(SysUserRoleAction.class);
	private int userid;
	private SysUserService service;

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getUserid() {
		return this.userid;
	}

	public SysUserRoleAction() {
		this.rightCode = "sysUserRole";
		service = (SysUserService) this.getBean("sysUserService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		LOG.debug("选择的roles::::" + this.sysRoles+",this.Userid==="+this.userid);
//		if (this.sysRoles != null && this.sysRoles.size() != 0) {
			service.assignRoles(this.userid, this.sysRoles);
//		}
		this.message="角色分配成功";
		this.nextPage = "sysUserList.pl";
		return SUCCESS;
	}

	private List sysRoles = new ArrayList();
	public void setSysRoles(List sysRoles) {
		this.sysRoles = sysRoles;
	}
	public List getSysRoles() {
		return this.sysRoles;
	}
	private Map roles = new HashMap();
	public Map getRoles() {
		return this.roles;
	}
	@Override
	public String input() throws Exception {
		SysUser user = service.getUser(userid);
		Set<SysRole> _sysRoles = user.getSysRoles();
		if (_sysRoles != null) {
			Iterator<SysRole> roleIterator = _sysRoles.iterator();
			while (roleIterator.hasNext()) {
				SysRole role = roleIterator.next();
				sysRoles.add(role.getRoleid());
			}
		}
		SysRoleService roleService = (SysRoleService) this.getBean("sysRoleService");
		List allroles = roleService.getRoles();
		for (int i = 0; allroles != null && i < allroles.size(); i++) {
			SysRole role = (SysRole) allroles.get(i);
			roles.put(role.getRoleid(), role.getRolename());
		}

		return INPUT;
	}
}