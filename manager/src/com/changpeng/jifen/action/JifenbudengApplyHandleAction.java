/**
 * OfficeChangeApplyAction.java
 */
package com.changpeng.jifen.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.JifenbudengService;
import com.changpeng.models.JifenbudengApply;
import com.changpeng.models.Lawyers;

/**
 * 律师发起转所申请
 * 
 * 插入转所申请表
 * 
 * @author 华锋 Feb 27, 20104:43:49 PM
 * 
 */
public class JifenbudengApplyHandleAction extends AbstractAction {

	private JifenbudengApply budengApply;

	public JifenbudengApply getBudengApply() {
		if (budengApply == null)
			budengApply = (JifenbudengApply) this.get("budengApply");
		return budengApply;
	}

	/*
	 * 这里限定只能在同一个律协转换
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		if (budengApply.getStatus() == 2
				&& (budengApply.getConfirmcontent() == null || budengApply.getConfirmcontent().equals(""))) {
			this.message = "请输入积分补登申请不通过的原因";
			return "message";
		}
		JifenbudengService jifenbudengService = (JifenbudengService) getBean("jifenbudengService");
		budengApply.setConfirmtime(new java.sql.Timestamp(System.currentTimeMillis()));
		budengApply.setConfirmuser(this.getLoginUser().getUsername());
		budengApply.setConfirmuserid(this.getLoginUser().getUserid());
		int result=jifenbudengService.handleJifenbudengApply(budengApply);

		if(result==-1){
			this.message = "该律师的本地课程积分补登申请不能通过,该律师已有该补登课程的听课记录";
			this.opResult = "申请人" + budengApply.getApplyuser() + "补登课程:" + budengApply.getTitle() + ",学分:"
			+ budengApply.getXuefen() + "的申请处理通过,记录为失败,原因:"+message;
			return "message";
		}
		this.message = "课程补登申请处理通过";
		this.opResult = "申请人" + budengApply.getApplyuser() + "补登课程:" + budengApply.getTitle() + ",学分:"
		+ budengApply.getXuefen() + "的申请处理通过";
		this.nextPage = "jifenbudengApplyList.pl";

		return SUCCESS;
	}

	public String input() throws Exception {
		if (this.getLoginUser().getSysGroup() != null && this.getLoginUser().getSysGroup().getGrouptype() == 1) {
			this.message = "您是事务所,不能处理转所事宜";
			return "message";
		}
		budengApply = (JifenbudengApply) basicService.get(JifenbudengApply.class, budengid);
		budengApply.setStatus((short) 1);
		this.lawyers = (Lawyers) basicService.get(Lawyers.class, budengApply.getLawyerid());

		set("budengApply", budengApply);
		return INPUT;
	}

	private Lawyers lawyers;

	public Lawyers getLawyers() {
		return lawyers;
	}

	private int budengid;

	/**
	 * @return the budengid
	 */
	public int getBudengid() {
		return budengid;
	}

	/**
	 * @param budengid
	 *            the budengid to set
	 */
	public void setBudengid(int budengid) {
		this.budengid = budengid;
	}

}