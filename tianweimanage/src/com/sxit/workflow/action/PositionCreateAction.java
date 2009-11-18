package com.sxit.workflow.action;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflBusiness;
import com.sxit.models.workflow.TwflPosition;

/**
 * 
 * @author 华锋 Jul 9, 2009 3:47:02 PM
 * 
 */

public class PositionCreateAction extends AbstractAction {

	private TwflPosition position;

	public PositionCreateAction() {

		position = new TwflPosition();
	}

	public String go() throws Exception {

		position.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		position.setCreateuserid(this.getLoginUser().getUserid());

		position.setGroupid(0);
	
		basicService.save(position);
		this.nextPage="positionList.action";
		this.message="职务新增成功";
		
	
		return SUCCESS;
	}



	public TwflPosition getPosition() {
		return position;
	}


}
