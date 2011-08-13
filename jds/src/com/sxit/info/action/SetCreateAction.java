package com.sxit.info.action;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.*;
import com.sxit.system.model.TsysDepartment;
import com.sxit.system.model.TsysFunctionUser;
import com.sxit.system.model.TsysFunctionRole;
import com.sxit.system.model.TsysUser;
import com.sxit.system.model.TsysUserRole;

/**
 *
 * <p>功能： 创建权限</p>
 * <p>作者： 吴桂荣</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2008-08-28</p>
 * @版本： V1.0
 * @修改：
 */

public class SetCreateAction extends AbstractAction {

	private TinfSet set;
	private List userlist;
	private List typeList;
	private String depstr = "";
	private List powerlist ;
	private int powerid=0;

	public SetCreateAction() {
           rights="inf5,2";
		set = new TinfSet();
	}

	public String go() throws HibernateException {
		
		Criteria criteria = getSession().createCriteria(TinfSet.class);
		criteria.add(Expression.eq("userid",set.getUserid()));
		LOG.debug("----------------------0");
		criteria.createCriteria("tinfType").add(
				Expression.eq("typeid", set.getTinfType().getTypeid()));
		LOG.debug("----------------------1");
		if(criteria.list().size()!=0){
			nextpage="setList.action";
			message="本类别该用户已存在";
			LOG.debug("----------------------2");
			return ERROR;
		}
		
		TsysUser user = (TsysUser)getSession().get(TsysUser.class,set.getUserid());
		set.setUsername(user.getUsername());
		set.setPowerid(setPower(powerlist));
		getSession().save(set);
		set("set", set);
                nextpage="setList.action";
                message="保存成功！";
		return SUCCESS;
	}
	
    public String input() throws Exception {
    	Criteria criteria = getSession().createCriteria(TinfType.class);
		criteria.addOrder(Order.asc("typeid"));
		typeList = criteria.list();
		set("powerlist",powerlist);
//		if(powerid==0){
//			userlist = getQuery2().list();
//		}else{
			userlist = getQuery3().list();
//		}
            return "input";
    }
    
//    private String tempstr = "";

    
//    private Query getQuery2() throws HibernateException {
//		String queryName;
//		queryName = "from TsysUser where statusid=1 order by username desc";
//		Query query = getSession().createQuery(queryName);
//		return query;
//	}

	private Query getQuery3() throws HibernateException {
		LOG.debug("-=-=-=-=-=-=-=");
		String queryName;
		String queryName1;
		String queryName2;
		queryName = "from TsysFunctionUser where comp_id.tsysFunction.functionid like 'inf%'";
		Query query = getSession().createQuery(queryName);
		queryName1 = "from TsysFunctionRole where comp_id.tsysFunction.functionid like 'inf%'";
		Query query1 = getSession().createQuery(queryName1);
		List list = query.list();
		List list1 = query1.list();
		Long[][] userIDs = new Long[list1.size()][];
		for (int i = 0; i < list1.size(); i++) {
			int roleid=((TsysFunctionRole) list1.get(i)).getComp_id().getTsysRole().getRoleid();
			queryName2="from TsysUserRole where comp_id.tsysRole.roleid="+roleid;
			Query query2 = getSession().createQuery(queryName2);
			List list2 = query2.list();
			userIDs[i] = new Long[list2.size()];
			for(int j=0;j<list2.size();j++){
				userIDs[i][j]=((TsysUserRole)list2.get(j)).getComp_id().getTsysUser().getUserid();
				LOG.debug("---------------------------"+userIDs[i][j]);
			}
		}
		int len = 0;
		for(int m = 0;m<userIDs.length;m++){
			len += userIDs[m].length;
		}
		Long[] userids = new Long[list.size()+len];
		int i ;
		for ( i = 0; i < list.size(); i++) {
			userids[i] = ((TsysFunctionUser) list.get(i)).getComp_id()
					.getTsysUser().getUserid();
			LOG.debug("====================="+userids[i]);
		}
		for (int m = 0;m<userIDs.length;m++){
			for (int n = 0;n<userIDs[m].length;n++){
				userids[i++] = userIDs[m][n];
				LOG.debug("[m][n]==============["+m+"]["+n+"]" );
				LOG.debug("userids[i+m+n] ==================="+userids[i-1]);
			}
		}
		queryName = "from TsysUser where statusid=1 and userid in (:userids) order by username desc";
		Query query3 = getSession().createQuery(queryName).setParameterList(
				"userids", userids);
		return query3;
	}
	
	public Long setPower(List list){
		Long p=(long)0;
		if(list!=null){
			int i;
			for (i = 0; i < list.size(); i++) {
				if (list.get(i) != null && !list.get(i).equals("")) {
					p=p+Long.valueOf(list.get(i).toString());
				}
			}
		}
		return p;
	}

	public TinfSet getSet() {
		return set;
	}
	
	public List getUserlist(){
		return this.userlist;
	}
	
	public List  getPowerlist(){
		return this.powerlist ;
	}
	
	public void setPowerlist(List powerlist){
		this.powerlist = powerlist;
	}

	public List getTypeList() {
		return typeList;
	}

	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}

	public int getPowerid() {
		return powerid;
	}

	public void setPowerid(int powerid) {
		this.powerid = powerid;
	}
	
}
