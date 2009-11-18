/**
 * 
 */
package com.sxit.netquality.action;

import java.text.DateFormat;
import java.util.Date;

import com.sxit.common.action.AbstractListAction;
import com.sxit.stat.service.StatService;

/**
 * 
 * gb链路统计,从stat_nsvc拿数据
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class GbLinkStaticAction extends AbstractListAction {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String date;
	private String bscid;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	/**
	 * @return the bscid
	 */
	public String getBscid() {
		return bscid;
	}

	/**
	 * @param bscid
	 *            the bscid to set
	 */
	public void setBscid(String bscid) {
		this.bscid = bscid;
	}

	@Override
	protected String go() throws Exception {
		Date thedate = null;
		if (date == null || date.equals("")) {
			thedate = new Date();
			date = df.format(thedate);
		} else {
			try {
				thedate = df.parse(date);
			} catch (Exception e) {
				thedate = new Date();
				date = df.format(thedate);
			}
		}
		StatService statservice = (StatService) getBean("statService");

		if (resultType.equals("list")) {

			this.page = statservice.getNsvcStat(thedate, bscid, pageNo, pageSize);
			return SUCCESS;
		} else {

			this.page = statservice.getNsvcStat(thedate, bscid, 1, Integer.MAX_VALUE);
			return "excel";
		}
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

}
