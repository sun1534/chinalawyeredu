/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUnionparams;
import com.changpeng.system.util.CommonDatas;

/**
 * 
 * 律协信息的新增修改
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class TheUnionCreateEditAction extends AbstractAction {
	private BasicService bservice = null;
	private SysGroup sysGroup;

	public SysGroup getSysGroup() {
		if (sysGroup == null)
			sysGroup = (SysGroup) this.get("sysGroup");
		return this.sysGroup;
	}

	public TheUnionCreateEditAction() {
		this.datavisible = new DataVisible();

		bservice = (BasicService) this.getBean("basicService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		sysGroup.setGroupenname(com.changpeng.common.util.Chinese2Pinyin.to2pinyin(sysGroup.getGroupname()));

		if (datavisible.getProvinceid() == 0) {
			this.message = "请选择对应的省律协";
			this.nextPage = "sysGroupList.pl";
			return "message";
		}
		sysGroup.setParentid(datavisible.getProvinceid());
		sysGroup.setDirectgroup(sysGroup.getParentid());
		SysGroup parent = (SysGroup) bservice.get(SysGroup.class, sysGroup.getParentid());
		int grouplevel = 1;
		if (parent != null) {
			grouplevel = parent.getGrouplevel() + 1;
		}

		if (!isedit) {
			sysGroup.setCreateuser(getLoginUser().getUsername());
			sysGroup.setCreatetype(1);
			sysGroup.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			sysGroup.setGrouplevel(grouplevel);
			sysGroup.setDelflag(false);
			sysGroup.setSystemno(System.currentTimeMillis() / 1000 + "");

		

			bservice.save(sysGroup);
			SysUnionparams params = new SysUnionparams();
			params.setDabiaofen(dabiaofen);
			params.setGroupid(sysGroup.getGroupid());
			params.setNianshen(month + "-" + days);
			params.setSysGroup(sysGroup);
			bservice.save(params);
			this.message = "市律协信息新增成功";

		} else {
			bservice.update(sysGroup);
			SysUnionparams params = (SysUnionparams) bservice.get(SysUnionparams.class, sysGroup.getGroupid());
			if (params != null) {
				params.setDabiaofen(dabiaofen);
				params.setNianshen(month + "-" + days);

				bservice.update(params);
			} else {
				params = new SysUnionparams();
				params.setDabiaofen(dabiaofen);
				params.setGroupid(sysGroup.getGroupid());
				params.setNianshen(month + "-" + days);
				params.setSysGroup(sysGroup);
				bservice.save(params);
			}

			this.message = "市律协信息修改成功";
		}

		CommonDatas.getGroups();
		CommonDatas.getGroupParamsUrl();
		
		debug("刷新下group的信息");

		this.nextPage = "theUnionList.pl";

		// return "toparent";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		datavisible.getVisibleDatas(this.getLoginUser(), false);
		sysGroup = (SysGroup) bservice.get(SysGroup.class, groupid);

		if (this.sysGroup == null) {
			sysGroup = new SysGroup();
			sysGroup.setGrouptype(2);
			isedit = false;
		} else {

			if (sysGroup.getGrouptype() == 3) {
				this.message = "省律协的修改,请联系技术人员";
				this.nextPage = "theUnionList.pl";
				return "message";
			}

			datavisible.setProvinceid(sysGroup.getParentid());

			SysUnionparams params = sysGroup.getSysUnionparams();
			if (params != null) {
				dabiaofen = params.getDabiaofen();
				String nianshen = params.getNianshen();
				int inx = nianshen.indexOf("-");
				this.month = nianshen.substring(0, inx);
				this.days = nianshen.substring(inx + 1);
			}
			isedit = true;
		}

		set("sysGroup", sysGroup);

		return INPUT;
	}

	private boolean isedit = false;

	private int groupid;

	/**
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid
	 *            the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	private float dabiaofen;
	private String month;
	private String days;

	/**
	 * @return the dabiaofeng
	 */
	public float getDabiaofen() {
		return dabiaofen;
	}

	/**
	 * @param dabiaofeng
	 *            the dabiaofeng to set
	 */
	public void setDabiaofen(float dabiaofeng) {
		this.dabiaofen = dabiaofeng;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the days
	 */
	public String getDays() {
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(String days) {
		this.days = days;
	}

	/**
	 * @return the isedit
	 */
	public boolean isIsedit() {
		return isedit;
	}

	/**
	 * @param isedit
	 *            the isedit to set
	 */
	public void setIsedit(boolean isedit) {
		this.isedit = isedit;
	}
}