/**
 * ShoucanListAction.java
 */
package com.changpeng.lessons.action;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.lessons.service.LessonsService;

/**
 * @author 华锋
 * Jan 7, 201111:49:21 AM
 *
 */
public class ShoucanListAction extends AbstractListAction {

	public ShoucanListAction(){
		this.moduleId=1004;
	}
	
	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		LessonsService lessonservice = (LessonsService) this.getBean("lessonsService");
		
		int lawyerId=this.getLoginUser().getLawyerid();
		
		
		
		this.page=lessonservice.getShoucangList(lawyerId, lessonType, orderBy, pageSize, pageNo);
		
//		if(resultType.equals("list"))
//			return "list";
//		return "view";
		
		return SUCCESS;
	}
	
	private int lessonType=-1;
	private String orderBy;
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
