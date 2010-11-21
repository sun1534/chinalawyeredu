/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action.ajax;

import com.sxit.common.action.AbstractAction;
import com.sxit.communicateguard.service.MemService;

/**
 * 删除mem的设备,所有人都没了
 * @author 华锋
 * Nov 16, 20109:42:04 PM
 *
 */
public class MemDeviceDeleteAction extends AbstractAction {

	private int deviceId;
	
	/**
	 * @return the deviceId
	 */
	public int getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		try {
			MemService service = (MemService) getBean("memService");

			service.deleteDevice(deviceId);

		} catch (Exception e) {
			isok = 0;
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private int isok = 1;

	/**
	 * @return the isok
	 */
	public int getIsok() {
		return isok;
	}

}
