/**
 * SettoshouyeAction.java
 */
package com.changpeng.articles.action.ajax;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Articles;

/**
 * 改变系统帮助的显示顺序
 * 
 * @author 华锋
 * 2008-5-30 下午10:33:19
 *
 */
public class ChangeorderAction extends AbstractAction {

	public ChangeorderAction(){
		this.needsession=false;
	}
	
	private int listorder;
	private String msg;
	private int articlesid;
	
	
	/**
	 * @param articlesid the articlesid to set
	 */
	public void setArticlesid(int articlesid) {
		this.articlesid = articlesid;
	}


	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}


	/**
	 * @param listorder the listorder to set
	 */
	public void setListorder(int listorder) {
		this.listorder = listorder;
	}


	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		
		try {

			BasicService basicService = (BasicService) getBean("basicService");
			Articles articles = (Articles) basicService.get(Articles.class, articlesid);
			articles.setListorder(listorder);
			basicService.update(articles);
			this.msg = "系统帮助显示顺序修改成功";

		}
		catch (Exception e) {

			this.msg = "系统帮助显示顺序更改失败:" + e.getMessage();
		}

		return SUCCESS;
	}

}
