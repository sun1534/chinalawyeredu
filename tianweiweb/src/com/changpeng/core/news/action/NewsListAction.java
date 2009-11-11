package com.changpeng.core.news.action;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.model.TinfInfo;
import com.changpeng.core.service.InfoService;


public class NewsListAction extends AbstractListAction {

	private int type;
	
	private TinfInfo info;
	
	private String pageString;
	public NewsListAction(){
		this.rightCode="PUBLIC";
		
	}
	
	@Override
	protected String go() throws Exception {
		if(pageNo==0) pageNo=1;
		InfoService infoservice=(InfoService)this.getBean("infoService");
		page=infoservice.getInfolist(pageSize, pageNo, type);
		
		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage");
		return SUCCESS;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public TinfInfo getInfo() {
		return info;
	}

	public void setInfo(TinfInfo info) {
		this.info = info;
	}

	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}

}
