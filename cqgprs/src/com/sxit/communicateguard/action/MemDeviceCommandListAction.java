/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sxit.common.action.AbstractListAction;
import com.sxit.communicateguard.service.MemService;
import com.sxit.models.mem.MemDevice;

/**
 * @author 华锋
 * Nov 16, 20109:42:04 PM
 *
 */
public class MemDeviceCommandListAction extends AbstractListAction {

	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		MemService service=(MemService)getBean("memService");
		
		this.page=service.getCommandList(deviceId, name, pageNo, pageSize);
		
		List list=service.getDeviceList(null, 1, Integer.MAX_VALUE).getItems();
		deviceList =new LinkedHashMap();
		for(int i=0;i<list.size();i++){
			MemDevice device=(MemDevice)list.get(i);
			deviceList.put(device.getDeviceid(), device.getDevicename());
		}
		
		return SUCCESS;
	}
	private Map deviceList;
	public Map getDeviceList(){
		return this.deviceList;
	}
	
	private int deviceId;
	
	private String name;

	/**
	 * @return the deviceName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param deviceName the deviceName to set
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
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

}
