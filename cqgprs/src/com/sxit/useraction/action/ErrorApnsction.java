/**
 * 
 */
package com.sxit.useraction.action;

import java.text.DateFormat;
import java.util.List;

import com.sxit.common.action.AbstractAction;
import com.sxit.useraction.service.UseractionService;

/**
 * 
 * 错误apn分布,从stat_apn_error表里拿数据
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class ErrorApnsction extends AbstractAction {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private String date;
	private String orderby; // 排序依据

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

	/**
	 * @return the orderby
	 */
	public String getOrderby() {
		return orderby;
	}

	/**
	 * @param orderby
	 *            the orderby to set
	 */
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		if (date == null || date.equals("")) {
			date = df.format(com.sxit.stat.util.StatUtil.getPrevDate());
		}
		UseractionService service = (UseractionService) this.getBean("useractionService");
		apnerrorslist = service.getApnErrors(date, null, null);
		if (resultType.equals("list"))
			return SUCCESS;
		else
			return "excel";
	}

	private List apnerrorslist;

	public List getApnerrorslist() {
		return this.apnerrorslist;
	}
}
