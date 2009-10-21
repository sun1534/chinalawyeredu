/**
 * 
 */
package com.sxit.netquality.action;

import com.sxit.common.action.AbstractAction;

/**
 * 
 * 高流量小区
 * 从stat_cell里拿流量排名前100的小区
 * 
 * 最好是做成可配置的,高流量小区，排名前多少位的或流量大于多少滴
 * 
 * 
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class HightStreamCellAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		return SUCCESS;
	}

}
