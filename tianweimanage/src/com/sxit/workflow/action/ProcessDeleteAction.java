package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.action.AbstractAction;
import com.sxit.models.workflow.TwflNode;
import com.sxit.models.workflow.TwflProcess;

/**
 * 
 * @author 华锋 Jul 9, 2009 10:39:31 PM
 * 
 */
public class ProcessDeleteAction extends AbstractAction {

	public ProcessDeleteAction() {

	}

	public String go() throws Exception {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TwflNode.class);
		detachedCriteria.add(Restrictions.eq("twflProcess.processid", processid));
		List list = basicService.findAllByCriteria(detachedCriteria);
		if (list != null && list.size() > 0)
			message = "有未删除的节点,请返回";

		else {
			basicService.delete(TwflProcess.class, processid);
			message = "流程删除成功！";
		}

		nextPage = "processList.action";
		return SUCCESS;
	}

	private int processid;

	public int getProcessid() {
		return processid;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}

}
