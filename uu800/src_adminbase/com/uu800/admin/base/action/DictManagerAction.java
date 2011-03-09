package com.uu800.admin.base.action;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysDicttype;
import com.uu800.admin.base.service.DictManagerService;


public class DictManagerAction extends AbstractAdminAction {

	private static final long serialVersionUID = 415164623308506120L;
	
	private String dictType;
	private String dictName;
	private String enabledFlag;
	private List<TsysDicttype> tsysDicttypes;
	private DictManagerService dictManagerService;
	
	public DictManagerAction()
	{
		dictManagerService = (DictManagerService)getBean("dictManagerService");
	}

	@Override
	public String execute() 
   {
		page= dictManagerService.getTsysDicttype(dictType, dictName,enabledFlag, pageSize,pageNo);
		tsysDicttypes = page.getItems();
		set("TsysDictValue", null);
		return SUCCESS;
	}

	public String getDictType() 
	{
		return dictType;
	}

	public void setDictType(String dictType) 
	{
		this.dictType = dictType;
	}

	public String getDictName() 
	{
		return dictName;
	}

	public void setDictName(String dictName) 
	{
		this.dictName = dictName;
	}

	public List<TsysDicttype> getTsysDicttypes() 
	{
		return tsysDicttypes;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

}
