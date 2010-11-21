/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sxit.common.action.AbstractListAction;
import com.sxit.communicateguard.service.MemService;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.models.system.SysUser;

/**
 * 根据userId等显示这个人的mem设备操作日志信息
 * @author 华锋
 * Nov 16, 20109:42:04 PM
 *
 */
public class MemLogListAction extends AbstractListAction {

	private java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
	private int userId;
	private int commandId=-5;
	private int deviceId;
	private String start;
	private String end;
	
	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		MemService service=(MemService)getBean("memService");
		
		Timestamp _start=null;
		Timestamp _end=null;
		try{
			if(start!=null&&!start.equals(""))
				_start=new java.sql.Timestamp(df.parse(start).getTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			if(end!=null&&!end.equals(""))
				_end=new java.sql.Timestamp(df.parse(end).getTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		this.page=service.getLogList(userId, deviceId, commandId, _start, _end, pageNo, pageSize);
		
		
		List list = basicService.findAll(SysUser.class);

		for (int i = 0; list != null && i < list.size(); i++) {
			SysUser user = (SysUser) list.get(i);
			userList.put(user.getUserid(), user.getUsername());

		}
		
		List list1=service.getDeviceList(null, 1, Integer.MAX_VALUE).getItems();
		for(int i=0;i<list1.size();i++){
			MemDevice device=(MemDevice)list1.get(i);
			deviceList.put(device.getDeviceid(), device.getDevicename());
		}

		List list2= basicService.findAll(MemDevicecommand.class);
		for(int i=0;i<list2.size();i++){
			MemDevicecommand command=(MemDevicecommand)list2.get(i);
			commandList.put(command.getCommandid(), command.getCommananame());
		}
		
		return SUCCESS;
	}
	
	private Map userList=new HashMap();
	private Map commandList=new HashMap();
	private Map deviceList=new HashMap();

	/**
	 * @return the userList
	 */
	public Map getUserList() {
		return userList;
	}

	/**
	 * @return the deviceList
	 */
	public Map getDeviceList() {
		return deviceList;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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

	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * @return the commandList
	 */
	public Map getCommandList() {
		return commandList;
	}
	
	
}
