package com.sxit.workflow.action;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflDirection;

/**
 * 移除后续节点
 * 
 * @author 华锋 Jul 9, 2009 11:26:37 PM
 * 
 */
public class NodeDeleteToAction extends AbstractAction {

	private TwflDirection direction;
	private int directionid;
	private int nodeid;

	public NodeDeleteToAction() {

	}

	public String go() throws Exception {
		TwflDirection direction = (TwflDirection) basicService.get(TwflDirection.class, directionid);
		if (direction == null) {
			message = "未找到此流向";
			return "message";
		}
		basicService.delete(direction);
		message = "后续节点删除成功！";
		nextPage = "nodeView.action?nodeid=" + nodeid+"&processid="+processid;
		return SUCCESS;
	}

	private int processid;
	
	public int getProcessid() {
		return processid;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}

	public void setDirectionid(int directionid) {
		this.directionid = directionid;
	}

	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}

	public int getNodeid() {
		return nodeid;
	}
}
