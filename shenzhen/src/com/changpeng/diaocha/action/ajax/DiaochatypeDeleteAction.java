package com.changpeng.diaocha.action.ajax;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.*;
public class DiaochatypeDeleteAction extends AbstractAction{
	//private String typename;
	private Integer typeid;
	private BasicService service;
	public DiaochatypeDeleteAction(){
		service=(BasicService)this.getBean("basicService");
	}
/*	public void setTypename(String typename) {
		this.typename = typename;
	}*/
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		Diaochatype type=(Diaochatype)service.get(Diaochatype.class, typeid);
		service.delete(type);
		return SUCCESS;
	}
}
