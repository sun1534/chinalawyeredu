package com.changpeng.sns.diary.service;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.util.HtmlUtil;
import com.changpeng.core.friend.model.CoreFriend;
import com.changpeng.core.model.CoreChannel;
import com.changpeng.core.service.FriendService;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.diary.model.SnsDiary;

/**
 * 2009年4月22 主贴Service
 * 
 * @author lenovo
 * 
 */
public class ProDiaryService extends BasicService {
//	private ProDiaryDAO proDiaryDAO;
	private UserService userService;
	private FriendService friendService;

	/**
	 * 根据当前登陆ID返回所有文章列表
	 * 
	 * @param userId
	 * @return
	 */
	public PaginationSupport getCurrProDiaryListByUserID(int userId,
			int pageSize, int pageNo) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(SnsDiary.class);

//		Userinfo user = (Userinfo) userService.get(Userinfo.class, userId);
		detachedCriteria.add(Restrictions.eq("userid", userId));
		detachedCriteria.addOrder(Order.desc("createTime"));
		return this.findPageOnPageNo(detachedCriteria, pageSize, pageNo);
		// return
		// proDiaryDAO.findPageByCriteria(detachedCriteria,pageSize,pageNo);
	}

	/**
	 * 根据当前登陆用户ID和主贴ID
	 * 
	 * @param userId
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginationSupport getCurrProDiaryListByUserIDadnDiaryId(int userId,
			int diaryid, int pageSize, int pageNo) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(SnsDiary.class);

//		Userinfo user = (Userinfo) userService.get(Userinfo.class, userId);
		detachedCriteria.add(Restrictions.eq("diaryid", diaryid));
		detachedCriteria.add(Restrictions.eq("userid", userId));
		detachedCriteria.addOrder(Order.desc("createTime"));

		return basicDAO.findPageOnPageNo(detachedCriteria, pageSize,pageNo);

	}

	/**
	 * 根据HQL语句返回一个ProDiary对象
	 * 
	 * @param hql
	 * @return
	 */
	public SnsDiary getProDiaryBySqlandDiaryid(int proDiaryId, int userid) {
		// String sql="FROM SnsDiary WHERE diaryid>'"+proDiaryId+"' limit 1";
		String hql = "FROM SnsDiary WHERE diaryid=" + proDiaryId
				+ " and userid=" + userid;

		List list = basicDAO.findByQuery(hql);
		SnsDiary diary = new SnsDiary();
		if (null != list && list.size() > 0) {
			diary = (SnsDiary) list.get(0);
			// 更新主贴点击数
			int click = diary.getClickCount();
			diary.setClickCount(click + 1);

//			String sqlUpdate = "UPDATE SnsDiary SET clicknumber=clicknumber+1 WHERE diaryid="
//					+ proDiaryId;
//			proDiaryDAO.updateProDiary(sqlUpdate);
			basicDAO.update(diary);
		}
		return diary;
	}

	public SnsDiary getProDiaryBySql(int proDiaryId, int falg, int userid) {
		// String sql="FROM SnsDiary WHERE diaryid>'"+proDiaryId+"' limit 1";
		System.out.println(falg);
		String sql = "FROM SnsDiary WHERE userid=" + userid
				+ "  and  diaryid<" + proDiaryId + " ORDER BY createTime DESC";
		if (falg == 1) {
			sql = "FROM SnsDiary WHERE userid=" + userid
					+ "  and diaryid>" + proDiaryId + " ORDER BY createTime ASC";
			falg = 0;
		}

		System.out.println(sql);
		List list = basicDAO.findByQuery(sql);

		SnsDiary diary = (SnsDiary) list.get(0);
		System.out.println(diary.getDiaryid());
		return diary;

	}

	/**
	 * 返回主贴表的第一条记录
	 * 
	 * @return
	 */
	public SnsDiary getProDiaryFirstBySql(int userid) {
		String sql = "FROM SnsDiary where userid=" + userid
				+ "  ORDER BY createTime ASC";
		List list = basicDAO.findByQuery(sql);

		SnsDiary diary = (SnsDiary) list.get(0);
		System.out.println(diary.getDiaryid());
		return diary;

	}

	/**
	 * 返回最后一条记录
	 * 
	 * @return
	 */
	public SnsDiary getProDiaryLastBysql(int userid) {
		String sql = "FROM SnsDiary where userid=" + userid
				+ " ORDER BY createTime DESC";
		List list = basicDAO.findByQuery(sql);

		SnsDiary diary = (SnsDiary) list.get(0);
		System.out.println(diary.getDiaryid());
		return diary;
	}

	/**
	 * 查询好友的博客
	 * 
	 * @param userId
	 * @return
	 */
	public List<SnsDiary> getViewDiaryListByUserID(int userId) {
		String sql = "FROM SnsDiary p where p.userid=" + userId + " order by createTime desc";
		List<SnsDiary> diaryList = this.findByQuery(sql);
		return diaryList;
	}
	
	
	public List<SnsDiary> getViewDiaryListByUserID(int userId,int count) {
		String sql = "FROM SnsDiary p where  p.userid=" + userId
				+ " and statusid=0 order by createTime desc limit "+count;
		List<SnsDiary> diaryList = this.findByQuery(sql);
		return diaryList;
	}
	
	/**
	 * 保存博客
	 */
	public void saveProDiary(SnsDiary diary, String username) {
		super.saveOrupdate(diary);

		// dynamicMessageService.insertDynamicmessage(3, 3, diary.getDiaryid(),
		// diary.getTitle(), "url", sumary, null,user);
		//   
		//发送动态
		String counton="";
		counton=diary.getContent();
		counton = HtmlUtil.Html2Text(counton);
			if(counton.length()>80){
				counton = counton.substring(0, 80)+"...";
			}
			else{
				counton = HtmlUtil.Html2Text(counton);
			}
		 String actionContent="1="+username+";2="+diary.getDiaryid()+";3="+diary.getTile()+";4="+counton+";";
	}

	/**
	 * 删除博客
	 * 
	 * @param diaryId
	 */
	public void deleteProDiary(int diaryId) {
//		SnsDiary diary = new SnsDiary();
//		diary.setDiaryid(diaryId);
		SnsDiary diary =(SnsDiary)this.get(SnsDiary.class, diaryId);
		basicDAO.delete(diary);
	}

	/**
	 * 更新博客
	 * 
	 * @param diary
	 */
	public void updateProDiary(String sql) {
		basicDAO.executeSql(sql);
	}

	/**
	 * 查询博客
	 * 
	 * @param diary
	 */
	public SnsDiary findProDiary(int diaryId) {
		SnsDiary diary = (SnsDiary)this.get(SnsDiary.class, diaryId);
		int count = diary.getClickCount();
		diary.setClickCount(count + 1);
		this.update(diary);
		return diary;
	}

	/**
	 * 根据主贴ID，返回主贴列表
	 * 
	 * @param diaryId
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginationSupport getProDiaryListByDiaryId(int diaryId,
			int pageSize, int pageNo) {
		DetachedCriteria detacherCriteria = DetachedCriteria
				.forClass(SnsDiary.class);
		detacherCriteria.add(Restrictions.eq("diaryid", diaryId));
		detacherCriteria.addOrder(Order.desc("createTime"));
		PaginationSupport page = this.findPageOnPageNo(detacherCriteria,
				pageSize, pageNo);

		return page;
	}

	/**
	 * 根据指定页的行数和页码返回数据。
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PaginationSupport getProDiaryListByDiaryId(int pageSize, int pageNo) {
		DetachedCriteria detacherCriteria = DetachedCriteria
				.forClass(SnsDiary.class);
		detacherCriteria.addOrder(Order.desc("createTime"));
		PaginationSupport page = this.findPageOnPageNo(detacherCriteria,
				pageSize, pageNo);
		return page;
	}

	public List getChannels() {
		DetachedCriteria detacherCriteria = DetachedCriteria.forClass(CoreChannel.class);
		detacherCriteria.add(Restrictions.eq("status", (short)1));
		return this.findByCriteria(detacherCriteria, 100);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public FriendService getFriendService() {
		return friendService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}
	
	
	
	
	public PaginationSupport getViewProDiaryListByUserID(int userId,
			int pageSize, int pageNo) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(SnsDiary.class);

//		Userinfo user = (Userinfo) userService.get(Userinfo.class, userId);
		detachedCriteria.add(Restrictions.eq("userid", userId));
		detachedCriteria.add(Restrictions.eq("statusid", (short)0));
		detachedCriteria.addOrder(Order.desc("createTime"));
		return this.findPageOnPageNo(detachedCriteria, pageSize, pageNo);
	}

}
