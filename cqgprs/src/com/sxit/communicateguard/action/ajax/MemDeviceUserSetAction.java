/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sxit.common.action.AbstractAction;
import com.sxit.communicateguard.service.MemService;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysUserService;

/**
 * 设置这个终端给哪些个用户使用
 * 
 * @author 华锋 Nov 16, 20109:42:04 PM
 * 
 */
public class MemDeviceUserSetAction extends AbstractAction {

	private int deviceId;

	private int[] userIds;
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
			MemService service = (MemService) getBean("memService");

			if(userIds!=null){
				for(int i:userIds){
					System.out.println("==="+i);
				}
			}
			
			service.setUserDevice(deviceId, userIds);

		} catch (Exception e) {
			isok = 0;
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String input() throws Exception {

		// 得到所有的用户列表

		MemDevice device = (MemDevice) basicService.get(MemDevice.class, deviceId);
		if(device!=null)
			deviceName=device.getDevicename();
		
		// 得到这个设备的所有userid
		MemService service = (MemService) getBean("memService");
		SysUserService userService = (SysUserService) getBean("sysUserService");
		useridList = service.getDeviceUserList(deviceId);
		
		List list = basicService.findAll(SysUser.class);

		for (int i = 0; list != null && i < list.size(); i++) {
			SysUser user = (SysUser) list.get(i);
//			alluseridList.add(user.getUserid());
			alluseridList.add(user);
			alluserList.put(user.getUserid(), user.getUsername());

		}

		return INPUT;
	}

	private List alluseridList = new ArrayList();
	private Map alluserList = new HashMap();
	private List useridList = new ArrayList();

	private String deviceName;
	public String getDeviceName(){
		return this.deviceName;
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

	/**
	 * @param userIds
	 *            the userIds to set
	 */
	public void setUserIds(int[] userIds) {
		this.userIds = userIds;
	}

	/**
	 * @return the alluseridList
	 */
	public List getAlluseridList() {
		return alluseridList;
	}

	/**
	 * @return the alluserList
	 */
	public Map getAlluserList() {
		return alluserList;
	}

	/**
	 * @return the useridList
	 */
	public List getUseridList() {
		return useridList;
	}

}