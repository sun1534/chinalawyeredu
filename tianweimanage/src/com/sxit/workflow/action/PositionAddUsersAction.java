package com.sxit.workflow.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysUser;
import com.sxit.models.workflow.TwflPosition;
import com.sxit.models.workflow.TwflPositionUser;

/**
 * 
 * @author 华锋 Jul 9, 2009 9:43:58 PM
 * 
 */

public class PositionAddUsersAction extends AbstractAction {

	private List userlist;
	private String[] check;
	private int positionid;

	public PositionAddUsersAction() {

	}

	protected String go() throws Exception {
		nextPage = "positionViewUsers.action?positionid=" + positionid;
		TwflPosition twflPosition=new TwflPosition();
		twflPosition.setPositionid(positionid);
		for (int i = 0; check != null && i < check.length; i++) {
			TwflPositionUser positionuser = new TwflPositionUser();
		
			positionuser.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			positionuser.setCreateuserid(this.getLoginUser().getUserid());
			SysUser sysUser=new SysUser();
			sysUser.setUserid(Integer.parseInt(check[i]));
			positionuser.setSysUser(sysUser);
			positionuser.setTwflPosition(twflPosition);

			basicService.save(positionuser);
		}

		message = "新增用户成功！";
		return "message";

	}

	@Override
	public String input() {

		List thelist = basicService.find("from TwflPositionUser where positionid=" + positionid);
		userlist = new ArrayList();
		Iterator iterator = com.sxit.system.util.CommonDatas.usernameMap.keySet().iterator();
		while (iterator.hasNext()) {
			userlist.add(Integer.parseInt(iterator.next().toString()));
		}
		List remove = new ArrayList();
		for (int i = 0; thelist != null && i < thelist.size(); i++) {
			TwflPositionUser user = (TwflPositionUser) thelist.get(i);
			remove.add(user.getSysUser().getUserid());
		}
		userlist.removeAll(remove);

		return "input";
	}


	public int getPositionid() {
		return positionid;
	}

	public void setPositionid(int positionid) {
		this.positionid = positionid;
	}

	public List getUserlist() {
		return userlist;
	}

	public void setCheck(String[] check) {
		this.check = check;
	}

}
