package com.changpeng.report.action;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import com.changpeng.operation.model.ToprCreditcard;
import com.changpeng.report.models.TrptTduser;
import com.sxit.common.action.AbstractListAction;
import java.util.*;
public class LawfeeListAction extends AbstractListAction{
	private String username;
	private String creditcard;
	private String idcard;
	
	private List feelist;
	
	
	public List getFeelist() {
		return feelist;
	}

	public String getUsername() {
		return username;
	}

	

	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Override
	protected String go() throws JDBCException, HibernateException {
		  Criteria criteria = getSession().createCriteria(TrptTduser.class);
  		
  		if (username!= null&&!"".equals(username))
  			criteria.add(Expression.like("username", username,MatchMode.ANYWHERE));
  		if (creditcard!= null&&!"".equals(creditcard))
      		criteria.add(Expression.like("creditcard", creditcard,MatchMode.ANYWHERE));
  		if (idcard!= null&&!"".equals(idcard))
      		criteria.add(Expression.like("idcard", idcard,MatchMode.ANYWHERE));
  		
  		feelist = page(criteria);
  		return SUCCESS;
	}

}
