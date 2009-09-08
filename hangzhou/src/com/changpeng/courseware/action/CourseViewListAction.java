package com.changpeng.courseware.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.*;

public class CourseViewListAction extends AbstractListAction{
	private BasicService service;
	private int typeid;	
	private String warename;
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getTypeid() {
		return typeid;
	}	
	public CourseViewListAction(){
		service = (BasicService) this.getBean("basicService");
		this.rightCode="document";
	}
	@Override
	protected String go() throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Coursetype.class);		
		detachedCriteria.addOrder(Order.desc("typeid"));
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		return "typelist";
	}
	
	@SuppressWarnings("unchecked")
	public String warelist() throws Exception{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Courseware.class);		
	
		if(typeid!=0)
			detachedCriteria.add(Restrictions.eq("coursetype.typeid", typeid));		
		
		if (warename != null && !"".equals(warename))
			detachedCriteria.add(Restrictions.like("warename", warename, MatchMode.ANYWHERE));
		
		detachedCriteria.addOrder(Order.desc("wareid"));
		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		
		List<Courseware> list=page.getItems();
		coursewareList=new ArrayList();
		detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
		detachedCriteria.add(Restrictions.eq("lawer.userid", this.getLoginUser().getUserid()));// 得到这个人所有的培训记录
		detachedCriteria.add(Restrictions.eq("learnmode", "文本课件"));
		List<Lawyerlessonxf> lessonxflist = service.findAllByCriteria(detachedCriteria);
		
		debug("lessonxflist.size():"+lessonxflist.size());
		
		List xfwareididlist = new ArrayList();
		Map xflessonidmap = new HashMap();
		for (Lawyerlessonxf xf :lessonxflist) {
			if (xf.getWareid() != null) {
				xfwareididlist.add(xf.getWareid().intValue());
				xflessonidmap.put(xf.getWareid().intValue(), xf);
			}
		}

		for (Courseware ware:list) {
			if (xfwareididlist.contains(ware.getWareid())) {
				Lawyerlessonxf xf=(Lawyerlessonxf) xflessonidmap.get(ware.getWareid());	
				ware.setUserlessonxf(xf);

			}
			coursewareList.add(ware);
		}
		return "warelist";
	}
	
	private List<Courseware> coursewareList;
	public List<Courseware> getCoursewareList(){
		return coursewareList;
	}
	
	public String getWarename() {
		return warename;
	}
	public void setWarename(String warename) {
		this.warename = warename;
	}
}
