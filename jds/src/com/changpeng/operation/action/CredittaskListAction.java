package com.changpeng.operation.action;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.changpeng.operation.model.ToprCreditcard;
import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.TsysRole;

/**
 * 列表待催收记录并选择记录进行任务分配
 * 
 * @author sinhoo Jun 11, 2009
 */
public class CredittaskListAction extends AbstractListAction {
	private List creditcardlist;
	private long bankid;
	private String bianhao;
	private String consigntype;
	private String consignflag;
	private String username;
	private String creditcard;
	private Set roleusers;
	private String consigndate; // 委托日期
	private int state;
	private String chengbanren;

	private List userlist; // 催收员

	public int getState() {
		return state;
	}

	public String getChengbanren() {
		return chengbanren;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setChengbanren(String chengbanren) {
		this.chengbanren = chengbanren;
	}

	public Set getRoleusers() {
		return roleusers;
	}

	public CredittaskListAction() {
		this.maxperpage = 50;
		rights = "opr3,1";
	}

	public String go() throws HibernateException {
		// 信用卡催收角色
		/*
		 * TsysRole role=(TsysRole)getSession().get(TsysRole.class, 1);
		 * if(role!=null) roleusers=role.getTsysUserRoles();
		 */

		userlist = getSession()
				.createQuery(
						"select a from TsysUser a,TsysUserRole b where a.userid=b.comp_id.tsysUser.userid and b.comp_id.tsysRole.roleid=1 and a.statusid=1 order by a.username asc")
				.list();

		// userlist=getSession().createQuery(" from TsysUser where statusid=1
		// order by userid").list();
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
		 * creditcard,MatchMode.ANYWHERE)); criteria.add(Expression.eq("state",
		 * 0)); // creditcardlist =
		 * criteria.setMaxResults(maxperpage).setFirstResult(maxperpage *
		 * pagenumber).setCacheable(true).list();
		 * 
		 *  // criteria.addOrder(Order.desc("to_number(curcnfee)"));
		 * 
		 * creditcardlist=page(criteria);//criteria.list();
		 */
		creditcardlist = getQuery().setMaxResults(maxperpage).setFirstResult(maxperpage * pagenumber)
				.setCacheable(true).list();
		return SUCCESS;
	}

	private String selected;
	private int[] check;
	
	public String export() throws HibernateException {
		
		if (selected != null && selected.equals("selected")) {
			if(check==null||check.length==0){
				this.message="您没有选择任何需要导出的记录,请选择";
				this.nextpage="javascript:history.go(-1)";
				
				return ERROR;
				
			}else{
				String s="";
				for(int ss:check){
					s+=ss+",";
				}
				s+="0";	
				
				String queryName = "from ToprCreditcard a where a.creditcardid in("+s+")";
				creditcardlist =getSession().createQuery(queryName).list();				
			}
			
		} else {
			creditcardlist = getQuery().list();
		}
		
//		creditcardlist = getQuery().list();
		return "export";
	}

	private Query getQuery() throws HibernateException {
		String queryName = "from ToprCreditcard  a where 1=1  and a.repaystatus<>2";
		if (chengbanren != null && !"".equals(chengbanren)) {
			queryName = "from ToprCreditcard a,ToprCredittask b,TsysUser c where  a.repaystatus<>2 and a.creditcardid=b.toprCreditcard.creditcardid and b.userid=c.userid and c.username like '"
					+ chengbanren.trim() + "%'  and b.taskstat=0";

		}
		if (bankid != 0)
			queryName += " and a.bankid=" + bankid;
		if (bianhao != null && !"".equals(bianhao))
			queryName += " and a.bianhao='" + bianhao + "'";
		if (consigntype != null && !"".equals(consigntype))
			queryName += " and a.consigntype='" + consigntype + "'";
		if (consignflag != null && !"".equals(consignflag))
			queryName += " and a.consignflag='" + consignflag + "'";
		if (username != null && !"".equals(username))
			queryName += " and a.username like '%" + username + "%'";
		if (creditcard != null && !"".equals(creditcard))
			queryName += " and a.creditcard like '%" + creditcard + "%'";
		if (consigndate != null && !"".equals(consigndate))
			queryName += " and a.consigndate='" + consigndate + "'";
		if (state != -1)
			queryName += " and a.state=" + state;

		// 未退单的
		queryName += " and a.tdflag=0";
		basicDao.setSession(getSession());
		recordsize = basicDao.getCountOfQuery(queryName);

		queryName = "select a " + queryName + " order by to_number(a.curcnfee) desc,a.creditcardid desc";

		Query query = getSession().createQuery(queryName);

		pagesize = (recordsize - 1) / maxperpage + 1;
		pagenumber = pagenumber > pagesize - 1 ? pagesize - 1 : pagenumber;
		return query;
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

	public String getConsigndate() {
		return consigndate;
	}

	public void setConsigndate(String consigndate) {
		this.consigndate = consigndate;
	}

	public List getUserlist() {
		return userlist;
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

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}

	/**
	 * @param check the check to set
	 */
	public void setCheck(int[] check) {
		this.check = check;
	}

}
