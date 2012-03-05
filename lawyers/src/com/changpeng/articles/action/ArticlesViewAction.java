/**
 * ForumCreateAction.java
 */

package com.changpeng.articles.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Articles;
import com.changpeng.models.Lawyers;
	
/**
 * 看主题帖
 * 
 * @author 华锋 2008-5-5 下午06:36:36
 * 
 */
public class ArticlesViewAction extends AbstractAction {
	public String getTopbarpic(){
		return com.changpeng.common.Constants.TOP_BAR_PIC;
	}
	private Lawyers lawyer;
	public Lawyers getLawyer() {
		return lawyer;
	}
	
	/**
	 * 主题帖的id
	 */
	private int articleid;

	public int getArticleid() {
		return articleid;
	}
	public void setType(int type) {
		this.type = type;
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
		this.lawyer = this.getLoginUser();
		BasicService basicService = (BasicService) getBean("basicService");
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
	

	private int type;

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

}
