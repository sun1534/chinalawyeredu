/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action.ajax;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sxit.common.action.AbstractAction;
import com.sxit.communicateguard.service.MemService;
import com.sxit.models.mem.MemCommand;
import com.sxit.models.mem.MemDevice;

/**
 * @author 华锋 Nov 16, 20109:42:04 PM
 * 
 */
public class MemDeviceCommandCreateEditAction extends AbstractAction {
private int commandtype;
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
//关联到哪些设备
	private List selectdevice;
	/**
	 * @return the selectdevice
	 */
	public List getSelectdevice() {
		return selectdevice;
	}

	/**
	 * @param selectdevice the selectdevice to set
	 */
	public void setSelectdevice(List selectdevice) {
		this.selectdevice = selectdevice;
	}

	
	private MemService memservice;
	public MemDeviceCommandCreateEditAction(){
		memservice=(MemService)getBean("memService");
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
			

			System.out.println("command==========="+isnew+",,,,,"+command.getCommandid());
			
			if (isnew == 1) {
		
				command.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				command.setCreateuser(this.getLoginUser().getUserid());
				command.setCreateusername(getLoginUser().getUsername());
//				basicService.save(command);
				memservice.addCommand(selectdevice,command);
				this.message = "MEM设备命令信息新增成功";
				isok=1;
			} else {

				memservice.updateCommand(selectdevice,command);
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
		selectdevice=new ArrayList();
		MemService service=(MemService)getBean("memService");
		List list=null;
		if(commandtype==3) //自定义命令的话，只显示自己的设备
			list=service.getUserDeviceList(this.getLoginUser().getUserid(),1,Integer.MAX_VALUE).getItems();
		else
			list=service.getDeviceList(null, 1, Integer.MAX_VALUE).getItems();
		if (isnew == 0) {			
			this.selectdevice=memservice.getDevicesByCommandId(commandId);			
			command = (MemCommand) basicService.get(MemCommand.class, commandId);
		} else {
			command = new MemCommand();
			command.setCommandtype(commandtype);
			command.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		}
		
		deviceList =new LinkedHashMap();
		for(int i=0;i<list.size();i++){
			MemDevice device=(MemDevice)list.get(i);
			deviceList.put(device.getDeviceid(), device.getDevicename());
		}
		
		set("command", command);

		return INPUT;
	}

	private Map deviceList;
	public Map getDeviceList(){
		return this.deviceList;
	}
	
	private MemCommand command;

	/**
	 * @return the device
	 */
	public MemCommand getCommand() {
		if (command == null)
			command = (MemCommand) get("command");
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

	/**
	 * @return the commandtype
	 */
	public int getCommandtype() {
		return commandtype;
	}

	/**
	 * @param commandtype the commandtype to set
	 */
	public void setCommandtype(int commandtype) {
		this.commandtype = commandtype;
	}


}
