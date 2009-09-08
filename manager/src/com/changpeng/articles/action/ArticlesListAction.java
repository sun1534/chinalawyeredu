/**
 * ForumAction.java
 */

package com.changpeng.articles.action;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.Articles;
import com.changpeng.models.SysGroup;

/**
 * 
 * 管理系统看处看到的通知或者公告
 * 
 * @author 华锋 2008-5-5 下午06:20:11
 * 
 */
public class ArticlesListAction extends AbstractListAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		BasicService basic = (BasicService) getBean("basicService");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Articles.class);
//		detachedCriteria.add(Restrictions.eq("status", 0));
		detachedCriteria.add(Restrictions.eq("type", type));
		
		this.mygroup = this.getLoginUser().getSysGroup();

		if (mygroup != null && type == 1) { // 重要通知才搞这个。那个什么帮助就都显示了
//			System.out.println("==========================="+mygroup.getGroupid());
//			Criterion province = Restrictions.eq("provinceid", this.getLoginUser().getProvinceid());
			Criterion province=null;
			if(mygroup.getGrouptype()==1){ //事务所的可以看到省和市的
				province=Restrictions.in("thegroup", new Integer[]{mygroup.getGroupid(),mygroup.getParentid(),mygroup.getDirectgroup(),0});
			}else if(mygroup.getGrouptype()==2){//市级律协的
				province=Restrictions.in("thegroup", new Integer[]{mygroup.getParentid(),mygroup.getGroupid(),0});
			
			}else if(mygroup.getGrouptype()==3){
				province=Restrictions.in("thegroup", new Integer[]{ mygroup.getGroupid(),0});
			}
//			Criterion noprovince = Restrictions.eq("provinceid", 0);
			// 显示某个律协的，这里要判断下登录的人是哪里的，有个可见性的设置
//			detachedCriteria.add(Restrictions.or(province, noprovince));
			Criterion c = Restrictions.eq("provinceid", 0);
			if (province != null) {
				detachedCriteria.add(Restrictions.or(c, province));
			} else
				detachedCriteria.add(c);
		}

		// 按现实顺序来
		detachedCriteria.addOrder(Order.desc("listorder"));
		detachedCriteria.addOrder(Order.desc("createtime"));

		this.page = basic.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	private SysGroup mygroup;
	public SysGroup getMygroup(){
		return this.mygroup;
	}

	private int type;

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