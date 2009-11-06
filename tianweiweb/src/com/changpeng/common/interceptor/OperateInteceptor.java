package com.changpeng.common.interceptor;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.changpeng.common.action.AbstractAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class OperateInteceptor implements Interceptor {
	private static final Log LOG = LogFactory.getLog(OperateInteceptor.class);

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		ActionContext context = ActionContext.getContext();
		Action action = (Action) invocation.getAction();
		if (!(action instanceof AbstractAction)) {
			return invocation.invoke();
		}
		String result = "jxqresult";
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String ip = getIpAddr(request);
		String url = request.getRequestURI();
		try {

			Object obj = ActionContext.getContext().getSession().get("USERSESSION");
			AbstractAction abstractAction = (AbstractAction) action;
			int moduleId = abstractAction.moduleid;
		
	
			abstractAction.userip = ip;
			if (obj != null) {
				LOG.debug("obj::" + obj);
				String str[] = obj.toString().split(",");
				abstractAction.usertype = (short) Integer.parseInt(str[6]);
				abstractAction.currentUserid = Integer.parseInt(str[0]);
				abstractAction.provinceid = Integer.parseInt(str[1]);
				abstractAction.cityid = Integer.parseInt(str[2]);
				abstractAction.districtid = Integer.parseInt(str[3]);
				abstractAction.currentRole = (short) Integer.parseInt(str[4]);
				abstractAction.currentUsername = str[5];
			} else if (!abstractAction.rightCode.equals("PUBLIC")) {
				return Action.LOGIN; // 跳回到登录页面
			}
			// 先处理完毕这个东东，事后再来处理发送到接口的请求
			long begin = System.currentTimeMillis();
			// 执行action里面的逻辑
			result = invocation.invoke();
			// 执行后面的一些逻辑处理
			int userId = abstractAction.currentUserid;
			int provinceId = abstractAction.provinceid;
			int cityId = abstractAction.cityid;
			int districtId = abstractAction.districtid;
			short userRole = (short) abstractAction.currentRole;
			short userType = (short) abstractAction.usertype;
			// 如果有模块id,则发送到陈总的接口
			if (abstractAction.moduleid != 0) {
				short flag = 0;
				// 判断请求参数是否存在
				if (request.getParameter("logflag") != null && !"".equals(request.getParameter("logflag")))
					flag = Short.parseShort(request.getParameter("logflag"));

				// 这里考虑换成异步的方式,应该会快很多
				// 也就是所有的日志操作部分等,都异步的方式操作,因为俺不用等返回啊

			}
			// 如果不是发生了错误等,判断是否记录积分。根据数据字典来，也就是根据url来判断是否需要记录积分
			// 这里的话，有些规则不好对应积分的话，自己在action里面判断
			// 积分规则表如下
			// 模块id 积分值 说明 对应url 是否拦截器处理
			// 这里感觉都可以通过陈总的统一的那个log日志来处理
			if (!result.equals(Action.ERROR)) {
				// 判断是否记录积分等
			}
			long now = System.currentTimeMillis();
			LOG.info(result+":" + userId + ">" + provinceId + ">" + cityId + ">" + districtId + ">" + moduleId + ">" + userRole + ">" + userType
					+ ">" + ip + ">" + url + ">" + (now - begin));
			return result;
		} catch (Exception e) {
			LOG.error("拦截失败::" + e+">"+ip+">"+url);
			e.printStackTrace();
			if (result!=null&&result.equals("jxqresult"))
				result = invocation.invoke();
			return result;
		}
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
}
