/**
 * ForumCreateAction.java
 */
package com.changpeng.articles.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Articles;

/**
 * 新增或者回复帖子
 * 
 * @author 华锋
 * 2008-5-5 下午06:36:36
 *
 */
public class ArticlesDeleteAction extends AbstractAction{
	BasicService basicService = null;
	
	
	public ArticlesDeleteAction(){
		basicService = (BasicService) getBean("basicService");
	}
	
	/**
	 * 回复帖子的帖子id
	 */
	private int articleid;
	
	
	/**
	 * @param mainforumid the mainforumid to set
	 */
	public void setArticleid(int forumid) {
		this.articleid = forumid;
	}
	

	public int getArticleid() {
		return this.articleid;
	}
	
	private int type;

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 删除帖子，如果是主题帖，删除所有跟帖
	 * @return
	 * @throws Exception
	 */
	public String go()throws Exception{
		
		Articles articles=(Articles)basicService.get(Articles.class, articleid);
	
		basicService.delete(articles);
		this.message="删除成功";
		this.nextPage="articlesList.pl?type="+type;

		return SUCCESS;
	}


	
	
}
