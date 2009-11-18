package com.sxit.users.action;

import java.text.DateFormat;

import com.sxit.common.action.AbstractListAction;

/**
 * 
 *  需要进行认证的用户列表
 *  1,家庭 2企业',
 * @author 华锋
 * Jul 9, 2009 11:16:21 PM
 *
 */

public class WaitListAction extends AbstractListAction {
	public WaitListAction() {

	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public String go() throws Exception {
	
		return SUCCESS;
	}




}
