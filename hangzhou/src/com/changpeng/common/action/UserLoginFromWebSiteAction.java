/**
 * UserLoginAction.java
 */

package com.changpeng.common.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.Constants;
import com.changpeng.common.util.Base64;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysLoginLogService;
import com.changpeng.system.service.SysUserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用户登录
 * 
 * @author 华锋 2008-2-22 上午11:13:18
 * 
 */
public class UserLoginFromWebSiteAction extends AbstractAction {

	private static Log LOG = LogFactory.getLog(UserLoginFromWebSiteAction.class);
	private String topxxt;

	public UserLoginFromWebSiteAction() {
		this.needsession = false;
	}

	public void setTopxxt(String topxxt) {
		this.topxxt = topxxt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		this.nextPage = "index.pl";

		// 根据loginname得到用户个人信息
		// SysUserDAO dao=new SysUserDAO();
		SysUserService userService = (SysUserService) getBean("sysUserService");
		//		
		// SysUser users=userService.getUser(1);
		// LOG.debug("users::::::::::::::::::::::::::"+users.getSysRoles());
		// loginname=13510073023&password=123456;
		String thepxxt = new String(Base64.decode(topxxt.getBytes()));
		int index = thepxxt.indexOf("=");
		int indexamp = thepxxt.indexOf("&");

		String loginname = thepxxt.substring(index + 1, indexamp);
		LOG.info("topxxt=" + topxxt + ",thepxxt=" + thepxxt + ",loginname=" + loginname);

		// SysUser _sysUser = userService.getSysUserByLoginname(loginname);
		//
		// // int loginResult = userService.userLogin(this.loginname, this.password);
		// // LOG.debug("登录验证结果:::" + loginResult);
		// // if (loginResult == -1) {
		// if (_sysUser == null) {
		// message = "您输入的帐号在系统中不存在,请确认";
		// return Constants.ACTION_MESSAGE;
		// }

		int loginResult = userService.userLogin(loginname);
		if (loginResult == -1) {
			message = "您输入的帐号在培训系统中不存在,请确认";
			return Constants.ACTION_MESSAGE;
		}

		LOG.info("网站登录Result:::" + loginResult);

		SysUser sysLoginUser = userService.getSysUser();
		LOG.info("网站登录后处理过的sysLoginUser:::" + sysLoginUser.getLoginname());

		SysLoginLogService loginService = (SysLoginLogService) getBean("sysLoginLogService");
		sysLoginUser.setLoginCount(loginService.getLoginCountByUserId(sysLoginUser.getUserid()));
		sysLoginUser.setLastLoginTime(loginService.getLastLoginTime(sysLoginUser.getUserid()));
		// 插入登录信息表
		// LOG.debug("==插入登陆日志。。。:"+_remoteAddr+"==="+sysLoginUser.getUserid());

		// LOG.debug("==插入登陆日志完成。。。");

		// 页面转向

		// return Constants.ACTION_MESSAGE;
		ActionContext ctx = ActionContext.getContext();
		String contextid = ctx.getApplication().get("CONTEXTID").toString();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		// HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		String _remoteAddr = request.getRemoteAddr();
		LOG.debug("系统每次启动后的标志ID:" + contextid);

		loginService.insertLoginLog(_remoteAddr, sysLoginUser.getUserid(), contextid, "从律协网站登录");
		sysLoginUser.setLoginId(loginService.getLoginId()); // 绑定本次登录的登录id
		LOG.debug("从网站正常登录成功.............");
		this.set(Constants.LOGIN_USER, sysLoginUser);
		return SUCCESS;

	}

}