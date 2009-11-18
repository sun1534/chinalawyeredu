package com.sxit.workflow.action;

import java.util.Set;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.workflow.TwflDirection;
import com.sxit.models.workflow.TwflNode;
import com.sxit.models.workflow.TwflProcess;

/**
 * 
 * @author 华锋 Jul 9, 2009 11:38:32 PM
 * 
 */

public class NodeAddToAction extends AbstractListAction {
	
	private TwflProcess process;
	private int nodeid;
	private Set<TwflNode> tonodes;

	public NodeAddToAction() {

	}

	public String go() throws Exception {
	
		TwflNode tonode = new TwflNode();
		tonode.setNodeid(nodeid);
		TwflNode fromnode = new TwflNode();
		fromnode.setNodeid(fromnodeid);
		
		TwflDirection direction = new TwflDirection();
		direction.setFromNode(fromnode);
		direction.setToNode(tonode);
		direction.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		direction.setCreateuserid(this.getLoginUser().getUserid());

		basicService.save(direction);
		nextPage = "nodeView.action?nodeid=" + fromnode.getNodeid();
		message = "后续节点添加成功";
		return SUCCESS;
	}

	private int fromnodeid;
	private int processid;
	
	
	public int getFromnodeid() {
		return fromnodeid;
	}

	public void setFromnodeid(int fromnodeid) {
		this.fromnodeid = fromnodeid;
	}

	public int getProcessid() {
		return processid;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}

	public int getNodeid() {
		return nodeid;
	}

	public TwflProcess getProcess() {
		
		return process;
	}

	public void setNodeid(int nodeid) {

		this.nodeid = nodeid;
	}
}
