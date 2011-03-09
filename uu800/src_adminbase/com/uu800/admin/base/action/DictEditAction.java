package com.uu800.admin.base.action;

import java.util.List;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysDicttype;
import com.uu800.admin.base.entity.TsysDictvalue;
import com.uu800.admin.base.entity.TsysLogs;
import com.uu800.admin.base.service.DictManagerService;

public class DictEditAction extends AbstractAdminAction
{
	private static final long serialVersionUID = 7983126184238760803L;
    
	private String dictType;
	private String dictCode;
	
	private List<TsysDictvalue> dictValues;
	
	private TsysDictvalue dictvalue;
	private TsysDicttype dicttype;
	private DictManagerService dictManagerService;


	public DictEditAction()
	{
		dictManagerService = (DictManagerService)getBean("dictManagerService");
	}
	
	@Override
	public String execute() 
	{

		if(null == dicttype.getDictType() || "".equals(dicttype.getDictType().trim()))
		{
			this.message="请输入数据字典类型";
			return "sysmsg";
		}
		
		if(null == dicttype.getDictName() || "".equals(dicttype.getDictName().trim()))
		{
			this.message="请输入数据字典名称";
			return "sysmsg";
		}
		
		try
		{
		List<TsysDictvalue> list = (List<TsysDictvalue>)get("TsysDictValue");
		dicttype.setTempValues(list);
		dictManagerService.updateDict(dicttype);
//		DictValue.refresh();
		this.nextPage = "dictList.action";
		this.message="数据字典修改成功";
		return "sysmsg";
		}
		catch(Exception ex)
		{
			this.message = ex.getMessage();
			return "sysmsg";
		}
	
	}
	
	public String input()
	{
		TsysDicttype dict = dictManagerService.getTsysDicttype(dictType);
		if(get("TsysDictValue") == null)
		{
				dicttype = dict;
				dictValues = dictManagerService.getTsysDictvalues(dictType);
				set("TsysDictValue", dictValues);
		}
		else
		{
			if(dicttype == null)
			{
				dicttype = dict;
			}
			else
			{
				dicttype.setDictType(dictType);
			}
		 dictValues = (List<TsysDictvalue>)get("TsysDictValue");
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
