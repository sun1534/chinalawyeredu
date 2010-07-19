/**
 * TSysUserAddAction.java
 */

package com.changpeng.lessons.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.LogLessonListen;

/**
 * 授课律师列表,即roleid=100的sys_user数据
 * 
 * @author 华锋 Jul 11, 20108:46:10 PM
 * 
 */
public class LessonListenListAction extends AbstractListAction {

	private int lessonid;

	public LessonListenListAction() {

	}

	/**
	 * @return the lessonid
	 */
	public int getLessonid() {
		return lessonid;
	}

	/**
	 * @param lessonid the lessonid to set
	 */
	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

	
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LogLessonListen.class).add(Restrictions.eq("lessonid",lessonid));
		

		// 按userid逆序排序
		detachedCriteria.addOrder(Order.desc("firsttime"));
		BasicService service = (BasicService) getBean("basicService");

		this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		// TODO Auto-generated method stub
		return SUCCESS;
	}
}