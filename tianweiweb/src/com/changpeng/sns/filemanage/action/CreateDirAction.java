package com.changpeng.sns.filemanage.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.filemanage.service.FileManageService;


/**
 * 
 * 新增一个目录
 *
 */
public class CreateDirAction extends AbstractAction {
	
	private String dirname;
	
	private String description;
	
	private int dirtype;
	
	public CreateDirAction(){
		this.moduleid=16;
	}
	
	@Override
	protected String go() throws Exception {
		
		FileManageService filemanageservice=(FileManageService)this.getBean("fileManageService");

		filemanageservice.createDir(dirname, description, dirtype, currentUserid);

		this.result="ok";
		return SUCCESS;

	}
	
	protected String getin() {
		
		return INPUT;
	}

	public String getDirname() {
		return dirname;
	}

	public void setDirname(String dirname) {
		this.dirname = dirname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDirtype() {
		return dirtype;
	}

	public void setDirtype(int dirtype) {
		this.dirtype = dirtype;
	}

	
}
