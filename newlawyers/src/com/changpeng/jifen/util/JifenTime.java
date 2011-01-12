/**
 * 
 */
package com.changpeng.jifen.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Map;

/**
 * @author 华锋
 *
 */
public class JifenTime {
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");
	
	public Timestamp start;
	public Timestamp end;
	
	public int nianshenyear;
	
	public Map<Integer,Integer> years;
	/**
	 * @return the years
	 */
	public Map<Integer, Integer> getYears() {
		return years;
	}
	/**
	 * @param years the years to set
	 */
	public void setYears(Map<Integer, Integer> years) {
		this.years = years;
	}
	/**
	 * @return the start
	 */
	public Timestamp getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(Timestamp start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public Timestamp getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	/**
	 * @return the nianshenyear
	 */
	public int getNianshenyear() {
		return nianshenyear;
	}
	/**
	 * @param nianshenyear the nianshenyear to set
	 */
	public void setNianshenyear(int nianshenyear) {
		this.nianshenyear = nianshenyear;
	}
	
	public String getStartstr(){
		return dfdate.format(start);
	}
	public String getEndstr(){
		return dfdate.format(end);
	}
	
}
