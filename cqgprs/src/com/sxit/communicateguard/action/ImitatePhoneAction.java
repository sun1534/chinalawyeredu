/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action;

import java.util.HashMap;
import java.util.List;

import com.sxit.common.CommonDatas;
import com.sxit.common.Globals;
import com.sxit.common.action.AbstractListAction;
import com.sxit.communicateguard.service.MemService;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.system.SysUser;

/**
 * @author 肖云亮
 * Nov 16, 20109:42:04 PM
 *
 */
public class ImitatePhoneAction extends AbstractListAction {

	public List devicelist;
	public HashMap<String, MemDevice> memdevice;
	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		SysUser sysUser=this.getLoginUser();
		if(sysUser==null)
		{
			return this.ERROR;
		}
		
		MemService memservice=(MemService) Globals.getBean("memService");
		devicelist=memservice.getUserDeviceList(sysUser.getUserid(), 1, Integer.MAX_VALUE).getItems();
		memdevice=CommonDatas.LOGINDEVICE.get(sysUser.getUserid()+"");
		return SUCCESS;
	}
	public List getDevicelist() {
		return devicelist;
	}
	public void setDevicelist(List devicelist) {
		this.devicelist = devicelist;
	}
	public HashMap<String, MemDevice> getMemdevice() {
		return memdevice;
	}
	public void setMemdevice(HashMap<String, MemDevice> memdevice) {
		this.memdevice = memdevice;
	}
	
	
}
