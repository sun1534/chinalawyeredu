/**
 * BasicService.java
 */
package common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mfq
 * 
 */
public abstract class BasicService {

	protected BasicDao basicDao;

	protected JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 新增记录
	 * 
	 * @param entity
	 * @throws com.sjlm.common.ServiceException
	 */
	@Transactional
	public void save(final Object entity) {

		basicDao.save(entity);

	}

	@Transactional
	public void saveOrupdate(final Object entity) {

		basicDao.saveOrupdate(entity);

	}

	/**
	 * 更新记录
	 * 
	 * @param entity
	 * @throws com.sjlm.common.ServiceException
	 */
	@Transactional
	public void update(final Object entity) {

		basicDao.update(entity);

	}

	/**
	 * 删除记录
	 * 
	 * @param entity
	 * @param id
	 * @throws com.sjlm.common.ServiceException
	 */
	@Transactional
	public void delete(final Class entity, final Serializable id) {

		Object obj = basicDao.get(entity, id);
		basicDao.delete(obj);

	}

	/**
	 * 删除记录
	 * 
	 * @param obj
	 * @throws com.sjlm.common.ServiceException
	 */
	@Transactional
	public void delete(final Object obj) {

		basicDao.delete(obj);

	}

	/**
	 * 根据ID获取对象
	 * 
	 * @param entity
	 * @param id
	 * @return
	 * @throws com.sjlm.common.ServiceException
	 */
	public Object get(final Class entity, final Serializable id) {

		return basicDao.get(entity, id);

	}

	/**
	 * �?有对象列�?
	 * 
	 * @param entity
	 * @return
	 * @throws com.sjlm.common.ServiceException
	 */
	public List findAll(final Class entity) {

		return basicDao.findAll(entity);

	}

	public List findByQuery(final String query) {

		return basicDao.findByQuery(query);

	}

	public List findBySqlQuery(final String sql) {

		return basicDao.findBySqlQuery(sql);

	}

	/**
	 * 通过Query查询 单参�?
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 * @throws com.sjlm.common.ServiceException
	 */
	public List findByQuery(final String query, final Object parameter) {

		return basicDao.findByQuery(query, parameter);

	}

	/**
	 * 通过Query查询 多参�?
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 * @throws com.sjlm.common.ServiceException
	 */
	public List findByQuery(final String query, final Object[] parameter) {

		return basicDao.findByQuery(query, parameter);

	}

	/**
	 * 执行�?个hql语句
	 * 
	 * @param hql
	 */
	public int execute(String hql) {

		return basicDao.execute(hql);

	}

	/**
	 * 执行hql操作
	 * 
	 * @param hql
	 * @param value
	 * @return
	 * @throws com.sjlm.common.ServiceException
	 */
	public int execute(final String hql, final Object value) {
		return execute(hql, new Object[] { value });
	}

	/**
	 * 
	 * @param hql
	 * @param values
	 * @return
	 * @throws ServiceException
	 */
	public int execute(final String hql, final Object[] values) {

		return basicDao.execute(hql, values);

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

		return basicDao.findPageOnPageNo(dc, pageSize, pageNo);

	}

	/**
	 * 条件查询
	 * 
	 * @param dc
	 * @return
	 * @throws com.sjlm.common.ServiceException
	 */
	public List findByCriteria(final DetachedCriteria dc) {

		dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		return basicDao.findByCriteria(dc);

	}

	/**
	 * 搜索指定数量的数�?
	 * 
	 * @param dc
	 * @param count
	 * @return
	 */
	public List findByCriteria(final DetachedCriteria dc, final int count) {
		return basicDao.findByCriteria(dc, count);
	}
    /**
     * 分页查询
     * @param dc
     * @param page
     * @return PageSupport
     * @throws ServiceException
     */
    public PageSupport findPageOnPageNo(final DetachedCriteria dc, PageSupport page)  {
            return basicDao.findPageOnPageNo(dc, page);
    }
	/**
	 * 查询指定数量的对�?
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 */
	public List findNumList(final String query, final int resultcount) {
		return basicDao.findNumList(query, resultcount);

	}

	/**
	 * 查询符合条件的记录条�?
	 * 
	 * @param dc
	 * @return
	 * @throws com.sjlm.common.ServiceException
	 */
	public int getCountByCriteria(final DetachedCriteria dc) {

		return basicDao.getCountByCriteria(dc);

	}
	// /**
	// * 返回唯一记录
	// * @param query
	// * @param parameter
	// * @return
	// */
	// public Object findUniqueResult(final String query){
	// return
	// basicDao.getSessionFactory().getCurrentSession().createQuery(query).uniqueResult();
	// }

	public BasicDao getBasicDao() {
		return basicDao;
	}
	public void setBasicDao(BasicDao basicDao) {
		this.basicDao = basicDao;
	}
}