/**
 * ArrangeAction.java
 */

package com.changpeng.arrange.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.Arrange;
import com.changpeng.models.system.SysUser;

/**
 * 
 * @author 华锋 2008-5-7 下午05:46:01
 * 
 */
public class ArrangeDeleteAction extends AbstractAction {

	private int arrangeid;
	private byte arrangetype;



	/**
	 * @param arrangeid
	 *            the arrangeid to set
	 */
	public void setArrangeid(int arrangeid) {
		this.arrangeid = arrangeid;
	}

	/**
	 * @return the arrangetype
	 */
	public byte getArrangetype() {
		return arrangetype;
	}

	/**
	 * @param arrangetype
	 *            the arrangetype to set
	 */
	public void setArrangetype(byte arrangetype) {
		this.arrangetype = arrangetype;
	}


	public String go() throws Exception {
		BasicService basic = (BasicService) getBean("basicService");

		basic.delete(Arrange.class, arrangeid);

	
		if (arrangetype == 1)
			this.message = "岗前培训删除成功";
		else
			this.message = "活动安排删除成功";
		this.nextPage = "arrangeList.pl?arrangetype=" + arrangetype;
		return SUCCESS;

	}



}
