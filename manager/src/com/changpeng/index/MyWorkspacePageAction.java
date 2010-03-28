/**
 * IndexPageAction.java
 */

package com.changpeng.index;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.models.Articles;
import com.changpeng.models.Lessons;
import com.changpeng.models.SysGroup;

/**
 * 
 * 这里只显示通知和课件等
 * 
 * @author 华锋 2008-4-22 下午06:02:52
 * 
 */
public class MyWorkspacePageAction extends AbstractAction {

	public MyWorkspacePageAction() {

	}

	@Override
	protected String go() throws Exception {

		com.changpeng.system.util.CommonDatas.getGroups();
		// 获取重要通知
		// 事务所的人也好，市级律协的人也好，看到的都是这个省的最重要的一个通知。
		// 当然不能看到别人的。也就是深圳律协的不能看东莞律协的。这样子处理的话，可以看到呢
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Articles.class).add(Restrictions.eq("status", 0))
				.add(Restrictions.eq("type", 1)).add(Restrictions.eq("toshouye", true));
		SysGroup group = this.getLoginUser().getSysGroup();
		if (group != null) {
			Criterion province = null;
		
			if (group.getGrouptype() == 1) { // 事务所的可以看到省和市的
				province = Restrictions.in("thegroup", new Integer[] { group.getGroupid(), group.getParentid(),
						group.getDirectgroup(), 0 });
			} else if (group.getGrouptype() == 2) {// 市级律协的
				province = Restrictions.in("thegroup", new Integer[] { group.getParentid(), group.getGroupid(), 0 });

			} else if (group.getGrouptype() == 3) {
				province = Restrictions.in("thegroup", new Integer[] { group.getGroupid(), 0 });
			}
			Criterion c = Restrictions.eq("provinceid", 0);
			if (province != null) {
				detachedCriteria.add(Restrictions.or(c, province));
			} else
				detachedCriteria.add(c);
		}
		detachedCriteria.addOrder(Order.desc("createtime"));
		List list = basicService.findAllByCriteria(detachedCriteria);
		if (list != null && list.size() != 0) {
			this.tongzhi = (Articles) list.get(0);
		}
//这里的课程显示为本省的或者本市的，不分享的
//		LessonsService lessonsService = (LessonsService) this.getBean("lessonsService");
//		PaginationSupport page = lessonsService.getPages(this.getLoginUser().getSysGroup(), -1, 0, 0, null, null, 6,
//				1, null, null);
		detachedCriteria=DetachedCriteria.forClass(Lessons.class);
		detachedCriteria.add(Restrictions.in("groupid", new Object[]{this.getLoginUser().getProvinceid(),this.getLoginUser().getCityid()}));
		detachedCriteria.addOrder(Order.desc("lessondate"));
		PaginationSupport page =basicService.findPageByCriteria(detachedCriteria, 6, 1);
		this.lessonList = page.getItems();

		// System.out.println(lessonList);

		com.changpeng.system.util.CommonDatas.getGroups();

		return SUCCESS;
	}

	private List lessonList;

	/**
	 * @return the lessonList
	 */
	public List getLessonList() {
		return lessonList;
	}

	private Articles tongzhi;

	public Articles getTongzhi() {
		return this.tongzhi;
	}

}