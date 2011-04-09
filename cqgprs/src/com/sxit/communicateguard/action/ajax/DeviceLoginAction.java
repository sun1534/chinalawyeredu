/**
 * 
 */
package com.sxit.communicateguard.action.ajax;

import java.net.URLDecoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.Globals;
import com.sxit.common.action.AbstractAction;
import com.sxit.communicateguard.service.MemService;
import com.sxit.models.mem.MemDevice;

/**
 * 
 * 设置或者取消重点apn设置
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class DeviceLoginAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(GuadrNoSetAction.class);
	private String now;
	private String username;
	private String password;
	private int deviceId;

	private String isok;

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
		MemService memservice = (MemService) Globals.getBean("memService");
		MemDevice device = (MemDevice) memservice
				.get(MemDevice.class, deviceId);

		String usernametemp = new URLDecoder().decode(username, "utf-8");
		String passwordtemp = new URLDecoder().decode(password, "utf-8");
		System.out.println("username==" + username + "password=" + password);
		// System.out.println("username1=="+username1+"password1="+password1);
		if (device == null) {
			isok = "2";
			return SUCCESS;
		}
		if (device.getLoginName().equals(usernametemp)
				&& device.getLoginPwd().equals(passwordtemp)) {
			isok = "1";
		} else {
			isok = "2";
		}
		try {
		} catch (Exception e) {
			isok = "2";
			_LOG.error("登录失败", e);
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getNow() {
		return now;
	}

	public void setIsok(String isok) {
		this.isok = isok;
	}

}
