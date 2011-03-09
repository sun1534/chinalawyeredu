/**
 * 
 */
package com.tinylight.common.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 负责为单个Entity对象提供CRUD操作的Hibernate DAO基类.<br>
 * 子类只要在类定义时指定所管理Entity的Class<br>
 * 即拥有对单个Entity对象的CRUD操作.
 * @author scott.Cgi
 * @since  2009-5-11
 *
 */
public class GenericEntityDao<T,PK extends Serializable> extends HibernateDaoSupport {
	
	protected Class<T> entityClass;// DAO所管理的Entity类型.
	public void setEntityClass(Class<T> type){//注入实体类型
		this.entityClass=type;
	}
	public Class<T> getEntityClass(){
		return this.entityClass;
	}
	
	public GenericEntityDao(){}
	public GenericEntityDao(Class<T> entityClass){this.entityClass = entityClass;}

	/**
	 * 根据主键类型的id获取实体对象,立即执行查询返回对象,数据库没有匹配则返回null
	 */
	@SuppressWarnings("unchecked")
	public T getById(PK id) {
		return (T)this.getHibernateTemplate().get(this.entityClass, id);
	}
	
	/**
	 * 获取实体类型的全部对象
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return (List<T>)(this.getHibernateTemplate().loadAll(this.entityClass));
	}
	

	/**
	 * 获取实体对象的代理,如果数据库没有匹配则异常,实体类有关联其它对象则延时加载
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T load(PK id){
		return (T)this.getHibernateTemplate().load(this.entityClass, id);
	}
	
	
	/**
	 * 把数据加载到指定的非持久化实例上
	 * @param entityObject
	 * @param id
	 */
	public void load(T entityObject,PK id){
		this.getHibernateTemplate().load(entityObject, id);
	}
	
	/**
	 * 删除对象.
	 */
	public void delete(T entityObject) {
        this.getHibernateTemplate().delete(entityObject);
	}
	
	/**
	 * 根据id删除对象
	 * @param id
	 */
	public void deleteById(PK id){
		this.delete(this.getById(id));
	}
	
	/**
	 * 强迫装载对象和它的集合,使用了触发器的数据字段比较适合使用
	 * @param entityObject
	 */
	public void refresh(T entityObject){
		this.getHibernateTemplate().refresh(entityObject);
	}
	
    /**
     * 消除与 Hibernate Session 的关联
     * @param entityObject
     */
    public void evict(T entityObject){
    	this.getHibernateTemplate().evict(entityObject);
    }
    
    /**
	 * 保存对象.<br>
	 * 如果对象已在本session中持久化了,不做任何事。<br>
	 * 如果另一个seesion拥有相同的持久化标识,抛出异常。<br>
	 * 如果没有持久化标识属性,调用save()。<br>
	 * 如果持久化标识表明是一个新的实例化对象,调用save()。<br>
	 * 如果是附带版本信息的(version或timestamp)且版本属性表明为新的实例化对象就save()。<br>
	 * 否则调用update()重新关联托管对象
     * @param entityObject
     */
    public void save(T entityObject){
    	this.getHibernateTemplate().saveOrUpdate(entityObject);
    }    
    
    /**
     * 如果对象已在本session中持久化了,覆盖原有的<br>
     * 如果session中没有对应对象,从数据库加载<br>
     * 如果是脱管对象,则什么都不做
     * @param entityObject
     * @return
     */
    @SuppressWarnings("unchecked")
	public T merge(T entityObject){
      return (T)this.getHibernateTemplate().merge(entityObject);
    }
    
}
