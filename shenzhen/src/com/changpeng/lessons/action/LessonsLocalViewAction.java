
package com.changpeng.lessons.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Lessons;

public class LessonsLocalViewAction extends AbstractListAction {
	private Lessons lesson;
	private int lessonid;
	
	private List<String> filelist;
	private boolean hasattach;
	public boolean getHasattach(){
		return hasattach;
	}
	public List<String> getFilelist() {
		return filelist;
	}
	
	
	public Lessons getLesson() {
		return lesson;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public LessonsLocalViewAction() {
		this.rightCode = "locale";
	}


	
	@Override
	protected String go() throws Exception {

		// System.out.println("run here.......................lessonid:"+lessonid);
		BasicService service = (BasicService) this.getBean("basicService");
		// lessonid=1;
		if (lessonid == 0) {

			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lessons.class);
			// detachedCriteria.add(Restrictions.isNull("onlinefile"));
			detachedCriteria.setProjection(Projections.max("lessonid"));

			// detachedCriteria.addOrder(Order.desc("lessonid"));
			List list = service.findAllByCriteria(detachedCriteria);
			int maxlessonid = Integer.parseInt(list.get(0).toString());

			lesson = (Lessons) service.get(Lessons.class, maxlessonid);
	
			// if(page.getTotalCount()>0){
			// System.out.println(page.getItems());
			// lesson=(Lessons)(page.getItems());

			// 这个这样子写是不是太别扭了点 该改成其他什么方式呢？
			// lesson=(Lessons)service.get(Lessons.class, Integer.parseInt(page.getItems().get(0).toString()));
			// }

		}
		else {

			lesson = (Lessons) service.get(Lessons.class, lessonid);

		}
		
		String attach=lesson.getAttach();
		filelist=new java.util.ArrayList<String>();
		if(attach!=null){
			String file[]=attach.split(",");
			for(String str:file){
				if(str!=null&&!"".equals(str.trim()))
					filelist.add(str);
			}
		}
		if(filelist.size()>0)
			hasattach=true;
		
		if(lesson==null){
			this.message="暂时没有培训课程,请转到课程管理进行添加";
			this.nextPage="../lessons/lessonsCreate!input.pl";//
			return "message";
		}

		return SUCCESS;
	}

}