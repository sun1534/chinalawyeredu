package com.changpeng.customer.action;

import java.util.List;

import org.hibernate.HibernateException;

import com.changpeng.customer.model.TusrCustomerNew;
import com.sxit.common.action.AbstractListAction;

/**
 * 当事人客户列表
 * 
 * @author sinhoo 2010-1-3
 */
public class Customer3ServiceListAction extends AbstractListAction {
	
	private int customerid;
	
	/**
	 * @return the customerid
	 */
	public int getCustomerid() {
		return customerid;
	}

	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	

	public String go() throws HibernateException {
		
		this.serviceList=getSession().createQuery("from TusrCustomerService a where a.tusrCustomerNew.customerid="+customerid).list();
		
		return SUCCESS;
	}

	public List serviceList;

	/**
	 * @return the serviceList
	 */
	public List getServiceList() {
		return serviceList;
	}
	
}
