package com.sxit.workflow.action;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflPosition;

/**
 * 
 * @author 华锋
 * Jul 10, 2009 12:05:23 AM
 *
 */

public class PositionEditAction extends AbstractAction {

	private TwflPosition position;

	public String go() throws Exception {

		
		basicService.update(position);
		return SUCCESS;
	}
	
	public String input(){
	
		TwflPosition position=(TwflPosition)basicService.get(TwflPosition.class, positionid);
		set("position",position);
		
		return INPUT;
	}

	public int getPositionid() {
		return positionid;
	}

	public void setPositionid(int positionid) {
		this.positionid = positionid;
	}

	public TwflPosition getPosition() {
		if (position == null)
			position = (TwflPosition) get("position");
		return position;
	}
	
	private int positionid;

	
	
}
