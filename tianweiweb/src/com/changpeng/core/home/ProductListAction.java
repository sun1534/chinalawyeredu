package com.changpeng.core.home;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.common.util.Pagefoot;
import com.changpeng.core.service.InfoService;
import com.changpeng.core.service.ProgressService;
import com.changpeng.core.service.UserService;


public class ProductListAction extends AbstractListAction {

	private String pageString;

	public ProductListAction(){
		this.rightCode="PUBLIC";
	}
	
	@Override
	protected String go() throws Exception {
		InfoService userservice = (InfoService)this.getBean("infoService");
		
		page = userservice.getProductlist(pageSize, pageNo,this.currentRole);

		Pagefoot pagefoot = new Pagefoot();
		pageString = pagefoot.packString(page, pageNo,"getPage");
		return SUCCESS;
	}
	


	public String getPageString() {
		return pageString;
	}

}
