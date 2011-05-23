/**
 * LawerCardNoListAction.java
 */
package com.changpeng.system.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.system.SysUser;

/**
 * 律师的卡号管理
 * 只显示律师<br/>
 * 根据姓名，事务所，执业证号和卡号进行查询
 * 
 * @author 华锋
 * 2008-5-5 下午05:17:59
 *
 */
public class LawerCardNoListAction extends AbstractListAction {

	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractListAction#go()
	 */
	public LawerCardNoListAction(){
		this.rightCode="kahaoguanli";
	}
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
//		return null;
		BasicService basicService=(BasicService)getBean("basicService");
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(SysUser.class);
		if (username != null && !"".equals(username) ) {
			detachedCriteria.add(Restrictions.like("username", username, MatchMode.ANYWHERE));
			
		}
		if (lawerno != null && !"".equals(lawerno) ) {
			detachedCriteria.add(Restrictions.like("lawerno", lawerno, MatchMode.ANYWHERE));
			
		}
		if (cardno != null && !"".equals(cardno) ) {
			detachedCriteria.add(Restrictions.like("cardno", cardno, MatchMode.ANYWHERE));
			
		}
		if (certno != null && !"".equals(certno) ) {
			detachedCriteria.add(Restrictions.like("certno", certno, MatchMode.ANYWHERE));
			
		}
		if (groupname != null && !"".equals(groupname)) {
		    detachedCriteria.createAlias("sysGroup", "group").add(
						Restrictions.like("group.groupname", groupname, MatchMode.ANYWHERE));
		}
		
		//只显示律师,不知道对不对，得实践检验一下
//		detachedCriteria.createAlias("sysRoles", "roles").add(Restrictions.eq("roles.roleid", (short)1));
		detachedCriteria.add(Restrictions.eq("roleid",1));
		
		this.page=basicService.findPageByCriteria(detachedCriteria, pageSize, pageNo);
				
		return SUCCESS;
	}
	private  String username;
	private String groupname;
	private String lawerno;
	private String cardno;
	private String certno;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the groupname
	 */
	public String getGroupname() {
		return groupname;
	}
	/**
	 * @param groupname the groupname to set
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	/**
	 * @return the lawerno
	 */
	public String getLawerno() {
		return lawerno;
	}
	/**
	 * @param lawerno the lawerno to set
	 */
	public void setLawerno(String lawerno) {
		this.lawerno = lawerno;
	}
	/**
	 * @return the cardno
	 */
	public String getCardno() {
		return cardno;
	}
	/**
	 * @param cardno the cardno to set
	 */
	public void setCardno(String cardno) {
		this.cardno = cardno;
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
