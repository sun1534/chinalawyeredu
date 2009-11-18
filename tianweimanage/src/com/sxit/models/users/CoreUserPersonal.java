package com.sxit.models.users;

import java.util.Date;

/**
 * CoreUserPersonal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CoreUserPersonal implements java.io.Serializable {

	// Fields

	private Integer userid;
	private Short onlineFlag;
	private Integer mark;
	private Short countFriend;
	private Short countBlog;
	private Integer countBlogView;
	private Short countBlogArticle;
	private Short countGroupCreate;
	private Short countGroupJoin;
	private Short countGroupTopic;
	private Short countAlbum;
	private Integer countAlbumView;
	private Short countAlbumPic;
	private Integer countLoginTimes;
	private Short countMsgUnread;
	private Short countSysmsgUnread;
	private Short countHiUnread;
	private Short countFirendUnread;
	private Short countComrepUnread;
	private String lastIp;
	private Date lastTime;
	private String mood;
	private Date moodTime;

	// Constructors

	/** default constructor */
	public CoreUserPersonal() {
	}

	/** minimal constructor */
	public CoreUserPersonal(Short onlineFlag, Integer mark, Short countFriend, Short countBlog, Integer countBlogView, Short countBlogArticle,
			Short countGroupCreate, Short countGroupJoin, Short countGroupTopic, Short countAlbum, Integer countAlbumView, Short countAlbumPic,
			Integer countLoginTimes, Short countMsgUnread, Short countSysmsgUnread, Short countHiUnread, Short countFirendUnread,
			Short countComrepUnread) {
		this.onlineFlag = onlineFlag;
		this.mark = mark;
		this.countFriend = countFriend;
		this.countBlog = countBlog;
		this.countBlogView = countBlogView;
		this.countBlogArticle = countBlogArticle;
		this.countGroupCreate = countGroupCreate;
		this.countGroupJoin = countGroupJoin;
		this.countGroupTopic = countGroupTopic;
		this.countAlbum = countAlbum;
		this.countAlbumView = countAlbumView;
		this.countAlbumPic = countAlbumPic;
		this.countLoginTimes = countLoginTimes;
		this.countMsgUnread = countMsgUnread;
		this.countSysmsgUnread = countSysmsgUnread;
		this.countHiUnread = countHiUnread;
		this.countFirendUnread = countFirendUnread;
		this.countComrepUnread = countComrepUnread;
	}

	/** full constructor */
	public CoreUserPersonal(Short onlineFlag, Integer mark, Short countFriend, Short countBlog, Integer countBlogView, Short countBlogArticle,
			Short countGroupCreate, Short countGroupJoin, Short countGroupTopic, Short countAlbum, Integer countAlbumView, Short countAlbumPic,
			Integer countLoginTimes, Short countMsgUnread, Short countSysmsgUnread, Short countHiUnread, Short countFirendUnread,
			Short countComrepUnread, String lastIp, Date lastTime, String mood, Date moodTime) {
		this.onlineFlag = onlineFlag;
		this.mark = mark;
		this.countFriend = countFriend;
		this.countBlog = countBlog;
		this.countBlogView = countBlogView;
		this.countBlogArticle = countBlogArticle;
		this.countGroupCreate = countGroupCreate;
		this.countGroupJoin = countGroupJoin;
		this.countGroupTopic = countGroupTopic;
		this.countAlbum = countAlbum;
		this.countAlbumView = countAlbumView;
		this.countAlbumPic = countAlbumPic;
		this.countLoginTimes = countLoginTimes;
		this.countMsgUnread = countMsgUnread;
		this.countSysmsgUnread = countSysmsgUnread;
		this.countHiUnread = countHiUnread;
		this.countFirendUnread = countFirendUnread;
		this.countComrepUnread = countComrepUnread;
		this.lastIp = lastIp;
		this.lastTime = lastTime;
		this.mood = mood;
		this.moodTime = moodTime;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Short getOnlineFlag() {
		return this.onlineFlag;
	}

	public void setOnlineFlag(Short onlineFlag) {
		this.onlineFlag = onlineFlag;
	}

	public Integer getMark() {
		return this.mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Short getCountFriend() {
		return this.countFriend;
	}

	public void setCountFriend(Short countFriend) {
		this.countFriend = countFriend;
	}

	public Short getCountBlog() {
		return this.countBlog;
	}

	public void setCountBlog(Short countBlog) {
		this.countBlog = countBlog;
	}

	public Integer getCountBlogView() {
		return this.countBlogView;
	}

	public void setCountBlogView(Integer countBlogView) {
		this.countBlogView = countBlogView;
	}

	public Short getCountBlogArticle() {
		return this.countBlogArticle;
	}

	public void setCountBlogArticle(Short countBlogArticle) {
		this.countBlogArticle = countBlogArticle;
	}

	public Short getCountGroupCreate() {
		return this.countGroupCreate;
	}

	public void setCountGroupCreate(Short countGroupCreate) {
		this.countGroupCreate = countGroupCreate;
	}

	public Short getCountGroupJoin() {
		return this.countGroupJoin;
	}

	public void setCountGroupJoin(Short countGroupJoin) {
		this.countGroupJoin = countGroupJoin;
	}

	public Short getCountGroupTopic() {
		return this.countGroupTopic;
	}

	public void setCountGroupTopic(Short countGroupTopic) {
		this.countGroupTopic = countGroupTopic;
	}

	public Short getCountAlbum() {
		return this.countAlbum;
	}

	public void setCountAlbum(Short countAlbum) {
		this.countAlbum = countAlbum;
	}

	public Integer getCountAlbumView() {
		return this.countAlbumView;
	}

	public void setCountAlbumView(Integer countAlbumView) {
		this.countAlbumView = countAlbumView;
	}

	public Short getCountAlbumPic() {
		return this.countAlbumPic;
	}

	public void setCountAlbumPic(Short countAlbumPic) {
		this.countAlbumPic = countAlbumPic;
	}

	public Integer getCountLoginTimes() {
		return this.countLoginTimes;
	}

	public void setCountLoginTimes(Integer countLoginTimes) {
		this.countLoginTimes = countLoginTimes;
	}

	public Short getCountMsgUnread() {
		return this.countMsgUnread;
	}

	public void setCountMsgUnread(Short countMsgUnread) {
		this.countMsgUnread = countMsgUnread;
	}

	public Short getCountSysmsgUnread() {
		return this.countSysmsgUnread;
	}

	public void setCountSysmsgUnread(Short countSysmsgUnread) {
		this.countSysmsgUnread = countSysmsgUnread;
	}

	public Short getCountHiUnread() {
		return this.countHiUnread;
	}

	public void setCountHiUnread(Short countHiUnread) {
		this.countHiUnread = countHiUnread;
	}

	public Short getCountFirendUnread() {
		return this.countFirendUnread;
	}

	public void setCountFirendUnread(Short countFirendUnread) {
		this.countFirendUnread = countFirendUnread;
	}

	public Short getCountComrepUnread() {
		return this.countComrepUnread;
	}

	public void setCountComrepUnread(Short countComrepUnread) {
		this.countComrepUnread = countComrepUnread;
	}

	public String getLastIp() {
		return this.lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getMood() {
		return this.mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public Date getMoodTime() {
		return this.moodTime;
	}

	public void setMoodTime(Date moodTime) {
		this.moodTime = moodTime;
	}

}