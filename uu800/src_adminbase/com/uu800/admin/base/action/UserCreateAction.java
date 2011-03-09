package com.uu800.admin.base.action;


import java.util.*;

import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.UserService;
import com.uu800.admin.base.entity.TsysLogs;
import com.uu800.webbase.util.*;

/**
 *
 * <p>功能： 创建用户管理</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-05-22</p>
 * @版本： V1.0
 * @修改：
 */

public class UserCreateAction extends AbstractAdminAction {

	private TsysUser user;
	private long orgid;
	private long jobid=-1;
	private LinkedHashMap orgmap;
	private LinkedHashMap usertypemap;
	private LinkedHashMap areacodemap;
	private String password;
	private UserService service;
	
	public UserCreateAction() {
        service = (UserService)this.getBean("userService");
		user = new TsysUser();
	}

	@Override
	public String execute()  {
//		log=new TsysLogs("新增用户信息: 用户名称为"+user.getUsername(),2,true);
		
		user.setCreatetime(new Date(System.currentTimeMillis()));
		user.setCreateuser(getUserinfo().getId());
		user.setPassword(com.uu800.webbase.util.Md5.MD5(password));
		
		if(getUserinfo().getUsertype()>1)
		{
			TsysUser cuser=(TsysUser)service.get(TsysUser.class,getUserinfo().getId());
			Set roles = new HashSet();
			roles.addAll(cuser.getTsysRoles());
			user.setTsysRoles(roles);
		}
		
		service.save(user);		
		
		nextPage="userList.action?orgid="+user.getTsysOrg().getOrgid()+"&jobid="+jobid;
        message="保存成功！";
		return SUCCESS;
	}

	public TsysUser getUser() {
		return user;
	}
    @Override
	public String input() throws Exception {
    	
    	TsysOrg org = (TsysOrg)service.get(TsysOrg.class,orgid);
    	
    	orgmap = new LinkedHashMap(); 
    	orgmap.put(org.getOrgid(), org.getOrgname());
    	
    	usertypemap =  new LinkedHashMap();
    	areacodemap =  new LinkedHashMap();
    	if(getUserinfo().getUsertype()<2)
    	{
    		usertypemap.put(1, "省级管理员");
    		usertypemap.put(2, "地市级管理员");
    		areacodemap = service.getAreaCodeList();
    		//areacodemap.put(userinfo.getAreacode(), userinfo.getAreaname());
    	}else
    	{
    		usertypemap.put(2, "地市级管理员");
    		areacodemap.put(getUserinfo().getAreacode(), getUserinfo().getAreaname());  		
    	}
        return "input";
    }

	public LinkedHashMap getOrgmap() {
		return orgmap;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getOrgid() {
		return orgid;
	}

	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}

	public LinkedHashMap getUsertypemap() {
		return usertypemap;
	}

	public void setUsertypemap(LinkedHashMap usertypemap) {
		this.usertypemap = usertypemap;
	}

	public long getJobid() {
		return jobid;
	}

	public void setJobid(long jobid) {
		this.jobid = jobid;
	}

	public LinkedHashMap getAreacodemap()
	{
		return areacodemap;
	}
	
	
}
