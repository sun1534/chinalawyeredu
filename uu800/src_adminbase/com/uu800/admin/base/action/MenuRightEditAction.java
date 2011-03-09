package com.uu800.admin.base.action;

import java.util.List;
import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.MenuService;
import com.uu800.admin.base.entity.TsysLogs;

/**
 * 
 * 功能： 编辑菜单管理
 * 作者： zrb
 * 公司： 深圳信科
 * 日期： 2009-05-22
 * @版本： V1.0
 * @修改：
 */

public class MenuRightEditAction extends AbstractAdminAction{

	private String menuid;
	private String[] check;
	private TsysMenu menu;
	private List<Right>  rightlist;
	private MenuService service;
	
	public MenuRightEditAction() {
		service = (MenuService)this.getBean("menuService");
	}

	@Override
	public String execute()throws Exception {
		
		
		
		menu=(TsysMenu)service.get(TsysMenu.class,menuid);	
		
//		log=new TsysLogs("编辑菜单权限:菜单ID为"+menuid+" 菜单名称为"+menu.getMenuname(),4,true);
		
		service.updateMenuRights(menu, check);
		
		nextPage = "menuList.action?menutype="+menu.getMenutype();
		message = "保存成功！";
		return SUCCESS;
	}

	public TsysMenu getMenu() {
		return menu;
	}

	@Override
	public String input() throws Exception {

		menu =(TsysMenu) service.get(TsysMenu.class,menuid);
        if(menu==null){
          message="未找到此菜单";
          return "sysmsg";
        }
        rightlist=service.getMenuRightList(menu);
       
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

	public List<Right> getRightlist() {
		return rightlist;
	}

	public void setCheck(String[] check) {
		this.check = check;
	}

}
