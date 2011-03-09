package com.uu800.admin.base.dao;

import java.util.List;



import com.uu800.webbase.BasicDao;


public class AreaCodeManagerDAO extends BasicDao
{
	public List getRegionList()
	{
		String sql = "Select t.areacode,t.parentid, t.areaname  From tsys_area_code t Start With t.parentid = '0' Connect By Prior areacode = PARENTID";
	     return findBySqlQuery(sql);
	}
}
