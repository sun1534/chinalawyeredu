/**
 * 
 */
package com.changpeng.lawcase.action;

import java.text.DateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.lawcase.model.TlawCaseJixiao;
import com.changpeng.lawcase.model.TlawLawcase;
import com.sxit.common.action.AbstractListAction;

/**
 * 
 * 案件登记列表
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class CaseJixiaoListAction extends AbstractListAction {

	private long caseid;
	private TlawLawcase lawcase;
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);

		Criteria criteria = getSession().createCriteria(TlawCaseJixiao.class).add(Restrictions.eq("caseid", caseid));
		// 按入库顺序逆序排列
		criteria.addOrder(Order.desc("jixiaologid"));
		jixiaolist = this.page(criteria);

		return SUCCESS;
	}

	private List jixiaolist;

	public List getJixiaolist() {
		return this.jixiaolist;
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
