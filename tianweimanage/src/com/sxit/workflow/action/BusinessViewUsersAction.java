package com.sxit.workflow.action;

import java.util.List;
import java.util.Set;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflBusiness;

/**
 * 这个业务哪些人用
 * 
 * @author 华锋 Jul 9, 2009 9:32:09 PM
 * 
 */

public class BusinessViewUsersAction extends AbstractAction {

	private TwflBusiness business;

	private int businessid;
	private Set businessusers;

	public BusinessViewUsersAction() {

	}

	protected String go() throws Exception {

		return SUCCESS;
	}

	public TwflBusiness getBusiness() {

		this.business = (TwflBusiness) basicService.get(TwflBusiness.class, businessid);

		userlist = basicService.find("from TwflBusinessUser where businessid=" + businessid);

		return business;
	}

	private List userlist;

	public List getUserlist() {
		return this.userlist;
	}

	public int getBusinessid() {
		return businessid;
	}

	public void setBusinessid(int businessid) {
		this.businessid = businessid;
	}

}
