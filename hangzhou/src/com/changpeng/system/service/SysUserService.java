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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.changpeng.common.PaginationSupport;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.common.util.MD5;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysRight;
import com.changpeng.models.system.SysRole;
import com.changpeng.models.system.SysUser;
import com.changpeng.system.dao.SysRightDAO;
import com.changpeng.system.dao.SysRoleDAO;
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

	private SysRoleDAO sysRoleDAO;

	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setSysUserDAO(SysUserDAO userDAO) {
		this.sysUserDAO = userDAO;
	}

	public void setSysRightDAO(SysRightDAO sysRightDAO) {
		this.sysRightDAO = sysRightDAO;
	}

	public void setSysRoleDAO(SysRoleDAO sysRoleDAO) {
		this.sysRoleDAO = sysRoleDAO;
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
			if (user.getUserid() == 0)
				user.setUserid((int)(System.currentTimeMillis() / 10000));
//			String md5pass = MD5.md5(user.getPassword());
//			// user.setPassword(md5pass+(user.getPasskey()==null?"":user.getPasskey()));
//			user.setPassword(md5pass);
			// user.setDelflag(false);
			sysUserDAO.save(user);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 新增律师，同时要新增为律师角色
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	public void addLawyer(final SysUser user) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					user.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
					user.setRoleid(1);
					// 设置律师角色
					if (user.getSysGroup() == null) {
						// throw new java.lang.RuntimeException("新增律师,请设置对应的事务所");
						LOG.warn(user.getLawerno() + "没有设置到对应的事务所,不予考虑");
						// SysGroup sysGroup=(SysGroup)sysUserDAO.get(SysGroup.class, id)
					}
					if (user.getUserid() == 0)
						user.setUserid((int)(System.currentTimeMillis() / 10000));
					List roleids = new ArrayList();
					roleids.add((short) 1);
//					String md5pass = MD5.md5(user.getPassword());
//					// user.setPassword(md5pass+(user.getPasskey()==null?"":user.getPasskey()));
//					user.setPassword(md5pass);
					// user.setDelflag(false);
					sysUserDAO.save(user);
					assignRolesNotransaction(user, roleids);
				}
			});
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 新增系统管理员,系统管理员的groupid都设置为1
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	public void addSysManager(final SysUser user) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					user.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));

					SysGroup sysGroup = (SysGroup) sysUserDAO.get(SysGroup.class, 1);
					user.setSysGroup(sysGroup);
					if (user.getUserid() == 0)
						user.setUserid((int)(System.currentTimeMillis() / 10000));
					// 设置律师角色
					List roleids = new ArrayList();
					roleids.add((short) 3);
//					String md5pass = MD5.md5(user.getPassword());
//					// user.setPassword(md5pass+(user.getPasskey()==null?"":user.getPasskey()));
//					user.setPassword(md5pass);
					// user.setDelflag(false);
					sysUserDAO.save(user);
					assignRolesNotransaction(user, roleids);
				}
			});
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 新增事务所管理员 *
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	public void addGroupManager(final SysUser user) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					user.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
					// 设置律师角色

					if (user.getSysGroup() == null)
						throw new java.lang.RuntimeException("事务所管理员,请设置对应的事务所");
					if (user.getUserid() == 0)
						user.setUserid((int)(System.currentTimeMillis() / 10000));
					List roleids = new ArrayList();
					roleids.add((short) 2);
//					String md5pass = MD5.md5(user.getPassword());
//					// user.setPassword(md5pass+(user.getPasskey()==null?"":user.getPasskey()));
//					user.setPassword(md5pass);
					// user.setDelflag(false);
					sysUserDAO.save(user);
					assignRolesNotransaction(user, roleids);
				}
			});
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @param userid
	 * @return
	 * @throws ServiceException
	 */
	public SysUser getUser(int userid) throws ServiceException {
		try {
			SysUser user = (SysUser) sysUserDAO.get(SysUser.class, new Integer(userid));
			return user;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	public void updateUser(SysUser user) throws ServiceException {
		try {
			sysUserDAO.update(user);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 逻辑删除
	 * 
	 * @param userid
	 * @throws ServiceException
	 */
	public void deleteUserLogic(int userid) throws ServiceException {
		try {
			SysUser user = (SysUser) sysUserDAO.get(SysUser.class, userid);
			user.setDelflag(true);
			sysUserDAO.update(user);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @param userid
	 * @throws ServiceException
	 */
	public void deleteUser(int userid) throws ServiceException {
		try {
			SysUser user = (SysUser) sysUserDAO.get(SysUser.class, userid);
			sysUserDAO.delete(user);
		}
		catch (Exception e) {
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
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @param userids
	 * @return
	 * @throws ServiceException
	 */
	public int deleteUsersLogic(int[] userids) throws ServiceException {
		try {
			return sysUserDAO.deleteUsersLogic(userids);
		}
		catch (Exception e) {
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
			// String passkey = user.getPasskey();
			String pass = user.getPassword();

			// String md5pass = MD5.md5(oldpass + user.getPasskey() == null ? "" : user.getPasskey());
//			String md5pass = MD5.md5(oldpass);
		String md5pass=oldpass;
			if (!pass.equals(md5pass)) {
				return 1;// 输入的旧密码不对
			}
			// String newmd5pass = MD5.md5(newpass + user.getPasskey() == null ? "" : user.getPasskey());
//			String newmd5pass = MD5.md5(newpass);
//			user.setPassword(newmd5pass);
			
			user.setPassword(newpass);
			sysUserDAO.update(user);
			return 0;
		}
		catch (Exception e) {
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

	public int userLogin(final String loginName, final String password) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					int i = sysUserDAO.userLogin(loginName, password);
					if (i > 0) {// i<0的话,登录错误
						// 得到rightcodelist
						sysUser = sysUserDAO.getSysUser();// 登录成功了，获取其他信息等
						LOG.debug("部门:" + sysUser.getSysGroup());
						LOG.debug("角色:" + sysUser.getSysRoles());
						LOG.debug("权限:" + sysUser.getSysRights());
						// 登录的时候获取下来与这个用户相关的一些数据下来
						if (sysUser.getLoginname().equals("admin")) {
							List<SysRight> userMenus = new ArrayList<SysRight>();
							List<SysRight> rightList = new ArrayList<SysRight>();
							getAdminRightsMenus(rightList, userMenus);
							sysUser.setUserMenus(userMenus);
							sysUser.setRightList(getUserRightCodes(rightList));
							// rightcode就不管了，不管是否最底层的，都拿过来了
						}
						else { // 不是admin的话,就要去取了
							Set<SysRight> rights = getUserRights(sysUser);// 得到这个人的所有权限信息
							sysUser.setRightList(getUserRightCodes(rights));

							List<SysRight> userMenus = getUserMenus(rights);
							sysUser.setUserMenus(userMenus);

						}

					}
					return new Integer(i);
				}
			});
			return ((Integer) object).intValue();
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	public int userLogin(final String loginName) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					sysUser = sysUserDAO.getSysUserByLoginname(loginName);
					int i=-1;
					if (sysUser!=null) {// i<0的话,登录错误
						// 得到rightcodelist
						i=sysUser.getUserid();
//						sysUser = sysUserDAO.getSysUser();// 登录成功了，获取其他信息等
						LOG.debug("部门:" + sysUser.getSysGroup());
						LOG.debug("角色:" + sysUser.getSysRoles());
						LOG.debug("权限:" + sysUser.getSysRights());
						// 登录的时候获取下来与这个用户相关的一些数据下来
						if (sysUser.getLoginname().equals("admin")) {
							List<SysRight> userMenus = new ArrayList<SysRight>();
							List<SysRight> rightList = new ArrayList<SysRight>();
							getAdminRightsMenus(rightList, userMenus);
							sysUser.setUserMenus(userMenus);
							sysUser.setRightList(getUserRightCodes(rightList));
							// rightcode就不管了，不管是否最底层的，都拿过来了
						}
						else { // 不是admin的话,就要去取了
							Set<SysRight> rights = getUserRights(sysUser);// 得到这个人的所有权限信息
							sysUser.setRightList(getUserRightCodes(rights));

							List<SysRight> userMenus = getUserMenus(rights);
							sysUser.setUserMenus(userMenus);

						}

					}
					return new Integer(i);
				}
			});
			return ((Integer) object).intValue();
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据用户id得到这个人的所有权限列表 如果是admin用户,不予理睬,直接显示所有的right
	 * 
	 * @param userid
	 * @return
	 */
	public Set<SysRight> getUserRights(SysUser sysuser) {
		Set<SysRight> rightList = new HashSet<SysRight>();
		// if (!sysuser.getLoginname().equals("admin")) {
		Set<SysRight> rights = sysuser.getSysRights();// 得到已经绑定好了的每人的权限
		Set<SysRole> roles = sysuser.getSysRoles();// 得到每个人绑定好的角色
		if (rights != null && rights.size() != 0) {// 绑定了权限
			Iterator<SysRight> rightIterator = rights.iterator();
			while (rightIterator.hasNext()) {// 根据sys_user_right得到right信息
				SysRight right = rightIterator.next();
				rightList.add(right);
				LOG.debug(sysuser.getLoginname() + "绑定的权限::" + right.getRightcode() + "-" + right.getRightname());
			}
		}
		if (roles != null && roles.size() != 0) {// 绑定了角色
			Iterator<SysRole> roleIterator = roles.iterator();
			while (roleIterator.hasNext()) {
				SysRole role = roleIterator.next();
				LOG.debug(sysuser.getLoginname() + "绑定的角色::" + role.getRolename());
				Set<SysRight> rolerights = role.getSysRights();
				Iterator<SysRight> rolerightIterator = rolerights.iterator();// 如果设定了角色的话,则一定就有权限
				while (rolerightIterator.hasNext()) {
					SysRight right = rolerightIterator.next();
					LOG.debug("---" + role.getRolename() + "绑定的权限::" + right.getRightcode() + "-" + right.getRightname());
					rightList.add(right);
				}
			}
		}
		// 获取iscommon的权限
		// List list = sysRightDAO.find("from com.changpeng.models.system.SysRight right where right.iscommon=true");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysRight.class).add(Restrictions.eq("iscommon", true));
		List list = sysRightDAO.findAllByCriteria(detachedCriteria);
		for (int i = 0; list != null && i < list.size(); i++) {
			SysRight right = (SysRight) list.get(i);
			rightList.add(right);
		}
		// }
		// else {
		// List list = sysRightDAO.findAll(SysRight.class);
		// // List list=sysRightDAO.find("from com.changpeng.models.system.SysRight rights where rights.nodetype=1");
		// for (int i = 0; list != null && i < list.size(); i++) {
		// SysRight right = (SysRight) list.get(i);
		// // if (right.getNodetype() == 1) {// 忽略掉当作模块功能用的权限
		// rightList.add(right);
		// // LOG.debug("=====" + right);
		// // }
		// }
		// // rightList.addAll(list);
		// }
		return rightList;
	}

	/**
	 * 得到权限code列表
	 * 
	 * @param user
	 * @return
	 */
	// public Set<String> getUserRightCodes(final int userid) throws ServiceException {
	// try {
	// TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
	// Object object = transactionTemplate.execute(new TransactionCallback() {
	// public Object doInTransaction(TransactionStatus status) {
	// SysUser sysuser = (SysUser) sysUserDAO.get(SysUser.class, userid);
	// Set<SysRight> rights = getUserRights(sysuser);
	// Set<String> rightcodes = getUserRightCodes(rights);
	// return rightcodes;
	// }
	// });
	// return (Set<String>) object;
	// }
	// catch (Exception e) {
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
	 * 得到显示的菜单，即获取ismenu=true的部分<br/> 在金助理部分中,考虑菜单部分的显示固定死。即角色部分所分配的权限，在后台管理系统中来进行设置
	 * 
	 * @param rights
	 * @return
	 */
	private List<SysRight> getUserMenus(Set<SysRight> rights) {
		Set<SysRight> menus = new HashSet<SysRight>();// 返回的menus
		// 然后找到这个menu的上级是不是0或者层级是否1，如果不是，则往上翻，将这个menu对应的上级menu也翻出来
		Iterator<SysRight> menuIterator = rights.iterator();
		String parentcode = ""; // 如果2个的parentcode相同,就不在比较了,因为父级已经找到了
		while (menuIterator.hasNext()) {
			SysRight menu = menuIterator.next();

			// 找到这个menu的上级的上级的上级,一路往上找,加入到最终的menu列表中
			// if (menu.getMenulevel() != 1 || (menu.getParentid() != null && !menu.getParentid().equals(""))) {
			// 如果不是顶级的话,就找到父菜单
			// if (menu.getGrade() != 1) {
			// LOG.debug("此菜单的代码=" + menu.getRightcode() + ",现在设置的上级=" + parentcode + ",实际的上级=" + menu.getParentcode());
			if (!menu.getParentcode().equals(parentcode)) {
				parentcode = menu.getParentcode();

				List<SysRight> parentMenu = RightTree.getParentRights(menu.getRightcode());

				// LOG.debug("---\"" + menu.getRightname() + "\"的上级菜单列表:::" + parentMenu);
				// 这个地方有个问题,就是会多次获得相同的上级信息,找个办法将这个解决掉
				for (int i = 0; i < parentMenu.size(); i++) {
					SysRight sysMenu = parentMenu.get(i);
					// LOG.debug("------\"" + menu.getRightname() + "\"的上级菜单" + (i + 1) + "::" + sysMenu.getRightname() + ",顺序:" +
					// sysMenu.getOrderid()
					// + ",ISMENU=" + sysMenu.getIsmenu());
					if (sysMenu.getIsmenu()) // 如果这是一个菜单的话,就加入进来
						menus.add(sysMenu);
				}
			}
			else {
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

	// /**
	// * 根据权限得到所有的菜单 实际上只需要根据right得到他的上级就可以了 <br/> 修改1.将上级right的获得改为RightTree来处理,RightTree是系统初始化的时候就有了的数据,不必要再从数据库里面获取了
	// *
	// * @param rights
	// * @return
	// */
	// private List<SysRight> getUserMenus(Set<SysRight> rights) {
	// Set<SysRight> menus = new HashSet<SysRight>();// 返回的menus
	// // 然后找到这个menu的上级是不是0或者层级是否1，如果不是，则往上翻，将这个menu对应的上级menu也翻出来
	// Iterator<SysRight> menuIterator = rights.iterator();
	// String parentcode = ""; // 如果2个的parentcode相同,就不在比较了,因为父级已经找到了
	// while (menuIterator.hasNext()) {
	// SysRight menu = menuIterator.next();
	// LOG.debug("此菜单的代码:::" + menu.getRightcode());
	// // 找到这个menu的上级的上级的上级,一路往上找,加入到最终的menu列表中
	// // if (menu.getMenulevel() != 1 || (menu.getParentid() != null && !menu.getParentid().equals(""))) {
	// // 如果不是顶级的话,就找到父菜单
	// // if (menu.getGrade() != 1) {
	// LOG.debug("现在设置的上级=" + parentcode + ",实际的上级=" + menu.getParentcode());
	// if (!menu.getParentcode().equals(parentcode)) {
	// parentcode = menu.getParentcode();
	// // List parentMenu = node.getParentList(menu.getRightcode());
	// List<SysRight> parentMenu = RightTree.getParentRights(menu.getRightcode());
	// LOG.debug("---\"" + menu.getRightname() + "\"的上级菜单列表:::" + parentMenu);
	// // 这个地方有个问题,就是会多次获得相同的上级信息,找个办法将这个解决掉
	// for (int i = 0; i < parentMenu.size(); i++) {
	// // String menuid = parentMenu.get(i).toString();
	// // if (!menuid.equals(Constants.ROOT_RIGHT)) {// 忽略掉最顶级的parentid=-1
	// // SysRight sysMenu = menuMap.get(parentMenu.get(i));
	// SysRight sysMenu = parentMenu.get(i);
	// LOG.debug("------\"" + menu.getRightname() + "\"的上级菜单" + (i + 1) + "::" + sysMenu.getRightname() + ",顺序:" + sysMenu.getOrderid()
	// + ",ISMENU=" + sysMenu.getIsmenu());
	// if (sysMenu.getIsmenu()) // 如果这是一个菜单的话,就加入进来
	// // menus.add(sysMenu);
	//						
	// menus.add(RightTree.rightMap.get(menu.getRightcode()));
	// // }
	// }
	// }
	// else {
	// LOG.debug("具有同样的上级菜单,不再做出处理");
	// }
	// // }
	// LOG.debug("将自己加入到菜单列表中::" + menu.getRightname());
	// if (menu.getIsmenu())
	// menus.add(menu);// 将自己加入到菜单列表中
	// }
	// LOG.debug("menus.size()::::::::::" + menus.size());
	// List<SysRight> list = new ArrayList<SysRight>();
	// list.addAll(menus);
	// // 将菜单排序
	// Collections.sort(list, new RightComparator());
	// // for (int i = 0; i < list.size(); i++) {
	// // SysMenu menu = (SysMenu) list.get(i);
	// // LOG.debug("这里的菜单:::" + menu.getMenuname() + "==" + menu.getOrderid());
	// // }
	// return list;
	// }

	// 将right对应的menuid都找出来,menuid必须是树状形式的队列吧.根据权限信息得到此人的menu信息

	public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo)
			throws ServiceException {
		try {
			return sysUserDAO.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}

	/**
	 * 得到管理员的菜单和所有权限列表
	 * 
	 * @param rightList
	 * @param menus
	 */
	private void getAdminRightsMenus(List<SysRight> rightList, List<SysRight> menus) {

		// List<SysRight> rightMenus = new ArrayList<SysRight>();
		// List<SysRight> _rightList= new ArrayList<SysRight>();
		List list = RightTree.getRightList();
		for (int i = 0; i < list.size(); i++) {
			SysRight right = (SysRight) list.get(i);
			if (right.getIsmenu())
				menus.add(right);
			rightList.add(right);
		}
		// menus.addAll(rightMenus);
		// 将菜单排序
		// Collections.sort(menus, new RightComparator());
	}

	/**
	 * 得到userid和loginname的对应关系表
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public Map getAllUsersMap() throws ServiceException {
		Map map = new HashMap();
		List list = sysUserDAO.findAll(SysUser.class);
		for (int i = 0; list != null && i < list.size(); i++) {
			SysUser user = (SysUser) list.get(i);
			map.put(user.getUserid(), user.getLoginname());
		}
		return map;
	}

	/**
	 * 多对多中间表数据的删除
	 * 
	 * @param userid
	 * @return
	 * @throws ServiceException
	 */
	private int deleteRoles(final int userid) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {

					SysUser sysUser = (SysUser) sysUserDAO.get(SysUser.class, userid);
					Set<SysRole> roles = sysUser.getSysRoles();
					Iterator<SysRole> i = roles.iterator();
					while (i.hasNext()) {
						SysRole role = i.next();
						role.getSysUsers().remove(sysUser);
						i.remove();
					}
					return new Integer(0);
				}
			});
			return ((Integer) object).intValue();
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}

	}

	public int assignRolesNotransaction(final SysUser sysUser, final List roleids) {

		int len = roleids.size();
		// SysUser sysUser = (SysUser) sysUserDAO.get(SysUser.class, userid);
		// 先删除掉所有的角色
		Set<SysRole> roles = sysUser.getSysRoles();
		Iterator<SysRole> iterator = roles.iterator();
		while (iterator.hasNext()) {
			SysRole role = iterator.next();
			role.getSysUsers().remove(sysUser);
			iterator.remove();
		}
		LOG.debug("删除掉所有的角色......");
		for (int i = 0; i < len; i++) {
			SysRole role = (SysRole) sysUserDAO.get(SysRole.class, Short.parseShort(roleids.get(i).toString()));
			role.getSysUsers().add(sysUser);
			// sysUser.getSysRoles().add(role);
			roles.add(role);
		}
		return len;

	}

	/**
	 * 给userid新增roleids这些个角色
	 * 
	 * @param userid
	 * @param roleids
	 * @throws ServiceException
	 */
	public int assignRoles(final int userid, final List roleids) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					int len = roleids.size();
					SysUser sysUser = (SysUser) sysUserDAO.get(SysUser.class, userid);
					// 先删除掉所有的角色
					Set<SysRole> roles = sysUser.getSysRoles();
					Iterator<SysRole> iterator = roles.iterator();
					while (iterator.hasNext()) {
						SysRole role = iterator.next();
						role.getSysUsers().remove(sysUser);
						iterator.remove();
					}
					LOG.debug("删除掉所有的角色......");
					for (int i = 0; i < len; i++) {
						SysRole role = (SysRole) sysUserDAO.get(SysRole.class, Short.parseShort(roleids.get(i).toString()));
						role.getSysUsers().add(sysUser);
						// sysUser.getSysRoles().add(role);
						roles.add(role);
					}
					return new Integer(len);
				}
			});
			return ((Integer) object).intValue();
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * * 给userid新增rightcods这些个功能
	 * 
	 * @param userid
	 * @param rightcodes
	 * @throws ServiceException
	 */
	public int assignRrights(final int userid, final List _rigths) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					int len = _rigths.size();
					SysUser sysUser = (SysUser) sysUserDAO.get(SysUser.class, userid);
					// 先删除掉所有的角色
					Set<SysRight> rights = sysUser.getSysRights();
					Iterator<SysRight> iterator = rights.iterator();
					while (iterator.hasNext()) {
						SysRight right = iterator.next();
						right.getSysUsers().remove(sysUser);
						iterator.remove();
					}
					LOG.debug("删除掉所有的角色......");
					for (int i = 0; i < len; i++) {
						SysRight right = (SysRight) sysRightDAO.get(SysRight.class, _rigths.get(i).toString());
						right.getSysUsers().add(sysUser);
						// sysUser.getSysRoles().add(role);
						rights.add(right);
					}
					return new Integer(len);
				}
			});
			return ((Integer) object).intValue();
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public SysUser getSysUserByLawerNo(String lawerno) throws ServiceException {
		try {
			return sysUserDAO.getSysUserByLawerNo(lawerno);

		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public SysUser getSysUserBySystemno(String systemno) throws ServiceException {
		try {
			return sysUserDAO.getSysUserBySystemno(systemno);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	public SysUser getSysUserByCardNo(String cardno) throws ServiceException {
		try {
			return sysUserDAO.getSysUserByCardNo(cardno);

		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public SysUser getSysUserByCertNo(String certno) throws ServiceException {
		try {
			return sysUserDAO.getSysUserByCertNo(certno);

		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public SysUser getSysUserByLoginname(String loginname) throws ServiceException {
		try {
			return sysUserDAO.getSysUserByLoginname(loginname);

		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	//编辑时判断用户名是否重复
	public SysUser getSysUser(String loginname,int userid) throws ServiceException {
		try {
			return sysUserDAO.getSysUser(loginname,userid);

		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}