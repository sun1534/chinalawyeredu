/**
 * ArticlesCreateAction.java
 */

package com.changpeng.articles.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Articles;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUser;

/**
 * 新增或者回复帖子
 * 
 * @author 华锋 2008-5-5 下午06:36:36
 * 
 */
public class ArticlesCreateEditAction extends AbstractAction {

	public ArticlesCreateEditAction() {

	}

	/**
	 * 回复帖子的帖子id
	 */
	private int articleid;

	/**
	 * @return the mainarticles
	 */
	public int getArticleid() {
		return articleid;
	}

	/**
	 * @param articleid
	 *            the articleid to set
	 */
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}

	/**
	 * 对帖子进行新增
	 */
	public String go() throws Exception {

		// 对主题帖的回帖数加1

		BasicService basic = (BasicService) getBean("basicService");
		SysUser user = (SysUser) this.getLoginUser();
		String type = "";

		// debug("status::::::::::::" + articles.getStatus());

		if (get("articleexist") != null && "0".equals(get("articleexist"))) {

			SysGroup mygroup = this.getLoginUser().getSysGroup();

			if (mygroup != null) {
				if (mygroup.getGrouptype() <= 3) {
					articles.setProvinceid(this.getLoginUser().getProvinceid());
					articles.setCityid(this.getLoginUser().getCityid());
					articles.setOfficeid(this.getLoginUser().getOfficeid());
				}
				articles.setThegroup(this.getLoginUser().getSysGroup().getGroupid());
			}

			articles.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			articles.setCreateuserid(user.getUserid());
			articles.setCreateuser(user.getUsername());

			basic.save(articles);
			type = "新增";
		} else {
			type = "修改";
			basic.update(articles);
		}
		if (articles.getType() == 1)
			this.message = "重要通知" + type + "成功";
		else
			this.message = "系统帮助" + type + "成功";
		this.nextPage = "articlesList.pl?type=" + type;
		return SUCCESS;
	}

	public String input() throws Exception {
		BasicService basic = (BasicService) getBean("basicService");
		this.articles = (Articles) basic.get(Articles.class, articleid);

		if (this.articles == null) {
			set("articleexist", "0");
			this.articles = new Articles();
			this.articles.setType(type);
		} else {
			set("articleexist", "1");
		}
		this.type = this.articles.getType();
		set("articles", articles);

		return INPUT;

	}

	private int type;

	private Articles articles;

	/**
	 * @return the articlesadd
	 */
	public Articles getArticles() {
		if (articles == null)
			articles = (Articles) get("articles");
		return articles;
	}

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

}
