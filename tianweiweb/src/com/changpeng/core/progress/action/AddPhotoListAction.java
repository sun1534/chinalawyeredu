package com.changpeng.core.progress.action;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.model.CorePublish;
import com.changpeng.sns.photo.service.PhotoService;

public class AddPhotoListAction extends AbstractListAction {

	private int publishid;
	
	private String pageString;
	
	private int contentid;
	
	private String remark;
	@Override
	protected String go() throws Exception {
		this.pageSize=8;
		if(pageNo<1) pageNo=1;
		
		CorePublish publish=(CorePublish)service.get(CorePublish.class, publishid);
		
		
		PhotoService photoservice=(PhotoService)this.getBean("photoService");
		
		page=photoservice.getSelectPhotos(currentUserid, pageSize, pageNo);
		
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPhotoPage");
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

	public int getContentid() {
		return contentid;
	}

	public void setContentid(int contentid) {
		this.contentid = contentid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

}
