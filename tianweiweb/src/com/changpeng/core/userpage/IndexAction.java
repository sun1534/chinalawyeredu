package com.changpeng.core.userpage;


import java.util.List;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.sns.diary.service.ProDiaryService;
import com.changpeng.sns.photo.service.PhotoService;

public class IndexAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(IndexAction.class);
	
	private int userid;
	CoreUser user;
	private List infos;
	private List photos;
	
	public IndexAction() {
		this.rightCode="PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		UserService service = (UserService) this.getBean("userService");
		ProDiaryService diaryService = (ProDiaryService) this.getBean("proDiaryService");
		PhotoService photoService = (PhotoService)this.getBean("photoService");
		
		user = service.getUserById(userid);
		
		infos = diaryService.getViewDiaryListByUserID(userid,10);
		
		photos=photoService.getUserPhotos(userid,10);
		
		
		return SUCCESS;

	}

	public CoreUser getUser() {
		return user;
	}

	public List getInfos() {
		return infos;
	}

	public List getPhotos() {
		return photos;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
