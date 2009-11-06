package com.changpeng.core.home;


import java.util.List;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserService;

public class EntshowAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(EntshowAction.class);
	
	List entlist;
	
	public EntshowAction() {
		this.rightCode="PUBLIC";
		this.moduleid = 11;
		
	}

	@Override
	protected String go() throws Exception {
		UserService userservice = (UserService) this.getBean("userService");
		entlist=userservice.getEntlistindex();
		return SUCCESS;
	}

	public List getEntlist() {
		return entlist;
	}
}
