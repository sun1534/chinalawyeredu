/**
 * 
 */
package com.sxit.cellConference.action;

import java.text.DateFormat;
import java.util.Date;

import com.sxit.common.action.AbstractAction;

/**
 * @author 华锋
 * Nov 20, 2009-10:09:40 PM
 *
 */
public class CellConferenceAction extends AbstractAction {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String date;
	
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

	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		if (date == null || date.equals("")) {
			date = df.format(com.sxit.stat.util.StatUtil.getPrevDate());
		}
		Date thedate = null;
//		try {
//			thedate = df.parse(date);
//		} catch (Exception e) {
//			e.printStackTrace();
//			thedate = com.sxit.stat.util.StatUtil.getPrevDate();
//		}
		thedate= com.sxit.stat.util.StatUtil.getDate(date);
		
		return SUCCESS;
	}

}
