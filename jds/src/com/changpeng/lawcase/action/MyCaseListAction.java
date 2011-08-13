/**
 * 
 */
package com.changpeng.lawcase.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
 * 我承办的案件 根据一些什么来查呢？ 借款人姓名、受理时间、受理案号、银行、身份证号码等
 * 
 * 
 * 
 * @author 华锋 Oct 26, 2009-10:07:19 AM
 * 
 */
public class MyCaseListAction extends AbstractListAction {

	private String jiekuanren;
	private String thedate;
	private String contractno;
	private int bankid;
	private String theidcard;
	/**
	 * 案件的状态,新增案件?存量案件?已结案件?默认是新增案件
	 */
	private String status = "";

	/**
	 * 1是诉讼承办人2是执行承办人
	 */
	private int thetype = 1;

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
	 * @param mycaselist
	 *            the mycaselist to set
	 */
	public void setMycaselist(List mycaselist) {
		this.mycaselist = mycaselist;
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

		// criteria.add(Restrictions.eq("nowworkerid",
		// this.curuser.getUserid()));
		
		if (thetype == 1){ //诉讼承办人
			criteria.add(Restrictions.eq("susongworkid", this.curuser.getUserid()));
			statuslist.put(1, "入口案件");
			statuslist.put(10, "存量案件");
			statuslist.put(21, "结案中案件");
			statuslist.put(40, "出口案件");
			
		}else{ //执行承办人
			criteria.add(Restrictions.eq("zhixingworkid", this.curuser.getUserid()));
		}
		if ((jiekuanren != null && !"".equals(jiekuanren)) || (theidcard != null && !"".equals(theidcard))) {
			criteria.createAlias("jiekuanren", "jiekuanren");
			if (jiekuanren != null && !"".equals(jiekuanren))
				criteria.add(Restrictions.like("jiekuanren.jiekuanren", jiekuanren, MatchMode.ANYWHERE));
			if (theidcard != null && !"".equals(theidcard))
				criteria.add(Restrictions.like("jiekuanren.theidcard", theidcard, MatchMode.ANYWHERE));
		}

		if (thedate != null && !"".equals(thedate)) {
			criteria.add(Restrictions.eq("thedate", thedate));
		}
		if (contractno != null && !"".equals(contractno)) {
			criteria.add(Restrictions.like("contractno", contractno, MatchMode.ANYWHERE));
		}
		if (bankid > 0) {
			criteria.add(Restrictions.eq("bankid", bankid));
		}
		// if(statusid!=4){
		// criteria.add(Restrictions.eq("statusid", statusid));
		// }else{
		// criteria.add(Restrictions.in("statusid", new Object[]{4,5}));
		// //包括阶段已结和完全已结
		// }

		criteria.addOrder(Order.desc("createtime"));

		mycaselist = page(criteria);

		return SUCCESS;
	}

	private List mycaselist;

	public List getMycaselist() {
		return this.mycaselist;

	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the thetype
	 */
	public int getThetype() {
		return thetype;
	}

	/**
	 * @param thetype
	 *            the thetype to set
	 */
	public void setThetype(int thetype) {
		this.thetype = thetype;
	}
	
	private Map<Integer,String> statuslist=new LinkedHashMap<Integer,String>();
	public Map getStatuslist(){
		return this.statuslist;
	}

}