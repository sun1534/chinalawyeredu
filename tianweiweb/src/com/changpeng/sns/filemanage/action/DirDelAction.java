package com.changpeng.sns.filemanage.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.filemanage.service.FileManageService;

public class DirDelAction extends AbstractAction {
	
	private int dirid;
	
	@Override
	protected String go() throws Exception {
		FileManageService filemanageservice=(FileManageService)this.getBean("fileManageService");
		filemanageservice.deleteDir(dirid,this.currentUserid);
		this.result="0";
		return "result";
	}

	public void setDirid(int dirid) {
		this.dirid = dirid;
	}
	

}
