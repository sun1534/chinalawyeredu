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
public class Customer3ViewAction extends AbstractListAction {
	private List addressList;
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
		return customer;
	}

	public String go() throws HibernateException {

		this.customer = (TusrCustomerNew) getSession().get(TusrCustomerNew.class, customerid);
		addressList = getSession().createQuery(" from TusrAddress where customerid=" + customerid).list();
		return SUCCESS;
	}

	/**
	 * @return the addressList
	 */
	public List getAddressList() {
		return addressList;
	}

}
