/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action.ajax;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sxit.common.action.AbstractAction;
import com.sxit.communicateguard.service.MemService;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;

/**
 * @author 华锋 Nov 16, 20109:42:04 PM
 * 
 */
public class MemDeviceCommandCreateEditAction extends AbstractAction {

	private int commandId;
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
		try {
			if (isnew == 1) {
		
				command.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				command.setCreateuser(this.getLoginUser().getUserid());
				command.setCreateusername(getLoginUser().getUsername());
				basicService.save(command);
				this.message = "MEM设备命令信息新增成功";
				isok=1;
			} else {

				basicService.update(command);

				this.message = "MEM设备命令信息更新成功";
				isok=2;
				
			}
		} catch (Exception e) {
			isok = 0;
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String input() throws Exception {
		
		MemService service=(MemService)getBean("memService");
		List list=service.getDeviceList(null, 1, Integer.MAX_VALUE).getItems();
		deviceList =new LinkedHashMap();
		for(int i=0;i<list.size();i++){
			MemDevice device=(MemDevice)list.get(i);
			deviceList.put(device.getDeviceid(), device.getDevicename());
		}
		
		System.out.println("===============command==="+commandId);
		if (isnew == 0) {
			command = (MemDevicecommand) basicService.get(MemDevicecommand.class, commandId);

		} else {
			command = new MemDevicecommand();
			command.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		}
		
		set("command", command);

		return INPUT;
	}

	private Map deviceList;
	public Map getDeviceList(){
		return this.deviceList;
	}
	
	private MemDevicecommand command;

	/**
	 * @return the device
	 */
	public MemDevicecommand getCommand() {
		if (command == null)
			command = (MemDevicecommand) get("command");
		return command;
	}

	/**
	 * @return the commandId
	 */
	public int getCommandId() {
		return commandId;
	}

	/**
	 * @param commandId the commandId to set
	 */
	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}


}
