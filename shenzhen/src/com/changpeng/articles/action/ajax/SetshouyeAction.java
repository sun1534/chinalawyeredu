/**
 * SettoshouyeAction.java
 */

package com.changpeng.articles.action.ajax;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Articles;

/**
 * @author 华锋 2008-5-30 下午10:33:19
 * 
 */
public class SetshouyeAction extends AbstractAction {

	public SetshouyeAction() {
		this.needsession = false;
	}

	private String msg;
	private int articlesid;
	private String changeok;
	private String toshouye;

	/**
	 * @return the articlesid
	 */
	public int getArticlesid() {
		return articlesid;
	}

	/**
	 * @param articlesid
	 *            the articlesid to set
	 */
	public void setArticlesid(int articlesid) {
		this.articlesid = articlesid;
	}

	/**
	 * @return the toshouye
	 */
	public String getToshouye() {
		return toshouye;
	}

	/**
	 * @param toshouye
	 *            the toshouye to set
	 */
	public void setToshouye(String toshouye) {
		this.toshouye = toshouye;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @return the changeok
	 */
	public String getChangeok() {
		return changeok;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		try {

			BasicService basicService = (BasicService) getBean("basicService");
			Articles articles = (Articles) basicService.get(Articles.class, articlesid);
			if (this.toshouye.equals("true")) {
				articles.setToshouye(true);
			}
			if (this.toshouye.equals("false")) {
				articles.setToshouye(false);
			}

			basicService.update(articles);
			this.msg = "首页显示修改成功";
			this.changeok = "ok";
		}
		catch (Exception e) {
			this.changeok = "error";
			this.msg = "首页显示修改失败:" + e.getMessage();
		}

		return SUCCESS;
	}

}
