package com.changpeng.diaocha.action;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Diaocha;
import com.changpeng.models.Diaochatype;
import com.changpeng.models.Diaochawenti;
import com.opensymphony.xwork2.ActionContext;


public class DiaochaViewAction extends AbstractAction{
	private boolean hasreply;
	private int diaochaid;
	private Set<Diaochawenti> wentilist=new HashSet<Diaochawenti>(0);
	private Diaocha diaocha;
	
	private boolean hastype;
	private Set<Diaochatype> diaochatypes=new HashSet<Diaochatype>(0);
	
	
	public boolean getHasreply(){
		return hasreply;
	}
	public Set<Diaochawenti> getWentilist() {
		return wentilist;
	}
	public Diaocha getDiaocha() {
		return diaocha;
	}
	
	public Set<Diaochatype> getDiaochatypes(){
		return diaochatypes;
	}
	public boolean getHastype(){
		return hastype;
	}
	
	private BasicService service;
	public DiaochaViewAction(){
	    service=(BasicService) this.getBean("basicService");
//		this.rightCode="diaochareply";
	    this.needsession=false;
	}
	
	public String input() throws Exception{
		
		//diaocha=(Diaocha)service.get(Diaocha.class, diaochaid);
		
		
		hasreply=false;
		BasicService service=(BasicService) this.getBean("basicService");
		List list = service
				.find("from Diaochareply where diaochawenti.diaocha.diaochaid="
						+ diaochaid + " and replyuser='"
						+ getLoginUser().getLoginname() + "'");
		if (list.size() > 0) {
			hasreply=true;
		}
		return "input";
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected String go() throws Exception {
		
		diaocha=(Diaocha)service.get(Diaocha.class, diaochaid);
		diaochatypes=diaocha.getDiaochatypes();
		
		if(diaochatypes!=null&&diaochatypes.size()>0)
			hastype=true;
		
		/*DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Diaocha.class);	
		
		detachedCriteria.add(Restrictions.eq("state", 2));
	
		
		detachedCriteria.setProjection(Projections.max("diaochaid"));
		PaginationSupport page = service.findPageByCriteria(detachedCriteria);
		
		//没有数据时该页面会报空指针错误，具体再怎么改下好		
		if(page!=null&&page.getTotalCount()>0){		
			//if(page.getItems().get(0)!=null)
			diaocha=(Diaocha)service.get(Diaocha.class, Integer.parseInt(page.getItems().get(0).toString()));
		}else{
			this.message="暂时未有调查。";
			return "message";
		}
		*/
		/*detachedCriteria = DetachedCriteria.forClass(Diaochawenti.class);
		detachedCriteria.add(Restrictions.eq("diaocha.diaochaid", diaocha.getDiaochaid()));
		detachedCriteria.addOrder(Order.asc("wentiid"));
		wentilist=service.findAllByCriteria(detachedCriteria);*/
		wentilist=diaocha.getDiaochawentis();
		if(wentilist==null||wentilist.size()==0){
			this.message="暂未有调查内容";
			return "message";
		}
		
		
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
//		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		
		
		String ip = getIpAddr(request);
		
		
		String cookievalue=com.changpeng.common.util.MD5.md5(diaochaid+"");//参与完毕后，写个cookie信息进去
		String reqcookie="";
		String cookiename="hasreplied"+diaochaid;
		Cookie[] cookies = request.getCookies();
		int length = cookies == null ? 0 : cookies.length;
		for (int i = 0; i < length; i++) {
			if (cookies[i].getName().equals(cookiename)) {
				reqcookie= cookies[i].getValue();
			}
		}
		
		BasicService service = (BasicService) this.getBean("basicService");
		
		List list=null;
		if(!reqcookie.equals("")){
		 list = service
		.find("from Diaochareply where diaochawenti.diaocha.diaochaid="
				+ diaochaid + " and ip='"
				+ ip + "' and cookie='"+reqcookie+"'");
		}
		System.out.println("查看问卷数据信息："+cookiename+","+cookievalue+","+reqcookie+","+ip);
		
		if (list!=null&&list.size() > 0) {
			this.message = "您已经参与过该调查,点返回查看调查结果";
//			this.nextPage="diaochaStat.pl?diaochaid="+diaochaid;
//			return "message";
			hasjoin=true;
		}
		
		
		return SUCCESS;
	}
	private boolean hasjoin;
	public boolean getHashjoin(){
		return hasjoin;
	}
	public void setDiaochaid(int diaochaid) {
		this.diaochaid = diaochaid;
	}
	public int getDiaochaid() {
		return diaochaid;
	}

}
