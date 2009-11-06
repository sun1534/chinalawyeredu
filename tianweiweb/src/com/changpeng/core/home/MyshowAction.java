package com.changpeng.core.home;


import java.util.List;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserService;

public class MyshowAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(MyshowAction.class);
	
	List userlist;
	
	public MyshowAction() {
		this.rightCode="PUBLIC";
		this.moduleid = 11;
	}

	@Override
	protected String go() throws Exception {
		UserService userservice = (UserService) this.getBean("userService");
		userlist=userservice.getMyshowlistindex();
		return SUCCESS;

	}

	public List getUserlist() {
		return userlist;
	}
}
