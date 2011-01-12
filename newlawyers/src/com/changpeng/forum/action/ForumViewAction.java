/**
 * ForumCreateAction.java
 */

package com.changpeng.forum.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Forum;

/**
 * 看主题帖
 * 
 * @author 华锋 2008-5-5 下午06:36:36
 * 
 */
public class ForumViewAction extends AbstractAction {
	BasicService basicService = null;

	public ForumViewAction() {
		basicService = (BasicService) getBean("basicService");
	}

	/**
	 * 主题帖的id
	 */
	private int mainforumid;

	/**
	 * @return the mainforum
	 */
	public int getMainforumid() {
		return mainforumid;
	}

	/**
	 * @param mainforumid
	 *            the mainforumid to set
	 */
	public void setMainforumid(int forumid) {
		this.mainforumid = forumid;
	}

	/**
	 * 看主题贴和回帖
	 */
	public String go() throws Exception {
		
		
		this.themainforum = (Forum) basicService.get(Forum.class, mainforumid);

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Forum.class);
		detachedCriteria.add(Restrictions.eq("mainforum", mainforumid));
		detachedCriteria.addOrder(Order.desc("lastupdate"));
		this.forumlist = basicService.findAllByCriteria(detachedCriteria);

		return SUCCESS;
	}

	private List forumlist;

	public List getForumlist() {
		return this.forumlist;
	}

	private Forum themainforum;

	/**
	 * @return the mainforum
	 */
	public Forum getThemainforum() {
		return themainforum;
	}
	
	private boolean hasright=true;
	public boolean getHasright(){
		return this.hasright;
	}
}
