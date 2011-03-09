package com.uu800.admin.base.action;

import java.util.LinkedHashMap;

import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.RoleService;
import com.uu800.admin.base.entity.TsysLogs;

/**
 * 
 * 功能： 创建角色管理
 * 作者： zrb
 * 公司： 深圳信科
 * 日期： 2009-05-22 
 * @版本： V1.0
 * @修改：
 */

public class RoleCreateAction extends AbstractAdminAction {

	private TsysRole role;
	private LinkedHashMap roletypemap;
	private LinkedHashMap rightobjmap;
	private long orgid;
	private RoleService service;
	
	public RoleCreateAction() {
		service = (RoleService)this.getBean("roleService");
		role = new TsysRole();
	}

	@Override
	public String execute()  {
//		log=new TsysLogs("新增角色: 角色名称为"+role.getRolename(),2,true);
		
		role.setOperatetype(1);
		role.setCreatetime(new java.sql.Date(System.currentTimeMillis()));
		role.setModifytime(new java.sql.Date(System.currentTimeMillis()));
		role.setCreateuser(getUserinfo().getId());
		role.setModifyuser(getUserinfo().getId());
		
		if(role.getRoletype()!=1)
		{
			role.setRightobj(0);
		}

		service.save(role);
		set("role", role);
		nextPage = "roleList.action";
		message = "保存成功！";
		return SUCCESS;
	}

	public TsysRole getRole() {
		return role;
	}

	@Override
	public String input() throws Exception {
		
    	roletypemap =  new LinkedHashMap();
    	
    	roletypemap.put("0", "运营商");    		
    	roletypemap.put("1", "企业版");
    	roletypemap.put("2", "个人版");    	
    	
    	rightobjmap  =  new LinkedHashMap();
    	rightobjmap.put("0", "管理员");    		
    	rightobjmap.put("1", "子帐号");
    	
    	TsysUser sysuser=(TsysUser)service.get(TsysUser.class,getUserinfo().getId());
    	//orgid = sysuser.getTsysOrg().getOrgid();
		return "input";
	}

	public LinkedHashMap getRoletypemap() {
		return roletypemap;
	}
	
	public LinkedHashMap getRightobjmap() {
		return rightobjmap;
	}

	public long getOrgid() {
		return orgid;
	}
}
