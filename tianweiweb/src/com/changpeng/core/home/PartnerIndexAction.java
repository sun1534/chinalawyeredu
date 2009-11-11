package com.changpeng.core.home;


import java.util.List;

import org.apache.log4j.Logger;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.service.UserService;

public class PartnerIndexAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(PartnerIndexAction.class);
	
	List partnerlist;
	
	public PartnerIndexAction() {
		this.rightCode="PUBLIC";
		this.moduleid = 11;
	}

	@Override
	protected String go() throws Exception {
		UserService userservice = (UserService) this.getBean("userService");
		partnerlist=userservice.getPartnerlistindex();
		
		this.allcnt=userservice.getPartnerCnts();
		return SUCCESS;
	}

	public List getPartnerlist() {
		return partnerlist;
	}
	private int allcnt;
	public int getAllcnt(){
		return this.allcnt;
	}
}
