/**
 * 
 */
package com.sxit.alarm.action;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.alarm.service.AlarmService;
import com.sxit.common.action.AbstractListAction;

/**
 * 
 * 按时从alarm_nsvc里面拿数据
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class GbAlarmStatHourAction extends AbstractListAction {
	protected static java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat dfhour = new java.text.SimpleDateFormat("HH");
	private static Log _LOG = LogFactory.getLog(GbAlarmStatHourAction.class);

	private String date;
	private String hour;


	@Override
	protected String go() throws Exception {

		
		if(hour==null||hour.equals("")){
			Calendar c = Calendar.getInstance();
			int now = c.get(Calendar.HOUR_OF_DAY);
			if (now == 0 || now == 1)
				hour = "01";
			else {
				Date d = new Date();
				long _now = d.getTime();
				d.setTime(_now - 60 * 60 * 1000);
				hour = dfhour.format(d);
			}
		}

		if (date==null||date.equals("")){
		
			date=df.format(new Date());
		}
		if(orderfield==null||orderfield.equals("")){
			orderfield="alarmcount";
			ascdesc="desc";
		}
	
		AlarmService service = (AlarmService) this.getBean("alarmService");
//		this.page = service.getAlarmhishory(startdate, enddate, nsvc,getOrderby(),pageNo, pageSize);
		this.page = service.getGbAlarmStat(date, hour, getOrderby(), pageNo, pageSize);
		return SUCCESS;
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
}
