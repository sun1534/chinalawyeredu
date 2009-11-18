package com.sxit.workflow.action;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;

public class PositionDeleteUsersAction extends AbstractAction {

	private int positionid;
	private String[] userid;

	public PositionDeleteUsersAction() {

	}

	protected String go() throws Exception {
		nextPage = "positionViewUsers.action?positionid=" + positionid;

		String hqlDelete = "delete from TwflPositionUser where positionid =" + positionid + " and sysUser.userid in (" + array2str() + ")";
		basicService.execute(hqlDelete);
		message = "职务用户删除成功！";
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

	public int getpositionid() {
		return positionid;
	}

	public void setpositionid(int positionid) {
		this.positionid = positionid;
	}

	public void setUserid(String[] userid) {
		this.userid = userid;
	}

}
