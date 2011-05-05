/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action.ajax;

import java.util.HashMap;

import com.sxit.common.CommonDatas;
import com.sxit.common.action.AbstractListAction;
import com.sxit.communicateguard.service.MemService;
import com.sxit.memdevice.command.Command;
import com.sxit.memdevice.common.Client;
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
			result="解析结果出错!";
			orgresult="";
			return SUCCESS;
		}
		
		MemService memService = (MemService) getBean("memService");
		MemDevicecommand command=(MemDevicecommand)memService.get(MemDevicecommand.class, commandId);
//		MemDevice device=(MemDevice)memService.get(MemDevice.class, command.getDeviceid());
		HashMap<String, MemDevice> userListDevice=CommonDatas.LOGINDEVICE.get(sysUser.getUserid()+"");
		MemDevice device=(MemDevice)userListDevice.get(command.getDeviceid());
		if(device==null)
		{
			device=(MemDevice)memService.get(MemDevice.class, command.getDeviceid());
		}
		try {
			if(command.getCommandtype()==1){
			Command cmdprocess=(Command)Class.forName(command.getPlugin()).newInstance();
			result=cmdprocess.getresult(memService, device, command, sysUser.getUserid()+"");
//			
//			System.out.println("111====="+result.indexOf("^p"));
//			System.out.println("2222===="+result.indexOf("\r\n"));
			result=result.replace("\r\n", "<br/>");
//			result.replaceAll("^p", "<br/>").replaceAll("^p","<br/>");
			orgresult=cmdprocess.orgresult;
			orgresult=orgresult.replace("\r\n", "<br/>");
//			orgresult.replaceAll("^p", "<br/>").replaceAll("^p","<br/>");
			System.out.println("result=="+result+",orgresult=="+orgresult);
			}else {
				
				orgresult=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),command.getCommandscript());
				result=orgresult;
				commandtype=command.getCommandtype();
			}
		} catch (Exception e) {
		    e.printStackTrace();
		    System.out.println(e.getMessage());
			result="解析结果出错!";
			orgresult="获取原始结果出错!";
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
	
	public int commandtype;
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
	public int getCommandtype() {
		return commandtype;
	}
	public void setCommandtype(int commandtype) {
		this.commandtype = commandtype;
	}
	
	
	
	
}
