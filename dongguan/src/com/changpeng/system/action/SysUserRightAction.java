/**
 * TSysUserAddAction.java
 */

package com.changpeng.system.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysRight;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.service.SysUserService;
import com.changpeng.system.util.RightTree;

/**
 * 
 * 给用户分配权限
 * 
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class SysUserRightAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(SysUserRightAction.class);
	private int userid;
	private SysUserService service;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public SysUserRightAction() {
		this.rightCode = "sysUserRight";
		this.service = (SysUserService) getBean("sysUserService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		LOG.debug("选择的roles::::" + this.userRights+",this.Userid==="+this.userid);
//		if (this.userRights != null && this.userRights.size() != 0) {
			service.assignRrights(this.userid, this.userRights);
//		}
		this.message="用户权限分配成功";
		this.nextPage = "sysUserList.pl";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
	//	Set<String> rightcodes=service.getUserRightCodes(userid);
		SysUser sysUser=(SysUser)service.getUser(userid);
	//	Set<SysRight> userrights=service.getUserRights(sysUser);
		//这里只考虑分配的权限,不考虑分配的角色里面的帐号
		Set<SysRight> userrights=sysUser.getSysRights();
		Set<String> rightcodes=service.getUserRightCodes(userrights);
		
		userRights.addAll(rightcodes);
		//获得所有的rights,想在这里实现排序
		List rights=RightTree.getRightList();
		for(int i=0;i<rights.size();i++){
			SysRight right=(SysRight)rights.get(i);
			LOG.debug("==code::"+right.getRightcode()+"=="+right.getRightname()+"==="+right.getGrade());
			if(!right.getIscommon()){
				//allRights.put(right.getRightcode(), right.getRightname());
				allRights.add(right);
			}
		}
		return INPUT;
	}

	/**
	 * 选中的权限列表
	 */
	private List userRights = new ArrayList();

	public void setUserRights(List sysRights) {
		this.userRights = sysRights;
	}

	public List getUserRights() {
		return this.userRights;
	}

	/**
	 * 所有的权限列表
	 */
	private List allRights = new ArrayList();

	public List getAllRights() {
		return this.allRights;
	}
}
