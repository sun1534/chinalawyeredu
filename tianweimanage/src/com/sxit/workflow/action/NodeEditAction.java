package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflNode;
import com.sxit.models.workflow.TwflProcess;

/**
 * 
 * @author 华锋
 * Jul 9, 2009 11:30:31 PM
 *
 */

public class NodeEditAction extends AbstractAction {

	private TwflNode node;
	private TwflProcess process;
	public TwflProcess getProcess() {
		return process;
	}

	public NodeEditAction() {

	}

	public String go() throws HibernateException {

		basicService.update(node);

		nextPage = "nodeView.action?nodeid="+node.getNodeid()+"&processid=" + processid;
		message = "节点信息修改成功！";
		return SUCCESS;
	}

	public TwflNode getNode() {
		if (node == null)
			node = (TwflNode) get("node");
		return node;
	}

	public String input() throws Exception {
		this.process = (TwflProcess) basicService.get(TwflProcess.class, processid);
		
		node = (TwflNode) basicService.get(TwflNode.class, nodeid);
		node.setTwflProcess(process);
		set("node",node);
		return "input";
	}



	private int nodeid;
	private int processid;

	public int getNodeid() {
		return nodeid;
	}

	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}

	public int getProcessid() {
		return processid;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}

	// 职务列表
	public List getPositionlist() throws HibernateException {
		String query="from TwflPosition order by positionid desc";
		return basicService.find(query);
	}
}
