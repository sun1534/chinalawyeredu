/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action.ajax;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDeviceTransit;

/**
 * @author 华锋 Nov 16, 20109:42:04 PM
 * 
 */
public class MemDeviceCreateEditAction extends AbstractAction {

	private int deviceId;
	private int isnew = 1;

	/**
	 * @return the isnew
	 */
	public int getIsnew() {
		return isnew;
	}

	/**
	 * @param isnew
	 *            the isnew to set
	 */
	public void setIsnew(int isnew) {
		this.isnew = isnew;
	}

	private int isok = 1;

	/**
	 * @return the isok
	 */
	public int getIsok() {
		return isok;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("isnew============="+isnew);
		
		try {
			if (isnew == 1) {
				
				System.out.println("devicename:::"+device.getDevicename());
				
				device.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				device.setCreateuser(this.getLoginUser().getUserid());
				device.setCreateusername(getLoginUser().getUsername());
				basicService.save(device);
				
				transit.setDeviceid(device.getDeviceid());
				basicService.save(transit);
				this.message = "MEM设备信息新增成功";
				isok=1;
			} else {

				basicService.update(device);
				
				basicService.saveOrupdate(transit);
				
				
				this.message = "MEM设备信息更新成功";
				isok=2;
				
			}
		} catch (Exception e) {
			isok = 0;
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String input() throws Exception {

		MemDevice device = null;
		if (isnew == 0) {
			device = (MemDevice) basicService.get(MemDevice.class, deviceId);
			transit= (MemDeviceTransit) basicService.get(MemDeviceTransit.class, deviceId);
			
			if(transit==null){
				transit= new MemDeviceTransit();
				transit.setDeviceid(deviceId);
			}
		} else {
			device = new MemDevice();
			device.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			transit= new MemDeviceTransit();
		}
	
		
		set("device", device);
		set("transit", transit);
		return INPUT;
	}

	private MemDevice device;
	private MemDeviceTransit transit;

	/**
	 * @return the device
	 */
	public MemDevice getDevice() {
		if (device == null)
			device = (MemDevice) get("device");
		return device;
	}
	
	public MemDeviceTransit getTransit() {
		if (transit == null)
			transit = (MemDeviceTransit) get("transit");
		return transit;
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

}
