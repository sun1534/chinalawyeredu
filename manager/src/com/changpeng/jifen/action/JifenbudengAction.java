/**
 * JifenbudengAction.java
 */

package com.changpeng.jifen.action;

import java.io.File;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.JifenbudengService;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Jifenbudeng;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysUnionparams;
import com.changpeng.models.SysUser;

/**
 * @author 华锋 2008-5-4 下午10:46:27
 * 
 */
public class JifenbudengAction extends AbstractAction {

	private Jifenbudeng budeng;

	// private boolean beupload;
	// private File uploadfile; // 上传文件

	// private String fileName;

	// public void setUploadfile(File uploadfile) {
	// this.uploadfile = uploadfile;
	// }
	//
	// // public void setUploadfileFileName(String fileName) {
	// // this.fileName = fileName;
	// // }
	// public void setBeupload(boolean beupload) {
	// this.beupload = beupload;
	// }

	public Jifenbudeng getBudeng() {
		if (budeng == null)
			this.budeng = (Jifenbudeng) get("budeng");
		return this.budeng;
	}

	public JifenbudengAction() {
		this.datavisible = new DataVisible();
	}

	@Override
	public String go() throws Exception {
		// 积分的补登，同时应计入积分表里面，考虑事务来处理
		JifenbudengService budengservice = (JifenbudengService) getBean("jifenbudengService");
		LawyersService lawyersService = (LawyersService) getBean("lawyersService");
		SysUser user = (SysUser) this.getLoginUser();

		Lawyers lawyer = null;
		if (get("budengexist") != null && "0".equals(get("budengexist"))) {

			// if (!beupload) {
			lawyer = (Lawyers) lawyersService.getLawyerbyLawyerno(budeng.getLawyerno(), datavisible.getProvinceid(),
					datavisible.getCityid());
			if (lawyer == null) {
				this.message = "执业资格证号:" + budeng.getLawyerno() + "对应的律师资料已不存在,请返回";
				this.nextPage = "jifenbudeng!input.pl?budengid=" + this.budengid;
				return "message";
			}
			budeng.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			budeng.setCreateuser(user.getUsername());
			budeng.setLawyerid(lawyer.getLawyerid());
			budeng.setProvinceid(lawyer.getProvinceunion());
			budeng.setCityid(lawyer.getDirectunion());
			budeng.setOfficeid(lawyer.getTheoffice());
			budeng.setLawyername(lawyer.getLawyername());
			budengservice.saveJifenbudeng(budeng);
		
			this.opResult="为"+budeng.getLawyerno()+"新增补登积分成功";
		} else {
			Float oldxuefen = (Float) get("oldbudeng");

			debug("补登前后的积分差异为:::" + (budeng.getXuefen().floatValue() - oldxuefen.floatValue()));

			budengservice.updateJifenbudeng(budeng, oldxuefen);
			this.opResult="为"+budeng.getLawyerno()+"修改补登积分成功";
		}
		this.message = "积分补登成功";
		this.nextPage = "jifenbudengList.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		jifentime = com.changpeng.jifen.util.CommonDatas.getJifenTime(0, "12-31");

		BasicService basic = (BasicService) getBean("basicService");
		this.budeng = (Jifenbudeng) basic.get(Jifenbudeng.class, budengid);
		if (this.budeng == null) {
			set("budengexist", "0");
			this.budeng = new Jifenbudeng();
		} else {

			this.datavisible.setCityid(budeng.getCityid());
			this.datavisible.setProvinceid(budeng.getProvinceid());

			set("oldbudeng", this.budeng.getXuefen());
			set("budengexist", "1");
		}

		this.datavisible.getVisibleDatas(this.getLoginUser(), false);
		set("budeng", budeng);

		return INPUT;
	}

	private JifenTime jifentime;
	private int budengid;

	public void setBudengid(int budengid) {
		this.budengid = budengid;
	}

	public int getBudengid() {
		return this.budengid;
	}

	/**
	 * @return the jifentime
	 */
	public JifenTime getJifentime() {
		return jifentime;
	}
}