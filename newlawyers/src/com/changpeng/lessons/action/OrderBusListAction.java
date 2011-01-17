/**
 * OrderBusListAction.java
 */
package com.changpeng.lessons.action;

import com.changpeng.common.action.AbstractAction;

/**
 * @author 华锋
 * Jan 7, 201111:49:43 AM
 *
 */
public class OrderBusListAction extends AbstractAction {
public OrderBusListAction(){
	this.moduleId=1005;
}
	
	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;	}

}
