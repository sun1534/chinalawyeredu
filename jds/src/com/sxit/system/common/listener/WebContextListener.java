/**
 * <pre>
 *               
 * </pre>
 */

package com.sxit.system.common.listener;

import java.util.List;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;


import com.changpeng.common.Globals;
import com.changpeng.operation.model.ToprBank;
import com.opensymphony.xwork2.ActionContext;
import com.sxit.common.component.HibernateSession;
import com.sxit.system.model.*;


/**
 * 
 *
 * <p>功能： web容器启动和关闭的时候，所需要做的操作。不包括以kill的方式删除进程</p>
 * <p>作者： sinhoo</p>
 * <p>日期： Oct 7, 2008</p>
 * @修改：
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
		/* ActionContext.getContext().getSession().*/
		try {
			Globals globals = Globals.getInstance();
			HibernateSession hibernateSession=(HibernateSession)globals.getBean("hibernateSession");
			Session session=hibernateSession.getSession();
			
			session.createSQLQuery("update sys_loginlog set logouttime=sysdate, logoutremarks='WEB容器关闭导致退出' where contextid='"+contextid+"' and (logouttime is null or logouttime='')").executeUpdate();
			session.getTransaction().commit();
			session.close();
			
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
		try {
			Globals globals = Globals.getInstance();
			com.changpeng.common.CommonDatas.bankList=globals.findAll(" from ToprBank");
			for(ToprBank bank:com.changpeng.common.CommonDatas.bankList){
				com.changpeng.common.CommonDatas.bankMap.put(bank.getBankid()+"", bank.getBankname());
			}
		}
		catch (Exception e) {
			LOG.error(e);
		}
		String contextid = System.currentTimeMillis() / 1000 + "";
		event.getServletContext().setAttribute("CONTEXTID", contextid);
		LOG.info("系统启动初始化,系统ID......" + contextid);
	}
}