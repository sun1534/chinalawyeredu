/**
 * ArrangeAction.java
 */

package com.changpeng.arrange.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Arrangesignup;

/**
 * 
 * @author 华锋 2008-5-7 下午05:46:01
 * 
 */
public class ArrangesignupDeleteAction extends AbstractAction {

	private int signupid;
	private int arrangeid;

	/**
	 * @param arrangeid
	 *            the arrangeid to set
	 */
	public void setSignupid(int signupid) {
		this.signupid = signupid;
	}

	/**
	 * @return the arrangetype
	 */
	public int getArrangeid() {
		return arrangeid;
	}

	/**
	 * @param arrangetype
	 *            the arrangetype to set
	 */
	public void setArrangeid(int arrangeid) {
		this.arrangeid = arrangeid;
	}

	public String go() throws Exception {
		BasicService basic = (BasicService) getBean("basicService");

		Arrangesignup signup = (Arrangesignup) basic.get(Arrangesignup.class, signupid);
		
		basic.delete(signup);

		// if (arrangetype == 1)
		// this.message = "岗前培训删除成功";
		// else
		this.message = "报名信息删除成功";
		this.nextPage = "arrangesignupList.pl?arrangeid=" + arrangeid;
		return SUCCESS;

	}

}
