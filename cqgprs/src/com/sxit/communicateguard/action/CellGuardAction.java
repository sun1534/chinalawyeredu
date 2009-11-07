/**
 * 
 */
package com.sxit.communicateguard.action;

import java.text.DateFormat;
import java.util.Date;

import com.sxit.common.action.AbstractAction;
import com.sxit.common.action.AbstractListAction;
import com.sxit.query.service.QueryService;

/**
 * 
 * SGSN流量分析
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class CellGuardAction extends AbstractListAction {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String start;
	private String end;
	private String date;
	private String cellid;

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
	 * @return the cellid
	 */
	public String getCellid() {
		return cellid;
	}

	/**
	 * @param cellid
	 *            the cellid to set
	 */
	public void setCellid(String cellid) {
		this.cellid = cellid;
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
		if (cellid != null && !cellid.equals("")) {
			if (resultType.equals("list")) {
				this.page = queryservice.queryCdr(_date, null, cellid, null, pageNo, pageSize);
				return SUCCESS;
			} else if (resultType.equals("excel")) {
				this.page = queryservice.queryCdr(_date, null, cellid, null, pageNo, Integer.MAX_VALUE);
				return "excel";
			}
		}
		return SUCCESS;
	}

}
