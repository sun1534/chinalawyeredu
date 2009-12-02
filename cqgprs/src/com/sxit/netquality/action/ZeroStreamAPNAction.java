/**
 * 
 */
package com.sxit.netquality.action;

import java.text.DateFormat;
import java.util.Date;

import com.sxit.common.action.AbstractListAction;
import com.sxit.netquality.service.BasicSetService;
import com.sxit.netquality.service.ZeroService;

/**
 * 
 * SGSN流量分析
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class ZeroStreamAPNAction extends AbstractListAction {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		BasicSetService setservice = (BasicSetService) this.getBean("basicSetService");
		setservice.getAllSets();
		if (date == null || date.equals("")) {
			date = df.format(com.sxit.stat.util.StatUtil.getPrevDate());
		}
		Date thedate = null;
//		try {
//			thedate = df.parse(date);
//		} catch (Exception e) {
//			e.printStackTrace();
//			thedate = com.sxit.stat.util.StatUtil.getPrevDate();
//		}
		thedate= com.sxit.stat.util.StatUtil.getDate(date);

//		if(orderfield==null||orderfield.equals(""))
//			orderfield="apnni";
		
		if (resultType != null && resultType.equals("excel")) {
			this.pageNo = 1;
			this.pageSize = Integer.MAX_VALUE;
		}

		ZeroService zeroservice = (ZeroService) this.getBean("zeroService");
		if (dayflag.equals("1")) {
			this.page = zeroservice.getDayZeroApns(thedate, getOrderby(),pageNo, pageSize);
		} else {
			this.page = zeroservice.getHourZeroApns(date, hour,getOrderby(), pageNo, pageSize);
		}

		if (resultType != null && resultType.equals("excel")) {
			return "excel";
		}
		return SUCCESS;
	}

	private String resultType = "list";

	/**
	 * 某天的还是某个时间段的
	 */
	private String dayflag = "1";

	private String date;
	private String hour;

	/**
	 * @return the dayflag
	 */
	public String getDayflag() {
		return dayflag;
	}

	/**
	 * @param dayflag
	 *            the dayflag to set
	 */
	public void setDayflag(String dayflag) {
		this.dayflag = dayflag;
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
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * @param resultType
	 *            the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

}
