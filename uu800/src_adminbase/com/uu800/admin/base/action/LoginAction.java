package com.uu800.admin.base.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.Menu;
import com.uu800.admin.base.entity.TsysUser;
import com.uu800.admin.base.service.LoginService;

public class LoginAction extends AbstractAdminAction 
{
	
	private static Log LOG = LogFactory.getLog(AbstractAdminAction.class);

	private TsysUser userinfo;
	private String username;
	private String password;
	private String randnum;
	private TsysUser sysuser;
	private LoginService service;
	
	public LoginAction()
	{		
		this.needLogin = false;
		service = (LoginService) this.getBean("loginService");		
	}	

	@Override
	public String execute() 
	{
		userinfo = service.Login(username,password);
     
     	
     	LOG.debug("userinfo====="+userinfo);
     	
		String rand = (String)get(com.uu800.webbase.VerifyCodeServlet.SESSION_KEY);
		
		LOG.debug("验证码:"+rand);

		if (rand == null || "".equals(rand)) 
		{
			message = "此次登录已经过期，请重新登录！";
			return "sysmsg";
		}

		if (!randnum.equals(rand))
		{
			message = "验证码不正确！";
			return "sysmsg";
		}

		if (userinfo == null ) 
		{
			message = "用户不存在或密码错误！";
			return "sysmsg";
		} 
		else 
		{
			if (userinfo.getStatus() != 0) 
			{
				message = "用户已经被锁定或注销，请联系管理员！";
				return "sysmsg";
			}
			userinfo.setLogintype("SYS");
			userinfo.setCorpcode("1001");  
            userinfo.setCorpid(0);
            userinfo.setEnterpriseid(0);
            userinfo.setCorpname("运营商");
            if(userinfo.getTsysAreaCode()!=null)
            {
               userinfo.setAreacode(userinfo.getTsysAreaCode().getAreacode());
               userinfo.setAreaname(userinfo.getTsysAreaCode().getAreaname());
            }
			set(com.uu800.admin.base.SessionKey.SK_USER_SESSION, userinfo);
			HashSet<String> rights = service.getUserRights(userinfo);
			set("rights", rights);		
			List<Menu> menuList = service.getMenuList(userinfo);
			set("menuList", menuList);
			LOG.debug("nextPage============="+nextPage);
//			if(null == nextPage || "".equals(nextPage.trim()))
				return SUCCESS;
//			else
//				return "currenturl";
		}
	}

	public TsysUser getUser() {
		return userinfo;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRandnum(String randnum) {
		this.randnum = randnum;
	}

	public String getRandnum() {
		return this.randnum;
	}
	
	public TsysUser getSysuser() {
		return sysuser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
