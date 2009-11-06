package com.changpeng.core.progress.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import beartool.MD5;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;
import com.changpeng.sns.diary.service.ProDiaryService;
import com.changpeng.sns.filemanage.service.FileManageService;
import com.changpeng.sns.photo.service.PhotoService;

public class AddAudioListAction extends AbstractListAction {

	private int publishid;
	
	private String pageString;
	@Override
	protected String go() throws Exception {
		if(pageNo<1) pageNo=1;
		
		CorePublish publish=(CorePublish)service.get(CorePublish.class, publishid);
		
		
		FileManageService fileservice=(FileManageService)this.getBean("fileManageService");
		
		page=fileservice.getUserFilelist(true,currentUserid, pageSize, pageNo);
		
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage");
		return SUCCESS;
	}
	
	public int getPublishid() {
		return publishid;
	}
	public void setPublishid(int publishid) {
		this.publishid = publishid;
	}
	public String getPageString() {
		return pageString;
	}
	public void setPageString(String pageString) {
		this.pageString = pageString;
	}

	

}
