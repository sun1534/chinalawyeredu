package com.changpeng.core.home;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.ProgressService;
import com.changpeng.core.service.UserService;


public class EntListAction extends AbstractListAction {

	private String pageString;

	public EntListAction(){
		this.rightCode="PUBLIC";
	}
	
	@Override
	protected String go() throws Exception {
		pageSize = 8;
		UserService userservice = (UserService)this.getBean("userService");
		
		page = userservice.getEntlist(pageSize, pageNo);

		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage");
		return SUCCESS;
	}
	


	public String getPageString() {
		return pageString;
	}

}
