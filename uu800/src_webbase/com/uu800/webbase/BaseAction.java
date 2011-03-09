/**
 * BaseAction.java
 */
package com.uu800.webbase;

import com.opensymphony.xwork2.ActionSupport;
import com.uu800.webbase.PageSupport;

/**
 * 
 * 
 * 
 * @author 华锋
 * Jul 12, 2010 2:46:55 PM
 */
public abstract class BaseAction extends ActionSupport {
	/**
	 * 每页显示的个数
	 */
	protected int pageSize = 10;
	/**
	 * 页码
	 */
	protected int pageNo = 1;
	/**
	 * 分页类
	 */
	
	protected PageSupport page;

	/**
	 * action处理完毕,跳转到信息页面后,继续返回的页面
	 */
	protected String nextPage = "javascript:history.go(-1)";

	/** 
	 * 错误或者信息显示页面上,显示的消息 
	 */
	protected String message = "";

	/**
	 *用户访问的ip地址 
	 */
	protected String userIp = "";

	/**
	 * 遮罩层 点击确定之后如果需要跳转到另一个页面，设置这个url
	 */
	//protected String redirectURL = "$.jDialog.removeBox();";
	protected String redirectURL = "";

	/**
	 * 这个action，是否需要登录才可以执行
	 */
	protected boolean needLogin = false;


	/**
	 * @return the nextPage
	 */
	public String getNextPage() {
		return nextPage;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	
	/**
	 * @return the redirectURL
	 */
	public String getRedirectURL() {
		//onclick="";
		
		
		return redirectURL;
	}

	public BaseAction() {
		if (pageNo == 0)
			pageNo = 1;
	}

	public PageSupport getPage() {
		return page;
	}



	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the userIp
	 */
	public String getUserIp() {
		return userIp;
	}

	/**
	 * @param userIp
	 *            the userIp to set
	 */
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}


	/**
	 * @return the needLogin
	 */
	public boolean getNeedLogin() {
		return needLogin;
	}

	/**
	 * @param needLogin the needLogin to set
	 */
	public void setNeedLogin(boolean needLogin) {
		this.needLogin = needLogin;
	}

	/**
	 * @param nextPage the nextPage to set
	 */
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

}