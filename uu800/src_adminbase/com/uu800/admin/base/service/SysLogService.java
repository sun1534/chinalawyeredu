package com.uu800.admin.base.service;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.uu800.webbase.BasicService;
import com.uu800.webbase.PageSupport;
import com.uu800.admin.base.dao.SysLogDAO;
import com.uu800.admin.base.entity.TsysLogs;
import com.uu800.admin.base.entity.TsysRight;


public class SysLogService extends BasicService
{
	private SysLogDAO sysLogDAO;
	
	
	@Transactional
	public void save(List<TsysLogs>  loglist)
	{
		if(loglist!=null)
		{
			for(TsysLogs log :loglist )
			{
				try{
				sysLogDAO.save(log);
				}catch(Exception e)
				{
					System.out.println("日志记录错误! 日志信息:"+log.getLogmessage());
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	@Resource
	public void setSysLogDAO(SysLogDAO sysLogDAO) {
		this.sysLogDAO = sysLogDAO;
	}

	public void addSysLog(TsysLogs log) 
	{
		sysLogDAO.addSysLog(log);

	}

	public void deleteSysLog(long logId) 
	{
		sysLogDAO.deleteLog(logId);

	}

	public PageSupport findTsysLogs(String opDateBegin, String opDateEnd,
			String loginName, String userType, String logType, String corpCode,String areacode,
			int pageSize,int pageNo) {
		// TODO Auto-generated method stub
		return sysLogDAO.findTsysLogs(opDateBegin, opDateEnd, loginName, userType, logType, corpCode,areacode, pageSize,pageNo);
	}

	public TsysLogs getTsysLogs(long logId) 
	{
		// TODO Auto-generated method stub
		return sysLogDAO.getLog(logId);
	}
	
	/**
	    * 初始化权限模块名称--权限ID
	    * @param regionCode
	    * @return
	    * @author agu
	    */
	public  LinkedHashMap initModelNameMap(){		
		
		DetachedCriteria dc = DetachedCriteria.forClass(TsysRight.class);
		dc.add(Restrictions.ne("grade", 1L));
		List<TsysRight>  regionlist = sysLogDAO.findByCriteria(dc);
		
		LinkedHashMap modelNameMap = new LinkedHashMap(); 
		for(TsysRight right:regionlist)
		{
		  String modelname = right.getRightname();
		  String rightid = right.getRightid();
		  
		  if(rightid!=null&&!"".equals(rightid))
		  {
			  modelNameMap.put(rightid,modelname);
			  
//			  System.out.print(rightid);
//			  System.out.print(" ");			  
//			  System.out.println(modelNameMap.get(rightid));
		  }		  
		}		
		return modelNameMap;		
	}
	/**
	    * 初始化权限模块ID--权限编码
	    * @param regionCode
	    * @return
	    * @author agu
	    */
	public  LinkedHashMap initModelIdMap(){		
		
		DetachedCriteria dc = DetachedCriteria.forClass(TsysRight.class);
		dc.add(Restrictions.eq("grade", 3L));
		List<TsysRight>  regionlist = sysLogDAO.findByCriteria(dc);
		
		LinkedHashMap modelId = new LinkedHashMap(); 
		for(TsysRight right:regionlist)
		{
		  String rightid = right.getParentRight().getRightid();
		  String rightcode = right.getRightcode();
		  
		  if(rightcode!=null&&!"".equals(rightcode))
		  {
			  modelId.put(rightcode,rightid);		  
		  }		  
		}		
		return modelId;		
	}
	/**
	    * 初始化权限ID--权限编码
	    * @param regionCode
	    * @return
	    * @author agu
	    */
	public  LinkedHashMap initRightIdMap(){		
		
		DetachedCriteria dc = DetachedCriteria.forClass(TsysRight.class);
		dc.add(Restrictions.eq("grade", 3L));
		List<TsysRight>  regionlist = sysLogDAO.findByCriteria(dc);
		
		LinkedHashMap rightId = new LinkedHashMap(); 
		for(TsysRight right:regionlist)
		{
		  String rightid = right.getRightid();
		  String rightcode = right.getRightcode();
		  
		  if(rightcode!=null&&!"".equals(rightcode))
		  {
			  rightId.put(rightcode,rightid);	  
		  }		  
		}		
		return rightId;		
	}
}
