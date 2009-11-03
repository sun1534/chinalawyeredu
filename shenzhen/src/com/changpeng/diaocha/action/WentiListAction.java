package com.changpeng.diaocha.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Diaocha;
import com.changpeng.models.Diaochaoption;
import com.changpeng.models.Diaochatype;
import com.changpeng.models.Diaochawenti;
public class WentiListAction extends AbstractAction{
	private int diaochaid;
	//private Set<Diaochawenti> wentilist;
//	private Set<Diaochawenti> wentilist=new HashSet<Diaochawenti>(0);
	private List wentilist=null;
	private Diaocha diaocha;
	private boolean hastype;
	private Set<Diaochatype> diaochatypes=new HashSet<Diaochatype>(0);
	
	public Diaocha getDiaocha() {
		return diaocha;
	}

	public void setDiaochaid(int diaochaid) {
		this.diaochaid=diaochaid;
	}
	
	/*public Set<Diaochawenti> getWentilist(){
		return wentilist;
	}*/
	public List getWentilist(){
		return wentilist;
	}
	public Set<Diaochatype> getDiaochatypes(){
		return diaochatypes;
	}
	public boolean getHastype(){
		return hastype;
	}
	
	public WentiListAction(){
		this.rightCode="diaocha";
	}
	@SuppressWarnings("unchecked")
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService) this.getBean("basicService");
		
		diaocha=(Diaocha)service.get(Diaocha.class, diaochaid);
		
		/*DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Diaochawenti.class);
		detachedCriteria.add(Restrictions.eq("diaocha.diaochaid", diaochaid));
		
		detachedCriteria.addOrder(Order.asc("wentiid"));
		wentilist=service.findAllByCriteria(detachedCriteria);*/
		//这样子写的话 页面上再获取wenti.options会有问题
		diaocha=(Diaocha)service.get(Diaocha.class, diaochaid);
		diaochatypes=diaocha.getDiaochatypes();
		
		if(diaochatypes!=null&&diaochatypes.size()>0)
			hastype=true;
		
//		wentilist=diaocha.getDiaochawentis();
	
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Diaochawenti.class);
		detachedCriteria.add(Restrictions.eq("diaocha.diaochaid",diaochaid));
		
		detachedCriteria.addOrder(Order.asc("listorder"));
		wentilist=service.findAllByCriteria(detachedCriteria);
		//debug(".................................."+hastype);
		return SUCCESS;
	}
	
	
	public String select() throws Exception {
		BasicService service=(BasicService) this.getBean("basicService");
		
		diaocha=(Diaocha)service.get(Diaocha.class, diaochaid);
		Diaochaoption diaochaoption=(Diaochaoption)service.get(Diaochaoption.class, optionid);
		logicwenti=diaochaoption.getLogicwenti();
		if(logicwenti==null)
			logicwenti=-1;
		diaocha=(Diaocha)service.get(Diaocha.class, diaochaid);
		diaochatypes=diaocha.getDiaochatypes();
		hastype=false;
		if(diaochatypes!=null&&diaochatypes.size()>0)
			hastype=true;
		
//		wentilist=diaocha.getDiaochawentis();
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Diaochawenti.class);
		detachedCriteria.add(Restrictions.eq("diaocha.diaochaid",diaochaid));
		
		detachedCriteria.addOrder(Order.asc("listorder"));
		wentilist=service.findAllByCriteria(detachedCriteria);
		return "select";
	}
	
//设置页面跳转时用到
	private int optionid;
	private Integer logicwenti;
	public int getOptionid() {
		return optionid;
	}
	public void setOptionid(int optionid) {
		this.optionid = optionid;
	}
	public Integer getLogicwenti(){
		return logicwenti;
	}
}
