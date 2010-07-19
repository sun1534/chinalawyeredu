/**
 * UserLogoutAction.java
 */

package com.changpeng.common.action;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.Constants;
import com.changpeng.lawyers.service.LawyerLoginLogService;
import com.changpeng.models.Lawyers;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用户关闭窗口,调用这个action
 * 
 * @author 华锋 2008-2-22 上午11:13:33
 * 
 */
public class UserLogoutAjaxAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(UserLogoutAjaxAction.class);

	public UserLogoutAjaxAction(){
		this.needsession=false;
	}
	private String now;
	public void setNow(String now){
		this.now=now;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		Iterator sessions=ActionContext.getContext().getSession().keySet().iterator();
		while(sessions.hasNext()){
			Object sessionName=sessions.next();
			LOG.info("AJAX系统退出,将清除掉会话信息:"+sessionName);
			if(sessionName.toString().equals(Constants.LOGIN_USER)){
				Lawyers sysUser =this.getLoginUser();
				LawyerLoginLogService service = (LawyerLoginLogService) getBean("lawyerLoginLogService");
				service.updateLogoutInfo(sysUser.getLawyerid(),sysUser.getLoginId(), "用户关闭窗口退出");
				ActionContext.getContext().getSession().remove(Constants.LOGIN_USER);
				
			}
			ActionContext.getContext().getSession().remove(sessionName);
		}
		return SUCCESS;
		// return SUCCESS;
	}
	
}