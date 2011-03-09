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

import com.tinylight.common.dao.jdbc.SimpleJdbcDao;
import com.tinylight.common.dao.support.Page;

/**
 * @author scott.Cgi
 * @since  2009-5-12
 *
 */
public interface IBaseDao<T,PK extends Serializable> {

	/**
	 * 根据主键类型的id获取实体对象,立即执行查询返回对象,数据库没有匹配则返回null
	 */
	public T getById(PK id);
	
	/**
	 * 获取实体类型的全部对象
	 */
	public List<T> getAll();
	
	/**
	 * 获取实体对象的代理,如果数据库没有匹配则异常,实体类有关联其它对象则延时加载
	 * @param id
	 * @return
	 */
	public T load(PK id);
	
	/**
	 * 把数据加载到指定的非持久化实例上
	 * @param entityObject
	 * @param id
	 */
	public void load(T entityObject,PK id);
	
	/**
	 * 删除对象.
	 */
	public void delete(T entityObject);
	
	/**
	 * 根据id删除对象
	 * @param id
	 */
	public void deleteById(PK id);
	
	/**
	 * 强迫装载对象和它的集合,使用了触发器的数据字段比较适合使用
	 * @param entityObject
	 */
	public void refresh(T entityObject);
	
    /**
     * 消除与 Hibernate Session 的关联
     * @param entityObject
     */
    public void evict(T entityObject);
    
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
    public void save(T entityObject);
    
    /**
     * 如果对象已在本session中持久化了,覆盖原有的<br>
     * 如果session中没有对应对象,从数据库加载<br>
     * 如果是脱管对象,则什么都不做
     * @param entityObject
     * @return
     */
	public T merge(T entityObject);
	
	
	/**
	 * 根据hql查询,直接使用HibernateTemplate的find函数.
	 * @param <T>
	 * @param hql
	 * @param values
	 * @return
	 */
	public  List<T> find(String hql, Object... values);
	
	/**
	 * 根据命名参数查询
	 * @param <T>
	 * @param hql 带有命名参数的hql语句
	 * @param paramNames 命名参数的名字
	 * @param values  命名参数的值<br>
	 * <b>例如:</b><br>
	 * findByNamedParams("from Test where t1 = :t",new String[]{"t"},tValue);
	 * @return 
	 */
	public  List<T> findByNamedParams(String hql,String[] paramNames,Object...values);
	
	/**
	 * 创建Query对象.<br>
	 * 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * @param hql
	 * @param values
	 * @return
	 */
	public Query createQuery(String hql,Object... values);
	
	/**
	 * 执行一些必须的sql语句把内存中的对象同步到数据库中
	 */
	public void flush();
	
	/**
	 * 清除对象缓存
	 */
	public void clear();
	
	/**
	 * 返回iterator接口类型的结果
	 * @param <T>
	 * @param hql
	 * @param values
	 * @return
	 */
	public  Iterator<T> iterator(String hql,Object...values);
	
	/**
	 * @return 当前上下文的原生Hibernate session对象,依然受到spring事务管理不需要手动close
	 */
	public Session getNativeHibernateSession();
	
	/**
	 * @return 当前上下文的原生Hibernate StatelessSession对象<br>
	 * 此对象不级联关联实例,忽略集合不触发Hibernate事件模型和拦截器,没有一级缓存,没有持久化上下文,接近JDBC.
	 */
	public StatelessSession getNativeStatelessHibernateSession();
	
	/**
	 * 执行本地查询获得SQLQuery对象<br>
	 * 可以调用addEntity(*.class).list();获得对应实体list集合<br>
	 * addEntity.add(*.class).addJoin(*.class).list();获得一对多代理对象<br>
	 * 更多用法见google
	 * @param sql
	 * @param isBind 表示底层使用的session是否与当前线程绑定,如果绑定则受到spring管理生命周期
	 * @return
	 */
	public SQLQuery nativeSqlQuery(String sql,boolean isBind);
	
	/**
	 * @param <T>
	 * @param hql
	 * @param pageNo 页面号
	 * @param pageSize 页面容量
	 * @param values
	 * @return
	 */
	public  Page<T> pagedQuery(String hql, int pageNo, int pageSize, Object... values);
	
    /**
     * @param <T>
     * @param hql
     * @param startNo 分页从哪一条数据开始
     * @param pageSize 页面容量
     * @param values
     * @return
     */
	public  Page<T> pagedQueryByStartNo(String hql, int startNo, int pageSize, Object... values);
	
	/**
	 * @return 获得spring的HibernateTemplate拥有更多的功能
	 */
	public HibernateTemplate getHibernateTemplate();
	
	
	/**
	 * @return 获得jdbc操作的超绚酷dao
	 */
	public SimpleJdbcDao jdbc();
	
	/**
	 * 设置dao实体类的类型
	 */
	public void changeEntityClass(Class<T> entityClass);
}
