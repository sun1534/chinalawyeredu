/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action.ajax;

import com.sxit.common.action.AbstractListAction;
import com.sxit.communicateguard.service.MemService;
import com.sxit.memdevice.command.Command;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.models.system.SysUser;

/**
 * @author 肖云亮
 * Nov 16, 20109:42:04 PM
 *
 */
public class ImitateExceCommandAction extends AbstractListAction {


	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		SysUser sysUser=this.getLoginUser();
		if(sysUser==null)
		{
			result="";
			orgresult="解析结果出错!";
			return SUCCESS;
		}
		
		MemService memService = (MemService) getBean("memService");
		MemDevicecommand command=(MemDevicecommand)memService.get(MemDevicecommand.class, commandId);
		MemDevice device=(MemDevice)memService.get(MemDevicecommand.class, command.getDeviceid());
		System.out.println("commandId=="+commandId+",");
		try {
			Command cmdprocess=(Command)Class.forName(command.getPlugin()).newInstance();
			result=cmdprocess.getresult(memService, device, command, sysUser.getUserid()+"");
			orgresult=cmdprocess.orgresult;
			System.out.println("result=="+result+",orgresult=="+orgresult);
		
		} catch (Exception e) {
		    e.printStackTrace();
			result="";
			orgresult="解析结果出错!";
			return SUCCESS;
		}
//		result="解析后的结果";
//		orgresult="解析前的结果";
		return SUCCESS;
	}
	
	
	private int commandId;
	public int getCommandId() {
		return commandId;
	}
	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}


	private String now;
	/**
	 * 解析后的结果
	 */
	public String result;
	/**
	 * 解析前的结果
	 */
	public String orgresult;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getOrgresult() {
		return orgresult;
	}
	public void setOrgresult(String orgresult) {
		this.orgresult = orgresult;
	}
	
	
	
}
