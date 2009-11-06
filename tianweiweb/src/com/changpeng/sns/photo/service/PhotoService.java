package com.changpeng.sns.photo.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.core.service.FriendService;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.photo.action.AlbumListAction;
import com.changpeng.sns.photo.model.SnsPhoto;
import com.changpeng.sns.photo.model.SnsPhotoAlbum;
import com.changpeng.sns.photo.model.SnsPhotoComment;
import com.changpeng.sns.photo.model.SnsPhotoDelLog;

public class PhotoService extends BasicService {
	Logger log = Logger.getLogger(PhotoService.class);
	public FriendService friendService;
	public UserService userService;

	// public UserService userService;

	/**
	 * 首页显示照片
	 */
	public List indexShow(int userid, int count) {
		String hql = "from Album where userid=" + userid + "order by createTime desc";
		List list = basicDAO.findNumList(hql, count);
		return list;
	}
/**
 * 
 * 
 * @param albumid
 * @return
 */
	public SnsPhotoAlbum getAlbumById(int albumid){
		return ((SnsPhotoAlbum)basicDAO.get(SnsPhotoAlbum.class, albumid));
	}
	/**
	 * 
	 * @param photoid
	 * @return
	 */
	public SnsPhoto getPhotoById(int photoid){
		return ((SnsPhoto)basicDAO.get(SnsPhoto.class, photoid));
	}
	
	
	/**
	 * 获取我的相册的列表
	 * 
	 * @param userid
	 * @return
	 */
	public List getMyAlbumList(int userid) {
		String hql = "from SnsPhotoAlbum a where a.userid=" + userid;
		List list = findByQuery(hql);
		return list;
	}

	/**
	 * 获取我的相册，分页的方式显示
	 * 
	 * @return
	 */
	public PaginationSupport getMyAlbumList(int userid, int size, int pageno) {
		if (pageno == 0)
			pageno = 1;
		//int startIndex = size * (pageno - 1);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsPhotoAlbum.class);
		if (userid != 0) {
			detachedCriteria.add(Restrictions.eq("userid", userid));
			
		}
		detachedCriteria.addOrder(Order.desc("createTime"));
		PaginationSupport page = this.findPageOnPageNo(detachedCriteria, size, pageno);
		return page;
	}

	/**
	 * 获取好友的相册列表,关联好友表
	 * 
	 * @param userid
	 * @param size
	 * @param pageno
	 * @return
	 */
	public List getMyFriendAlbumList(int userid) {
		String sql = "select a.albumid,a.userid,a.photo_count,a.private_flag,a.create_time,a.album_name,a.url from sns_photo_album a inner join core_friend b on a.userid=b.friend_userid where b.userid="
				+ userid +" order by a.userid";
		List list = basicDAO.findBySqlQuery(sql);
		List<SnsPhotoAlbum> result = new ArrayList();
		int len = list == null ? 0 : list.size();
		int j=0;
		boolean isnew = true;
		for (int i = 0; i < len; i++) {
			Object[] obj = (Object[]) list.get(i);
			SnsPhotoAlbum album = new SnsPhotoAlbum();
			album.setAlbumid(Integer.parseInt(obj[0].toString()));
			album.setUserid(Integer.parseInt(obj[1].toString()));
			album.setPhotoCount(obj[2] == null ? 0 : Integer.parseInt(obj[2].toString()));
			album.setPrivateFlag(obj[3] == null ? null : obj[3].toString());
			album.setCreateTime(obj[4] == null ? null : (Timestamp) obj[4]);
			album.setAlbumName((String) obj[5]);
			album.setUrl((String) obj[6]);
			//第一次加入一个相册
			if(isnew&&!album.getPrivateFlag().equals("2")){
				result.add(album);
				j++;
				isnew = false;
			}else{
				if(j>0){
					//判读是否是同一个人的相册
					if((int)((SnsPhotoAlbum)result.get(j-1)).getUserid()==(int)album.getUserid()){
						//判读这个相册是否比之前那个相册新 && 这个相册是否允许查看
						if(((SnsPhotoAlbum)result.get(j-1)).getCreateTime().before(album.getCreateTime())
								&& !album.getPrivateFlag().equals("2")) {
							result.remove(j-1);
							result.add(album);
						}
					}
						//若不是同一个人的相册并允许查看
					else if(!album.getPrivateFlag().equals("2")){
						result.add(album);
						j++;
					}
				}
			}
			
		}
		return result;
	}

	/**
	 * 删除用户的相册
	 * 
	 * @param albumid
	 *            相册id
	 * @param userid
	 *            用户id
	 * @return int 0成功 -1相册拥有人和删除的人不是同一个人 -2相册下还有照片,不能删呢
	 */
	@Transactional
	public int deleteAlbum(int albumid, int userid) {
		SnsPhotoAlbum album = (SnsPhotoAlbum) basicDAO.get(SnsPhotoAlbum.class, albumid);
		/*
 		if (album.getPhotoCount() > 0) {
			// return "请先删除相册下的照片";
			return -2;
		}
		*/
		List todelphotolist = getMyPhotos(albumid,userid);
		for(int i=0;i<todelphotolist.size();i++){
			SnsPhoto userphoto = (SnsPhoto) todelphotolist.get(i);
			deletePhoto(userphoto.getPhotoid(),albumid,userid);
		}
		if (album.getUserid() != userid) {
			return -1;
		}
		this.delete(album);
		return 0;
	}

	/**
	 * 添加专辑
	 * 
	 * @param userid
	 *            用户id
	 * @param userRole
	 *            角色
	 * @param albumname
	 *            相册名字
	 * @param albumpic
	 *            相册图片
	 * @param ip
	 *            相册的ip
	 * @return
	 */
	@Transactional
	public boolean addAlbum(int userid, String albumname, String albumpic, String ip,String privateFlag,String remark) {
		SnsPhotoAlbum album = new SnsPhotoAlbum();
		album.setAlbumName(albumname);
		album.setUserid(userid);
		album.setUrl(albumpic);
		album.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		album.setPhotoCount(0);
		album.setPrivateFlag("1");
		album.setPrivateFlag(privateFlag);
		album.setRemark(remark);
		this.save(album);
		return true;
	}

	/**
	 * 获取相片列表
	 * 
	 * @param albumid
	 * @param userid
	 * @return
	 */
	public List getMyPhotos(int albumid, int userid) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsPhoto.class);
		if (userid != 0) {
			detachedCriteria.add(Restrictions.eq("userid", userid));
		}
		if (albumid != 0) {
			detachedCriteria.add(Restrictions.eq("albumid", albumid));
		}
		detachedCriteria.addOrder(Order.asc("createTime"));
		return basicDAO.findByCriteria(detachedCriteria);
	}

	/**
	 * 显示图片的列表
	 * 
	 * @param albumid
	 * @param userid
	 * @param size
	 * @param pageno
	 * @return
	 */
	public PaginationSupport getMyPhotos(int userid, int albumid, int size, int pageno) {
		if (pageno == 0)
			pageno = 1;
		
		
//		int startIndex = size * (pageno - 1);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsPhoto.class);
		if (userid != 0) {
			detachedCriteria.add(Restrictions.eq("userid", userid));
		}
		if (albumid != 0) {
			detachedCriteria.add(Restrictions.eq("albumid", albumid));
		}
		detachedCriteria.addOrder(Order.asc("createTime"));
		PaginationSupport page = this.findPageOnPageNo(detachedCriteria, size, pageno);

		return page;
	}
	
	/**
	 * 获取查看相片列表
	 * @param albumid
	 * @return
	 */
	public List getViewPhotos(int albumid) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsPhoto.class);
		
		if (albumid != 0) {
			detachedCriteria.add(Restrictions.eq("albumid", albumid));
		}
		detachedCriteria.addOrder(Order.asc("createTime"));
		return basicDAO.findByCriteria(detachedCriteria);
	}
	
	/**
	 * 获取查看相片列表
	 * @param albumid
	 * @param size
	 * @param pageno
	 * @return
	 */
	public PaginationSupport getViewPhotos(int albumid, int size, int pageno){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsPhoto.class);

		if (albumid != 0) {
			detachedCriteria.add(Restrictions.eq("albumid", albumid));
		}
		detachedCriteria.addOrder(Order.asc("createTime"));
		PaginationSupport page = this.findPageOnPageNo(detachedCriteria, size, pageno);

		return page;
	}

	/**
	 * 修改照片的名字
	 * 
	 * @param photoname
	 * @param photoid
	 * @param userid
	 * @return
	 */
	public boolean modifyPhotoName(int photoid, int userid, String photoName) {
		SnsPhoto userphoto = (SnsPhoto) basicDAO.get(SnsPhoto.class, photoid);
		System.out.println("photoid:"+photoid);
		System.out.println("userphoto:"+userphoto);
		if (userphoto.getUserid() != userid) {
			return false;
		}
		userphoto.setPhotoName(photoName);
		this.update(userphoto);
		return true;
	}
	
	/**
	 * 编辑相册
	 * @param albumid
	 * @param userid
	 * @param albumName
	 * @param privateFlag
	 * @return
	 */
	public boolean modifyAlbumName(int albumid, int userid, String albumName,String privateFlag,String remark ){
		SnsPhotoAlbum snsPhotoAlbum = (SnsPhotoAlbum) basicDAO.get(SnsPhotoAlbum.class, albumid);
		if (snsPhotoAlbum.getUserid()!=userid){
			return false;
		}
		snsPhotoAlbum.setAlbumName(albumName);
		snsPhotoAlbum.setPrivateFlag(privateFlag);
		snsPhotoAlbum.setRemark(remark);
		this.update(snsPhotoAlbum);
		return true;
	}

	/**
	 * 用户新增一个照片.同时发送一个动态消息
	 * 
	 * @param photoname
	 * @param filename
	 * @param albumid
	 * @param userid
	 * @param ip
	 * @return
	 */
	@Transactional
	public boolean addPhoto(String photoname, String smallurl, int albumid, int userid, String createIp,String url) {

		SnsPhotoAlbum album = (SnsPhotoAlbum) basicDAO.get(SnsPhotoAlbum.class, albumid);
		SnsPhoto userphoto = new SnsPhoto();
		Userinfo userinfo = userService.getUserinfoById(userid);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		userphoto.setCreateTime(time);
		userphoto.setSmallUrl(smallurl);
		userphoto.setPhotoName(photoname);
		userphoto.setAlbumid(albumid);
		userphoto.setCreateIp(createIp);
		userphoto.setUserid(userid);
		userphoto.setUrl(url);
		userphoto.setStatusid((short)1);
		if (album.getPhotoCount() == null) {
			album.setPhotoCount(0);
		}
		album.setPhotoCount(album.getPhotoCount() + 1);
		this.save(userphoto);
		if (album.getPhotoCount() == 1) {
			album.setUrl(userphoto.getSmallUrl());
		}
		this.update(album);

		return true;
	}

	/**
	 * 
	 * 删除相册下的某个具体的照片。如果对应的相册没有照片了，则设置相册的url为默认图片
	 * 
	 * @param photoid
	 * @param albumid
	 * @param userid
	 * @return 0 删除成功 -1 删除失败 -2 删除的照片不存在
	 */
	@Transactional
	public int deletePhoto(int photoid, int albumid, int userid) {

		SnsPhoto userphoto = (SnsPhoto) basicDAO.get(SnsPhoto.class, photoid);
		if (userphoto == null)
			return -2;
		// 修改所属相册的照片数减1
		SnsPhotoAlbum album = (SnsPhotoAlbum) basicDAO.get(SnsPhotoAlbum.class,
				albumid);
		album.setPhotoCount(album.getPhotoCount() - 1);
		if (album.getUrl().equals(userphoto.getSmallUrl())) {
			album.setUrl(com.changpeng.common.sysdata.CommonData.DEFAULT_ALBUM_PIC);
		}
		this.update(album);

		// String url =
		// ServletActionContext.getServletContext().getRealPath(userphoto.getUrl());
		// String smallurl =
		// ServletActionContext.getServletContext().getRealPath(userphoto.getSmallUrl());

		/* 不做物理删除 */
		/*
		 * try { (new File(url)).delete(); (new File(smallurl)).delete(); }
		 * catch (Exception e) {
		 * 
		 * LOG.error("照片删除出错Exception::" + e); return -1; }
		 */
		addDelPhotoLog(userphoto);
		basicDAO.delete(userphoto);
		return 0;
	}
	
	/**
	 * 添加删除照片记录
	 * @param userphoto
	 */
	public void addDelPhotoLog(SnsPhoto userphoto){
		SnsPhotoDelLog dellog = new SnsPhotoDelLog();
		dellog.setAlbumid(userphoto.getAlbumid());
		dellog.setCreateIp(userphoto.getCreateIp());
		dellog.setCreateTime(userphoto.getCreateTime());
		dellog.setDescription(userphoto.getDescription());
		dellog.setPhotoid(userphoto.getPhotoid());
		dellog.setPhotoName(userphoto.getPhotoName());
		dellog.setSmallUrl(userphoto.getSmallUrl());
		dellog.setUrl(userphoto.getUrl());
		dellog.setUserid(userphoto.getUserid());
		dellog.setDeltime(new Timestamp(System.currentTimeMillis()));
		this.save(dellog);
	}

	/**
	 * 获取照片
	 * 
	 * @param photoid
	 * @return
	 */
	public SnsPhoto getUserPhoto(int photoid) {
		SnsPhoto userphoto = (SnsPhoto) basicDAO.get(SnsPhoto.class, photoid);
		return userphoto;
	}

	/**
	 * 设置相册的url
	 * 
	 * @param album
	 */
	@Transactional
	public void setAlbumUrl(int albumid, String albumUrl) {

		String sql = "update sns_photo_album set url='" + albumUrl + "' where albumid=" + albumid;
		basicDAO.executeSql(sql);
	}

	/**
	 * 修改相册的名称
	 * 
	 * @param albumid
	 * @param albumname
	 */
	public void updateAlbumName(int albumid, String albumName) {

		String query = "update SnsPhotoAlbum set albumName ='" + albumName + "' where albumid =" + albumid;
		basicDAO.execute(query);
	}

	/**
	 * 得到这个人有多少个相册
	 * 
	 * @param userid
	 * @return
	 */
	public int getAlbumCount(int userid) {
		String query = "select count(*) as cnt from SnsPhotoAlbum as a where a.userid =" + userid;
		List list = basicDAO.findByQuery(query);
		int cnt = Integer.parseInt(list.get(0).toString());
		return cnt;

	}
	
	/**
	 * 根据隐私设置判读是否可以查看相册
	 * @param userid 查看人(当前用户)的userid
	 * @param albumid 要查看相册的id
	 * @return
	 */
	public boolean allowByPrivateFlag(int userid,int albumid){
		SnsPhotoAlbum album = (SnsPhotoAlbum) basicDAO.get(SnsPhotoAlbum.class, albumid);
		if(album.getPrivateFlag().equals("0")){
			return true;
		}else if(album.getPrivateFlag().equals("1")){
			if(friendService.isFriendWithThem(userid, album.getUserid())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	/**
	 * 根据隐私设置判读是否可以查看相册
	 * @param userid 查看人(当前用户)的userid
	 * @param album_userid 要查看相册的主人id
	 * @param flag 隐私设置标识
	 * @return
	 */
	public boolean allowByPrivateFlag(int userid,int album_userid, String flag){
		if(flag.equals("0")){
			return true;
		}else if (flag.equals("1")){
			if(friendService.isFriendWithThem(userid, album_userid)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	/** 
	 *获取相片的评论 
	 */
	public PaginationSupport getPhotoComment(int photoid, int size, int pageno){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsPhotoComment.class);

		detachedCriteria.add(Restrictions.eq("photoid", photoid));
		
		detachedCriteria.addOrder(Order.asc("createTime"));
		PaginationSupport page = this.findPageOnPageNo(detachedCriteria, size, pageno);

		return page;
	}
	
	/**
	 * 获取某个相片的评论数
	 * @param photoid
	 * @return
	 */
	public int getCommentCount(int photoid){
		String sql = "select COUNT(*) from sns_photo_comment where photoid="+photoid;
		List list = basicDAO.findBySqlQuery(sql);
		return ((BigInteger)list.get(0)).intValue();
	}
	
	/**
	 * 添加相片评论
	 * @param userid
	 * @param userRole
	 * @param photoid
	 * @param content
	 */
	public void addPhotoComment(int userid ,int photoid,String content){
		SnsPhotoComment comment = new SnsPhotoComment();
		comment.setAnonymousFlag((short) 0);
		comment.setContent(content);
		comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
		comment.setHiddenFlag((short)0);
		comment.setPhotoid(photoid);
		comment.setReplyFloorid(0);
		comment.setUserid(userid);
		this.save(comment);
	}
	
	/**
	 * 删除相册评论
	 * @param userid
	 * @param commentid
	 */
	public void delPhotoComment(int photouserid, int userid ,int commentid){
		SnsPhotoComment comment = (SnsPhotoComment) this.get(SnsPhotoComment.class, commentid);
		if(comment.getUserid()==userid||photouserid==userid){
			this.delete(comment);
		}
	}
	
//	private String getResourcepath() {
//		return jxq.common.sysdata.CommonData.Resourcepath;
//	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}
	private static final Logger LOG=Logger.getLogger(AlbumListAction.class);

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	/**
	 *
	 *查看用户的照片列表
	 * 
	 * @param albumid
	 * @param userid
	 * @return
	 */
	public List getUserPhotos(int userid,int count) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsPhoto.class);
		detachedCriteria.add(Restrictions.eq("userid", userid));
		detachedCriteria.add(Restrictions.eq("statusid", (short)0));
		detachedCriteria.addOrder(Order.asc("createTime"));
		return basicDAO.findByCriteria(detachedCriteria,count);
	}
	
	
	
	/**
	 * 获取相片列表
	 * @param albumid
	 * @param size
	 * @param pageno
	 * @return
	 */
	public PaginationSupport getSelectPhotos(int userid,int size, int pageno){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsPhoto.class);

		detachedCriteria.add(Restrictions.eq("userid", userid));
		detachedCriteria.addOrder(Order.asc("createTime"));
		PaginationSupport page = this.findPageOnPageNo(detachedCriteria, size, pageno);

		return page;
	}
	
	/**
	 * 获取相片列表
	 * @param albumid
	 * @param size
	 * @param pageno
	 * @return
	 */
	public PaginationSupport getPageViewPhotos(int userid,int size, int pageno){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsPhoto.class);

		detachedCriteria.add(Restrictions.eq("userid", userid));
		detachedCriteria.add(Restrictions.eq("statusid", (short)0));
		detachedCriteria.addOrder(Order.asc("createTime"));
		PaginationSupport page = this.findPageOnPageNo(detachedCriteria, size, pageno);

		return page;
	}
}
