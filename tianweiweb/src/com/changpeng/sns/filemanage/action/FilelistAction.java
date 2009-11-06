package com.changpeng.sns.filemanage.action;

import java.util.List;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.sns.filemanage.model.SnsDir;
import com.changpeng.sns.filemanage.service.FileManageService;

public class FilelistAction extends AbstractListAction {

	//目录id
	private int id;
	
	private SnsDir dir;
	
	private String pageString;
	@Override
	protected String go() throws Exception {
		FileManageService filemanageservice=(FileManageService)this.getBean("fileManageService");
		dir=(SnsDir)filemanageservice.get(SnsDir.class, id);
		page=filemanageservice.getDirFilelist(true, id, pageSize, pageNo);
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage");
		return SUCCESS;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SnsDir getDir() {
		return dir;
	}
	public String getPageString() {
		return pageString;
	}
}
