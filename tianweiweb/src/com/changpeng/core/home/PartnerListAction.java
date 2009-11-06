package com.changpeng.core.home;


import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.UserService;

public class PartnerListAction extends AbstractListAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(PartnerListAction.class);
	private String pageString;
	public PartnerListAction() {
		this.pageSize=30;
		this.rightCode="PUBLIC";
		this.moduleid = 11;
	}

	@Override
	protected String go() throws Exception {
		UserService userservice = (UserService) this.getBean("userService");
		this.page=userservice.getPartnerList(this.pageSize,this.pageNo);
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage");
		return SUCCESS;
	}

	public String getPageString() {
		return pageString;
	}

}
