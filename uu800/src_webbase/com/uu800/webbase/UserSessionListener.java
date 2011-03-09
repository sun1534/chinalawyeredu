/**
 * <pre>
 * 记录用户对此网站的访问次数                          
 * </pre>
 */

package com.uu800.webbase;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author 华锋 2008-2-22 上午09:13:35
 * 
 */
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
		sessionDestroyed(event);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("name=" + event.getName() + ",value=" + event.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("name=" + event.getName() + ",value=" + event.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("name=" + event.getName() + ",value=" + event.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("name=" + event.getName() + ",value=" + event.getValue());
		attributeAdded(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("name=" + event.getName() + ",value=" + event.getValue());
		attributeRemoved(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent event) {
//		LOG.info("session created start..........");
		// 如果需要记录访问量的话,在此处实现
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		
	}
}