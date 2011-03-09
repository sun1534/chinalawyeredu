package com.uu800.admin.base.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.uu800.admin.base.AbstractAdminAction;


public class IndexAction extends AbstractAdminAction {
	
	private static Log LOG = LogFactory.getLog(AbstractAdminAction.class);

	@Override
	public String execute() {
		return SUCCESS;
	}
	
	public String left() {
		return SUCCESS;
	}
	
	public String top() {
		return SUCCESS;
	}
	
	public String workspace() {
		return SUCCESS;
	}
}
