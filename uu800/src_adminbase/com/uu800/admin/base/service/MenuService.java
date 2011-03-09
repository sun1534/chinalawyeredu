
package com.uu800.admin.base.service;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.springframework.transaction.annotation.Transactional;

import com.uu800.webbase.BasicService;
import com.uu800.admin.base.dao.MenuDAO;
import com.uu800.admin.base.entity.TsysMenu;

public class MenuService  extends BasicService 
{
    private MenuDAO menuDAO;
	/**
	 * @param MenuDAO
	 */
    @Resource
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
		super.basicDao = menuDAO;
	}
	
	/**
	 * 取菜单List
	 * @return
	 */
	public List getMenuList(String menutype)
	{
		return this.menuDAO.getMenuList(menutype);
	}
	
	/**
	 * 编辑菜单权限时的权限list
	 * @param menu
	 * @return
	 */
	public List getMenuRightList(TsysMenu menu)
	{
		return this.menuDAO.getMenuRightList(menu);
	}
	/**
	 * 编辑菜单权限
	 */
    @Transactional
    public void updateMenuRights(final TsysMenu menu,final String[] check) throws ServiceException {
        try {
        	menuDAO.updateMenuRights(menu,check);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
