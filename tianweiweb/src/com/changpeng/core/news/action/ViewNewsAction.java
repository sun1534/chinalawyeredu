package com.changpeng.core.news.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CoreAdvtisment;
import com.changpeng.core.model.TinfInfo;


public class ViewNewsAction extends AbstractAction {

	private int newsid;
	
	private TinfInfo info;
	
	public ViewNewsAction(){
		this.rightCode="PUBLIC";
	}
	
	@Override
	protected String go() throws Exception {
		info=(TinfInfo)service.get(TinfInfo.class, newsid);
		return SUCCESS;
	}

	public int getNewsid() {
		return newsid;
	}

	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}

	public TinfInfo getInfo() {
		return info;
	}

	public void setInfo(TinfInfo info) {
		this.info = info;
	}

}
