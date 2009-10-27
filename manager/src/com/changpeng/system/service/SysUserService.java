/**
 * SysUserService.java
 */

package com.changpeng.system.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.exception.ServiceException;
import com.changpeng.common.util.MD5;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysRight;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysUser;
import com.changpeng.system.dao.SysRightDAO;
import com.changpeng.system.dao.SysUserDAO;
import com.changpeng.system.util.RightComparator;
import com.changpeng.system.util.RightTree;

/**
 * @author 华锋 2008-2-27 上午10:43:29
 * 
 */
public class SysUserService {

	private static Log LOG = LogFactory.getLog(SysUserService.class);

	private SysUserDAO sysUserDAO;

	private SysRightDAO sysRightDAO;

	public void setSysUserDAO(SysUserDAO userDAO) {
		this.sysUserDAO = userDAO;
	}

	public void setSysRightDAO(SysRightDAO sysRightDAO) {
		this.sysRightDAO = sysRightDAO;
	}

	private SysUser sysUser;

	public SysUser getSysUser() {
		return this.sysUser;
	}

	/**
	 * 这里要注意<br/> 1、现在的删除，可能是逻辑删除，如果设置loginname唯一的话，看怎么处理 2、新增删除和更新了后，怎么保留修改的痕迹
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	public void addUser(SysUser user) throws ServiceException {
		try {
			user.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			String md5pass = user.getPassword();
			md5pass = MD5.md5(user.getPassword());
			user.setSystemno(System.currentTimeMillis() + "");
			user.setPassword(md5pass);
			sysUserDAO.save(user);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 同时
	 * 
	 * @param user
	 * @param roleid
	 * @throws ServiceException
	 */
	@Transactional
	public void addUser(final SysUser user, final int roleid) throws ServiceException {
		try {
			SysRole role = new SysRole();
			role.setRoleid(roleid);
			user.setSysRole(role);

			addUser(user);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	//
	// /**
	// *
	// * @param user
	// * @throws ServiceException
	// */
	@Transactional
	public void updateUser(SysUser user) throws ServiceException {
		try {
			String md5pass = MD5.md5(user.getPassword());
			user.setPassword(md5pass);
			sysUserDAO.update(user);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @param userids
	 * @return
	 * @throws ServiceException
	 */
	public int deleteUsers(int[] userids) throws ServiceException {
		try {
			return sysUserDAO.deleteUsers(userids);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public SysUser getUser(int userid) throws ServiceException {
		try {
			return (SysUser) sysUserDAO.get(SysUser.class, userid);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param userid
	 * @param oldpass
	 * @param newpass
	 * @return
	 * @throws ServiceException
	 */
	public int changePass(int userid, String oldpass, String newpass) throws ServiceException {
		try {
			SysUser user = (SysUser) sysUserDAO.get(SysUser.class, userid);

			String pass = user.getPassword();

			String md5pass =  MD5.md5(oldpass);
			if (!pass.equals(md5pass)) {
				return 1;// 输入的旧密码不对
			}

			String newmd5pass = MD5.md5(newpass);
			user.setPassword(newmd5pass);
			sysUserDAO.update(user);
			return 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@Transactional
	public int userLogin(final String loginName) throws ServiceException {
		try {

			sysUser = sysUserDAO.getSysUserByLoginname(loginName);
			int i = -1;
			if (sysUser!=null) {// i<0的话,登录错误
				// 得到rightcodelist
		
				i=sysUser.getUserid();
				// LOG.debug("部门:" + sysUser.getSysGroup());
				LOG.debug("角色:" + sysUser.getSysRole());
				if (sysUser.getSysRole() != null)
					LOG.debug("角色可见性::" + sysUser.getSysRole().getSysRoleVisibles());
				if (sysUser.getSysGroup() != null) {
					LOG.debug("部门参数::" + sysUser.getSysGroup().getSysUnionparams());
				}

				// LOG.debug("权限:" + sysUser.getSysRights());
				// 登录的时候获取下来与这个用户相关的一些数据下来
				if (sysUser.getLoginname().equals("admin")) {
					List<SysRight> userMenus = new ArrayList<SysRight>();
					List<SysRight> rightList = new ArrayList<SysRight>();
					getAdminRightsMenus(rightList, userMenus);
					sysUser.setUserMenus(userMenus);
					sysUser.setRightList(getUserRightCodes(rightList));
					// rightcode就不管了，不管是否最底层的，都拿过来了
				} else { // 不是admin的话,就要去取了
					Set<SysRight> rights = getUserRights(sysUser);// 得到这个人的所有权限信息
					sysUser.setRightList(getUserRightCodes(rights));

					List<SysRight> userMenus = getUserMenus(rights);
					sysUser.setUserMenus(userMenus);

				}

			}
			return new Integer(i);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * 
	 * 在这里面获取所有的信息,权限,菜单,用户等等 放在一个事务里面进行处理
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public int userLogin(final String loginName, final String password) throws ServiceException {
		try {
			// TransactionTemplate transactionTemplate = new
			// TransactionTemplate(this.transactionManager);
			// Object object = transactionTemplate.execute(new
			// TransactionCallback() {
			// public Object doInTransaction(TransactionStatus status) {
			int i = sysUserDAO.userLogin(loginName, password);
			if (i > 0) {// i<0的话,登录错误
				// 得到rightcodelist
				sysUser = sysUserDAO.getSysUser();// 登录成功了，获取其他信息等
				// LOG.debug("部门:" + sysUser.getSysGroup());
				LOG.debug("角色:" + sysUser.getSysRole());
				if (sysUser.getSysRole() != null)
					LOG.debug("角色可见性::" + sysUser.getSysRole().getSysRoleVisibles());
				if (sysUser.getSysGroup() != null) {
					LOG.debug("部门参数::" + sysUser.getSysGroup().getSysUnionparams());
				}

				// LOG.debug("权限:" + sysUser.getSysRights());
				// 登录的时候获取下来与这个用户相关的一些数据下来
				if (sysUser.getLoginname().equals("admin")) {
					List<SysRight> userMenus = new ArrayList<SysRight>();
					List<SysRight> rightList = new ArrayList<SysRight>();
					getAdminRightsMenus(rightList, userMenus);
					sysUser.setUserMenus(userMenus);
					sysUser.setRightList(getUserRightCodes(rightList));
					// rightcode就不管了，不管是否最底层的，都拿过来了
				} else { // 不是admin的话,就要去取了
					Set<SysRight> rights = getUserRights(sysUser);// 得到这个人的所有权限信息
					sysUser.setRightList(getUserRightCodes(rights));

					List<SysRight> userMenus = getUserMenus(rights);
					sysUser.setUserMenus(userMenus);

				}

			}
			return new Integer(i);
			// }
			// });
			// return ((Integer) object).intValue();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据用户id得到这个人的所有权限列表 如果是admin用户,不予理睬,直接显示所有的right
	 * 
	 * @param userid
	 * @return
	 */
	private Set<SysRight> getUserRights(SysUser sysuser) {
		Set<SysRight> rightList = new HashSet<SysRight>();
		Map<String, SysRight> rightMap = new HashMap<String, SysRight>();
		// if (!sysuser.getLoginname().equals("admin")) {
		Set<SysRight> rights = sysuser.getSysRights();// 得到已经绑定好了的每人的权限

		// Set<SysRole> roles = sysuser.getSysRoles();// 得到每个人绑定好的角色
		// SysRole roles = sysUser.getSysRole(); // 这个人的登录权限都从sys_user_rights中得来
		if (rights != null && rights.size() != 0) {// 绑定了权限
			Iterator<SysRight> rightIterator = rights.iterator();
			while (rightIterator.hasNext()) {// 根据sys_user_right得到right信息
				SysRight right = rightIterator.next();
				rightList.add(right);
				rightMap.put(right.getRightcode(), right);
				LOG.debug(sysuser.getLoginname() + "绑定的权限::" + right.getRightcode() + "-" + right.getRightname());
			}
		}
		// if (roles != null && roles.size() != 0) {// 绑定了角色
		// Iterator<SysRole> roleIterator = roles.iterator();
		// while (roleIterator.hasNext()) {
		SysRole role = sysUser.getSysRole();
		LOG.debug(sysuser.getLoginname() + "绑定的角色::" + role.getRolename());
		Set<SysRight> rolerights = role.getSysRights();
		Iterator<SysRight> rolerightIterator = rolerights.iterator();// 如果设定了角色的话,则一定就有权限
		while (rolerightIterator.hasNext()) {
			SysRight right = rolerightIterator.next();
			LOG.debug("---" + role.getRolename() + "绑定的权限::" + right.getRightcode() + "-" + right.getRightname());
			rightList.add(right);
			rightMap.put(right.getRightcode(), right);
		}

		// 排除掉所在部门的功能
		SysGroup group = sysuser.getSysGroup();
		List excluderightcodes = new ArrayList();
		if (group != null && group.getGrouptype() >= 3) {
			excluderightcodes = sysUserDAO.find("select a.rightcode from SysGroupExcludeRights a where a.groupid="
					+ group.getGroupid());
		} else if (group != null && group.getGrouptype() == 2) {
			excluderightcodes = sysUserDAO.find("select a.rightcode from SysGroupExcludeRights a where a.groupid="
					+ group.getGroupid() + " or (a.groupid=" + group.getParentid() + " and a.recursion=1)");
		} else if (group != null && group.getGrouptype() == 1) {
			excluderightcodes = sysUserDAO.find("select a.rightcode from SysGroupExcludeRights a where a.groupid="
					+ group.getGroupid() + " or (a.groupid=" + group.getParentid()
					+ " and a.recursion=1) or (a.groupid=" + group.getDirectgroup() + " and a.recursion=1)");
		}

		Iterator<String> iterator = rightMap.keySet().iterator();
		while (iterator.hasNext()) {
			String code = iterator.next();
			SysRight right = rightMap.get(code);
			if (excluderightcodes.contains(code))
				rightList.remove(right);

		}
		rightMap.clear();
		rightMap=null;
		//如上，排除掉了该人员所在部门的功能

		// }
		// }
		// 获取iscommon的权限
		// List list = sysRightDAO.find("from
		// com.changpeng.models.system.SysRight right where
		// right.iscommon=true");
		// DetachedCriteria detachedCriteria =
		// DetachedCriteria.forClass(SysRight.class).add(
		// Restrictions.eq("iscommon", true));
		// List list = sysRightDAO.findAllByCriteria(detachedCriteria);
		// for (int i = 0; list != null && i < list.size(); i++) {
		// SysRight right = (SysRight) list.get(i);
		// rightList.add(right);
		// }

		return rightList;
	}

	/**
	 * 得到权限code列表
	 * 
	 * @param user
	 * @return
	 */

	// @Transactional
	// public Set<String> getUserRightCodes(final int userid) throws
	// ServiceException {
	// try {
	// // TransactionTemplate transactionTemplate = new
	// // TransactionTemplate(this.transactionManager);
	// // Object object = transactionTemplate.execute(new
	// // TransactionCallback() {
	// // public Object doInTransaction(TransactionStatus status) {
	// SysUser sysuser = (SysUser) sysUserDAO.get(SysUser.class, userid);
	// Set<SysRight> rights = getUserRights(sysuser);
	// Set<String> rightcodes = getUserRightCodes(rights);
	// return rightcodes;
	// // }
	// // });
	// // return (Set<String>) object;
	// } catch (Exception e) {
	// throw new ServiceException(e);
	// }
	// }
	/**
	 * 根据权限得到所有的rightcodes
	 * 
	 * @param rights
	 * @return
	 */
	public Set<String> getUserRightCodes(Set<SysRight> rights) {
		Set<String> rightList = new HashSet<String>();
		Iterator<SysRight> rightIterator = rights.iterator();
		while (rightIterator.hasNext()) {
			String rightcode = rightIterator.next().getRightcode();
			LOG.debug("整理后所有的权限代码::::" + rightcode);
			rightList.add(rightcode);
		}
		return rightList;
	}

	/**
	 * 根据权限得到所有的rightcodes
	 * 
	 * @param rights
	 * @return
	 */
	public Set<String> getUserRightCodes(List<SysRight> rights) {
		Set<String> rightList = new HashSet<String>();
		Iterator<SysRight> rightIterator = rights.iterator();
		while (rightIterator.hasNext()) {
			String rightcode = rightIterator.next().getRightcode();
			LOG.debug("整理后所有的权限代码::::" + rightcode);
			rightList.add(rightcode);
		}
		return rightList;
	}

	/**
	 * 得到显示的菜单，即获取ismenu=true的部分<br/>
	 * 在金助理部分中,考虑菜单部分的显示固定死。即角色部分所分配的权限，在后台管理系统中来进行设置
	 * 
	 * @param rights
	 * @return
	 */
	public List<SysRight> getUserMenus(Set<SysRight> rights) {
		Set<SysRight> menus = new HashSet<SysRight>();// 返回的menus
		// 然后找到这个menu的上级是不是0或者层级是否1，如果不是，则往上翻，将这个menu对应的上级menu也翻出来
		Iterator<SysRight> menuIterator = rights.iterator();
		String parentcode = ""; // 如果2个的parentcode相同,就不在比较了,因为父级已经找到了
		while (menuIterator.hasNext()) {
			SysRight menu = menuIterator.next();

			// 找到这个menu的上级的上级的上级,一路往上找,加入到最终的menu列表中
			// if (menu.getMenulevel() != 1 || (menu.getParentid() != null &&
			// !menu.getParentid().equals(""))) {
			// 如果不是顶级的话,就找到父菜单
			// if (menu.getGrade() != 1) {
			// LOG.debug("此菜单的代码=" + menu.getRightcode() + ",现在设置的上级=" +
			// parentcode + ",实际的上级=" + menu.getParentcode());
			if (!menu.getParentcode().equals(parentcode)) {
				parentcode = menu.getParentcode();

				List<SysRight> parentMenu = RightTree.getParentRights(menu.getRightcode());

				// LOG.debug("---\"" + menu.getRightname() + "\"的上级菜单列表:::" +
				// parentMenu);
				// 这个地方有个问题,就是会多次获得相同的上级信息,找个办法将这个解决掉
				for (int i = 0; i < parentMenu.size(); i++) {
					SysRight sysMenu = parentMenu.get(i);
					// LOG.debug("------\"" + menu.getRightname() + "\"的上级菜单" +
					// (i + 1) + "::" + sysMenu.getRightname() + ",顺序:" +
					// sysMenu.getOrderid()
					// + ",ISMENU=" + sysMenu.getIsmenu());
					if (sysMenu.getIsmenu()) // 如果这是一个菜单的话,就加入进来
						menus.add(sysMenu);
				}
			} else {
				LOG.debug("具有同样的上级菜单,不再做出处理");
			}
			// }
			// LOG.debug("将自己加入到菜单列表中::" + menu.getRightname());
			if (menu.getIsmenu())
				// menus.add(menu);// 将自己加入到菜单列表中
				menus.add(RightTree.rightMap.get(menu.getRightcode()));
		}
		LOG.debug("menus.size()::::::::::" + menus.size());

		List<SysRight> list = new ArrayList<SysRight>();
		list.addAll(menus);
		// 将菜单排序
		Collections.sort(list, new RightComparator());
		return list;
	}

	/**
	 * 得到管理员的菜单和所有权限列表
	 * 
	 * @param rightList
	 * @param menus
	 */
	private void getAdminRightsMenus(List<SysRight> rightList, List<SysRight> menus) {

		List list = RightTree.getRightList();
		for (int i = 0; i < list.size(); i++) {
			SysRight right = (SysRight) list.get(i);
			if (right.getIsmenu())
				menus.add(right);
			rightList.add(right);
		}
	}

	/**
	 * 得到userid和loginname的对应关系表
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public void getAllUsersMap(Map loginname, Map username) throws ServiceException {

		List list = sysUserDAO.findAll(SysUser.class);
		for (int i = 0; list != null && i < list.size(); i++) {
			SysUser user = (SysUser) list.get(i);
			loginname.put(user.getUserid(), user.getLoginname());
			username.put(user.getUserid(), user.getUsername());
		}
	}

	// /**
	// * 多对多中间表数据的删除
	// *
	// * @param userid
	// * @return
	// * @throws ServiceException
	// */
	// private int deleteRoles(final int userid) throws ServiceException {
	// try {
	// TransactionTemplate transactionTemplate = new
	// TransactionTemplate(this.transactionManager);
	// Object object = transactionTemplate.execute(new TransactionCallback() {
	// public Object doInTransaction(TransactionStatus status) {
	//
	// SysUser sysUser = (SysUser) sysUserDAO.get(SysUser.class, userid);
	// Set<SysRole> roles = sysUser.getSysRoles();
	// Iterator<SysRole> i = roles.iterator();
	// while (i.hasNext()) {
	// SysRole role = i.next();
	// role.getSysUsers().remove(sysUser);
	// i.remove();
	// }
	// return new Integer(0);
	// }
	// });
	// return ((Integer) object).intValue();
	// } catch (Exception e) {
	//
	// throw new ServiceException(e);
	// }
	//
	// }

	// public int assignRolesNotransaction(final SysUser sysUser, final List
	// roleids) {
	//
	// int len = roleids.size();
	//
	// Set<SysRole> roles = sysUser.getSysRoles();
	//
	// roles.clear();
	//
	// LOG.info("删除掉所有的角色......");
	// for (int i = 0; i < len; i++) {
	// SysRole role = (SysRole) sysUserDAO.get(SysRole.class,
	// Short.parseShort(roleids.get(i).toString()));
	// role.getSysUsers().add(sysUser);
	// // sysUser.getSysRoles().add(role);
	// roles.add(role);
	// }
	// return len;
	// }

	// /**
	// * 给userid新增roleids这些个角色
	// *
	// * @param userid
	// * @param roleids
	// * @throws ServiceException
	// */
	// @Transactional
	// public int assignRoles(final int userid, final List roleids, final int
	// locationid) throws ServiceException {
	// try {
	// // TransactionTemplate transactionTemplate = new
	// TransactionTemplate(this.transactionManager);
	// // Object object = transactionTemplate.execute(new TransactionCallback()
	// {
	// // public Object doInTransaction(TransactionStatus status) {
	// int len = roleids.size();
	// SysUser sysUser = (SysUser) sysUserDAO.get(SysUser.class, userid);
	//
	// assignRolesNotransaction(sysUser, roleids);
	// return new Integer(len);
	// // }
	// // });
	// // return ((Integer) object).intValue();
	// } catch (Exception e) {
	// throw new ServiceException(e);
	// }
	// }

	/**
	 * * 给userid新增rightcods这些个功能
	 * 
	 * @param userid
	 * @param rightcodes
	 * @throws ServiceException
	 */
	@Transactional
	public int assignRrights(final int userid, final List _rigths) throws ServiceException {
		try {
			// TransactionTemplate transactionTemplate = new
			// TransactionTemplate(this.transactionManager);
			// Object object = transactionTemplate.execute(new
			// TransactionCallback() {
			// public Object doInTransaction(TransactionStatus status) {
			int len = _rigths == null ? 0 : _rigths.size();
			SysUser sysUser = (SysUser) sysUserDAO.get(SysUser.class, userid);
			// 先删除掉所有的权限
			sysUser.getSysRights().clear();

			if (len != 0) {
				for (Object rightCodeOjbect : _rigths) {
					SysRight right = (SysRight) sysRightDAO.get(SysRight.class, rightCodeOjbect.toString());
					sysUser.getSysRights().add(right);
				}
			}
			return new Integer(len);
			// }
			// });
			// return ((Integer) object).intValue();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public SysUser getSysUserByLoginname(String loginname) throws ServiceException {
		try {
			return sysUserDAO.getSysUserByLoginname(loginname);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public SysUser getSysUserBySystemno(String systemno) throws ServiceException {
		try {
			return sysUserDAO.getSysUserBySystemno(systemno);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// 编辑时判断用户名是否重复
	public SysUser getSysUser(String loginname, int userid) throws ServiceException {
		try {
			return sysUserDAO.getSysUser(loginname, userid);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}