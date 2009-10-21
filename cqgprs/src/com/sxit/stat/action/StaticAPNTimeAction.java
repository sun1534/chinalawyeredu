/**
 * 
 */
package com.sxit.stat.action;

import java.util.List;

/**
 * 行业APN分时段统计
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class StaticAPNTimeAction extends StatAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		if(startDate==null){
			startDate=getPrevDate();
			this.start=df.format(startDate);
		}
		apntimelist=statservice.getApnDayTimeStat(startDate, apnid);
		return SUCCESS;
	}

	private String apnid;
	private List apntimelist;

	/**
	 * @return the apnid
	 */
	public String getApnid() {
		return apnid;
	}

	/**
	 * @param apnid
	 *            the apnid to set
	 */
	public void setApnid(String apnid) {
		this.apnid = apnid;
	}

	/**
	 * @return the apntimelist
	 */
	public List getApntimelist() {
		return apntimelist;
	}

}
