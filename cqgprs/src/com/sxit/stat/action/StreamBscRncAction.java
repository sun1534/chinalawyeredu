/**
 * 
 */
package com.sxit.stat.action;


/**
 * BSC/RNC流量分析
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class StreamBscRncAction extends StatAction {

	/*
	 * 得到某一天的所有bsc/rnc的流量
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		this.pageSize = 1000;

		if(startDate==null){
			startDate=getPrevDate();
			this.end=df.format(startDate);
		}
		this.page = statservice.getBscRncStat(startDate, pageNo, pageSize);
		return SUCCESS;
	}
}