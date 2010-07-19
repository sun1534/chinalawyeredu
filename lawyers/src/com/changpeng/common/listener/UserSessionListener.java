/**
 * <pre>
 * 记录用户对此网站的访问次数                          
 * </pre>
 */

package com.changpeng.common.listener;

import java.util.Enumeration;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.Constants;
import com.changpeng.common.context.Globals;
import com.changpeng.lawyers.service.LawyerLoginLogService;
import com.changpeng.models.Lawyers;

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

		Enumeration enume = event.getSession().getAttributeNames();
		while (enume.hasMoreElements()) {
			String sessionName = (String) enume.nextElement();
		//	LOG.info("失效session名称:" + sessionName);
			if (sessionName.equals(Constants.LOGIN_USER)) {
				Lawyers sysUser = (Lawyers) event.getSession().getAttribute(Constants.LOGIN_USER);
				LOG.debug("sysUser=="+sysUser);
				try {
				
					LawyerLoginLogService service = (LawyerLoginLogService) Globals.getBean("lawyerLoginLogService");

					service.updateLogoutInfo(sysUser.getLawyerid(),sysUser.getLoginId(), "会话信息失效导致退出");

					LOG.info("记录用户的会话失效信息:" + sysUser.getLawyerenname());
				}
				catch (Exception e) {
					LOG.error("记录用户的会话失效信息失败:" + e);
					e.printStackTrace();
				}
			}
		}
	}
}
