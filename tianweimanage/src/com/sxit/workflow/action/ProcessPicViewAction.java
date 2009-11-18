package com.sxit.workflow.action;

import java.util.List;

import org.hibernate.HibernateException;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.workflow.TwflProcess;

/**
 * 
 * @author 华锋 Jul 9, 2009 10:48:18 PM
 * 
 */

public class ProcessPicViewAction extends AbstractListAction {
	private List nodelist;
	private TwflProcess process;
	private int processid;

	public ProcessPicViewAction() {

	}

	public String go() throws Exception {
		process = (TwflProcess) basicService.get(TwflProcess.class, processid);
		if (process == null) {
			message = "未找到此流程";
			return "message";
		}

		// nodelist = getQuery()
		// .setInteger("processid",processid)
		// .setMaxResults(maxperpage)
		// .setFirstResult(maxperpage * pagenumber)
		// .setCacheable(true)
		// .list();
		return SUCCESS;
	}

	public List getNodelist() {
		return nodelist;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}

	public TwflProcess getProcess() {
		return process;
	}
}
