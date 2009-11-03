//$Id: HibernateInterceptor.java,v 1.1 2003/11/16 17:15:35 max Exp $

package com.changpeng.common.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.Constants;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysUser;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 拦截器，判断是否有权限以及是否已经登录 
 * 2、判断是否已经登录 
 * 3、判断是否有权限
 * 
 * @author 华锋 2008-2-21 下午02:14:38
 * 
 */
public class UserVerifyInterceptor implements Interceptor {

	private static final Log LOG = LogFactory.getLog(UserVerifyInterceptor.class);
	
	public void destroy() {
		LOG.debug("UserVerifyInterceptor Destroying...");
	}

	public void init() {
		LOG.debug("UserVerifyInterceptor Init...");
	}
/*
 *  暂时不考虑重新登录后转向到之前页面的情况
 * 
 *  考虑不设置权限id了，权限id通过actionname来获得.
 */
	public String intercept(ActionInvocation invocation) throws Exception {
		Action action = (Action) invocation.getAction();
		if ( !(action instanceof AbstractAction)) 
	    	return invocation.invoke();
	    //将进行是否登录以及对此action是否有权限的判断
	    AbstractAction abstractAction=(AbstractAction)action;
	    //如果right为空或者没有设置right的值或者不需要session
//	    if(abstractAction.getRightCode()==null||abstractAction.getRightCode().isEmpty()||!abstractAction.isNeedsession())
	   if(!abstractAction.isNeedsession())
	       return invocation.invoke();
		Object obj=ActionContext.getContext().getSession().get(Constants.LOGIN_USER);
	    if(obj==null||!(obj instanceof SysUser)){
	    	return "pasivelogin";//被动登录
	    	//没有登录,跳转到登录页面
	    }
	    SysUser sysUser=(SysUser)obj;
	    //登录名admin设置为超级管理员,不做任何权限验证
	    if(sysUser.getLoginname().equalsIgnoreCase("admin")) 
	    	return invocation.invoke();
//	    if(abstractAction.getRightCode()==null||abstractAction.getRightCode().isEmpty())
//	    	 return invocation.invoke();
	    //判断这个人是否有权限,根据这个人所绑定的权限列表，判断此right是否在此权限列表中
	    
	    LOG.debug("rightCode::::"+abstractAction.getRightCode());
	    if(sysUser.hasRight(abstractAction.getRightCode()))
	       return invocation.invoke(); //有权限的话就执行,没权限的话跳转到没权限页面
	    return "noright"; //没权限的话,返回页面都是执行操作的前一个页面,即history.back();
	}

	/**
	 * 获取当前请求的url和所有的参数信息
	 * 
	 * @return
	 */
//	private String getCurentUrl() {
//		ActionContext ctx = ActionContext.getContext();
//		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
//		String url1 = request.getRequestURL().toString();
//		String url2 = request.getQueryString();
//		return url1 + (url2 == null ? "" : "?" + url2);
//	}
	

}