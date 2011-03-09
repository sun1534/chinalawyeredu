package com.uu800.admin.base.action;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;


import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysAreaCode;
import com.uu800.admin.base.service.AreaCodeManagerService;

public class RegionViewAction extends AbstractAdminAction 
{
	   private String areacode = "00";
	   private TsysAreaCode tsysRegion;
	   private AreaCodeManagerService regionManagerService;
	   
	   public RegionViewAction()
	   {
		   regionManagerService = (AreaCodeManagerService)getBean("regionManagerService");
	   }
	   
		@Override
		public String execute() 
		{
			tsysRegion = (TsysAreaCode)regionManagerService.get(TsysAreaCode.class, areacode);
			return SUCCESS;
		}

		public String getAreacode() {
			return areacode;
		}

		public void setAreacode(String areacode) {
			this.areacode = areacode;
		}

		public TsysAreaCode getTsysRegion() {
			return tsysRegion;
		}

		public void setTsysRegion(TsysAreaCode tsysRegion) {
			this.tsysRegion = tsysRegion;
		}

}
