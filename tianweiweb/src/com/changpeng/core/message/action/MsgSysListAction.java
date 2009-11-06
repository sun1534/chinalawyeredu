package com.changpeng.core.message.action;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.core.service.SystemMessageService;
import com.changpeng.core.service.UserPersonalService;

public class MsgSysListAction extends AbstractListAction {

	private String pageString;

	
	public MsgSysListAction(){
		pageSize=10;
		moduleid=12;
	}
	@Override
	protected String go() {
		
		SystemMessageService systemMessageService = (SystemMessageService) this.getBean("systemMessageService");
		UserPersonalService ups=(UserPersonalService)this.getBean("userPersonalService");
		ups.clearInnerMsgCount(this.currentUserid);
		if(pageNo<1) pageNo=1;
		page = systemMessageService.getSystemMessageList(currentUserid, pageSize, pageNo);

		pageString = page.getJxqPage();
		return SUCCESS;
	}

	
	public String getPageString() {
		return pageString;
	}
}
