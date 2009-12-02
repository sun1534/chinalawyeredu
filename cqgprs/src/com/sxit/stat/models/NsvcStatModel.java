/**
 * 
 */
package com.sxit.stat.models;

import com.sxit.netquality.models.BscRnc;
import com.sxit.netquality.models.Nsvc;
import com.sxit.system.util.NumberUtil;

/**
 * @author 华锋
 * Oct 30, 2009-3:08:01 PM
 *
 */
public class NsvcStatModel {
	/**
	 * 这里是以字节为单位,我要转化为M
	 *
	 */
	private float difference;
	private String nsvcid;
	private String date;
	private String datehour;
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
	 * @return the datehour
	 */
	public String getDatehour() {
		return datehour;
	}

	/**
	 * @param datehour the datehour to set
	 */
	public void setDatehour(String datehour) {
		this.datehour = datehour;
	}

	public String getBscid(){
		return com.sxit.netquality.service.BasicSetService.NSVC_BSCS.get(nsvcid);
	}
	
	public BscRnc getBsc(){
		return com.sxit.netquality.service.BasicSetService.ALL_BSCS.get(getBscid());
	}
	
	public Nsvc getNsvc(){
		return com.sxit.netquality.service.BasicSetService.ALL_NSVCS.get(nsvcid);
	}
	
	/**
	 * @return the difference
	 */
	public float getDifference() {
		return difference;
	}

	/**
	 * @param difference the difference to set
	 */
	public void setDifference(float difference) {
		this.difference = difference;
	}

	/**
	 * @return the nsvcid
	 */
	public String getNsvcid() {
		return nsvcid;
	}

	/**
	 * @param nsvcid the nsvcid to set
	 */
	public void setNsvcid(String nsvcid) {
		this.nsvcid = nsvcid;
	}

	private float abs(float f){
		if(f>0)
			return f;
		else 
			return 0-f;
	}
	/**
	 * 以兆为单位
	 * @return
	 */
	public String getTotalStreamStr(){
		if(abs(difference)<10)
			return difference+"（B）";
		else if(abs(difference)<1024)
		{
			double d = ((double) difference) / (1024);
			String totalStreamStr= NumberUtil.toMoney(d);
			return totalStreamStr+"（K）";
		}
		double d = ((double) difference) / (1024*1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return totalStreamStr;
	}
	
	/**
	 * 以M为单位
	 * @return the totalStream
	 */
	public double getTotalStream() {
//		return totalStream;
//		if(totalStream<1)
//			return totalStream+"（K）";
		double d = ((double) difference) / (1024*1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return Float.parseFloat(totalStreamStr);
		
	}
}