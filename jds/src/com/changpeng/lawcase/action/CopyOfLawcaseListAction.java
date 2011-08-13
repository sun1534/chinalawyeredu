/**
 * 
 */
package com.changpeng.lawcase.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.lawcase.model.TlawLawcase;
import com.sxit.common.action.AbstractListAction;

/**
 * 
 * 新增案件、存量案件、异常案件等
 * 
 * @author 华锋 Oct 26, 2009-10:07:19 AM
 * 
 */
public class CopyOfLawcaseListAction extends AbstractListAction {

	private String jiekuanren;
	private String thedate;
	private String contractno;
	private int bankid;
	private String theidcard;
	/**
	 * 案件的状态,新增案件?存量案件?已结案件?默认是新增案件
	 * 
	 * 入口案件为提交到总经理进行了分配的，也就是status=1
	 * 存量案件为已经缴费了，也就是计算了诉讼承办人的诉状以及立案绩效
	 * 已结案件分为阶段性已结和完全性结案
	 * 
	 * 入口案件(1)、出口案件、存量案件(10,11)、异常案件(30)
	 * 
	 */
	private String statusid= "";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		Criteria criteria = getSession().createCriteria(TlawLawcase.class);

		// criteria.add(Restrictions.eq("nowworkerid",
		// this.curuser.getUserid()));

		if ((jiekuanren != null&&!jiekuanren.equals("")) ||(theidcard != null&&!theidcard.equals(""))) {
			criteria.createAlias("jiekuanren", "jiekuanren");
			if (jiekuanren != null)
				criteria.add(Restrictions.like("jiekuanren.jiekuanren", jiekuanren, MatchMode.ANYWHERE));
			if (theidcard != null)
				criteria.add(Restrictions.like("jiekuanren.theidcard", theidcard, MatchMode.ANYWHERE));
		}

		if (thedate != null&&!thedate.equals("")) {
			criteria.add(Restrictions.eq("thedate", thedate));
		}
		if (contractno != null&&!contractno.equals("")) {
			criteria.add(Restrictions.like("contractno", contractno, MatchMode.ANYWHERE));
		}
		if (bankid > 0) {
			criteria.add(Restrictions.eq("bankid", bankid));
		}
		String result=SUCCESS;
		if(statusid!=null&&!statusid.equals(""))
		{
			if(statusid.equals("rukou")){
				criteria.add(Restrictions.eq("statusid", 1));
				result="rukou";
			}else if(statusid.equals("exception")){
				criteria.add(Restrictions.eq("statusid", 40));
				result="exception";
			}else if(statusid.equals("cunliang")){//存量案件是缴费了的案件,如果已经分配了执行承办人,对于诉讼承办人来说,就是阶段性已结案件
				criteria.add(Restrictions.in("statusid", new Object[] { 10, 11 }));
				result="cunliang";
			}else if(statusid.equals("chukou")){
				criteria.add(Restrictions.eq("statusid", 30));
				result="chukou";
			}else if(statusid.equals("jieaning")){
				criteria.add(Restrictions.eq("statusid", 20));
				result="jieaning";
			}else{//不包括出口案件
				criteria.add(Restrictions.ne("statusid", 30));
				result="wait";//待处理的案件列表,在入口,存量已经已结案件里,不出现处理的字眼
			}
		}
		criteria.addOrder(Order.desc("createtime"));
		lawcaselist = page(criteria);
		return result;

	}

	private List lawcaselist;

	public List getLawcaselist() {
		return this.lawcaselist;

	}

	/**
	 * @return the jiekuanren
	 */
	public String getJiekuanren() {
		return jiekuanren;
	}

	/**
	 * @param jiekuanren
	 *            the jiekuanren to set
	 */
	public void setJiekuanren(String jiekuanren) {
		this.jiekuanren = jiekuanren;
	}

	/**
	 * @return the thedate
	 */
	public String getThedate() {
		return thedate;
	}

	/**
	 * @param thedate
	 *            the thedate to set
	 */
	public void setThedate(String thedate) {
		this.thedate = thedate;
	}

	/**
	 * @return the contractno
	 */
	public String getContractno() {
		return contractno;
	}

	/**
	 * @param contractno
	 *            the contractno to set
	 */
	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	/**
	 * @return the bankid
	 */
	public int getBankid() {
		return bankid;
	}

	/**
	 * @param bankid
	 *            the bankid to set
	 */
	public void setBankid(int bankid) {
		this.bankid = bankid;
	}

	/**
	 * @return the theidcard
	 */
	public String getTheidcard() {
		return theidcard;
	}

	/**
	 * @param theidcard
	 *            the theidcard to set
	 */
	public void setTheidcard(String theidcard) {
		this.theidcard = theidcard;
	}

	/**
	 * @return the status
	 */
	public String getStatusid() {
		return statusid;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatusid(String status) {
		this.statusid = status;
	}
}