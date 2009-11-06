package com.changpeng.sns.userwall.action;

import java.util.List;


import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.util.JugeTime;
import com.changpeng.sns.userwall.model.SnsUserWall;
import com.changpeng.sns.userwall.service.UserWallService;

public class IndexLeaveMessageAction extends AbstractAction {
	Logger log = Logger.getLogger(IndexLeaveMessageAction.class);

	public List<SnsUserWall> usermessagelist;
	private JugeTime jt;
	private int messagecount;

	@Override
	protected String go() {
		UserWallService userwallservice = (UserWallService) this.getBean("userWallService");
		jt = new JugeTime();
		usermessagelist = userwallservice.getUserWallList(viewUserid, 10, 1).getItems();
		messagecount = userwallservice.getMsgCount(viewUserid);
		
		return SUCCESS;

	}
	
	public List<SnsUserWall> getUsermessagelist() {
		return usermessagelist;
	}

	public int getMessagecount() {
		return messagecount;
	}
	
	public JugeTime getJt() {
		return jt;
	}
}
