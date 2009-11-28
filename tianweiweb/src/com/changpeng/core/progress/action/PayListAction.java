package com.changpeng.core.progress.action;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.ProgressService;

public class PayListAction extends AbstractListAction {

	private String pageString;

	public PayListAction() {
		this.rightCode = "PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		if (this.currentUserid == 0) {
			return "payshow";
		}
		pageSize = 8;
		ProgressService progressservice = (ProgressService) this.getBean("progressService");

		page = progressservice.getPayList(currentUserid, pageSize, pageNo);

		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo, "getPage");
		return SUCCESS;
	}

	public String getPageString() {
		return pageString;
	}

}
