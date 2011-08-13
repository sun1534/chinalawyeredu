/**
 * 
 */
package com.changpeng.lawcase.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.criterion.Order;

import com.changpeng.lawcase.model.TlawKaitingDengji;
import com.changpeng.lawcase.model.TlawLawcase;
import com.sxit.common.action.AbstractListAction;

/**
 * 
 * 显示所有的开庭登记列表,应该标志下已处理开庭信息
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class CaseDengjiListAction extends AbstractListAction {

	private long caseid;
	private TlawLawcase lawcase;
//	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

	
		Criteria criteria = getSession().createCriteria(TlawKaitingDengji.class);
		// 按入库顺序逆序排列
		criteria.addOrder(Order.desc("kaitingdate"));
		dengjilist = this.page(criteria);

		return SUCCESS;
	}

	private List dengjilist;

	public List getDengjilist() {
		return this.dengjilist;
	}

	/**
	 * @return the caseid
	 */
	public long getCaseid() {
		return caseid;
	}

	/**
	 * @param caseid
	 *            the caseid to set
	 */
	public void setCaseid(long caseid) {
		this.caseid = caseid;
	}

	/**
	 * @return the lawcase
	 */
	public TlawLawcase getLawcase() {
		return lawcase;
	}

}
