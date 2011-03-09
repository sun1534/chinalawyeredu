
package com.uu800.admin.base.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.uu800.webbase.BasicDao;
import com.uu800.admin.base.entity.Menu;
import com.uu800.admin.base.entity.Right;
import com.uu800.admin.base.entity.TsysMenu;
import com.uu800.admin.base.entity.TsysRight;

/**
 * @author zrb  
 * auto make 
 */
public class MenuDAO extends BasicDao {
	
	/**
	 * 功能：取菜单列表 不分页
	 * 
	 * @return String
	 */	
	public List getMenuList(final String menutype) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				String sql="from TsysMenu as menu where menulevel>1 and iscomponent=0  and menutype=?  order by menu.parentMenu.orderid,menu.orderid";
				Query query = session.createQuery(sql);
				query.setParameter(0, menutype);
				List<TsysMenu>  list = query.list();
	        	
				List menulist = new ArrayList();
				
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
			}	
		}
	    );
	  return (List)object;
	}
	/**
	 * 编辑菜单权限时的权限list
	 * @return
	 */
	public List getMenuRightList(final TsysMenu menu) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				String sql="from TsysRight where grade>0 and moduletype='"+menu.getMenutype()+"' order by rightid";
				
				

				Query query = session.createQuery(sql);
				
				Set<String> rightset = new HashSet();
				Set<TsysRight> rights = menu.getTsysRights();
				for(TsysRight right:rights)
				{
					rightset.add(right.getRightid());
				}
				List<TsysRight>  list = query.list();
				
				List rightlist = new ArrayList();
				
				for(TsysRight right:list)
				{ 
					Right temp = new Right(right);
					
					if(rightset.contains(temp.getRightid()))
						temp.setChecked(true);
					else
						temp.setChecked(false);			
					rightlist.add(temp);
				}
                return rightlist;
			}	
		}
	    );
	  return (List)object;
	}
	
	public boolean updateMenuRights(final TsysMenu menu,final String[] check) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				
				Set<TsysRight> rights= new HashSet();
				if(check!=null)
				{
					for(String rightid:check)
					{
						TsysRight right=(TsysRight)session.get(TsysRight.class,rightid);
						rights.add(right);			
					}
				}
				
				//取交集 
				menu.getTsysRights().retainAll(rights);
				//新的集合中取补集 
				rights.removeAll(menu.getTsysRights());
				//最终的集合 
				menu.getTsysRights().addAll(rights);
				
				session.update(menu);

                return true;
			}	
		}
	    );
	  return true;
	}
}
