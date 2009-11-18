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
 * @author 华锋 Jul 9, 2009 10:29:19 PM
 * 
 */

public class ProcessCreateAction extends AbstractAction {

	private TwflProcess process;
	private List<TwflBusiness> businesslist;

	public ProcessCreateAction() {

		process = new TwflProcess();
	}

	public String go() throws Exception {
		process.setAuthorid(this.getLoginUser().getUserid());
		process.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		process.setXposition(200);
		process.setYposition(40);
		process.setNewnodeid(1);
	
		basicService.save(process);
	
		nextPage = "processList.action";
		message = "流程新增成功！";
		return SUCCESS;
	}

	public TwflProcess getProcess() {
		return process;
	}

	public String input() throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TwflBusiness.class);
		detachedCriteria.addOrder(Order.desc("businessid"));
		businesslist = basicService.findAllByCriteria(detachedCriteria);
		return "input";
	}

	public List getBusinesslist() {
		return businesslist;
	}
}
