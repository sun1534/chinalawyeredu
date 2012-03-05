/**
 * JifenQueryAction.java
 */

package com.changpeng.jifen.action;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.models.SysGroup;

/**
 * 根据进来的人的不同，进行业务的跳转
 * 
 * @author 华锋 2008-5-5 上午12:08:59
 * 
 */
public class JifenStaticsQueryAction extends AbstractListAction {



	public JifenStaticsQueryAction() {
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractListAction#go()
	 */
	@Override
	protected String go() throws Exception {
	
		SysGroup group=this.getLoginUser().getSysGroup();
		System.out.println("aaaaaaaa:"+group.getGrouptype());
		if(group==null||(group!=null&&group.getGrouptype()>3)){
			return "globals";
		}
		else if(group.getGrouptype()==1){
			return "office";
		}
		else if(group.getGrouptype()==2){
			return "city";
		}else if(group.getGrouptype()==3){
			return "province";
		}else
			return "globals";
	}
}