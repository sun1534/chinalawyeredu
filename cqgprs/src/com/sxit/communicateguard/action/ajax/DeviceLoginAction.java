/**
 * 
 */
package com.sxit.communicateguard.action.ajax;

import java.net.URLDecoder;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.CommonDatas;
import com.sxit.common.Globals;
import com.sxit.common.action.AbstractAction;
import com.sxit.communicateguard.service.MemService;
import com.sxit.memdevice.common.Client;
import com.sxit.memdevice.common.ClientHW;
import com.sxit.memdevice.common.ClientTZ;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDeviceTransit;
import com.sxit.models.system.SysUser;

/**
 * 
 * 设置或者取消重点apn设置
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class DeviceLoginAction extends AbstractAction {

	private static Log _LOG = LogFactory.getLog(GuadrNoSetAction.class);
	private int flag; // 1表示输入用户密码登录 2表示不用登录
	private String now;
	private String username;
	private String password;
	private String password1;
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
		MemDevice device = (MemDevice) memservice.get(MemDevice.class, deviceId);
		SysUser sysUser = this.getLoginUser();
		HashMap<String, MemDevice> deviceList = CommonDatas.LOGINDEVICE.get(sysUser.getUserid() + "");
		// System.out.println("username1=="+username1+"password1="+password1);
		if (device == null) {
			isok = "2";
			return SUCCESS;
		}
		if (flag == 2) {
			isok = "1";
			if (deviceList == null || deviceList.size() == 0)
				deviceList = new HashMap<String, MemDevice>();

			// deviceList.put(device.getDeviceid() + "", device);
			// CommonDatas.LOGINDEVICE.put(sysUser.getUserid() + "",
			// deviceList);
			return SUCCESS;
		}

		
		
		
		String usernametemp = new URLDecoder().decode(username, "utf-8");
		String passwordtemp = new URLDecoder().decode(password, "utf-8");
		
		username=usernametemp;
		password=passwordtemp;
		
		System.out.println(password1+",,,,"+password+",,,"+passwordtemp);
		System.out.println("username==" + username + "password=" + password);

		String flag = "";
		if (device.getIstransit() == 1&&device.getIshuawei()==0) {
			int deviceid = device.getDeviceid();
			com.sxit.models.mem.MemDeviceTransit mdt = (MemDeviceTransit) basicService.get(MemDeviceTransit.class,
					deviceid);
			flag = ClientTZ
					.testlogin(device.getIp(), usernametemp, passwordtemp, mdt.getIp(), mdt.getLoginname(), mdt.getPwd());
		} else if (device.getIshuawei() == 1&&device.getIstransit()==0) {
			flag = ClientHW.testlogin(device.getIp(), usernametemp, passwordtemp);
		} else if (device.getIshuawei() == 1 && device.getIstransit() == 1) {
			int deviceid = device.getDeviceid();
			com.sxit.models.mem.MemDeviceTransit mdt = (MemDeviceTransit) basicService.get(MemDeviceTransit.class,
					deviceid);
			flag =	com.sxit.memdevice.common.ClientTZHW.testlogin(device.getIp(), usernametemp, passwordtemp, mdt.getIp(), mdt
					.getLoginname(), mdt.getPwd());

		} else
			flag = Client.testlogin(device.getIp(), usernametemp, passwordtemp);
		if (flag.equals("OK")) {
			isok = "1";
			if (deviceList == null || deviceList.size() == 0)
				deviceList = new HashMap<String, MemDevice>();

			device.setLoginName(usernametemp);
			device.setLoginPwd(passwordtemp);
			deviceList.put(device.getDeviceid() + "", device);
			CommonDatas.LOGINDEVICE.put(sysUser.getUserid() + "", deviceList);
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

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
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

	/**
	 * @param password1 the password1 to set
	 */
	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	/**
	 * @return the password1
	 */
	public String getPassword1() {
		return password1;
	}

}
