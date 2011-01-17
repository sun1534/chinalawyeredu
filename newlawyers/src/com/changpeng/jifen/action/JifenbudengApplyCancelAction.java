/**
 * OfficeChangeApplyAction.java
 */
package com.changpeng.jifen.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.JifenbudengApply;

/**
 * 律师发起转所申请
 * 
 * 插入转所申请表
 * 
 * @author 华锋 Feb 27, 20104:43:49 PM
 * 
 */
public class JifenbudengApplyCancelAction extends AbstractAction {

	private int budengid;
	public JifenbudengApplyCancelAction(){
		this.moduleId = 1003;
	}
	

	/**
	 * @return the budengid
	 */
	public int getBudengid() {
		return budengid;
	}



	/**
	 * @param budengid the budengid to set
	 */
	public void setBudengid(int budengid) {
		this.budengid = budengid;
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

	
		JifenbudengApply apply=(JifenbudengApply)basicService.get(JifenbudengApply.class, budengid);
		apply.setStatus((short)3);
		apply.setConfirmcontent("律师自己取消");
		apply.setConfirmtime(new java.sql.Timestamp(System.currentTimeMillis()));
		apply.setConfirmuserid(this.getLoginUser().getLawyerid());
		apply.setConfirmuser(this.getLoginUser().getLawyername());
		basicService.update(apply);
		
		this.message="您的积分补登申请取消成功";
		
		this.nextPage = "jifenbudengApplyList.pl";

		return SUCCESS;
	}

	

}
