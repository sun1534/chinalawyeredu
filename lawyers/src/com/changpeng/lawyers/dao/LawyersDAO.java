/**
 * TSysUserDAO.java
 */

package com.changpeng.lawyers.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicDAO;
import com.changpeng.models.Lawyers;

/**
 * @author 华锋 2008-2-22 下午02:09:48
 * 
 */
public class LawyersDAO extends BasicDAO {

	/**
	 * 律师登录，-1帐号不存在,-2帐号被禁用-3密码不对,大于0则表示验证ok
	 * 
	 * delflag为true的话，不予考虑
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public int userLogin(String loginName, String password) {
		this.lawyers = getLawyerByLoginname(loginName);
		if (lawyers == null)
			lawyers=this.getLawyerBySystemno(loginName);
		if(lawyers==null)
			return -1;// message = "您输入的帐号在系统中不存在,请确认";

		if (lawyers.getStatus() != 0)
			return -2;// 帐号被禁用了
		String md5password = lawyers.getPasswd();
		String md5input=password;
//		String md5input = MD5.md5(password);
		if (!md5password.equals(md5input))
			return -3;// 密码错误
		return lawyers.getLawyerid();
	}

	/**
	 * 根据登录名获取律师信息
	 * 
	 * @param loginname
	 * @return
	 */
	public Lawyers getLawyerByLoginname(String loginname) {

		DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class);
		dc.add(Restrictions.eq("loginname", loginname));
		List list = this.findAllByCriteria(dc);
		if (list == null || list.size() == 0)
			return null;
		else
			return (Lawyers) list.get(0);
	}

	public Lawyers getLawyerBySystemno(String systemno){
		DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class);
		dc.add(Restrictions.eq("systemno", systemno));
		List list = this.findAllByCriteria(dc);
		if (list == null || list.size() == 0)
			return null;
		else
			return (Lawyers) list.get(0);
	}
	
	/**
	 * 根据卡号获取律师信息
	 * 
	 * @param cardno
	 * @return
	 */
	public Lawyers getLawyerByCardno(String cardno) {

		DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class);
		dc.add(Restrictions.eq("cardno", cardno));
		List list = this.findAllByCriteria(dc);
		if (list == null || list.size() == 0)
			return null;
		else
			return (Lawyers) list.get(0);
	}

	/**
	 * 编辑时判断用户名是否重复
	 * 
	 * @param loginname
	 * @param userid
	 * @return
	 */
	public Lawyers getLawyer(String loginname, int userid) {

		DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class);
		dc.add(Restrictions.eq("lawyerid", userid));
		dc.add(Restrictions.eq("loginname", loginname));
		List list = this.findAllByCriteria(dc);
		if (list == null || list.size() == 0)
			return null;
		else
			return (Lawyers) list.get(0);

	}

	public Lawyers getLawyerbyLawyerno(String lawyerno, int thegroup) {

		DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class);
		dc.add(Restrictions.eq("lawyerno", lawyerno));
		dc.add(Restrictions.eq("directunion", thegroup));
		List list = this.findAllByCriteria(dc);
		if (list == null || list.size() == 0)
			return null;
		else
			return (Lawyers) list.get(0);

	}

	public Lawyers getLawyerbyLawyerno(String lawyerno, int province, int city) {

		DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class);
		dc.add(Restrictions.eq("lawyerno", lawyerno));
		if(city!=0)
		dc.add(Restrictions.eq("directunion", city));
		if(province!=0)
		dc.add(Restrictions.eq("provinceunion", province));
		List list = this.findAllByCriteria(dc);
		if (list == null || list.size() == 0)
			return null;
		else
			return (Lawyers) list.get(0);

	}

	/**
	 * 判断这个执业证号在所在律协中是否存在
	 * 
	 * @param lawyerno
	 * @param cityid
	 * @return
	 */
	public boolean isexist(String lawyerno, int cityid) {
		DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class);
		dc.add(Restrictions.eq("lawyerno", lawyerno));
		dc.add(Restrictions.eq("directunion", cityid));
		List list = this.findAllByCriteria(dc);
		if (list == null || list.size() == 0)
			return false;
		else
			return true;
	}

	/**
	 * 
	 * 得到省、市、事务所的律师数
	 * @param field
	 * @param fieldvalue
	 * @return
	 */
	public int getFieldLawyerCnt(String field, int fieldvalue) {
		DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class);
		dc.setProjection(org.hibernate.criterion.Projections.count("lawyerid"));
		if (field != null && !field.equals("")) {
			dc.add(Restrictions.eq(field, fieldvalue));
		}
		List list = this.findAllByCriteria(dc);
		if (list != null && list.size() > 0)
			return Integer.parseInt(list.get(0).toString());
		return 0;
	}

	public int getOfficeLawyerCnt(int officeid) {
		DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class);
		dc.setProjection(org.hibernate.criterion.Projections.count("lawyerid"));
		dc.add(Restrictions.eq("theoffice", officeid));
		List list = this.findAllByCriteria(dc);

		if (list != null && list.size() > 0)
			return Integer.parseInt(list.get(0).toString());
		return 0;
	}

	public int getCityLwyerCnt(int cityid) {
		DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class);
		dc.setProjection(org.hibernate.criterion.Projections.count("lawyerid"));
		dc.add(Restrictions.eq("directunion", cityid));
		List list = this.findAllByCriteria(dc);

		if (list != null && list.size() > 0)
			return Integer.parseInt(list.get(0).toString());
		return 0;
	}

	public int getProvinceLwyerCnt(int provinceid) {
		DetachedCriteria dc = DetachedCriteria.forClass(Lawyers.class);
		dc.setProjection(org.hibernate.criterion.Projections.count("lawyerid"));
		dc.add(Restrictions.eq("provinceunion", provinceid));
		List list = this.findAllByCriteria(dc);

		if (list != null && list.size() > 0)
			return Integer.parseInt(list.get(0).toString());
		return 0;
	}

	private Lawyers lawyers;

	public Lawyers getLawyers() {
		return this.lawyers;
	}
}