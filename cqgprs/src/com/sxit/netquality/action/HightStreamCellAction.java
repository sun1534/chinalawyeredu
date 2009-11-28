/**
 * 
 */
package com.sxit.netquality.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sxit.common.PaginationSupport;
import com.sxit.common.action.AbstractListAction;
import com.sxit.netquality.service.HighStreamService;

/**
 * 
 * 高流量小区 从stat_cell里拿流量排名前100的小区
 * 
 * 最好是做成可配置的,高流量小区，排名前多少位的或流量大于多少滴
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class HightStreamCellAction extends AbstractListAction {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private String date; // 日期
	private String hour; // 时间点
	private String resultType = "list";
	private String standard = "2"; // 按排名
	// 是按流量大小1排名2流量
	private String condition = "100"; // 超过多少的处理,需要前面的1000个

	private String flag = "1"; // 1按天统计2按小时

	private String bscid;
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

	private String isinit;

	/**
	 * @return the isinit
	 */
	public String getIsinit() {
		return isinit;
	}

	/**
	 * @param isinit
	 *            the isinit to set
	 */
	public void setIsinit(String isinit) {
		this.isinit = isinit;
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
			thedate = com.sxit.stat.util.StatUtil.getDate(date);
		}

		if (orderfield == null || orderfield.equals("")) {
			orderfield = "allvolume";
			ascdesc = "desc";
		}
		HighStreamService hightService = (HighStreamService) this.getBean("highStreamService");
		String result = SUCCESS;

		List resultList = null;

		if (firstpage.equals("yes")) {
			if (flag.equals("1"))
				resultList = hightService.getHightStreamDayCell(thedate, standard, condition, bscid, getOrderby());
			else
				resultList = hightService.getHightStreamHourCell(date, hour, bscid, standard, condition, getOrderby());
			set("resultList", resultList);
		} else {
			resultList = (List) get("resultList");
		}
		if (resultType.equals("list")) {
			int totalCount = resultList.size();
			int startIndex = (pageNo - 1) * pageSize;
			List list = new ArrayList();

			for (int i = startIndex; i < totalCount && i < startIndex + pageSize; i++) {
				list.add(resultList.get(i));
			}
			this.page = new PaginationSupport(list, totalCount, pageSize, startIndex);

			topcelllist = list;

			result = SUCCESS;
		} else {
			topcelllist = resultList;

			result = "excel";
		}
		return result;

	}

	private List topcelllist;

	public List getTopcelllist() {
		return this.topcelllist;
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

}
