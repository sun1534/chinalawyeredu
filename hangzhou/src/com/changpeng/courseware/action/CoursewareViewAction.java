package com.changpeng.courseware.action;

import java.util.List;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.*;

public class CoursewareViewAction extends AbstractAction {
	private int wareid;
	private Courseware ware;
	private float pxminutes; // 该课件已学习时间
	
	private Integer roleid;
	
	public Integer getRoleid() {
		return roleid;
	}

	public float getPxminutes() {
		return pxminutes;
	}

	public void setWareid(int wareid) {
		this.wareid = wareid;
	}

	public Courseware getWare() {
		return ware;
	}

	public CoursewareViewAction() {
		this.rightCode = "document";
	}

	@Override
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");
		
		roleid=getLoginUser().getRoleid();
		
		List list = service.find(" from Lawyerlessonxf where lawer.userid="
				+ getLoginUser().getUserid() + " and wareid="
				+ wareid + "");
		
		if (list.size() > 0) {
			Lawyerlessonxf lessonxf = (Lawyerlessonxf) list.get(0);
			pxminutes = lessonxf.getPxminutes();
		} else
			pxminutes = new Float(0.0);

		ware = (Courseware) service.get(Courseware.class, wareid);

		// 观看次数加1
		ware.setReadcount(ware.getReadcount() + 1);
		service.update(ware);

		set("ware", ware);
		return SUCCESS;
	}
}
