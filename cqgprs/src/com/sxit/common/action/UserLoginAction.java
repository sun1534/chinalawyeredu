/**
 * UserLoginAction.java
 */

package com.sxit.common.action;

import java.text.DateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sxit.common.Constants;
import com.sxit.log.service.SysLoginLogService;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysUserService;

/**
 * 用户登录
 * 
 * @author 华锋 2009-1-5 上午11:13:18
 * 
 */
public class UserLoginAction extends AbstractAction {

	private static Log LOG = LogFactory.getLog(UserLoginAction.class);

//	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd操");
	private String loginname;
	private String password;
//	private String randnum;
	
	

	/**
	 * 如果是被动登录的话,此值不为空
	 */
	private String pasivelogin;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// String today=df.format(new java.util.Date());
		SysLoginLogService loginService = (SysLoginLogService) getBean("sysLoginLogService");
		ActionContext ctx = ActionContext.getContext();

		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		String _remoteAddr = request.getRemoteAddr();
		String contextid = ctx.getApplication().get("CONTEXTID").toString();
		this.nextPage = "index.action";
		Object obj = get(Constants.VALIDATE_CODE);
//		LOG.debug("===================验证码:::" + obj);
		// 验证码为空
//		if (!request.getRemoteAddr().equals("127.0.0.1")) {
//			if (obj == null) {
//				message = "验证码已经过期,请返回重新输入";
//				return "message";
//			}
//			// 输入的验证码不符合要求
//			if (!((String) obj).equals(randnum)) {
//				message = "您输入的验证码错误,请返回重新输入";
//				return "message";
//			}
//		}
		// 根据loginname得到用户个人信息
		// SysUserDAO dao=new SysUserDAO();
		SysUserService userService = (SysUserService) getBean("sysUserService");
		//		
		// SysUser users=userService.getUser(1);
		// LOG.debug("users::::::::::::::::::::::::::"+users.getSysRoles());

		int loginResult = userService.userLogin(this.loginname, this.password);
		LOG.debug("登录验证结果:::" + loginResult);
		if (loginResult == -1) {
			loginService.insertLoginLog(_remoteAddr,0, loginname,contextid, "帐号在系统中不存在");
			message = "您输入的帐号在系统中不存在,请确认";
			return "message";
		}
		if (loginResult == -2) {
			loginService.insertLoginLog(_remoteAddr,0, loginname,contextid, "帐号已被禁用");
			message = "您的帐号已被禁用,请联系管理员";
			return "message";
		}
		if (loginResult == -3) {
			loginService.insertLoginLog(_remoteAddr,0, loginname,contextid, "帐号密码错误");
			message = "您输入的密码错误,请返回重新输入";
			return "message";
		}

		// int userid = loginResult;
		// SysUser sysUser = userService.getUser(userid);
		// this.sysUser=userService.getUser(userid);
		// 根据userid得到权限信息,如果是admin的话,就得到所有的菜单和rightcode
		// Set<SysRight> rights = userService.getUserRights(sysUser);
		// sysUser.setRightList(userService.getUserRightCodes(rights));
		// 得到这个人的菜单显示列表
		// userMenus = userService.getUserMenus(rights);
		// Iterator<SysMenu> menu=userMenus.iterator();
		// while(menu.hasNext()){
		// SysMenu mu=menu.next();
		// LOG.debug("菜单================="+mu.getMenuname()+"=="+mu.getOrderid());
		// }
		// LOG.debug("================菜单获取完成...");
		// 将其登录信息存入t_sys_loginlog表中,同时将其他此userid的Islast=1的数据全部改为0

		SysUser sysUser = userService.getSysUser();

		sysUser.setLoginCount(loginService.getLoginCountByUserId(sysUser.getUserid()));
		sysUser.setLastLoginTime(loginService.getLastLoginTime(sysUser.getUserid()));
		// 插入登录信息表
		// LOG.debug("==插入登陆日志。。。:"+_remoteAddr+"==="+sysUser.getUserid());
		// LOG.debug("==插入登陆日志完成。。。");
		// 页面转向
		// return "message";

		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		
		LOG.debug("系统每次启动后的标志ID:" + contextid);
		if (savecookie) {
			Cookie cookie = new Cookie(Constants.COOKIE_LOGINNAME, this.loginname);
			cookie.setMaxAge(Integer.MAX_VALUE);
			Cookie passwd = new Cookie(Constants.COOKIE_PASSWORD, this.password);
			passwd.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
			response.addCookie(passwd);
		}

		if (pasivelogin != null && pasivelogin.equals("true")) {
			LOG.debug("系统被动登录成功.............");
			loginService.insertLoginLog(_remoteAddr, sysUser.getUserid(), sysUser.getUsername(),contextid, "会话退出重新登录");
			sysUser.setLoginId(loginService.getLoginId()); // 绑定本次登录的登录id
			this.set(Constants.LOGIN_USER, sysUser);
			return "pasiveloginsuccess";
		} else {

			loginService.insertLoginLog(_remoteAddr, sysUser.getUserid(),sysUser.getUsername(), contextid, "正常登录");
			sysUser.setLoginId(loginService.getLoginId()); // 绑定本次登录的登录id
			LOG.debug("系统正常登录成功.............");
			this.set(Constants.LOGIN_USER, sysUser);
			return SUCCESS;
		}

	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public void setRandnum(String randnum) {
//		this.randnum = randnum;
//	}

	public void setPasivelogin(String login) {
		this.pasivelogin = login;
	}

	private boolean savecookie;

	public void setSavecookie(boolean savecookie) {
		this.savecookie = savecookie;
	}

	public UserLoginAction() {
		this.needsession = false;
	}
}