/**
 * JifenQueryAction.java
 */

package com.changpeng.jifen.action;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Base64;

/**
 * 点此，将链接到点睛的课程学习处，需要点开新的窗口
 * 
 * @author 华锋 2008-5-5 上午12:08:59
 * 
 */
public class DianjingLearnAction extends AbstractListAction {

	public DianjingLearnAction() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractListAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// SysUser lawer = ;
		int lawyerid = this.getLoginUser().getLawyerid();
		String name = this.getLoginUser().getLawyername();
		url = "";
		url += "?stoken=" + new String(Base64.encode((lawyerid + "|" + name).getBytes("gb2312")), "gb2312");

		return SUCCESS;
	}

	private String url;

	public String getUrl() {
		return this.url;
	}
}