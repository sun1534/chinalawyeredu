
package com.uu800.admin.base.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.uu800.webbase.BasicDao;
import com.uu800.admin.base.entity.Right;
import com.uu800.admin.base.entity.TsysRight;
import com.uu800.admin.base.entity.TsysRole;
/**
 * @author zrb  
 * auto make 
 */
public class RoleDAO extends BasicDao {

	/**
	 * 编辑角色权限时的权限list
	 * @return
	 */
	public List getRoleRightList(final TsysRole role) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				
				String sql = "from TsysRight where grade>0 and moduletype='"+role.getModuletype()+"' order by rightid";
				Query query = session.createQuery(sql);

				Set<String> rightset = new HashSet();
				Set<TsysRight> rights = role.getTsysRights();
				for (TsysRight right : rights) {
					rightset.add(right.getRightid());
				}
				List<TsysRight> list = query.list();

				List rightlist = new ArrayList();

				for (TsysRight right : list) {
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
	/**
	 * 更新角色权限
	 * @param role
	 * @param check
	 * @return
	 */
	public boolean updateRoleRights(final TsysRole role,final String[] check) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				
				Set<TsysRight> rights = new HashSet();
				if (check != null) {
					for (String rightid : check) {
						TsysRight right = (TsysRight) session.get(TsysRight.class,
								rightid);
						rights.add(right);
					}
				}

				// 取交集
				role.getTsysRights().retainAll(rights);
				// 新的集合中取补集
				rights.removeAll(role.getTsysRights());
				// 最终的集合
				role.getTsysRights().addAll(rights);

				session.update(role);

                return true;
			}	
		}
	    );
	  return true;
	}
}
