
package com.changpeng.diaocha.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.diaocha.service.DiaochaService;
import com.changpeng.models.Diaocha;
import com.changpeng.models.Diaochatype;

public class DiaochaEditAction extends AbstractAction {
	private int diaochaid;
	private BasicService service;
	private List<String> typename;

	private Diaocha diaocha;

	private List<Diaochatype> diaochatypes;// = new HashSet<Diaochatype>(0);
	private boolean hastype;

	public void setDiaochaid(int diaochaid) {
		this.diaochaid = diaochaid;
	}

	public Diaocha getDiaocha() {
		if (diaocha == null)
			diaocha = (Diaocha) get("diaocha");
		return diaocha;
	}

	public void setTypename(List<String> typename) {
		this.typename = typename;
	}

	public DiaochaEditAction() {
		service = (BasicService) this.getBean("basicService");
		this.rightCode = "diaocha";
	}

	@Override
	protected String go() throws Exception {
//		String now = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
		/*
		 * System.out.println("now.compareTo(diaocha.getStartdate())::::"+now.compareTo(diaocha.getStartdate()));
		 * if(now.compareTo(diaocha.getStartdate())>=0){ this.message="调查已进行，禁止编辑"; return "message"; }else{
		 */

		service.update(diaocha);

		DiaochaService diaochaService = (DiaochaService) this.getBean("diaochaService");
		diaochaService.saveType(typename, diaocha);

		this.nextPage = "diaochaList.pl";
		this.message = "调查编辑成功";
		return SUCCESS;
		// }

	}

	@SuppressWarnings("unchecked")
	public String input() throws ServiceException {
		Diaocha diaocha = (Diaocha) service.get(Diaocha.class, diaochaid);
		/*
		 * String now=new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()); if(now.compareTo(diaocha.getStartdate())>=0){
		 * this.message="调查已进行，禁止编辑"; return "message"; }else{
		 */
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Diaochatype.class);
		detachedCriteria.add(Restrictions.eq("diaocha.diaochaid", diaochaid));
		detachedCriteria.addOrder(Order.asc("typeid"));

		diaochatypes = service.findAllByCriteria(detachedCriteria);

		// 拥有分类
		if (diaochatypes != null && diaochatypes.size() > 0)
			hastype = true;

		set("diaocha", diaocha);
		return INPUT;
		// }

	}

	public void setDiaocha(Diaocha diaocha) {
		this.diaocha = diaocha;
	}

	public List<Diaochatype> getDiaochatypes() {
		return diaochatypes;
	}

	public boolean isHastype() {
		return hastype;
	}
}
