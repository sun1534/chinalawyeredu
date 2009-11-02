/**
 * TSysUserDAO.java
 */

package com.changpeng.system.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.common.util.MD5;
import com.changpeng.models.system.SysUser;

/**
 * @author 华锋 2008-2-22 下午02:09:48
 * 
 */
public class SysUserDAO extends BasicDAO {

	/**
	 * 将数据从系统中彻底删除
	 * 
	 * @param userids
	 * @return
	 */
	public int deleteUsers(final int[] userids) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				String hqlDelete = "delete from com.changpeng.models.system.SysUser user where user.userid in (:userids)";
				Query queryObject = session.createQuery(hqlDelete);
				Integer[] params = new Integer[userids.length];
				for (int i = 0; i < userids.length; i++) {
					params[i] = new Integer(userids[i]);
				}
				queryObject.setParameterList("userids", params);
				int i = queryObject.executeUpdate();
				return new Integer(i);
			}
		});
		return ((Integer) object).intValue();
		// Session session = getSession(false);
		// int deletedEntities = session.createQuery(hqlDelete).setParameterList("userids", params).executeUpdate();
		// return deletedEntities;
	}

	/**
	 * 逻辑删除,仅仅将delflag设置为true
	 * 
	 * 记录删除的时间，同时考虑如果之后相同的用户名注册，将delflag设置为false即可
	 * 
	 * @param userids
	 * @return
	 */
	public int deleteUsersLogic(final int[] userids) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				String hqlDelete = "update com.changpeng.models.system.SysUser user set user.delflag=true where user.userid in (:userids)";
				Query queryObject = session.createQuery(hqlDelete);
				Integer[] params = new Integer[userids.length];
				for (int i = 0; i < userids.length; i++) {
					params[i] = new Integer(userids[i]);
				}
				queryObject.setParameterList("userids", params);
				int i = queryObject.executeUpdate();
				return new Integer(i);
			}
		});
		return ((Integer) object).intValue();
		// Session session = getSession(false);
		//
		// Integer[] params = new Integer[userids.length];
		// System.arraycopy(userids, 0, params, 0, userids.length);
		//
		// String hqlDelete = "update com.changpeng.models.system.SysUser user set user.delflag=true where user.userid in (:userids)";
		// int deletedEntities = session.createQuery(hqlDelete).setParameterList("userids", params).executeUpdate();
		// return deletedEntities;

	}

	/**
	 * 用户登录，-1帐号不存在,-2帐号被禁用-3密码不对,大于0则表示验证ok
	 * 
	 * delflag为true的话，不予考虑
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public int userLogin(String loginName, String password) {
		List list = find("from com.changpeng.models.system.SysUser sysuser where sysuser.delflag=false and sysuser.loginname=?", loginName);
		if (list == null || list.size() == 0)
			return -1;// message = "您输入的帐号在系统中不存在,请确认";
		this.sysUser = (SysUser) list.get(0);
		if (sysUser.getStatus() != 0)
			return -2;// 帐号被禁用了
		// String passkey = sysuser.getPasskey();
		String md5password = sysUser.getPassword();
		// 判断组合后的密码是否正确
		// 对输入的密码进行再次处理,防止所谓的md5猜测攻击。^_^
		// if (passkey != null && !passkey.isEmpty())
		// password += passkey;
		String md5input = MD5.md5(password);
		if (!md5password.equals(md5input))
			return -3;// 密码错误
		return sysUser.getUserid();
	}
	
//	/**
//	 * 对密码不进行md5的加密方式
//	 * @param loginName
//	 * @param password
//	 * @return
//	 */
//	public int userLoginNotMd5(String loginName, String password) {
//		List list = find("from com.changpeng.models.system.SysUser sysuser where sysuser.delflag=false and sysuser.loginname=?", loginName);
//		if (list == null || list.size() == 0)
//			return -1;// message = "您输入的帐号在系统中不存在,请确认";
//		this.sysUser = (SysUser) list.get(0);
//		if (sysUser.getStatus() != 0)
//			return -2;// 帐号被禁用了
//		// String passkey = sysuser.getPasskey();
//		String md5password = sysUser.getPassword();
//		// 判断组合后的密码是否正确
//		// 对输入的密码进行再次处理,防止所谓的md5猜测攻击。^_^
//		// if (passkey != null && !passkey.isEmpty())
//		// password += passkey;
////		String md5input = MD5.md5(password);
//		if (!password.equals(md5password))
//			return -3;// 密码错误
//		return sysUser.getUserid();
//	}

	private SysUser sysUser;
	public SysUser getSysUser() {
		return this.sysUser;
	}

	
	public SysUser getSysUserByLawerNo(String lawerno){
		
			List list=this.find("from com.changpeng.models.system.SysUser user where user.lawerno='"+lawerno+"' and roleid=1");
			if(list==null||list.size()==0)
				return null;
			else
				
				return (SysUser)list.get(0);
		
	}
	public SysUser getSysUserByCardNo(String cardno){
		
			List list=this.find("from com.changpeng.models.system.SysUser user where user.cardno='"+cardno+"'");
			if(list==null||list.size()==0)
				return null;
			else
				
				return (SysUser)list.get(0);
			
		
	}
	public SysUser getSysUserByCertNo(String certno){
		
			List list=this.find("from com.changpeng.models.system.SysUser user where user.certno='"+certno+"'");
			if(list==null||list.size()==0)
				return null;
			else
				
				return (SysUser)list.get(0);
	
	}
	
	public SysUser getSysUserByLoginname(String loginname){
		
		List list=this.find("from com.changpeng.models.system.SysUser user where user.loginname='"+loginname+"'");
		if(list==null||list.size()==0)
			return null;
		else
			
			return (SysUser)list.get(0);

	}
	
	//编辑时判断用户名是否重复
	public SysUser getSysUser(String loginname,int userid){
		
		List list=this.find("from com.changpeng.models.system.SysUser user where user.userid!="+userid+" and user.loginname='"+loginname+"'");
		if(list==null||list.size()==0)
			return null;
		else
			
			return (SysUser)list.get(0);

	}
}