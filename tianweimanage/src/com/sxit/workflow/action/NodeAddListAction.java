package com.sxit.workflow.action;

import java.util.Set;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.workflow.TwflDirection;
import com.sxit.models.workflow.TwflNode;
import com.sxit.models.workflow.TwflProcess;

/**
 * 
 * @author 华锋 Jul 9, 2009 11:31:30 PM
 * 
 */
public class NodeAddListAction extends AbstractListAction {
	private TwflNode node;
	private TwflProcess process;
	private Set tonodes;

	public NodeAddListAction() {
	}

	public String go() throws Exception {
	
		node = (TwflNode) basicService.get(TwflNode.class, nodeid);
		process=node.getTwflProcess();

		tonodes = process.getTwflNodes(); //这个流程的所有节点
		tonodes.remove(node);             //排除掉自己
		
		for (TwflDirection direction : node.getToNode()) {
			tonodes.remove(direction.getToNode());
		}
		nextPage = "nodeView.action?nodeid=" + node.getNodeid();
		return SUCCESS;
	}

	public int getFromnodeid() {
		return this.nodeid;
	}
	
	private int nodeid;
	private int processid;
	
	
	public int getProcessid() {
		return processid;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}

	public int getNodeid() {
		return nodeid;
	}

	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}

	public TwflNode getNode() {
		
		return node;
	}

	public TwflProcess getProcess() {
		
		return process;
	}

	public Set getTonodes() {
		return tonodes;
	}
}
