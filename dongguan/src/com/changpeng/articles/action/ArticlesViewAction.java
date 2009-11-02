/**
 * ForumCreateAction.java
 */

package com.changpeng.articles.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Articles;
import com.changpeng.models.Forum;

/**
 * 看主题帖
 * 
 * @author 华锋 2008-5-5 下午06:36:36
 * 
 */
public class ArticlesViewAction extends AbstractAction {
	BasicService basicService = null;

	public ArticlesViewAction() {
		basicService = (BasicService) getBean("basicService");
	}

	/**
	 * 主题帖的id
	 */
	private int articleid;

	/**
	 * @return the mainforum
	 */
	public int getArticleid() {
		return articleid;
	}

	/**
	 * @param articlesid
	 *            the articlesid to set
	 */
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}

	/**
	 * 看主题贴和回帖
	 */
	public String go() throws Exception {

		this.articles = (Articles) basicService.get(Articles.class, articleid);
		return SUCCESS;
	}

	

	private Articles articles;

	/**
	 * @return the mainforum
	 */
	public Articles getArticles() {
		return articles;
	}

}
