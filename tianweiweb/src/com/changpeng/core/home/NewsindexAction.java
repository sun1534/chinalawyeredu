package com.changpeng.core.home;


import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.InfoService;

public class NewsindexAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	List newslist;
	
	public NewsindexAction() {
		this.rightCode="PUBLIC";
		this.moduleid = 11;
	}

	@Override
	protected String go() throws Exception {
		InfoService userservice = (InfoService) this.getBean("infoService");
		newslist=userservice.getNewsindex();
		return SUCCESS;
	}

	public List getNewslist() {
		return newslist;
	}
}
