/**
 * 
 */
package com.sxit.stat.action;

import java.util.List;

/**
 * CELL分时流量分析
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class StreamCellTimeAction extends StatAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		if(startDate==null){
			startDate=getPrevDate();
			this.end=df.format(startDate);
		}
		this.celltimelist = statservice.getCellDayTimeStat(startDate, cellid);
		return SUCCESS;
	}

	private String cellid;
	private List celltimelist;

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
	 * @return the celltimelist
	 */
	public List getCelltimelist() {
		return celltimelist;
	}

}
