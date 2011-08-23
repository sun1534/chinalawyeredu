package com.sxit.common.action;

import java.util.Iterator;

import org.hibernate.HibernateException;

import com.opensymphony.xwork2.ActionContext;
import com.sxit.system.model.*;

/**
 *
 * <p>功能： 退出登录 </p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2004.9.29</p>
 * @版本： V1.0
 * @修改：
 */


public class UserLogoutAction extends AbstractAction {
  public UserLogoutAction() {
  }
protected String go() throws HibernateException {

//    set("curuser",null);
//    set("rights",null);
//    set("curwebuser",null);
	Iterator sessions=ActionContext.getContext().getSession().keySet().iterator();
	while(sessions.hasNext()){
		Object sessionName=sessions.next();
		LOG.info("系统退出,将清除掉会话信息:"+sessionName);
		if(sessionName.toString().equals("curuser")){
			TsysUser user=(TsysUser)get("curuser");
			SysLoginlog log = (SysLoginlog)getSession().get(SysLoginlog.class, user.getLoginId());	
			if(log!=null){
				log.setLogouttime(new java.util.Date());						
				log.setLogoutremarks("系统正常退出.");				
				getSession().update(log);						
			}	
		}
		ActionContext.getContext().getSession().remove(sessionName);
	}
	
    return "success";
  }
}
