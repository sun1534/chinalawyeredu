
package com.uu800.admin.base.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.uu800.webbase.BasicDao;
import com.uu800.admin.base.entity.TsysOrg;
import com.uu800.admin.base.entity.TsysRole;
import com.uu800.admin.base.entity.TsysUser;

/**
 * @author zrb  
 * auto make 
 */
public class UserDAO extends BasicDao {
	
	/**
	 * 取组织结构MAP (orgid,orgname)
	 * @return LinkedHashMap
	 */
	public LinkedHashMap getOrgList() {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				
				String sql="Select  orgid ,lpad('├',2*(Level),'　')||' '|| orgshortname " +
				"From tsys_org Start With parentid = 0 Connect By Prior orgid= parentid";
				Query query = session.createSQLQuery(sql);
				List list= query.list();
				
				LinkedHashMap map = new LinkedHashMap();
				
				for(Object obj:list)
				{	
					 Object[] str=(Object[]) obj;
					 java.math.BigDecimal bd =(java.math.BigDecimal)str[0];
					 int orgid = bd.intValue();
					 String orgname =(String) str[1];
					 map.put(orgid,orgname);
				}			
				return map;
			}	
		}
	    );
	  return (LinkedHashMap)object;
	}
	/**
	 * 取组织结构List (orgid,parentid,orgname)
	 * @return LinkedHashMap
	 */
	public List getOrgTreeList() {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				
				String sql="Select ORGID,PARENTID,PATHCODE,ORGTYPE,GRADE,ORGCODE,ORGSHORTNAME,ORGENNAME,ORGNAME," +
					" CONTACTPERSON,CONTACTPHONE,MOBILE,FAXNUM,COMMENTS,CREATEUSER,CREATETIME,MODIFYUSER,MODIFYTIME"+
				    " From tsys_org Start With parentid = 0 Connect By Prior orgid= parentid";
				Query query = session.createSQLQuery(sql).addEntity(TsysOrg.class);
				List list= query.list();
				return list;
			}
		}
	    );
	  return (List)object;
	}
	
	/**
	 * 编辑用户角色时的list
	 * @return
	 */
	
	public List getUserRoleList(final TsysUser user) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				
				String sql="from TsysRole where roletype=0 and rightobj=0 order by roleid";
				Query query = session.createQuery(sql);
				
				Set<Long> roleset = new HashSet();
				Set<TsysRole>roles = user.getTsysRoles();
				for(TsysRole role:roles)
				{
					roleset.add(role.getRoleid());
				}
			
				List<TsysRole>  list = query.list();
				
				List rolelist = new ArrayList();
				
				for(TsysRole role:list)
				{ 
					if(roleset.contains(role.getRoleid()))
						role.setChecked(true);
					else
						role.setChecked(false);
					
					rolelist.add(role);
				}

                return rolelist;
			}	
		}
	    );
	return (List)object;
	}
	
	/**
	 * 更新用户角色
	 * @param role
	 * @param check
	 * @return
	 */
	public boolean updateUserRoles(final TsysUser user,final Long[] check) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Set<TsysRole> roles= new HashSet();
				if(check!=null)
				{
					for(Long roleid:check)
					{
						TsysRole role=(TsysRole)session.get(TsysRole.class,roleid);
						roles.add(role);			
					}
				}
				
				//取交集 
				user.getTsysRoles().retainAll(roles);
				//新的集合中取补集 
				roles.removeAll(user.getTsysRoles());
				//最终的集合 
				user.getTsysRoles().addAll(roles);
				
				//session.update(user);	
                return true;
			}	
		}
	    );
	  return true;
	}


}
