/**
 * SysLogService.java
 */

package com.changpeng.lawyers.service;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUser;

/**
 * @author 华锋 2008-2-26 下午02:23:53
 * 
 */
public class SysGroupService extends BasicService {

	private BasicDAO basicDAO;

	private static Log _LOG = LogFactory.getLog(SysGroupService.class);

	
	/**
	 * @param basicDAO the basicDAO to set
	 */
	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}

	/**
	 * 得到直接下属
	 * 
	 * @param parentid
	 * @return
	 */
	public List getChildGroup(int parentid) {

		DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class);
		dc.add(Restrictions.eq("parentid", parentid)); // 省律协
		return basicDAO.findAllByCriteria(dc);
	}

	/**
	 * 得到所有的省律协
	 * 
	 * @return
	 */
	public List getProvinceUnion() {
		return getProvinceUnion(false);
	}

	public List getProvinceUnion(boolean includeother) {
		DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class);
		if (!includeother)
			dc.add(Restrictions.eq("grouptype", 3)); // 省律协
		else
			dc.add(Restrictions.in("grouptype", new Object[] { 3, 4, 5 }));
		return basicDAO.findAllByCriteria(dc);
	}

	/**
	 * 得到某个省下的所有市律协
	 * 
	 * @param provinceunion
	 * @return
	 */
	public List getCityUnion(int provinceunion) {
		DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class);
		dc.add(Restrictions.eq("grouptype", 2)); // 市律协
		if (provinceunion != 0)
			dc.add(Restrictions.eq("parentid", provinceunion)); //
		return basicDAO.findAllByCriteria(dc);
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
		dc.add(Restrictions.eq("delflag",false));
		basicDAO.setCriteriaSpecification(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		return basicDAO.findAllByCriteria(dc);
	}

	/**
	 * 得到某个市下的所有事务所
	 * 
	 * @param cityunicon
	 * @return
	 */
	public List getOffices(int cityunicon) {
		DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class);
		dc.add(Restrictions.eq("grouptype", 1)); // 事务所
		if (cityunicon != 0)
			dc.add(Restrictions.eq("parentid", cityunicon)); // 事务所
		return basicDAO.findAllByCriteria(dc);
	}

	@Transactional
	public int deleteGroup(int groupid) throws ServiceException {

		try {

			SysGroup group = (SysGroup) basicDAO.get(SysGroup.class, groupid);
			Set<SysUser> users = group.getSysUsers();
			if (users != null && users.size() != 0) {
				return -1;// 下面有人员呢,不能删除
			}
			List list = getChildGroup(groupid);
			if (list != null && list.size() != 0)
				return -2;// 下面要是有下级部门的话,也是不能删除的
			basicDAO.delete(group);
			return 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}