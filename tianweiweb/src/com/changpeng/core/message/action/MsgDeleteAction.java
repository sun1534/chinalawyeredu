/**
 * 删除或清空 站内信
 */
package com.changpeng.core.message.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.GeneralmessageService;

public class MsgDeleteAction extends AbstractAction {
	private final int SENDBOX=2;
	private final int INBOX=1;
	
	/**
	 * 要删除的消息id
	 */
	private int commid;// 消息ID

	/**
	 * 要删除的是收件箱还是发件箱的标识
	 */
	private int sendtype;
	
	/**
	 * 删除单条还是清空的标识
	 *  9 清空
	 */
	private int deltype;

	public void setCommid(int commid) {
		this.commid = commid;
	}

	@Override
	protected String go() {

		GeneralmessageService messageService = (GeneralmessageService) this.getBean("generalmessageService");

		if (deltype == 9) {
			if(sendtype==SENDBOX){
				messageService.clearSendBox(currentUserid);
			}else if(sendtype==INBOX){
				messageService.clearReceiveBox(currentUserid);
			}
		} else {
			messageService.deleteShortMessage(currentUserid, commid);
		}
		message = "删除成功";
		return SUCCESS;
	}

	public int getSendtype() {
		return sendtype;
	}

	public void setSendtype(int sendtype) {
		this.sendtype = sendtype;
	}

	public void setDeltype(int deltype) {
		this.deltype = deltype;
	}

}
