package com.uu800.admin.base.action;

import java.util.HashSet;
import java.util.List;
import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.RoleService;
import com.uu800.admin.base.entity.TsysLogs;

/**
 * 
 * @功能： 编辑菜单管理 
 * @作者： zrb 
 * @公司： 深圳信科 
 * @日期： 2009-05-22 * 
 * @版本： V1.0
 * @修改：
 */

public class RoleRightEditAction extends AbstractAdminAction {

	private long roleid;
	private String[] check;
	private TsysRole role;
	private List<Right> rightlist;
	private RoleService service;

	public RoleRightEditAction() {
		service = (RoleService)this.getBean("roleService");
	}

	@Override
	public String execute()throws Exception  {
		
		
		role = (TsysRole) service.get(TsysRole.class, roleid);

//	    log=new TsysLogs("编辑角色权限:角色ID为"+roleid+" 角色名称为"+role.getRolename(),4,true);
		
		service.updateRoleRights(role, check);

		nextPage = "roleList.action?roletype="+role.getRoletype();
		message = "保存成功！";
		return SUCCESS;
	}

	public TsysRole getRole() {
		return role;
	}

	@Override
	public String input() throws Exception {
		nextPage = "roleList.action";
		role = (TsysRole) service.get(TsysRole.class, roleid);
		if (role == null) {
			message = "未找到此角色";
			return "sysmsg";
		}
       
		rightlist = service.getRoleRightList(role);
		
//		page.recordsize=rightlist.size();
		
		return "input";
	}

	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public void setRole(TsysRole role) {
		this.role = role;
	}

	public List<Right> getRightlist() {
		return rightlist;
	}

	public void setCheck(String[] check) {
		this.check = check;
	}

}
