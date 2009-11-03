package com.changpeng.courseware.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;

import com.changpeng.models.*;
import com.changpeng.models.system.SysUser;

public class CoursewareDeleteAction extends AbstractAction {
	private int wareid;
	private int typeid;
	public void setWareid(int wareid) {
		this.wareid = wareid;
	}
	public void setTypeid(int typeid){
		this.typeid=typeid;
	}
	public CoursewareDeleteAction() {
		this.rightCode = "courseware";
	}

	@Override
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");
		
		//该课件类别下附件数量减1
		Coursetype type=(Coursetype)service.get(Coursetype.class, typeid);
		type.setWarecount(type.getWarecount()-1);
		service.update(type);
		
		service.delete(Courseware.class, wareid);
		
		
		
		this.message = "课件删除成功";
		this.nextPage = "coursewareList.pl?typeid="+typeid;
		return SUCCESS;

	}
}
