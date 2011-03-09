package com.uu800.admin.base.action;


import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.UserService;
import com.uu800.admin.base.entity.TsysLogs;




/**
 *
 * <p>功能： 删除用户管理</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-05-22</p>
 * @版本： V1.0
 * @修改：
 */

public class UserDeleteAction extends AbstractAdminAction {

	private long userid;
	private long orgid;
	private UserService service;
	public UserDeleteAction() {
           service = (UserService)this.getBean("userService");
	}
	@Override
	public String execute() {		
	
		nextPage="userList.action?orgid="+orgid;
		int type = getUserinfo().getUsertype();
		
		TsysUser user=(TsysUser)service.get(TsysUser.class,userid);   

		if(type>=2&&user.getUsertype()<=2)
		{
			message = "你没权限删除此用户";
//			log=new TsysLogs("删除系统用户:用户ID为"+userid,5,false);
			return "sysmsg";
		}
		
		if(type>=2 && user.getTsysOrg().getOrgid()!=orgid)
		{
			message = "你没权限删除此用户";
//			log=new TsysLogs("删除系统用户:用户ID为"+userid,5,false);
			return "sysmsg";
		}
		
		if(user.getUsertype()==1)
		{
			message = "省管理员不可以删除";
//			log=new TsysLogs("删除系统用户:用户ID为"+userid,5,false);
			return "sysmsg";
		}
		
		if(user.getUserid()==1)
		{
			message = "系统管理员不可以被删除";
//			log=new TsysLogs("删除系统用户:用户ID为"+userid,5,false);    		
        	return "sysmsg";
		}	
		
//		log=new TsysLogs("删除系统用户:用户ID为"+userid+" 用户名称为"+user.getName(),5,true);
		
		service.delete(user);
        message="删除成功！";
        
		return SUCCESS;
	}


	public long getOrgid() {
		return orgid;
	}
	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
}
