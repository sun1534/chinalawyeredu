package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflNode;
import com.sxit.models.workflow.TwflProcess;
/**
 * 
 * @author 华锋
 * Jul 9, 2009 11:18:05 PM
 *
 */

public class NodeCreateAction extends AbstractAction {

	private TwflNode node;

	private TwflProcess process;
	private int processid;

	public NodeCreateAction() {

		node = new TwflNode();
	}

	public String go() throws Exception {
		
		this.process = (TwflProcess) basicService.get(TwflProcess.class, node.getTwflProcess().getProcessid());
		node.setXcoordinate(process.getXposition());
		node.setYcoordinate(process.getYposition());

		process.setNewnodeid(process.getNewnodeid() + 1);
		process.setYposition(process.getYposition() + 60);



//		byte temppower = 0;
//		byte temppower1 = 0;
//		byte temppower2 = 0;
//		if (bodypower != null) {
//			for (int temp : bodypower) {
//				temppower += temp;
//			}
//		}
//		if (flowpower != null) {
//			for (int temp : flowpower) {
//				temppower1 += temp;
//			}
//		}
//		if (attachpower != null) {
//			for (int temp : attachpower) {
//				temppower2 += temp;
//			}
//		}
//		node.setBodydotype(temppower);
//		node.setFlowdotype(temppower1);
//		node.setAttachdotype(temppower2);
//		getSession().update(process);
		basicService.save(node);
	
		nextPage = "nodeList.action?processid="+ processid;
		message = "节点新增成功！";
		return SUCCESS;
	}

	public TwflNode getNode() {
		return node;
	}

	public String input() throws Exception {
		this.process = (TwflProcess) basicService.get(TwflProcess.class, processid);
		node.setTwflProcess(process);
			if (process == null) {
			message = "未找到此流程";
			return "message";
		}
			
			
			
		return "input";
	}


	public void setProcessid(int processid) {
		this.processid = processid;
	}

	public TwflProcess getProcess() {
		return process;
	}

	
	
	// 职务列表
	public List getPositionlist() throws HibernateException {
		String query="from TwflPosition order by positionid desc";
		return basicService.find(query);
	}

	public int getProcessid() {
		return processid;
	}
}
