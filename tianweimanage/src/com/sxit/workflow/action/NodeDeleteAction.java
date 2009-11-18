package com.sxit.workflow.action;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflNode;

/**
 * 
 * @author 华锋 Jul 9, 2009 11:23:13 PM
 * 
 */
public class NodeDeleteAction extends AbstractAction {

	public NodeDeleteAction() {

	}

	public String go() throws Exception {

		nextPage = "nodeList.action?processid=" + processid;
		basicService.delete(TwflNode.class, nodeid);
		message = "节点删除成功！";
		return SUCCESS;
	}

	private int nodeid;

	public int getNodeid() {
		return nodeid;
	}

	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}

	private int processid;

	public int getProcessid() {
		return processid;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}

}
