
package com.uu800.admin.base.service;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.springframework.transaction.annotation.Transactional;

import com.uu800.webbase.BasicService;
import com.uu800.admin.base.dao.RoleDAO;
import com.uu800.admin.base.entity.TsysRole;

public class RoleService  extends BasicService 
{
    private RoleDAO roleDAO;
	/**
	 * @param RoleDAO
	 */
    @Resource
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
		super.basicDao = roleDAO;
	}
	/**
	 * 批量删除
	 */	
	@Transactional
	public int deletes(Object[] ids)
	{
		String hql = "delete from TsysRole where roleid in (:ids)";
		return this.roleDAO.deletes(hql,ids);
	}
	/**
	 * 编辑角色权限时的权限list
	 * @param menu
	 * @return
	 */
	public List getRoleRightList(final TsysRole role)
	{
		return this.roleDAO.getRoleRightList(role);
	}
	/**
	 * 编辑角色权限
	 */
    @Transactional
    public void updateRoleRights(final TsysRole role,final String[] check) throws ServiceException {
        try {
        	roleDAO.updateRoleRights(role,check);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
