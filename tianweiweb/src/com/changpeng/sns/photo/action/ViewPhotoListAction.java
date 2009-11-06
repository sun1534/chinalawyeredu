package com.changpeng.sns.photo.action;

import java.util.List;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.FriendService;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.photo.model.SnsPhotoAlbum;
import com.changpeng.sns.photo.service.PhotoService;

/**
 * 
 * 
 * 
 * @author 华锋 May 20, 2009 9:54:49 PM
 * 
 */
public class ViewPhotoListAction extends AbstractListAction {

	List photolist;
	int albumid;
	private String albumname;
	private String rolename;
	private String pageString;
	SnsPhotoAlbum album;
	private String username;

	public ViewPhotoListAction() {
		this.moduleid = 16;
	}

	// private String username;
	@Override
	protected String go() throws Exception {
		int userid = 0;
		userid = this.viewUserid;
		pageSize = 8;
		
		if(pageNo<1){
			pageNo=1;
		}
		
		PhotoService photoService = (PhotoService) this.getBean("photoService");
		UserService  user = (UserService)this.getBean("userService");
		FriendService friendService = (FriendService) this.getBean("friendService");
		
		album = photoService.getAlbumById(albumid);
		boolean viewerisfriend = friendService.isFriendWithThem(this.viewUserid, this.currentUserid);
		if ((album == null || album.getPrivateFlag().equals("2"))
				&& this.viewUserid != this.currentUserid) {
			return "forbid";
		} else if ((album == null || (album.getPrivateFlag().equals("2") && !viewerisfriend))
				&& this.viewUserid != this.currentUserid) {
			return "forbid";
		}
		
		
		
		
		page = photoService.getMyPhotos(userid, albumid,pageSize,pageNo);

		this.albumname = album.getAlbumName();
		
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
		
		
		
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage");
		return SUCCESS;
	}

	public List getPhotolist() {
		return photolist;
	}

	public void setPhotolist(List photolist) {
		this.photolist = photolist;
	}

	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

	public String getAlbumname() {
		return albumname;
	}

	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}

//	public String getUsername() {
//		return this.currentUsername;
//	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}

	public SnsPhotoAlbum getAlbum() {
		return album;
	}

	public void setAlbum(SnsPhotoAlbum album) {
		this.album = album;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
