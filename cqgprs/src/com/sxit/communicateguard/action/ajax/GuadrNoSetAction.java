/**
 * 
 */
package com.sxit.communicateguard.action.ajax;

import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.action.AbstractAction;
import com.sxit.communicateguard.service.GuardService;
import com.sxit.netquality.service.BasicSetService;

/**
 * 
 * 设置或者取消重点小区设置
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class GuadrNoSetAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(GuadrNoSetAction.class);

	private String now;
	private String mobile;
	private String isok;
	private int isdelete;

	/**
	 * @param isdelete
	 *            the isdelete to set
	 */
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public int getIsdelete() {
		return this.isdelete;
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
	 * @return the isok
	 */
	public String getIsok() {
		return isok;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		BasicSetService setservice = (BasicSetService) this.getBean("basicSetService");
		GuardService service=(GuardService)this.getBean("guardService");

		setservice.getAllSets();
		try {
			if (isdelete == 0)
				if(service.isExistMobile(mobile))
					isok="4";
				else{
				setservice.setFocusMobile(mobile, this.getLoginUser().getUserid(), this.getLoginUser().getUsername(),
						true);
				isok = "1";
				}
			else {

				StringTokenizer st = new StringTokenizer(mobile, ",");
				while (st.hasMoreTokens()) {
					String mobile1 = st.nextToken();
					_LOG.debug("mobile1:::" + mobile1);
					setservice.setFocusMobile(mobile1, this.getLoginUser().getUserid(), this.getLoginUser()
							.getUsername(), false);
				}
				isok = "1";
			}
			
		} catch (Exception e) {
			isok = "2";
			_LOG.error("保障号码设置错误", e);
		}
		return SUCCESS;
	}

	/**
	 * @param now
	 *            the now to set
	 */
	public void setNow(String now) {
		this.now = now;
	}

}
