package com.uu800.admin.base.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import com.opensymphony.xwork2.Preparable;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.MenuService;
import com.uu800.admin.base.entity.TsysLogs;

/**
 * 
 * 功能： 编辑菜单管理 作者： zrb 公司： 深圳信科 日期： 2009-05-22
 * 
 * @版本： V1.0
 * @修改：
 */

public class MenuEditAction extends AbstractAdminAction implements Preparable {
	private static Log LOG = LogFactory.getLog(MenuEditAction.class);
	private String menuid;
	private TsysMenu menu;
	private MenuService service;

	public MenuEditAction() {
		service = (MenuService) this.getBean("menuService");
	}

	@Override
	public String execute() {

		// log=new TsysLogs("编辑菜单:菜单ID为"+menuid+"
		// 菜单名称为"+menu.getMenuname(),4,true);

		service.update(menu);

		nextPage = "menuList.action?menutype=" + menu.getMenutype();
		message = "保存成功！";
		return SUCCESS;
	}

	public TsysMenu getMenu() {
		return menu;
	}

	public void prepare() throws Exception {

		
		menu = (TsysMenu) service.get(TsysMenu.class, menuid);
		
		System.out.println("prepare============"+menu);
	}

	@Override
	public String input() throws Exception {
		System.out.println("prepare============input"+menu);
		if (menu == null) {
			message = "未找到此菜单";
			return "sysmsg";
		}
		return "input";
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public void setMenu(TsysMenu menu) {
		this.menu = menu;
	}

}
