/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action;

import com.sxit.common.action.AbstractListAction;
import com.sxit.communicateguard.service.MemService;

/**
 * @author 华锋
 * Nov 16, 20109:42:04 PM
 *
 */
public class SelfDeviceListAction extends AbstractListAction {

	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		MemService service=(MemService)getBean("memService");
		
		this.page=service.getUserDeviceList(this.getLoginUser().getUserid(),deviceName, pageNo, pageSize);
		
		return SUCCESS;
	}
	
	private String deviceName;

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

}
