package com.uu800.admin.base.action;


import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.RoleService;
import com.uu800.admin.base.entity.TsysLogs;




/**
 *
 * <p>功能： 删除角色管理</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-05-22</p>
 * @版本： V1.0
 * @修改：
 */

public class RoleDeleteAction extends AbstractAdminAction {

	private TsysRole role;
	private long roleid;
	private RoleService service;
	
	public RoleDeleteAction() {
           service = (RoleService)this.getBean("roleService");       
	}
	
	@Override
	public String execute() {
		nextPage="roleList.action";
		role=(TsysRole)service.get(TsysRole.class,roleid);
        if(role==null){
          message="未找到此角色";
//          log=new TsysLogs("删除角色:角色ID为"+roleid,5,false);  
          return "sysmsg";
        }
		TsysUser sysuser = (TsysUser) service.get(TsysUser.class,
				getUserinfo().getId());
		int type = this.getUserinfo().getUsertype();
		if (type > 2 && "SYS".equals(role.getRoletype())) {
			message = "你没权限删除此角色";
//			log=new TsysLogs("删除角色:角色ID为"+roleid,5,false);    		
        	return "sysmsg";
		}		
//		log=new TsysLogs("删除角色信息:职位ID为"+roleid+" 角色名称为"+role.getRolename(),5,true);
		
		service.delete(role);
        message="删除成功！";
        
		return SUCCESS;
	}

	
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
}
