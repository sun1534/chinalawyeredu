/**
 * OfficeChangeApplyAction.java
 */
package com.changpeng.lawyers.action;

import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lawyers;
import com.changpeng.models.LawyersOfficeChangeApply;
import com.changpeng.models.SysGroup;
import com.changpeng.system.util.CommonDatas;

/**
 * 事务所管理员发起转所申请
 * 
 * 
 * @author 华锋 Feb 27, 20104:43:49 PM
 * 
 */
public class OfficeChangeApplyAction extends AbstractAction {

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
		this.lawyers = (Lawyers) basicService.get(Lawyers.class, lawyerid);
		changeApply.setLawyerid(lawyerid);
		changeApply.setApplyTime(new java.sql.Timestamp(System.currentTimeMillis()));
		changeApply.setOldcity(this.lawyers.getDirectunion());
		changeApply.setOldoffice(this.lawyers.getTheoffice());
		changeApply.setOldprovince(this.lawyers.getProvinceunion());
		changeApply.setNewcity(this.lawyers.getDirectunion());
		changeApply.setNewprovince(this.lawyers.getProvinceunion());
		changeApply.setLawyername(this.lawyers.getLawyername());
		changeApply.setApplyusertype(2);
		changeApply.setApplyname(this.getLoginUser().getUsername());
		String hql = "from LawyersOfficeChangeApply where applyusertype=2 and status=0 and lawyerid=" + this.lawyers.getLawyerid();
		List list = basicService.find(hql);
		if (list != null && list.size() > 0) {
			changeApply = (LawyersOfficeChangeApply) list.get(0);
			changeApply.setRemarks((changeApply.getRemarks() == null ? "" : changeApply.getRemarks() + "|") + "重新提交");
			basicService.update(changeApply);
		} else {
			
			basicService.save(changeApply);
		}
		
		this.message = lawyers.getLawyername()+"的转所申请已提交成功";

		this.nextPage = "officeChangeApplyList.pl";

		return SUCCESS;
	}

	public String input() throws Exception {

		if (this.getLoginUser().getSysGroup() != null && this.getLoginUser().getSysGroup().getGrouptype() != 1) {
			this.message = "您不是事务所管理员,不能申请";
			return "message";
		}
		this.lawyers=(Lawyers)basicService.get(Lawyers.class, lawyerid);
		String hql = "from LawyersOfficeChangeApply where status=0 and lawyerid=" + lawyerid;
		List list = basicService.find(hql);
		if (list != null && list.size() > 0) {
			this.message = "转所申请已经提交,请等待管理员审批";
			return "message";
		}

		this.lawyers = (Lawyers) basicService.get(Lawyers.class, lawyerid);
		changeApply = new LawyersOfficeChangeApply();

		CommonDatas.getGroups();

		this.officelist = basicService.find("from SysGroup where groupid!=" + lawyers.getTheoffice()
				+ " and grouptype=1 and parentid=" + this.getLoginUser().getCityid());

		set("changeApply", changeApply);

		return INPUT;
	}

	private int lawyerid;

	private List officelist;

	public List getOfficelist() {
		return this.officelist;
	}

	private List lawyerslist;

	/**
	 * @return the lawyerslist
	 */
	public List getLawyerslist() {
		return lawyerslist;
	}

	/**
	 * @return the lawyerid
	 */
	public int getLawyerid() {
		return lawyerid;
	}

	/**
	 * @param lawyerid
	 *            the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

	private Lawyers lawyers;

	public Lawyers getLawyers() {
		return lawyers;
	}

}
