package com.changpeng.core.message.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.message.util.MsgUtil;
import com.changpeng.core.service.GeneralmessageService;
import com.changpeng.core.user.model.Userinfo;

public class MsgReplyAction extends AbstractAction {
	private int msgid;// 消息ID
	private String content;// 内容
	private int desuserid;// 接收人ID
	MsgUtil util;
	
	Userinfo destuser;
	@Override
	protected String go() {
		GeneralmessageService msgService = (GeneralmessageService) this.getBean("generalmessageService");
		if(msgid!=0){
			msgService.saveReplyMessage(content, msgid, currentUserid);
		}else{
			msgService.saveShortMessage(currentUserid, new int[]{currentUserid,desuserid}, content, false);
		}
		return SUCCESS;
	}

	public String getin() {
		util=new MsgUtil();
//		UserService userservice=(UserService)this.getBean("userService");
//		destuser = (Userinfo) userservice.getUserinfoById(desuserid);
		
		return "input";
	}
	
	
	public int getMsgid() {
		return msgid;
	}

	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDesuserid(int desuserid) {
		this.desuserid = desuserid;
	}

	public int getDesuserid() {
		return desuserid;
	}

	public Userinfo getDestuser() {
		return destuser;
	}

	public void setDestuser(Userinfo destuser) {
		this.destuser = destuser;
	}

}
