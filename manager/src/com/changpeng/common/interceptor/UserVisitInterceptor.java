//$Id: HibernateInterceptor.java,v 1.1 2003/11/16 17:15:35 max Exp $

package com.changpeng.common.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.CommonDatas;
import com.changpeng.common.Constants;
import com.changpeng.models.SysUnionparams;
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
//		String action = context.getName();
		// context.get(ServletActionContext.APPLICATION);

		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		// String ip = ((HttpServletRequest)
		// context.get(ServletActionContext.HTTP_REQUEST)).getRemoteAddr();
		String ip = getIpAddr((HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST));
    	long now=System.currentTimeMillis();
    	String result="message";
    	try{
		// 最理想的,在这里,通过这个url从数据库找到对应的rightcode。从而在代码中免除设置rightcode
		// 有个问题,abc!input.pl实际上是等于abc.pl的.一个是入口,一个是保存

		// webcontext初始化的时候,得到系统所有的rightcode到内存中
    		getDomain(request);
    		result=invocation.invoke();
    	}catch(Exception e){
    	
    		LOG.error("拦截失败:" + e+">"+ip+">"+request.getRequestURI()+">"+(System.currentTimeMillis()-now));
    		throw e;
    	}
		LOG.info(ip + ">" + request.getRequestURI()+">"+(System.currentTimeMillis()-now));
		return result;
	}

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private void getDomain(HttpServletRequest request) {
		Constants.CURRENT_DOMAIN = request.getHeader("x-host");

		if (Constants.CURRENT_DOMAIN == null || Constants.CURRENT_DOMAIN.length() == 0
				|| "unknown".equalsIgnoreCase(Constants.CURRENT_DOMAIN)) {
			Constants.CURRENT_DOMAIN = request.getHeader("X-Host");
		}

		SysUnionparams params = CommonDatas.SysUnionparams.get(Constants.CURRENT_DOMAIN);
		if (params != null) {
			
//			System.out.println(" params.getLogopath():"+ params.getLogopath());
			
			if (params.getIndexpic() != null && !"".equals(params.getIndexpic())) {
				Constants.INDEX_PIC = params.getIndexpic();
			} else {
				Constants.INDEX_PIC = Constants.DEFAULT_INDEX_PIC;
			}
			if (params.getLogopath() != null && !"".equals(params.getLogopath())) {
				Constants.LOGO_PATH = params.getLogopath();
			} else {
				Constants.LOGO_PATH = Constants.DEFAULT_LOGO_PATH;
			}
			if (params.getSysname() != null && !"".equals(params.getSysname())) {
				Constants.SYS_NAME = params.getSysname();
			} else {
				Constants.SYS_NAME = Constants.DEFAULT_SYS_NAME;
			}
			if (params.getTopbarpic() != null && !"".equals(params.getTopbarpic())) {
				Constants.TOP_BAR_PIC = params.getTopbarpic();
			} else {
				Constants.TOP_BAR_PIC = Constants.DEFAULT_TOP_BAR_PIC;
			}

			Constants.HAVELOCAL = params.getHavelocal();

		}else{
			Constants.TOP_BAR_PIC = Constants.DEFAULT_TOP_BAR_PIC;
			Constants.SYS_NAME = Constants.DEFAULT_SYS_NAME;
			Constants.INDEX_PIC = Constants.DEFAULT_INDEX_PIC;
			Constants.LOGO_PATH = Constants.DEFAULT_LOGO_PATH;
			Constants.HAVELOCAL =Constants.DEFAULT_HAVELOCAL;
		}
		LOG.debug("Constants.SYS_NAME:" + Constants.SYS_NAME);
	}
}