/**
 * OfficeChangeApplyAction.java
 */
package com.changpeng.lawyers.action;

import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lawyers;
import com.changpeng.models.LawyersOfficeChangeApply;
import com.changpeng.models.SysGroup;

/**
 * 律师发起转所申请
 * 
 * 插入转所申请表
 * 
 * @author 华锋 Feb 27, 20104:43:49 PM
 * 
 */
public class OfficeChangeApplyCancelAction extends AbstractAction {

	private int id;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	
		LawyersOfficeChangeApply apply=(LawyersOfficeChangeApply)basicService.get(LawyersOfficeChangeApply.class, id);
		apply.setStatus((short)4);
		apply.setConfirmContent("律师自己取消");
		apply.setConfirmTime(new java.sql.Timestamp(System.currentTimeMillis()));
		apply.setConfirmuserid(this.getLoginUser().getLawyerid());
		apply.setConfirmusername(this.getLoginUser().getLawyername());
		basicService.update(apply);
		
		this.message="您的转所申请取消成功";
		
		this.nextPage = "officeChangeApplyList.pl";

		return SUCCESS;
	}

	

}
