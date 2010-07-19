/**
 * LessonsService.java
 */

package com.changpeng.lessons.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.lessons.action.LessonStatic;
import com.changpeng.lessons.dao.LessonsDAO;
import com.changpeng.lessons.util.Lessonstatics;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonscore;
import com.changpeng.models.Lessonshared;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUser;

/**
 * @author 华锋 2008-5-16 上午10:55:55
 * 
 */
public class LessonsService extends BasicService {
	private static Log _LOG = LogFactory.getLog(LessonsService.class);
	private LessonsDAO lessonsDAO;

	// private PlatformTransactionManager transactionManager;

	// public void setTransactionManager(PlatformTransactionManager
	// transactionManager) {
	// this.transactionManager = transactionManager;
	// }

	/**
	 * @param lessonsDAO
	 *            the lessonsDAO to set
	 */
	public void setLessonsDAO(LessonsDAO lessonsDAO) {
		this.lessonsDAO = lessonsDAO;
	}

	public Lessonscore getScorebyLessonUser(long userid, int lessonid) throws ServiceException {
		try {
			return lessonsDAO.getScorebyLessonUser(userid, lessonid);
		} catch (Exception e) {

			throw new ServiceException(e);
		}
	}
	/**
	 * 课程听课的统计情况
	 * @param teacherid
	 * @param title
	 * @param from
	 * @param to
	 * @return
	 */
	public com.changpeng.common.PaginationSupport getLessonStatic(int teacherid,String title,Timestamp from,Timestamp to,int pageNo,int pageSize) {
		int startIndex=(pageNo-1)*pageSize;
		String where=" where 1=1 ";		
		if(teacherid!=0){
			where+=" and teacherid="+teacherid;
		}		
		if(title!=null&&!title.equals("")){
			where+=" and title like '%"+title+"%'";
		}
		String sql="select count(lessonid) as cnt,lessonid from log_lesson_listen "+where+" group by lessonid";
		String totalsql="select count(a.lessonid) from ("+sql+") a";
		String pagesql="select a.* from ("+sql+") a order by a.cnt desc limit "+startIndex+","+pageSize;
		_LOG.debug("pageSql:::"+pagesql);
		_LOG.debug("totalsql:::"+totalsql);
		int totalCount=lessonsDAO.getCountBySqlQuery(totalsql);
		
		List list=lessonsDAO.findBySqlQuery(pagesql);
		int len=list==null?0:list.size();
		List result=new ArrayList();
		for(int i=0;i<len;i++){
			Object[] obj=(Object[])list.get(i);
			LessonStatic lstatic=new LessonStatic();
			lstatic.setLessonid(((Integer)obj[1]).intValue());
			lstatic.setCount(((BigInteger)obj[0]).intValue());
			result.add(lstatic);
		}
		
		if(list!=null){
			list.clear();
			list=null;
		}
		PaginationSupport ps = new PaginationSupport(result, totalCount, pageSize, startIndex);
		return ps;
	}
	

	@Transactional
	public void saveLesson(Lessons lesson, List groupids, SysUser user) throws ServiceException {
		lessonsDAO.save(lesson);
		
		if(lesson.getTeacherid()!=0){
			String sql="update teacher set lessoncount=lessoncount+1 where userid="+lesson.getTeacherid();
			lessonsDAO.executeSql(sql);
		}

		// 保存共享的情况
	
			for (int i = 0;groupids != null&& i < groupids.size(); i++) {
				com.changpeng.models.Lessonshared share = new Lessonshared();
				share.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				share.setCreateuserid(user.getUserid());
				share.setCreateusername(user.getUsername());
				share.setLessons(lesson);
				share.setGroupid(Integer.parseInt(groupids.get(i).toString()));
				share.setXuefen(lesson.getXuefen());

				lessonsDAO.save(share);
			}
		
	}
/**
 * 这里不考虑共享的变化情况
 * @param lesson
 * @param groupids
 * @param user
 * @throws ServiceException
 */
	@Transactional
	public void updateLesson(Lessons lesson, List groupids, SysUser user) throws ServiceException {
		lessonsDAO.update(lesson);
//		String hql = "delete from com.changpeng.models.Lessonshared share where share.lessons.lessonid=?";
//		lessonsDAO.execute(hql, lesson.getLessonid());
//		// 保存共享的情况
//		if (groupids != null && groupids.size() > 0) {
//			for (int i = 0; i < groupids.size(); i++) {
//				com.changpeng.models.Lessonshared share = new Lessonshared();
//				share.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
//				share.setCreateuserid(user.getUserid());
//				share.setCreateusername(user.getUsername());
//				share.setLessons(lesson);
//				share.setGroupid(Integer.parseInt(groupids.get(i).toString()));
//				share.setXuefen(lesson.getXuefen());
//
//				lessonsDAO.save(share);
//			}
//		}
	}

	/**
	 * 修改课程的共享情况
	 * 
	 * @param lesson
	 * @param user
	 * @throws ServiceException
	 */
	@Transactional
	public void updateLessonShared(Lessons lesson, List groupids, SysUser user) throws ServiceException {
		// 先清除掉原有的共享
		String hql = "delete from com.changpeng.models.Lessonshared share where share.lessons.lessonid=?";
		lessonsDAO.execute(hql, lesson.getLessonid());

		// 再保存共享的情况
		if (groupids != null && groupids.size() > 0) {
			for (int i = 0; i < groupids.size(); i++) {
				com.changpeng.models.Lessonshared share = new Lessonshared();
				share.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				share.setCreateuserid(user.getUserid());
				share.setCreateusername(user.getUsername());
				share.setLessons(lesson);
				share.setGroupid(Integer.parseInt(groupids.get(i).toString()));
				share.setXuefen(lesson.getXuefen());

				lessonsDAO.save(share);
			}
		}
	}

	/**
	 * 建立课程的共享
	 * 
	 * @param lesson
	 * @param groupids
	 * @param user
	 */
	public void createLessonShare(Lessons lesson, List groupids, SysUser user) {
		// 保存共享的情况
		if (groupids != null && groupids.size() > 0) {
			for (int i = 0; i < groupids.size(); i++) {
				com.changpeng.models.Lessonshared share = new Lessonshared();
				share.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				share.setCreateuserid(user.getUserid());
				share.setCreateusername(user.getUsername());
				share.setLessons(lesson);
				share.setGroupid(Integer.parseInt(groupids.get(i).toString()));
				share.setXuefen(lesson.getXuefen());

				lessonsDAO.save(share);
			}
		}
	}

	public Lessonstatics getFiledLessons(Timestamp _from, Timestamp _end, String field, int fieldvalue)
			throws ServiceException {

		String sql = "";

		if (field != null && !field.equals("")) {
			sql = "select lessonstyle,count(lessonstyle) from lessons where deleteflag=0 and (UNIX_TIMESTAMP(createtime) between "
					+ _from.getTime() / 1000 + " and " + _end.getTime() / 1000 + ") and " + field + "=" + fieldvalue
					+ " group by lessonstyle";
		} else {
			sql = "select lessonstyle,count(lessonstyle) from lessons where deleteflag=0 and (UNIX_TIMESTAMP(createtime) between "
					+ _from.getTime() / 1000 + " and " + _end.getTime() / 1000 + ") group by lessonstyle";

		}
		
		_LOG.debug("getFiledLessons:"+sql);
		Lessonstatics statics = new Lessonstatics();

		List tongjilist = lessonsDAO.findBySqlQuery(sql);
		int tongjilength = tongjilist == null ? 0 : tongjilist.size();
		// 1现场课程2在线培训课程3现场和在线的合并4文本课件5补登的积分',

		for (int i = 0; i < tongjilength; i++) {
			Object[] obj = (Object[]) tongjilist.get(i);
			int style = Integer.parseInt(obj[0].toString());
			if (style == 1) {
				statics.setLocal(Integer.parseInt(obj[1].toString()));
			} else if (style == 2) {
				statics.setOnline(Integer.parseInt(obj[1].toString()));
			} else if (style == 3) {
				statics.setOnlineandlocal(Integer.parseInt(obj[1].toString()));
			} else if (style == 4) {
				statics.setWenbenkejian(Integer.parseInt(obj[1].toString()));
			}
		}

		statics.setLocal(statics.getLocal() + statics.getOnlineandlocal());
		statics.setOnline(statics.getOnline() + statics.getOnlineandlocal());

		return statics;
	}
	private static final DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
	public com.changpeng.common.PaginationSupport getPages(SysGroup mygroup, int groupid,int audioQuality,int videoQuality,int onlinetype, int lessonstyle,
			int lessontype, String title, String teachers, int pageSize, int pageNo,Timestamp start,Timestamp end) {

		// 现场课程的话，如果我是admin或者group>3，则显示所有的现场课程，否则的话，
		// 我是省的话，显示本省的所有，市的话，只显示市的
		// 1是显示本地的本地课程，100是显示外地的本地课程
		if (lessonstyle == 1||lessonstyle==100) {
		
			
//			DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class).add(Restrictions.eq("deleteflag", false));
			DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class);
			if (mygroup != null && mygroup.getGrouptype() <= 3) {
				if (mygroup.getGrouptype() == 1) { // 无权限
					dc.add(Restrictions.eq("provinceid", -1));
				} else if (mygroup.getGrouptype() == 2) {
					//自己干的和省律协干的
					dc.add(Restrictions.or(Restrictions.eq("cityid", mygroup.getGroupid()), Restrictions.eq("groupid", mygroup.getParentid())));
				} else {
					dc.add(Restrictions.eq("provinceid", mygroup.getGroupid()));
				}
			}
		
			if (title != null && !"".equals(title)) {
				dc.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
			}
			if (teachers != null && !"".equals(teachers)) {
				dc.add(Restrictions.like("teachers", teachers, MatchMode.ANYWHERE));
			}
			if (lessontype != 0) {
				dc.add(Restrictions.eq("lessontype", lessontype));
			}
			if (onlinetype != -1) {
				dc.add(Restrictions.eq("onlineType", onlinetype));
			}if (audioQuality != -1) {
				dc.add(Restrictions.eq("audioQuality", audioQuality));
			}if (videoQuality != -1) {
				dc.add(Restrictions.eq("videoQuality", videoQuality));
			}
			if (groupid != -1) {
//				dc.add(Restrictions.eq("lessons.groupid", groupid));
				dc.add(Restrictions.or(Restrictions.eq("groupid", groupid), Restrictions.eq("provinceid", groupid)));
//				hql += " and a.lessons.groupid=" + groupid;
			}
			if(start!=null&&end!=null)
				dc.add(Restrictions.between("lessondate", start, end));
			dc.add(Restrictions.in("lessonstyle", new Integer[] { 1, 3 }));
			dc.addOrder(Order.desc("lessondate"));
			dc.addOrder(Order.desc("lessonid"));
			PaginationSupport page = lessonsDAO.findPageByCriteria(dc, pageSize, pageNo);
			return page;
		} else {

			DetachedCriteria dc = DetachedCriteria.forClass(Lessonshared.class);
			dc.createAlias("lessons", "lessons");
			String hql = "select distinct a.lessons from Lessonshared a where 1=1 ";

			if (mygroup != null && mygroup.getGrouptype() <= 3) {// mygroup为null的话,能看到所有律协的
				List<Integer> groupids = new ArrayList<Integer>();
				String str = "";
				if (mygroup.getGrouptype() == 1) {
					groupids.add(mygroup.getGroupid());
					groupids.add(mygroup.getParentid());
					groupids.add(mygroup.getDirectgroup());
					str = mygroup.getGroupid() + "," + mygroup.getParentid() + "," + mygroup.getDirectgroup();
					dc.add(Restrictions.in("groupid", groupids));
					hql += " and a.groupid in(" + str + ")";
				} else if (mygroup.getGrouptype() == 2) {
					groupids.add(mygroup.getGroupid());
					groupids.add(mygroup.getParentid());
					str = mygroup.getGroupid() + "," + mygroup.getParentid();
					dc.add(Restrictions.in("groupid", groupids));
					hql += " and a.groupid in(" + str + ")";
				} else if (mygroup.getGrouptype() == 3) {//省律协的,我省id是这个就行了吧
					groupids.add(mygroup.getGroupid());
					str = mygroup.getGroupid() + "";
					
					dc.add(Restrictions.or(Restrictions.in("groupid", groupids), Restrictions.eq("lessons.provinceid", mygroup.getGroupid())));
					hql += " and (a.groupid in(" + str + ") or a.lessons.provinceid =" +  mygroup.getGroupid() + ")";
					
				}
			
			}
			// 不显示删除的
//			dc.add(Restrictions.eq("lessons.deleteflag", false));
//			hql += " and a.lessons.deleteflag=false";
			// 具体的来源
			if (groupid != -1) {
//				dc.add(Restrictions.eq("lessons.groupid", groupid));
				dc.add(Restrictions.or(Restrictions.eq("lessons.groupid", groupid), Restrictions.eq("lessons.provinceid", groupid)));
				
				hql += " and (a.lessons.groupid=" + groupid+" or a.lessons.provinceid="+groupid+")";
			}

			if (title != null && !"".equals(title)) {
				dc.add(Restrictions.like("lessons.title", title, MatchMode.ANYWHERE));
				hql += " and a.lessons.title like '%" + title + "%'";
			}
			if (audioQuality != -1) {
				dc.add(Restrictions.eq("lessons.audioQuality",audioQuality ));
				hql += " and a.lessons.audioQuality = " + audioQuality ;
			}if (videoQuality != -1) {
				dc.add(Restrictions.eq("lessons.videoQuality",videoQuality ));
				hql += " and a.lessons.videoQuality = " + videoQuality ;
			}
			
			if (onlinetype != -1) {
				dc.add(Restrictions.eq("lessons.onlineType",onlinetype ));
				hql += " and a.lessons.onlineType = " + onlinetype ;
			}
			if (teachers != null && !"".equals(teachers)) {
				dc.add(Restrictions.like("lessons.teachers", teachers, MatchMode.ANYWHERE));
				hql += " and a.lessons.teachers like '%" + teachers + "%'";
			}
			if (lessontype != 0) {
				dc.add(Restrictions.eq("lessons.lessontype", lessontype));
				hql += " and a.lessons.lessontype =" + lessontype;
			}
			if (lessonstyle != 0) {
//				if (lessonstyle == 1 || lessonstyle == 2)
					dc.add(Restrictions.in("lessons.lessonstyle", new Object[] { lessonstyle, 3 }));
//				else
//					dc.add(Restrictions.eq("lessons.lessonstyle", lessonstyle));
				hql += " and a.lessons.lessonstyle in(" + lessonstyle + ",3)";
			}
			
			if(start!=null&&end!=null){
				String startStr=df.format(start)+" 00:00:00";
				String endStr=df.format(end)+" 23:59:59";
				dc.add(Restrictions.between("lessons.lessondate", start, end));
//				dc.add(Restrictions.between("lessons.lessondate", start, end));
				hql += " and a.lessons.lessondate between '"+startStr+"'and '"+endStr+"'";
			}
			
//			hql += " order by a.lessons.lessondate,a.lessons.lessonid desc";
			hql += " order by a.lessons.lessondate desc,a.lessons.lessonid desc";
		
			dc.setProjection(Projections.countDistinct("lessons"));
//			dc.addOrder(Order.desc("lessons.lessondate"));
//			dc.addOrder(Order.desc("lessons.lessonid"));
			List list = lessonsDAO.findAllByCriteria(dc);
			int len = list == null ? 0 : list.size();
			int totalCount = len == 0 ? 0 : Integer.parseInt(list.get(0).toString());
			// 根据授课时间进行排序
			// detachedCriteria.addOrder(Order.desc("lessons.lessondate"));
			PaginationSupport page = lessonsDAO.findByQuery(hql, pageSize, pageNo, totalCount);
			return page;
		}
	}

}
