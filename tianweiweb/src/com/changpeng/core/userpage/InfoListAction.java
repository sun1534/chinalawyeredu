package com.changpeng.core.userpage;


import java.util.List;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.sns.diary.service.ProDiaryService;
import com.changpeng.sns.photo.service.PhotoService;

public class InfoListAction extends AbstractListAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(IndexAction.class);
	
	private int userid;
	private String pageString;
	CoreUser user;
	public InfoListAction() {
		this.rightCode="PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		
		ProDiaryService diaryService = (ProDiaryService) this.getBean("proDiaryService");
		UserService service = (UserService) this.getBean("userService");
		user = service.getUserById(userid);
		
		page=diaryService.getCurrProDiaryListByUserID(userid, pageSize, pageNo);
		
		Pagefoot pagefoot=new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage");;
		return SUCCESS;

	}
	public CoreUser getUser() {
		return user;
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

	public int getUserid() {
		return userid;
	}

}
