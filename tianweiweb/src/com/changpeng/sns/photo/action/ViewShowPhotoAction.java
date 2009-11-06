package com.changpeng.sns.photo.action;

import java.util.List;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.FriendService;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.photo.model.SnsPhoto;
import com.changpeng.sns.photo.model.SnsPhotoAlbum;
import com.changpeng.sns.photo.service.PhotoService;

public class ViewShowPhotoAction extends AbstractListAction {
	
	private String previous;
	private String next;
	private int albumid;
	private SnsPhotoAlbum album;
	private String username;
	private String rolename;
	private int photoid=0;
	private PaginationSupport commentpage;
	private String pageString;
	private boolean photoismine = false;

	@Override
	protected String go() throws Exception {
		
//		photolist = (List) get("photolist");
		pageSize = 1;
		
		
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		UserService  user = (UserService)this.getBean("userService");
		FriendService friendService = (FriendService) this.getBean("friendService");
		
		
		
		/* 通过photoid来查询，这里主要是相片动态的链入用 */
		if(photoid!=0){
			SnsPhoto photo = (SnsPhoto) photoService.getPhotoById(photoid);
			if(photo==null){
				return "del";
			}
			albumid = photo.getAlbumid();
			List list = photoService.getViewPhotos(albumid);
			int i;
			for(i=0;i<list.size();i++){
				if(((SnsPhoto)list.get(i)).getPhotoid()==photoid)
					break;
			}
			pageNo = i+1;
			this.viewUserid = photo.getUserid();
		}
		
		album = photoService.getAlbumById(albumid);
		boolean viewerisfriend = friendService.isFriendWithThem(this.viewUserid, this.currentUserid);
		if ((album == null || album.getPrivateFlag().equals("2"))
				&& this.viewUserid != this.currentUserid) {
			return "forbid";
		} else if ((album == null || (album.getPrivateFlag().equals("2") && !viewerisfriend))
				&& this.viewUserid != this.currentUserid) {
			return "forbid";
		}
		
		int userid = this.viewUserid;
		
		Userinfo coreUser = (Userinfo)user.getUserinfoById(userid);
		username = coreUser.getUserName();
		
		short roleid = coreUser.getUserRole();
		// short roleid =((Userinfo)photoService.get(Userinfo.class,
		// userid)).getUserRole();
		if (roleid == (short) 1) {
			rolename = "同学";
		} else if (roleid == (short) 3) {
			rolename = "老师";
		} else if (roleid == (short) 2) {
			rolename = "家长";
		} else if (roleid == (short) 4) {
			rolename = "老师";
		}
		
		/* 相片的分页显示 */
		page = photoService.getViewPhotos(albumid,pageSize,pageNo);
		
		/* 获取相册的信息 */
//		album =(SnsPhotoAlbum)photoService.get(SnsPhotoAlbum.class, albumid);
		
		/* 获取评论信息 */
		if(page.getItems().size()>0){
			photoid = ((SnsPhoto)page.getItems().get(0)).getPhotoid();
			
			commentpage = photoService.getPhotoComment(photoid, 5, 1);
			
			Pagefoot pagefoot = new Pagefoot();
			pageString = pagefoot.packString(commentpage, 1,"getcommentpage");
		}
		
		/* 判读这个是不是我的相片 */
		SnsPhoto photo = (SnsPhoto) photoService.get(SnsPhoto.class, photoid);
		if(photo.getUserid() == this.currentUserid){
			photoismine = true;
		}
		
		/* 封装上一页和下一页的链接 */
		if(pageNo>1){
			previous = "viewshowphoto.action?albumid="+albumid+"&pageNo="+(pageNo-1)+"&viewUserid="+viewUserid;
		}else{
			previous = "javascript:alert('这是第一张图片')";
		}
		
		if(pageNo<page.getCount()){
			next = "viewshowphoto.action?albumid="+albumid+"&pageNo="+(pageNo+1)+"&viewUserid="+viewUserid;
		}else{
			next = "javascript:alert('这是最后一张图片')";
		}

		return SUCCESS;
	}
	

	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

	public SnsPhotoAlbum getAlbum() {
		return album;
	}

	public void setAlbum(SnsPhotoAlbum album) {
		this.album = album;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public int getPhotoid() {
		return photoid;
	}

	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}


	public PaginationSupport getCommentpage() {
		return commentpage;
	}


	public void setCommentpage(PaginationSupport commentpage) {
		this.commentpage = commentpage;
	}


	public String getPageString() {
		return pageString;
	}


	public void setPageString(String pageString) {
		this.pageString = pageString;
	}


	public boolean isPhotoismine() {
		return photoismine;
	}


	public void setPhotoismine(boolean photoismine) {
		this.photoismine = photoismine;
	}


}
