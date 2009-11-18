/**
 * 
 */
package com.sxit.communicateguard.action;

import java.text.DateFormat;
import java.util.Date;

import com.sxit.common.action.AbstractListAction;
import com.sxit.communicateguard.service.GuardService;
import com.sxit.query.service.QueryService;

/**
 * 
 * 保障号码列表
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class GuardNosAction extends AbstractListAction {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String mobile;
	

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}


	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		GuardService service=(GuardService)this.getBean("guardService");
		
		this.page=service.getFocusMobiles(mobile, pageNo, pageSize);
			return SUCCESS;
	}

}
