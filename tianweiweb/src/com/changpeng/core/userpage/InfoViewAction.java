package com.changpeng.core.userpage;


import java.util.List;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.sns.diary.model.SnsDiary;
import com.changpeng.sns.diary.service.ProDiaryService;
import com.changpeng.sns.photo.service.PhotoService;

public class InfoViewAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(InfoViewAction.class);
	
	private int diaryid;
	private int userid;
	SnsDiary diary;
	CoreUser user;
	public InfoViewAction() {
		this.rightCode="PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		
		UserService service = (UserService) this.getBean("userService");
		user = service.getUserById(userid);
		diary=(SnsDiary)service.get(SnsDiary.class, diaryid);
		
		return SUCCESS;

	}




	public int getDiaryid() {
		return diaryid;
	}

	public void setDiaryid(int diaryid) {
		this.diaryid = diaryid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public SnsDiary getDiary() {
		return diary;
	}

	public void setDiary(SnsDiary diary) {
		this.diary = diary;
	}

	public int getUserid() {
		return userid;
	}

	public CoreUser getUser() {
		return user;
	}

	public void setUser(CoreUser user) {
		this.user = user;
	}

}
