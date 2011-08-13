package com.changpeng.lawcase.workflow;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;

import com.changpeng.lawcase.model.TlawHistory;
import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.sxit.system.model.TsysRole;
import com.sxit.system.model.TsysUser;
import com.sxit.system.model.TsysUserRole;
import com.sxit.wait.util.IntefaceWork;
import com.sxit.wait.util.WaitWork;
import com.sxit.workflow.model.TwflAction;
import com.sxit.workflow.model.TwflDirection;
import com.sxit.workflow.model.TwflNode;

public class CaseDoSubmitAction extends WorkFlowAction {
	public CaseDoSubmitAction() {
		lawcase = new TlawLawcase();
	}

	private TlawLawcase lawcase;
	
	private long userid;// 用于选择人物的时候
	private List tonodes;

	public List getTonodes() {
		return this.tonodes;
	}

//	private List sysDepartmentPosition;
//
//	public List getSysDepartmentPosition() {
//		return this.sysDepartmentPosition;
//	}

	private Set roleusers;

	/**
	 * @return the roleusers
	 */
	public Set getRoleusers() {
		return roleusers;
	}
	
	public TlawLawcase getLawcase() {
		return this.lawcase;
	}

	public String go() throws HibernateException {

		// 设置返回路径
		nextpage = "workbillWaitList.action";
		// 取得费用申请的对象
		lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		// 判断是否存在此对象
		if (lawcase == null) {
			message = "没有找到这个案件";
			return "message";
		}
		// 判断是否是当前处理人
		if (!(curuser.getLoginname().equals("admin") || lawcase.getHotman() == curuser.getUserid())) {
			message = "您不是当前的处理人,不能提交!";
			return "message";
		}
		// 草稿状态或退回状态
		// if (ttskCommon.getStatus() == -1 || ttskCommon.getStatus() == -2) {
		// ttskCommon.setStatus((byte) 2);
		// nextpage = "myworkBill.action?pagenumber=" + pagenumber;
		// }

		int nownodeid = lawcase.getNodeid();
		TwflNode curnode = (TwflNode) getSession().get(TwflNode.class, nownodeid);

		// 如果有其他要处理的话,必须先处理其他的了才能提交，通过session来控制
		// if (curnode.getDescription() != null &&
		// !"".equals(curnode.getDescription())) {
		// String does = curnode.getDescription();
		// StringTokenizer st = new StringTokenizer(does, ",");
		// String thedoes = "";
		// if (st.countTokens() == 1) {
		// thedoes = does;
		// }
		// else {
		// thedoes = st.nextToken();// 取得第一个，第一个是提交的时候所需要的
		// }
		// int index = thedoes.indexOf("|");
		// if (get("does") == null) {
		// message = "提交之前请先处理\"" + thedoes.substring(0, index) + "\"事宜";
		// this.nextpage = "caseDoView.action?caseid=" + caseid;
		// return "error";
		// }
		// }

		// 判断当前节点是否是结束节点
		if (curnode.getNodetype() == 5) {
			message = "当前节点已经为结束节点,不可以继续提交!";
			return "message";
		}
		TwflNode tonode = null;
		// System.out.println("nodeid===" + nodeid + ",,,,userid===" + userid);
		// 获取要转向的节点
		if (nodeid == 0) {
			// 获取提交的节点
			Set<TwflDirection> directionset = curnode.getToNode();
			// System.out.println("后续节点个数:::" + directionset.size());
			// 取得后续节点数
			int n = directionset.size();
			// 无后续节点
			if (n == 0) {
				message = "流程设置有误!,请与管理员联系!";
				return "message";
			}
			// 多于一个后续节点
			if (n > 1) {
				tonodes = new ArrayList();
				Iterator<TwflDirection> iterator = directionset.iterator();
				while (iterator.hasNext()) {
					TwflDirection d = iterator.next();
					// tonodes.add(d.getToNode());
					tonodes.add(d);
				}
				// 有分支节点,提交到选择节点的页面，将directionset提到页面前台
				message = "选择节点";
				return "toselect";
			}
			// 只有一个节点的情况
			TwflDirection tempdirection = (TwflDirection) directionset.toArray()[0];
			tonode = tempdirection.getToNode();
		} else {
			tonode = (TwflNode) getSession().get(TwflNode.class, nodeid);
		}
		// 获取要转向的人
		// 处理人职务
//		TsysPosition position = tonode.getTsysPosition();
		TsysRole role=tonode.getTsysRole();
		// System.out.println(position.getPositionid() + "=====" +
		// position.getPositionname());

		TsysUser hotman = null;
		if (this.userid != 0) {// userid不等于0表示选择了下一个要处理的人
			hotman = (TsysUser) getSession().get(TsysUser.class, userid);
		} else {
//			List list = SystemFunction.getPositionUser(getSession(), position);
			this.roleusers=role.getTsysUserRoles();
			// System.out.println("==============" + list.size());
			// 获取不到处理人 提示当前没有处理人，联系管理员，不处理跳过节点的情况
			if (roleusers == null || roleusers.size() == 0) {
				message = "该节点没有处理人,请联系管理员";
				this.nextpage = "workbillWaitList.action";
				return "message";
			} else if (roleusers.size() > 1) {
//				this.sysDepartmentPosition = list;
				return "selectman";
			}
			// 否则的话,获取
//			hotman = ((TsysDepartmentPosition) list.get(0)).getTsysUser();
			hotman=((TsysUserRole)(roleusers.iterator().next())).getComp_id().getTsysUser();
		}

		// 处理历史记录
		TlawHistory history = new TlawHistory();
		history.setActionid(actionid);
		if (actionid != 0) {
			TwflAction action = (TwflAction) getSession().get(TwflAction.class, actionid);
			history.setDomessage(action.getActionname());
		} else {
			history.setDomessage("提交");
		}

		Timestamp stamp = new java.sql.Timestamp(System.currentTimeMillis());
		history.setDotime(stamp);
		history.setNodeid(lawcase.getNodeid());

		System.out.println(lawcase.getNodeid() + ",,,,," + curuser.getUserid());

		history.setDouserid(curuser.getUserid());
		history.setDousername(curuser.getUsername());
		history.setCaseid(caseid);
		history.setRemarks(remarks == null || remarks.equals("") ? "" : StringUtil.strhex2str(remarks));
		getSession().save(history);

		// 获取处理人

		// 判断当前节点是否归档当处理人是申请人时跳过此节点 但不适用于结束结点
		LOG.debug("|||||||||||||||||||||==" + curnode.getNodename());
		// 不处理自己是申请人和审批人相同的情况

		// ============正式处理此申请======================
		// 设置的处理人
		lawcase.setHotman(hotman.getUserid());
		// 设置下一个处理节点
		lawcase.setNodeid(tonode.getNodeid());

		// 添加短信通知
		// 处理已处理代办事宜
		if (lawcase.getWaitid() > 0) {
			WaitWork.EndWait(getSession(), lawcase.getWaitid(), curuser);
			IntefaceWork.EndInterface(getSession(), lawcase.getWaitid(), curuser);
		}

		String subject = "合同编号为" + lawcase.getContractno() + "的诉讼案件处理（" + tonode.getNodename() + "）";
		String url = "../lawcase/caseDoView.action?caseid=" + caseid;
		String flowstep = tonode.getNodename();
		int waitid = WaitWork.Sendwait(getSession(), hotman, subject, url, 2, curuser.getUsername(), flowstep, "");

		System.out.println("==========待办事宜ID：" + waitid);

		// int intefaceid = IntefaceWork.Sendinteface(session, hotman, subject,
		// waitid);
		// System.out.println("==========事务处理接口ID：" + intefaceid);
		lawcase.setWaitid(waitid);
		lawcase.setModifytime(stamp);

		System.out.println("lawcase.getNodeid():::" + lawcase.getNodeid());
		getSession().update(lawcase);
		ActionContext.getContext().getSession().remove("does");
		message = "该案件提交到处理人：" + hotman.getUsername() + "，节点：" + tonode.getNodename() + " 成功";
		return "success";
	}

	/**
	 * @param ttskCommon
	 *            the ttskCommon to set
	 */
	public void setLawcase(TlawLawcase lawcase) {
		this.lawcase = lawcase;
	}

	// /**
	// * @return the taskid
	// */
	// public long getCaseid() {
	// return caseid;
	// }
	//
	// /**
	// * @param taskid
	// * the taskid to set
	// */
	// public void setCaseid(long caseid) {
	// this.caseid = caseid;
	// }

	/**
	 * @return the nodeid
	 */
	// public int getNodeid() {
	// return nodeid;
	// }
	/**
	 * @param nodeid
	 *            the nodeid to set
	 */
	// public void setNodeid(int nodeid) {
	// this.nodeid = nodeid;
	// }
	/**
	 * @return the userid
	 */
	public long getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(long userid) {
		this.userid = userid;
	}
}