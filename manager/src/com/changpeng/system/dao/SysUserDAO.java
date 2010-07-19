/**
 * TSysUserDAO.java
 */

package com.changpeng.system.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.util.MD5;
import com.changpeng.models.SysUser;

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
				String hqlDelete = "delete from com.changpeng.models.SysUser user where user.userid in (:userids)";
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
		this.sysUser = getSysUserByLoginname(loginName);
		if (sysUser == null)
			sysUser = getSysUserBySystemno(loginName);
		if (sysUser == null)
			return -1;// message = "您输入的帐号在系统中不存在,请确认";

		if (sysUser.getStatus() != 0)
			return -2;// 帐号被禁用了
		// String passkey = sysuser.getPasskey();
		String md5password = sysUser.getPassword();
		// 判断组合后的密码是否正确
		// 对输入的密码进行再次处理,防止所谓的md5猜测攻击。......猜个屁，没用
		String md5input = MD5.md5(password);
		if (!md5password.equals(md5input)&&!md5password.equals(password)) //对没有md5加密的也通过.md5加密啥用呢？纯粹搞自己
			return -3;// 密码错误
		return sysUser.getUserid();
	}

	private SysUser sysUser;

	public SysUser getSysUser() {
		return this.sysUser;
	}

	/**
	 * 
	 * @param loginname
	 * @return
	 */
	public SysUser getSysUserByLoginname(String loginname) {

		DetachedCriteria dc = DetachedCriteria.forClass(SysUser.class);
		dc.add(Restrictions.eq("loginname", loginname));
		List list = this.findAllByCriteria(dc);
		if (list == null || list.size() == 0)
			return null;
		else
			return (SysUser) list.get(0);
	}

	/**
	 * 
	 * @param systemno
	 * @return
	 */
	public SysUser getSysUserBySystemno(String systemno) {

		DetachedCriteria dc = DetachedCriteria.forClass(SysUser.class);
		dc.add(Restrictions.eq("systemno", systemno));
		List list = this.findAllByCriteria(dc);
		if (list == null || list.size() == 0)
			return null;
		else
			return (SysUser) list.get(0);
	}

	/**
	 * 编辑时判断用户名是否重复
	 * 
	 * @param loginname
	 * @param userid
	 * @return
	 */
	public SysUser getSysUser(String loginname, int userid) {

		DetachedCriteria dc = DetachedCriteria.forClass(SysUser.class);
		dc.add(Restrictions.eq("userid", userid));
		dc.add(Restrictions.eq("loginname", loginname));
		List list = this.findAllByCriteria(dc);
		if (list == null || list.size() == 0)
			return null;
		else
			return (SysUser) list.get(0);

	}
}