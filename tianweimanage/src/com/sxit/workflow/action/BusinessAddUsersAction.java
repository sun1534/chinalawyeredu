package com.sxit.workflow.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflBusinessUser;

/**
 * 
 * @author 华锋 Jul 9, 2009 9:43:58 PM
 * 
 */

public class BusinessAddUsersAction extends AbstractAction {

	private List userlist;
	private String[] check;
	private int businessid;

	public BusinessAddUsersAction() {

	}

	protected String go() throws Exception {
		nextPage = "businessViewUsers.action?businessid=" + businessid;

		for (int i = 0; check != null && i < check.length; i++) {
			TwflBusinessUser businessuser = new TwflBusinessUser();
			businessuser.setBusinessid(businessid);
			businessuser.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			businessuser.setCreateuserid(this.getLoginUser().getUserid());
			businessuser.setUserid(Integer.parseInt(check[i].toString()));

			basicService.save(businessuser);
		}

		message = "新增业务用户成功！";
		return "message";

	}

	@Override
	public String input() {

		List thelist = basicService.find("from TwflBusinessUser where businessid=" + businessid);
		userlist = new ArrayList();
		Iterator iterator = com.sxit.system.util.CommonDatas.usernameMap.keySet().iterator();
		while (iterator.hasNext()) {
			userlist.add(Integer.parseInt(iterator.next().toString()));
		}
		List remove = new ArrayList();
		for (int i = 0; thelist != null && i < thelist.size(); i++) {
			TwflBusinessUser user = (TwflBusinessUser) thelist.get(i);
			remove.add(user.getUserid());
		}
		userlist.removeAll(remove);

		return "input";
	}

	public int getBusinessid() {
		return businessid;
	}

	public void setBusinessid(int businessid) {
		this.businessid = businessid;
	}

	public List getUserlist() {
		return userlist;
	}

	public void setCheck(String[] check) {
		this.check = check;
	}

}
