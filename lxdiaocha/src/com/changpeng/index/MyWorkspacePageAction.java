/**
 * IndexPageAction.java
 */

package com.changpeng.index;

import java.text.DateFormat;
import java.util.Calendar;

import com.changpeng.common.CommonDatas;
import com.changpeng.common.action.AbstractAction;

/**
 * 
 * 我的工作台，显示我的案件，我的案源，我的日志，我的日程，我的待办事项，法律速递等等 <br/>之后考虑采用extjs的方式来进行数据加载
 * 
 * @author 华锋 2008-4-22 下午06:02:52
 * 
 */
public class MyWorkspacePageAction extends AbstractAction {

	private static final DateFormat dftime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final int START_YEAR = 2006;

	public MyWorkspacePageAction() {
//		this.rightCode = "shouye";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * 我的案件，通过分配的情况来，不是通过userid来
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
	

		
		
	
		// detachedCriteria = DetachedCriteria.forClass(Lessonbaoming.class).createAlias("lessons", "lessons").add(
		// Restrictions.eq("userid", this.getLoginUser().getUserid()));
		// detachedCriteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		// List _baominglist = basicService.findAllByCriteria(detachedCriteria);
		// List baominglist = new ArrayList();
		// for (int i = 0; i < _baominglist.size(); i++) {
		// Map map = (Map) _baominglist.get(i);
		// debug("=======map" + map);
		// Lessons lessons = (Lessons) map.get("lessons");
		// baominglist.add(lessons.getLessonid());// 这个人所有已经报名了的课程
		// }

		if(this.getLoginUser().getLoginname().equals("admin"))
		
		return SUCCESS;
		return "diaocha";
	}

}