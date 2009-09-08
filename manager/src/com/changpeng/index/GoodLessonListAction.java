/**
 * 
 */
package com.changpeng.index;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Articles;
import com.changpeng.models.SysGroup;

/**
 * @author 华锋
 * 
 * 首页的论坛区块的显示
 * 2009-5-2
 */
public class GoodLessonListAction extends AbstractListAction {

	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Articles.class).add(Restrictions.eq("status", 0)).add(Restrictions.eq("type", 2));
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Articles.class).add(Restrictions.eq("type", 2));
		
		detachedCriteria.addOrder(Order.asc("listorder"));
		page = basicService.findPageByCriteria(detachedCriteria, 10, 1);
		this.helpList = page.getItems();
		
		
		
		//这里暂时没考虑那个课程的可见性的情况，nnd，等发现了再说吧
//		detachedCriteria = DetachedCriteria.forClass(Lessonscore.class);
//		detachedCriteria.setProjection(Projections.projectionList().add(Projections.count("lessonid"), "count").add(
//				Projections.groupProperty("lessonid")));
//		detachedCriteria.addOrder(Order.desc("count"));
//		detachedCriteria.add(Restrictions.ge("score", 2));
//		page = basicService.findPageByCriteria(detachedCriteria, 10, 1);
//		this.goodlessonList = page.getItems();
		
		
		SysGroup group=this.getLoginUser().getSysGroup();
		String sql="";
		if(group==null||group.getGrouptype()>3){
			sql = "select count(a.lessonid) as count,a.lessonid from Lessonscore a where a.score>=2 group by a.lessonid order by count desc limit 10";
		}else if(group.getGrouptype()==1){ //事务所的
			 sql = "select count(a.lessonid) as count,a.lessonid from Lessonscore a where a.score>=2 and exists(select lessonid from lessonshared b where a.lessonid=b.lessonid and b.groupid in("
					+ group.getGroupid() + "," + group.getParentid() +  "," + group.getDirectgroup() + "))group by a.lessonid order by count desc limit 10";
		}else if(group.getGrouptype()==2){ //市的
			 sql = "select count(a.lessonid) as count,a.lessonid from Lessonscore a where a.score>=2 and exists(select lessonid from lessonshared b where a.lessonid=b.lessonid and b.groupid in("
					+ group.getGroupid() + "," + group.getParentid() + "))group by a.lessonid order by count desc limit 10";
		}else if(group.getGrouptype()==3){ //省的
			 sql = "select count(a.lessonid) as count,a.lessonid from Lessonscore a where a.score>=2 and exists(select lessonid from lessonshared b where a.lessonid=b.lessonid and b.groupid in("
					+ group.getGroupid() + "))group by a.lessonid order by count desc limit 10";
		}
		
		
	this.goodlessonList = basicService.findBySqlQuery(sql);
		
//		for(int i=0;i<goodlessonList.size();i++){
//			Object obj[]=(Object[])goodlessonList.get(i);
//			System.out.println(obj[0]+"==="+obj[1]);
//		}
//		
		
//		debug("goodlessonList="+goodlessonList);
//		debug("helpList="+helpList);
		
		com.changpeng.lessons.util.CommonDatas.getAlllessons();
		
		return SUCCESS;
	}

	
	private List helpList;
	private List goodlessonList;
	/**
	 * @return the helpList
	 */
	public List getHelpList() {
		return helpList;
	}
	/**
	 * @return the goodlessonList
	 */
	public List getGoodlessonList() {
		return goodlessonList;
	}
}