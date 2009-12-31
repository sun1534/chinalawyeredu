/**
 * IndexPageAction.java
 */

package com.sxit.index;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.sxit.common.action.AbstractAction;
import com.sxit.netquality.service.BasicSetService;

/**
 * 
 * 
 * 
 * @author 华锋 2008-4-22 下午06:02:52 2009-3-6 Tompan 修改加上获取用户信息
 * 
 */
public class MyWorkspacePageAction extends AbstractAction {

	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");

	

	public MyWorkspacePageAction() {

	}

	
	@Override
	protected String go() throws Exception {


		return SUCCESS;
	}

	

}