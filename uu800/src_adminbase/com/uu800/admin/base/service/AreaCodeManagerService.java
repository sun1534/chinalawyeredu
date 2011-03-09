package com.uu800.admin.base.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import com.uu800.webbase.BasicService;
import com.uu800.webbase.util.TreeInfo;
import com.uu800.admin.base.dao.AreaCodeManagerDAO;
import com.uu800.admin.base.entity.TsysAreaCode;

public class AreaCodeManagerService extends BasicService 
{
	private AreaCodeManagerDAO regionManagerDAO;
	
	@Resource
	public void setRegionManagerDAO(AreaCodeManagerDAO regionManagerDAO)
	{
		this.regionManagerDAO = regionManagerDAO;
		super.basicDao = regionManagerDAO;
	}

		public String getAreaTree()
		{
			List<TreeInfo> list = new ArrayList();
			List orglist = this.regionManagerDAO.getRegionList();
			TreeInfo  tree = new TreeInfo(0,-1,"Root","javascript: void(0);","areamain");		 
			list.add(tree);
			for(Object obj:orglist)
			{	
				 Object[] str=(Object[]) obj;
				 String areaCode =(String) str[0];
				 String parentid = (String) str[1];
				 String areaName =(String) str[2];
				 tree = new TreeInfo(areaCode,parentid,areaName,"regionView.action?areacode="+areaCode,"areamain");
				 list.add(tree);
			}		
			return TreeInfo.createTreeInfo(list);
		}
		
	   /**
	    * 初始化区域MAP
	    * @param regionCode
	    * @return
	    * @author agu
	    */
	public  LinkedHashMap initAreaValue()
	{		
		List<TsysAreaCode> regionlist=regionManagerDAO.findAll(TsysAreaCode.class);
		LinkedHashMap areaMap = new LinkedHashMap(); 
		for(TsysAreaCode region:regionlist)
		{
		  String areaCode =	region.getAreacode();
		  String areaName = region.getAreaname();
		  
		  if(areaCode!=null&&!"".equals(areaCode))
		  {
			  areaMap.put(areaCode, areaName);
		  }
		  
		}		
		return areaMap;		
	}
	
}
