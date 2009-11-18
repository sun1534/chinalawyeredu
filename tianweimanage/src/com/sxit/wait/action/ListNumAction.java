package com.sxit.wait.action;

import java.util.List;

import com.sxit.common.BasicService;
import com.sxit.common.Globals;
import com.sxit.common.action.AbstractAction;

/**
 * 显示这个人当前的待办任务个数
 * 
 * @author 华锋 Jul 6, 2009 1:22:41 AM
 * 
 */

public class ListNumAction extends AbstractAction {
	private int recordsize;

	public ListNumAction() {
	}

	protected String go() throws Exception {
	 BasicService basicService = (BasicService) Globals.getBean("basicService");

	 int userid=this.getLoginUser().getUserid();
	  List list=basicService.findBySqlQuery("select count(*) as cnt from twat_wait where status=0 and userid="+userid);
	 this.recordsize=Integer.parseInt(list.get(0).toString()); 
	 
    return SUCCESS;
  }

	public int getRecordsize() {
		return recordsize;
	}

}
