 /**
 * TSysUserAddAction.java
 */
package com.changpeng.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysGroupExcludeRights;
import com.changpeng.models.SysRight;
import com.changpeng.system.service.SysGroupService;

/**
 * 
 * 针对sys_right中isextend=1的，看这个律协是否进行排除，是排除的话，就将他插入到表sys_group_exclude_rights中
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysGroupExcludeRightAction extends AbstractAction {

	private static Log LOG = LogFactory.getLog(SysGroupExcludeRightAction.class);

	private int groupid;

	public SysGroupExcludeRightAction() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("选择的roles::::" +
		// this.assignedRightcode+",this.roleid==="+this.roleid);
		// if (this.roleRights != null && this.roleRights.size() != 0) {

		SysGroupService service = (SysGroupService) getBean("sysGroupService");

		service.excludeRrights(this.groupid, excludedRightcode, recursion, remarks);
		// }
		this.opResult = "管理员" + super.getLoginUser().getLoginname() + "排除了部门" + groupid + "的权限:" + excludedRightcode;
		this.message = "此部门部分功能排除成功";
		this.nextPage = backurl;
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
SysGroupService groupservice= (SysGroupService) getBean("sysGroupService");
		this.group = (SysGroup) basicService.get(SysGroup.class, groupid);
		if (this.group == null) {
			this.message = "该部门不存在,请返回";
			return "message";
		}
		if (group.getGrouptype() == 1) {
			backurl = "theOfficeList.pl";
		} else if (group.getGrouptype() == 2) {
			backurl = "theUnionList.pl";
		} else if (group.getGrouptype() == 3) {
			backurl = "theUnionList.pl";
		} else {
			backurl = "sysGroupList.pl";
		}
		// 找到所有需要排除的功能
		DetachedCriteria dc = DetachedCriteria.forClass(SysRight.class).add(Restrictions.eq("isextend", true));
		allRights = basicService.findAllByCriteria(dc);
		if (allRights == null || allRights.size() == 0) {
			this.message = "没有需要排除的功能,请返回";
			return "message";
		}
		// 当前这个部门被排除的功能
		List excludedRights = groupservice.getExcludedRights(groupid);

		Iterator<SysGroupExcludeRights> _rights = excludedRights.iterator();
		while (_rights.hasNext()) {
			SysGroupExcludeRights value=_rights.next();
			this.excludedRightcode.add(value.getRightcode());
			maps.put(value.getRightcode(), value);
		}

		return INPUT;
	}
	
	private Map<String,SysGroupExcludeRights> maps=new HashMap<String,SysGroupExcludeRights>();
	public Map<String,SysGroupExcludeRights> getMaps(){
		return this.maps;
	}

	private SysGroup group;

	public SysGroup getGroup() {
		return this.group;
	}

	// /**
	// * 选中要排除的权限列表
	// */
	// private List excludedRights;
	//
	// public List getExcludedRights() {
	// return this.excludedRights;
	// }

	private List recursion = null;

	private String backurl;
	/**
	 * 所有需要排除的的权限列表
	 */
	private List allRights;

	public List getAllRights() {
		return this.allRights;
	}

	private List<String> excludedRightcode = new ArrayList<String>();

	public List<String> getExcludedRightcode() {
		return this.excludedRightcode;
	}

	public void setExcludedRightcode(List<String> codes) {
		this.excludedRightcode = codes;
	}

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
	 * @param recursion
	 *            the recursion to set
	 */
	public void setRecursion(List recursion) {
		this.recursion = recursion;
	}

	/**
	 * @return the backurl
	 */
	public String getBackurl() {
		return backurl;
	}

	/**
	 * @param backurl
	 *            the backurl to set
	 */
	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}

	private List remarks;

	public void setRemarks(List remarks) {
		this.remarks = remarks;
	}

}
