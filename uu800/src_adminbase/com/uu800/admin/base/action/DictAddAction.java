package com.uu800.admin.base.action;

import java.util.List;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysDicttype;
import com.uu800.admin.base.entity.TsysDictvalue;
import com.uu800.admin.base.entity.TsysLogs;
import com.uu800.admin.base.service.DictManagerService;

/**
 * 新增数据字典Action
 * @author Administrator
 *
 */
public class DictAddAction extends AbstractAdminAction
{
	private static final long serialVersionUID = 7983126184238760803L;
    
	private String dictType;
	private List<TsysDictvalue> dictValues;
	private TsysDicttype dicttype;
	private DictManagerService dictManagerService;
	private String tip;
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public DictAddAction()
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
		dictManagerService.saveDict(dicttype);
		this.nextPage = "dictList.action";
		this.message="数据字典新增成功";
		return "sysmsg";
		}
		catch(Exception ex)
		{
			this.message = ex.getMessage();
			return "sysmsg";
		}
	}
	
	@Override
	public String input()
	{
		dictValues = (List<TsysDictvalue>)get("TsysDictValue");
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
}
