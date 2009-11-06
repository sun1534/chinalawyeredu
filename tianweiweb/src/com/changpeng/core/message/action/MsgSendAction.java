package com.changpeng.core.message.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.FriendService;
import com.changpeng.core.service.GeneralmessageService;
import com.changpeng.core.service.UserService;

/**
 * 
 * @author 发送消息
 */
public class MsgSendAction extends AbstractAction {
	Logger log = Logger.getLogger(MsgSendAction.class);

	private String content;// 内容

	private int[] desuserid;// 接收人ID

	/**
	 * 发送对象 的角色
	 *  1 学生
	 *  2 家长
	 *  3 老师
	 *  如果是其他的标识为好友
	 */
	private int sendto;

	@Override
	protected String go() {
		GeneralmessageService messageService = (GeneralmessageService) this.getBean("generalmessageService");
//		log.debug("站内信目标人数:"+desuserid.length);
//		log.debug("站内信内容:"+content);
		for (int destuid : desuserid) {
			messageService.saveShortMessage(currentUserid, new int[] { currentUserid,destuid }, content, false);
		}
		return SUCCESS;
	}

	

	public MsgSendAction() {
		moduleid=12;
	}



	public void setContent(String content) {
		this.content = content;
	}

	public void setDesuserid(int[] desuserid) {
		this.desuserid = desuserid;
	}

	public int getSendto() {
		return sendto;
	}

	public void setSendto(int sendto) {
		this.sendto = sendto;
	}

	@Override
	public String getin() {
		UserService userService = (UserService) this.getBean("userService");
		FriendService friendService=(FriendService)this.getBean("friendService");

		hasfriend=friendService.getMyFriendCount(currentUserid);
		return INPUT;
	}
	
	
	/**
	 * 判断当前用户有没有好友 
	 *  0 没有好友
	 *  1 有好友
	 */
	private int hasfriend;


	public int getHasfriend() {
		return hasfriend;
	}
	
}
