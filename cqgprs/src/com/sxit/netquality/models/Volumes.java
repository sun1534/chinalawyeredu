/**
 * 
 */
package com.sxit.netquality.models;

import com.sxit.system.util.NumberUtil;

/**
 * @author 华锋
 * Nov 16, 2009-11:06:45 PM
 *
 */
public class Volumes {
/**
 * 一直以来的总流量
 */
	private double allvolume;
	/**
	 * 一直以来的下行流量
	 */
	private double downvolume;
	/**
	 * 一直以来的上行流量
	 */
	private double upvolume;
	/**
	 * @return the allvolume
	 */
	public double getAllvolume() {
		double d = ((double) allvolume) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		allvolume= Double.parseDouble(totalStreamStr);
		return allvolume;
	}
	/**
	 * @param allvolume the allvolume to set
	 */
	public void setAllvolume(double allvolume) {
		this.allvolume = allvolume;
	}
	/**
	 * @return the downvolume
	 */
	public double getDownvolume() {
		
		double d = ((double) downvolume) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		downvolume= Double.parseDouble(totalStreamStr);
		return downvolume;
	}
	/**
	 * @param downvolume the downvolume to set
	 */
	public void setDownvolume(double downvolume) {
		this.downvolume = downvolume;
	}
	/**
	 * @return the upvolume
	 */
	public double getUpvolume() {
		double d = ((double) upvolume) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		upvolume= Double.parseDouble(totalStreamStr);
		return upvolume;
	}
	/**
	 * @param upvolume the upvolume to set
	 */
	public void setUpvolume(double upvolume) {
		this.upvolume = upvolume;
	}
	

}
