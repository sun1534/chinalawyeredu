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

import com.changpeng.lawcase.model.TlawCaselog;
import com.changpeng.lawcase.model.TlawLawcase;
import com.sxit.common.action.AbstractListAction;

/**
 * 
 * 显示这个案件的日志列表
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class CaselogDeleteAction extends AbstractListAction {

	private long check[];
	private long caseid;

	/**
	 * @return the caseid
	 */
	public long getCaseid() {
		return caseid;
	}


	/**
	 * @param caseid the caseid to set
	 */
	public void setCaseid(long caseid) {
		this.caseid = caseid;
	}


	/**
	 * @return the check
	 */
	public long[] getCheck() {
		return check;
	}


	/**
	 * @param check the check to set
	 */
	public void setCheck(long[] check) {
		this.check = check;
	}


	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		if ((check == null || check.length == 0)) {
			this.message = "请选择需要删除的案件日志信息";
			return "message";
		}
	
		int i = 0;
		for (long _caseid : check) {
			i += getSession().createSQLQuery("delete from tlaw_caselog where caselogid=" + _caseid).executeUpdate();
			com.changpeng.lawcase.service.LawcaseLogicService.saveOperlog(getSession(), _caseid, 21001, "删除案件日志", 4);

		}
		this.message = "删除" + i + "个案件的日志信息成功";
	
		this.nextpage="caselogList.action?caseid="+caseid+"&pagenumber="+pagenumber;
		return SUCCESS;
	}
	
}
