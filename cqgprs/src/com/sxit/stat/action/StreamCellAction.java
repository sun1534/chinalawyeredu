/**
 * 
 */
package com.sxit.stat.action;


/**
 * CELL流量分析
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class StreamCellAction extends StatAction {
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		if(startDate==null){
			startDate=super.getPrevDate();
			this.start=df.format(startDate);
		}
		
		
		
		if (resultType.equals("list")){
			this.page=statservice.getCellDayStat(startDate, pageNo, pageSize);
			return SUCCESS;
		}

		else if (resultType.equals("excel")){
		    this.page=statservice.getCellDayStat(startDate, 1, Integer.MAX_VALUE);
			return "excel";
		}
		
		return SUCCESS;
	}

}
