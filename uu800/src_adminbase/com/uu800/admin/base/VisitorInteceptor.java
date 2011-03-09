package com.uu800.admin.base;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

import com.uu800.webbase.WebContextWrapper;
import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.SessionKey;
import com.uu800.admin.base.dao.LogDao;
import com.uu800.admin.base.entity.TsysUser;

/**
 * web网站部分的拦截器,包括登录鉴权以及日志访问记录等
 * 
 * @author 华锋 Feb 28, 2011 5:03:20 PM
 */
public class VisitorInteceptor implements Interceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6971362291147031353L;
	private static final Log LOG = LogFactory.getLog(VisitorInteceptor.class);

	private static volatile int SEQ = 0;

	public void destroy() {
		LOG.debug("VisitorInteceptor销毁" + (SEQ++));
		// TODO Auto-generated method stub
	}

	public void init() {
		LOG.debug("VisitorInteceptor初始化" + (SEQ++));
		// TODO Auto-generated method stub
	}

	/**
	 * 存储模块访问日志的队列
	 */
	// public static java.util.Vector<LogOpt> LOGOPTS = new
	// java.util.Vector<LogOpt>();
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub

		long begin = System.currentTimeMillis();
		Action action = (Action) invocation.getAction();
		if (!(action instanceof AbstractAdminAction)) {
			return invocation.invoke();
		}
		String result = "actionResult";
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String ip = getIpAddr(request);
		String url = request.getRequestURI();
		String ajax = request.getHeader("x-requested-with");
		String actionName = invocation.getProxy().getActionName();
		LOG.debug("ajax:" + ajax + ",+actionName=" + actionName);
		String moduleId = "NO";
		Object obj = null;
		TsysUser userinfo = null;
		try {

			obj = ActionContext.getContext().getSession().get(SessionKey.SK_USER_SESSION);
			AbstractAdminAction abstractAction = (AbstractAdminAction) action;
			abstractAction.setUserIp(ip);

			boolean execute = false;
			// setAutoLogin(obj);
			if (obj != null) {
				userinfo = (TsysUser) obj;
				abstractAction.setUserinfo(userinfo);
				if (userinfo.getLoginName().equalsIgnoreCase("admin"))
					execute = true;
				if (userinfo.hasRight(actionName)) {
					execute = true;
				} else {
					result = "noright";
					execute = false;
				}
			} else {
				abstractAction.setUserinfo(null);
				if (abstractAction.getNeedLogin()) {// 没有会话信息但是又需要登录的情况
					if (ajax != null && ajax.equalsIgnoreCase("XMLHttpRequest"))
						result = "poplogin";
					else
						result = Action.LOGIN; // 跳回到登录页面。这里如果是ajax请求的话,可以将其他跳转到Action.AjaxLogin的登录页
				} else { // 这里是不需要登录的情况,即继续执行那个action
					execute = true;
				}
			}
			if (execute) {
				result = invocation.invoke();
				moduleId = abstractAction.getModuleId();
				String opResult = abstractAction.getOpResult();
				if (moduleId != "" || (opResult != null && opResult.equals(""))) {// 记录到log_opt,这里考虑通过接口来做
					LogDao logdao = (LogDao) WebContextWrapper.getBean("logDao");
					if (logdao != null && userinfo != null)
						logdao.logOpt(userinfo.getId(), userinfo.getUserName(), moduleId, url, ip, opResult, result);
					else
						LOG.warn("未有实现logDao的接口");
				}
			}
			return result;

		} catch (Exception e) {
			LOG.error("拦截失败::" + ip + ">" + url, e);
			// if (result != null && result.equals("actionResult"))
			// result = invocation.invoke();
			return "error";
		} finally {
			long now = System.currentTimeMillis();
			if (obj != null)
				LOG.info(result + ":" + userinfo.getUserName() + ">" + ip + ">" + url + ">" + (now - begin) + ">"
						+ result);
			else
				LOG.info(result + ":WHO>" + ip + ">" + url + ">" + (now - begin) + ">" + result);
		}
	}

	private void setAutoLogin(Object obj) {

		// LoginService loginService = (LoginService)
		// Globals.getWebBean("loginService");
		// int loginResult = loginService.userLogin("luxun", "123456", ip);
		// if (loginResult == 0 || loginResult == 1) {
		// // 设置session等会话信息
		// userinfo = loginService.getUserinfo();
		// ActionContext.getContext().getSession().put(CommonData.LOGIN_SESSION_KEY,
		// userinfo);
		// // abstractAction.setUserinfo(userinfo);
		// obj = userinfo;
		// }

	}

	/**
	 * 获取访问的ip,这是有nginx的原因
	 * 
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		return ip;
	}

	/**
	 * 得到对应的域名信息
	 * 
	 * @param request
	 * @return
	 */
	private String getDomain(HttpServletRequest request) {
		ValueStack vs = (ValueStack) request.getAttribute("struts.valueStack");
		String domain = request.getHeader("x-host");
		if (domain == null || domain.equals(""))
			domain = request.getServerName();
		// 根据域名得到对应的schoolId;
		return domain;
	}
}