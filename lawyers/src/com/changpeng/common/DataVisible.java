/**
 * 
 */
package com.changpeng.common;

import java.util.ArrayList;
import java.util.List;

import com.changpeng.common.context.Globals;
import com.changpeng.lawyers.service.SysGroupService;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysRole;

/**
 * @author 华锋
 * 
 */
public class DataVisible {

	private int provinceid = 0;
	private int cityid = 0;
	private int officeid = 0;

	private List<SysGroup> provincelist = new ArrayList<SysGroup>();
	private List<SysGroup> citylist = new ArrayList<SysGroup>();
	private List<SysGroup> officelist = new ArrayList<SysGroup>();

	private boolean provinceview;
	private boolean cityview;
	private boolean officeview;

	

	
	public void getVisibleDatas(Lawyers lawyers,boolean includeother) {

		Globals g = new Globals();
	
		SysGroupService groups = (SysGroupService) g.getBean("sysGroupService");
	
	
			provinceview = true;
			cityview = true;
			this.provincelist = groups.getProvinceUnion(true);
//			this.provinceid=lawyers.getProvinceunion();
//			this.cityid=lawyers.getDirectunion();
			
			
			if(this.provinceid!=0){
				this.citylist = groups.getCityUnion(provinceid);
			}
// if(this.cityid!=0){
// this.officelist = groups.getOffices(cityid);
//				
// }
//			
//		} 
	}

	/**
	 * @return the provincelist
	 */
	public List<SysGroup> getProvincelist() {
		return provincelist;
	}

	/**
	 * @return the citylist
	 */
	public List<SysGroup> getCitylist() {
		return citylist;
	}

	/**
	 * @return the officelist
	 */
	public List<SysGroup> getOfficelist() {
		return officelist;
	}

	/**
	 * @return the provinceid
	 */
	public int getProvinceid() {
		return provinceid;
	}

	/**
	 * @return the cityid
	 */
	public int getCityid() {
		return cityid;
	}

	/**
	 * @return the officeid
	 */
	public int getOfficeid() {
		return officeid;
	}

	/**
	 * @param provinceid
	 *            the provinceid to set
	 */
	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	/**
	 * @param cityid
	 *            the cityid to set
	 */
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	/**
	 * @param officeid
	 *            the officeid to set
	 */
	public void setOfficeid(int officeid) {
		this.officeid = officeid;
	}


	/**
	 * @param provinceview
	 *            the provinceview to set
	 */
	public boolean getProvinceview() {
		return provinceview;
	}

	/**
	 * @return the cityview
	 */
	public boolean getCityview() {
		return cityview;
	}

	/**
	 * @return the officeview
	 */
	public boolean getOfficeview() {
		return officeview;
	}

	/**
	 * @param provinceview
	 *            the provinceview to set
	 */
	public void setProvinceview(boolean provinceview) {
		this.provinceview = provinceview;
	}

	/**
	 * @param cityview
	 *            the cityview to set
	 */
	public void setCityview(boolean cityview) {
		this.cityview = cityview;
	}

	/**
	 * @param officeview
	 *            the officeview to set
	 */
	public void setOfficeview(boolean officeview) {
		this.officeview = officeview;
	}

}
