package com.changpeng.customer.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;

/**
 * 当事人客户列表
 * 
 * @author sinhoo 2010-1-3
 */
public class Customer3ListAction extends AbstractListAction {
	private List customerlist;
	private int customerflag;
	private String username;
	private String company;
	private String idcard;
	private String phone;

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getCustomerflag() {
		return customerflag;
	}

	public String getUsername() {
		return username;
	}

	public void setCustomerflag(int customerflag) {
		this.customerflag = customerflag;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Customer3ListAction() {
		rights = "usr4,1";
	}

	public String go() throws HibernateException {
		customerlist = getQuery().setMaxResults(maxperpage).setFirstResult(maxperpage * pagenumber).setCacheable(true)
				.list();
		return SUCCESS;
	}

	private Query getQuery() throws HibernateException {

		// String sql="select * from ";
		// sql+="(select creditcardid as id,
		// username,idcard,mobileold,homephoneold,workphoneold as
		// companyphone,company,homeaddr, 1 as oprflag from topr_creditcard";
		//                
		// sql+=" union ";
		// sql+="select nonlawid as id,
		// username,idcard,mobileold,homephoneold,companyphone,company,homeaddr,2
		// as oprflag from tnlw_nonlaw)";
		//                
		// sql+=" where 1=1";
		// if(username!=null&&!"".equals(username))
		// sql+=" and username like '%"+username+"%'";
		// if(company!=null&&!"".equals(company))
		// sql+=" and company like '%"+company+"%'";
		// if(idcard!=null&&!"".equals(idcard))
		// sql+=" and idcard like '%"+idcard+"%'";
		// sql+=" order by oprflag";
		// Query query = getSession().createSQLQuery(sql);
		// recordsize = query.list().size();
		// pagesize = (recordsize - 1) / maxperpage + 1;
		// pagenumber= pagenumber>pagesize-1?pagesize-1:pagenumber;
		// return query;

		// String sql="select * from ";
		// sql+="(select creditcardid as id,
		// username,idcard,mobileold,homephoneold,workphoneold as
		// companyphone,company,homeaddr, 1 as oprflag from topr_creditcard";
		//               
		// sql+=" union ";
		// sql+="select nonlawid as id,
		// username,idcard,mobileold,homephoneold,companyphone,company,homeaddr,2
		// as oprflag from tnlw_nonlaw)";

		String sql = "from TusrCustomerNew where 1=1";

		if (username != null && !"".equals(username))
			sql += " and username like '%" + username + "%'";
		if (company != null && !"".equals(company))
			sql += " and company like '%" + company + "%'";
		if (idcard != null && !"".equals(idcard))
			sql += " and idcard like '%" + idcard + "%'";
		if (phone != null && !"".equals(phone)) {
			sql += " and(homephone like '%" + phone + "%' or mobile like '%" + phone + "%' or mobile2 like '%" + phone
					+ "%' or compphone like '%" + phone + "%' or compfax like '%" + phone + "%')";

		}

		sql += " order by customerid";
		System.out.println(sql);
		Query query = getSession().createQuery(sql);
		recordsize = query.list().size();
		pagesize = (recordsize - 1) / maxperpage + 1;
		pagenumber = pagenumber > pagesize - 1 ? pagesize - 1 : pagenumber;
		return query;

	}

	public List getCustomerlist() {
		return customerlist;
	}
}
