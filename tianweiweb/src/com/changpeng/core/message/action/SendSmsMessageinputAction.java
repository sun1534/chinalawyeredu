package com.changpeng.core.message.action;

import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.Userinfo;

/**
 * 
 * 
 * 发送手机短信的进入页面
 * 
 * @author 华锋
 * 
 */
public class SendSmsMessageinputAction extends AbstractListAction {
	Logger log=Logger.getLogger(SendSmsMessageinputAction.class);

	private String mobile;


	@Override
	protected String go() throws Exception {

		UserService userService = (UserService) this.getBean("userService");

		mobile=userService .getUserById(currentUserid).getMobile();
		// log.debug("=====================111");
		return SUCCESS;
	}

	public String getMobile() {
		return mobile;
	}

}
