package com.changpeng.sns.userwall.action;


import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.userwall.service.UserWallService;

public class IndexAddMessageAction extends AbstractAction {
	Logger log = Logger.getLogger(IndexAddMessageAction.class);
	private String messageid = "0";

	private String content;
	private boolean secrect;
	private boolean anonymous;
	@Override
	protected String go() {
		UserWallService userwallservice = (UserWallService) this.getBean("userWallService");

		userwallservice.addUserWall(currentUserid, viewUserid, content, userip, Integer.parseInt(messageid), secrect, anonymous);
		
		return SUCCESS;
		
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}
	
}
