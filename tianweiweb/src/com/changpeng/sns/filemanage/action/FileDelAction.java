package com.changpeng.sns.filemanage.action;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.sns.filemanage.service.FileManageService;

public class FileDelAction extends AbstractListAction {

	//目录id
	private int id;
	
	@Override
	protected String go() throws Exception {
		FileManageService filemanageservice=(FileManageService)this.getBean("fileManageService");
		filemanageservice.deleteFile(id,this.currentUserid);
		this.result="0";
		return "result";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
