package com.sxit.workflow.action;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflNode;

/**
 * 
 * @author 华锋 Jul 9, 2009 11:28:03 PM
 * 
 */
public class NodeViewAction extends AbstractAction {
	private TwflNode node;
	private int nodeid;

	public NodeViewAction() {

		node = new TwflNode();
	}

	public String go() throws Exception {
		nextPage = "nodeList.action";
		node = (TwflNode) basicService.get(TwflNode.class, nodeid);
		if (node == null) {
			message = "未找到此节点";
			return "message";
		}
	
		return SUCCESS;
	}
	private int processid;
	public int getProcessid(){
		return this.processid;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}

	public TwflNode getNode() {
		return node;
	}

	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}

	public int getNodeid() {
		return this.nodeid;
	}
}
