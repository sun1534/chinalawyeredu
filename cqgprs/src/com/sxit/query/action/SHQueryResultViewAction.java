/**
 * 
 */
package com.sxit.query.action;

import java.text.DateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.action.AbstractAction;
import com.sxit.query.service.SHQueryService;

/**
 * 
 * 执行脚本的方式获得数据
 * 
 * @author 华锋 Nov 5, 2009-10:32:58 PM
 * 
 */
public class SHQueryResultViewAction extends AbstractAction {
	private static Log _LOG = LogFactory.getLog(SHQueryResultViewAction.class);

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String random;
	private String mobile;

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

	private String now;

	/**
	 * @return the now
	 */
	public String getNow() {
		return now;
	}

	/**
	 * @param now
	 *            the now to set
	 */
	public void setNow(String now) {
		this.now = now;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		System.out.println("key:::" + (mobile + random));

		this.viewlist = SHQueryService.QUERY_RESULT.get(mobile + ","+random);
		if (viewlist != null) {
			String s = viewlist.get(viewlist.size() - 1).toString();
			if (s.indexOf("isover") != -1) {
				hasover = true;
				viewlist.remove(viewlist.size() - 1);// 去掉最后一个自己加上的isover
			}
		}
		// System.out.println(viewlist);
		return SUCCESS;
	}

	private boolean hasover;

	public boolean getHasover() {
		return this.hasover;
	}

	private List<String> viewlist;

	public List<String> getViewlist() {
		return this.viewlist;
	}
}