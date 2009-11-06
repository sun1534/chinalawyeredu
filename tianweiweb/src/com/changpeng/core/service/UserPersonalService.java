/**
 * 
 */
package com.changpeng.core.service;


import java.util.List;

import com.changpeng.common.BasicService;

/**
 * 
 * 用户相关sns属性的资料
 * 
 * @author 华锋 May 22, 2009 3:34:28 PM
 * 
 */
public class UserPersonalService extends BasicService {

	public String getHomeMsgCount(int userid){
		String sql="select count_sysmsg_unread,count_msg_unread,count_comrep_unread from core_user_personal where userid="+userid;
	List list=basicDAO.findBySqlQuery(sql);
	if(list!=null&&list.size()>0){
		Object[] obj=(Object[])list.get(0);
		StringBuffer sb=new StringBuffer();
	    sb.append(obj[0]).append(",").append(obj[1]).append(",").append(obj[2]);
	   return sb.toString();
	}
	return "0,0,0";
	}

	/**
	 * sign sign_time
	 * 
	 * 更改用户的签名
	 * 
	 * @param userid
	 * @param sign
	 */
	public void updateSign(int userid, String sign) {
		String sql = "update core_user_personal set sign='" + sign + "',sign_time=now() where id=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * mood mood_time
	 * 
	 * 
	 * 更新用户的心情
	 * 
	 * @param userid
	 * @param mood
	 */
	public void updateMoon(int userid, String mood) {
		String sql = "update core_user_personal set mood='" + mood + "',mood_time=now() where id=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * online_flag
	 * 
	 * 设置这个人是否登录了
	 * 
	 * @param userid
	 * @param online
	 */
	public void updateOnline(int userid, boolean isonline) {
		String sql = "update core_user_personal set online_flag=" + (isonline ? 1 : 0) + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * mark
	 * 
	 * 更改这个人的积分变动值
	 * 
	 * @param userid
	 * @param markChanged
	 */
	public void updateMark(int userid, int changed) {
		String sql = "update core_user_personal set mark=mark+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_friend 更改这个人的好友变动值
	 * 
	 * @param userid
	 * @param friendCntChanged
	 */
	public void updateCountFriend(int userid, int changed) {
		String sql = "update core_user_personal set count_friend=count_friend+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_blog
	 * 
	 * @param userid
	 * @param blogCntChanged
	 */
	public void updateCountBlog(int userid, int changed) {
		String sql = "update core_user_personal set count_blog=count_blog+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * 
	 * 这个人的博客点击数加1 count_blog_view
	 * 
	 * @param userid
	 */
	public void updateCountBlogView(int userid) {
		String sql = "update core_user_personal set count_blog_view=count_blog_view+1 where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_blog_article
	 * 
	 * @param userid
	 * @param blogCntBlogArticles
	 */
	public void updateCountBlogArticle(int userid, int changed) {
		String sql = "update core_user_personal set count_blog_article=count_blog_article+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_group_create
	 * 
	 * @param userid
	 * @param changed
	 */
	public void updateCountGroupCreate(int userid, int changed) {
		String sql = "update core_user_personal set count_group_create=count_group_create+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_group_topic
	 * 
	 * @param userid
	 * @param changed
	 */
	public void updateCountGroupTopic(int userid, int changed) {
		String sql = "update core_user_personal set count_group_topic=count_group_topic+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_group_join
	 * 
	 * @param userid
	 * @param changed
	 */
	public void updateCountGroupJoin(int userid, int changed) {
		String sql = "update core_user_personal set count_group_join=count_group_join+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_album
	 * 
	 * @param userid
	 * @param changed
	 */
	public void updateCountAlbum(int userid, int changed) {
		String sql = "update core_user_personal set count_album=count_album+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_album_view
	 * 
	 * @param userid
	 */
	public void updateCountAlbumView(int userid) {
		String sql = "update core_user_personal set count_album_view=count_album_view+1 where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_album_pic
	 * 
	 * @param userid
	 * @param changed
	 */
	public void updateCountAlbumPic(int userid, int changed) {
		String sql = "update core_user_personal set count_album_pic=count_album_pic+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * `last_ip` varchar(20) DEFAULT NULL COMMENT '最后登录ip', `last_time` datetime
	 * DEFAULT NULL COMMENT '最后登录时间', count_login_times 新增登录次数加1
	 * 
	 * @param userid
	 * @param changed
	 */
	public void updateCountLogin(int userid, String ip) {
		String sql = "update core_user_personal set count_friend=count_friend+1,last_time=now(),last_ip=" + ip + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_msg_unread
	 * 
	 * @param userid
	 * @param changed
	 */
	public void updateCountMsgUnRead(int userid, int changed) {
		String sql = "update core_user_personal set count_msg_unread=count_msg_unread+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_sysmsg_unread
	 * 
	 * @param userid
	 * @param changed
	 */
	public void updateCountSysmsgUnRread(int userid, int changed) {
		String sql = "update core_user_personal set count_sysmsg_unread=count_sysmsg_unread+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_hi_unread
	 * 
	 * @param userid
	 * @param changed
	 */
	public void updateCountHiUnRead(int userid, int changed) {
		String sql = "update core_user_personal set count_hi_unread=count_hi_unread+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_firend_unread
	 * 
	 * @param userid
	 * @param changed
	 */
	public void updateCountFriendUnRead(int userid, int changed) {
		String sql = "update core_user_personal set count_firend_unread=count_firend_unread+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}

	/**
	 * count_comrep_unread
	 * 
	 * @param userid
	 * @param changed
	 */
	public void updateCountComrepUnRead(int userid, int changed) {
		String sql = "update core_user_personal set count_comrep_unread=count_comrep_unread+" + changed + " where userid=" + userid;
		basicDAO.executeSql(sql);
	}
	
	/**
	 * 清空站内信数量
	 * @param userid
	 */
	public void clearInnerMsgCount(int userid){
		String sql = "update core_user_personal set count_msg_unread=0 where userid=" + userid;
		basicDAO.executeSql(sql);
	}
	/**
	 * 清空系统消息数量
	 * @param userid
	 */
	public void clearSysMsgCount(int userid){
		String sql = "update core_user_personal set count_sysmsg_unread=0 where userid=" + userid;
		basicDAO.executeSql(sql);
	}
	/**
	 * 清空留言数量
	 * @param userid
	 */
	public void clearComMsgCount(int userid){
		String sql = "update core_user_personal set count_comrep_unread=0 where userid=" + userid;
		basicDAO.executeSql(sql);
	}
}