package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflBusiness;
import com.sxit.models.workflow.TwflProcess;

/**
 * 
 * 
 * 
 * @author 华锋
 * Jul 9, 2009 10:32:43 PM
 *
 */

public class ProcessEditAction extends AbstractAction {

	private TwflProcess process;
	private List businesslist;

	public ProcessEditAction() {

	}

	public String go() throws Exception {
		basicService.update(process);
		
		nextPage = "processList.action";
		message = "保存成功！";
		return SUCCESS;
	}

	public TwflProcess getProcess() {
		if (process == null)
			process = (TwflProcess) get("process");
		return process;
	}

	public String input() throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TwflBusiness.class);
		detachedCriteria.addOrder(Order.desc("businessid"));
		businesslist = basicService.findAllByCriteria(detachedCriteria);

		TwflProcess process = (TwflProcess) basicService.get(TwflProcess.class, processid);
set("process",process);
		return "input";
	}

	public List getBusinesslist() {
		return businesslist;
	}

	private int processid;

	public int getProcessid() {
		return processid;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}

}
