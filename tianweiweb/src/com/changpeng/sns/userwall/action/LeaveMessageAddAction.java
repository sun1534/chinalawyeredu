/**
 * Add userwall record or reply a userwall.
 * 2009-5-17 
 */
package com.changpeng.sns.userwall.action;


import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.userwall.service.UserWallService;

public class LeaveMessageAddAction extends AbstractAction {
	Logger log = Logger.getLogger(LeaveMessageAddAction.class);

	/**
	 * Reply messageid
	 */
	private String messageid = "0";

	/**
	 * Message content.
	 */
	private String content;
	
	/**
	 * Secrect
	 */
	boolean secrect;
	/**
	 * Anonymous
	 */
	boolean anonymous;
	@Override
	protected String go() {
		
		UserWallService userwallservice = (UserWallService) this.getBean("userWallService");

		userwallservice.addUserWall(currentUserid, viewUserid, content, userip, Integer.parseInt(messageid), secrect, anonymous);
		
		return SUCCESS;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSecrect(boolean secrect) {
		this.secrect = secrect;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

}
