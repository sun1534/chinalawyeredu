/**
 * BasicService.java
 */
package com.changpeng.common;

import java.io.Serializable;
import java.util.List;


import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.exception.ServiceException;

/**
 * @author mfq
 * 
 */
public class BasicService {

    protected BasicDAO basicDAO;

    public void setBasicDAO(BasicDAO basicDAO) {
        this.basicDAO = basicDAO;
    }

    /**
     * 新增记录
     * @param entity
     * @throws jxq.common.ServiceException
     */
    @Transactional
    public void save(final Object entity) throws ServiceException {
        try {
            basicDAO.save(entity);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    @Transactional
    public void saveOrupdate(final Object entity) throws ServiceException {
        try {
            basicDAO.saveOrupdate(entity);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    /**
     * 更新记录
     * @param entity
     * @throws jxq.common.ServiceException
     */
    @Transactional
    public void update(final Object entity) throws ServiceException {
        try {
            basicDAO.update(entity);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 删除记录
     * @param entity
     * @param id
     * @throws jxq.common.ServiceException
     */
    @Transactional
    public void delete(final Class entity, final Serializable id) throws ServiceException {
        try {
            Object obj = basicDAO.get(entity, id);
            basicDAO.delete(obj);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 删除记录
     * @param obj
     * @throws jxq.common.ServiceException
     */
    @Transactional
    public void delete(final Object obj) throws ServiceException {
        try {
            basicDAO.delete(obj);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据ID获取对象
     * @param entity
     * @param id
     * @return
     * @throws jxq.common.ServiceException
     */
    public Object get(final Class entity, final Serializable id) throws ServiceException {
        try {
            return basicDAO.get(entity, id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 所有对象列表
     * @param entity
     * @return
     * @throws jxq.common.ServiceException
     */
    public List findAll(final Class entity) throws ServiceException {
        try {
            return basicDAO.findAll(entity);

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List findByQuery(final String query) throws ServiceException {
        try {
            return basicDAO.findByQuery(query);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

	public List findBySqlQuery(final String sql) throws ServiceException {
		   try {
	            return basicDAO.findBySqlQuery(sql);
	        } catch (Exception e) {
	            throw new ServiceException(e);
	        }
	}
    
    /**
     * 通过Query查询 单参数
     * @param query
     * @param parameter
     * @return
     * @throws jxq.common.ServiceException
     */
    public List findByQuery(final String query, final Object parameter) throws ServiceException {
        try {
            return basicDAO.findByQuery(query, parameter);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /**
     * 通过Query查询  多参数
     * @param query
     * @param parameter
     * @return
     * @throws jxq.common.ServiceException
     */
    public List findByQuery(final String query, final Object[] parameter) throws ServiceException {
        try {
            return basicDAO.findByQuery(query, parameter);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    

    /**
     * 执行一个hql语句
     *
     * @param hql
     */
    public int execute(String hql) throws ServiceException {
        try {
            return basicDAO.execute(hql);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 执行sql操作
     * @param sql
     * @return
     * @throws jxq.common.ServiceException
     */
    public int executeSql(String sql) throws ServiceException {
        try {
            return basicDAO.executeSql(sql);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 执行hql操作
     * @param hql
     * @param value
     * @return
     * @throws jxq.common.ServiceException
     */
    public int execute(final String hql, final Object value) throws ServiceException {
        return execute(hql, new Object[]{value});
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
            return basicDAO.findPageOnPageNo(detachedCriteria, pageSize, pageNo);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    /**
     * 条件查询
     * @param detachedCriteria
     * @return
     * @throws jxq.common.ServiceException
     */
    public List findByCriteria(final DetachedCriteria detachedCriteria) throws ServiceException {
        try {
        	detachedCriteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
            return basicDAO.findByCriteria(detachedCriteria);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 搜索指定数量的数据
     * @param detachedCriteria
     * @param count
     * @return
     */
    public List findByCriteria(final DetachedCriteria detachedCriteria,final int count) {
    	return basicDAO.findByCriteria(detachedCriteria,count);
    }
    
    /** 
     * 查询指定数量的对象
     * @param query
     * @param parameter
     * @return
     */   
    public List findNumList(final String query,final int resultcount){
		return basicDAO.findNumList(query, resultcount);
    	
    }
    
    /**
     * 查询符合条件的记录条数
     * @param detachedCriteria
     * @return
     * @throws jxq.common.ServiceException
     */
    public int getCountByCriteria(final DetachedCriteria detachedCriteria) throws ServiceException {
        try {
            return basicDAO.getCountByCriteria(detachedCriteria);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    /** 
     * 返回唯一记录
     * @param query
     * @param parameter
     * @return
     */   
    public Object findUniqueResult(final String query){
		return basicDAO.getSessionFactory().getCurrentSession().createQuery(query).uniqueResult();
    }

}



