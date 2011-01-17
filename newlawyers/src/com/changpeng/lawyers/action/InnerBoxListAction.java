/**
 * InnerBoxListAction.java
 */
package com.changpeng.lawyers.action;

import com.changpeng.common.action.AbstractAction;

/**
 * 收件箱
 * @author 华锋
 * Jan 15, 201110:05:31 PM
 *
 */
public class InnerBoxListAction extends AbstractAction {

	public InnerBoxListAction(){
		this.moduleId=1007;
	}
	
	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

}
