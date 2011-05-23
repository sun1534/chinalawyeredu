/**
 * <pre>
 *               
 * </pre>
 */

package com.changpeng.common.listener;

import java.util.List;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.context.Globals;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.system.SysParameter;
import com.changpeng.system.service.SysLoginLogService;
import com.changpeng.system.service.SysRightService;
import com.changpeng.system.util.RightTree;

/**
 * 
 * web容器启动和关闭的时候，所需要做的操作。不包括以kill的方式删除进程
 * 
 * @author 华锋 2008-2-22 上午09:16:34
 * 
 */
public class WebContextListener implements ServletContextListener {

	private static final Log LOG = LogFactory.getLog(WebContextListener.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextAttributeListener#attributeAdded(javax.servlet.ServletContextAttributeEvent)
	 */
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		LOG.info("context attributeAdded,name=" + arg0.getName() + ",value=" + arg0.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextAttributeListener#attributeRemoved(javax.servlet.ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		LOG.info("context attributeRemoved,name=" + arg0.getName() + ",value=" + arg0.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextAttributeListener#attributeReplaced(javax.servlet.ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		LOG.info("context attributeReplaced,name=" + arg0.getName() + ",value=" + arg0.getValue());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {

		String contextid = event.getServletContext().getAttribute("CONTEXTID").toString();
		LOG.info("系统退出,系统ID......" + contextid);

		try {
			Globals globals = new Globals();
			SysLoginLogService service = (SysLoginLogService) globals.getBean("sysLoginLogService");
			
			
			
			service.updateLogoutInfo(contextid);
			LOG.info("容器退出时，记录用户的会话失效信息OK！");
		}
		catch (Exception e) {
			LOG.error("容器退出时，记录用户的会话失效信息失败！" + e);

		}

		// 更新所有登录用户的退出信息，通过contextid来进行

		// Globals globals = new Globals();
		// SysLoginLogService service = (SysLoginLogService) globals.getBean("sysLoginLogService");
		// service.updateLogoutInfo(sysUser.getLoginId(), "系统正常退出");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {

		// servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.context);
		// 这个方式也可以得到WebApplicationContext；

//		WebApplicationContext wac1 = (WebApplicationContext) event.getServletContext().getAttribute(
//				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

//		if (wac1 != null) {
//			System.out.println("-----------wac1:::" + wac1);
//		}

//		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//		if (wac != null) {
//
//			System.out.println("-----------wac::::::" + wac);
//			Object obj = wac.getBean("sysRightService");
//			System.out.println(" wac.isSingleton:::" + wac.isSingleton("sysRightService"));
//			System.out.println("===========================" + obj);
//
//		}
		try {
			Globals globals = new Globals();
			SysRightService rightService = (SysRightService) globals.getBean("sysRightService");
			BasicService basicService=(BasicService)globals.getBean("basicService");
			List list=basicService.findAll(SysParameter.class);
			int length=list==null?0:list.size();
			for(int i=0;i<length;i++){
				SysParameter param=(SysParameter)list.get(i);
				CommonDatas.SysParameter.put(param.getParamname(), param.getParamvalue());
			}
			
			RightTree.setRightList(rightService.findAll());
		}
		catch (ServiceException e) {
			LOG.error("系统启动初始化权限列表为空" + e);
		}
		String contextid = System.currentTimeMillis() / 1000 + "";
		event.getServletContext().setAttribute("CONTEXTID", contextid);
		LOG.info("系统启动初始化,系统ID......" + contextid);
	}
}