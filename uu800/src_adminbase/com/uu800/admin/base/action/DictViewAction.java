package com.uu800.admin.base.action;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;


import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.TsysDicttype;
import com.uu800.admin.base.entity.TsysDictvalue;
import com.uu800.admin.base.service.DictManagerService;

public class DictViewAction extends AbstractAdminAction {

	private TsysDicttype dicttype;
	private DictManagerService dictManagerService;
	private List<TsysDictvalue> dictValues;
	private String dictType;
	
	public DictViewAction()
	{
		dictManagerService = (DictManagerService)getBean("dictManagerService");
	}
	@Override
	public String execute() 
   {
		dicttype = dictManagerService.getTsysDicttype(dictType);
		dictValues = dictManagerService.getTsysDictvalues(dictType);
	   return SUCCESS;
	}
	public TsysDicttype getDicttype() {
		return dicttype;
	}
	public void setDicttype(TsysDicttype dicttype) {
		this.dicttype = dicttype;
	}
	public List<TsysDictvalue> getDictValues() {
		return dictValues;
	}
	public void setDictValues(List<TsysDictvalue> dictValues) {
		this.dictValues = dictValues;
	}
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

}
