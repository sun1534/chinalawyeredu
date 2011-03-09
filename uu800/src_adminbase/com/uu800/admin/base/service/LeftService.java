package com.uu800.admin.base.service;

import java.util.List;

import javax.annotation.Resource;

import com.uu800.webbase.BasicService;
import com.uu800.admin.base.entity.User;
import com.uu800.admin.base.dao.LeftDAO;

public class LeftService  extends BasicService 
{
	private LeftDAO leftDAO;
	
	/**
	 * @param loginDAO
	 */
	 @Resource
	public void setLeftDAO(LeftDAO leftDAO) {
		this.leftDAO = leftDAO;
	}
	/**
	 * 功能：取菜单集合函数 *
	 * 
	 * @return String
	 */	
	public List getMenuList(User user) {
	   return	leftDAO.getMenuList(user);
	}
}
