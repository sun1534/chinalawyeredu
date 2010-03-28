/**
 * TSysUserAddAction.java
 */

package com.changpeng.lawyers.action;

import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.LawyersShixi;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysRoleVisible;
import com.changpeng.system.util.CommonDatas;

/**
 * 
 * 用户信息列表 1、列表的部分，只能查看自己组以及下面组的人员，对于有权限的人来说 2、对于group部分,也是同样如此 3、根据用户姓名、登录名进行查询
 * 4、
 * 
 * @author 华锋 2008-2-25 上午11:12:05
 * 
 */
public class LawyersShixiListAction extends AbstractListAction {

	private String resultType = "list";
	private String lawyername;
	private String zigeno;
	private String shixino;
	private String certno;
	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * @param resultType
	 *            the resultType to set
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	


	public LawyersShixiListAction() {
		this.datavisible = new DataVisible();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {


		CommonDatas.getGroups();
		this.datavisible.getVisibleDatas(this.getLoginUser(), false);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LawyersShixi.class);
		
//		if (loginname != null && !"".equals(loginname))
//			detachedCriteria.add(Restrictions.like("loginname", loginname, MatchMode.START));
		if (lawyername != null && !"".equals(lawyername))
			detachedCriteria.add(Restrictions.like("lawyername", lawyername, MatchMode.START));
	
		if (shixino != null && !"".equals(shixino))
			detachedCriteria.add(Restrictions.eq("shixino", shixino));
		if (zigeno != null && !"".equals(zigeno))
			detachedCriteria.add(Restrictions.eq("zigeno", zigeno));
		if (certno != null && !"".equals(certno))
			detachedCriteria.add(Restrictions.eq("certno", certno));
		

		SysRole role = this.getLoginUser().getSysRole();
		if (role != null) {
			Set<SysRoleVisible> rolevisibles = role.getSysRoleVisibles();
			SysRoleVisible rolevisible = null;
			for (SysRoleVisible v : rolevisibles) {

				if (v.getThetable().equalsIgnoreCase("lawyers")) { //跟律师表一样的可见性
					rolevisible = v;
					break;
				}
			}

			// 权限判断了
			if (rolevisible != null) {
				String field = "officeid";
				if (rolevisible.getThefield().equals("theoffice"))
					field = "officeid";
				else if (rolevisible.getThefield().equals("directunion"))
					field = "cityid";
				else if (rolevisible.getThefield().equals("provinceunion"))
					field = "provinceid";

				detachedCriteria.add(Restrictions.eq(rolevisible.getThefield(), PropertyUtils.getProperty(this
						.getLoginUser(), field)));
			}
		}

		if (datavisible.getOfficeid() != 0) {
			detachedCriteria.add(Restrictions.eq("theoffice", datavisible.getOfficeid()));
		} else if (datavisible.getCityid() != 0) {
			detachedCriteria.add(Restrictions.eq("directunion", datavisible.getCityid()));
		} else if (datavisible.getProvinceid() != 0) {
			detachedCriteria.add(Restrictions.eq("provinceunion", datavisible.getProvinceid()));
		}

		// 按userid逆序排序
		detachedCriteria.addOrder(Order.desc("lawyerid"));
		BasicService service = (BasicService) getBean("basicService");

		if (resultType.equals("list")) {
			this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
			return SUCCESS;
		} else if(resultType.equals("excel")) {
			this.page = service.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, 1);
			return "excel";
		}else{
			this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
//			System.out.println(resultType);
			return "cardnolist";
		}
	}

	/**
	 * @return the lawyername
	 */
	public String getLawyername() {
		return lawyername;
	}

	/**
	 * @param lawyername the lawyername to set
	 */
	public void setLawyername(String lawyername) {
		this.lawyername = lawyername;
	}

	/**
	 * @return the shixino
	 */
	public String getShixino() {
		return shixino;
	}

	/**
	 * @param shixino the shixino to set
	 */
	public void setShixino(String shixino) {
		this.shixino = shixino;
	}

	/**
	 * @return the zigeno
	 */
	public String getZigeno() {
		return zigeno;
	}

	/**
	 * @param zigeno the zigeno to set
	 */
	public void setZigeno(String zigeno) {
		this.zigeno = zigeno;
	}

	/**
	 * @return the certno
	 */
	public String getCertno() {
		return certno;
	}

	/**
	 * @param certno the certno to set
	 */
	public void setCertno(String certno) {
		this.certno = certno;
	}
}