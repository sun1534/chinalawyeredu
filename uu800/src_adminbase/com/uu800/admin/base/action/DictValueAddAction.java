package com.uu800.admin.base.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysDictvalue;
import com.uu800.admin.base.service.DictManagerService;

public class DictValueAddAction extends AbstractAdminAction
{
	private static final long serialVersionUID = 7983126184238760803L;
    
	private String dictType;
	private String dictCode;
	private TsysDictvalue dictvalue;

	
	private DictManagerService dictManagerService;
	private String tip;
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public DictValueAddAction()
	{
		dictManagerService = (DictManagerService)getBean("dictManagerService");
	}
	
	@Override
	public String execute() 
	{
		
		if( null == dictvalue.getDictCode() || "".equals(dictvalue.getDictCode()))
		  {
			  setTip("InputDictValueCode");
			  return SUCCESS;
			  
		  }
		  if( null == dictvalue.getDictName() || "".equals(dictvalue.getDictName()))
		  {
			  setTip("InputDictValueName");
			  return SUCCESS;
		  }
		    
		  try
		  {
		  List<TsysDictvalue> list = (List<TsysDictvalue>)get("TsysDictValue");
		    if(null == list)
		    {
		    	list = new ArrayList();
		    }
		   
		    // 判断新增的数据字典值编码是否已经存在
		    TsysDictvalue value = findDictValue(dictvalue.getDictCode());
		    if(null != value)
		    {
		       setTip("DictValueCodeRepeat");
		       return SUCCESS;
		    }
		    dictvalue.setCreatedBy(getUserinfo().getId());
			dictvalue.setCreationDate(Calendar.getInstance().getTime());
			list.add(dictvalue);
		   
		     set("TsysDictValue",list);
		     setTip("OK");
		  }
		  catch(Exception ex)
		  {
				setTip("SystemError");
		  }
		return SUCCESS;
	}
	
	public String input()
	{
		return "input";
	}
	
	/**
	 * 从HttpSession中获取数据字典值
	 * @param dictCode
	 * @return
	 */
	private TsysDictvalue findDictValue(String dictCode)
	{
		List<TsysDictvalue> list = (List<TsysDictvalue>)get("TsysDictValue");
		if(null != list)
		{
		  for (TsysDictvalue tsysDictvalue : list)
		  {
			if(tsysDictvalue.getDictCode().equals(dictCode))
			{
				return tsysDictvalue;
			}
		  }
		}
		return null;
	}
	
	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public TsysDictvalue getDictvalue() {
		return dictvalue;
	}

	public void setDictvalue(TsysDictvalue dictvalue) {
		this.dictvalue = dictvalue;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

}
