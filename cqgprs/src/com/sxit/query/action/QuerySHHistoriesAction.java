/**
 * 
 */
package com.sxit.query.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.PaginationSupport;
import com.sxit.common.action.AbstractListAction;
import com.sxit.query.service.SHQueryService;

/**
 * 
 * 根据手机号码和时间查询执行的历史记录
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class QuerySHHistoriesAction extends AbstractListAction {
	private static Log _LOG = LogFactory.getLog(QuerySHHistoriesAction.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String mobile;
	private String start;
	private String end;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		SHQueryService service = (SHQueryService) getBean("shQueryService");

		Timestamp from = null;
		Timestamp to = null;
		String result = "";
		if (current != null && current.equals("current")) {
			pageSize = Integer.MAX_VALUE;
			from = new java.sql.Timestamp(df.parse(df.format(new Date())).getTime());
			to = new java.sql.Timestamp(df.parse(df.format(new Date())).getTime() + 24 * 60 * 60 * 1000);
			result = "current";
		} else {
			if (start != null && !start.equals("")) {
				from = new java.sql.Timestamp(df.parse(start).getTime());
			}
			if (end != null && !end.equals("")) {
				to = new java.sql.Timestamp(df.parse(end).getTime());
			}
			result = SUCCESS;
		}
		if (mobile != null && !mobile.equals(""))
			this.page = service.getQueryLogsByMsisdn(mobile, from, to, pageNo, pageSize);
		else
			this.page = new PaginationSupport(new ArrayList(), 0, pageSize, 0);
		
		System.out.println("page.getItems().size():::"+page.getItems().size());
		
		// 显示手机号码、执行结果、执行时间、执行人。提供下载执行命令的结果
		return result;
	}

	private String current;

	public void setCurrent(String c) {
		this.current = c;
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
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}
}