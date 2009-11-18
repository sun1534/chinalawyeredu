package com.sxit.wait.action;

import com.sxit.common.action.AbstractAction;

/**
 * 删除待办任务
 * 
 * @author 华锋 Jul 6, 2009 1:22:27 AM
 * 
 */

public class DeleteWaitAction extends AbstractAction {

	private int waitid;

	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public DeleteWaitAction() {

	}

	protected String go() throws org.hibernate.HibernateException {

		if (com.sxit.wait.util.WaitWork.DelWait(waitid, this.getLoginUser().getUserid())) {
	
			if (status == 0)
				this.nextPage = "shouldDoList.action";
			else
				this.nextPage = "hasBeenDoneList.action";
			this.message="任务信息删除成功";
		} else {
			this.message = "任务信息删除失败";

			this.nextPage = "javascript:history.go(-1)";
		}
		return SUCCESS;

	}

	

	public int getWaitid() {
		return waitid;
	}

	public void setWaitid(int waitid) {
		this.waitid = waitid;
	}

}
