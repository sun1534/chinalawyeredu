package com.uu800.admin.base.action;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysLogs;

public class LogOutAction extends AbstractAdminAction 
{
	private static Log LOG = LogFactory.getLog(AbstractAdminAction.class);

	public LogOutAction(){
		this.needLogin=false;
	}
	
	@Override
	public String execute() {
		
	
		Iterator sessions=ActionContext.getContext().getSession().keySet().iterator();
		while(sessions.hasNext())
		{
			Object sessionName=sessions.next();
			LOG.info("系统退出,将清除掉会话信息:"+sessionName);
			ActionContext.getContext().getSession().remove(sessionName);
		}
	    return SUCCESS;
	  }
	}
