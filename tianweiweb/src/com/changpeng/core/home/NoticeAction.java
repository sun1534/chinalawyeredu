package com.changpeng.core.home;


import java.util.List;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;

public class NoticeAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(NoticeAction.class);
	
	List noticelist;
	
	public NoticeAction() {
		this.rightCode="PUBLIC";
		this.moduleid = 11;
	}

	@Override
	protected String go() throws Exception {
		
		noticelist=service.findNumList("from TinfInfo where typeid=100 and statusid=0", 10);
		return SUCCESS;

	}

	public List getNoticelist() {
		return noticelist;
	}
}
