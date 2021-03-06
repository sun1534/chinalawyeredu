/**
 * SpringHibernateSessionInterceptor.java
 */
package com.uu800.webbase;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 
 * 对hibernate的lazy=true进行管理
 * 
 * @author 华锋
 * 2008-3-17 上午11:49:39
 *
 */
public class SpringHibernateSessionInterceptor implements Interceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6813319933279781258L;

	private static final Log logger = LogFactory.getLog(SpringHibernateSessionInterceptor.class);
	
	public static final String DEFAULT_SESSION_FACTORY_BEAN_NAME = "sessionFactory";

	private String sessionFactoryBeanName = DEFAULT_SESSION_FACTORY_BEAN_NAME;

	private boolean singleSession = true;

	//private FlushMode flushMode = FlushMode.NEVER;


	private FlushMode flushMode = FlushMode.MANUAL;
	/**
	 * Set the bean name of the SessionFactory to fetch from Spring's
	 * root application context. Default is "sessionFactory".
	 * @see #DEFAULT_SESSION_FACTORY_BEAN_NAME
	 */
	public void setSessionFactoryBeanName(String sessionFactoryBeanName) {
		this.sessionFactoryBeanName = sessionFactoryBeanName;
	}

	/**
	 * Return the bean name of the SessionFactory to fetch from Spring's
	 * root application context.
	 */
	protected String getSessionFactoryBeanName() {
		return this.sessionFactoryBeanName;
	}

	/**
	 * Set whether to use a single session for each request. Default is "true".
	 * <p>If set to "false", each data access operation or transaction will use
	 * its own session (like without Open Session in View). Each of those
	 * sessions will be registered for deferred close, though, actually
	 * processed at request completion.
	 * @see SessionFactoryUtils#initDeferredClose
	 * @see SessionFactoryUtils#processDeferredClose
	 */
	public void setSingleSession(boolean singleSession) {
		this.singleSession = singleSession;
	}

	/**
	 * Return whether to use a single session for each request.
	 */
	protected boolean isSingleSession() {
		return this.singleSession;
	}

	/**
	 * Specify the Hibernate FlushMode to apply to this filter's
	 * {@link org.hibernate.Session}. Only applied in single session mode.
	 * <p>Can be populated with the corresponding constant name in XML bean
	 * definitions: e.g. "AUTO".
	 * <p>The default is "NEVER". Specify "AUTO" if you intend to use
	 * this filter without service layer transactions.
	 * @see org.hibernate.Session#setFlushMode
	 * @see org.hibernate.FlushMode#NEVER
	 * @see org.hibernate.FlushMode#AUTO
	 */
	public void setFlushMode(FlushMode flushMode) {
		this.flushMode = flushMode;
	}

	/**
	 * Return the Hibernate FlushMode that this filter applies to its
	 * {@link org.hibernate.Session} (in single session mode).
	 */
	protected FlushMode getFlushMode() {
		return this.flushMode;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	public void init() {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub		
		ActionContext context = ActionContext.getContext();
	
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		SessionFactory sessionFactory = lookupSessionFactory(request);
		boolean participate = false;

//		if (isSingleSession()) {
			// single session mode
			if (TransactionSynchronizationManager.hasResource(sessionFactory)) {
				logger.debug("sessionFactory hasbeen into the TransactionSynchronizationManager");
				
				// Do not modify the Session: just set the participate flag.
				participate = true;
			}
			else {
				logger.debug("Opening single Hibernate Session in OpenSessionInViewFilter");
				Session session = getSession(sessionFactory);
				TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
			}
//		}
//		else {
//			// deferred close mode
//			if (SessionFactoryUtils.isDeferredCloseActive(sessionFactory)) {
//				// Do not modify deferred close: just set the participate flag.
//				participate = true;
//			}
//			else {
//				SessionFactoryUtils.initDeferredClose(sessionFactory);
//			}
//		}

		try {
			//filterChain.doFilter(request, response);
			return invocation.invoke();
		}

		finally {
			if (!participate) {
//				if (isSingleSession()) {
					// single session mode
					SessionHolder sessionHolder =
							(SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
					logger.debug("Closing single Hibernate Session in OpenSessionInViewFilter");
					closeSession(sessionHolder.getSession(), sessionFactory);
//				}
//				else {
//					// deferred close mode
//					SessionFactoryUtils.processDeferredClose(sessionFactory);
//				}
			}
		}
	}
	/**
	 * Look up the SessionFactory that this filter should use,
	 * taking the current HTTP request as argument.
	 * <p>The default implementation delegates to the {@link #lookupSessionFactory()}
	 * variant without arguments.
	 * @param request the current request
	 * @return the SessionFactory to use
	 */
	protected SessionFactory lookupSessionFactory(HttpServletRequest request) {
		return lookupSessionFactory();
	}

	/**
	 * Look up the SessionFactory that this filter should use.
	 * <p>The default implementation looks for a bean with the specified name
	 * in Spring's root application context.
	 * @return the SessionFactory to use
	 * @see #getSessionFactoryBeanName
	 */
	protected SessionFactory lookupSessionFactory() {
		if (logger.isDebugEnabled()) {
			logger.debug("Using SessionFactory '" + getSessionFactoryBeanName() + "' for OpenSessionInViewFilter");
		}
		ActionContext ctx = ActionContext.getContext();
		WebApplicationContext wac =(WebApplicationContext)ctx.getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		
//		String contextid = ctx.getApplication().get("CONTEXTID").toString();
//		
//		WebApplicationContext wac =
//				WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
//		WebApplicationContext wac1 = ContextLoader.getCurrentWebApplicationContext();
//		logger.debug("=========wac:::::::"+wac);
//		logger.debug("=========wac1:::::::"+wac1);
		SessionFactory sesionFactory=(SessionFactory)wac.getBean(getSessionFactoryBeanName(), SessionFactory.class);
		logger.debug("the SessionFactory:"+sesionFactory);
		
		return sesionFactory;
		//return (SessionFactory) wac.getBean(getSessionFactoryBeanName(), SessionFactory.class);
	}

	/**
	 * Get a Session for the SessionFactory that this filter uses.
	 * Note that this just applies in single session mode!
	 * <p>The default implementation delegates to the
	 * <code>SessionFactoryUtils.getSession</code> method and
	 * sets the <code>Session</code>'s flush mode to "NEVER".
	 * <p>Can be overridden in subclasses for creating a Session with a
	 * custom entity interceptor or JDBC exception translator.
	 * @param sessionFactory the SessionFactory that this filter uses
	 * @return the Session to use
	 * @throws DataAccessResourceFailureException if the Session could not be created
	 * @see org.springframework.orm.hibernate3.SessionFactoryUtils#getSession(SessionFactory, boolean)
	 * @see org.hibernate.FlushMode#NEVER
	 */
	protected Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
		Session session = SessionFactoryUtils.getSession(sessionFactory, true);
		FlushMode flushMode = getFlushMode();
		if (flushMode != null) {
			session.setFlushMode(flushMode);
		}
		return session;
	}

	/**
	 * Close the given Session.
	 * Note that this just applies in single session mode!
	 * <p>Can be overridden in subclasses, e.g. for flushing the Session before
	 * closing it. See class-level javadoc for a discussion of flush handling.
	 * Note that you should also override getSession accordingly, to set
	 * the flush mode to something else than NEVER.
	 * @param session the Session used for filtering
	 * @param sessionFactory the SessionFactory that this filter uses
	 */
	protected void closeSession(Session session, SessionFactory sessionFactory) {
		SessionFactoryUtils.closeSession(session);
	}
	
	private String code;
	public void setCode(String code){
		this.code=code;
		logger.debug("在配置文件中设置的code值:::"+code);
	}
}
