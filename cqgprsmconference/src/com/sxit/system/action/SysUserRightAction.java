/**
 * TSysUserAddAction.java
 */

package com.sxit.system.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.BasicService;
import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysRight;
import com.sxit.models.system.SysRole;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysUserService;
import com.sxit.system.util.CommonDatas;
import com.sxit.system.util.RightComparator;
import com.sxit.system.util.RightTree;

/**
 * 
 * 给用户分配权限,分配的人有什么就只能分配什么
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 2009-3-12 Tompan修改用户权限部分
 */
public class SysUserRightAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(SysUserRightAction.class);
	private int userid;
//	private short roleid;
	private SysUserService userService;
//	private SysRoleService roleService;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public SysUserRightAction() {
		// this.rightCode = "sysUserRight";
		this.userService = (SysUserService) getBean("sysUserService");
//		this.roleService = (SysRoleService) getBean("sysRoleService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		LOG.debug("选择的roles::::" + this.assignedRightcode + ",this.Userid===" + this.userid);
		if (this.assignedRightcode != null && this.assignedRightcode.size() != 0) {
			userService.assignRrights(this.userid, this.assignedRightcode);
			this.message = "用户权限分配成功";
		} else {
			this.message = "请选择需要分配的权限";
		}

		this.nextPage = "sysUserList.action";
		this.opResult = "管理员" + super.getLoginUser().getLoginname() + "给用户"+CommonDatas.getLoginNameByUserId(String.valueOf(userid))+"进行了权限分配";
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		// Set<String> rightcodes=service.getUserRightCodes(userid);
		SysUser sysUser = (SysUser) userService.getUser(userid);
		// Set<SysRight> userrights=service.getUserRights(sysUser);
		// 这里只考虑分配的权限,不考虑分配的角色里面的帐号
		assignedRights = sysUser.getSysRights();
		Set<String> rightcodes = userService.getUserRightCodes(assignedRights);

		assignedRightcode.addAll(rightcodes);
		
		BasicService bs=(BasicService)this.getBean("basicService");
		//2009-3-11 Tompan Add it判断用户是否是admin,如果是就显示全部权限
//		SysUser sysUserSelf = (SysUser)bs.get(SysUser.class, this.getLoginUser().getUserid());
//		System.out.println("UserID Myself:"+sysUserSelf.getLoginname());
//		if (!sysUserSelf.getLoginname().equals("admin"))
//		{
			
			//左边的权限列表从这个人的角色得来，也就是这个人拥有的权限不会大于他对应角色的权限
			
//			if(sysUserSelf.getSysRights().isEmpty()){
//				
//				this.message="您没有可以分配的权限列表.请返回";
//				this.nextPage="sysUserList.action";
//				return "message";
//			}
			
			
			
			Set<SysRole> roles = sysUser.getSysRoles();
			if(!(roles!=null&&roles.size()!=0)){
				this.message="该用户不属于任何角色,请先分配!";
				this.nextPage="sysUserList.action";
				return "message";
			}
			
			
//			Set<SysRight> rights=roles.iterator().next().getSysRights();
			Set<SysRight> _allRights=roles.iterator().next().getSysRights();
			allRights=new ArrayList<SysRight>();
			allRights.addAll(_allRights);
			// 将菜单排序
			Collections.sort(allRights, new RightComparator());
//			Set<SysRight> rights=sysUserSelf.getSysRights();
//			allRights=userService.getUserMenus(rights);
			
//			Set<SysRole> _sysRoles = sysUser.getSysRoles();
//			for (SysRole role : _sysRoles) 
//			{
//				roleid = role.getRoleid();
//				break;
//			}
////		System.out.println("Get RoleID:"+roleid);
//		
//			SysRole sysRole=(SysRole)roleService.getRole(roleid);
//			roleAssignedRights=sysRole.getSysRights();
//			Iterator<SysRight> _rights=roleAssignedRights.iterator();
//			while(_rights.hasNext())
//			{
////			this.roleRights.add(_rights.next());
//				allRights.add(_rights.next());
////			allRights.add(_rights.next());
////			System.out.println("Get Role Right:"+_rights.next().getRightcode());
//			}
//		}
//		else
//		{
//			// 获得所有的rights,想在这里实现排序
//			List rights = RightTree.getRightList();
//			for (int i = 0; i < rights.size(); i++) 
//			{
//				SysRight right = (SysRight) rights.get(i);
//				//LOG.debug("==code::" + right.getRightcode() + "==" + right.getRightname() + "===" + right.getGrade());
////				System.out.println("==code::" + right.getRightcode() + "==" + right.getRightname() + "===" + right.getGrade());
//				// if(!right.getIscommon()){
//				// allRights.put(right.getRightcode(), right.getRightname());
//				allRights.add(right);
//				// }
//			}
//		}
		
//		System.out.println("roleRights==================");
		
		return INPUT;
	}

	/**
	 * 选中的权限列表
	 */

	private List<String> assignedRightcode=new ArrayList<String>();
	public List<String> getAssignedRightcode() {
		return this.assignedRightcode;
	}
	public void setAssignedRightcode(List<String> codes){
		this.assignedRightcode=codes;
	}
	/**
	 * 选中的权限列表
	 */
	private Set<SysRight> assignedRights ;



	public Set<SysRight> getAssignedRights() {
		return this.assignedRights;
	}
	
//	private Set<SysRight> roleAssignedRights ;
	
	/**
	 * 角色列表
	 */
//	private Set<SysRole> userRole ;
	
	/**
	 * 所有需要分配的权限列表
	 */
	private List<SysRight> allRights ;

	public List<SysRight> getAllRights() {
		return this.allRights;
	}
}
