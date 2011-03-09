package com.uu800.admin.base.action;

import java.util.HashSet;
import java.util.List;
import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.UserService;
import com.uu800.admin.base.entity.TsysLogs;

/**
 * 
 * 功能： 编辑用户角色
 * 作者： zrb
 * 公司： 深圳信科
 * 日期： 2009-05-22
 * @版本： V1.0
 * @修改：
 */

public class UserRoleEditAction extends AbstractAdminAction{

	private long userid;
	private Long[] check;
	private TsysUser user;
	private List<TsysRole>  rolelist;
	private UserService service;

	public UserRoleEditAction() {
		service = (UserService)this.getBean("userService");
	}

	@Override
	public String execute() throws Exception {
		
		user=(TsysUser)service.get(TsysUser.class,userid);
		
//		log=new TsysLogs("编辑用户角色:用户ID为"+userid+" 用户名称为"+user.getUsername(),4,true);
	
		service.updateUserRoles(user, check);
	
		
		nextPage="userList.action?orgid="+user.getTsysOrg().getOrgid();
		message = "保存成功！";
		return SUCCESS;
	}

	public TsysUser getUser() {
		return user;
	}

	@Override
	public String input() throws Exception {
		user=(TsysUser)service.get(TsysUser.class,userid);
		nextPage="userList.action?orgid="+user.getTsysOrg().getOrgid();
		
		TsysUser cursysuser=(TsysUser)service.get(TsysUser.class,getUserinfo().getUserid());
		
        if(user==null){
          message="未找到此用户";
          return "sysmsg";
        }
        
// 	    int type = userinfo.getUsertype();
//		if(type>=2&&user.getUsertype()<=2)
//		{
//			message = "你没权限编辑此用户";
//			return "sysmsg";
//		}
		
//		if(type>=2 && user.getTsysOrg().getOrgid()!=cursysuser.getTsysOrg().getOrgid())
//		{
//			message = "你没权限编辑别的组织的用户";
//			return "sysmsg";
//		}
		//当前用户所具有的权限
//		HashSet<String> userinforights =(HashSet<String>) get("rights");		
		
		rolelist=service.getUserRoleList(user);

		return "input";
	}


	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public void setUser(TsysUser user) {
		this.user = user;
	}

	public List<TsysRole> getRolelist() {
		return rolelist;
	}

	public void setCheck(Long[] check) {
		this.check = check;
	}

}
