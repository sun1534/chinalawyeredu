/**
 * BasicService.java
 */
package com.sxit.common;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public abstract class BasicService<T> {

	private static Log LOG = LogFactory.getLog(BasicService.class);

	protected BasicDao<T> basicDao;

	protected JdbcTemplate jdbcTemplate;

	@Resource
	public void setBasicDao(BasicDao<T> basicDao) {
		this.basicDao = basicDao;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 新增记录
	 * 
	 * @param entity
	 * @throws com.prj.common.ServiceException
	 */
	@Transactional
	public void save(final T entity) {

		basicDao.save(entity);

	}

	@Transactional
	public void saveOrupdate(final T entity) {

		basicDao.saveOrupdate(entity);

	}

	/**
	 * 更新记录
	 * 
	 * @param entity
	 * @throws com.prj.common.ServiceException
	 */
	@Transactional
	public void update(final T entity) {

		basicDao.update(entity);

	}

	// /**
	// * 删除记录
	// *
	// * @param entity
	// * @param id
	// * @throws com.prj.common.ServiceException
	// */
	// @Transactional
	// public void delete(final Class<T> entity, final Serializable id) {
	//
	// T obj = basicDao.get(entity, id);
	// basicDao.delete(obj);
	//
	// }

	/**
	 * 删除记录
	 * 
	 * @param obj
	 * @throws com.prj.common.ServiceException
	 */
	@Transactional
	public void delete(final T obj) {

		basicDao.delete(obj);

	}

	/**
	 * 根据ID获取对象
	 * 
	 * @param entity
	 * @param id
	 * @return
	 * @throws com.prj.common.ServiceException
	 */
	public Object get(final Class<T> entity, final Serializable id) {

		return basicDao.get(entity, id);

	}

	/**
	 * 所有对象列表
	 * 
	 * @param entity
	 * @return
	 * @throws com.prj.common.ServiceException
	 */
	public List findAll(final Class<T> entity) {

		return basicDao.findAll(entity);

	}

	public List findByQuery(final String query) {

		return basicDao.findByQuery(query);

	}

	// public List findBySqlQuery(final String sql) {
	//
	// return basicDao.findBySqlQuery(sql);
	//
	// }

	/**
	 * 通过Query查询 单参数
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 * @throws com.prj.common.ServiceException
	 */
	public List findByQuery(final String query, final Object parameter) {

		return basicDao.findByQuery(query, parameter);

	}

	/**
	 * 通过Query查询 多参数
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 * @throws com.prj.common.ServiceException
	 */
	public List findByQuery(final String query, final Object[] parameter) {

		return basicDao.findByQuery(query, parameter);

	}

	// /**
	// * 执行一个hql语句
	// *
	// * @param hql
	// */
	// public int execute(String hql) {
	//
	// return basicDao.execute(hql);
	//
	// }

	/**
	 * 执行hql操作
	 * 
	 * @param hql
	 * @param value
	 * @return
	 * @throws com.prj.common.ServiceException
	 */
	public int execute(final String hql, final Object value) {
		return execute(hql, new Object[] { value });
	}

	// /**
	// *
	// * @param hql
	// * @param values
	// * @return
	// * @throws ServiceException
	// */
	// public int execute(final String hql, final Object[] values) {
	//
	// return basicDao.execute(hql, values);
	//
	// }

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
	public PageSupport findPageOnPageNo(final DetachedCriteria detachedCriteria, final int pageSize, final int pageNo) {

		return basicDao.findPageOnPageNo(detachedCriteria, pageSize, pageNo);

	}

	public PageSupport findPageOnPageNo(final Criteria detachedCriteria, final int pageSize, final int pageNo) {

		return basicDao.findPageOnPageNo(detachedCriteria, pageSize, pageNo);

	}

	/**
	 * 条件查询
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws com.prj.common.ServiceException
	 */
	public List findByCriteria(final DetachedCriteria detachedCriteria) {

		detachedCriteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		return basicDao.findByCriteria(detachedCriteria);

	}

	/**
	 * 搜索指定数量的数据
	 * 
	 * @param detachedCriteria
	 * @param count
	 * @return
	 */
	public List findByCriteria(final DetachedCriteria detachedCriteria, final int count) {
		return basicDao.findByCriteria(detachedCriteria, count);
	}

	/**
	 * 查询指定数量的对象
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 */
	public List findNumList(final String query, final int resultcount) {
		return basicDao.findNumList(query, resultcount);

	}

	/**
	 * 查询符合条件的记录条数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws com.prj.common.ServiceException
	 */
	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {

		return basicDao.getCountByCriteria(detachedCriteria);

	}

}