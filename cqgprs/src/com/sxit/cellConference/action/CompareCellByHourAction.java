/**
 * 
 */
package com.sxit.cellConference.action;

import java.util.List;

import com.sxit.cellConference.service.CellConferenceService;
import com.sxit.common.action.AbstractListAction;

/**
 * 
 * 这个cellid和这个lac，在监控的小时，连续n天的一个数据情况
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class CompareCellByHourAction extends AbstractListAction {

	private String cellid;
	private String lac;
	
	
	
	/**
	 * @return the lac
	 */
	public String getLac() {
		return lac;
	}

	/**
	 * @param lac the lac to set
	 */
	public void setLac(String lac) {
		this.lac = lac;
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

	private String date;
	private String hour;
	private int days;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		CellConferenceService service = (CellConferenceService) this.getBean("cellConferenceService");

		
		
		comparelist=service.getConitnueTimeStatCells(cellid, lac, date, hour, days);
		
		return SUCCESS;
	}
	
	
	private List comparelist;
	public List getComparelist(){
		return this.comparelist;
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

	/**
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}

	/**
	 * @param hour the hour to set
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}

	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}
}
