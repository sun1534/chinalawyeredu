/**
 * 
 */
package com.sxit.alarm.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.alarm.service.AlarmService;
import com.sxit.common.action.AbstractListAction;
import com.sxit.stat.action.StatAction;

/**
 * 
 * 告警历史记录查询
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class AlarmHistoryAction extends AbstractListAction {
	protected static java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static Log _LOG = LogFactory.getLog(AlarmHistoryAction.class);

	private String start;
	private String end;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	protected String go() throws Exception {

		Date startdate = null;
		Date enddate = null;

		if (start != null && !start.equals("")) {
			try {
				startdate = df.parse(start);
			} catch (Exception e) {
				_LOG.error("起始时间解析错误:", e);
			}
		}
		if (end != null && !end.equals("")) {
			try {
				enddate = df.parse(end);
			} catch (Exception e) {
				_LOG.error("起始时间解析错误:", e);
			}
		}
		
	
		AlarmService service = (AlarmService) this.getBean("alarmService");
		this.page = service.getAlarmhishory(startdate, enddate, pageNo, pageSize);

		return SUCCESS;
	}
}
