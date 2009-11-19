/**
 * 
 */
package com.changpeng.common.action;

import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.sysdata.CommonData;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.global.model.GlobalLogLogin;
import com.opensymphony.xwork2.ActionContext;

/**
 */
public class LoginAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(LoginAction.class);
	// 用户输入的验证码
	private String verifycode;
	// 用户输入的用户名
	private String loginname;
	// 用户输入的密码
	private String password;

private String from;
	public String getFrom() {
	return from;
}

public void setFrom(String from) {
	this.from = from;
}

	public LoginAction() {
		rightCode = "PUBLIC";
		this.moduleid = 10;
	}

	@Override
	protected String go() throws Exception {

		this.moduleid = 10;

		String result = "plainmsg";
		
		if (loginname == null || loginname.equals("")) {
			message = "请输入您的用户名";
		} else if (password == null || password.equals("")) {
			message = "请输入您的登录密码";
		}else if(verifycode!=null&&get("verifycode")!=null&&(!verifycode.equals(get("verifycode").toString()))){
			message="请输入正确的验证码";
		}else {
			UserService service = (UserService) this.getBean("userService");
	
			CoreUser user = service.getUserByLoginName(loginname);
			if (user != null&&user.getPwd().equals(password)) {
					String usersession = user.getId() + "," + user.getProvinceId()
							+ "," + user.getCityId() + "," + user.getDistrictId()
							+ "," + user.getUserRole() + "," + user.getUserName() + ","
							+ user.getUserType();
					set("USERSESSION", usersession);
					this.currentUserid = user.getId();
					this.currentRole = user.getUserRole();
					this.provinceid = user.getProvinceId();
					this.cityid = user.getCityId();
					this.districtid = user.getDistrictId();
					this.usertype = user.getUserType();
					this.currentUsername=user.getUserName();
	
					GlobalLogLogin loglogin=new GlobalLogLogin();
					loglogin.setLoginIp(this.userip);
					loglogin.setLoginTime(new Timestamp(System.currentTimeMillis()));
					loglogin.setMobile(user.getMobile());
					loglogin.setUserid(user.getId());
					loglogin.setUserName(user.getUserName());
					loglogin.setUserRole(user.getUserRole());
					service.save(loglogin);
					
					ActionContext ctx = ActionContext.getContext();
					HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);

					Cookie cookie = new Cookie(CommonData.LOGINCOOKIE, loginname+"||"+password);
					cookie.setMaxAge(60*60*1000);
					cookie.setPath("/");
					response.addCookie(cookie);
					System.out.println("加入COOKIE......");
					
					
					message="ok";
			} else {
				message = "密码错误，请重新输入!";
			}
		}
		
		System.out.println("from::::::::"+from);
		if(from!=null&&from.equals("register")){
			System.out.println("from111111111111::::::::"+from);
			return "tohome";
		}
		else
			return result;
	}

	// 以下set方法页面调用
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
