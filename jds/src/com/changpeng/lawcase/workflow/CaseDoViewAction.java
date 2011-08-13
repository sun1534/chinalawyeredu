/**
 * WorkbillDoViewAction.java
 */

package com.changpeng.lawcase.workflow;

import java.text.DateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.service.LawcaseLogicService;
import com.changpeng.lawcase.service.LawcaseService;
import com.sxit.workflow.model.TwflAction;
import com.sxit.workflow.model.TwflNode;

/**
 * @author 华锋 2008-4-15 下午04:22:48
 * 
 */
public class CaseDoViewAction extends WorkFlowAction {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMddHHmmss");

	public CaseDoViewAction() {

	}

	private int userid;


	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getUserid() {
		return this.userid;
	}

	private TlawLawcase lawcase;

	private TwflNode curnode;



	public TlawLawcase getLawcase() {
		if (lawcase == null)
			this.lawcase = (TlawLawcase) get("lawcase");
		return this.lawcase;
	}
	

	private boolean issubmit = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub
		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		this.userid = (int) this.curuser.getUserid();

		this.nodeid = lawcase.getNodeid();
		this.curnode = (TwflNode) getSession().get(TwflNode.class, nodeid);

		// 如果有自己的action的话，就不显示提交了

		otherdoes = this.curnode.getActions();
		if (otherdoes != null &&otherdoes.size() > 0){
			issubmit = false;
			Iterator<TwflAction> iterator=otherdoes.iterator();
			while(iterator.hasNext()){
				TwflAction a=iterator.next();
				a.setIshandled(LawcaseLogicService.actionIsHandled(getSession(), caseid, a.getActionid()));
			}
		}
		
		this.dohistories=getSession().createQuery("from TlawHistory a where a.caseid="+caseid+" order by dotime").list();
		
		return SUCCESS;
	}

	private List dohistories;
	public List getDohistories(){
		return this.dohistories;
	}
	
	// 编辑的话，草稿状态才能编辑
	// -1表示是草稿状态
	public boolean getIsedit() {
		// if (common.getStatus() != -1) {
		// return false;
		// }
		// int power = curnode.getBodydotype();

		// 设定只有台帐管理员随时能修改案件信息
		this.curuser.getTsysUserRoles().iterator().next().getComp_id().getTsysRole();
		List rolelist = LawcaseService.getRolesById(getSession(), this.curuser.getUserid());
		System.out.println("rolelist:::"+rolelist);
		// 621是台帐管理有的职位id
		if (rolelist.contains(com.changpeng.lawcase.util.CommanDatas.TAIZHANGROLE)||this.curuser.getLoginname().equals("admin")) {
			return true;
		}
		return false;

	}


	// // 驳回
	// public boolean getIsreject() {
	// // if (common.getStatus() != 2) {
	// // return false;
	// // }
	// int power = curnode.getFlowdotype();
	// return 2 == (power & 2);
	// }

	// // 退回
	// public boolean getIsback() {
	// // if (!(common.getStatus() == 2||common.getStatus() == 3)) {
	// // return false;
	// // }
	// int power = curnode.getFlowdotype();
	// return 4 == (power & 4);
	// }

	// // 归档
	// public boolean getIsfiling() {
	// // if (common.getStatus() != 2 && common.getStatus() != -1) {
	// // return false;
	// // }
	// int power = curnode.getFlowdotype();
	// return 8 == (power & 8);
	// }

	// 结束
	public boolean getIsend() {
		// if (common.getStatus() != 2) {
		// return false;
		// }
		int power = curnode.getNodetype();
		return 5 == power;
	}
	public boolean getIsdelete() {
		if(this.curuser.getLoginname().equals("admin"))
			return true;
		else if(lawcase.getStatusid()==-1)
			return true;
		return false;
	}
	// /**
	// * 草稿的或者关闭的或者完成的不能退回，其他都行
	// * @return
	// */
	// public boolean getBack2shangwu(){
	// // if (common.getStatus() == -1 || common.getStatus()
	// ==2||common.getStatus()==3||common.getStatus()==-2) {
	// // return true;
	// // }
	// return false;
	// }

	public boolean getIssubmit() {

		// 关闭了的和完成了的，都不能提交了
		// if(common.getStatus()==1||common.getStatus()==0)
		// return false;

		int power = curnode.getNodetype();
		// 结束节点不能提交
		if (5 == power) {
			return false;
		}
		// 不是当前处理人不能提交
		if (curuser.getUserid() != lawcase.getHotman()) {
			return false;
		}
		return issubmit;
	}

	// 节点其他要做的事情,包括名称和对应的url
	// private List otherdoes = new ArrayList();
    private Set otherdoes;
	public Set getOtherdoes() {
		if(curuser.getLoginname().equals("admin"))
			return otherdoes;
		else if(curuser.getUserid() == lawcase.getHotman())
			return otherdoes;
		else
			return new HashSet(); 
//		if (curuser.getUserid() != lawcase.getHotman()) {
//			return new HashSet();
//		}
//		return otherdoes;
	}
}