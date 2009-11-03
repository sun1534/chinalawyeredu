/**
 * GetLessonRequest.java
 */

package com.changpeng.service.szsync;

import java.text.DateFormat;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.Constants;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysLoginLogService;
import com.changpeng.system.service.SysUserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class LogoutSyncRequest extends ElearningSyncRequests {

	private static final Log LOG = LogFactory.getLog(LogoutSyncRequest.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public String requestSyncService(org.dom4j.Element rootElement) {

		StringBuilder result = new StringBuilder("");
		result.append("<response>");

		try {
			SysUserService sysUserService = (SysUserService) globals.getBean("sysUserService");
			String loginName = rootElement.elementText("loginname");
			SysLoginLogService loginService = (SysLoginLogService) globals.getBean("sysLoginLogService");
			Iterator sessions = ActionContext.getContext().getSession().keySet().iterator();
			while (sessions.hasNext()) {
				Object sessionName = sessions.next();
				LOG.info("系统退出,将清除掉会话信息:" + sessionName);
				if (sessionName.toString().equals(Constants.LOGIN_USER)) {
					SysUser sysUser = (SysUser) ActionContext.getContext().getSession().get(Constants.LOGIN_USER);
					loginService.updateLogoutInfo(sysUser.getLoginId(), "律协网站退出而退出");
					ActionContext.getContext().getSession().remove(Constants.LOGIN_USER);
				}
				ActionContext.getContext().getSession().remove(sessionName);
			}
			result.append("<respmsg>从网站退出</respmsg>");

		}
		catch (Exception e) {
			result.append("<respcode>").append(-1).append("</respcode>");
			result.append("<respmsg>获取该课程已培训信息异常:").append(e.getMessage()).append("</respmsg>");
		}

		result.append(result);
		result.append("</response>");
		return result.toString();
	}
}
