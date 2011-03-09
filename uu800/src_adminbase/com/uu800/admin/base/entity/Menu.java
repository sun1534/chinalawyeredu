package com.uu800.admin.base.entity;

import java.util.LinkedHashSet;

/**
 * 用于保存用户菜单.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {

	// Fields
	private String menuid;
	private long menulevel;
	private String menuname;
	private long orderid;
	private String imageurl;
	private String linkurl;
	private String opentarget;
	private LinkedHashSet childMenus = new LinkedHashSet(0);

	public Menu() {
		
	}
	
	public Menu(TsysMenu sysmenu) {		
	 this.menuid = sysmenu.getMenuid();
	 this.menulevel = sysmenu.getMenulevel();
	 this.menuname = sysmenu.getMenuname();
	 this.imageurl = sysmenu.getImageurl();
	 this.linkurl = sysmenu.getLinkurl();
	 this.opentarget = sysmenu.getOpentarget();	
	 this.orderid = sysmenu.getOrderid();	
	}

	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public long getMenulevel() {
		return menulevel;
	}
	public void setMenulevel(long menulevel) {
		this.menulevel = menulevel;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getLinkurl() {
		return linkurl;
	}
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
	public String getOpentarget() {
		return opentarget;
	}
	public void setOpentarget(String opentarget) {
		this.opentarget = opentarget;
	}
	public LinkedHashSet getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(LinkedHashSet childMenus) {
		this.childMenus = childMenus;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

}