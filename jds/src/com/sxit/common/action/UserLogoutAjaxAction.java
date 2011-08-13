/**
 * UserLogoutAction.java
 */

package com.sxit.common.action;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;

import com.opensymphony.xwork2.ActionContext;
import com.sxit.system.model.SysLoginlog;
import com.sxit.system.model.TsysUser;

/**
 * 用户关闭窗口,调用这个action
 * 
 * @author 华锋 2008-2-22 上午11:13:33
 * 
 */
public class UserLogoutAjaxAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(UserLogoutAjaxAction.class);


	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws HibernateException {

		Iterator sessions=ActionContext.getContext().getSession().keySet().iterator();
		while(sessions.hasNext()){
			Object sessionName=sessions.next();
			LOG.info("AJAX系统退出,将清除掉会话信息:"+sessionName);
			if(sessionName.toString().equals("curuser")){
				TsysUser user=(TsysUser)get("curuser");
				SysLoginlog log = (SysLoginlog)getSession().get(SysLoginlog.class, user.getLoginId());	
				if(log!=null){
					log.setLogouttime(new java.util.Date());	
					
					log.setLogoutremarks("用户关闭窗口退出.");
						
					getSession().update(log);	
					commit();
				}					
				
			}
			ActionContext.getContext().getSession().remove(sessionName);
		}
		return SUCCESS;
		// return SUCCESS;
	}
	
	private String now;
	public void setNow(String now){
		this.now=now;
	}
}