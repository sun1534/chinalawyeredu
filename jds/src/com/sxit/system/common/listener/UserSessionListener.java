/**
 * <pre>
 * 记录用户对此网站的访问次数                          
 * </pre>
 */

package com.sxit.system.common.listener;

import java.util.Enumeration;

import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.changpeng.common.Globals;
import com.sxit.common.component.HibernateSession;
import com.sxit.system.model.*;


public class UserSessionListener implements HttpSessionActivationListener, HttpSessionAttributeListener, HttpSessionBindingListener,
		HttpSessionListener {
	private static final Log LOG = LogFactory.getLog(UserSessionListener.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionActivationListener#sessionDidActivate(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDidActivate(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("sessionDidActivate=" + event.getSession().getId());
		LOG.debug("sessionDidActivate,使用sessionCreated(event)方法");
		sessionCreated(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionActivationListener#sessionWillPassivate(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionWillPassivate(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("sessionWillPassivate=" + event.getSession().getId());
		LOG.debug("sessionWillPassivate,使用sessionDestroyed(event)方法");
		sessionDestroyed(event);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("attributeAdded=" + event.getSession().getId());
		LOG.debug("name=" + event.getName() + ",value=" + event.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("attributeRemoved=" + event.getSession().getId());
		LOG.debug("name=" + event.getName() + ",value=" + event.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("attributeReplaced=" + event.getSession().getId());
		LOG.debug("name=" + event.getName() + ",value=" + event.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LOG.info("valueBound=" + event.getSession().getId());
		LOG.info("name=" + event.getName() + ",value=" + event.getValue());
		LOG.info("valueBound,use the attributeAdded method");
		attributeAdded(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("valueUnbound=" + event.getSession().getId());
		LOG.debug("name=" + event.getName() + ",value=" + event.getValue());
		LOG.debug("valueUnbound,use the valueUnbound method");
		attributeRemoved(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent event) {
		LOG.debug("session created start..........");
		// 如果需要记录访问量的话,在此处实现
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		LOG.info("会话信息失效,进行处理......");
		// session失效,如果是登录失效的话,则记录到数据库
		Enumeration enume = event.getSession().getAttributeNames();
		while (enume.hasMoreElements()) {
			String sessionName = (String) enume.nextElement();
			LOG.info("失效session名称:" + sessionName);
			if (sessionName.equals("curuser")) {
				TsysUser sysUser = (TsysUser) event.getSession().getAttribute("curuser");
				try {
					
					Globals globals = Globals.getInstance();
					HibernateSession hibernateSession=(HibernateSession)globals.getBean("hibernateSession");
					Session session=hibernateSession.getSession();
					SysLoginlog log = (SysLoginlog)session.get(SysLoginlog.class, sysUser.getLoginId());	
					log.setLogouttime(new java.util.Date());
					log.setLogoutremarks("会话信息失效退出");
					session.update(log);
					
					
					session.getTransaction().commit();
					
					session.close();
			
					LOG.info("记录用户的会话失效信息:" + sysUser.getUsername());
				}
				catch (Exception e) {
					LOG.error("记录用户的会话失效信息失败:" + e);
					e.printStackTrace();
				}
			}
		}
	}
}
