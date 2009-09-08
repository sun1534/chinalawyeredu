package com.changpeng.courseware.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.common.util.StringUtil;

import com.changpeng.models.*;

public class CoursetypeEditAction extends AbstractAction {
	private int typeid;
	private BasicService service;
	private Coursetype type;

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public Coursetype getType() {
		if (type == null)
			type = (Coursetype) get("type");
		return type;
	}

	public CoursetypeEditAction() {
		service = (BasicService) this.getBean("basicService");
		this.rightCode = "courseware";
	}

	@Override
	protected String go() throws Exception {
		service.update(type);
		this.nextPage = "coursetypeList.pl";
		this.message = "课件类别编辑成功";
		return SUCCESS;
	}

	public String input() throws ServiceException {
		type = (Coursetype) service.get(Coursetype.class, typeid);
		set("type", type);
		return INPUT;

	}

	public void setType(Coursetype type) {
		this.type = type;
	}
}
