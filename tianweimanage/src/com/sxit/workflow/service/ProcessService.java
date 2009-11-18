package com.sxit.workflow.service;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.sxit.common.BasicDAO;
import com.sxit.models.workflow.TwflBusiness;
import com.sxit.models.workflow.TwflNode;
import com.sxit.models.workflow.TwflProcess;

/**
 * 
 * @author 华锋 Jul 9, 2009 2:30:09 PM
 * 
 */
public class ProcessService {

	private BasicDAO basicDAO;

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}

	/**
	 * @todo 获取某业务上可以使用的流程列表
	 * @param session
	 *            Session
	 * @param user
	 *            TsysUser
	 * @param functionid
	 *            String
	 * @return List
	 */
	public List getProcess(int businessid) {

		String queryName = "from TwflProcess where businessid=" + businessid + "  and statusid=1 order by processid desc";

		List processlist = basicDAO.find(queryName);

		return processlist;
	}

	/**
	 * @todo 获取流程的开始节点
	 * @param session
	 *            Session
	 * @param process
	 *            TwflProcess
	 * @return int
	 */
	public TwflNode getProcessStartNode(int processid) {

		String hql = "from TwflNode where nodetype=1 and twflProcess.processid=" + processid;
		List list = basicDAO.find(hql);
		if (list != null && list.size() != 0)
			return (TwflNode) list.get(0);
		return null;
	}

	/**
	 * @todo 判断用户是否有权限使用业务
	 * @param session
	 *            Session
	 * @param process
	 *            TwflProcess
	 * @return int
	 */
	public static boolean isCanUse( int userid, int businessid) {
//		TwflBusiness business = (TwflBusiness) session.get(TwflBusiness.class, Integer.valueOf(businessid));
//		if (business == null)
//			return false;
//		// 业务是所有人使用时
//		if (business.getUsetype() == 1)
//			return true;
//		// 业务是成员使用时
//		if (!business.getUsers().contains(user))
//			return false;
		return true;
	}
}
