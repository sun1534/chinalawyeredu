/**
 * IndexPageAction.java
 */

package com.changpeng.index;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lawyers;

/**
 * 
 * userlogin成功后,redirect到这个页面。防止刷新，重复登录
 * 
 * @author 华锋 2008-4-22 下午06:02:52
 * 
 */
public class IndexPageAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(IndexPageAction.class);

	
	public IndexPageAction(){
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		this.lawyer = this.getLoginUser();
		LOG.info(lawyer.getLawyerenname()+ "进入首页成功......");
		return SUCCESS;
	}

	public String top() throws Exception {

	    this.lawyer = getLoginUser();
		return "top";
	}

	public String left() throws Exception {

		this.lawyer = this.getLoginUser();
		return "left";
	}
	private Lawyers lawyer;
	public Lawyers getLawyers() {
		return this.lawyer;
	}
	
	public String getTopbarpic(){
		return com.changpeng.common.Constants.TOP_BAR_PIC;
	}
	
}
