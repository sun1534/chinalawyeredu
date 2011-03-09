package com.uu800.admin.base.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.uu800.webbase.BasicDao;
import com.uu800.webbase.PageSupport;
import com.uu800.webbase.util.DateUtil;
import com.uu800.admin.base.entity.TsysLogs;

public class SysLogDAO extends BasicDao
{

	public void addSysLog(TsysLogs log)
	{
		this.save(log);
	}

	public void deleteLog(long logId) 
	{
		String hql = "delete from TsysLogs where logId=" + logId;
		this.execute(hql);

	}

	public TsysLogs getLog(long logId) 
	{
		Object obj = this.get(TsysLogs.class, logId);
		return (TsysLogs)obj;
	}

	public PageSupport findTsysLogs(String opDateBegin, String opDateEnd,
			String loginName, String userType, String logType, String corpCode,String areacode,
			int pageSize,int pageNo)
	{
		 DetachedCriteria dc=DetachedCriteria.forClass(TsysLogs.class);
			
			if(null != opDateBegin && !"".equals(opDateBegin))
			{
				 dc.add(Restrictions.ge("logtime",DateUtil.strToDate(opDateBegin,DateUtil.YEAR_MONTH_DAY)));
			}
			
			if(null != opDateEnd && !"".equals(opDateEnd))
			{
				dc.add(Restrictions.le("logtime",DateUtil.strToDate(opDateEnd,DateUtil.YEAR_MONTH_DAY)));
			}
			
			// 登录名不为空
	        if(null != loginName && !"".equals(loginName))
	        {
	       	 dc.add(Restrictions.like("username", loginName,MatchMode.ANYWHERE).ignoreCase()); 
	        }
	        
	        // 登录对象不为空
	        if(null != userType && !"".equals(userType))
	        {
	        	 dc.add(Restrictions.eq("usertype",userType)); 
	        }
	        
	        // 操作类型不为空
	        if(null != logType && !"".equals(logType))
	        {
	        	dc.add(Restrictions.eq("logtype", Integer.valueOf(logType)));
	        }
	        
	        if(null != corpCode && !"".equals(corpCode.trim()))
	        {
	        	dc.add(Restrictions.like("corpname", corpCode,MatchMode.ANYWHERE).ignoreCase()); 
	        }
	        
	        //归属区域查询
	        if(null!=areacode && !"".equals(areacode))
	        {
	        	dc.add(Restrictions.eq("areacode", areacode));
	        }
	        
	        
	        dc.addOrder(Order.desc("logid")); 
        return super.findPageOnPageNo(dc, pageSize, pageNo);
	}

	public void updateLog(TsysLogs log)
   {
		this.update(log);

	}

}
