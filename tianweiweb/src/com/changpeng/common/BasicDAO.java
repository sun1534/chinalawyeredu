/**
 * BasicDAO.java
 */

package com.changpeng.common;

import java.io.Serializable;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.changpeng.common.exception.ServiceException;


/**
 * @author mfq 
 * 
 */
public class BasicDAO extends HibernateDaoSupport {

    /**
     * 保存对象 新增
     * @param entity
     * 
     */
	public void save(final Object entity) {
		getHibernateTemplate().save(entity);
	}
	
	/**
	 * 新增或更新对象
	 * @param entity
	 */
	public void saveOrupdate(final Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
    /**
     * 更新对象
     * @param entity
     */
	public void update(final Object entity) {
		getHibernateTemplate().update(entity);
	}

    /**
     * 删除对象
     * @param entity
     */
	public void delete(final Object entity) {
		getHibernateTemplate().delete(entity);
	}

     /**
     * 根据ID获取对象
     * @param entity
     * @param id
     * @return 对象，或者未能发现符合条件的记录，返回null
     */
	public Object get(final Class entity, final Serializable id) {
		return getHibernateTemplate().get(entity, id);
	}

    /**
     * 返回所有对象的列表
     * @param entity
     * @return
     */
	public List findAll(final Class entity) {
         List l=null;
         l=getHibernateTemplate().find("from " + entity.getName());
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
     * query="from user u where u.username=? and u.password=?"
     * parameter {"tom","123456"}
     * @param query
     * @param parameter
     * @return
     */
    public List findByQuery(final String query, final Object[] parameter) {
        return getHibernateTemplate().find(query, parameter);
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
    
    /**
     * 
     * 查询指定数量的对象
     * @param query
     * @param parameter
     * @return
     */
    public List findNumList(final String query,final int resultcount) {
    	return (List) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query q=session.createQuery(query);
                q.setMaxResults(resultcount);
                return q.list();
            }
        });
    }

    /**
     * 返回符合条件的记录列表
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
     * @param detachedCriteria
     * @param count
     * @return
     */
    public List findByCriteria(final DetachedCriteria detachedCriteria,final int count) {
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
     * 执行hql 更新,删除 操作
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

	/**
     * 分页查询
     * @param detachedCriteria
     * @param pageSize
     * @param pageNo 从1开始的页数
     * @return PaginationSupport
     * @throws ServiceException
     */
    public PaginationSupport findPageOnPageNo(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo) throws ServiceException {
        try {
        	int startIndex=pageSize*(pageNo-1);
        	if(pageNo==0){
        		 startIndex=0;
        	}
            return this.findPageByCriteria(detachedCriteria, pageSize, startIndex);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    /**
     * 分页查询
     * @param detachedCriteria 条件
     * @param pageSize 返回记录的数量
     * @param startIndex 第一条记录的序号
     * @return PaginationSupport
     */
    private PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize,
            final int startIndex) {
        return (PaginationSupport) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
                criteria.setProjection(null);
                criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
                List items = criteria.setCacheable(true).setFirstResult(startIndex).setMaxResults(pageSize).list();
                PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
                return ps;
            }
        });
    }

    /**
     * 返回符合条件的记录数量
     * @param detachedCriteria
     * @return int
     */
    public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
        Integer count = (Integer) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                return criteria.setProjection(Projections.rowCount()).uniqueResult();
            }
        });
        return count.intValue();
    }
}
