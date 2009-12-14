/**
 * 
 */
package com.sxit.tradeCustomer.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sxit.common.PaginationSupport;
import com.sxit.common.action.AbstractListAction;
import com.sxit.useraction.service.UseractionService;

/**
 * 
 * 高流量行业用户号码（只对*.CQ的做） 高流量的含义是该号码的流量是这个APN的平均流量的2倍以上（可更改）
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class HightStreamCustomerUserAction extends AbstractListAction {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfhour = new java.text.SimpleDateFormat("HH");

	private String firstpage = "yes";
	private String apnni;

	/**
	 * @return the apnni
	 */
	public String getApnni() {
		return apnni;
	}

	/**
	 * @param apnni
	 *            the apnni to set
	 */
	public void setApnni(String apnni) {
		this.apnni = apnni;
	}

	/**
	 * @return the firstpage
	 */
	public String getFirstpage() {
		return firstpage;
	}

	/**
	 * @param firstpage
	 *            the firstpage to set
	 */
	public void setFirstpage(String firstpage) {
		this.firstpage = firstpage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		this.start = df.format(new java.util.Date());
		Date enddate = this.getPrevCountDate(7);
		this.end = df.format(enddate);

		UseractionService service = (UseractionService) this.getBean("useractionService");
		Date thedate = null;
		if (date == null || date.equals("")) {
			thedate = new Date();
			date = df.format(thedate);
		} else {
			thedate = com.sxit.stat.util.StatUtil.getDate(date);
		}

		if (orderfield == null || orderfield.equals("")) {
			orderfield = "allvolume";
			ascdesc = "desc";
		}

		if (hour == null || hour.equals("")) {
			Calendar c = Calendar.getInstance();
			int now = c.get(Calendar.HOUR_OF_DAY);
			if (now == 0 || now == 1)
				hour = "01";
			else {
				Date d = new Date();
				long _now = d.getTime();
				d.setTime(_now - 60 * 60 * 1000);
				hour = dfhour.format(d);
			}
		}

		String result = SUCCESS;

		List resultList = null;


		if (firstpage.equals("yes")) {
			if (flag.equals("1")) {
				String stattime = date.replace("-", "");
				float avg = service.getApnDayAverageVolume(apnni, stattime);
				condition = com.sxit.system.util.NumberUtil.toMoney(avg * Float.parseFloat(standard));
				resultList = service.getHightStreamDayCustomerUser(apnni, thedate, condition, getOrderby());
			} else {
				String start = date + " " + hour + ":00:00";
				long _start = com.sxit.stat.util.StatUtil.getDateHourTime(start) / 1000;
				long _end = com.sxit.stat.util.StatUtil.getHourAfterTime((int) _start);
				float avg = service.getApnHourAverageVolume(apnni, _start, _end);
				condition = com.sxit.system.util.NumberUtil.toMoney(avg * Float.parseFloat(standard));
				resultList = service.getHightStreamHourCustomerUser(apnni, thedate, hour, condition, getOrderby());
			}
			set("hightcustomeruser", resultList);
			set("condition", condition);
		} else {
			resultList = (List) get("hightcustomeruser");
			condition = get("condition").toString();
		}
		if (resultType.equals("list")) {
			int totalCount = resultList.size();
			int startIndex = (pageNo - 1) * pageSize;
			List list = new ArrayList();

			for (int i = startIndex; i < totalCount && i < startIndex + pageSize; i++) {
				list.add(resultList.get(i));
			}
			this.page = new PaginationSupport(list, totalCount, pageSize, startIndex);

			top1000users = list;

			result = SUCCESS;
		} else {
			top1000users = resultList;

			result = "excel";
		}
		return result;

	}

	private String condition;
	private String start;
	private String end;

	/**
	 * 1代表大于多少m
	 * 
	 */
	private String standard = "2";

	private List top1000users;

	/**
	 * 1为按天2为按小时
	 */
	private String flag = "2";
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

	/**
	 * @return the standard
	 */
	public String getStandard() {
		return standard;
	}

	/**
	 * @param standard
	 *            the standard to set
	 */
	public void setStandard(String standard) {
		this.standard = standard;
	}

	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition
	 *            the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
}