/**
 * 显示相册列表
 */
package com.changpeng.sns.photo.action;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.JugeTime;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.FriendService;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.sns.photo.service.PhotoService;

/**
 * 
 * 
 * 
 * @author 华锋
 * May 20, 2009 9:54:36 PM
 *
 */
public class ViewAlbumListAction extends AbstractListAction {
	
	private PaginationSupport albumPage;
	private String pageString;
	private JugeTime jugeTime;
	private String username;
	private String rolename;
	private boolean viewerisfriend;
	
	public ViewAlbumListAction(){
		this.moduleid=16;
	}
	
	@Override
	protected String go() throws Exception {
		int userid = 0;
		userid = this.viewUserid;
		jugeTime = new JugeTime();
		pageSize = 8;
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		UserService  user = (UserService)this.getBean("userService");
		FriendService friendService = (FriendService) this.getBean("friendService");
		
		albumPage = photoService.getMyAlbumList(userid, pageSize, pageNo);
		Userinfo coreUser = (Userinfo)user.getUserinfoById(userid);
		username = coreUser.getUserName();
		viewerisfriend = friendService.isFriendWithThem(this.viewUserid, this.currentUserid);
		
		if(username==null||coreUser.getMobile()==null){
			this.message="您的好友已经不再存在,请返回";
			return "message";
			
		}
		
//		rolename =coreUser.getUserRole();
		if(coreUser.getUserRole()==(short) 1){
			rolename = "同学";
		}
		if(coreUser.getUserRole()==(short) 3){
			rolename = "老师";
		}
		if(coreUser.getUserRole()==(short) 2){
			rolename = "家长";
		}
		if(coreUser.getUserRole()==(short) 4){
			rolename = "老师";
		}
		//log.debug("====================="+albumPage.getItems().size());
		
		if(pageNo<1){
			pageNo=1;
		}
		
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(albumPage, pageNo,"getPage");
		return SUCCESS;
	}


	public PaginationSupport getAlbumPage() {
		return albumPage;
	}

	public void setAlbumPage(PaginationSupport albumPage) {
		this.albumPage = albumPage;
	}

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}


	public JugeTime getJugeTime() {
		return jugeTime; 
	}

	public void setJugeTime(JugeTime jugeTime) {
		this.jugeTime = jugeTime;
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

	public boolean isViewerisfriend() {
		return viewerisfriend;
	}

	public void setViewerisfriend(boolean viewerisfriend) {
		this.viewerisfriend = viewerisfriend;
	}
	
	
}
