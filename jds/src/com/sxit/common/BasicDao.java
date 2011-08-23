/**
 * BasicDao.java
 */

package com.sxit.common;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl.OrderEntry;

/**
 * 
 * @author 华锋 Feb 21, 2011 9:54:47 PM
 */

//public class BasicDao<T> extends HibernateDaoSupport {
	public class BasicDao {

		public BasicDao(){
		}
		public BasicDao(Session session){
			this.session=session;
		}
		
		private Session session;

	/**
		 * @param session the session to set
		 */
		public void setSession(Session session) {
			this.session = session;
		}

	/**
	 * 保存对象 新增
	 * 
	 * @param entity
	 * 
	 */
	public void save(final Object entity) {
		session.save(entity);
	}

	/**
	 * 新增或更新对象
	 * 
	 * @param entity
	 */
	public void saveOrupdate(final T entity) {
		session.saveOrUpdate(entity);
	}

	/**
	 * 更新对象
	 * 
	 * @param entity
	 */
	public void update(final T entity) {
		session.update(entity);

	}

	/**
	 * 删除一个对象，一般为 Object o=basicDao.get(Object.class); basicDao.delete(o);
	 * 
	 * @param entity
	 */
	public void delete(final T entity) {
		session.delete(entity);
	}

	/**
	 * 批量删除 String hql = "delete from TsysOrg where orgid in (:ids)"; return
	 * this.orgDAO.deletes(hql,ids);
	 * 
	 * @param hql
	 * @param ids
	 * @return int
	 */
	public int deletes(final String hql, final Object[] ids) {
	
				int deletedEntities = session.createQuery(hql).setParameterList("ids", ids).executeUpdate();
				return deletedEntities;
		
	}

	/**
	 * 根据ID获取对象
	 * 
	 * @param entity
	 * @param id
	 * @return 对象，或者未能发现符合条件的记录，返回null
	 */
	public Object get(final Class<T> entity, final Serializable id) {
		return session.get(entity, id);
	}




	

	/**
	 * sql语句查询 List list="select * from core_user where statusid=0"
	 * 
	 * @param sql
	 * @return
	 */
	public List findBySqlQuery(final String sql) {

				Query queryObject = session.createSQLQuery(sql);
				return queryObject.list();
			
	}

	/**
	 * 返回符合条件的记录列表,detachedCriteria封装了所有的业务请求
	 * 
	 * 
	 * @param detachedCriteria
	 * @return list
	 */
	public List findByCriteria(final DetachedCriteria detachedCriteria) {
		
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				criteria.setProjection(null);
				criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				return criteria.list();
			
	}

	public List findByProjectCriteria(final DetachedCriteria detachedCriteria) {
		
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
//				criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				return criteria.list();
		
	}
	/**
	 * 搜索指定数量的数据,detachedCriteria封装了所有的业务请求
	 * 
	 * @param detachedCriteria
	 * @param count
	 * @return
	 */
	public List findByCriteria(final DetachedCriteria detachedCriteria, final int count) {
	
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				criteria.setProjection(null);
				criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY).setMaxResults(count);
				return criteria.list();
			
	}

	/**
	 * String hql="update CoreUser a set a.loginName=?,a.loginPwd=?" values为new
	 * String[]{loginName,loginPwd};
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public int execute(final String hql, final Object[] values) {
		
				Query queryObject = session.createQuery(hql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				int i = queryObject.executeUpdate();
				return i;
			
	}

	/**
	 * 执行hql 更新,删除 操作 execute("delete CoreUser u where u.id=100");
	 * 
	 * @param hql
	 * @return int
	 */
	public int execute(final String hql) {
		
				Query queryObject = session.createQuery(hql);
				int i = queryObject.executeUpdate();
				return new Integer(i);
			}
		


	/**
	 * 分页查询
	 * 
	 * @param detachedCriteria
	 * @param pageSize
	 * @param pageNo
	 *            从1开始的页数
	 * @return PageSupport
	 */
	public PageSupport findPageOnPageNo(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo) {

		return this.findPageByCriteria(detachedCriteria, pageSize, pageNo);

	}
	
	public PageSupport findPageOnPageNo(final Criteria detachedCriteria, final int pageSize, final int pageNo) {

		return this.findPageByCriteria(detachedCriteria, pageSize, pageNo);

	}

	/**
	 * 获取分页
	 * 
	 * @param detachedCriteria
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	private PageSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo) {

//		return (PageSupport) getHibernateTemplate().execute(new HibernateCallback<PageSupport>() {
		
				List items = null;
				int recordCount=0;
				int reallyPageNo = pageNo;
				if (reallyPageNo <= 0) {
					reallyPageNo = 1;
				}
				if (reallyPageNo==1&&pageSize == Integer.MAX_VALUE) {
					Criteria criteria = detachedCriteria.getExecutableCriteria(session);
					criteria.setProjection(null);
					criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
					items= criteria.list();
					recordCount=items.size();
				} else {
					Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);
					OrderEntry[] orderEntries = HibernateUtil.getOrders(executableCriteria);
					executableCriteria = HibernateUtil.removeOrders(executableCriteria);
					Projection projection = HibernateUtil.getProjection(executableCriteria);

					recordCount = ((Long) executableCriteria.setProjection(Projections.rowCount()).uniqueResult())
							.intValue();

					executableCriteria.setProjection(projection);
					if (projection == null) {
						executableCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
					}
					// Add the orginal orderEntries
					executableCriteria = HibernateUtil.addOrders(executableCriteria, orderEntries);

					
					int pageCount = recordCount - 1 / pageSize + 1; // 总页数，如果当前的pageNo大于总页数,那就设置为总页数,否则总显示为空
					// pageCount=10页,pageNo=11,那实际上pageNo应该为
					
					reallyPageNo = pageNo > pageCount ? pageCount : pageNo;
					int startIndex = pageSize * (pageNo - 1);
					items = executableCriteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
				}
				PageSupport ps = new PageSupport(items, recordCount, pageSize, reallyPageNo);
				return ps;
		

	}
	
	/**
	 * 获取分页
	 * 
	 * @param detachedCriteria
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	private PageSupport findPageByCriteria(final Criteria criteria, final int pageSize, final int pageNo) {

//		return (PageSupport) getHibernateTemplate().execute(new HibernateCallback<PageSupport>() {
		
				List items = null;
				int recordCount=0;
				int reallyPageNo = pageNo;
//				if (reallyPageNo <= 0) {
//					reallyPageNo = 1;
//				}
				if (reallyPageNo==1&&pageSize == Integer.MAX_VALUE) {
					criteria.setProjection(null);
					criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
					items= criteria.list();
					recordCount=items.size();
				} else {
//					Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);
//					Criteria executableCriteria =criteria;
//					OrderEntry[] orderEntries = HibernateUtil.getOrders(executableCriteria);
//					executableCriteria = HibernateUtil.removeOrders(executableCriteria);
//					Projection projection = HibernateUtil.getProjection(executableCriteria);
					criteria.setProjection(null);
					recordCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult())
							.intValue();

					criteria.setProjection(null);
//					if (projection == null) {
						criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
//					}
					// Add the orginal orderEntries
//					executableCriteria = HibernateUtil.addOrders(executableCriteria, orderEntries);

					
					int pageCount = recordCount - 1 / pageSize + 1; // 总页数，如果当前的pageNo大于总页数,那就设置为总页数,否则总显示为空
					// pageCount=10页,pageNo=11,那实际上pageNo应该为
					
					reallyPageNo = pageNo > pageCount ? pageCount : pageNo;
//					int startIndex = pageSize * (reallyPageNo - 1);
					int startIndex = pageSize * (reallyPageNo );
					
					items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
					System.out.println("items====="+items+",startIndex==="+startIndex+",,,"+pageSize+"==="+pageNo);
				}
				PageSupport ps = new PageSupport(items, recordCount, pageSize, reallyPageNo);
				return ps;
			
	}

	/**
	 * 返回符合条件的记录数量
	 * 
	 * @param detachedCriteria
	 * @return int
	 */
	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);

				int i = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
				return i;
			
	}

//	/**
//	 * 分页查询
//	 * 
//	 * @param detachedCriteria
//	 * @param page
//	 *            组装了pageNo和pageSize的分页
//	 * @return
//	 */
//
//	public PageSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final PageSupport page) {
//		return  getHibernateTemplate().executeWithNativeSession(new HibernateCallback<PageSupport>() {
//			public PageSupport doInHibernate(Session session) throws HibernateException {
//
//				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
//				long recordsize = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
//				// 请不要随便动这里的代码, 如果这里报错 请先检查 配置文件有没有包含hibernate的xml文件
//				// 计算记录条数
//				// criteria.setProjection(Projections.rowCount());
//				// int recordsize = (Integer) criteria.uniqueResult();
//
//				criteria.setProjection(null);
//				criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
//
//				int startIndex = (page.getPageNo() - 1) * page.getPageSize();
//				List items = criteria.setCacheable(true).setFirstResult(startIndex).setMaxResults(page.getPageSize())
//						.list();
//				// PageSupport ps = new PageSupport(page,items,recordsize);
//				PageSupport ps = new PageSupport(items, (int) recordsize, page.getPageSize(), page.getPageNo());
//				return ps;
//
//			}
//		});
//	}

	/**
	 * 
	 * 查询指定数量的对象
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 */
	public List findNumList(final String query, final int resultcount) {
//		return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List>() {
//			public List doInHibernate(Session session) throws HibernateException {
//				Query q = session.createQuery(query);
//				q.setMaxResults(resultcount);
//				return q.list();
//			}
//		});
		return this.findNumList(query, 0, resultcount);
	}
	/**
	 * 
	 * @param query
	 * @param resultcount
	 * @return
	 */
	public List findNumList(final String query, final int start,final int resultcount) {
		
				Query q = session.createQuery(query);
				q.setFirstResult(start);
				q.setMaxResults(resultcount);
				return q.list();
			
	}

	public List<Object[]> findBySqlQuery(final String sql, final Class[] entities,
			final Object[] params, final int first, final int maxResults) {
		
				SQLQuery queryObject = session.createSQLQuery(sql);
				if (entities != null){
					for(Class c:entities){
						queryObject.addEntity(c);
					}					
				}
				if (params != null){
					int idx = 0;
					for(Object p : params){
						queryObject.setParameter(idx, p);
						idx += 1;
					}
				}
				if (first >= 0){
					queryObject.setFirstResult(first);
				}
				if (maxResults >= 0){
					queryObject.setMaxResults(maxResults);
				}
				return queryObject.list();
	
	}
	
	public List<Object[]> findBySqlQuery(final String sql, final Class[] entities1, final Map<String,Class> entities2,
			final Object[] params, final int first, final int maxResults) {
		
				SQLQuery queryObject = session.createSQLQuery(sql);
				if (entities1 != null){
					for(Class c:entities1){
						queryObject.addEntity(c);
					}					
				}
				if (entities2 != null){
					for(String alias : entities2.keySet()){
						queryObject.addEntity(alias,entities2.get(alias));
					}
				}
				if (params != null){
					int idx = 0;
					for(Object p : params){
						queryObject.setParameter(idx, p);
						idx += 1;
					}
				}
				if (first >= 0){
					queryObject.setFirstResult(first);
				}
				if (maxResults >= 0){
					queryObject.setMaxResults(maxResults);
				}
				return queryObject.list();
		
	}
	
	/**
	 * 通过hsql分页
	 * @param hsql
	 * @param params
	 * @param first
	 * @param maxResults
	 * @return
	 */
	public PageSupport findByQuery(final String hsql,
			final Object[] params, final int pageSize, final int pageNo) {
//			List object = (List)getHibernateTemplate().execute(new HibernateCallback() {
//			public List doInHibernate(Session session) {
				List items=null;
				Query queryObject = session.createQuery(hsql);
				
				if (params != null){
					int idx = 0;
					for(Object p : params){
						queryObject.setParameter(idx, p);
						idx += 1;
					}
				}
				int recordCount=0;
				int reallyPageNo = pageNo;
				if (reallyPageNo <= 0) {
					reallyPageNo = 1;
				}
				
				recordCount=countOfQuery(hsql,params);
//				int pageCount = recordCount / pageSize + 1; // 总页数，如果当前的pageNo大于总页数,那就设置为总页数,否则总显示为空
				int pageCount = 1;
				// pageCount=10页,pageNo=11,那实际上pageNo应该为
				if(recordCount%pageSize!=0){
					pageCount = recordCount/pageSize+1;
				}else{
					pageCount = recordCount/pageSize;
				}
				
				
				reallyPageNo = pageNo > pageCount ? pageCount : pageNo;
				int startIndex = pageSize * (reallyPageNo - 1);
				
				
				if (startIndex >= 0){
					queryObject.setFirstResult(startIndex);
				}
				if (pageSize >= 0){
					queryObject.setMaxResults(pageSize);
				}
				items=queryObject.list();
				
				PageSupport ps = new PageSupport(items,recordCount,pageSize,pageNo);
				
				return ps;
//				List result = new ArrayList();
//				result.add(ps);
//				return result;
//			}
//		});
		
		
//		return (PageSupport) object.get(0);
	}
	public Integer getCountOfQuery(final String hsql) {
		return this.countOfQuery(hsql, null);
	}
	/**
	 * 计算总数
	 * @param hsql
	 * @param params
	 * @return
	 */
	public Integer countOfQuery(final String hsql,final Object[] params) {
//		Query  query =getHibernateTemplate().getSessionFactory().openSession().createQuery("select count(*) "+ hsql);
		Query  query =session.createQuery("select count(*) "+ hsql);
		
		if (params != null){
			int idx = 0;
			for(Object p : params){
				query.setParameter(idx, p);
				idx += 1;
			}
		}
		int count = ((Long)query.uniqueResult()).intValue();
		
		return count;
	}
	
	
	
	public int executeUpdateSqlQuery(final String sql,Session session,final Object...params){
//		int result =(Integer) getHibernateTemplate().execute(new HibernateCallback() {
//			public Integer doInHibernate(Session session) {
				
				SQLQuery queryObject = session.createSQLQuery(sql);
				if (params != null){
					int idx = 0;
					for(Object p : params){
						queryObject.setParameter(idx, p);
						idx += 1;
					}
				}
				return queryObject.executeUpdate();
//			}
//		});
//		return result;
	}
	
	public List executeSqlQuery(final String sql,final Object... params){
		
				
				SQLQuery queryObject = session.createSQLQuery(sql);
				if (params != null){
					int idx = 0;
					for(Object p : params){
						queryObject.setParameter(idx, p);
						idx += 1;
					}
				}
				return queryObject.list();
		
	}
	
	
	
	public int countOfSqlQuery(final String sql){

				String newsql = "select distinct count(*) from (" + sql +") zz";
				SQLQuery queryObject = session.createSQLQuery(newsql);
				BigInteger count =(	BigInteger) queryObject.list();
				return count.intValue();
			
	}
	
	
	
	public PageSupport getPageSupportBySqlQuery(final String sql, final Class[] classes,
			final Object[] params, PageSupport ps){
		List lst = findBySqlQuery(sql,classes,params,ps.getFirstRecordNo(),ps.getPageSize());
		int count = countOfSqlQuery(sql);
		ps.setRecordCount(count);
		ps.setItems(lst);
		return ps;
	}
	
}
