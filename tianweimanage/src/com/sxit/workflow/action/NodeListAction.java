package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.workflow.TwflNode;
import com.sxit.models.workflow.TwflProcess;

/**
 * 
 * @author 华锋
 * Jul 9, 2009 11:16:21 PM
 *
 */

public class NodeListAction extends AbstractListAction {
	private List nodelist;
	private TwflProcess process;
	private int processid;

	public NodeListAction() {

	}

	public String go() throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TwflNode.class);
		detachedCriteria.add(Restrictions.eq("twflProcess.processid", processid));
		detachedCriteria.addOrder(Order.desc("nodeid"));
		nodelist = basicService.findAllByCriteria(detachedCriteria);

		this.process = (TwflProcess) basicService.get(TwflProcess.class, processid);

		return SUCCESS;
	}

	public List getNodelist() {
		return nodelist;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}
	
	public int getProcessid() {
		return this.processid ;
	}

	public TwflProcess getProcess() {
		return process;
	}
}
