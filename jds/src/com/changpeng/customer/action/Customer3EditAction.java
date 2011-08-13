package com.changpeng.customer.action;

import org.hibernate.HibernateException;

import com.changpeng.customer.model.TusrCustomerNew;
import com.sxit.common.action.AbstractListAction;

/**
 * 当事人客户修改
 * 
 * @author sinhoo 2010-1-3
 */
public class Customer3EditAction extends AbstractListAction {

	private int customerid;
	private TusrCustomerNew customer;

	/**
	 * @return the customerid
	 */
	public int getCustomerid() {
		return customerid;
	}

	/**
	 * @param customerid
	 *            the customerid to set
	 */
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	/**
	 * @return the customer
	 */
	public TusrCustomerNew getCustomer() {
		if (customer == null)
			customer = (TusrCustomerNew) get("customer");
		return customer;
	}

	public String go() throws HibernateException {

		getSession().update(customer);
		set("customer", customer);
		nextpage = "customer3View.action?customerid="+customer.getCustomerid();
		this.message = "客户信息修改成功";
		return SUCCESS;

	}

	public String input() throws Exception {

		this.customer = (TusrCustomerNew) getSession().get(TusrCustomerNew.class, customerid);

		set("customer", customer);

		return INPUT;
	}

}
