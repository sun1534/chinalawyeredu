package com.uu800.admin.base.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.uu800.webbase.BasicDao;
import com.uu800.admin.base.entity.Menu;
import com.uu800.admin.base.entity.TsysMenu;
import com.uu800.admin.base.entity.User;

/**
 * @author zrb 
 * 
 */
@Repository
public class LoginDAO extends BasicDao {
	
    public List findByQuery(final String query, final String[] parameter) {
        return getHibernateTemplate().find(query, parameter);
    }
    
	/**
	 * 功能：取SYS菜单集合函数 *
	 * 
	 * @return String
	 */	
	public List getMenuList(final User user) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				try {
					long userid = user.getUserid();
					List<TsysMenu> list = null;
					Query query;
					String sql;
					// 如果用户是超级管理员则拥有全部权限
					if (user.getUsertype() == 0) 
					{
						sql = "select t1.MenuId,t1.ParentID,t1.MenuName,t1.OrderID,t1.ImageURL,t1.LinkUrl,t1.Comments,t1.MenuType,t1.CreateTime,t1.OpenTarget,t1.MenuLevel,t1.iscomponent"
								+ " from tsys_Menu t1"
								+ " inner join tsys_Menu t2 on t1.ParentID = t2.MenuID " 
								+ " Where t1.MenuLevel=2 and t1.MenuType='SYS'"
								+ " Order By t2.orderid,t1.orderid";
					} 
					else 
					{
						sql = "select  MenuId,ParentID, MenuName,  OrderID, ImageURL, "
								+ "LinkUrl, Comments, MenuType, CreateTime, OpenTarget, "
								+ "MenuLevel,iscomponent from v_usermenu " + "Where userid ="
								+ userid + " Order by porder,OrderID";
					}
					query = session.createSQLQuery(sql).addEntity(TsysMenu.class);

					
					list = query.list();

		            
		            List<Menu> menulist = new ArrayList();

					String parentmenuid = "";
					
					Menu t1_menu = new Menu();
					
					for (TsysMenu menu : list) {
						if (!(parentmenuid.equals(menu.getParentMenu().getMenuid()))
								|| "".equals(parentmenuid)) {
							t1_menu = new Menu(menu.getParentMenu());
							menulist.add(t1_menu);
						}
						t1_menu.getChildMenus().add(new Menu(menu));
						parentmenuid = menu.getParentMenu().getMenuid();
					}
					return menulist;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
		return (List)object;
	}  
}
