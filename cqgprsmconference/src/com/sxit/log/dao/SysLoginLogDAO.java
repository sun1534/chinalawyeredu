/**
 * TSysLoginLogDAO.java
 */

package com.sxit.log.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.sxit.common.BasicDAO;
import com.sxit.models.log.SysLoginLog;

/**
 * @author 华锋 2008-2-25 上午11:10:52
 * 
 * <pre>
 * 作为不使用Spring的 HibernateTemplate 来实现DAO的替代解决方案，你依然可以用传统的编程风格来编写你的数据访问代码。 无需将你的Hibernate访问代码包装在一个回调中，只需符合Spring的通用的 DataAccessException 异常体系。 Spring的 HibernateDaoSupport 基类提供了访问与当前事务绑定的 Session 对象的函数，因而能保证在这种情况下异常的正确转化。 类似的函数同样可以在 SessionFactoryUtils 类中找到，但他们以静态方法的形式出现。 值得注意的是，通常将一个false作为参数（表示是否允许创建）传递到 getSession(..) 方法中进行调用。 此时，整个调用将在同一个事务内完成（它的整个生命周期由事务控制，避免了关闭返回的 Session 的需要）。 
 * </pre>
 * 
 */
public class SysLoginLogDAO extends BasicDAO {
	
	public SysLoginLog load(final Serializable id) {
		return (SysLoginLog)load(SysLoginLog.class, id);
	}
     /**
     * 根据ID获取对象
     * @param entity
     * @param id
     * @return 对象，或者未能发现符合条件的记录，返回null
     */
	public SysLoginLog get(final Serializable id) {
		return (SysLoginLog)get(SysLoginLog.class, id);
	}
	
	/**
	 * 获取记录
	 * @return
	 */
	public List findAll() {
		return findAll(SysLoginLog.class);
	}

	/**
	 * 将sysloginlog中所有的islast设置为false
	 */
	public void updateIsLast2False(int userid) {

		execute("update com.sxit.models.log.SysLoginLog log set log.islast=false where log.userid=?", userid);
	}
	
	/**
	 * 得到某个人总计的登录次数,另外一种方式是自己写select count(*) from sysloginlog
	 * @param userid
	 * @return
	 */
	public int getLoginCountByUserId(int userid){
		List list=find("from com.sxit.models.log.SysLoginLog log where log.userid=?", userid);
		if(list!=null&&list.size()!=0)
			return list.size();
		return 0;
	}
	
	public Timestamp getLastLoginTime(int userid){
		List list=find("from com.sxit.models.log.SysLoginLog log where log.islast=true");
		if(list!=null&&list.size()!=0)
		{
			SysLoginLog loginlog=(SysLoginLog)(list.get(0));
			return loginlog.getLoginTime();
		}
		return null;
		
		
	}
}