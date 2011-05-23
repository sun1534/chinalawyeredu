package com.changpeng.diaocha.action;

import java.util.*;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.*;

public class DiaochaStatAction extends AbstractAction{
	private int diaochaid;
	private Diaocha diaocha;
	private List<Diaochawenti> wentilist;
	public List<Diaochawenti> getWentilist() {
		return wentilist;
	}
	public void setDiaochaid(int diaochaid){
		this.diaochaid=diaochaid;
	}
	public Diaocha getDiaocha(){
		return diaocha;
	}
	public DiaochaStatAction(){
//		this.rightCode="diaochareply";
		this.needsession=false;
	}
	@SuppressWarnings("unchecked")
	@Override
	protected String go() throws Exception {
		BasicService service=(BasicService) this.getBean("basicService");
		diaocha=(Diaocha)service.get(Diaocha.class, diaochaid);
		
		//题目类型可为1或2 不晓得这样子怎么写
		/*DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Diaochawenti.class);
		detachedCriteria.add(Restrictions.eq("diaocha.diaochaid", diaocha.getDiaochaid()));
		detachedCriteria.add(Restrictions.eq("wentileixing", "1"));
		detachedCriteria.add(Restrictions.eq("wentileixing", "2"));
		detachedCriteria.addOrder(Order.asc("wentiid"));
		wentilist=service.findPageByCriteria(detachedCriteria).getItems();
		*/
		wentilist=service.find(" from Diaochawenti where diaocha.diaochaid="+diaochaid+" and (wentileixing=1 or wentileixing=2)");
				
		
		return SUCCESS;
	}


}
