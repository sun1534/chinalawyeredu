/**
 * 
 */
package com.sxit.useraction.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sxit.common.PaginationSupport;
import com.sxit.common.action.AbstractListAction;
import com.sxit.useraction.service.UseractionService;

/**
 * 
 * 高流量用户排名,从mobile分APN统计表中拿数据
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class HightStreamUserAction extends AbstractListAction {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfhour = new java.text.SimpleDateFormat("yyyy-MM-dd HH");

	private String firstpage = "yes";

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
			thedate = com.sxit.stat.util.StatUtil.getPrevDate();
			date = df.format(thedate);
		} else {
			// try {
			// thedate = df.parse(date);
			// } catch (Exception e) {
			// thedate = com.sxit.stat.util.StatUtil.getPrevDate();
			// date = df.format(thedate);
			// }
			thedate = com.sxit.stat.util.StatUtil.getDate(date);
		}

		if (orderfield == null || orderfield.equals("")) {
			orderfield = "allvolume";
			ascdesc = "desc";
		}

		// if (flag.equals("1"))
		// top1000users = service.getHightStreamDayUser(thedate, standard,
		// condition,getOrderby());
		// else
		// top1000users = service.getHightStreamHourUser(thedate, hour,standard,
		// condition, getOrderby());
		//
		// if (this.resultType.equals("list"))
		//
		// return SUCCESS;
		// else
		// return "excel";

		String result = SUCCESS;

		List resultList = null;

		if (firstpage.equals("yes")) {
			if (flag.equals("1"))
				resultList = service.getHightStreamDayUser(thedate, standard, condition, getOrderby());
			else
				resultList = service.getHightStreamHourUser(thedate, hour, standard, condition, getOrderby());
			set("hightuser", resultList);
		} else {
			resultList = (List) get("hightuser");
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

	private String start;
	private String end;

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
}