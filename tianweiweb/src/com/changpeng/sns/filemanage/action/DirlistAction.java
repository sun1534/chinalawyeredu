package com.changpeng.sns.filemanage.action;

import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.filemanage.service.FileManageService;

public class DirlistAction extends AbstractAction {

	private List dirs;
	
	private int dirtype;
	
	@Override
	protected String go() throws Exception {
		FileManageService filemanageservice=(FileManageService)this.getBean("fileManageService");
		if(dirtype==0){
			dirtype=1;
		}
		dirs=filemanageservice.getDirs(currentUserid,dirtype);
		return SUCCESS;
	}
	public List getDirs() {
		return dirs;
	}
	public int getDirtype() {
		return dirtype;
	}
	public void setDirtype(int dirtype) {
		this.dirtype = dirtype;
	}

}
