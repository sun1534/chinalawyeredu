/**
 * 
 */
package com.sxit.netquality.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sxit.common.PaginationSupport;
import com.sxit.common.action.AbstractListAction;
import com.sxit.netquality.service.BasicSetService;
import com.sxit.netquality.service.ZeroService;

/**
 * 
 * SGSN流量分析
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class ZeroStreamCellAction extends AbstractListAction {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfhour = new java.text.SimpleDateFormat("HH");

	private String firstpage= "yes";
	private String starthour;
	private String endhour;
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
		Date thedate = com.sxit.stat.util.StatUtil.getDate(date);

		if(orderfield==null||orderfield.equals(""))
			orderfield="cellid";
		
		if (resultType != null && resultType.equals("excel")) {
			this.pageNo = 1;
			this.pageSize = Integer.MAX_VALUE;
		}

		
		
		
		ZeroService zeroservice = (ZeroService) this.getBean("zeroService");
		if (dayflag.equals("1")) {
			this.page = zeroservice.getDayZeroCells(thedate,getOrderby(), pageNo, pageSize);
		} else {
			
			if (starthour == null || starthour.equals("")) {
				starthour = "01";

			}

			if (endhour == null || endhour.equals("")) {
				Calendar c = Calendar.getInstance();
				int now = c.get(Calendar.HOUR_OF_DAY);
				if (now == 0 || now == 1)
					endhour = "01";
				else {
				
					Date d = new Date();
					long _now=d.getTime();
					d.setTime(_now-60*60*1000);
					endhour = dfhour.format(d);
				}
			}
			int start_ = Integer.parseInt(starthour);
			int end_ = Integer.parseInt(endhour);
			
			
//			this.page = zeroservice.getHourZeroCells(date, hour,getOrderby(),pageNo, pageSize);
			List resultList = null;
			if (firstpage.equals("yes")) {
				resultList = zeroservice.getHourZeroCells(date, getOrderby(), start_, end_);
				set("zerocelllist", resultList);
			} else {
				resultList = (List) get("zerocelllist");
			}
			int totalCount = resultList.size();
			int startIndex = (pageNo - 1) * pageSize;
			List list = new ArrayList();

			for (int i = startIndex; i < totalCount && i < startIndex + pageSize; i++) {
				list.add(resultList.get(i));
			}
			this.page = new PaginationSupport(list, totalCount, pageSize, startIndex);
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

	/**
	 * @return the firstpage
	 */
	public String getFirstpage() {
		return firstpage;
	}

	/**
	 * @param firstpage the firstpage to set
	 */
	public void setFirstpage(String firstpage) {
		this.firstpage = firstpage;
	}

	/**
	 * @return the starthour
	 */
	public String getStarthour() {
		return starthour;
	}

	/**
	 * @param starthour the starthour to set
	 */
	public void setStarthour(String starthour) {
		this.starthour = starthour;
	}

	/**
	 * @return the endhour
	 */
	public String getEndhour() {
		return endhour;
	}

	/**
	 * @param endhour the endhour to set
	 */
	public void setEndhour(String endhour) {
		this.endhour = endhour;
	}


}
