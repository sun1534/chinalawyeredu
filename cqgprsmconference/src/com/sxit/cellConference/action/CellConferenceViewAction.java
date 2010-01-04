/**
 * 
 */
package com.sxit.cellConference.action;

import java.text.DateFormat;
import java.util.Date;

import com.sxit.cellConference.service.CellConferenceService;
import com.sxit.common.action.AbstractListAction;
import com.sxit.netquality.service.BasicSetService;

/**
 * @author 华锋 Nov 20, 2009-10:09:40 PM
 * 
 */
public class CellConferenceViewAction extends AbstractListAction {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	
	private String lac;
	private String cellid;
	
	private String date;
private String predate;
private String oldpredate;
private String today;

	/**
 * @return the today
 */
public String getToday() {
	return today;
}

/**
 * @param today the today to set
 */
public void setToday(String today) {
	this.today = today;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
//		Date thedate=null;
		if (date == null || date.equals("")) {
			date = df.format(new Date());
		}
		
		BasicSetService setservice = (BasicSetService) this.getBean("basicSetService");
		setservice.getAllSets();
		
//		predate=df.format(com.sxit.stat.util.StatUtil.getPrevCountDate(thedate,1));
//		oldpredate=df.format(com.sxit.stat.util.StatUtil.getPrevCountDate(thedate,2));
		if(!resultType.equals("list"))
		{
			this.pageNo=1;
			this.pageSize=Integer.MAX_VALUE;
		}
		
		
		
		
		CellConferenceService service = (CellConferenceService) this.getBean("cellConferenceService");
		service.getAllConferenceCell(false);
		service.updatePreDayData(date);
		
		this.page = service.getDayConferenceList(date,cellid,lac, pageNo, pageSize,false,getOrderby());

		if (resultType.equals("list"))
			return SUCCESS;
		return "excel";
	}

	/**
	 * @return the predate
	 */
	public String getPredate() {
		return predate;
	}

	/**
	 * @return the oldpredate
	 */
	public String getOldpredate() {
		return oldpredate;
	}

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
	 * @param cellid the cellid to set
	 */
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}
}
