/**
 * 
 */
package com.sxit.alarm.models;

import java.text.DateFormat;

import com.sxit.netquality.models.BscRnc;
import com.sxit.system.util.NumberUtil;

/**
 * @author 华锋
 * Nov 17, 2009-10:45:52 PM
 *
 */
public class GbLinkAlarm {
	private static final DateFormat dfyyyyMmddHHmmss = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
private static String ADDRATE="100%";
	private String nsvc;
	private int alarmtype;
	private long alarmtime;
	private float flowcount;
	private int isactive;
	/**
	 * @return the isactive
	 */
	public int getIsactive() {
		return isactive;
	}

	/**
	 * @param isactive the isactive to set
	 */
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	
	public String getReason(){
		if(alarmtype==1){
			return "该活动链路流量为0";
		}else if(alarmtype==1){
			return "此链路流量比上一时间段增长"+ADDRATE;
		}else
			return "此链路当前为锁定状态";
	}

	/**
	 * 以兆为单位
	 * @return
	 */
	public String getCurrentstream(){
		if(flowcount<1)
			return flowcount+"（B）";
		else if(flowcount<1024)
		{
			double d = ((double) flowcount) / (1024);
			String totalStreamStr= NumberUtil.toMoney(d);
			return totalStreamStr+"（K）";
		}
		double d = ((double) flowcount) / (1024*1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return totalStreamStr;
	}
	
	public String getAlarmdate(){
		return dfyyyyMmddHHmmss.format(new java.sql.Timestamp(alarmtime*1000));
	}
	
	public String getBscid(){
		return com.sxit.netquality.service.BasicSetService.NSVC_BSCS.get(nsvc);
	}
	
	public BscRnc getBsc(){
		return com.sxit.netquality.service.BasicSetService.ALL_BSCS.get(getBscid());
	}

	/**
	 * @return the nsvc
	 */
	public String getNsvc() {
		return nsvc;
	}

	/**
	 * @param nsvc the nsvc to set
	 */
	public void setNsvc(String nsvc) {
		this.nsvc = nsvc;
	}

	/**
	 * @return the alarmtype
	 */
	public int getAlarmtype() {
		return alarmtype;
	}

	/**
	 * @param alarmtype the alarmtype to set
	 */
	public void setAlarmtype(int alarmtype) {
		this.alarmtype = alarmtype;
	}

	/**
	 * @return the alarmtime
	 */
	public long getAlarmtime() {
		return alarmtime;
	}

	/**
	 * @param alarmtime the alarmtime to set
	 */
	public void setAlarmtime(long alarmtime) {
		this.alarmtime = alarmtime;
	}

	/**
	 * @return the flowcount
	 */
	public float getFlowcount() {
		return flowcount;
	}

	/**
	 * @param flowcount the flowcount to set
	 */
	public void setFlowcount(float flowcount) {
		this.flowcount = flowcount;
	}
}
