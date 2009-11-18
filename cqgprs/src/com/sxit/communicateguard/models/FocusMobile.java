/**
 * 
 */
package com.sxit.communicateguard.models;

import java.text.DateFormat;

import com.sxit.netquality.models.Cell;

/**
 * @author 华锋
 * Nov 18, 2009-2:06:00 AM
 *
 */
public class FocusMobile {
	private static final DateFormat dfyyyyMmddHHmmss = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String mobile;
	private long updatetime;
	private int createuserid;
	private String createusername;
	

	public String getLastupdate(){
		return dfyyyyMmddHHmmss.format(new java.sql.Timestamp(updatetime*1000));
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the updatetime
	 */
	public long getUpdatetime() {
		return updatetime;
	}
	/**
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * @return the createuserid
	 */
	public int getCreateuserid() {
		return createuserid;
	}
	/**
	 * @param createuserid the createuserid to set
	 */
	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}
	/**
	 * @return the createusername
	 */
	public String getCreateusername() {
		return createusername;
	}
	/**
	 * @param createusername the createusername to set
	 */
	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}
}
