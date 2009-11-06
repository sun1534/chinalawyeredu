package com.changpeng.core.message.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.SystemMessageService;

public class MsgSysDeleteAction extends AbstractAction {

	private int sysmessid;
	private int type = 0;

	@Override
	protected String go() throws Exception {
		SystemMessageService systemMessageService = (SystemMessageService) this.getBean("systemMessageService");

		if (type == 0) {
			// 删除一条记录
			systemMessageService.deleteSysMsgById(sysmessid, currentUserid);
			this.redirectURL = "../message/systemmessagelist.action";
			message = "删除成功";
		} else {
			// 清空所有
			systemMessageService.deleteMsgByUserid(currentUserid);
			this.redirectURL = "../message/systemmessagelist.action";
			message = "清空成功";
		}
		return "message";
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setSysmessid(int sysmessid) {
		this.sysmessid = sysmessid;
	}

}
