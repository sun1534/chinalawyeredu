/**
 * 
 */
package com.sxit.index;

import java.util.List;

/**
 * @author 华锋
 * Nov 20, 2009-1:37:58 PM
 *
 */
public class IndexCache {

	private List totallist;
	private List total23glist;
	private int exceptionapn;
	private int exceptioncell;
	private List streamlist;
	private long keyday;
	private int hasset;
	
	/**
	 * @return the keyhourmm
	 */
	public int getKeyhourmm() {
		return hasset;
	}
	/**
	 * @param keyhourmm the keyhourmm to set
	 */
	public void setKeyhourmm(int hasset) {
		this.hasset = hasset;
	}
	/**
	 * @return the totallist
	 */
	public List getTotallist() {
		return totallist;
	}
	/**
	 * @param totallist the totallist to set
	 */
	public void setTotallist(List totallist) {
		this.totallist = totallist;
	}
	/**
	 * @return the total23glist
	 */
	public List getTotal23glist() {
		return total23glist;
	}
	/**
	 * @param total23glist the total23glist to set
	 */
	public void setTotal23glist(List total23glist) {
		this.total23glist = total23glist;
	}
	/**
	 * @return the exceptionapn
	 */
	public int getExceptionapn() {
		return exceptionapn;
	}
	/**
	 * @param exceptionapn the exceptionapn to set
	 */
	public void setExceptionapn(int exceptionapn) {
		this.exceptionapn = exceptionapn;
	}
	/**
	 * @return the exceptioncell
	 */
	public int getExceptioncell() {
		return exceptioncell;
	}
	/**
	 * @param exceptioncell the exceptioncell to set
	 */
	public void setExceptioncell(int exceptioncell) {
		this.exceptioncell = exceptioncell;
	}
	/**
	 * @return the streamlist
	 */
	public List getStreamlist() {
		return streamlist;
	}
	/**
	 * @param streamlist the streamlist to set
	 */
	public void setStreamlist(List streamlist) {
		this.streamlist = streamlist;
	}
	/**
	 * @return the keydate
	 */
	public long getKeyday() {
		return keyday;
	}
	/**
	 * @param keydate the keydate to set
	 */
	public void setKeyday(long keday) {
		this.keyday = keday;
	}
	
	
}
