package com.changpeng.sns.userwall.action;


import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.userwall.service.UserWallService;

public class LeaveMessageDeleteAction extends AbstractAction {

	Logger log = Logger.getLogger(LeaveMessageDeleteAction.class);
	private int messageid;

	private String ishome;

	@Override
	protected String go() {
		UserWallService userwallservice = (UserWallService) this.getBean("userWallService");
		
		userwallservice.deleteUserWall(messageid, currentUserid);
		if (ishome != null && !ishome.equals("")) {
			return "home";
		}
		return SUCCESS;

	}

	public void setIshome(String ishome) {
		this.ishome = ishome;
	}

	public int getMessageid() {
		return messageid;
	}

	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
}
