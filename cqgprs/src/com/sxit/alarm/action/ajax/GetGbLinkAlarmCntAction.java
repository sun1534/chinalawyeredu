/**
 * 
 */
package com.sxit.alarm.action.ajax;

import java.util.List;

import com.sxit.alarm.service.AlarmService;
import com.sxit.common.action.AbstractAction;

/**
 * 
 * gb流量告警
 * 从alarm_gb表中拿数据
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class GetGbLinkAlarmCntAction extends AbstractAction {

	private int alarmcount;
	private String now;
	
	/**
	 * @param now the now to set
	 */
	public void setNow(String now) {
		this.now = now;
	}


	/**
	 * @return the alarmcount
	 */
	public int getAlarmcount() {
		return alarmcount;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		
		AlarmService service=(AlarmService)this.getBean("alarmService");
		this.alarmcount=service.getGbAlarmCounts();
		return SUCCESS;
	}

}
