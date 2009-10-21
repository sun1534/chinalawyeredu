/**
 * 
 */
package com.sxit.stat.action;

import java.util.List;

/**
 * 行业APN分重点小区分析
 * 根据apn编号以及时间,得到这个apn的所有小区的数据
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class StaticAPNCellAction extends StatAction {
	

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
		apncelllist=statservice.getApnCellDayStat(startDate, apnid);
		
		
		return SUCCESS;
	}
	private List apncelllist;
	
	
	private String apnid;
	/**
	 * @return the apnid
	 */
	public String getApnid() {
		return apnid;
	}
	/**
	 * @param apnid the apnid to set
	 */
	public void setApnid(String apnid) {
		this.apnid = apnid;
	}
	/**
	 * @return the apncelllist
	 */
	public List getApncelllist() {
		return apncelllist;
	}
	
}
