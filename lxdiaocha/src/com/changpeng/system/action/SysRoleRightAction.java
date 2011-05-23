/**
 * TSysUserAddAction.java
 */
package com.changpeng.system.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.system.SysRight;
import com.changpeng.models.system.SysRole;
import com.changpeng.system.service.SysRoleService;
import com.changpeng.system.util.RightTree;

/**
 * 
 * 用户信息列表
 * 
 * @author 华锋
 * 2008-2-25 上午11:12:05
 *
 */
public class SysRoleRightAction extends AbstractAction {

	
	private static Log LOG = LogFactory.getLog(SysRoleRightAction.class);
	private short roleid;
	private SysRoleService service;

	public short getRoleid() {
		return roleid;
	}

	public void setRoleid(short roleid) {
		this.roleid = roleid;
	}

	public SysRoleRightAction(){
		this.rightCode="sysRoleRight";
		this.service = (SysRoleService) getBean("sysRoleService");
	}
	
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		LOG.debug("选择的roles::::" + this.roleRights+",this.roleid==="+this.roleid);
	//	if (this.roleRights != null && this.roleRights.size() != 0) {
			service.assignRrights(this.roleid, this.roleRights);
	//	}
		this.message="角色权限分配成功";
		this.nextPage = "sysRoleList.pl";
		return SUCCESS;
	}
	@Override
	public String input() throws Exception {
		SysRole sysRole=(SysRole)service.getRole(roleid);
		Set<SysRight> userrights=sysRole.getSysRights();
		Iterator<SysRight> _rights=userrights.iterator();
		while(_rights.hasNext()){
			this.roleRights.add(_rights.next().getRightcode());
		}
        
		//获得所有的rights,想在这里实现排序
		List rights=RightTree.getRightList();
		for(int i=0;i<rights.size();i++){
			SysRight right=(SysRight)rights.get(i);
//			LOG.debug("==code::"+right.getRightcode()+"=="+right.getRightname()+"==="+right.getGrade());
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
	private List roleRights = new ArrayList();

	public void setRoleRights(List sysRights) {
		this.roleRights = sysRights;
	}

	public List getRoleRights() {
		return this.roleRights;
	}

	/**
	 * 所有的权限列表
	 */
	private List allRights = new ArrayList();

	public List getAllRights() {
		return this.allRights;
	}
	
}
