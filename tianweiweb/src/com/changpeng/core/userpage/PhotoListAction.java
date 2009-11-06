package com.changpeng.core.userpage;


import java.util.List;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.sns.diary.service.ProDiaryService;
import com.changpeng.sns.photo.service.PhotoService;

public class PhotoListAction extends AbstractListAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(PhotoListAction.class);
	
	private int userid;

	private String pageString;
	CoreUser user;
	public PhotoListAction() {
		this.rightCode="PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		UserService service = (UserService) this.getBean("userService");
		user = service.getUserById(userid);	
		
		page=photoService.getPageViewPhotos(userid, pageSize, pageNo);
		Pagefoot pagefoot=new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage");;
		return SUCCESS;

	}

	public CoreUser getUser() {
		return user;
	}
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}

}
