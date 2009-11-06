package com.changpeng.core.home;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserPersonalService;
import com.changpeng.core.user.model.CoreUserPersonal;


/**
 * 显示这个人的系统消息、站内消息以及留言回复的个数
 * @author 华锋
 * May 16, 2009 3:56:17 PM
 *
 */
public class MessageCountShowAction extends AbstractAction{
  
	@Override
	protected String go(){
		// TODO Auto-generated method stub
		UserPersonalService userPersonalService = (UserPersonalService) this.getBean("userPersonalService");

		msgcounts=userPersonalService.getHomeMsgCount(this.currentUserid);
		
		return SUCCESS;
	}

	
	private String msgcounts;
	public String getMsgcounts()
	{
		return this.msgcounts;
	}
	
	
}
