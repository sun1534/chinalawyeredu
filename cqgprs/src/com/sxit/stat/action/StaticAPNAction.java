/**
 * 
 */
package com.sxit.stat.action;


/**
 * 这里要分页啊
 * 
 * APN业务统计
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class StaticAPNAction extends StatAction {
	

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
		
		this.page=statservice.getApnDayStat(startDate,pageNo,pageSize);
		
		return SUCCESS;
	}
	
}
