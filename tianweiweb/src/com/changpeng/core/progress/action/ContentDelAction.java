package com.changpeng.core.progress.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CorePublishContent;
import com.changpeng.core.service.ProgressService;

public class ContentDelAction extends AbstractAction {

	private int id;
	private int publishid;
	public ContentDelAction() {
	}

	@Override
	protected String go() throws Exception {
		
		ProgressService progressService=(ProgressService)this.getBean("progressService");
		
		CorePublishContent content=(CorePublishContent)progressService.get(CorePublishContent.class, id);
		
		if(content.getPublishid()==publishid){
			service.delete(content);
		}
		this.result="ok";
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPublishid() {
		return publishid;
	}

	public void setPublishid(int publishid) {
		this.publishid = publishid;
	}

	
}
