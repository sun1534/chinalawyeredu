package com.changpeng.courseware.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;

import com.changpeng.models.*;
import com.changpeng.models.system.SysUser;

public class CoursetypeDeleteAction extends AbstractAction{
	private int typeid;

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public CoursetypeDeleteAction(){
		this.rightCode="courseware";	
	}
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService)this.getBean("basicService");
		Coursetype type=(Coursetype)service.get(Coursetype.class, typeid);
		if(type.getWarecount()>0){
			this.message="该类别下存在课件，不能被删除";
			return "message";
		}else{
			service.delete(type);
			this.message="课件类别删除成功";
			this.nextPage="coursetypeList.pl";
			return SUCCESS;
		}		
	}
}
