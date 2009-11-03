/**
 * ArrangeListAction.java
 */

package com.changpeng.arrange.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Arrange;
import com.changpeng.models.Arrangesignup;
import com.changpeng.models.system.SysUser;

/**
 * 培训的显示，现在分为律协活动和岗位培训，通过arrangetype区分开
 * 
 * @author 华锋 2008-5-5 上午01:15:02
 * 
 */
public class ArrangeListAction extends AbstractAction {

	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	public ArrangeListAction(){
//		this.rightCode="gangqian";
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		BasicService basic = (BasicService) getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Arrange.class);

		detachedCriteria.add(Restrictions.eq("arrangetype", arrangetype));
		detachedCriteria.addOrder(Order.desc("createtime"));
		arrangelist = new ArrayList();
		List list = basic.findAllByCriteria(detachedCriteria);
		int length = list == null ? 0 : list.size();
		for (int i = 0; i < list.size(); i++) {
			Arrange arrange = (Arrange) list.get(i);
			int arrangeid = arrange.getArrangeid();
			
			DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(Arrangesignup.class);
			detachedCriteria1.add(Restrictions.eq("arrange", arrange));
			
			detachedCriteria1.setProjection(Projections.projectionList().add(Projections.rowCount()).add(Projections.countDistinct("groupid")));
			
			List projectionlist=basic.findAllByCriteria(detachedCriteria1);
		      if(projectionlist!=null&&projectionlist.size()!=0){
		    	  Object[] obj=(Object[])projectionlist.get(0);
		    	  arrange.setShiwusuo(Integer.parseInt(obj[1].toString()));
		    	  arrange.setBaomingrenshu(Integer.parseInt(obj[0].toString()));
		      }
			
			
//			Set<Arrangesignup> arrangesignups = arrange.getArrangesignups();
//			arrange.setBaomingrenshu(arrangesignups.size());
//
//			int shiwusuo = 0;
//			Iterator assigups = arrangesignups.iterator();
//			int groupid = 0;
//			while (assigups.hasNext()) {
//				Arrangesignup signup = (Arrangesignup) assigups.next();
//				if (groupid != signup.getGroupid()) {
//					shiwusuo++;
//					groupid = signup.getGroupid();
//				}
//			}

			long end = df.parse(arrange.getArrangend()).getTime();
			if (System.currentTimeMillis() >= end) {
				arrange.setCanbaoming(false);
			}
			else {
				arrange.setCanbaoming(true);
			}
			
			arrangelist.add(arrange);

		}

		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private List arrangelist;

	public List getArrangelist() {
		return this.arrangelist;
	}

	private byte arrangetype;

	/**
	 * @return the arrangetype
	 */
	public byte getArrangetype() {
		return arrangetype;
	}

	/**
	 * @param arrangetype
	 *            the arrangetype to set
	 */
	public void setArrangetype(byte arrangetype) {
		this.arrangetype = arrangetype;
	}
	public SysUser getSysUser(){
		return this.getLoginUser();
	}


}
