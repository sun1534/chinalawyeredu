
package com.uu800.admin.base.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.uu800.webbase.BasicDao;

/**
 * @author zrb  
 * auto make 
 */
public class OrgDAO extends BasicDao {

/*
 * 回调处理方法
*/	
	public List getXXXList(final String xxx) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				
                //此处添加需要处理的代码

                return null;
			}	
		}
	    );
	  return (List)object;
	}

	/**
	 * 取组织结构List (orgid,parentid,orgname)
	 * @return List
	 */
	public List getOrgList() {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				
				String sql="Select  orgid ,parentid,ORGNAME " +
				"From tsys_org Start With parentid = 0 Connect By Prior orgid= parentid";
				Query query = session.createSQLQuery(sql);
				List list= query.list();
				return list;
			}	
		}
	    );
	  return (List)object;
	}
	
}
