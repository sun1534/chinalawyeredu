package com.changpeng.core.home;


import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.InfoService;

public class HuodongindexAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	List huodonglist;
	
	public HuodongindexAction() {
		this.rightCode="PUBLIC";
		this.moduleid = 11;
	}

	@Override
	protected String go() throws Exception {
		InfoService userservice = (InfoService) this.getBean("infoService");
		huodonglist=userservice.getHuodongindex();
		return SUCCESS;
	}

	public List getHuodonglist() {
		return huodonglist;
	}
}
