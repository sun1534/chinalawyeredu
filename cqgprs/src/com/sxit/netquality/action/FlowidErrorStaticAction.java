/**
 * HwchrQueryAction.java
 */
package com.sxit.netquality.action;

import java.text.DateFormat;
import java.util.Calendar;

import com.sxit.common.action.AbstractListAction;
import com.sxit.netquality.service.ChrQueryService;

/**
 * @author 华锋 Jul 29, 20108:41:24 PM
 * 
 */
public class FlowidErrorStaticAction extends AbstractListAction {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String statdate;
	private String stattime="";
	private int statflag;// 1按天0按小时
	private String flowid;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		boolean stattdateset=true;
		if (this.statdate == null || this.statdate.equals("")) {
			this.statdate = df.format(new java.util.Date()); // 默认当天
			stattdateset=false;
		}
		
		if(stattime==null||stattime.equals("")){
			Calendar c=Calendar.getInstance();
			c.setTimeInMillis(c.getTimeInMillis()-60*60*1000);
			int minute=c.get(Calendar.MINUTE);
			int hour=c.get(Calendar.HOUR_OF_DAY);
			if(minute<35)
			{
				if(hour==0){
					hour=23;
					if (!stattdateset) {
						this.statdate = df.format(new java.sql.Timestamp(System.currentTimeMillis()-24*60*60*1000)); // 默认当天
					}
				}else{
					hour=hour-1;
				}
			}
			stattime=(hour<10?"0"+hour:""+hour);
		}

		ChrQueryService queryservice = (ChrQueryService) this.getBean("chrQueryService");

		String _statdate = statdate.replace("-", "");
		String _stattime=stattime.startsWith("0")?stattime.substring(1):stattime;

		if (!(flowid == null || flowid.equals("")))
			this.page = queryservice.getFlowidErrorStat(statflag, _statdate, _stattime, flowid, pageNo, pageSize);

		return SUCCESS;
	}

	/**
	 * @return the statdate
	 */
	public String getStatdate() {
		return statdate;
	}

	/**
	 * @param statdate the statdate to set
	 */
	public void setStatdate(String statdate) {
		this.statdate = statdate;
	}

	/**
	 * @return the stattime
	 */
	public String getStattime() {
		return stattime;
	}

	/**
	 * @param stattime the stattime to set
	 */
	public void setStattime(String stattime) {
		this.stattime = stattime;
	}

	/**
	 * @return the statflag
	 */
	public int getStatflag() {
		return statflag;
	}

	/**
	 * @param statflag the statflag to set
	 */
	public void setStatflag(int statflag) {
		this.statflag = statflag;
	}

	/**
	 * @return the flowid
	 */
	public String getFlowid() {
		return flowid;
	}

	/**
	 * @param flowid the flowid to set
	 */
	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}

}
