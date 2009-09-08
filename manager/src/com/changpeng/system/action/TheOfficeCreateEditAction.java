/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.SysGroup;
import com.changpeng.system.util.CommonDatas;

/**
 * 
 * 事务所信息的新增修改
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class TheOfficeCreateEditAction extends AbstractAction {
	private BasicService bservice = null;
	private SysGroup sysGroup;

	public SysGroup getSysGroup() {
		if (sysGroup == null)
			sysGroup = (SysGroup) this.get("sysGroup");
		return this.sysGroup;
	}

	public TheOfficeCreateEditAction() {
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

		if (datavisible.getCityid() == 0) {
			this.message = "请选择对应的市律协";
			this.nextPage = "theOfficeList.pl";
			return "message";
		}

		if (datavisible.getProvinceid() == 0) {
			this.message = "请选择对应的省律协";
			this.nextPage = "theOfficeList.pl";
			return "message";
		}

		SysGroup parent = (SysGroup) bservice.get(SysGroup.class, sysGroup.getParentid());
		int grouplevel = 1;
		if (parent != null) {
			grouplevel = parent.getGrouplevel() + 1;
		}

		sysGroup.setParentid(datavisible.getCityid());
		sysGroup.setDirectgroup(datavisible.getProvinceid());
		
		sysGroup.setGrouplevel(grouplevel);
		sysGroup.setDelflag(false);
		

		if (!isedit) {
			sysGroup.setCreatetype(1);
			sysGroup.setCreateuser(getLoginUser().getUsername());
			sysGroup.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			sysGroup.setSystemno(System.currentTimeMillis() / 1000 + "");
			bservice.save(sysGroup);
			this.message = "事务所信息新增成功";
		} else {
			bservice.update(sysGroup);
			this.message = "事务所信息修改成功";
		}

		this.nextPage = "theOfficeList.pl";

		CommonDatas.getGroups();
		System.out.println("刷新下group的信息");
		// return "toparent";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		
		sysGroup = (SysGroup) bservice.get(SysGroup.class, groupid);
	
		
	

		if (this.sysGroup == null) {
			sysGroup = new SysGroup();
			sysGroup.setGrouptype(1); // 事务所的新增修改
			isedit = false;
		} else {

			if (sysGroup.getGrouptype() != 1) {
				this.message = "你修改的不是事务所信息,请返回";
				this.nextPage = "theOfficeList.pl";
				return "message";
			}
			datavisible.setCityid(sysGroup.getParentid());
			datavisible.setProvinceid(sysGroup.getDirectgroup());
			datavisible.setOfficeid(groupid);

			isedit = true;
		}
		datavisible.getVisibleDatas(this.getLoginUser(), false);

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