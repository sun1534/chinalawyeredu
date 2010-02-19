/**
 * IndexPageAction.java
 */

package com.changpeng.index;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.LawyerlessonxfService;
import com.changpeng.jifen.util.CommonDatas;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.jifen.util.NumberUtil;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.models.Articles;
import com.changpeng.models.Forum;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessonscore;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUnionparams;

/**
 * 
 * 我的工作台，显示我的案件，我的案源，我的日志，我的日程，我的待办事项，法律速递等等 <br/>之后考虑采用extjs的方式来进行数据加载
 * 
 * @author 华锋 2008-4-22 下午06:02:52
 * 
 */
public class MyWorkspacePageAction extends AbstractAction {
	// private static Log LOG = LogFactory.getLog(AbstractAction.class);

	private BasicService basicService = null;
	private JifenTime jifentime;

	/**
	 * @return the jifentime
	 */
	public JifenTime getJifentime() {
		return jifentime;
	}

	public MyWorkspacePageAction() {

		basicService = (BasicService) this.getBean("basicService");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		com.changpeng.lessons.util.CommonDatas.getAlllessons();
		com.changpeng.common.CommonDatas.getGroups();

		LawyerlessonxfService xfservice = (LawyerlessonxfService) this.getBean("lawyerlessonxfService");
		this.lawyers = (Lawyers)basicService.get(Lawyers.class, this.getLoginUser().getLawyerid());
		// 根据查询的年来查,默认为当前时间所在的积分年

		SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, lawyers.getDirectunion());
		needfen = params.getDabiaofen();
		jifentime = CommonDatas.getJifenTime(0, params.getNianshen());

		debug("起始时间:::" + jifentime.getStartstr() + ",终止时间::::" + jifentime.getEndstr());

		DetachedCriteria detachedCriteria = null;

//		nowxuefen = xfservice.getLawyerZongjifen(this.lawyers.getLawyerid(), jifentime.getStart(), jifentime.getEnd());

		nowxuefen = xfservice.getLawyerZongjifen(this.lawyers.getLawyerid(), jifentime.getNianshenyear());

		
		// detachedCriteria =
		// DetachedCriteria.forClass(Lawyerlessonxf.class).add(
		// Restrictions.eq("lawyerid", lawyers.getLawyerid())).add(
		// Restrictions.between("lastupdate", jifentime.getStart(),
		// jifentime.getEnd()));
		// List jflist = basicService.findAllByCriteria(detachedCriteria);
		// int length = jflist == null ? 0 : jflist.size();
		// for (int i = 0; i < length; i++) {
		// Lawyerlessonxf xf = (Lawyerlessonxf) jflist.get(i);
		// nowxuefen += xf.getPxxf();
		// }

		detachedCriteria = DetachedCriteria.forClass(Forum.class).add(Restrictions.eq("ismain", true)).add(Restrictions.eq("provinceid",lawyers.getProvinceunion()));
		detachedCriteria.addOrder(Order.desc("lastupdate"));

		PaginationSupport page = basicService.findPageByCriteria(detachedCriteria, 6, 1);
		this.forumList = page.getItems();

		// 1是通知2是帮助
		detachedCriteria = DetachedCriteria.forClass(Articles.class).add(
				Restrictions.eq("type", 2));
		detachedCriteria.addOrder(Order.asc("listorder"));
		page = basicService.findPageByCriteria(detachedCriteria, 10, 1);
		this.helpList = page.getItems();
		// 获取重要通知,我的事务所发布的或者我的市律协发布的或者我的省律协发布的,也包括系统发布滴
		
		detachedCriteria = DetachedCriteria.forClass(Articles.class).add(Restrictions.eq("status", 0)).add(
				Restrictions.eq("type", 1)).add(Restrictions.eq("toshouye", true));
		Criterion province = Restrictions.in("thegroup", new Integer[] { this.getLoginUser().getProvinceunion(),
				this.getLoginUser().getDirectunion(), this.getLoginUser().getTheoffice(), 0 });
		Criterion c = Restrictions.eq("provinceid", 0);
	   detachedCriteria.add(Restrictions.or(c, province));
	
		
	
		detachedCriteria.addOrder(Order.desc("createtime"));
		List list = basicService.findAllByCriteria(detachedCriteria);
		if (list != null && list.size() != 0) {
			this.tongzhi = (Articles) list.get(0);
		}
		// 我的所属市律协
		SysGroup group = (SysGroup) basicService.get(SysGroup.class, this.getLoginUser().getDirectunion());

		LessonsService lessonsService = (LessonsService) this.getBean("lessonsService");
		PaginationSupport __page = lessonsService.getPages(group, -1, 0, 0, null, null, 6, 1,null,null);

		this.lessonList = __page.getItems();

		// 好评课程，这里也只要显示共享给我的，也就是共享到市律协和省律协的

		String sql = "select count(a.lessonid) as count,a.lessonid from Lessonscore a where a.score>=2 and exists(select lessonid from lessonshared b where a.lessonid=b.lessonid and b.groupid in("
				+ group.getGroupid() + "," + group.getParentid() + "))group by a.lessonid order by count desc limit 10";
		this.goodlessonList = basicService.findBySqlQuery(sql);

		// this.goodlessonList = page.getItems();
		return SUCCESS;
	}

	private float nowxuefen;

	public float getNowxuefen() {
		return Float.parseFloat(NumberUtil.toMoney(nowxuefen + ""));
	}

	private float needfen;

	public float getNeedfen() {
		return this.needfen;
	}

	private Lawyers lawyers;

	public Lawyers getLawyers() {

		return this.lawyers;
	}

	private List forumList;
	private List lessonList;
	private List helpList;
	private List goodlessonList;

	/**
	 * @return the forumList
	 */
	public List getForumList() {
		return forumList;
	}

	/**
	 * @return the lessonList
	 */
	public List getLessonList() {
		return lessonList;
	}

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

	private Articles tongzhi;

	public Articles getTongzhi() {
		return this.tongzhi;
	}

}