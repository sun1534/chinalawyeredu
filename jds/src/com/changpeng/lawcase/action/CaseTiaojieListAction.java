/**
 * CaseTiaojieListAction.java
 */
package com.changpeng.lawcase.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.lawcase.model.TlawLawcase;
import com.sxit.common.action.AbstractListAction;

/**
 * 案件调解列表,条件为当前节点是调解状态的,即nodeid=21006;
 * 默认为当天转入的调解案件
 * 
 * @author 华锋 Jan 10, 201010:22:29 PM
 * 
 */
public class CaseTiaojieListAction extends AbstractListAction {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dff = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String date;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		int nodeid = 21006;
		Date _date = null;
		if (date == null || date.equals(""))
			date = df.format(new java.util.Date());

		
		
		String startstr = date + " 00:00:00";
		String endstr = date + " 23:59:59";

		Timestamp start = null;
		Timestamp end = null;
		try {
			start = new Timestamp(dff.parse(startstr).getTime());
			
			end = new Timestamp(dff.parse(endstr).getTime());
			System.out.println(dff.format(start));
			System.out.println(dff.format(end));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Criteria criteria = getSession().createCriteria(TlawLawcase.class).add(Restrictions.eq("nodeid", nodeid));
		criteria.add(Restrictions.between("modifytime", start, end));
		// 按入库顺序逆序排列
		criteria.addOrder(Order.desc("modifytime"));
		tiaojielist = this.page(criteria);

		return SUCCESS;
	}

	private List tiaojielist;

	public List getTiaojielist() {
		return this.tiaojielist;
	}
}
