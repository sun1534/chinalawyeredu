/**
 * JifenStatics.java
 */
package com.changpeng.lessons.util;

import java.sql.Timestamp;

/**
 * @author 华锋 2008-6-19 下午04:23:28
 * 
 */
public class Lessonstatics {

	private int online;
	private int local;
	private int onlineandlocal;
	private int wenbenkejian;
	
	private Timestamp start;
	private Timestamp end;
	/**
	 * @return the online
	 */
	public int getOnline() {
		return online;
	}
	/**
	 * @param online the online to set
	 */
	public void setOnline(int online) {
		this.online = online;
	}
	/**
	 * @return the local
	 */
	public int getLocal() {
		return local;
	}
	/**
	 * @param local the local to set
	 */
	public void setLocal(int local) {
		this.local = local;
	}
	
	/**
	 * @return the onlineandlocal
	 */
	public int getOnlineandlocal() {
		return onlineandlocal;
	}
	/**
	 * @param onlineandlocal the onlineandlocal to set
	 */
	public void setOnlineandlocal(int onlineandlocal) {
		this.onlineandlocal = onlineandlocal;
	}
	/**
	 * @return the wenbenkejian
	 */
	public int getWenbenkejian() {
		return wenbenkejian;
	}
	/**
	 * @param wenbenkejian the wenbenkejian to set
	 */
	public void setWenbenkejian(int wenbenkejian) {
		this.wenbenkejian = wenbenkejian;
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

}
