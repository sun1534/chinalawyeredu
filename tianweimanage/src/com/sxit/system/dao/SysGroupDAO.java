/**
 * TSysGroupDAO.java
 */

package com.sxit.system.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.sxit.common.BasicDAO;

/**
 * 对部门分组的处理
 * 
 * @author 华锋 2008-2-22 下午02:26:00
 * 
 */
public class SysGroupDAO extends BasicDAO {

	public void deleteLogic(final int groupid) {
		execute("update com.sxit.models.system.SysGroup group set group.delflag=true where group.groupid=?", new Integer(groupid));
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				String hql="update com.sxit.models.system.SysGroup group set group.delflag=true where group.groupid=?";
				Query queryObject = session.createQuery(hql);
				queryObject.setInteger(0, groupid);
				int i = queryObject.executeUpdate();
				return new Integer(i);
			}
		});
		
//		SysGroup group=(SysGroup)this.get(SysGroup.class, groupid);
//		group.setDelflag(true);
//		this.update(group);
		
		
	
	}
	public List getChildGroup(int parentid) {
		
			List list = find("from com.sxit.models.system.SysGroup group where group.parentid=?", parentid);
			return list;
		
	}

}