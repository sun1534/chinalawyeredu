/**
 * <pre>
 *               
 * </pre>
 */

package com.uu800.webbase;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * web容器启动和关闭的时候，所需要做的操作。不包括以kill的方式删除进程
 * 这里包括系统启动初始化的时候，要执行的操作都放在这里面
 * 另外，通过实现接口和继承等，实现其他的一些初始化动作
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
		
		LOG.info("系统关闭并执行各release完毕...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * 这里初始化ice的相关接口的参数
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		
		
//		SysDictionaryService dictS=(SysDictionaryService)Globals.getWebBean("sysDictionaryService");
//		
//		dictS.getAllAreas();
//		dictS.getAllParams();
//		
//		SysMenuRightService rightMenuS=(SysMenuRightService)Globals.getWebBean("sysMenuRightService");
	
		
		LOG.info("系统启动并初始化参数完毕...");

	}
}