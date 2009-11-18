package com.sxit.workflow.action;

import java.util.List;
import java.util.Set;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflBusiness;
import com.sxit.models.workflow.TwflPosition;

/**
 * 这个业务哪些人用
 * 
 * @author 华锋 Jul 9, 2009 9:32:09 PM
 * 
 */

public class PositionViewUsersAction extends AbstractAction {

	private TwflPosition position;

	private int positionid;
	private Set positionusers;

	public PositionViewUsersAction() {

	}

	protected String go() throws Exception {
		this.position = (TwflPosition) basicService.get(TwflPosition.class, positionid);
		
		userlist = basicService.find("from TwflPositionUser where twflPosition.positionid=" + positionid);
		return SUCCESS;
	}
	private List userlist;

	public List getUserlist() {
		return this.userlist;
	}

	public int getPositionid() {
		return positionid;
	}

	public void setPositionid(int positionid) {
		this.positionid = positionid;
	}

	public TwflPosition getPosition() {
		

		return position;
	}

	public Set getPositionusers() {
		return positionusers;
	}

	
}
