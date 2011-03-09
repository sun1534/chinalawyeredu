package com.uu800.admin.base.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.springframework.transaction.annotation.Transactional;

import com.uu800.webbase.BasicService;
import com.uu800.webbase.PageSupport;
import com.uu800.admin.base.dao.DictManagerDAO;
import com.uu800.admin.base.entity.TsysDicttype;
import com.uu800.admin.base.entity.TsysDictvalue;

public class DictManagerService extends BasicService 
{

	private DictManagerDAO dictManagerDAO;
	
	@Resource
	public void setDictManagerDAO(DictManagerDAO dictManagerDAO) {
		this.dictManagerDAO = dictManagerDAO;
		super.basicDao = dictManagerDAO;
	}
	
	public TsysDicttype getTsysDicttype(String dictType)
	{
		TsysDicttype tsysDicttype = dictManagerDAO.getTsysDicttype(dictType);
		if(null != tsysDicttype)
		{
		 tsysDicttype.setTempValues(getTsysDictvalues(dictType));
		}
		return tsysDicttype;
	}

	public PageSupport getTsysDicttype(String dictType, String dictName,String enabledFlag,
			int pageSize,int pageNo) 
	{
		return dictManagerDAO.getTsysDicttype(dictType, dictName,enabledFlag, pageSize,pageNo);
	}

	public List<TsysDictvalue> getTsysDictvalues(String dictType) 
	{
		return dictManagerDAO.getTsysDictvalues(dictType,"");
	}


	
	@Transactional
	public void saveDict(TsysDicttype tsysDicttype) throws ServiceException
	{
			tsysDicttype.setDictType(tsysDicttype.getDictType().trim());
        	// 判断新增加的数据字典中是否有相同的数据字典类型
        	if(getTsysDicttype(tsysDicttype.getDictType()) != null)
        	{
        		throw new ServiceException("SameDictType");
        	}
        	
        	tsysDicttype.setCreatedBy(0L);
        	tsysDicttype.setCreationDate(Calendar.getInstance().getTime());
        	dictManagerDAO.saveDict(tsysDicttype);
        	
        	List<TsysDictvalue> list = tsysDicttype.getTempValues();
        	if(null != list)
        	{
        	for(TsysDictvalue tsysDictvalue : list)
        	{
        		tsysDictvalue.setCreatedBy(0L);
        		tsysDictvalue.setCreationDate(Calendar.getInstance().getTime());
        		tsysDictvalue.setDictType(tsysDicttype.getDictType());
        		dictManagerDAO.saveDictValue(tsysDictvalue);
        	}
        	}   
	}

	@Transactional
	public void updateDict(TsysDicttype tsysDicttype) throws ServiceException
	{
		try
		{
			// 更新数据字典类型
			TsysDicttype tsysDictType = getTsysDicttype(tsysDicttype.getDictType());
			tsysDictType.setLastUpdateDate(Calendar.getInstance().getTime());
			tsysDictType.setLastUpdatedBy(0L);
			tsysDictType.setDictName(tsysDicttype.getDictName());
			tsysDictType.setOpFlag(tsysDicttype.getOpFlag());
			tsysDictType.setEnabledFlag(tsysDicttype.getEnabledFlag());
			dictManagerDAO.updateDict(tsysDictType);
			// END 更新数据字典类型
			
			// 更新数据字典值集合
			List<TsysDictvalue> list = tsysDicttype.getTempValues();
			for(TsysDictvalue tsysDictvalue : tsysDicttype.getTempValues())
        	{
				// 新增
				if(tsysDictvalue.getDictId() == 0)
				{
					tsysDictvalue.setDictType(tsysDicttype.getDictType());
					dictManagerDAO.saveDictValue(tsysDictvalue);
				}
				// 更新
				else
				{
					TsysDictvalue oldDictValue = dictManagerDAO.getTsysDictvalue(tsysDictvalue.getDictId());
					oldDictValue.setDictName(tsysDictvalue.getDictName());
					oldDictValue.setEnabledFlag(tsysDictvalue.getEnabledFlag());
					dictManagerDAO.updateDictValue(oldDictValue);
				}
        	}
			// END 更新数据字典值集合
		}
		catch(Exception ex)
		{
			throw new ServiceException(ex);
		}
	}

	public List<TsysDictvalue> getTsysDictvalues(String dictType,
			String enabledFlag) {
		// TODO Auto-generated method stub
		return dictManagerDAO.getTsysDictvalues(dictType,enabledFlag);
	}
	
	//初始化DictValue
	public HashMap initDictValue() {
		// TODO Auto-generated method stub
		return dictManagerDAO.initDictValue();
	}
}
