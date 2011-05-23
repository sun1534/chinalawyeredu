//$Id: HibernateInterceptor.java,v 1.1 2003/11/16 17:15:35 max Exp $

package com.changpeng.common.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 拦截器，判断是否有权限以及是否已经登录 1、记录访问的来源ip和访问的action
 * 
 * @author 华锋 2008-2-21 下午02:14:38
 * 
 */
public class UserVisitInterceptor implements Interceptor {

	private static final Log LOG = LogFactory.getLog(UserVisitInterceptor.class);
    
	public void destroy() {
		LOG.debug("UserVisitInterceptor Destroying...");
	}
    
	public void init() {
		LOG.debug("UserVisitInterceptor Init...");
	}
    
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = ActionContext.getContext();
		String action = context.getName();
	//	context.get(ServletActionContext.APPLICATION);
		
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String ip = ((HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST)).getRemoteAddr();
		LOG.info("IP::" + ip + " visit the action:" + action+",URL="+request.getRequestURI());
		//最理想的,在这里,通过这个url从数据库找到对应的rightcode。从而在代码中免除设置rightcode
		//有个问题,abc!input.pl实际上是等于abc.pl的.一个是入口,一个是保存
		
		//webcontext初始化的时候,得到系统所有的rightcode到内存中
		return invocation.invoke();
	}

	/**
	 * 获取当前请求的url和所有的参数信息
	 * 
	 * @return
	 */
//	public String getCururl() {
//		ActionContext ctx = ActionContext.getContext();
//		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
//		String url1 = request.getRequestURL().toString();
//        return url1;
//		//	String url2 = request.getQueryString();
//	//	return url1 + (url2 == null ? "" : "?" + url2);
//	}
}