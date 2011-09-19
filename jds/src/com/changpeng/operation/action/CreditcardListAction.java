package com.changpeng.operation.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import com.changpeng.operation.model.ToprCreditcard;
import com.sxit.common.action.AbstractListAction;

/**
 * 
 * <p>
 * 功能： 列表催收记录
 * </p>
 * <p>
 * 作者： 刘兴华
 * </p>
 * <p>
 * 公司： 长鹏软件
 * </p>
 * <p>
 * 日期： 2009-06-09
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class CreditcardListAction extends AbstractListAction {
	private List creditcardlist;
	private long bankid;
	private String bianhao;
	private String consigntype;
	private String consignflag;
	private String username;
	private String creditcard;
	private int state = -1;
	private String consigndate; // 委托日期
	private String chengbanren; // 承办人
	private int lawflag; // 3:预警记录

	public String getConsigndate() {
		return consigndate;
	}

	public String getChengbanren() {
		return chengbanren;
	}

	public void setConsigndate(String consigndate) {
		this.consigndate = consigndate;
	}

	public void setChengbanren(String chengbanren) {
		this.chengbanren = chengbanren;
	}

	public CreditcardListAction() {
//		rights = "opr2,1";
	}

	public String go() throws HibernateException {
		
		System.out.println("===recordsize========》》》》》"+recordsize);
		
		
		creditcardlist = getQuery().setMaxResults(maxperpage).setFirstResult(maxperpage * pagenumber)
		// .setCacheable(true)
				.list();

		/*
		 * Criteria criteria =
		 * getSession().createCriteria(ToprCreditcard.class); if (bankid != 0)
		 * criteria.add(Expression.eq("bankid", bankid)); if (consigntype!=
		 * null&&!"".equals(consigntype))
		 * criteria.add(Expression.eq("consigntype", consigntype)); if
		 * (consignflag!= null&&!"".equals(consignflag))
		 * criteria.add(Expression.eq("consigntype", consignflag)); if
		 * (username!= null&&!"".equals(username))
		 * criteria.add(Expression.like("username",
		 * username,MatchMode.ANYWHERE)); if (creditcard!=
		 * null&&!"".equals(creditcard))
		 * criteria.add(Expression.like("creditcard",
		 * creditcard,MatchMode.ANYWHERE)); if (state != -1)
		 * criteria.add(Expression.eq("state", state)); creditcardlist =
		 * page(criteria);
		 */

		return SUCCESS;
	}

	public String export() throws HibernateException {
		/*
		 * creditcardlist = getQuery() .setMaxResults(maxperpage)
		 * .setFirstResult(maxperpage * pagenumber) .setCacheable(true) .list();
		 */

		/*
		 * Criteria criteria =
		 * getSession().createCriteria(ToprCreditcard.class); if (bankid != 0)
		 * criteria.add(Expression.eq("bankid", bankid)); if (consigntype!=
		 * null&&!"".equals(consigntype))
		 * criteria.add(Expression.eq("consigntype", consigntype)); if
		 * (consignflag!= null&&!"".equals(consignflag))
		 * criteria.add(Expression.eq("consigntype", consignflag)); if
		 * (username!= null&&!"".equals(username))
		 * criteria.add(Expression.like("username",
		 * username,MatchMode.ANYWHERE)); if (creditcard!=
		 * null&&!"".equals(creditcard))
		 * criteria.add(Expression.like("creditcard",
		 * creditcard,MatchMode.ANYWHERE)); if (state != -1)
		 * criteria.add(Expression.eq("state", state)); creditcardlist =
		 * criteria.list();
		 */
		creditcardlist = getQuery().list();
		return "export";
	}

	private Query getQuery() throws HibernateException {

		if (ext != null && ext.equals("ext")) {
			getSession().createSQLQuery("update topr_creditcard set repaystatus = null where repaystatus =0").executeUpdate();
			getSession().createSQLQuery("update tnlw_nonlaw set overnum=null where overnum=0").executeUpdate();
		} else {
			getSession().createSQLQuery("update topr_creditcard set repaystatus=0 where repaystatus is null").executeUpdate();
			getSession().createSQLQuery("update tnlw_nonlaw set overnum=0 where overnum is null").executeUpdate();

		}

		String queryName = "from ToprCreditcard  a where 1=1   and a.repaystatus<>2";
		
		if (chengbanren != null && !"".equals(chengbanren)) {
			queryName = "from ToprCreditcard a,ToprCredittask b,TsysUser c where a.repaystatus<>2 and a.creditcardid=b.toprCreditcard.creditcardid and b.userid=c.userid and c.username like '%"
					+ chengbanren.trim() + "%'  and b.taskstat=0";

		}
		if (lawflag == 3)
			queryName += " and a.lawflag=" + lawflag;
		if (bankid != 0)
			queryName += " and a.bankid=" + bankid;
		if (bianhao != null && !"".equals(bianhao))
			queryName += " and a.bianhao='" + bianhao + "'";
		if (consigntype != null && !"".equals(consigntype))
			queryName += " and a.consigntype='" + consigntype + "'";
		if (consignflag != null && !"".equals(consignflag))
			queryName += " and a.consignflag='" + consignflag + "'";
		if (consigndate != null && !"".equals(consigndate))
			queryName += " and a.consigndate='" + consigndate + "'";
		if (username != null && !"".equals(username))
			queryName += " and a.username like '%" + username + "%'";
		if (creditcard != null && !"".equals(creditcard))
			queryName += " and a.creditcard like '%" + creditcard + "%'";
		if (state != -1)
			queryName += " and a.state=" + state;

		// 未退单的
		queryName += " and a.tdflag=0";

		// queryName+=" order by to_number(a.curcnfee) desc,a.creditcardid
		// desc";
		
		 basicDao.setSession(getSession());
		 recordsize=basicDao.getCountOfQuery(queryName);
		
		queryName += " order by consigndate desc,idcard asc";
		
		
		queryName="select a "+queryName;
		Query query = getSession().createQuery(queryName);
//		recordsize = query.list().size();
		
		
		pagesize = (recordsize - 1) / maxperpage + 1;
		pagenumber = pagenumber > pagesize - 1 ? pagesize - 1 : pagenumber;

		
		
		
		return query;
	}

	private String ext;

	/**
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * @param ext
	 *            the ext to set
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

	public List getCreditcardlist() {
		return creditcardlist;
	}

	public long getBankid() {
		return bankid;
	}

	public String getConsigntype() {
		return consigntype;
	}

	public String getConsignflag() {
		return consignflag;
	}

	public String getUsername() {
		return username;
	}

	public String getCreditcard() {
		return creditcard;
	}

	public void setBankid(long bankid) {
		this.bankid = bankid;
	}

	public void setConsigntype(String consigntype) {
		this.consigntype = consigntype;
	}

	public void setConsignflag(String consignflag) {
		this.consignflag = consignflag;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getLawflag() {
		return lawflag;
	}

	public void setLawflag(int lawflag) {
		this.lawflag = lawflag;
	}

	/**
	 * @return the bianhao
	 */
	public String getBianhao() {
		return bianhao;
	}

	/**
	 * @param bianhao
	 *            the bianhao to set
	 */
	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}
}
