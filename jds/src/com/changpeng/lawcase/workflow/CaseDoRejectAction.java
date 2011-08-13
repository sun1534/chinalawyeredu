
package com.changpeng.lawcase.workflow;

import org.hibernate.HibernateException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.sxit.common.action.AbstractAction;
import com.sxit.wait.util.WaitWork;
import com.sxit.workflow.model.TwflNode;

public class CaseDoRejectAction extends AbstractAction {

	private TlawLawcase lawcase;
	private long caseid;
	private String domessage;

	public CaseDoRejectAction(){
		   
		this.rights="tsk1,1";
	}

	public String go() throws HibernateException {
		nextpage = "workbillWaitList.action";
		lawcase = (TlawLawcase) getSession().get(TlawLawcase.class,caseid);
		if (lawcase == null) {
			message = "未找到此工单";
			return "message";
		}
		if (lawcase.getHotman() != curuser.getUserid()) {
			message = "你不是当前的处理人,不能处理意见!";
			return "message";
		}
		// 获取当前节点
//		TwflNode curnode = lawcase.getCurNode();
		// 处理历史记录
//		TtskApplyhistory history = new TtskApplyhistory();
//		history.setTtskCommon(lawcase);
//		String mark = curuser.getUsername() + "将申请由\"" + curnode.getNodename() + "\"驳回";
//		history.setRemarks(mark);
//		history.setDotime(new java.sql.Timestamp(System.currentTimeMillis()));
//		history.setNode(curnode);
//		history.setUserid(curuser.getUserid());
//		history.setUsername(curuser.getUsername());
//		getSession().save(history);
//		// 处理已处理代办事宜
//		if (lawcase.getWaitid() > 0) {
//			WaitWork.EndWait(getSession(), lawcase.getWaitid(), curuser);
//		}
//		lawcase.setWaitid(0);
		
		//将其直接打回到申请人处，更改hotman和curnode，同时status更改为-3
	
		
//		lawcase.setStatus((byte)-3);
		getSession().update(lawcase);
		message = "处理成功!";
		return SUCCESS;
	}

	/**
	 * @return the lawcase
	 */
	public TlawLawcase getLawcase() {
		return lawcase;
	}

	/**
	 * @param lawcase the lawcase to set
	 */
	public void setLawcase(TlawLawcase lawcase) {
		this.lawcase = lawcase;
	}

	/**
	 * @return the caseid
	 */
	public long getTaskid() {
		return caseid;
	}

	/**
	 * @param caseid the caseid to set
	 */
	public void setTaskid(long caseid) {
		this.caseid = caseid;
	}

	/**
	 * @return the domessage
	 */
	public String getDomessage() {
		return domessage;
	}

	/**
	 * @param domessage the domessage to set
	 */
	public void setDomessage(String domessage) {
		this.domessage = domessage;
	}


}
