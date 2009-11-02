/**
 * IndexPageAction.java
 */

package com.changpeng.index;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.LawyerlessonxfService;
import com.changpeng.lessons.service.LessonsService;
import com.changpeng.models.Articles;
import com.changpeng.models.Forum;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonscore;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysRole;
import com.changpeng.models.system.SysUser;

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
	private static final int START_YEAR = 2007;

	public MyWorkspacePageAction() {
		this.rightCode = "shouye";

		Calendar calendar = Calendar.getInstance();
		int nowmonth = calendar.get(Calendar.MONTH) + 1;
		int nowday = calendar.get(Calendar.DATE);
		int nowyear = calendar.get(Calendar.YEAR);

		String nianshen = CommonDatas.SysParameter.get("nianshen").toString();
		int index = nianshen.indexOf("-");
		int shenmonth = Integer.parseInt(nianshen.substring(0, index));
		int shenday = Integer.parseInt(nianshen.substring(index + 1));
		// 今天是5.5，年審時間是5.4號，那應該算2008年的了,否则就算是2007年的
		if (nowmonth * 100 + nowday > shenmonth * 100 + shenday) {
			this.year = nowyear;
		}
		else {
			this.year = nowyear - 1;
		}
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
		this.needfen = Float.parseFloat(CommonDatas.SysParameter.get("dabiaofen").toString());
		BasicService basicService = (BasicService) getBean("basicService");
		LawyerlessonxfService xfService = (LawyerlessonxfService) getBean("lawyerlessonxfService");
		// this.sysUser = (SysUser) basicService.get(SysUser.class, this.getLoginUser().getUserid());
		this.sysUser = getLoginUser();
		if (this.sysUser.getLoginname().equals("admin"))
			this.isadminer = true;
		else {
			Iterator<SysRole> roles = sysUser.getSysRoles().iterator();
			short maxroleid = 0;
			while (roles.hasNext()) {
				SysRole role = roles.next();
				if (maxroleid <= role.getRoleid())
					maxroleid = role.getRoleid();
			}
			if (maxroleid == 3) {// 3的角色id是律协管理员
				isadminer = true;

			}
			else if (maxroleid == 2) {
				isofficeadminer = true;

			}

		}

		// Calendar calendar = Calendar.getInstance();

		String nianshen = CommonDatas.SysParameter.get("nianshen").toString();
		// 起始时间是现在的时间基础上加1
		Date temp = dfdate.parse(year + "-" + nianshen);
		// 年审时间的基础上加1的毫秒值
		long time = temp.getTime() + 24 * 60 * 60 * 1000;
		// 得到这个时间
		temp.setTime(time);
		this.from = dfdate.format(temp);
		this.end = (year + 1) + "-" + nianshen;
		Date _from = temp;
		Date _end = dftime.parse(end + " 23:59:59");

		debug("起始时间:::" + _from + ",终止时间::::" + _end);

		DetachedCriteria detachedCriteria = null;

		// 这里改成sum的方式来做，projections的方式，到时改下
		if (sysUser.getRoleid() != null && sysUser.getRoleid().intValue() == 1) {// 只有是律师的时候才来查这个
//			detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class).add(Restrictions.eq("lawer.userid", this.getLoginUser().getUserid()))
////					.add(Restrictions.between("lastupdate", _from, _end));
//			.add(Restrictions.eq("theyear",year));
//			List jflist = basicService.findAllByCriteria(detachedCriteria);
//			int length = jflist == null ? 0 : jflist.size();
//			for (int i = 0; i < length; i++) {
//				Lawyerlessonxf xf = (Lawyerlessonxf) jflist.get(i);
//				nowxuefen += xf.getPxxf();
//			}
			nowxuefen=xfService.getYearTotal(this.getLoginUser().getUserid(), year);

		}// 如果是管理员的话，显示登录的信息
		if (isadminer) {// 对课程进行统计

//			detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
//			detachedCriteria.add(Restrictions.between("lastupdate", _from, _end));
//			detachedCriteria.setProjection(
//					Projections.projectionList().add(Projections.groupProperty("lawer")).add(Projections.sum("pxxf"))).createAlias("lawer", "lawer").add(Restrictions.eq("lawer.roleid", 1));
//			List list = basicService.findAllByCriteria(detachedCriteria);
//			String adminsql="select a.userid,sum(a.pxxf) from lawyerlessonxf a,sys_user b where a.userid=b.userid and b.roleid=1 and (UNIX_TIMESTAMP(a.lastupdate) between "+_from.getTime()/1000 + " and " + _end.getTime()/1000 + ") group by a.userid";
			String adminsql="select a.userid,sum(a.pxxf) from lawyerlessonxf a,sys_user b where a.userid=b.userid and b.roleid=1 and theyear="+year+" group by a.userid";

			List list=basicService.findBySqlQuery(adminsql);
			int length = list == null ? 0 : list.size();
			for (int i = 0; i < length; i++) {
				Object[] obj = (Object[]) list.get(i);
				float xuefen = Float.parseFloat(obj[1].toString());
				debug(obj[0] + "===" + obj[1]);
				if (xuefen >= needfen) {
					dabiaoshu++;
				}
				else {
					weidabiaoshu++;
				}
			}
			// 得到所有的律师数
			detachedCriteria = DetachedCriteria.forClass(SysUser.class).add(Restrictions.eq("roleid", 1)).setProjection(Projections.count("userid"));
			int lawyercnt = Integer.parseInt(basicService.findAllByCriteria(detachedCriteria).get(0).toString());
			this.weipeishun = lawyercnt - length;// 没有参加培训的人员

			// 新增现场培训
			detachedCriteria = DetachedCriteria.forClass(Lessons.class);
			detachedCriteria.add(Restrictions.ge("lessondate", from + " 00:00")).add(Restrictions.le("lessondate", end + " 23:59"));
			detachedCriteria.setProjection(org.hibernate.criterion.Projections.count("lessonid"));
			lessoncnt = Integer.parseInt(basicService.findAllByCriteria(detachedCriteria).get(0).toString());
			// 新增视频课件
			detachedCriteria = DetachedCriteria.forClass(Lessons.class);
			detachedCriteria.add(Restrictions.ge("lessondate", from + " 00:00")).add(Restrictions.le("lessondate", end + " 23:59")).add(
					Restrictions.isNotNull("onlinefile"));
			detachedCriteria.setProjection(org.hibernate.criterion.Projections.count("lessonid"));
			onlinelessoncnt = Integer.parseInt(basicService.findAllByCriteria(detachedCriteria).get(0).toString());

			// 现场课程培训人数，视频课程培训人数。
			// 感觉有问题，暂时先这样子，到时再说
//			detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class);
//			detachedCriteria.add(Restrictions.between("lastupdate", _from, _end));
//			detachedCriteria.createAlias("lawer", "lawer").add(Restrictions.eq("lawer.roleid", 1));
//			detachedCriteria.setProjection(Projections.projectionList().add(Projections.property("learnmode")).add(Projections.count("learnmode"))
//					.add(Projections.groupProperty("learnmode")));
//			String sql="select learnmode,count(learnmode) from (select a.learnmode,a.userid from lawyerlessonxf a inner join sys_user b on a.userid=b.userid where b.roleid=1 and (UNIX_TIMESTAMP(a.lastupdate) between "+_from.getTime()/1000+" and "+_end.getTime()/1000+") group by learnmode,userid) c group by c.learnmode";
			String sql="select learnmode,count(learnmode) from (select a.learnmode,a.userid from lawyerlessonxf a inner join sys_user b on a.userid=b.userid where b.roleid=1 and theyear="+year+" group by learnmode,userid) c group by c.learnmode";

			//			if(groupid!=-1){
//				sql="select learnmode,count(learnmode) from (select a.learnmode,a.userid from lawyerlessonxf a inner join sys_user b on a.userid=b.userid where b.roleid=1 and b.groupid="+groupid+" and (UNIX_TIMESTAMP(a.lastupdate) between "+_from.getTime()/1000+" and "+_end.getTime()/1000+")  group by learnmode,userid) c group by c.learnmode";
//			}
			tongjilist = basicService.findBySqlQuery(sql);
//			tongjilist = basicService.findAllByCriteria(detachedCriteria);
			int tongjilength = tongjilist == null ? 0 : tongjilist.size();
			for (int i = 0; i < tongjilength; i++) {
				Object[] obj = (Object[]) tongjilist.get(i);
				if (obj[0].equals("现场培训")) {
					this.localecnt = Integer.parseInt(obj[1].toString());
				}
				else if (obj[0].equals("在线视频")) {
					this.video = Integer.parseInt(obj[1].toString());
				}
				else if (obj[0].equals("文本课件")) {
					this.wenbenkejian = Integer.parseInt(obj[1].toString());
				}
				else if (obj[0].equals("积分补登")) {
					this.budeng = Integer.parseInt(obj[1].toString());
				}
			}
		}
		if (isofficeadminer) {// 事务所管理员

			this.group = this.getLoginUser().getSysGroup();
			detachedCriteria = DetachedCriteria.forClass(SysUser.class).add(Restrictions.eq("roleid", 1)).add(
					Restrictions.eq("sysGroup", this.getLoginUser().getSysGroup()));
			detachedCriteria.setProjection(Projections.rowCount());
			List counts = basicService.findAllByCriteria(detachedCriteria);
			if (counts != null && counts.size() != 0)
				this.groupusers = Integer.parseInt(counts.get(0).toString());

		}

		detachedCriteria = DetachedCriteria.forClass(Forum.class).add(Restrictions.eq("ismain", true)).add(Restrictions.eq("delflag", false));
		detachedCriteria.addOrder(Order.desc("lastupdate"));

		PaginationSupport page = basicService.findPageByCriteria(detachedCriteria, 10, 1);
		this.forumList = page.getItems();

		// 1是通知2是帮助
		detachedCriteria = DetachedCriteria.forClass(Articles.class).add(Restrictions.eq("status", 1)).add(Restrictions.eq("type", 2));
		detachedCriteria.addOrder(Order.asc("listorder"));
		page = basicService.findPageByCriteria(detachedCriteria, 10, 1);
		this.helpList = page.getItems();
		// 获取重要通知
		detachedCriteria = DetachedCriteria.forClass(Articles.class).add(Restrictions.eq("status", 1)).add(Restrictions.eq("type", 1)).add(
				Restrictions.eq("toshouye", true));
		detachedCriteria.addOrder(Order.desc("createtime"));
		List list = basicService.findAllByCriteria(detachedCriteria);
		if (list != null && list.size() != 0) {
			this.tongzhi = (Articles) list.get(0);
		}

		detachedCriteria = DetachedCriteria.forClass(Lessons.class);
		detachedCriteria.addOrder(Order.desc("createtime"));
		List _list = basicService.findAllByCriteria(detachedCriteria);
		LessonsService lessonsService = (LessonsService) getBean("lessonsService");

		// 不考虑报名了
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

		this.lessonList = new ArrayList();
		this.lessonMap = new HashMap();
		for (int i = 0; i < _list.size(); i++) {
			Lessons lessons = (Lessons) _list.get(i);
			if (i < 5) {
				// if (baominglist.contains(lessons.getLessonid())) {// 已经报名了
				// lessons.setBaominguser(1);
				// }
				this.lessonList.add(lessons);
			}
			this.lessonMap.put(lessons.getLessonid(), lessons.getTitle());
		}
		// this.lessonList=page.getItems();

		detachedCriteria = DetachedCriteria.forClass(Lessonscore.class);
		detachedCriteria.setProjection(Projections.projectionList().add(Projections.count("lessons"), "count").add(
				Projections.groupProperty("lessons")));
		detachedCriteria.addOrder(Order.desc("count"));
		detachedCriteria.add(Restrictions.ge("score", (byte) 2));
		page = basicService.findPageByCriteria(detachedCriteria, 10, 1);
		this.goodlessonList = page.getItems();
		return SUCCESS;
	}

	private int video;
	private int localecnt;
	private int budeng;
	private int wenbenkejian;

	public int getVideo() {
		return this.video;
	}

	public int getLocalecnt() {
		return this.localecnt;
	}

	public int getBudeng() {
		return this.budeng;
	}

	public int getWenbenkejian() {
		return this.wenbenkejian;
	}

	private SysGroup group;

	public SysGroup getGroup() {
		return this.group;
	}

	private int groupusers;

	public int getGroupusers() {
		return this.groupusers;
	}

	private List tongjilist;
	private int lessoncnt;
	private int onlinelessoncnt;

	public List getTongjilist() {
		return this.tongjilist;
	}

	public int getLessoncnt() {
		return this.lessoncnt;
	}

	public int getOnlinelessoncnt() {
		return this.onlinelessoncnt;
	}

	private float nowxuefen;

	public float getNowxuefen() {
		return this.nowxuefen;
	}

	private float needfen;

	public float getNeedfen() {
		return this.needfen;
	}

	private SysUser sysUser;

	public SysUser getSysUser() {

		return this.sysUser;
	}

	private String from;
	private String end;

	public String getFrom() {
		return this.from;
	}

	public String getEnd() {
		return this.end;
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

	private Map lessonMap;

	public Map getLessonMap() {
		return this.lessonMap;
	}

	private int year;

	public int getYear() {
		return this.year;
	}

	private boolean isadminer;

	public boolean getIsadminer() {
		return this.isadminer;
	}

	private boolean isofficeadminer;

	public boolean getIsofficeadminer() {
		return this.isofficeadminer;
	}

	private int dabiaoshu;
	private int weidabiaoshu;
	private int weipeishun;

	/**
	 * @return the dabiaoshu
	 */
	public int getDabiaoshu() {
		return dabiaoshu;
	}

	/**
	 * @return the weidabiaoshu
	 */
	public int getWeidabiaoshu() {
		return weidabiaoshu;
	}

	/**
	 * @return the weipeishun
	 */
	public int getWeipeishun() {
		return weipeishun;
	}
}