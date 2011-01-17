/**
 * LessonCenterAction.java
 */
package com.changpeng.lessons.action;

import java.util.List;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.lessons.service.LessonsService;

/**
 * 到lessonorder里面找出对应的数据
 * 在左边显示类别
 * 
 * @author 华锋
 * Jan 7, 201111:47:40 AM
 *
 */
public class XuangouLessonListAction extends AbstractListAction {

	
	public XuangouLessonListAction(){
		this.moduleId=1001;
	}
	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		LessonsService lessonservice = (LessonsService) this.getBean("lessonsService");
		
		int lawyerId=this.getLoginUser().getLawyerid();
		
		this.typelist=lessonservice.getXuangouLessontypeTree(lawyerId);
		if(resultType.equals("view"))
			pageSize=8;
		
		System.out.println("resultType====="+resultType);
		
		this.page=lessonservice.getXuangouLessons(lawyerId, lessonType, isfree,orderBy, pageSize, pageNo);
		
//		if(resultType.equals("list"))
//			return "list";
//		return "view";
		
		return SUCCESS;
	}
	
	
	
	private String resultType="list";
	private String isfree="-1";
	private int lessonType=-1;
	private String orderBy;
	private List typelist;
	/**
	 * @return the typelist
	 */
	public List getTypelist() {
		return typelist;
	}


	/**
	 * @return the lessonType
	 */
	public int getLessonType() {
		return lessonType;
	}

	/**
	 * @param lessonType the lessonType to set
	 */
	public void setLessonType(int lessonType) {
		this.lessonType = lessonType;
	}

	/**
	 * @return the isfree
	 */
	public String getIsfree() {
		return isfree;
	}

	/**
	 * @param isfree the isfree to set
	 */
	public void setIsfree(String isfree) {
		this.isfree = isfree;
	}

	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * @param resultType the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	} 

}
