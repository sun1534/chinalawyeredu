/**
 * AbstractManager.java
 */

package com.sxit.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl.OrderEntry;
import org.hibernate.transform.ResultTransformer;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sxit.common.util.HibernateUtils;

/**
 * @author 华锋 2009-1-5  下午04:57:11
 * 
 */
public class BasicDAO extends HibernateDaoSupport {

//	private boolean cacheQueries = false;

//	private String queryCacheRegion;

//	public void setCacheQueries(boolean cacheQueries) {
//		this.cacheQueries = cacheQueries;
//	}
//
//	public void setQueryCacheRegion(String queryCacheRegion) {
//
//		this.queryCacheRegion = queryCacheRegion;
//	}

	public void save(final Object entity) {
		getHibernateTemplate().save(entity);
	}
	
	
	public void saveOrupdate(final Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	// public void persist(final Object entity) {
	// getHibernateTemplate().save(entity);
	// }

	public void update(final Object entity) {
		getHibernateTemplate().update(entity);
	}

	public void delete(final Object entity) {
		getHibernateTemplate().delete(entity);
	}

	public void delete(final String table,final String keycolumn,final Object keyvalue){
		
		StringBuilder sb=new StringBuilder();
		sb.append("delete from ").append(table).append(" where ").append(keycolumn).append("='").append(keyvalue).append("'");
		
		this.executeSql(sb.toString());
		
	}
	
	public Object load(final Class entity, final Serializable id) {
		return getHibernateTemplate().load(entity, id);
	}

	public Object get(final Class entity, final Serializable id) {
		return getHibernateTemplate().get(entity, id);
	}

	public List findAll(final Class entity) {
		return getHibernateTemplate().find("from " + entity.getName());
	}

	// public List findByNamedQuery(final String namedQuery) {
	// return getHibernateTemplate().findByNamedQuery(namedQuery);
	// }
	//
	// public List findByNamedQuery(final String query, final Object parameter) {
	// return getHibernateTemplate().findByNamedQuery(query, parameter);
	// }
	//
	// public List findByNamedQuery(final String query, final Object[] parameters) {
	// return getHibernateTemplate().findByNamedQuery(query, parameters);
	// }

	public List find(final String query) {
		return getHibernateTemplate().find(query);
	}

	public List find(final String query, final Object parameter) {
		return getHibernateTemplate().find(query, parameter);
	}
	 /**
     * query="from user u where u.username=? and u.password=?"
     * parameter {"tom","123456"}
     * @param query
     * @param parameter
     * @return
     */
    public List find(final String query, final Object[] parameter) {
        return getHibernateTemplate().find(query, parameter);
    }
	/**
	 * 执行一个hql语句
	 * 
	 * @param hql
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
	 * 
	 * @param sql
	 * @return
	 */
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

	public int execute(final String hql, final Object value) {
		return execute(hql, new Object[] { value });
	}

	public List findBySqlQuery(final String sql) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query queryObject = session.createSQLQuery(sql);
				return queryObject.list();
			}
		});
		return ((List) object);
	}

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

	public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria) {
		return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE, 0);
	}

	public PaginationSupport findPageByCriteriaDistinct(final DetachedCriteria detachedCriteria) {
		return findPageByCriteriaDistinct(detachedCriteria, PaginationSupport.PAGESIZE, 0);
	}

	public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int startIndex) {
		return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE, startIndex);
	}

	public PaginationSupport findPageByCriteriaDistinct(final DetachedCriteria detachedCriteria, final int startIndex) {
		return findPageByCriteriaDistinct(detachedCriteria, PaginationSupport.PAGESIZE, startIndex);
	}

	// public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int startIndex) {
	// return (PaginationSupport) getHibernateTemplate().execute(new HibernateCallback() {
	// public Object doInHibernate(Session session) throws HibernateException {
	//				
	// Criteria criteria = detachedCriteria.getExecutableCriteria(session);
	//				
	// int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	//				
	// criteria.setProjection(null);
	//				
	// List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
	//				
	// PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
	//				
	// return ps;
	// }
	// }, true);
	// }

	/**
	 * 
	 * @param detachedCriteria
	 * @param pageSize
	 *            查询的最大大小
	 * @param startIndex
	 *            查询的起始位置
	 * @return
	 */
	public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo) {
		return (PaginationSupport) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);

				// Get the orginal orderEntries
				OrderEntry[] orderEntries = HibernateUtils.getOrders(executableCriteria);
				// Remove the orders
				executableCriteria = HibernateUtils.removeOrders(executableCriteria);
				// get the original projection
				Projection projection = HibernateUtils.getProjection(executableCriteria);

				int totalCount = ((Integer) executableCriteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
				int temp = pageSize;
				if(temp > totalCount && totalCount != 0)
				{
					temp = totalCount;
				}
				executableCriteria.setProjection(projection);
				if (projection == null) {
					if (setTransformer) {
						executableCriteria.setResultTransformer(resultTransformer);
						setTransformer=false; //重新设置为false
					}
					else {
						executableCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
					}
				}
				// Add the orginal orderEntries
				executableCriteria = HibernateUtils.addOrders(executableCriteria, orderEntries);

				// Now, the Projection and the orderEntries have been resumed
				// List items = HibernateUtils.getPageResult(executableCriteria, firstResult, maxResults);
				int startIndex = (pageNo - 1) * temp;
				List items = executableCriteria.setFirstResult(startIndex).setMaxResults(temp).list();
				PaginationSupport ps = new PaginationSupport(items, totalCount, temp, startIndex);
				return ps;
			}
		}, true);
	}

	public PaginationSupport findPageByCriteriaDistinct(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo) {
		return (PaginationSupport) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);

				// Get the orginal orderEntries
				OrderEntry[] orderEntries = HibernateUtils.getOrders(executableCriteria);
				// Remove the orders
				executableCriteria = HibernateUtils.removeOrders(executableCriteria);
				// get the original projection
				Projection projection = HibernateUtils.getProjection(executableCriteria);

				int totalCount = ((Integer) executableCriteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();

				executableCriteria.setProjection(projection);
				if (projection == null) {
					executableCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
				}

				// Add the orginal orderEntries
				executableCriteria = HibernateUtils.addOrders(executableCriteria, orderEntries);

				// Now, the Projection and the orderEntries have been resumed
				// List items = HibernateUtils.getPageResult(executableCriteria, firstResult, maxResults);
				int startIndex = (pageNo - 1) * pageSize;
				List items = executableCriteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
				PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
				return ps;
			}
		}, true);
	}

	public List findAllByCriteria(final DetachedCriteria detachedCriteria) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return criteria.list();
			}
		}, true);
	}

	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		Integer count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				return criteria.setProjection(Projections.rowCount()).uniqueResult();
			}
		}, true);
		return count.intValue();
	}

	/**
	 * 默认的resultTransformer为ROOT_ENTITY，这里设置为可以设置的。
	 */
	private boolean setTransformer;
	private ResultTransformer resultTransformer;

	public void setCriteriaSpecification(ResultTransformer resultTransformer) {
		setTransformer = true;
		this.resultTransformer = resultTransformer;
	}
}
