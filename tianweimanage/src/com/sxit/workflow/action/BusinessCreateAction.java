package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflBusiness;

/**
 * 
 * @author 华锋 Jul 9, 2009 3:47:02 PM
 * 
 */

public class BusinessCreateAction extends AbstractAction {

	private TwflBusiness business;

	public BusinessCreateAction() {

		business = new TwflBusiness();
	}

	public String go() throws Exception {

		business.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		business.setCreateuserid(this.getLoginUser().getUserid());
		business.setOrdernum(0);
	business.setStatusid(1);

		basicService.save(business);
		this.nextPage="businessList.action";
		this.message="业务新增成功";
		
		
		return SUCCESS;
	}

	public TwflBusiness getBusiness() {
		return business;
	}

}
