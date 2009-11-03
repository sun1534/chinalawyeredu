package com.changpeng.lessons.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Lessons;

public class LessonsListAction extends AbstractListAction{

	private String title;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	private  String lessontype="-1";
	

	/**
	 * @return the lessontype
	 */
	public String getLessontype() {
		return lessontype;
	}

	/**
	 * @param lessontype the lessontype to set
	 */
	public void setLessontype(String lessontype) {
		this.lessontype = lessontype;
	}

	public LessonsListAction(){
		this.rightCode="kcguanli";
	}
	@Override
	protected String go() throws Exception {
		
		BasicService service=(BasicService) this.getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lessons.class);
		if (title != null && !"".equals(title))
			detachedCriteria.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
		if(this.lessontype.equals("online")){//在线视频
			detachedCriteria.add(Restrictions.isNotNull("onlinefile"));
		}else if(this.lessontype.equals("locale")){
			detachedCriteria.add(Restrictions.isNotNull("lessoncontent"));
		}
		
		detachedCriteria.addOrder(Order.desc("lessondate"));

		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		return SUCCESS;
	}

}
