package com.uu800.admin.base.action;


import com.opensymphony.xwork2.Preparable;
import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysUser;
import com.uu800.admin.base.service.UserService;



/**
 *
 * <p>功能： 编辑用户管理</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-05-22</p>
 * @版本： V1.0
 * @修改：
 */

public class UserEditAction extends AbstractAdminAction implements Preparable {

	private TsysUser user;
	private long userid;
	private long orgid;
	private long jobid=-1;
	private String password="";
	private UserService service;
	
	private String oldpassword;
	private String password1;
	private String password2;
	
	public UserEditAction() {
        service = (UserService)this.getBean("userService");
        
        user = new TsysUser();
	}

	@Override
	public String execute()  {
//		log=new TsysLogs("编辑用户:用户ID为"+userid+" 用户名称为"+user.getUsername(),4,true);
		
	    if(password.length()>5)
	    {
	      user.setPassword(com.uu800.webbase.util.Md5.MD5(password));
	    }
	    user.setModifyuser(getUserinfo().getId());
	    user.setModifytime(new java.sql.Date(System.currentTimeMillis()));
		
	    service.update(user);        
        nextPage="userList.action?orgid="+orgid+"&jobid="+jobid;
        message="保存成功！";
		return SUCCESS;
	}
	public void prepare() throws Exception {		
		user = (TsysUser) service.get(TsysUser.class, userid);
	}	
   @Override
   public String input() throws Exception 
   {
	   nextPage="userList.action?orgid="+orgid;
	   if(user==null)
	   {
		   message="未找到该用户！";
		   return "sysmsg";
	   }
	   int type = getUserinfo().getUsertype();
		if(type>2&&user.getUsertype()<2)
		{
			message = "你没权限编辑此用户";
			return "sysmsg";
		}
		
		if(type>=2 && user.getTsysOrg().getOrgid()!=orgid)
		{
			message = "你没权限编辑别的组织的用户";
			return "sysmsg";
		}
	   
      return "input";
   }
   
   //修改密码
   public String modifyPassword() throws Exception
	{
	   oldpassword = getUserinfo().getPassword();
	   System.out.println("userinfoid="+getUserinfo().getUserid()+",oldpassword="+getUserinfo().getPassword());
	   
	   message = "密码修改成功！";
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getJobid() {
		return jobid;
	}

	public void setJobid(long jobid) {
		this.jobid = jobid;
	}

	public TsysUser getUser() {
		return user;
	}

}
