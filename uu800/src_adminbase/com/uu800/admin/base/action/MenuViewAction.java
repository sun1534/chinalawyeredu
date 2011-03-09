package com.uu800.admin.base.action;

import org.hibernate.HibernateException;

import com.uu800.admin.base.AbstractAdminAction;
import com.uu800.admin.base.entity.*;
import com.uu800.admin.base.service.MenuService;
import com.uu800.admin.base.entity.TsysLogs;


/**
 *
 * <p>功能： 查看菜单管理</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2009-05-22</p>
 * @版本： V1.0
 * @修改：
 */

public class MenuViewAction extends AbstractAdminAction {
	private TsysMenu menu;
    private String menuid;
    private MenuService service;
	public MenuViewAction() {
       service = (MenuService)this.getBean("menuService");
	   menu = new TsysMenu();
	}

	@Override
	public String execute() {
           nextPage="menuList.action?pageNo="+pageNo;
           menu=(TsysMenu)service.get(TsysMenu.class,menuid);
           if(menuid==null){
             message="未找到此菜单管理";
//             log=new TsysLogs("查看菜单:菜单ID为 "+menuid,1,false);
             return "sysmsg";
           }    
//           log=new TsysLogs("查看菜单:菜单ID为 "+menuid+" 职位名称为 "+menu.getMenuname(),1,true);
           return SUCCESS;
	}
	public TsysMenu getMenu() {
		return menu;
	}
        public void setMenuid(String menuid) {

          this.menuid = menuid;
        }
        public String getMenuid() {
          return this.menuid;
        }

}
