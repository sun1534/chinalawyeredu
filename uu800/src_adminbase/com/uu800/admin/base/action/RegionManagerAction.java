package com.uu800.admin.base.action;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;


import com.uu800.webbase.util.Tree;
import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysAreaCode;
import com.uu800.admin.base.service.AreaCodeManagerService;


public class RegionManagerAction extends AbstractAdminAction 
{
    private AreaCodeManagerService regionManagerService;
    private String regiontree;
    private String areacode = "00";


	public RegionManagerAction()
    {
    	regionManagerService = (AreaCodeManagerService)getBean("regionManagerService");
    }
    
	@Override
	public String execute() 
	{
		regiontree = regionManagerService.getAreaTree();
		return SUCCESS;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getRegiontree() {
		return regiontree;
	}

	public void setRegiontree(String regiontree) {
		this.regiontree = regiontree;
	}

}
