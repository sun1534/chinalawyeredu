package com.changpeng.core.home;


import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.InfoService;

public class RedianindexAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	List redianlist;
	
	public RedianindexAction() {
		this.rightCode="PUBLIC";
		this.moduleid = 11;
	}

	@Override
	protected String go() throws Exception {
		InfoService userservice = (InfoService) this.getBean("infoService");
		redianlist=userservice.getRedianindex();
		return SUCCESS;
	}

	public List getRedianlist() {
		return redianlist;
	}
}
