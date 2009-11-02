package com.changpeng.courseware.action;

import org.hibernate.criterion.*;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.*;
public class CoursewareListAction extends AbstractListAction{
	private int typeid;
	private String warename;
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getTypeid() {
		return typeid;
	}
	public CoursewareListAction(){
		this.rightCode="courseware";
	}
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService) this.getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Courseware.class);
		if(typeid!=0)
			detachedCriteria.add(Restrictions.eq("coursetype.typeid", typeid));
		
		if (warename != null && !"".equals(warename))
			detachedCriteria.add(Restrictions.like("warename", warename, MatchMode.ANYWHERE));
		
		detachedCriteria.addOrder(Order.desc("wareid"));

		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}
	public String getWarename() {
		return warename;
	}
	public void setWarename(String warename) {
		this.warename = warename;
	}

}
