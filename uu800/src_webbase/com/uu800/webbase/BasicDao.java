/**
 * BasicDao.java
 */

package com.uu800.webbase;

import java.io.Serializable;
import java.lang.annotation.Inherited;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl.OrderEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.uu800.webbase.annotation.InheritedService;
import com.uu800.webbase.util.HibernateUtil;
import org.springframework.stereotype.Repository;



@InheritedService
public class BasicDao<T> extends HibernateDaoSupport {
	
	 @Resource(name="sessionFactory")  
	 public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {   
		 super.setSessionFactory(sessionFactory);  
	 }
	
	/**
	 * 保存对象 新增
	 * 
	 * @param entity
	 * 
	 */
	public void save(final T entity) {
		getHibernateTemplate().save(entity);
	}

	/**
	 * 新增或更新对象
	 * 
	 * @param entity
	 */
	public void saveOrupdate(final T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 更新对象
	 * 
	 * @param entity
	 */
	public void update(final T entity) {
		getHibernateTemplate().update(entity);
	}

	/**
	 * 删除对象
	 * 
	 * @param entity
	 */
	public void delete(final T entity) {
		getHibernateTemplate().delete(entity);
	}
    /**
     * 批量删除
     * String hql = "delete from TsysOrg where orgid in (:ids)";
	 *	return this.orgDAO.deletes(hql,ids);
     * @param hql
     * @param ids
     * @return int
     */
    public int deletes(final String hql,final Object[] ids) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {				
			    int deletedEntities = session.createQuery(hql)
		        .setParameterList("ids", ids)
		        .executeUpdate();
		        return deletedEntities;
			}
		});
		return ((Integer) object).intValue();
	}
	/**
	 * 根据ID获取对象
	 * 
	 * @param entity
	 * @param id
	 * @return 对象，或者未能发现符合条件的记录，返回null
	 */
	public T get(final Class<T> entity, final Serializable id) {
		return getHibernateTemplate().get(entity, id);
	}

	/**
	 * 返回所有对象的列表
	 * 
	 * @param entity
	 * @return
	 */
	public List findAll(final Class entity) {
		List l = null;
		l = getHibernateTemplate().find("from " + entity.getName());
		return l;
	}

	/**
	 * 
	 * @param query
	 * @return list
	 */
	public List findByQuery(final String query) {
		return getHibernateTemplate().find(query);
	}

	/**
	 * 
	 * @param query
	 * @param parameter
	 * @return list
	 */
	public List findByQuery(final String query, final Object parameter) {
		return getHibernateTemplate().find(query, parameter);
	}

	/**
	 * query="from user u where u.username=? and u.password=?" parameter
	 * {"tom","123456"}
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 */
	public List findByQuery(final String query, final Object[] parameter) {
		return getHibernateTemplate().find(query, parameter);
	}

	/**
	 * sql语句查询
	 * 
	 * @param sql
	 * @return
	 */
	@Deprecated
	public List findBySqlQuery(final String sql) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query queryObject = session.createSQLQuery(sql);
				return queryObject.list();
			}
		});
		return ((List) object);
	}

	/**
	 * sql语句分页查询
	 * 
	 * @param sql
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public List findBySqlQueryOnPage(final String sql, final int pageSize, final int pageNo) {
		int startIndex = pageSize * (pageNo - 1);
		if (pageNo == 0) {
			startIndex = 0;
		}
		final int startIndex1 = startIndex;
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query queryObject = session.createSQLQuery(sql);
				queryObject.setFirstResult(startIndex1);
				queryObject.setMaxResults(pageSize);

				return queryObject.list();
			}
		});
		return ((List) object);
	}

	/**
	 * 
	 * 查询指定数量的对象
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 */
	public List findNumList(final String query, final int resultcount) {
		return (List) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query q = session.createQuery(query);
				q.setMaxResults(resultcount);
				return q.list();
			}
		});
	}

	/**
	 * 返回符合条件的记录列表
	 * 
	 * @param detachedCriteria
	 * @return list
	 */
	public List findByCriteria(final DetachedCriteria detachedCriteria) {
		return (List) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				criteria.setProjection(null);
				criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				return criteria.list();
			}
		});
	}

	/**
	 * 搜索指定数量的数据
	 * 
	 * @param detachedCriteria
	 * @param count
	 * @return
	 */
	public List findByCriteria(final DetachedCriteria detachedCriteria, final int count) {
		return (List) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				criteria.setProjection(null);
				criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY).setMaxResults(count);
				return criteria.list();
			}
		});
	}

	/**
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public int execute(final String hql, final Object[] values) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query queryObject = session.createQuery(hql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				int i = queryObject.executeUpdate();
				return new Integer(i);
			}
		});
		return ((Integer) object).intValue();
	}
	
	/**
	 * 执行hql 更新,删除 操作
	 * 
	 * @param hql
	 * @return int
	 */
	public int execute(final String hql) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query queryObject = session.createQuery(hql);
				int i = queryObject.executeUpdate();
				return new Integer(i);
			}
		});
		return ((Integer) object).intValue();
	}

	/**
	 * 执行sql 更新,删除 操作
	 * 
	 * @param sql
	 * @return
	 */
	@Deprecated
	public int executeSql(final String sql) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query queryObject = session.createSQLQuery(sql);
				int i = queryObject.executeUpdate();
				return new Integer(i);
			}
		});
		return ((Integer) object).intValue();
	}

	/**
	 * 仅支持以 select count(*) from ... *
	 * 
	 * @param sql
	 * @return
	 * @author mickwlp
	 */
	public long findCountBySql(final String sql) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				List temp = session.createSQLQuery(sql).list();
				if (temp != null && temp.size() == 1) {
					java.math.BigInteger count = (java.math.BigInteger) temp.get(0);
					return (Long) count.longValue();
				} else {
					return new Long(0);
				}
			}
		});
		return ((Long) object).intValue();
	}
	
	/**
	 * hql
	 * @param hql
	 * @return
	 */
	public int findCountByQuery(final String hql) {
		
		
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query queryObject = session.createQuery(hql);
		      	Object obj=	queryObject.uniqueResult();
				return Integer.parseInt(obj.toString());
			}
		});
		return ((Integer) object).intValue();
	}

	/**
	 * 分页查询
	 * 
	 * @param detachedCriteria
	 * @param pageSize
	 * @param pageNo
	 *            从1开始的页数
	 * @return PaginationSupport
	 * @throws ServiceException
	 */
	public PageSupport findPageOnPageNo(final DetachedCriteria detachedCriteria, final int pageSize,
			final int pageNo) {

	
		return this.findPageByCriteria(detachedCriteria, pageSize, pageNo);

	}
	
	
	private PageSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo) {
		
		return (PageSupport) getHibernateTemplate().execute(new HibernateCallback() {
					public Object doInHibernate(Session session) {
				
				Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);
				OrderEntry[] orderEntries = HibernateUtil.getOrders(executableCriteria);
				executableCriteria = HibernateUtil.removeOrders(executableCriteria);
				Projection projection = HibernateUtil.getProjection(executableCriteria);

				int recordCount = ((Long) executableCriteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();

				executableCriteria.setProjection(projection);
				if (projection == null) {
						executableCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				}
				// Add the orginal orderEntries
				executableCriteria = HibernateUtil.addOrders(executableCriteria, orderEntries);
	
				int reallyPageNo=pageNo;
				int pageCount = recordCount-1 / pageSize+1; //总页数，如果当前的pageNo大于总页数,那就设置为总页数,否则总显示为空
				//pageCount=10页,pageNo=11,那实际上pageNo应该为
				if (reallyPageNo <= 0) {
					reallyPageNo = 0;
				}
				reallyPageNo = pageNo > pageCount ? pageCount : pageNo;
				int startIndex = pageSize * (pageNo - 1);
				List items = executableCriteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
				PageSupport ps = new PageSupport(items, recordCount, pageSize, reallyPageNo);
				return ps;
			}
		});
	}

	/**
	 * 分页查询
	 * 
	 * @param detachedCriteria
	 *            条件
	 * @param pageSize
	 *            返回记录的数量
	 * @param startIndex
	 *            第一条记录的序号
	 * @return PaginationSupport
	 */
//	private PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize,
//			final int startIndex) {
//		return (PaginationSupport) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
//			public Object doInHibernate(Session session) throws HibernateException {
//				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
//				int totalCount = ((int) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
//				criteria.setProjection(null);
//				criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
//				List items = criteria.setCacheable(true).setFirstResult(startIndex).setMaxResults(pageSize).list();
//
//				PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
//				return ps;
//			}
//		});
//	}

	/**
	 * 返回符合条件的记录数量
	 * 
	 * @param detachedCriteria
	 * @return int
	 */
	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		Integer count = (Integer) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			
				int i=((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
				return i;
			}
		});
		return count.intValue();
	}

	/**
	 * 
	 * @param sql
	 * @param c
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public List findBySqlEntity(final String sql, final Class c, final int pageSize, final int pageNo) {
		int startIndex = pageSize * (pageNo - 1);
		if (pageNo == 0) {
			startIndex = 0;
		}
		final int startIndex1 = startIndex;
		Object object = getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				SQLQuery queryObject = session.createSQLQuery(sql).addEntity(c);
				queryObject.setFirstResult(startIndex1);
				queryObject.setMaxResults(pageSize);
				return queryObject.list();
			}
		});
		return ((List) object);
	}
	
    /**
     * 分页查询
     * @param detachedCriteria 条件
     * @param page 页面信息  包含 maxperpage  pagenumber 等
     */

	public PageSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final PageSupport page) {
        return (PageSupport) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {

                Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                long recordsize = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();                
        		//请不要随便动这里的代码, 如果这里报错 请先检查 配置文件有没有包含hibernate的xml文件
                //计算记录条数
        		//criteria.setProjection(Projections.rowCount());
        		//int recordsize = (Integer) criteria.uniqueResult(); 
                
                criteria.setProjection(null);
                criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
                
                int startIndex=(page.getPageNo()-1)*page.getPageSize();
                List items = criteria.setCacheable(true).setFirstResult(startIndex).setMaxResults(page.getPageSize()).list();
//                PageSupport ps = new PageSupport(page,items,recordsize);  
                PageSupport ps = new PageSupport(items, (int)recordsize, page.getPageSize(), page.getPageNo());
                return ps;
                
            }
        });
    }
}
