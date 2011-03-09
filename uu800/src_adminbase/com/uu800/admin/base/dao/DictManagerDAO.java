package com.uu800.admin.base.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.uu800.webbase.BasicDao;
import com.uu800.webbase.PageSupport;
import com.uu800.admin.base.entity.TsysDicttype;
import com.uu800.admin.base.entity.TsysDictvalue;

public class DictManagerDAO extends BasicDao
{

	public PageSupport getTsysDicttype(String dictType, String dictName,String enabledFlag,
			int pageSize,int pageNo) 
	{
		DetachedCriteria dc=DetachedCriteria.forClass(TsysDicttype.class);
        if(null != dictType && !"".equals(dictType))
        {
       	 dc.add(Restrictions.like("dictType", dictType.trim(),MatchMode.ANYWHERE).ignoreCase()); 
        }
        if(null != dictName && !"".equals(dictName))
        {
       	 dc.add(Restrictions.like("dictName", dictName.trim(),MatchMode.ANYWHERE).ignoreCase()); 
        }
        
        if(null != enabledFlag && !"".equals("enabledFlag"))
        {
        	dc.add(Restrictions.eq("enabledFlag",enabledFlag)); 
        }
        dc.addOrder(Order.desc("dictType")); 
        return super.findPageOnPageNo(dc, pageSize, pageNo);
	}
	
	public void saveDict(TsysDicttype tsysDicttype) 
	{
		save(tsysDicttype);
	}

	public void updateDict(TsysDicttype tsysDicttype) 
	{
		update(tsysDicttype);
	}

	public void saveDictValue(TsysDictvalue tsysDictvalue)
	{
		save(tsysDictvalue);
	}
	
	public void updateDictValue(TsysDictvalue tsysDictvalue)
	{
		super.update(tsysDictvalue);
		
	}
	
	public void deleteTsysDictvalues(String dictType) 
	{
	   String hql = "delete from TsysDictvalue where dictType = :dictType";
	   deletes(hql, new Object[]{dictType});
	}

	public TsysDicttype getTsysDicttype(String dictType) 
	{
		return (TsysDicttype)get(TsysDicttype.class, dictType);
	}

	public TsysDictvalue getTsysDictvalue(long dictId)
	{
		return (TsysDictvalue)get(TsysDictvalue.class, dictId);
	}

	public TsysDictvalue getTsysDictvalue(String dictType, String dictCode)
	{
		List<TsysDictvalue> list = getTsysDictvalues(dictType,dictCode,"");
		if(null == list || list.size() == 0)
		{
			return null;
		}
		return list.get(0); 
	}

	public List<TsysDictvalue> getTsysDictvalues(String dictType,String enabledFlag) 
	{
		List<TsysDictvalue> list = getTsysDictvalues(dictType,"",enabledFlag);
		if(null == list || list.size() == 0)
		{
			return new ArrayList<TsysDictvalue>();
		}
		return list;
	}

	public TsysDictvalue getTsysDictvalue(String dictType, String dictCode,
			String enabledFlag)
    {
		List<TsysDictvalue> list = getTsysDictvalues(dictType,dictCode,enabledFlag);
		if(null == list || list.size() == 0)
		{
			return null;
		}
		return list.get(0); 
	}
	
	private List<TsysDictvalue> getTsysDictvalues(String dictType, String dictCode,
			String enabledFlag)
			{
		StringBuilder sb = new StringBuilder("from TsysDictvalue p where 1=1");
        List<Object> paramList = new ArrayList<Object>();
		if(null != dictType && !"".equals(dictType))
		{
			sb.append(" and p.dictType=?");
			paramList.add(dictType);
		}
		
		if(null != dictCode && !"".equals(dictCode))
		{
			sb.append(" and p.dictCode=?");
			paramList.add(dictCode);
		}
		if(null != enabledFlag && !"".equals(enabledFlag))
		{
			sb.append(" and p.enabledFlag=?");
			paramList.add(enabledFlag);
		}
		List<TsysDictvalue> list = super.findByQuery(sb.toString(), paramList.toArray());
		return list;
		}

	/**
	 * zrb添加 用于处理初始化DictValue
	 * 	@return
	 */
	public HashMap initDictValue() 
	{
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			{
                //此处添加需要处理的代码
                Criteria criteria = session.createCriteria(TsysDicttype.class);
                criteria.add(Restrictions.eq("enabledFlag","Y")); 
                criteria.setProjection(null);				
                List<TsysDicttype> typelist = criteria.setCacheable(true).list();
                HashMap dict = new HashMap();
                for(TsysDicttype type:typelist){
                	LinkedHashMap temp = new LinkedHashMap(); 
                	if(type.getTsysDictvalues()!=null)
                	{
                		for(TsysDictvalue dictvalue:type.getTsysDictvalues())
                    	{
                    		temp.put(dictvalue.getDictCode(), dictvalue.getDictName());
                    	}
                	}                	
                	dict.put(type.getDictType(), temp);
                }
                return dict;
			}	
		}
	    );
	  return (HashMap)object;
	}
}
