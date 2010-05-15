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
import com.changpeng.models.Lawyers;
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
public class LawyersListAction extends AbstractListAction {

	private String resultType = "list";
	// private String loginname;
	private String lawyername;
	private String lawyerno;
	private String certno;
	private String cardno;

	// private String groupname;

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

	/**
	 * @return the lawyerno
	 */
	public String getLawyerno() {
		return lawyerno;
	}

	/**
	 * @param lawyerno
	 *            the lawyerno to set
	 */
	public void setLawyerno(String lawyerno) {
		this.lawyerno = lawyerno;
	}

	/**
	 * @return the certno
	 */
	public String getCertno() {
		return certno;
	}

	/**
	 * @param certno
	 *            the certno to set
	 */
	public void setCertno(String certno) {
		this.certno = certno;
	}

	/**
	 * @return the cardno
	 */
	public String getCardno() {
		return cardno;
	}

	/**
	 * @param cardno
	 *            the cardno to set
	 */
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public LawyersListAction() {
		this.datavisible = new DataVisible();
	}

	private boolean candel;
	private boolean canupd;
	private boolean canins;

	/**
	 * @return the candel
	 */
	public boolean getCandel() {
		return candel;
	}

	/**
	 * @return the canupd
	 */
	public boolean getCanupd() {
		return canupd;
	}

	/**
	 * @return the canins
	 */
	public boolean getCanins() {
		return canins;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		candel = super.getLoginUser().hasRight("lawyersDelete");
		canins = super.getLoginUser().hasRight("lawyersCreateEditPre");
		canupd = super.getLoginUser().hasRight("lawyersCreateEditPre");

		CommonDatas.getGroups();
		this.datavisible.getVisibleDatas(this.getLoginUser(), false);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Lawyers.class).add(Restrictions.ge("lawyertype", 0));;
		System.out.println("0002552187:::" + cardno);

		// if (loginname != null && !"".equals(loginname))
		// detachedCriteria.add(Restrictions.like("loginname", loginname,
		// MatchMode.START));
//将实习律师的显示和律师的显示合并在一起
//		detachedCriteria.add(Restrictions.eq("lawyertype", lawyertype));
		if (lawyername != null && !"".equals(lawyername))
			detachedCriteria.add(Restrictions.like("lawyername", lawyername, MatchMode.START));
		if (cardno != null && !"".equals(cardno))
			detachedCriteria.add(Restrictions.eq("cardno", cardno));
		if (certno != null && !"".equals(certno))
			detachedCriteria.add(Restrictions.eq("certno", certno));
		if (lawyerno != null && !"".equals(lawyerno))
			detachedCriteria.add(Restrictions.eq("lawyerno", lawyerno));
		if (systemno != null && !"".equals(systemno))
			detachedCriteria.add(Restrictions.eq("systemno", systemno));
		if(hascardno==1){//1未分配卡
			detachedCriteria.add(Restrictions.isNull("cardno"));
		}else if(hascardno==2){//有卡
			detachedCriteria.add(Restrictions.isNotNull("cardno"));
		}
		
		SysRole role = this.getLoginUser().getSysRole();
		if (role != null) {
			Set<SysRoleVisible> rolevisibles = role.getSysRoleVisibles();
			SysRoleVisible rolevisible = null;
			for (SysRoleVisible v : rolevisibles) {

				if (v.getThetable().equalsIgnoreCase("lawyers")) {
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
		
		
		if(resultType!=null&&resultType.equals("cardnolist")){
			detachedCriteria.addOrder(Order.desc("systemno"));
		}else{
			detachedCriteria.addOrder(Order.desc("lawyerid"));
		}
		
		BasicService service = (BasicService) getBean("basicService");

		if (resultType.equals("list")) {
			this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
			return SUCCESS;
		} else if (resultType.equals("excel")) {
			this.page = service.findPageByCriteria(detachedCriteria, Integer.MAX_VALUE, 1);
			return "excel";
		} else {
			this.page = service.findPageByCriteria(detachedCriteria, pageSize, pageNo);
			// System.out.println(resultType);
			return "cardnolist";
		}
	}

	//是否有卡
private int hascardno;

	/**
 * @return the hascardno
 */
public int getHascardno() {
	return hascardno;
}

/**
 * @param hascardno the hascardno to set
 */
public void setHascardno(int hascardno) {
	this.hascardno = hascardno;
}

	/**
	 * @return the lawyername
	 */
	public String getLawyername() {
		return lawyername;
	}

	/**
	 * @param lawyername
	 *            the lawyername to set
	 */
	public void setLawyername(String lawyername) {
		this.lawyername = lawyername;
	}

	private String systemno;

	/**
	 * @return the systemno
	 */
	public String getSystemno() {
		return systemno;
	}

	/**
	 * @param systemno
	 *            the systemno to set
	 */
	public void setSystemno(String systemno) {
		this.systemno = systemno;
	}

}