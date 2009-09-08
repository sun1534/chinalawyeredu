/**
 * SysRoleService.java
 */

package com.changpeng.system.service;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.SysRight;
import com.changpeng.models.SysRole;
import com.changpeng.system.dao.SysRightDAO;
import com.changpeng.system.dao.SysRoleDAO;

/**
 * @author 华锋 2008-2-29 上午10:40:54
 * 
 */
public class SysRoleService {

	private SysRoleDAO sysRoleDAO;

	private SysRightDAO sysRightDAO;

	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setSysRoleDAO(SysRoleDAO sysRole) {
		this.sysRoleDAO = sysRole;
	}

	public void setSysRightDAO(SysRightDAO sysRightDAO) {
		this.sysRightDAO = sysRightDAO;
	}

	/**
	 * 这里要注意<br/> 1、现在的删除，可能是逻辑删除，如果设置loginname唯一的话，看怎么处理 2、新增删除和更新了后，怎么保留修改的痕迹
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	public void addRole(SysRole role) throws ServiceException {
		try {
			role.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));

			sysRoleDAO.save(role);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @param userid
	 * @return
	 * @throws ServiceException
	 */
	public SysRole getRole(int userid) throws ServiceException {
		try {
			SysRole role = (SysRole) sysRoleDAO.get(SysRole.class, userid);
			return role;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	public void updateRole(SysRole role) throws ServiceException {
		try {
			sysRoleDAO.update(role);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @param userid
	 * @throws ServiceException
	 */
	@Transactional
	public int deleteRole(int roleid) throws ServiceException {
		try {
			SysRole role = (SysRole) sysRoleDAO.get(SysRole.class, roleid);

			if (role.getSysUsers() != null && role.getSysUsers().size() != 0)
				return -1;

			sysRoleDAO.delete(role);
			return 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public List getRoles() throws ServiceException {
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(SysRole.class);
			return sysRoleDAO.findAllByCriteria(dc);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 角色分配权限
	 * 
	 * @param userid
	 * @param _rigths
	 * @return
	 * @throws ServiceException
	 */
	public int assignRrights(final int roleid, final List _rigths) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					int len = _rigths.size();
					SysRole sysRole = (SysRole) sysRoleDAO.get(SysRole.class, roleid);
					// 先删除掉所有的角色
					Set<SysRight> rights = sysRole.getSysRights();

					rights.clear();

					for (int i = 0; i < len; i++) {

						SysRight right = (SysRight) sysRightDAO.get(SysRight.class, _rigths.get(i).toString());
						right.getSysRoles().add(sysRole);
						// sysUser.getSysRoles().add(role);
						rights.add(right);
					}
					return new Integer(len);
				}
			});
			return ((Integer) object).intValue();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// 根据roleid得到所有的right
}