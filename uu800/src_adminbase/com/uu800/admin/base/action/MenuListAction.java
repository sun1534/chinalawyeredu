package com.uu800.admin.base.action;

import java.util.List;

import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.Menu;
import com.uu800.admin.base.service.MenuService;

/**
 * 
 * <p>
 * 功能： 列表菜单管理
 * </p>
 * <p>
 * 作者： zrb
 * </p>
 * <p>
 * 公司： 深圳信科
 * </p>
 * <p>
 * 日期： 2009-05-22
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class MenuListAction extends AbstractAdminAction {
	private List<Menu> menulist;
	private String menutype;
	private MenuService service;

	public MenuListAction() {
		service = (MenuService) this.getBean("menuService");
	}

	@Override
	public String execute() {

		System.out.println("this.getUserinfo().getName()====>"+this.getUserinfo().getName());
		
		if (menutype == null) {
			menutype = "SYS";
		}
		menulist = service.getMenuList(menutype);

		return SUCCESS;
	}

	public List<Menu> getMenulist() {
		return menulist;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

}
