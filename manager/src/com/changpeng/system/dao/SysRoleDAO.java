/**
 * TSysGroupDAO.java
 */
package com.changpeng.system.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.changpeng.common.BasicDAO;

/**
 * @author 华锋
 * 2008-2-22 下午02:26:00
 *
 */
public class SysRoleDAO extends BasicDAO {

	
	/**
	 * 将数据从系统中彻底删除
	 * 
	 * @param userids
	 * @return
	 */
	public int deleteRoles(final short[] roleids) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				String hqlDelete = "delete from com.changpeng.models.SysRole role where role.roleid in (:roleids)";
				Query queryObject = session.createQuery(hqlDelete);
				Short[] params = new Short[roleids.length];
				for (int i = 0; i < roleids.length; i++) {
					params[i] = new Short(roleids[i]);
				}
				queryObject.setParameterList("roleids", params);
				int i = queryObject.executeUpdate();
				return new Integer(i);
			}
		});
		return ((Integer) object).intValue();
	}
}
