package com.changpeng.core.message.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.core.message.util.MsgUtil;
import com.changpeng.core.service.GeneralmessageService;
import com.changpeng.core.service.UserPersonalService;


public class MsgListAction extends AbstractListAction {
	Logger log = Logger.getLogger(MsgListAction.class);
	private final int SENDBOX=2;
	private final int INBOX=1;
	/**
	 * 消息类型：
	 *  1 收件箱
	 *  2 发件箱
	 */
	private int type; 
	
	/**
	 * 返回的消息列表
	 */
	private List msglist;
	
	/**
	 * 
	 */
	private MsgUtil util;
	/**
	 * 消息对象角色,暂无此需求
	 *  0 全部 
	 *  1 学生 
	 *  2 家长 
	 *  3 老师
	 */
	//private int desttype;
	
	@Override
	protected String go() {
		util=new MsgUtil();
		GeneralmessageService messageService = (GeneralmessageService) this.getBean("generalmessageService");

		if(type!=SENDBOX&&type!=INBOX){
			type=SENDBOX;
		}
		
		if(type==INBOX){
			UserPersonalService ups=(UserPersonalService)this.getBean("userPersonalService");
			ups.clearInnerMsgCount(this.currentUserid);
		}
		page=messageService.getShorMessage(currentUserid,type, pageSize, pageNo);
		msglist=page.getItems();
		return SUCCESS;
	}

	public MsgListAction() {
		moduleid=12;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List getMsglist() {
		return msglist;
	}

	public MsgUtil getUtil() {
		return util;
	}
	
}
