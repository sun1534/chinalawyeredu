/**
 * TSysUserAddAction.java
 */

package com.sxit.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysGroup;
import com.sxit.models.system.SysRole;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysRoleService;
import com.sxit.system.service.SysUserService;

/**
 * 
 * 新增用户信息,新增成功后,转到信息提示页面吧
 * 
 * 默认所在的locationid为其所在的id
 * 
 * @author 华锋 2008-2-25 上午11:12:05 2009-3-11 Tompan 新增分配角色部分,默认为这个人所对应的角色
 * 
 */
public class SysUserCreateAction extends AbstractAction {

	private SysUser sysUser;
	private Map roles = new HashMap();

	public Map getRoles() {
		return this.roles;
	}

	public void setRoles(Map roles) {
		this.roles = roles;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public SysUserCreateAction() {
		// this.rightCode = "sysUserCreate";
		this.sysUser = new SysUser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * 这里还涉及到了所在地域的问题
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// System.out.println("sysUser.getLocationid():::"+sysUser.getLocationid()+"
		// RoleID:"+roleid);

		SysUserService service = (SysUserService) this.getBean("sysUserService");
		if (service.getSysUserByLoginname(sysUser.getLoginname()) != null) {
			this.message = "对不起，您输入的帐号【" + sysUser.getLoginname() + "】已经被他人使用。";
			return "message";
		}
		if (!sysUser.getPassword().equals(passagain)) {
			this.message = "两次密码输入不同,请重新输入!";
			return "message";
		}

		// 如果是省级管理员或者地市管理员
		// 加到sys_user_roles表里面
		// sysUser.setLocationid(this.getLoginUser().getLocationid());
		sysUser.setCreateuser(super.getLoginUser().getLoginname());
		sysUser.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		if (!(roleid != null && roleid.size() != 0)) {
			this.message = "您必须为该用户分配一个角色,不能为空!";
			return "message";

		}

		if (roleid != null && roleid.size() != 0) {
			service.addUser(sysUser, Short.parseShort(roleid.get(0).toString()));
		} else {
			service.addUser(sysUser);
		}

		this.nextPage = "sysUserList.action";
		this.opResult = "管理员" + super.getLoginUser().getLoginname() + "新增了用户" + sysUser.getLoginname();
		this.message = "用户新增成功";
		return SUCCESS;
	}

	/**
	 * 这里要将部门信息放出来,树形结构显示
	 * 
	 * 这里得到省一级的地市信息数据
	 */
	@Override
	public String input() throws Exception {

		BasicService bs = (BasicService) this.getBean("basicService");
		parentList = bs.findAll(SysGroup.class);
		
		// Tompan Add it 2009.03.11
		SysRoleService roleService = (SysRoleService) this.getBean("sysRoleService");
		List allroles = null;
		// List loginroleids = new ArrayList();
		if (!this.getLoginUser().getLoginname().equals("admin")) {
			SysRole sysrole = null;
			Set<SysRole> loginroles = this.getLoginUser().getSysRoles();
			if (loginroles != null && loginroles.size() != 0) { // 要是这个人没有角色，就不分配角色
				assignrole = true;
				for (SysRole role : loginroles) {
					sysrole = role;
					break;
				}
				roleid.add((int) sysrole.getRoleid());
			
				DetachedCriteria dc = DetachedCriteria.forClass(SysRole.class);
				Criterion c1 = Restrictions.gt("gradeid", sysrole.getGradeid());
				Criterion c2 = Restrictions.eq("roleid", sysrole.getRoleid());
				dc.add(Restrictions.or(c1, c2));
				allroles = bs.findAllByCriteria(dc);
			}
		} else { // admin的话，显示所有的角色
			allroles = roleService.getRoles();
			assignrole = true;
		}
		for (int i = 0; i < allroles.size(); i++) {
			SysRole role = (SysRole) allroles.get(i);
			roles.put((int) role.getRoleid(), role.getRolename());
		}

		return INPUT;
	}

	private List parentList;

	public List getParentList() {
		return parentList;
	}
	
	private String passagain;

	public void setPassagain(String passagain1) {
		this.passagain = passagain1;
	}

	private List roleid = new ArrayList();

	public List getRoleid() {
		return roleid;
	}

	public void setRoleid(List roleid) {
		this.roleid = roleid;
	}

	public boolean getAssignrole() {
		return assignrole;
	}

	private boolean assignrole;
}