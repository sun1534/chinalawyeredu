package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;

public class BusinessDeleteUsersAction extends AbstractAction {

	private int businessid;
	private String[] userid;

	public BusinessDeleteUsersAction() {

	}

	protected String go() throws Exception {
		nextPage = "businessViewUsers.action?businessid=" + businessid;

		String hqlDelete = "delete from TwflBusinessUser where businessid =" + businessid + " and userid in (" + array2str() + ")";
		basicService.execute(hqlDelete);
		message = "业务用户删除成功！";
		return "message";

	}

	private String array2str() {
		if (userid == null || userid.length == 0)
			return "-1";

		StringBuffer result = new StringBuffer();
		for (int i = 0; i < userid.length; i++) {
			result.append(userid[i]);
			if (i != userid.length - 1)
				result.append(",");
		}
		return result.toString();

	}

	public int getBusinessid() {
		return businessid;
	}

	public void setBusinessid(int businessid) {
		this.businessid = businessid;
	}

	public void setUserid(String[] userid) {
		this.userid = userid;
	}

}
