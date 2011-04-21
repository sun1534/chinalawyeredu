/**
 * BasicDAO.java
 */

package common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl.OrderEntry;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * @author 华锋 Feb 21, 2011 9:54:47 PM
 */
public class BasicDao extends HibernateDaoSupport {

	/**
	 * 保存对象 新增
	 * 
	 * @param entity
	 * 
	 */
	public void save(final Object entity) {
		getHibernateTemplate().save(entity);
	}

	/**
	 * 新增或更新对�?
	 * 
	 * @param entity
	 */
	public void saveOrupdate(final Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 更新对象
	 * 
	 * @param entity
	 */
	public void update(final Object entity) {
		getHibernateTemplate().update(entity);
	}

	/**
	 * 删除对象
	 * 
	 * @param entity
	 */
	public void delete(final Object entity) {
		getHibernateTemplate().delete(entity);
	}

	/**
	 * 根据ID获取对象
	 * 
	 * @param entity
	 * @param id
	 * @return 对象，或者未能发现符合条件的记录，返回null
	 */
	public Object get(final Class entity, final Serializable id) {
		return getHibernateTemplate().get(entity, id);
	}

	/**
	 * 返回�?有对象的列表
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
	 * 查询指定数量的对�?
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
	 * 返回符合条件的记录列�?
	 * 
	 * @param dc
	 * @return list
	 */
	public List findByCriteria(final DetachedCriteria dc) {
		return (List) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = dc.getExecutableCriteria(session);
				criteria.setProjection(null);
				criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				return criteria.list();
			}
		});
	}

	/**
	 * 搜索指定数量的数�?
	 * 
	 * @param dc
	 * @param count
	 * @return
	 */
	public List findByCriteria(final DetachedCriteria dc, final int count) {
		return (List) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = dc.getExecutableCriteria(session);
				// criteria.setProjection(null);
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
	 * 
	 * @param hql
	 * @return
	 */
	public int findCountByQuery(final String hql) {

		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query queryObject = session.createQuery(hql);
				Object obj = queryObject.uniqueResult();
				return Integer.parseInt(obj.toString());
			}
		});
		return ((Integer) object).intValue();
	}

	/**
	 * 分页查询
	 * 
	 * @param dc
	 * @param pageSize
	 * @param pageNo
	 *            �?1�?始的页数
	 * @return PaginationSupport
	 * @throws ServiceException
	 */
	public PageSupport findPageOnPageNo(final DetachedCriteria dc, final int pageSize, final int pageNo) {

		return this.findPageByCriteria(dc, pageSize, pageNo);

	}

	/**
	 * 分页查询
	 * 
	 * @param dc
	 *            条件
	 * @param page
	 *            页面信息 包含 maxperpage pagenumber �?
	 */

	public PageSupport findPageOnPageNo(final DetachedCriteria dc, final PageSupport page) {
		return (PageSupport) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {

				Criteria criteria = dc.getExecutableCriteria(session);
				long recordCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).longValue();
				// 请不要随便动这里的代�?, 如果这里报错 请先�?�? 配置文件有没有包含hibernate的xml文件
				// 计算记录条数
				criteria.setProjection(null);
				criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

				List items = criteria.setCacheable(true).setFirstResult(page.getStartRecord()).setMaxResults(
						page.getPageSize()).list();
				PageSupport ps = new PageSupport(page, items, (int) recordCount);
				return ps;

			}
		});
	}

	/**
	 * 
	 * @param dc
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public List findNumByCriteria(final DetachedCriteria dc, final int pageSize, final int startIndex) {

		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

				Criteria criteria = dc.getExecutableCriteria(session);
			
				Projection projection = HibernateUtil.getProjection(criteria);

				criteria.setProjection(projection);
				if (projection == null) {
					criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				}
			
				List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
				return items;
			}
		});
	}

	/**
	 * 
	 * @param dc
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	private PageSupport findPageByCriteria(final DetachedCriteria dc, final int pageSize, final int pageNo) {

		return (PageSupport) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

				Criteria criteria = dc.getExecutableCriteria(session);
				OrderEntry[] orderEntries = HibernateUtil.getOrders(criteria);
				criteria = HibernateUtil.removeOrders(criteria);
				Projection projection = HibernateUtil.getProjection(criteria);

				Object object = criteria.setProjection(Projections.rowCount()).uniqueResult();

				System.out.println(object.getClass());

				int recordCount = ((Long) object).intValue();

				criteria.setProjection(projection);
				if (projection == null) {
					criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				}
				// Add the orginal orderEntries
				criteria = HibernateUtil.addOrders(criteria, orderEntries);

				int reallyPageNo = pageNo;
				int pageCount = recordCount - 1 / pageSize + 1; // 总页数，如果当前的pageNo大于总页�?,那就设置为�?�页�?,否则总显示为�?
				// pageCount=10�?,pageNo=11,那实际上pageNo应该�?
				if (reallyPageNo <= 0) {
					reallyPageNo = 0;
				}
				reallyPageNo = pageNo > pageCount ? pageCount : pageNo;
				int startIndex = pageSize * (pageNo - 1);
				List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
				PageSupport ps = new PageSupport(items, recordCount, pageSize, reallyPageNo);
				return ps;
			}
		});
	}

	/**
	 * 分页查询
	 * 
	 * @param dc
	 *            条件
	 * @param pageSize
	 *            返回记录的数�?
	 * @param startIndex
	 *            第一条记录的序号
	 * @return PaginationSupport
	 */
	// private PaginationSupport findPageByCriteria(final DetachedCriteria dc,
	// final int pageSize,
	// final int startIndex) {
	// return (PaginationSupport)
	// getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
	// public Object doInHibernate(Session session) throws HibernateException {
	// Criteria criteria = dc.getExecutableCriteria(session);
	// int totalCount = ((Integer)
	// criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	// criteria.setProjection(null);
	// criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
	// List items =
	// criteria.setCacheable(true).setFirstResult(startIndex).setMaxResults(pageSize).list();
	//
	// PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize,
	// startIndex);
	// return ps;
	// }
	// });
	// }
	/**
	 * 返回符合条件的记录数�?
	 * 
	 * @param dc
	 * @return int
	 */
	public int getCountByCriteria(final DetachedCriteria dc) {
		Integer count = (Integer) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = dc.getExecutableCriteria(session);
				return criteria.setProjection(Projections.rowCount()).uniqueResult();
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
}
