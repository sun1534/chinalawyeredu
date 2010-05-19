/**
 * 
 */
package com.changpeng.common;

import java.util.ArrayList;
import java.util.List;

import com.changpeng.common.context.Globals;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysUser;
import com.changpeng.system.service.SysGroupService;

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

	
	public void getVisibleDatas(SysUser user,boolean includeother,boolean isgongzhengchu) {

		
		SysRole role = user.getSysRole();
		SysGroupService groups = (SysGroupService) Globals.getBean("sysGroupService");
		BasicDAO dao = (BasicDAO) Globals.getBean("basicDAO");

		if (role == null || role.getGradeid() == 0) { // 所有的都得到
			provinceview = true;
			cityview = true;
			officeview = true;
			this.provincelist = groups.getProvinceUnion(includeother);
			
			if(this.provinceid!=0){
				this.citylist = groups.getCityUnion(provinceid);
			}
			if(this.cityid!=0){
				this.officelist = groups.getOffices(cityid,isgongzhengchu);
				
			}
			
		} else if (role.getGradeid() == 1) {// 省的默认,其他的都得到
			provinceid = user.getProvinceid();

			cityview = true;
			officeview = true;
			SysGroup province = (SysGroup) dao.get(SysGroup.class, user.getProvinceid());
			this.provincelist.add(province);
			this.citylist = groups.getCityUnion(user.getProvinceid());
			 if(this.cityid!=0){
				this.officelist = groups.getOffices(cityid);
			}

		} else if (role.getGradeid() == 2) {// 省市默认，其他的都选择
			provinceid = user.getProvinceid();
			cityid = user.getCityid();
			officeview = true;
			SysGroup province = (SysGroup) dao.get(SysGroup.class, user.getProvinceid());
			SysGroup city = (SysGroup) dao.get(SysGroup.class, user.getCityid());
			this.provincelist.add(province);
			this.citylist.add(city);
			this.officelist = groups.getOffices(user.getCityid(),isgongzhengchu);

		} else if (role.getGradeid() == 3) {// 省市区都默认，没得选择咯
			provinceid = user.getProvinceid();
			cityid = user.getCityid();
			officeid = user.getOfficeid();

			SysGroup province = (SysGroup) dao.get(SysGroup.class, user.getProvinceid());
			SysGroup city = (SysGroup) dao.get(SysGroup.class, user.getCityid());
			SysGroup office = (SysGroup) dao.get(SysGroup.class, user.getOfficeid());

			this.provincelist.add(province);
			this.citylist.add(city);
			this.officelist.add(office);

		}
	}
	
	public void getVisibleDatas(SysUser user,boolean includeother) {

		getVisibleDatas(user,includeother,false);
//		SysRole role = user.getSysRole();
//		SysGroupService groups = (SysGroupService) Globals.getBean("sysGroupService");
//		BasicDAO dao = (BasicDAO) Globals.getBean("basicDAO");
//
//		if (role == null || role.getGradeid() == 0) { // 所有的都得到
//			provinceview = true;
//			cityview = true;
//			officeview = true;
//			this.provincelist = groups.getProvinceUnion(includeother);
//			
//			if(this.provinceid!=0){
//				this.citylist = groups.getCityUnion(provinceid);
//			}
//			if(this.cityid!=0){
//				this.officelist = groups.getOffices(cityid);
//				
//			}
//			
//		} else if (role.getGradeid() == 1) {// 省的默认,其他的都得到
//			provinceid = user.getProvinceid();
//
//			cityview = true;
//			officeview = true;
//			SysGroup province = (SysGroup) dao.get(SysGroup.class, user.getProvinceid());
//			this.provincelist.add(province);
//			this.citylist = groups.getCityUnion(user.getProvinceid());
//			 if(this.cityid!=0){
//				this.officelist = groups.getOffices(cityid);
//			}
//
//		} else if (role.getGradeid() == 2) {// 省市默认，其他的都选择
//			provinceid = user.getProvinceid();
//			cityid = user.getCityid();
//			officeview = true;
//			SysGroup province = (SysGroup) dao.get(SysGroup.class, user.getProvinceid());
//			SysGroup city = (SysGroup) dao.get(SysGroup.class, user.getCityid());
//			this.provincelist.add(province);
//			this.citylist.add(city);
//			this.officelist = groups.getOffices(user.getCityid());
//
//		} else if (role.getGradeid() == 3) {// 省市区都默认，没得选择咯
//			provinceid = user.getProvinceid();
//			cityid = user.getCityid();
//			officeid = user.getOfficeid();
//
//			SysGroup province = (SysGroup) dao.get(SysGroup.class, user.getProvinceid());
//			SysGroup city = (SysGroup) dao.get(SysGroup.class, user.getCityid());
//			SysGroup office = (SysGroup) dao.get(SysGroup.class, user.getOfficeid());
//
//			this.provincelist.add(province);
//			this.citylist.add(city);
//			this.officelist.add(office);
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
	 * @param provinceview the provinceview to set
	 */
	public void setProvinceview(boolean provinceview) {
		this.provinceview = provinceview;
	}

	/**
	 * @param cityview the cityview to set
	 */
	public void setCityview(boolean cityview) {
		this.cityview = cityview;
	}

	/**
	 * @param officeview the officeview to set
	 */
	public void setOfficeview(boolean officeview) {
		this.officeview = officeview;
	}

}
