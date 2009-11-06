package com.changpeng.core.home;


import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.SysParameter;

public class AboutAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private String type;
	
	private String information;
	
	public AboutAction() {
		this.rightCode="PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		if(type==null||type.equals("")){
			type="corpinfo";
		}
		SysParameter p=(SysParameter)service.get(SysParameter.class, type);
		this.information=p.getParamvalue();
		return SUCCESS;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}


}
