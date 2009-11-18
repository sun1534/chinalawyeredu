package com.sxit.workflow.action;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflBusiness;

/**
 * 
 * 
 * @author 华锋
 * Jul 10, 2009 12:05:44 AM
 *
 */
public class BusinessEditAction extends AbstractAction {

	private TwflBusiness business;

	public String go() throws Exception {

		
		basicService.update(business);
		this.nextPage="businessList.action";
		this.message="流程业务信息修改成功";
		return SUCCESS;
	}
	
	public String input(){
	
		TwflBusiness business=(TwflBusiness)basicService.get(TwflBusiness.class, businessid);
		set("business",business);
		
		return INPUT;
	}

	public TwflBusiness getBusiness() {
		if (business == null)
			business = (TwflBusiness) get("business");
		return business;
	}
	
	private int businessid;

	public void setBusinessid(int businessid) {
		this.businessid = businessid;
	}
	

}
