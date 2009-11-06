package com.changpeng.core.home;


import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.InfoService;

public class ProductindexAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	List productlist;
	
	public ProductindexAction() {
		this.rightCode="PUBLIC";
		this.moduleid = 11;
	}

	@Override
	protected String go() throws Exception {
		InfoService userservice = (InfoService) this.getBean("infoService");
		productlist=userservice.getProductindex();
		return SUCCESS;
	}

	public List getProductlist() {
		return productlist;
	}
}
