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
	private static DateFormat dfyyyyMMdd = new java.text.SimpleDateFormat("yyyyMMdd");

	private static DateFormat dfhour = new java.text.SimpleDateFormat("HH");
	/**
	 * 哪天,默认是前1天
	 */
	private String date;
	/**
	 * 所选择的时间，哪个小时
	 */
	private String hour;
	/**
	 * 1按天 2按时
	 */
	private String flag = "1";

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
			// try {
			// thedate = df.parse(date);
			// } catch (Exception e) {
			// thedate = com.sxit.stat.util.StatUtil.getPrevDate();
			// }
			thedate = com.sxit.stat.util.StatUtil.getDate(date);
		}
		if (hour == null || hour.equals("")) {
			hour = dfhour.format(new Date());
		}

		UseractionService actionservice = (UseractionService) this.getBean("useractionService");
		// int start = 0;
		// int end = 0;

		if (orderfield == null || orderfield.equals("")) {
			orderfield = "errcount";
			ascdesc = "desc";
		}
		String stattime = "";
		if (flag.equals("1")) {
			stattime = dfyyyyMMdd.format(thedate);
			// long _start = com.sxit.stat.util.StatUtil.getDateTime(thedate);
			// start = (int) (_start / 1000);
			// end = (int) (com.sxit.stat.util.StatUtil.getOneDayAfter(_start) /
			// 1000);

		} else {
			stattime = dfyyyyMMdd.format(thedate) + hour;
			//
			// date = df.format(new Date());
			// String _date = date + " " + hour + "00:00";
			// start = UseractionService.getDfSec(_date);
			// end = start + 60 * 60;
		}
		// this.resultList = actionservice.getPdpErrorTopList(start, end,
		// getOrderby());
		this.resultList = actionservice.getPdpErrorTopList(stattime, getOrderby());
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
