/**
 * SysLogService.java
 */

package com.changpeng.system.service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysGroupExcludeRights;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysUser;
import com.changpeng.system.dao.SysGroupDAO;
import com.changpeng.system.dao.SysUserDAO;

/**
 * @author 华锋 2008-2-26 下午02:23:53
 * 
 */
public class SysGroupService extends BasicService {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private SysGroupDAO sysGroupDAO;
	private SysUserDAO sysUserDAO;

	private static Log _LOG = LogFactory.getLog(SysGroupService.class);

	/**
	 * @param sysGroupDAO
	 *            the sysGroupDAO to set
	 */
	public void setSysGroupDAO(SysGroupDAO sysGroupDAO) {
		this.sysGroupDAO = sysGroupDAO;
	}

	/**
	 * 得到直接下属
	 * 
	 * @param parentid
	 * @return
	 */
	public List getChildGroup(int parentid) {

		DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class);
		dc.add(Restrictions.eq("parentid", parentid)).add(Restrictions.eq("delflag", false)); // 省律协
		return sysGroupDAO.findAllByCriteria(dc);
	}

	/**
	 * 得到所有的省律协
	 * 
	 * @return
	 */
	public List getProvinceUnion() {
		return getProvinceUnion(false);
	}

	public List<String> addGroupBatch(SysUser sysUser, int parentid, int directgroup, List<SysGroup> grouplist)
			throws ServiceException {
		List<String> result = new ArrayList<String>();
		try {
			List<String> oldlawyerno = new ArrayList<String>();

			java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
			for (SysGroup group : grouplist) {

				StringBuffer sb = new StringBuffer();

				group.setComments("批量导入");
				group.setCreatetime(timestamp);
				group.setCreatetype(3);
				group.setCreateuser(sysUser.getUsername());
				group.setGrouplevel(3);
				group.setGrouptype(1);
				group.setParentid(parentid);
				group.setDirectgroup(directgroup);
				group.setCreateuserid(sysUser.getUserid());

				if (oldlawyerno.contains(group.getGroupenname())) {
					result.add("第" + group.getExcelline() + "行错误:执业证号在此文件中已经存在:" + group.getGroupenname() + "|||");
					continue;
				}
				oldlawyerno.add(group.getGroupenname());

				if (sb.toString().length() == 0) {
					try {
						addTheOffice(group);
					} catch (Exception e) {
						e.printStackTrace();
						result.add("第" + group.getExcelline() + "行新增错误::" + e.getMessage());
					}
				} else {

					result.add("第" + group.getExcelline() + "行错误::" + sb);
				}
			}
			return result;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 修改事务所信息
	 * 
	 * 如果新旧一致的话，就不管了
	 * 
	 * @param theoffice
	 */
	public int updateTheOffice(String oldloginname, SysGroup theoffice) {
		sysGroupDAO.update(theoffice);
		// 不管,如果新的登录名不存在,不管,直接新增一个。也就是这个事务所2个都能用

		if (oldloginname == null || theoffice.getGroupenname() == null)
			return 0;
		if (!oldloginname.equals(theoffice.getGroupenname())) {

			SysUser olduser = (SysUser) sysUserDAO.getSysUserByLoginname(oldloginname);
			SysUser newuser = (SysUser) sysUserDAO.getSysUserByLoginname(theoffice.getGroupenname());
			if (olduser != null && newuser == null) {// 老的有新的没有
				olduser.setLoginname(theoffice.getGroupenname());
				olduser.setPassword(com.changpeng.common.util.MD5.md5("123456"));//密码初始化为123456
				sysUserDAO.update(olduser);
				return 0;
			} else if (olduser == null && newuser == null) { // 新增

				SysUser user = new SysUser();
				user.setCityid(theoffice.getParentid());
				user.setSysGroup(theoffice);
				user.setComments("随事务所一起新增");
				user.setCreatetime(theoffice.getCreatetime());
				user.setCreateuser(theoffice.getCreateuser());
				user.setCreateuserid(theoffice.getCreateuserid());
				user.setLoginname(theoffice.getGroupenname());
				user.setOfficeid(theoffice.getGroupid());
				SysRole role = new SysRole();
				role.setRoleid(1);
				user.setSysRole(role);
//				user.setPassword(com.changpeng.common.util.MD5.md5(user.getLoginname()));
				user.setPassword(com.changpeng.common.util.MD5.md5("123456"));
				user.setProvinceid(theoffice.getDirectgroup());
				user.setStatus(0);
				user.setUsername(theoffice.getGroupname());
				sysGroupDAO.save(user);
				return 3;
			}

			else if (olduser != null && newuser != null && newuser.getOfficeid() != theoffice.getGroupid()) { // 新的登录名有了，但不是同一个事务所的。修改为同一个事务所
				newuser.setSysGroup(theoffice);
				newuser.setComments(newuser.getComments() + "|这个帐号之前归属事务所:" + newuser.getOfficeid() + ".修改为这个事务所");
				newuser.setOfficeid(theoffice.getGroupid());
				newuser.setCityid(theoffice.getParentid());
				newuser.setUsername(theoffice.getGroupname());
				newuser.setProvinceid(theoffice.getDirectgroup());
				sysUserDAO.update(newuser);
				return 1;
			}

			return 2;
		}
		return 0;

	}

	@Transactional
	public int addTheOffice(SysGroup group) {
		sysGroupDAO.save(group);
		SysUser user = (SysUser) sysUserDAO.getSysUserByLoginname(group.getGroupenname());
		if (user == null) {
			user = new SysUser();
			user.setCityid(group.getParentid());
			user.setSysGroup(group);
			user.setComments("随事务所一起新增");
			user.setCreatetime(group.getCreatetime());
			user.setCreateuser(group.getCreateuser());
			user.setCreateuserid(group.getCreateuserid());
			user.setLoginname(group.getGroupenname());
			user.setOfficeid(group.getGroupid());
			SysRole role = new SysRole();
			role.setRoleid(1);
			user.setSysRole(role);
//			user.setPassword(com.changpeng.common.util.MD5.md5(user.getLoginname()));
			user.setPassword(com.changpeng.common.util.MD5.md5("123456"));
			user.setProvinceid(group.getDirectgroup());
			user.setStatus(0);
			user.setUsername(group.getGroupname());
			sysGroupDAO.save(user);
			return 0;
		} else {
			if (user.getOfficeid() != group.getGroupid()) { // 新的登录名有了，但不是同一个事务所的。修改为同一个事务所

				user.setComments(user.getComments() + "|这个帐号之前归属事务所:" + user.getOfficeid() + ".修改为这个事务所");
				user.setOfficeid(group.getGroupid());
				user.setCityid(group.getParentid());
				user.setProvinceid(group.getDirectgroup());
				sysUserDAO.update(user);
				return 1;
			}
			return 2;
		}
	}

	public List getProvinceUnion(boolean includeother) {
		DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class).add(Restrictions.eq("delflag", false));
		;
		if (!includeother)
			dc.add(Restrictions.eq("grouptype", 3)); // 省律协
		else
			dc.add(Restrictions.in("grouptype", new Object[] { 3, 4, 5 }));
		return sysGroupDAO.findAllByCriteria(dc);
	}

	/**
	 * 得到某个省下的所有市律协
	 * 
	 * @param provinceunion
	 * @return
	 */
	public List getCityUnion(int provinceunion) {
		DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class).add(Restrictions.eq("delflag", false));
		;
		dc.add(Restrictions.eq("grouptype", 2)); // 市律协
		if (provinceunion != 0)
			dc.add(Restrictions.eq("parentid", provinceunion)); //
		return sysGroupDAO.findAllByCriteria(dc);
	}

	/**
	 * 得到系统里面所有购买系统的律协的名单
	 * 
	 * @return
	 */
	public List getAllsharedunion() {
		DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class);
		Criterion createtype = Restrictions.eq("createtype", -1);// 购买了系统的
		Criterion grouptype = Restrictions.eq("grouptype", 3);// 所有的省律协

		dc.add(Restrictions.or(createtype, grouptype));
		dc.add(Restrictions.eq("delflag", false));

		sysGroupDAO.setCriteriaSpecification(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		return sysGroupDAO.findAllByCriteria(dc);
	}

	/**
	 * 得到某个市下的所有事务所
	 * 
	 * @param cityunicon
	 * @return
	 */
	public List getOffices(int cityunicon) {
		DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class).add(Restrictions.eq("delflag", false));
		;
		dc.add(Restrictions.eq("grouptype", 1)); // 事务所
		if (cityunicon != 0)
			dc.add(Restrictions.eq("parentid", cityunicon)); // 事务所
		return sysGroupDAO.findAllByCriteria(dc);
	}

	public SysGroup getGroupBySystemno(String systemno) {
		DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class).add(Restrictions.eq("delflag", false));
		;
		dc.add(Restrictions.eq("systemno", systemno));
		List list = sysGroupDAO.findAllByCriteria(dc);
		if (list != null && list.size() != 0) {
			return (SysGroup) list.get(0);
		}
		return null;
	}

	@Transactional
	public void excludeRrights(int groupid, List excluedRightcode, List recursion, List remarks)
			throws ServiceException {
		String hql = "delete from sys_group_exclude_rights where groupid=" + groupid;
		sysGroupDAO.executeSql(hql);
		for (int i = 0; excluedRightcode != null && i < excluedRightcode.size(); i++) {

			SysGroupExcludeRights ser = new SysGroupExcludeRights();
			ser.setGroupid(groupid);
			ser.setRecursion(Byte.parseByte(recursion.get(i).toString()));
			ser.setRemarks(remarks.get(i).toString());
			ser.setRightcode(excluedRightcode.get(i).toString());
			sysGroupDAO.save(ser);

		}

	}

	@Transactional
	public void add(int groupid, List excluedRightcode, List recursion, List remarks) throws ServiceException {
		String hql = "delete from sys_group_exclude_rights where groupid=" + groupid;
		sysGroupDAO.executeSql(hql);
		for (int i = 0; excluedRightcode != null && i < excluedRightcode.size(); i++) {

			SysGroupExcludeRights ser = new SysGroupExcludeRights();
			ser.setGroupid(groupid);
			ser.setRecursion(Byte.parseByte(recursion.get(i).toString()));
			ser.setRemarks(remarks.get(i).toString());
			ser.setRightcode(excluedRightcode.get(i).toString());
			sysGroupDAO.save(ser);

		}

	}

	public List getExcludedRights(int groupid) {
		DetachedCriteria dc = DetachedCriteria.forClass(SysGroupExcludeRights.class).add(
				Restrictions.eq("groupid", groupid));
		List excludedRights = sysGroupDAO.findAllByCriteria(dc);
		return excludedRights;
	}

	@Transactional
	public int deleteGroup(int groupid) throws ServiceException {

		try {

			SysGroup group = (SysGroup) sysGroupDAO.get(SysGroup.class, groupid);
			Set<SysUser> users = group.getSysUsers();
			if (users != null && users.size() != 0) {
				return -1;// 下面有人员呢,不能删除
			}
			List list = getChildGroup(groupid);
			if (list != null && list.size() != 0)
				return -2;// 下面要是有下级部门的话,也是不能删除的
			sysGroupDAO.delete(group);
			return 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @param sysUserDAO
	 *            the sysUserDAO to set
	 */
	public void setSysUserDAO(SysUserDAO sysUserDAO) {
		this.sysUserDAO = sysUserDAO;
	}
}