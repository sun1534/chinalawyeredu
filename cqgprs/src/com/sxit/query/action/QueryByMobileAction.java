/**
 * 
 */
package com.sxit.query.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sxit.common.PaginationSupport;
import com.sxit.common.action.AbstractListAction;
import com.sxit.query.service.QueryService;

/**
 * 
 * SGSN流量分析
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class QueryByMobileAction extends AbstractListAction {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String date;
	private String mobile;

	private String firstpage = "yes";

	private String start;
	private String end;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		if (this.date == null || this.date.equals("")) {
			this.date = df.format(new java.util.Date());
		}

		QueryService queryservice = (QueryService) this.getBean("queryService");
		this.start = df.format(new java.util.Date());
		Date enddate = this.getPrevCountDate(7);
		this.end = df.format(enddate);
		Date _date = df.parse(date);

		if (mobile != null && !mobile.equals("")) {
		
			if (firstpage.equals("yes")) {
				queryList = queryservice.queryCdr(_date, mobile, null, null, null);
				set("queryList", queryList);

			} else {
				queryList = (List) get("queryList");
			}
			
			int totalCount = queryList.size();
			int startIndex = (pageNo - 1) * pageSize;
			List list = new ArrayList();
			for (int i = startIndex; i < totalCount && i < startIndex + pageSize; i++) {
				list.add(queryList.get(i));
			}
			this.page = new PaginationSupport(list, totalCount, pageSize, startIndex);
		}
		if (resultType.equals("list"))
			return SUCCESS;
		return "excel";
	}

	private List queryList;
	public List getQueryList(){
		return this.queryList;
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
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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

}
