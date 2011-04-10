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
			result="解析结果出错!";
			orgresult="";
			return SUCCESS;
		}
		
		MemService memService = (MemService) getBean("memService");
		MemDevicecommand command=(MemDevicecommand)memService.get(MemDevicecommand.class, commandId);
		MemDevice device=(MemDevice)memService.get(MemDevice.class, command.getDeviceid());
		System.out.println("commandId=="+commandId);
		try {
			 System.out.println("加裁类=="+command.getPlugin());
			Command cmdprocess=(Command)Class.forName(command.getPlugin()).newInstance();
			System.out.println("加裁类=="+command.getPlugin());
			System.out.println("----"+cmdprocess);
			result=cmdprocess.getresult(memService, device, command, sysUser.getUserid()+"");
			result=result.replace("\r\n", "<br/>");
			
			System.out.println("返回结果："+result);
			orgresult=cmdprocess.orgresult;
			orgresult=orgresult.replace("\r", "<br/>");
			System.out.println("result=="+result+",orgresult=="+orgresult);
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
