package com.uu800.admin.base.action;

import org.hibernate.HibernateException;

import com.opensymphony.xwork2.Preparable;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.RoleService;
import com.uu800.admin.base.entity.TsysLogs;

/**
 * 
 * @功能： 编辑角色管理
 * @作者： zrb
 * @公司： 深圳信科
 * @日期： 2009-05-22
 * 
 * @版本： V1.0
 * @修改：
 */

public class RoleEditAction extends AbstractAdminAction implements Preparable {

	private TsysRole role;
	private long roleid;
	private RoleService service;
	public RoleEditAction() {
		
		service = (RoleService)this.getBean("roleService");
	}

	@Override
	public String execute() {
		
//		log=new TsysLogs("编辑角色:角色ID为"+roleid+" 角色名称为"+role.getRolename(),4,true);

		role.setModifytime(new java.sql.Date(System.currentTimeMillis()));
		role.setModifyuser(getUserinfo().getId());
		service.update(role);
		nextPage = "roleList.action";
		message = "保存成功！";
		return SUCCESS;
	}

	public void prepare() throws Exception {
		role = (TsysRole) service.get(TsysRole.class, roleid);
	}

	@Override
	public String input() throws Exception {
		nextPage = "roleList.action";
		TsysUser sysuser = (TsysUser) service.get(TsysUser.class,
				getUserinfo().getId());
//		int type = userinfo.getUsertype();
//		if (type >= 2 && "SYS".equals(role.getRoletype())) {
//			message = "你没权限编辑此角色";
//			return "sysmsg";
//		}
//
//		if (type >= 2
//				&& sysuser.getTsysOrg().getOrgid() != role.getTsysOrg()
//						.getOrgid()) {
//			message = "你没权限编辑别的组织的角色";
//			return "sysmsg";
//		}

		return "input";
	}

	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public TsysRole getRole() {
		return role;
	}

	public void setRole(TsysRole role) {
		this.role = role;
	}

}
