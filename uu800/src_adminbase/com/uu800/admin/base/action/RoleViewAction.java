package com.uu800.admin.base.action;

import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.RoleService;
import com.uu800.admin.base.entity.TsysLogs;


/**
 *
 * <p>功能： 查看角色管理</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-05-22</p>
 * @版本： V1.0
 * @修改：
 */

public class RoleViewAction extends AbstractAdminAction {
	private TsysRole role;
    private long roleid;
    private RoleService service;
	public RoleViewAction() {
       service = (RoleService)this.getBean("roleService");
	   role = new TsysRole();
	}

	@Override
	public String execute()  {
           nextPage="roleList.action?pageNo="+pageNo;
           role=(TsysRole)service.get(TsysRole.class,roleid);
           if(role==null){
             message="未找到此角色管理";
//             log=new TsysLogs("查看角色:角色ID为 "+roleid,1,false);
             return "sysmsg";
           }
//           log=new TsysLogs("查看角色:角色ID为 "+roleid+" 角色名称为 "+role.getRolename(),1,true);
           
           set("role", role);
           return SUCCESS;
	}
	public TsysRole getRole() {
		return role;
	}
        public void setRoleid(long roleid) {

          this.roleid = roleid;
        }
        public long getRoleid() {
          return this.roleid;
        }

}
