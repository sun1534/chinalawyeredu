package com.uu800.admin.base.action;

import java.util.List;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysDicttype;
import com.uu800.admin.base.entity.TsysDictvalue;
import com.uu800.admin.base.service.DictManagerService;

/**
 * 更新数据字典
 * @author Administrator
 *
 */
public class DictUpdateAction extends AbstractAdminAction
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

	public DictUpdateAction()
	{
		dictManagerService = (DictManagerService)getBean("dictManagerService");
	}
	
	@Override
	public String execute() 
	{
		if(null == dicttype.getDictType() || "".equals(dicttype.getDictType().trim()))
		{
			setTip("InputDictType");
			return SUCCESS;
		}
		
		if(null == dicttype.getDictName() || "".equals(dicttype.getDictName().trim()))
		{
			setTip("InputDictName");
			return SUCCESS;
		}
		
		try
		{
		List<TsysDictvalue> list = (List<TsysDictvalue>)get("TsysDictValue");
		dicttype.setTempValues(list);
		// 更新
		if(null != dictType && !"".equals(dictType))
		{
			dictManagerService.updateDict(dicttype);
		}
		// 保存
		else
		{
			dictManagerService.saveDict(dicttype);
		}
		setTip(null);
		}
		catch(Exception ex)
		{
			
				setTip("SystemError");

		}
		return SUCCESS;
	}
	
	 @Override
	 public String input() throws Exception 
	 {
		// 更新
			if(null != dictType && !"".equals(dictType))
			{
				TsysDicttype dict = dictManagerService.getTsysDicttype(dictType);
				if(super.get("TsysDictValue") == null)
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
			}
			// 新增
			else
			{
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

}
