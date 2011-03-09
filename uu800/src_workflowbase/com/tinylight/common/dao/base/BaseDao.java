/**
 * 
 */
package com.tinylight.common.dao.base;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tinylight.common.dao.hibernate.GenericDao;
import com.tinylight.common.dao.hibernate.GenericEntityDao;
import com.tinylight.common.dao.jdbc.SimpleJdbcDao;
import com.tinylight.common.dao.support.Page;



/**
 * @author scott.Cgi
 * @since  2009-5-12
 * 提供dao的所有操作<br>
 * 实现类由spring注入:<br>
 * {@link com.tinylight.common.dao.hibernate.GenericEntityDao}
 * {@link com.tinylight.common.dao.hibernate.GenericDao}
 * {@link com.tinylight.common.dao.jdbc.SimpleJdbcDao}
 */
public class BaseDao<T,PK extends Serializable> implements IBaseDao<T,PK>{

	protected Class<T> entityClass;// DAO所管理的Entity类型.
	private GenericEntityDao<T,PK> gedao;
	private GenericDao gdao;
	private SimpleJdbcDao sjdao;
	
	public Class<T> getEntityClass() {return entityClass;}
	public void setEntityClass(Class<T> entityClass) {
		gedao.setEntityClass(this.entityClass);//手动注入实体类类型
		this.entityClass = entityClass;
	}
	
	public GenericEntityDao<T, PK> getGedao() {return gedao;}
	public void setGedao(GenericEntityDao<T, PK> gedao) {
		gedao.setEntityClass(this.entityClass);//在spring注入泛型实体dao时候注入实体类型
		this.gedao = gedao;
	}
	
	public GenericDao getGdao() {return gdao;}
	public void setGdao(GenericDao gdao) {this.gdao = gdao;}
	
	public SimpleJdbcDao getSjdao() {return sjdao;}
	public void setSjdao(SimpleJdbcDao sjdao) {this.sjdao = sjdao;}

	/**
	 *让spring提供构造函数注入
	 */
	public BaseDao(Class<T> type) {
		this.entityClass = type;//spring构建时,根据配置文件最先注入实体类型
	}
	
	public BaseDao(){}
	
	
	public void clear() {
		gdao.clear();
	}
	
	
	public Query createQuery(String hql, Object... values) {
		return gdao.createQuery(hql, values);
	}
	
	
	public void delete(T entityObject) {
		gedao.delete(entityObject);
	}
	
	
	public void deleteById(PK id) {
		gedao.deleteById(id);
	}
	
	
	public void evict(T entityObject) {
		gedao.evict(entityObject);
	}
	
	
	public List<T> find(String hql, Object... values) {
		return gdao.find(hql, values);
	}
	
	
	public List<T> findByNamedParams(String hql, String[] paramNames,
			Object... values) {
		return gdao.findByNamedParams(hql, paramNames, values);
	}
	
	
	public void flush() {
		gdao.flush();
	}
	
	
	public List<T> getAll() {
		return gedao.getAll();
	}
	
	
	public T getById(PK id) {
		return gedao.getById(id);
	}
	
	
	public Session getNativeHibernateSession() {
		return gdao.getNativeHibernateSession();
	}
	
	
	public StatelessSession getNativeStatelessHibernateSession() {
		return gdao.getNativeStatelessHibernateSession();
	}
	
	
	public HibernateTemplate getHibernateTemplate() {
		return gdao.getHibernateTemplate();
	}
	
	
	public Iterator<T> iterator(String hql, Object... values) {
		return gdao.iterator(hql, values);
	}
	
	
	public SimpleJdbcDao jdbc() {
		return sjdao;
	}
	
	
	public T load(PK id) {
		return gedao.load(id);
	}
	
	
	public void load(T entityObject, PK id) {
		gedao.load(entityObject, id);
	}
	
	
	public T merge(T entityObject) {
		return gedao.merge(entityObject);
	}
	
	
	public SQLQuery nativeSqlQuery(String sql,boolean isBind) {
		return gdao.nativeSqlQuery(sql,isBind);
	}
	
	
	public Page<T> pagedQuery(String hql, int pageNo, int pageSize,
			Object... values) {
		return gdao.pagedQuery(hql, pageNo, pageSize, values);
	}
	
	
	public Page<T> pagedQueryByStartNo(String hql, int startNo, int pageSize,
			Object... values) {
		return gdao.pagedQueryByStartNo(hql, startNo, pageSize, values);
	}
	
	
	public void refresh(T entityObject) {
		gedao.refresh(entityObject);
	}
	
	
	public void save(T entityObject) {
		gedao.save(entityObject);
	}

	
	public void changeEntityClass(Class<T> entityClass) {
       gedao.setEntityClass(entityClass);	
	}
	
}
