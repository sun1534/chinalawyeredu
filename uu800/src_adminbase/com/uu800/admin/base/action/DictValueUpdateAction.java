package com.uu800.admin.base.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysDicttype;
import com.uu800.admin.base.entity.TsysDictvalue;
import com.uu800.admin.base.service.DictManagerService;

public class DictValueUpdateAction extends AbstractAdminAction
{
	private static final long serialVersionUID = 7983126184238760803L;
    
	private String dictType;
	private String dictCode;
	
	private List<TsysDictvalue> dictValues;
	
	private TsysDictvalue dictvalue;
	private TsysDicttype dicttype;
	
	private DictManagerService dictManagerService;
	private String tip;
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public DictValueUpdateAction()
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
		    	TsysDictvalue value = findDictValue(dictCode);
		    	value.setDictName(dictvalue.getDictName());
				value.setEnabledFlag(dictvalue.getEnabledFlag());
				value.setLastUpdatedBy(getUserinfo().getId());
				value.setLastUpdateDate(Calendar.getInstance().getTime());

		     set("TsysDictValue",list);
		     setTip("OK");
		  }
		  catch(Exception ex)
		  {
				setTip("SystemError");
		  }
		return SUCCESS;
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
	
   @Override
	public String input() throws Exception
	{
		List<TsysDictvalue> list = (List<TsysDictvalue>)get("TsysDictValue");
		if(null != list)
		{
		for (TsysDictvalue tsysDictvalue : list)
		{
			if(tsysDictvalue.getDictCode().equals(dictCode))
			{
				dictvalue = tsysDictvalue;
				break;
			}
		}
		}
		return "input";
	}
	
	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public List<TsysDictvalue> getDictValues() {
		return dictValues;
	}

	public void setDictValues(List<TsysDictvalue> dictValues) {
		this.dictValues = dictValues;
	}

	public TsysDicttype getDicttype() {
		return dicttype;
	}

	public void setDicttype(TsysDicttype dicttype) {
		this.dicttype = dicttype;
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
