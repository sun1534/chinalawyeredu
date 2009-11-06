package com.changpeng.sns.filemanage.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.sns.filemanage.model.SnsDir;
import com.changpeng.sns.filemanage.service.FileManageService;


public class EditDirAction extends AbstractAction {
	
	private int id;
	private SnsDir dir;
	private String dirname;
	private String description;

	public EditDirAction(){
	}
	
	@Override
	protected String go() throws Exception {
		
		FileManageService filemanageservice=(FileManageService)this.getBean("fileManageService");
		
		filemanageservice.editDir(id,dirname,description);
		
		return SUCCESS;
	}
	
	protected String getin(){
		FileManageService filemanageservice=(FileManageService)this.getBean("fileManageService");
		dir=(SnsDir)filemanageservice.get(SnsDir.class, id);
		return INPUT;
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

	public void setDir(SnsDir dir) {
		this.dir = dir;
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

}
