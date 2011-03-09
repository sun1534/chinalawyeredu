package com.uu800.admin.base.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * TsysMenu entity. @author MyEclipse Persistence Tools
 */

public class TsysMenu extends Menu  implements java.io.Serializable {


    // Fields    

     private String menuid;
     private TsysMenu parentMenu;
     private String menuname;
     private long orderid;
     private String imageurl;
     private String linkurl;
     private String comments;
     private String menutype;
     private Date createtime;
     private String opentarget;
     private long menulevel;
     private int iscomponent;
     private Set tsysMenus = new HashSet(0);
     private Set tsysRights = new HashSet(0);


    // Constructors

    /** default constructor */
    public TsysMenu() {
    }

	/** minimal constructor */
    public TsysMenu(String menuid, String menutype) {
        this.menuid = menuid;
        this.menutype = menutype;
    }
    
    /** full constructor */
    public TsysMenu(String menuid, TsysMenu parentMenu, String menuname, long orderid, String imageurl, String linkurl, String comments, String menutype, Date createtime, String opentarget, long menulevel, Set tsysMenus, Set tsysRights) {
        this.menuid = menuid;
        this.parentMenu = parentMenu;
        this.menuname = menuname;
        this.orderid = orderid;
        this.imageurl = imageurl;
        this.linkurl = linkurl;
        this.comments = comments;
        this.menutype = menutype;
        this.createtime = createtime;
        this.opentarget = opentarget;
        this.menulevel = menulevel;
        this.tsysMenus = tsysMenus;
        this.tsysRights = tsysRights;
    }

   
    // Property accessors

    @Override
	public String getMenuid() {
        return this.menuid;
    }
    
    @Override
	public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    @Override
	public String getMenuname() {
        return this.menuname;
    }
    
    @Override
	public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    @Override
	public long getOrderid() {
        return this.orderid;
    }
    
    @Override
	public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    @Override
	public String getImageurl() {
        return this.imageurl;
    }
    
    @Override
	public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
	public String getLinkurl() {
        return this.linkurl;
    }
    
    @Override
	public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMenutype() {
        return this.menutype;
    }
    
    public void setMenutype(String menutype) {
        this.menutype = menutype;
    }

    public Date getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
	public String getOpentarget() {
        return this.opentarget;
    }
    
    @Override
	public void setOpentarget(String opentarget) {
        this.opentarget = opentarget;
    }

    @Override
	public long getMenulevel() {
        return this.menulevel;
    }
    
    @Override
	public void setMenulevel(long menulevel) {
        this.menulevel = menulevel;
    }

    public Set getTsysMenus() {
        return this.tsysMenus;
    }
    
    public void setTsysMenus(Set tsysMenus) {
        this.tsysMenus = tsysMenus;
    }

    public Set getTsysRights() {
        return this.tsysRights;
    }
    
    public void setTsysRights(Set tsysRights) {
        this.tsysRights = tsysRights;
    }

	public TsysMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(TsysMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public int getIscomponent()
	{
		return iscomponent;
	}

	public void setIscomponent(int iscomponent)
	{
		this.iscomponent = iscomponent;
	}
   








}