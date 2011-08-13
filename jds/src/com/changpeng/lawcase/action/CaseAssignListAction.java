/**
 * 
 */
package com.changpeng.lawcase.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.lawcase.model.TlawLawcase;
import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.TsysRole;

/**
 * 
 * 需要分配的案件列表，
 * 这里包括分配诉讼承办人以及分配执行承办人
 * 
 * @author 华锋
 * Oct 26, 2009-10:07:19 AM
 *
 */
public class CaseAssignListAction extends AbstractListAction {

	private String jiekuanren;
	private String thedate;
	private String contractno;
	private int bankid;
	private String theidcard;

	  private Set roleusers;
	
	/**
	 * @return the roleusers
	 */
	public Set getRoleusers() {
		return roleusers;
	}
	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub
		
		TsysRole role=(TsysRole)getSession().get(TsysRole.class, 3);
		
    	if(role!=null)
    		roleusers=role.getTsysUserRoles();
    	else
    		roleusers=new HashSet();
    	
    	System.out.println("roleusers:::::::::"+roleusers);
		
		
		Criteria criteria = getSession().createCriteria(TlawLawcase.class);
//		criteria.add(Restrictions.eq("nowworkerid", this.curuser.getUserid()));
 
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
	    if(contractno!=null&&!thedate.equals("")){
			criteria.add(Restrictions.like("contractno", contractno,MatchMode.ANYWHERE));
		}
	    if(bankid>0){
			criteria.add(Restrictions.eq("bankid", bankid));
		}	
	 
//	    criteria.add(Restrictions.or(Restrictions.eq("nowworkerid", 0L), Restrictions.eq("statusid", 0)));
	//未分配的案件列表是nowworkerid=0 and (statusid=0或者statusid=20)
		
	    criteria.add(Restrictions.or(Restrictions.eq("susongworkid", 0L), Restrictions.eq("zhixingworkid", 0L)));

	    
		criteria.addOrder(Order.desc("createtime"));
		
		lawcaselist =page(criteria);

		return SUCCESS;
		
	}


	private List lawcaselist;
	public List getLawcaselist(){
		return this.lawcaselist;
	
	}
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


	
	
}