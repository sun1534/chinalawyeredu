package com.changpeng.core.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.util.UserUtil;
import com.changpeng.core.friend.model.CoreFriend;
import com.changpeng.core.friend.model.CoreFriendApply;
import com.changpeng.core.friend.model.CoreFriendCategory;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.Userinfo;

/**
 * 
 * 好友和好友组的服务层
 * 
 * @author 华锋 May 17, 2009 5:43:17 PM
 * 
 */
public class FriendService extends BasicService {
	Logger log = Logger.getLogger(FriendService.class);

	public SystemMessageService systemMessageService;
	public UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setSystemMessageService(SystemMessageService systemMessageService) {
		this.systemMessageService = systemMessageService;
	}

	public boolean isFriendWithThem(int userid, int friendid) {
		String hql = "from CoreFriend where userid=" + userid + " and friendUserid=" + friendid;
		List list = basicDAO.findByQuery(hql);
		if (list != null && list.size() != 0)
			return true;
		return false;
	}

	/**
	 * 得到我跟这个人的关系数据
	 * @param userid
	 * @param friendid
	 * @return
	 */
	public CoreFriend getTheFriend(int userid,int friendid){
		String hql = "from CoreFriend where userid=" + userid + " and friendUserid=" + friendid;
		List list = basicDAO.findByQuery(hql);
		if (list != null && list.size() != 0)
			return (CoreFriend)list.get(0);
		return null;
	}
	
	/**
	 * 得到我这个人的好友数
	 * 
	 * @param userid
	 * @return
	 */
	public int getMyFriendCount(int userid) {
		List useridlist = findByQuery(" select count(*) as cnt from CoreFriend u where u.userid=" + userid);
		return Integer.parseInt(useridlist.get(0).toString());
	}

	
	/**
	 * 根据好友分组情况显示我的好友分类列表，不需要班级id
	 * 
	 * @param userid
	 *            相当于currentuserid
	 * @param categoryid
	 *            用户选中的那个categoryid
	 * @return
	 */
	public PaginationSupport getMyFriendsByCategory(int userid, int categoryid, int pageNo, int pageSize) {
		if (pageNo <= 0)
			pageNo = 1;
		int startIndex = (pageNo - 1) * pageSize;
		String sql = "select a.id,a.userid,a.category_Id,a.friend_Userid from core_friend a where a.category_id="+ categoryid + " and a.userid=" + userid + " limit " + startIndex + "," + pageSize;
		List list = basicDAO.findBySqlQuery(sql);
		int len = list == null ? 0 : list.size();
		List resultlist = new ArrayList();
		for (int i = 0; i < len; i++) {
			CoreFriend friend = new CoreFriend();
			Object[] obj = (Object[]) list.get(i);
			friend.setId(Integer.parseInt(obj[0].toString()));
			friend.setUserid(userid);

			friend.setCategoryId(obj[3] == null ? 0 : Integer.parseInt(obj[3].toString()));
			friend.setFriendUserid(Integer.parseInt(obj[4].toString()));
			friend.setFriendUserRole(obj[5] == null ? 0 : Short.parseShort(obj[5].toString()));
			
			resultlist.add(friend);
		}

		String cntsql = "select count(*) as cnt from core_friend where category_id=" + categoryid + " and userid=" + userid;
		List cntlist = basicDAO.findBySqlQuery(cntsql);
		int count = Integer.parseInt(cntlist.get(0).toString());

		// String queryString = "from CoreFriend a where a.userid=" + userid;
		PaginationSupport page = new PaginationSupport(resultlist, count, pageSize, startIndex);

		return page;

	}

	/**
	 * 得到俺的好友啊(没有班级和学校以及姓名pic等咯)，个数限制为count个
	 * 
	 * 
	 * @param userid
	 * @param count
	 * @return
	 */
	public List getMyFriends(int userid, int count) {
		// String sql = "select
		// a.id,b.user_name,b.pic,a.friend_user_role,a.category_id,a.friend_Userid,a.friend_User_Role
		// from core_friend a inner join core_userinfo b on a.friend_userid=b.id
		// where a.userid="
		// + userid + " limit " + count;
		String sql = "select a.id,a.userid,a.friend_Userid,a.category_id from core_friend a where a.userid=" + userid
				+ " limit " + count;
		List list = basicDAO.findBySqlQuery(sql);
		List frientlist = new ArrayList();
		int len = list == null ? 0 : list.size();
		for (int i = 0; i < len; i++) {
			CoreFriend friend = new CoreFriend();
			Object[] obj = (Object[]) list.get(i);
			friend.setId(Integer.parseInt(obj[0].toString()));
			// friend.setUsername(obj[1].toString());
			// friend.setPic((String)obj[2]);
			friend.setUserid(obj[1] == null ? 0 : Integer.parseInt(obj[1].toString()));
			friend.setFriendUserid(obj[3] == null ? 0 : Integer.parseInt(obj[3].toString()));
			friend.setCategoryId(obj[5] == null ? 0 : Integer.parseInt(obj[5].toString()));

			frientlist.add(friend);
		}
		return frientlist;
	}

	/**
	 * 
	 * 我的好友的分类显示，这里不显示班级名
	 * 
	 * @param userid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	// public PaginationSupport getMyFriendsWithNoClasses(int userid, int
	// pageNo, int pageSize) {
	public PaginationSupport getMyFriends(int userid, int pageSize ,int pageNo) {

		if (pageNo <= 0)
			pageNo = 1;
		int startIndex = (pageNo - 1) * pageSize;
		
		// String sql = "select
		// a.id,b.user_name,b.pic,a.friend_user_role,a.category_id from
		// core_friend a inner join core_userinfo b on a.friend_userid=b.id
		// where a.userid="
		// + userid + " limit " + startIndex + "," + pageSize;
		String sql = "select a.id,a.userid,a.friend_Userid,a.category_id from core_friend a where a.userid=" + userid
				+ " limit " + startIndex + "," + pageSize;
		// String cntsql = "select count(a.id) as cnt from core_friend a inner
		// join core_userinfo b on a.friend_userid=b.id where a.userid=" +
		// userid;
		String cntsql = "select count(a.id) as cnt from core_friend a where a.userid=" + userid;

		int count = Integer.parseInt(basicDAO.findBySqlQuery(cntsql).get(0).toString());

		List list = basicDAO.findBySqlQuery(sql);
		List frientlist = new ArrayList();
		int len = list == null ? 0 : list.size();
		for (int i = 0; i < len; i++) {
			CoreFriend friend = new CoreFriend();
			Object[] obj = (Object[]) list.get(i);
			friend.setId(Integer.parseInt(obj[0].toString()));
			// friend.setUsername(obj[1].toString());
			// friend.setPic((String)obj[2]);
			friend.setUserid(obj[1] == null ? 0 : Integer.parseInt(obj[1].toString()));
			friend.setFriendUserid(obj[3] == null ? 0 : Integer.parseInt(obj[3].toString()));
			friend.setFriendUserRole(obj[4] == null ? 0 : Short.parseShort(obj[4].toString()));
			friend.setCategoryId(obj[5] == null ? 0 : Integer.parseInt(obj[5].toString()));
			frientlist.add(friend);
		}
		PaginationSupport page = new PaginationSupport(frientlist, count, pageSize, startIndex);
		return page;
	}

	/**
	 * <pre>
	 * 获取我的所有好友分页列表，这里要显示学校和班级啊。。。。
	 * 
	 * 这里还是同上
	 * </pre>
	 * @param userid
	 * @param size
	 * @param pageno
	 * @return
	 */
//	public PaginationSupport getMyFriendsWithClasses(int userid, int size, int pageno) {
//		if (pageno <= 0)
//			pageno = 1;
//		int startIndex = (pageno - 1) * size;
//
//		String sql = "select a.id,a.userid,a.user_role,a.category_Id,a.friend_Userid,a.friend_user_role,b.class_id,b.school_id from core_friend a left join core_class_member b on a.friend_Userid=b.userid where a.userid="
//				+ userid + " limit " + startIndex + "," + size;
//
//		List list = basicDAO.findBySqlQuery(sql);
//		int len = list == null ? 0 : list.size();
//
//		List resultlist = new ArrayList();
//		for (int i = 0; i < len; i++) {
//			CoreFriend friend = new CoreFriend();
//			Object[] obj = (Object[]) list.get(i);
//			friend.setId(Integer.parseInt(obj[0].toString()));
//			friend.setUserid(userid);
//			friend.setUserRole(obj[2] == null ? (short) 0 : Short.parseShort(obj[2].toString()));
//			friend.setCategoryId(obj[3] == null ? 0 : Integer.parseInt(obj[3].toString()));
//			friend.setFriendUserid(Integer.parseInt(obj[4].toString()));
//			friend.setFriendUserRole(obj[5] == null ? 0 : Short.parseShort(obj[5].toString()));
//			friend.setSchoolid(obj[6] == null ? 0 : Integer.parseInt(obj[6].toString()));
//			friend.setClassid(obj[7] == null ? 0 : Integer.parseInt(obj[7].toString()));
//			resultlist.add(friend);
//		}
//
//		String cntsql = "select count(*) as cnt from core_friend where userid=" + userid;
//		List cntlist = basicDAO.findBySqlQuery(cntsql);
//		int count = Integer.parseInt(cntlist.get(0).toString());
//
//		// String queryString = "from CoreFriend a where a.userid=" + userid;
//		PaginationSupport page = new PaginationSupport(resultlist, count, size, startIndex);
//
//		return page;
//	}

	/**
	 * @方法描述：获取好友用户ID
	 * @param userid
	 * @return list
	 */
	public List getMyFriendOnlyUids(int userid) {
		List useridlist = findByQuery(" select friendUserid from CoreFriend u where u.userid=" + userid);
		return useridlist;
	}

	
	/**
	 * 添加自定义分组
	 * 
	 * @param userid
	 * @param userrole
	 * @param groupsname
	 * 
	 * @return
	 */
	public boolean addGroup(int userid, String groupsname) {
		// Userinfo user = this.userinfoDAO.get(userid);
		// Friendgroup group = new Friendgroup();

		CoreFriendCategory category = new CoreFriendCategory();
		category.setName(groupsname);
		category.setTimeCreate(new java.sql.Timestamp(System.currentTimeMillis()));
		category.setUserid(userid);
		category.setSort((short) 0);
		basicDAO.save(category);

		return true;
	}

	/**
	 * 编辑分组
	 * 
	 * @param userid
	 * @param groupid
	 * @param groupname
	 * @return
	 */
	public boolean editGroup(int categoryid, String groupname) {

		String hql = "update CoreFriendCategory c set c.name='" + groupname + "' where c.id=" + categoryid;
		basicDAO.execute(hql);

		return true;
	}

	/**
	 * 删除分组,删除这个分组的话,则将core_friend中对应的categoryid设置为0
	 * 
	 * @param userid
	 * @param groupid
	 * @return
	 */
	@Transactional
	public boolean delGroup(int categoryid) {

		String hql = "update CoreFriend c set c.categoryId=0 where c.categoryId=" + categoryid;

		basicDAO.execute(hql);

		hql = "delete CoreFriendCategory a where a.id=" + categoryid;
		basicDAO.execute(hql);

		return true;
	}

	/**
	 * 修改好友分组情况（分组，备注） 将原有分组的好友数减1，新组的好友数加1
	 * 
	 * @param userid
	 * @param memberid
	 * @param groupid
	 * @param remarks
	 * @return
	 */
	@Transactional
	public boolean updateFriendCategory(int corefriendid,int newgroupid) {
		CoreFriend corefriend = (CoreFriend)this.get(CoreFriend.class, corefriendid);
		if(corefriend != null){	
			int oldcategory = corefriend.getCategoryId();
			if (oldcategory != 0) {
				String hql = "update CoreFriendCategory set friendCount=friendCount-1 where id=" + oldcategory;
				basicDAO.execute(hql);
			}
			String hql = "update CoreFriendCategory set friendCount=friendCount+1 where id=" + newgroupid;
			basicDAO.execute(hql);
			hql = "update CoreFriend set categoryId=" + newgroupid + " where id=" + corefriendid;
			basicDAO.execute(hql);
		}
		return true;
	}

	/**
	 * 删除好友.同时将这个好友所属组的人员数减1
	 * 
	 * @param userid
	 *            相当于currentUserid
	 * @param friendid
	 *            好友的id
	 */
	@Transactional
	public boolean deleteFriend(int userid, int friendid) {

		String myhql = "from CoreFriend where userid=" + userid + " and friendUserid=" + friendid;

		String friendhql = "from CoreFriend where friendUserid=" + userid + " and userid=" + friendid;

		List list = basicDAO.findByQuery(myhql);
		List friendlis = basicDAO.findByQuery(friendhql);

		CoreFriend myfriend = list == null ? null : (CoreFriend) list.get(0);
		CoreFriend friendfriend = list == null ? null : (CoreFriend) friendlis.get(0);

		String updhql = "update CoreFriendCategory set friendCount=friendCount-1 where id=" + myfriend.getCategoryId();
		String updfriendhql = "update CoreFriendCategory set friendCount=friendCount-1 where id=" + friendfriend.getCategoryId();

		basicDAO.execute(updhql);
		basicDAO.execute(updfriendhql);

		basicDAO.delete(friendfriend);
		basicDAO.delete(myfriend);

		String delapplyHql = "delete from CoreFriendApply where  activeUserid=" + userid + " and passiveUserid=" + friendid;
		String delapplyFriendHql = "delete from CoreFriendApply where  passiveUserid=" + userid + " and activeUserid=" + friendid;

		basicDAO.execute(delapplyHql);
		basicDAO.execute(delapplyFriendHql);

		return true;
	}

	/**
	 * 获取默认分组。默认分组的userid=0
	 * 
	 * @return
	 */
	public List getDefaultGroup() {
		List list = this.findByQuery("from CoreFriendCategory where userid=0");
		return list;
	}

	/**
	 * 得到我的好友分组情况
	 * 
	 * @param userid
	 * @return
	 */
	public List getMyFriendGroup(int userid) {
		List list = this.findByQuery("from CoreFriendCategory where userid=" + userid);
		if (list == null || list.size() == 0)
			list = getDefaultGroup();
		return list;
	}

	/**
	 * 判断这个组是否我的组
	 * 
	 * @param groupid
	 * @return
	 */
	private boolean isMyOwnGroup(int userid, int groupid) {
		String hql = "from CoreFriendCategory where userid=" + userid + " and id=" + groupid;
		List list = basicDAO.findByQuery(hql);
		return list != null && list.size() > 0;

	}

	/**
	 * 将好友加入分组（我的好友列表）。如果加入的分组不在我的组里面（也就是默认分组）则自动为其添加一分组
	 * 
	 * @param friendid
	 * @param userid
	 * @param groupid
	 * @return
	 */
	@Transactional
	public boolean addGroupFriend(int friendid, int userid, int groupid) {

		int realgroupid = groupid;
		if (!isMyOwnGroup(userid, groupid)) { // 给我自己加个组啊
			CoreFriendCategory category = new CoreFriendCategory();
			category.setFriendCount((short) 1);
			CoreFriendCategory defaultcategory = (CoreFriendCategory) basicDAO.get(CoreFriendCategory.class, groupid);
			category.setName(defaultcategory.getName());
			category.setSort((short) 0);
			category.setTimeCreate(new java.sql.Timestamp(System.currentTimeMillis()));
			category.setUserid(userid);

			basicDAO.save(category);
			realgroupid = category.getId();
		}

		String hql = "update CoreFriend set categoryId=" + realgroupid + " where id=" + friendid;
		basicDAO.execute(hql);

		return true;
	}

	/**
	 * 根据手机号和角色号查找好友。如果角色是1，返回core_user_kids，如果是2，返回core_user
	 * 
	 * @param mobile
	 * @param size
	 * @param pageno
	 * @return
	 */
	// public PaginationSupport findFriendByMobile(String mobile, int size, int
	// pageno) {
	public PaginationSupport findFriendByMobile(String mobile) {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CoreUser.class);
		if (mobile != null && !mobile.equals("")) {
			detachedCriteria.add(Restrictions.eq("mobile", mobile));
		}

		List __list = basicDAO.findByCriteria(detachedCriteria);
		List result = new ArrayList();
		for (int i = 0; i < __list.size(); i++) {
			CoreUser user = (CoreUser) __list.get(i);
			result.add(user);
		}

		PaginationSupport page = new PaginationSupport(result, result.size(), result.size(), 0);

		return page;
	}

	/**
	 * 
	 * 这里根据角色去查，解决了上面的问题，很不错
	 * 
	 * @param provinceid
	 * @param cityid
	 * @param areaid
	 * @param schoolid
	 * @param classlid
	 * @param username
	 * @param role
	 * @param size
	 * @param pageno
	 * @return
	 */
	@Transactional
	public PaginationSupport findFriends(int provinceid, int cityid, int areaid, int schoolid, int classid, String username, int size,
			int pageno) {
		if (pageno <= 0)
			pageno = 1;
		int startIndex = size * (pageno - 1);

		String table = "";
		String role = "";



			table = "core_user";
			role = " aa.user_role";

		String sql = "select distinct aa.id,aa.user_name,bb.class_name,cc.school_name,aa.pic,"
				+ role
				+ " from "
				+ table
				+ " aa left join core_class_member dd on aa.id=dd.userid left join core_school cc on dd.school_id=cc.id left join core_class bb on dd.class_id=bb.id where 1=1 ";
		String sqlcnt = "select count(distinct(aa.id)) as cnt from "
				+ table
				+ " aa left join core_class_member dd on aa.id=dd.userid left join core_school cc on dd.school_id=cc.id left join core_class bb on dd.class_id=bb.id where 1=1 ";
		String where = "";
		if (provinceid != 0) {
			where += " and aa.province_id=" + provinceid;
		}
		if (cityid != 0) {
			where += " and aa.city_id=" + cityid;
		}
		if (areaid != 0) {
			where += " and aa.district_id=" + areaid;
		}
		if (schoolid != 0) {
			where += " and cc.id=" + schoolid;
		}
		if (classid != 0) {
			where += " and bb.id=" + classid;
		}
		if (username != null && !username.equals("")) {
			where += " and aa.user_name like '" + username + "%'";
		}
		
		sql = sql + where + " limit " + startIndex + "," + size;
		sqlcnt += where;

		System.out.println("findfriends sql::" + sql);

		int count = Integer.parseInt(basicDAO.findBySqlQuery(sqlcnt).get(0).toString());

		List list = basicDAO.findBySqlQuery(sql);
		int len = list == null ? 0 : list.size();
		List result = new ArrayList();

		for (int i = 0; i < len; i++) {

			Userinfo userinfo = new Userinfo();
			Object[] obj = (Object[]) list.get(i);
			// aa.id,aa.user_name,bb.class_name,cc.school_name,aa.pic
			userinfo.setId(Integer.parseInt(obj[0].toString()));
			userinfo.setUserName(obj[1].toString());
			userinfo.setClassname((String) obj[2]);
			userinfo.setSchoolname((String) obj[3]);
			userinfo.setPic((String) obj[4]);
			userinfo.setUserRole(obj[5] == null ? (short) 1 : Short.parseShort(obj[5].toString()));
			result.add(userinfo);

		}
		PaginationSupport page = new PaginationSupport(result, count, size, startIndex);
		return page;
	}

	/**
	 * 发送好友申请
	 * 
	 * @param userid
	 * @param friendid
	 * @param remark
	 * @return 0成功发送 1之前已经发了,对方还没反应
	 */
	@Transactional
	public int sendApply(int userid, short userrole, int friendid, short friendrole, String applymsg) {
		String content = "";
		Timestamp time = new Timestamp(System.currentTimeMillis());

		CoreFriendApply apply = new CoreFriendApply();
		apply.setActiveUserid(userid);

		apply.setApplyMessage(applymsg);
		apply.setCreateTime(time);
		apply.setPassiveUserid(friendid);

		apply.setStatus((short) 0);

		int applyid = 0;
		String hql = "from CoreFriendApply apply where apply.activeUserid=" + userid + " and apply.passiveUserid=" + friendid
				+ " order by apply.createTime desc";

		List list = basicDAO.findByQuery(hql);
		if (null == list || list.size() == 0) {
			basicDAO.save(apply);
			applyid = apply.getId();
		} else {
			CoreFriendApply operate = (CoreFriendApply) list.get(0);
			int status = operate.getStatus();
			//status 0未处理,不能再发送消息 1通过 2拒绝
			if (status == 0) {
				return 1;
			} else if (status == 1) {
				//basicDAO.save(apply);
				//applyid = apply.getId();
				return 2;
			} else if(status == 2){
				basicDAO.save(apply);
				applyid = apply.getId();
			}
		}

		//Userinfo userinfo = (Userinfo) basicDAO.get(Userinfo.class, userid);
		Userinfo userinfo = (Userinfo) UserUtil.getInstance().getUserinfo(userid);
		content += "<p><b><a style=\"color:#f60\" href=\"../home/home.action?viewUserid=" + userid + "\">";
		content += userinfo.getUserName() + "</a></b>";
		content += " 希望加您为好友</p>";
		content += "<p><a href=\"#\" style=\"color:#f60\" onclick=\"passrequest(" + applyid + ",_0_)\" ><strong>同意</strong></a>加为好友 ";
		content += " <a href=\"#\" style=\"color:#f60\" onclick=\"showrefusebox(" + applyid + ",\"" + userinfo.getUserName()
				+ "\",0)\" ><strong>拒绝</strong></a>请求</p>";
		content += "<p><b>附言:</b>" + applymsg + "</p>";
		//??
		systemMessageService.sendfriendapply(friendid, content);

		// sysmessageid = systemMessageService.produceSystemMessage(userid, 1,
		// friendid, time, applyid, "请求加为好友", content);
		//

		// systemMessageService.backsystemmessage(sysmessageid, content);
		// content =

		return 0;
		// return "你加的好友需要身份验证！ 请耐心等待“" + friendname + "”的确认。";
	}

	/**
	 * 申请通过 , 互相将对方加入自己的默认分组
	 * 
	 * @param applyid
	 * @param userid
	 * @param sysmsgid
	 * @return 1 处理通过的人不是自己 0 ok
	 */
	@Transactional
	public int applyPass(int applyid, int userid, int sysmsgid) {
		CoreFriendApply friendapply = (CoreFriendApply) basicDAO.get(CoreFriendApply.class, applyid);
		Timestamp time = new Timestamp(System.currentTimeMillis());

		int scruserid = friendapply.getActiveUserid();
		int friendid = friendapply.getPassiveUserid();

		friendapply.setStatus((short) 1);

		basicDAO.update(friendapply);

		//Userinfo userinfo = (Userinfo) basicDAO.get(Userinfo.class, userid);
		//Userinfo friend = (Userinfo) basicDAO.get(Userinfo.class, friendid);
		Userinfo userinfo = (Userinfo) UserUtil.getInstance().getUserinfo(scruserid);
		Userinfo friend = (Userinfo) UserUtil.getInstance().getUserinfo(friendid);
		// systemMessageService

		String content = "";
		content += "<p>您已经将<b><a style=\"color:#f60\" href=\"../home/home.action?viewUserid=" + scruserid + "\">";
		content += userinfo.getUserName() + "</a></b>";
		content += " 加为好友。</p>";
		content += "<p><a style=\"color:#f60\" href=\"../friend/friendList.action\" >设置</a> 好友分组</p> ";
		
		systemMessageService.backSystemMessage(sysmsgid, friendid, content);
		
		String content1="";
		content1 += "<p><b><a style=\"color:#f60\" href=\"../home/home.action?viewUserid=" + friendid + "\">";
		content1 += friend.getUserName() + "</a></b>";
		content1 += " 通过了您的请求</p>";
		content1 += "<p><a style=\"color:#f60\" href=\"../friend/friendList.action\" >设置</a> 好友分组</p> ";
		systemMessageService.produceSystemMessage(scruserid, content1);

		// 2个人相互加为好友
		addFriend(scruserid, userinfo.getUserRole(), friendid, friend.getUserRole());

		
		
		return 0;
	}

	/**
	 * 
	 * 
	 * 加好友,两个人相互为好友，即为2条记录
	 * 
	 * @param userid
	 * @param friendid
	 * @return
	 */
	private boolean addFriend(int userid, short userrole, int friendid, short friendrole) {

		CoreFriend friend = new CoreFriend();
		friend.setCategoryId(0);
		friend.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		friend.setFriendUserid(friendid);
		friend.setFriendUserRole(friendrole);
		friend.setUserid(userid);

		basicDAO.save(friend);

		friend = new CoreFriend();
		friend.setCategoryId(0);
		friend.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		friend.setUserid(friendid);

		friend.setFriendUserid(userid);
		friend.setFriendUserRole(userrole);
		basicDAO.save(friend);

		return true;
	}

	/**
	 * 
	 * 拒绝某人的好友申请
	 * 
	 * @param applyid
	 * @param userid
	 * @param reason
	 * @param sysmsgid
	 * @return 1 不是被请求的用户来进行的操作 0 拒绝成功
	 */
	@Transactional
	private int _frienApplyRefused(int applyid, int userid, String reason, int sysmsgid) {

		CoreFriendApply friendapply = (CoreFriendApply) basicDAO.get(CoreFriendApply.class, applyid);

		int scruserid = friendapply.getActiveUserid();
		int friendid = friendapply.getPassiveUserid();
		friendapply.setStatus((short) 2);
		basicDAO.update(friendapply);

		if (friendid != userid) { // 如果不是被请求用户进行操作
			return 1;
		}
		return 0;
	}

	/**
	 * 
	 * 拒绝某人的好友申请
	 * 
	 * @param applyid
	 * @param userid
	 * @param reason
	 * @param sysmsgid
	 * @return 1 不是被请求的用户来进行的操作 0 拒绝成功 -1数据库失败
	 */
	public int frienApplyRefused(int applyid, int userid, String reason, int sysmsgid) {
		int result = 0;
		try {

			result = _frienApplyRefused(applyid, userid, reason, sysmsgid);

		} catch (Exception e) {
			result = -1;
		}
		if (result == 0) {

			//Userinfo friend = (Userinfo) basicDAO.get(Userinfo.class, userid);
			Userinfo friend = (Userinfo) UserUtil.getInstance().getUserinfo(userid);
			String content = "";
			content += "<p><b><a style=\"color:#f60\" href=\"../home/home.action?viewUserid=" + userid + "\">";
			content += friend.getUserName() + "</a></b>";
			content += " 拒绝了您的请求</p>";
			content += "<p><b>拒绝原因:</b>" + reason + "</p>";
			systemMessageService.backSystemMessage(sysmsgid, userid, content);
			// systemMessageService.produceSystemMessage(scruserid,
			// friendapply.getActiveUserRole(), content);

		}
		return result;

	}

}