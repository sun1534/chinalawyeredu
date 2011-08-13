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
 * 仅仅新增的案件，还没有提交到总经理进行案件分配
 * 
 * @author 华锋
 * Oct 28, 2009-11:27:03 AM
 *
 */
public class NewCaseListAction extends AbstractListAction{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2852856152242928027L;
	private String jiekuanren;
	private String thedate;
	private String contractno;
	private int bankid;
	private String theidcard;
	
	
	/**
	 * @return the jiekuanren
	 */
	public String getJiekuanren() {
		return jiekuanren;
	}
	/**
	 * @param jiekuanren the jiekuanren to set
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
	 * @param thedate the thedate to set
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
	 * @param contractno the contractno to set
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
	 * @param bankid the bankid to set
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
	 * @param theidcard the theidcard to set
	 */
	public void setTheidcard(String theidcard) {
		this.theidcard = theidcard;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		Criteria criteria = getSession().createCriteria(TlawLawcase.class);
//		DetachedCriteria criteria=DetachedCriteria.forClass(TlawLawcase.class);
 
		if((jiekuanren!=null&&!jiekuanren.equals(""))||(theidcard!=null&&!theidcard.equals(""))){
			criteria.createAlias("jiekuanren", "jiekuanren");
			if(jiekuanren!=null&&!jiekuanren.equals(""))
			criteria.add(Restrictions.like("jiekuanren.jiekuanren", jiekuanren,MatchMode.ANYWHERE));
			if(theidcard!=null&&!theidcard.equals(""))
				criteria.add(Restrictions.like("jiekuanren.theidcard", theidcard,MatchMode.ANYWHERE));
		}

	    if(thedate!=null&&!thedate.equals("")){
			criteria.add(Restrictions.eq("thedate", thedate));
		}	
	    if(contractno!=null&&!contractno.equals("")){
			criteria.add(Restrictions.like("contractno", contractno,MatchMode.ANYWHERE));
		}
	    if(bankid>0){
			criteria.add(Restrictions.eq("bankid", bankid));
		}	
//		criteria.add(Restrictions.or(Restrictions.eq("nodeid", 21001),Restrictions.eq("statusid", -1)));
	
		criteria.add(Restrictions.eq("statusid", -1));
		criteria.add(Restrictions.eq("nodeid", 21001));
		
	    criteria.addOrder(Order.desc("createtime"));
		
//		BasicService bs=(BasicService)BeanGlobals.getBean("basicService");
//		this.page=bs.findPageByCriteria(criteria, pageSize, pagenumber);
//		newcaselist =this.page.getItems();
		newcaselist=page(criteria);
		

		return SUCCESS;
	}
	
	private List newcaselist;
	public List getNewcaselist(){
		return this.newcaselist;
	
	}
}