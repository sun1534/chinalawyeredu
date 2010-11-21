/**
 * MemDeviceListAction.java
 */
package com.sxit.communicateguard.action.ajax;

import com.sxit.common.action.AbstractAction;
import com.sxit.communicateguard.service.MemService;

/**
 * 删除mem的设备,所有人都没了
 * 
 * @author 华锋 Nov 16, 20109:42:04 PM
 * 
 */
public class MemDeviceCommandDeleteAction extends AbstractAction {

	private int commandId;

	/**
	 * @return the commandId
	 */
	public int getCommandId() {
		return commandId;
	}

	/**
	 * @param commandId
	 *            the commandId to set
	 */
	public void setCommandId(int commandId) {
		this.commandId = commandId;
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

			service.deleteCommand(commandId);

		} catch (Exception e) {
			isok = 0;
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private int isok = 1;

	/**
	 * @return the isok
	 */
	public int getIsok() {
		return isok;
	}

}
