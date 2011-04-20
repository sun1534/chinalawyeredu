/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sxit.common.CommonDatas;
import com.sxit.common.action.AbstractListAction;
import com.sxit.communicateguard.service.MemService;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.system.SysUser;

/**
 * @author 华锋 Nov 16, 20109:42:04 PM
 * 
 */
public class ImitateCommandAction extends AbstractListAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		MemService service = (MemService) getBean("memService");
		SysUser sysUser=this.getLoginUser();
//		commandlist = service.getCommandList(deviceId, 0,name, status, 1,Integer.MAX_VALUE).getItems();
		commandlist=service.getCommandList(deviceId, status, name, 1, Integer.MAX_VALUE).getItems();
		memdevice=CommonDatas.LOGINDEVICE.get(sysUser.getUserid()+"");
		
		return SUCCESS;
	}

	public List commandlist;
	public HashMap<String, MemDevice> memdevice;

	private int status = 1;

	private int deviceId;

	private String name;

	/**
	 * @return the deviceName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param deviceName
	 *            the deviceName to set
	 */
	public void setName(String deviceName) {
		this.name = deviceName;
	}

	/**
	 * @return the deviceId
	 */
	public int getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId
	 *            the deviceId to set
	 */
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List getCommandlist() {
		return commandlist;
	}

	public void setCommandlist(List commandlist) {
		this.commandlist = commandlist;
	}

	public HashMap<String, MemDevice> getMemdevice() {
		return memdevice;
	}

	public void setMemdevice(HashMap<String, MemDevice> memdevice) {
		this.memdevice = memdevice;
	}

	
	
}
