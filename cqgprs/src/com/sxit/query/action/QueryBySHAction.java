/**
 * 
 */
package com.sxit.query.action;

import java.text.DateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.action.AbstractListAction;
import com.sxit.query.model.MobileApnState;
import com.sxit.query.service.EricssonQuery;
import com.sxit.query.service.HuaweiQuery;
import com.sxit.query.service.SHQueryService;
import com.sxit.query.service.UserQuery;

/**
 * 
 * 脚本的方式执行查询
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class QueryBySHAction extends AbstractListAction {
	private static Log _LOG = LogFactory.getLog(QueryBySHAction.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String random;
	private String mobile;
	private String sgsnid="SGSNCQ02";

	/**
	 * @return the random
	 */
	public String getRandom() {
		return random;
	}

	/**
	 * @param random
	 *            the random to set
	 */
	public void setRandom(String random) {
		this.random = random;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the sgsnid
	 */
	public String getSgsnid() {
		return sgsnid;
	}

	/**
	 * @param sgsnid
	 *            the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		SHQueryService service = (SHQueryService) getBean("shQueryService");
		long now = System.currentTimeMillis();

		System.out.println("sgsnid:::::::::"+sgsnid);
		maslist = service.queryUserState(sgsnid, mobile, random,this.getLoginUser());
		_LOG.debug("执行时间::::" + (System.currentTimeMillis() - now));
	
		return SUCCESS;
	}

	private List<MobileApnState> maslist;

	public List<MobileApnState> getMaslist() {
		return maslist;
	}
}