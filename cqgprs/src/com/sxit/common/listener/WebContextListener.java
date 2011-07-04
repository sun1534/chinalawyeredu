/**
 * <pre>
 *               
 * </pre>
 */

package com.sxit.common.listener;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.BasicService;
import com.sxit.common.CommonDatas;
import com.sxit.common.Constants;
import com.sxit.common.Globals;
import com.sxit.common.exception.ServiceException;
import com.sxit.log.service.SysLoginLogService;
import com.sxit.models.system.SysParameter;
import com.sxit.models.system.SysRight;
import com.sxit.netquality.service.BasicSetService;
import com.sxit.netquality.service.ChrQueryService;
import com.sxit.query.service.EricssonTrace;
import com.sxit.system.util.RightTree;

/**
 * 
 * web容器启动和关闭的时候，所需要做的操作。不包括以kill的方式删除进程
 * 
 * @author 华锋 2009-1-5 上午09:16:34
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
		LOG.debug("context attributeAdded,name=" + arg0.getName() + ",value=" + arg0.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextAttributeListener#attributeRemoved(javax.servlet.ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		LOG.debug("context attributeRemoved,name=" + arg0.getName() + ",value=" + arg0.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextAttributeListener#attributeReplaced(javax.servlet.ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		LOG.debug("context attributeReplaced,name=" + arg0.getName() + ",value=" + arg0.getValue());

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
		} catch (Exception e) {
			LOG.error("容器退出时，记录用户的会话失效信息失败！" + e);
		}

		// 停止对所有号码的追踪
		Iterator<String> iterator = EricssonTrace.TRACE_MOBILES.keySet().iterator();
		long now = 0;
		EricssonTrace service = new EricssonTrace();
		while (iterator.hasNext()) {
			now = System.currentTimeMillis();
			String key = iterator.next();
			String mobile = key.split(",")[0];
			service.mobileTracStop(EricssonTrace.TRACE_MOBILES.get(key), mobile, now + "");
			LOG.info("停止" + key + "追踪的执行时间:::" + (System.currentTimeMillis() - now));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {

		try {

			BasicService basicService = (BasicService) Globals.getBean("basicService");
			List list = basicService.findAll(SysParameter.class);
			// List chanellist=basicService.findAll(CoreChannel.class);
			int length = list == null ? 0 : list.size();
			for (int i = 0; i < length; i++) {
				SysParameter param = (SysParameter) list.get(i);
				CommonDatas.SysParameter.put(param.getParamname(), param.getParamvalue());
			}

			if (CommonDatas.SysParameter.containsKey("sysname")) {
				Constants.SYS_NAME = CommonDatas.SysParameter.get("sysname").toString();
			}
			if (CommonDatas.SysParameter.containsKey("resourcepath")) {
				Constants.RESOURCE_PATH = CommonDatas.SysParameter.get("resourcepath").toString();
			}
			if (CommonDatas.SysParameter.containsKey("resourcepathmanage")) {
				Constants.RESOURCE_PATH_MANAGE = CommonDatas.SysParameter.get("resourcepathmanage").toString();
			}
			// 获得所有的权限列表信息
			RightTree.setRightList(basicService.findAll(SysRight.class));

			com.sxit.system.util.CommonDatas.getUsers();
			com.sxit.system.util.CommonDatas.getGroups();
			BasicSetService setservice = (BasicSetService) Globals.getBean("basicSetService");
//			setservice.getAllSets();
			ChrQueryService queryservice = (ChrQueryService) Globals.getBean("chrQueryService");
			queryservice.getFlowids();
	
			// BasicSetService setservice = (BasicSetService)
			// Globals.getBean("basicSetService");
			// setservice.getAllSets();
			// LOG.info("得到所有的配置参数信息");

		} catch (ServiceException e) {
			LOG.error("系统启动初始化权限列表为空" + e);
		}
		String contextid = System.currentTimeMillis() / 1000 + "";
		event.getServletContext().setAttribute("CONTEXTID", contextid);
		LOG.info("系统启动初始化,系统ID......" + contextid);
	}
}