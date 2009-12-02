/**
 * 
 */
package com.sxit.stat.models;

import com.sxit.system.util.NumberUtil;

/**
 * @author 华锋
 * Oct 30, 2009-3:08:01 PM
 *
 */
public class StatModel {
	protected double totalStream=0.0d;

	protected int totalUser=0;

	
	protected double upvolume;
	protected double downvolume;
	/**
	 * 以兆为单位
	 * @return
	 */
	public String getTotalStreamStr(){
		if(totalStream<10)
			return totalStream+"（K）";
		double d = ((double) totalStream) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
//		return Float.parseFloat(totalStreamStr);
		return totalStreamStr;
	}
	public String getUpvolumeStr(){
		if(upvolume<10)
			return upvolume+"（K）";
		double d = ((double) upvolume) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
//		return Float.parseFloat(totalStreamStr);
		return totalStreamStr;
	}
	public String getDownvolumeStr(){
		if(downvolume<10)
			return downvolume+"（K）";
		double d = ((double) downvolume) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
//		return Float.parseFloat(totalStreamStr);
		return totalStreamStr;
	}
	/**
	 * @return the totalStream
	 */
	public double getTotalStream() {
//		return totalStream;
//		if(totalStream<1)
//			return totalStream+"（K）";
		double d = ((double) totalStream) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return Float.parseFloat(totalStreamStr);
		
	}

	/**
	 * @param totalStream the totalStream to set
	 */
	public void setTotalStream(double totalStream) {
		this.totalStream = totalStream;
	}

	/**
	 * 
	 * 
	 * @return the totalUser
	 */
	public int getTotalUser() {
		return totalUser;
	}

	/**
	 * @param totalUser the totalUser to set
	 */
	public void setTotalUser(int totalUser) {
		this.totalUser =totalUser;
	}

	/**
	 * 得到平均流量,单位为k
	 * @return the averageStream
	 */
	public float getAverageStreamStr() {
		if(totalUser==0)
			return 0.0f;
		float f= ((float) totalStream) / (totalUser);
		String averageStreamStr= NumberUtil.toMoney(f);
		return Float.parseFloat(averageStreamStr);
	}
	
	/**
	 * 得到平均流量,单位为k
	 * @return the averageStream
	 */
	public float getAverageStream() {
		if(totalUser==0)
			return 0.0f;
		float f= ((float) totalStream) / ( totalUser);
		String averageStreamStr= NumberUtil.toMoney(f);
		return Float.parseFloat(averageStreamStr);
	}

	/**
	 * @return the upvolume
	 */
	public double getUpvolume() {
		return upvolume;
	}

	/**
	 * @param upvolume the upvolume to set
	 */
	public void setUpvolume(double upvolume) {
		this.upvolume = upvolume;
	}

	/**
	 * @return the downvolume
	 */
	public double getDownvolume() {
		return downvolume;
	}

	/**
	 * @param downvolume the downvolume to set
	 */
	public void setDownvolume(double downvolume) {
		this.downvolume = downvolume;
	}
}
