package com.changpeng.core.progress.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.model.CorePublishContent;
import com.changpeng.core.service.ProgressService;

public class EditContentAction extends AbstractAction {

	private int publishid;
	private String content;
	
	public EditContentAction() {
	}

	@Override
	protected String go() throws Exception {
		
		ProgressService progressService=(ProgressService)this.getBean("progressService");
		CorePublish publish=(CorePublish)service.get(CorePublish.class, publishid);
		publish.setRemarks(content);
		System.out.println("content:"+content);
		publish.setStatusid((short)2);
		if(publish.getFee()==0.0){
			publish.setStatusid((short)3);
			
		}
		progressService.update(publish);
		this.result="ok";
		return SUCCESS;
	}

	public int getPublishid() {
		return publishid;
	}

	public void setPublishid(int publishid) {
		this.publishid = publishid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
