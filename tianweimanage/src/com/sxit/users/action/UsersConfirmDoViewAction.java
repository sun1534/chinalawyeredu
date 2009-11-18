/**
 * 
 */
package com.sxit.users.action;

import java.util.List;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.users.CoreUser;
import com.sxit.models.users.CoreUserDetail;

/**
 * 
 * 点处理的时候，进入查看这个人的信息页面，并在同意页面，输入处理意见
 * 
 * @author 华锋 Jul 12, 2009 12:41:42 PM
 * 
 */
public class UsersConfirmDoViewAction extends AbstractAction {
	private int businessid;

	public UsersConfirmDoViewAction() {
		this.businessid = 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		user = (CoreUser) basicService.get(CoreUser.class, userId);

		if (user == null) {
			this.message = "待认证的的用户已不存在";
			return "message";
		} 
//		else if (!(user.getStatus() == 2||user.getStatus()==3)) {
//			this.message = "该用户已经认证通过";
//			return "message";
//		}

		detail = (CoreUserDetail) basicService.get(CoreUserDetail.class, userId);
		if (detail != null)
			exist = true;

		String hql = "from com.sxit.models.workflow.TwflDohistory a where a.businessid=1 and a.serviceid=" + userId;
		dolist = basicService.find(hql);

		return SUCCESS;
	}

	private boolean exist;

	public boolean getExist() {
		return exist;
	}

	
	
	private List dolist;

	public List getDolist() {
		return this.dolist;
	}

	private CoreUser user;
	private CoreUserDetail detail;

	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public CoreUser getUser() {
		return user;
	}

	public CoreUserDetail getDetail() {
		return detail;
	}

	public int getBusinessid() {
		return businessid;
	}



}
