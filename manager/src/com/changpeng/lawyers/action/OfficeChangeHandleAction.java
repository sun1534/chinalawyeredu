/**
 * OfficeChangeApplyAction.java
 */
package com.changpeng.lawyers.action;

import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.lawyers.service.LawyersService;
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
public class OfficeChangeHandleAction extends AbstractAction {

	private LawyersOfficeChangeApply changeApply;

	public LawyersOfficeChangeApply getChangeApply() {
		if (changeApply == null)
			changeApply = (LawyersOfficeChangeApply) this.get("changeApply");
		return changeApply;
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

		LawyersService lawyerService = (LawyersService) getBean("lawyersService");
		lawyerService.updateLawyerOffice(changeApply.getLawyerid(), changeApply.getNewoffice(), changeApply
				.getNewcity(), changeApply.getNewprovince(), this.getLoginUser(), changeApply);

		this.message = "转所申请处理通过";

		this.nextPage = "officeChangeApplyList.pl";

		return SUCCESS;
	}

	public String input() throws Exception {
		if (this.getLoginUser().getSysGroup() != null && this.getLoginUser().getSysGroup().getGrouptype() == 1) {
			this.message = "您是事务所,不能处理转所事宜";
			return "message";
		}
		changeApply = (LawyersOfficeChangeApply) basicService.get(LawyersOfficeChangeApply.class, id);
		changeApply.setStatus((short) 1);
		this.lawyers = (Lawyers) basicService.get(Lawyers.class, changeApply.getLawyerid());

		// this.datavisible.setCityid(apply.getNewcity());
		// this.datavisible.setOfficeid(apply.getNewoffice());
		// this.datavisible.setProvinceid(apply.getNewprovince());
		//
		// this.datavisible.getVisibleDatas(this.getLoginUser(), false);
		set("changeApply", changeApply);
		return INPUT;
	}

	private Lawyers lawyers;

	public Lawyers getLawyers() {
		return lawyers;
	}

	private int id;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}