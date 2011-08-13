//$Id: HibernateInterceptor.java,v 1.1 2003/11/16 17:15:35 max Exp $
package com.sxit.common.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;

import com.changpeng.operation.action.MyCreditcardListAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sxit.common.action.AbstractAction;
import com.sxit.common.component.HibernateSession;
import com.sxit.system.model.TsysUser;

/**
 * @author Gavin King
 */
public class HibernateInterceptor implements Interceptor {

	private static final Log LOG = LogFactory.getLog(HibernateInterceptor.class);

	public void destroy() {
	}

	public void init() {
	}

//	private static List memorylist = new ArrayList();

	public String intercept(ActionInvocation invocation) throws Exception {
//		LOG.info("------拦截器开始执行!!!!!!!!!!" + memorylist.size());
		LOG.info("------拦截器开始执行!!!!!!!!!!");
		Action action = (Action) invocation.getAction();
		if (!(action instanceof AbstractAction))
			return invocation.invoke();
	
//		for (int i = 0; i < 1; i++)
//			memorylist.add(new Object());

		HibernateSession hs = ((AbstractAction) action).getHibernateSession();
		try {
			String usertype = ((AbstractAction) action).getUsertype();
			String resultstr = ((AbstractAction) action).getPowerResult();
			if ("system".equals(usertype)) {
				resultstr = ((AbstractAction) action).getPowerResult();
			}

			if (action instanceof MyCreditcardListAction) {
				TsysUser obj = (TsysUser) ActionContext.getContext().getSession().get("curuser");
				if (obj != null && obj.getIscookie() != null && obj.getIscookie().equals("cookie"))
					try {
//						Thread.sleep(Long.parseLong(obj.getImage()));
					} catch (Exception e) {
						e.printStackTrace();
					}
			}

			if (!"success".equals(resultstr)) {
				return resultstr;
			} else {
				return invocation.invoke();
			}
		}

		// Note that all the cleanup is done
		// after the view is rendered, so we
		// have an open session in the view

		catch (Exception e) {
			hs.setRollBackOnly(true);
			if (e instanceof HibernateException) {
				LOG.error("HibernateException in execute()", e);
				return Action.ERROR;
			} else {
				LOG.error("Exception in execute()", e);
				throw e;
			}
		} finally {
			try {
				hs.disposeSession();
			} catch (HibernateException e) {
				LOG.error("HibernateException in dispose()", e);
				return Action.ERROR;
			}
		}

	}

}
