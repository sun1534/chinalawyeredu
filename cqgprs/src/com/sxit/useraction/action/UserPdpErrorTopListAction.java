/**
 * 
 */
package com.sxit.useraction.action;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.sxit.common.action.AbstractAction;
import com.sxit.useraction.service.UseractionService;

/**
 * 
 * pdp异常用户排名 表3、PDP异常用户排名（按失败次数取前1000个用户），其实主要是33号错误的
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class UserPdpErrorTopListAction extends AbstractAction {

	private static DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private String date;
	private String hour;
	private String flag;

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
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}

	/**
	 * @param hour
	 *            the hour to set
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		Date thedate = null;
		if (date == null || date.equals("")) {
			thedate = com.sxit.stat.util.StatUtil.getPrevDate();
			date = df.format(thedate);

		} else {
			try {
				thedate = df.parse(date);
			} catch (Exception e) {
				thedate = com.sxit.stat.util.StatUtil.getPrevDate();
			}
		}

		UseractionService actionservice = (UseractionService) this.getBean("useractionService");

		this.resultList = actionservice.getPdpErrorTopList(thedate);
		if (this.resultType.equals("list"))
			return SUCCESS;
		else
			return "excel";
	}

	private List resultList;

	public List getResultList() {
		return this.resultList;
	}
}
