/**
 * 
 */
package com.changpeng.core.home;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.core.model.CoreProduct;
import com.changpeng.core.model.TinfInfo;
import com.changpeng.core.user.model.CoreUser;

/**
 * 
 * 1资讯
 * 2产品
 * 3企业
 * 4个人
 * 5活动
 * @author 华锋
 * Jul 15, 2009 10:49:31 PM
 *
 */
public class InfoQueryAction extends AbstractListAction {

	
private int typeid;
private String keyword;
	
public InfoQueryAction(){
	this.rightCode="PUBLIC";
}
public int getTypeid() {
	return typeid;
}

public void setTypeid(int typeid) {
	this.typeid = typeid;
}

public String getKeyword() {
	return keyword;
}

public void setKeyword(String keyword) {
	this.keyword = keyword;
}

	@Override
	protected String go() throws Exception {
//		 * 1资讯
//		 * 2产品
//		 * 3企业
//		 * 4个人
		// 5 活动
		
		
		if(typeid==0)
			typeid=1;
		
		String result="";
		
		DetachedCriteria dc=null;
		
		if(typeid==1){
			dc=DetachedCriteria.forClass(TinfInfo.class);
			dc.add(Restrictions.like("infoname",keyword,MatchMode.ANYWHERE));
			result="info";
			
		}else if(typeid==2){
			dc=DetachedCriteria.forClass(CoreProduct.class);
			dc.add(Restrictions.like("name",keyword,MatchMode.ANYWHERE));
			result="product";
		}else if(typeid==3){
			dc=DetachedCriteria.forClass(CoreUser.class);
			dc.add(Restrictions.like("userName",keyword,MatchMode.ANYWHERE));
			result="user";
		}else if(typeid==4){
			dc=DetachedCriteria.forClass(CoreUser.class);
			dc.add(Restrictions.like("userName",keyword,MatchMode.ANYWHERE));
			result="user";
		}else if(typeid==5){
			dc=DetachedCriteria.forClass(TinfInfo.class);
			dc.add(Restrictions.like("infoname",keyword,MatchMode.ANYWHERE));
			dc.add(Restrictions.eq("typeid",2));
			dc.add(Restrictions.eq("statusid",0));
			result="info";
		}
		BasicService basicService=(BasicService)this.getBean("basicService");
		
		this.page=basicService.findPageOnPageNo(dc, pageSize, pageNo);
		
		return result;

	}
}
