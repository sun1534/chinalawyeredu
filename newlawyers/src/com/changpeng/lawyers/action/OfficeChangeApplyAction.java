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
public class OfficeChangeApplyAction extends AbstractAction {

	
	public OfficeChangeApplyAction(){
		this.moduleId=1008;
	}
	
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


		
		changeApply.setLawyerid(this.getLoginUser().getLawyerid());
		changeApply.setApplyTime(new java.sql.Timestamp(System.currentTimeMillis()));
		changeApply.setOldcity(this.getLoginUser().getDirectunion());
		changeApply.setOldoffice(this.getLoginUser().getTheoffice());
		changeApply.setOldprovince(this.getLoginUser().getProvinceunion());
//		changeApply.setNewcity(this.getLoginUser().getDirectunion());
		changeApply.setNewprovince(this.getLoginUser().getProvinceunion());
		changeApply.setLawyername(this.getLoginUser().getLawyername());
		changeApply.setApplyname(this.getLoginUser().getLawyername());
		changeApply.setApplyusertype(1);
		if (isedit == 0)
			basicService.save(changeApply);
		else {
			changeApply.setRemarks((changeApply.getRemarks() == null ? "" : changeApply.getRemarks() + "|") + "重新提交");
			basicService.update(changeApply);
		}
		this.message = "您的转所申请提交成功";

		this.nextPage = "officeChangeApplyList.pl";

		return SUCCESS;
	}

	public String input() throws Exception {

		String hql = "from LawyersOfficeChangeApply where status=0 and lawyerid=" + this.getLoginUser().getLawyerid();
		List list = basicService.find(hql);
		if (list != null && list.size() > 0) {
//			changeApply = (LawyersOfficeChangeApply) list.get(0);
//			isedit = 1;
			this.message = "您的转所申请已经提交,请等待管理员审批";
			return "message";
		} else {
			changeApply = new LawyersOfficeChangeApply();
			isedit = 0;
		}
		com.changpeng.common.CommonDatas.getGroups();
		// System.out.println(com.changpeng.common.CommonDatas.groups);

		this.officelist = basicService.find("from SysGroup where grouptype=1 and parentid="
				+ this.getLoginUser().getDirectunion());
		
		this.citylist = basicService.find("from SysGroup where grouptype=2 and parentid="
				+ this.getLoginUser().getProvinceunion());
		
		for (int i = 0; officelist != null && i < officelist.size(); i++) {
			SysGroup group = (SysGroup) officelist.get(i);
			if (group.getGroupid() == this.getLoginUser().getTheoffice()) {
				officelist.remove(group);
				break;
			}
		}
		changeApply.setLawyerid(this.getLoginUser().getLawyerid());
		changeApply.setNewcity(this.getLoginUser().getDirectunion());
		set("changeApply", changeApply);

		return INPUT;
	}

	private int isedit;

	private List officelist;
private List citylist;
	/**
 * @return the citylist
 */
public List getCitylist() {
	return citylist;
}

	public List getOfficelist() {
		return this.officelist;
	}

	public Lawyers getLawyers() {
		return this.getLoginUser();
	}

	/**
	 * @return the isedit
	 */
	public int getIsedit() {
		return isedit;
	}

	/**
	 * @param isedit
	 *            the isedit to set
	 */
	public void setIsedit(int isedit) {
		this.isedit = isedit;
	}

}
