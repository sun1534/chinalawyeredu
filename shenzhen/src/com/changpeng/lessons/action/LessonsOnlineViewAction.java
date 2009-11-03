
package com.changpeng.lessons.action;

import org.hibernate.criterion.*;

import com.changpeng.common.BasicService;

import com.changpeng.common.action.AbstractListAction;

import com.changpeng.models.*;

import java.util.*;

public class LessonsOnlineViewAction extends AbstractListAction {
	private Lessons lesson;
	private int lessonid;

/*	private int common; // 评价一般票数
	private int preferably; // 评价较好票数
	private int good; // 评价很好票数

	private int commonl; // 评价一般票数显示宽度
	private int preferablyl;
	private int goodl;*/
	
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

/*	public int getCommon() {
		return common;
	}

	public int getPreferably() {
		return preferably;
	}

	public int getGood() {
		return good;
	}

	public int getCommonl() {
		return commonl;
	}

	public int getPreferablyl() {
		return preferablyl;
	}

	public int getGoodl() {
		return goodl;
	}
*/
	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public LessonsOnlineViewAction() {
		this.rightCode = "online";
	}

	@Override
	protected String go() throws Exception {

		BasicService service = (BasicService) this.getBean("basicService");
		// lessonid=1;
		if (lessonid == 0) {

			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lessons.class);
			detachedCriteria.add(Restrictions.isNotNull("onlinefile"));
			detachedCriteria.setProjection(Projections.max("lessonid"));

			List list = service.findAllByCriteria(detachedCriteria);
			int maxlessonid = Integer.parseInt(list.get(0).toString());

			lesson = (Lessons) service.get(Lessons.class, maxlessonid);

			// detachedCriteria.addOrder(Order.desc("lessonid"));
			// this.page = service.findPageByCriteria(detachedCriteria);
			// if(page.getTotalCount()>0){
			// //System.out.println(page.getItems());
			// //lesson=(Lessons)(page.getItems());
			//				
			// //这个这样子写是不是太别扭了点 该改成其他什么方式呢？
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

		// DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lessonscore.class);
		// detachedCriteria.add(Restrictions.eq("lessonid", lesson.getLessonid()));
		// List<Lessonscore> lessonscores = service.findPageByCriteria(detachedCriteria).getItems();

		// 像下面这样子用的话会报懒加载错误 但设置lazy=false又不爽
		// 在该action中加入<interceptor-ref name="hibernateSessionStack"/>就OK了
		// 但是我按上面那样写的话 我该怎么指定detachedCriteria才行呢

		if (lesson == null) {
			this.message = "暂时没有视频课程,请转到课程管理进行添加";
			this.nextPage = "../lessons/lessonsCreate!input.pl";//
			return "message";
		}

		/*Set<Lessonscore> lessonscores = lesson.getLessonscores();
		debug("lessonscores.size()===" + lessonscores.size());

		common = 0;
		preferably = 0;
		good = 0;
		for (Lessonscore score : lessonscores) {
			if (score.getScore() == 1)
				common++;
			else if (score.getScore() == 2)
				preferably++;
			else if (score.getScore() == 3)
				good++;
		}

		int all = lessonscores.size();
		
		
		if (all == 0)
			commonl = preferablyl = goodl = 30;
		else {
			debug("(commonl * 250) / all==="+((commonl * 250) / all));
			commonl = 30 + (common * 250) / all;
			preferablyl = 30 + (preferably * 250) / all;
			goodl = 30 + (good * 250) / all;
		}
		debug("common1=" + commonl + ",preferablyl==" + preferablyl + "==,goodl==" + goodl);*/

		return SUCCESS;
	}

}
