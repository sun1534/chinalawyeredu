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
 * 高流量用户排名,从mobile分APN统计表中拿数据
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class HightStreamUserAction extends AbstractAction {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfhour = new java.text.SimpleDateFormat("yyyy-MM-dd HH");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		UseractionService service = (UseractionService) this.getBean("useractionService");
		Date thedate = null;
		if (date == null || date.equals("")) {
			thedate = com.sxit.stat.util.StatUtil.getPrevDate();
			date = df.format(thedate);
		} else {
			try {
				thedate = df.parse(date);
			} catch (Exception e) {
				thedate = com.sxit.stat.util.StatUtil.getPrevDate();
				date = df.format(thedate);
			}

		}
		if (flag.equals("1"))
			top1000users = service.getHightStreamDayUser(thedate, standard, condition);
		else
			top1000users = service.getHightStreamHourUser(standard, condition, thedate, hour);

		if (this.resultType.equals("list"))

			return SUCCESS;
		else
			return "excel";
	}

	/**
	 * 1代表大于多少m
	 * 
	 */
	private String standard = "2";
	/**
	 * 代表条件,这里的standard为2，联合这里，表示取排量靠前的1000个号码
	 */
	private String condition = "1000";

	private List top1000users;

	/**
	 * 1为按天2为按小时
	 */
	private String flag = "1";
	private String date;
	/**
	 * 这里的hour为00到23，不能为00:00
	 */
	private String hour;

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
	 * @return the top1000users
	 */
	public List getTop1000users() {
		return top1000users;
	}

}
