package com.changpeng.core.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.BasicService;
import com.changpeng.core.user.model.CoreUserVisit;
import com.changpeng.core.user.model.Userinfo;

public class UservisitService extends BasicService {
	private BasicDAO basicDAO;

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}

/**
 * 增加用户访问痕迹
 * @param userid 被访问的人的id
 * @param userrole 被访问的人的角色
 * @param visituserid 访问的人的id
 * @param visitrole 访问的人的角色
 */
	@Transactional
	public void addUservisit(int userid, int visituserid) {
		basicDAO.execute("delete CoreUserVisit where userid=" + userid + " and visitUserid=" + visituserid);
		CoreUserVisit visit = new CoreUserVisit();
		visit.setUserid(userid);
		visit.setVisitUserid(visituserid);
		visit.setVisitTime(new java.sql.Timestamp(System.currentTimeMillis()));
		
		basicDAO.save(visit);
	}


	
/**
 * 查询我的访问痕迹(我看过谁),返回userinfo对象
 * @param srcusrid
 * @return
 */
	public List getMyVisitList(int userid,int count) {
		String sql="select distinct a.userid,a.visit_userid,a.visit_time from core_user_visit a where a.visit_userid="+userid+" order by visit_time desc limit "+count;
		List list=basicDAO.findBySqlQuery(sql);
		int len=list==null?0:list.size();
		List result=new ArrayList();
		for (int i = 0; i < len; i++) {
			CoreUserVisit uservisit = new CoreUserVisit();
			Object[] obj = (Object[]) list.get(i);
			uservisit.setUserid(Integer.parseInt(obj[0].toString()));
			uservisit.setVisitUserid(Integer.parseInt(obj[2].toString()));
			uservisit.setVisitTime((Timestamp)obj[3]);
			result.add(uservisit);
		}
		return result;
	}

/**
 * 查询我被访问的痕迹(谁来看过我)(张星),返回的是userinfo对象
 * @param userid
 * @return
 */
	public List getMyVisitedList(int userid,int count) {
		String sql="select distinct a.visit_userid,a.userid,a.visit_time from core_user_visit a where a.userid="+userid+" order by visit_time desc limit "+count;
	
		List list=basicDAO.findBySqlQuery(sql);
		int len=list==null?0:list.size();
		List result=new ArrayList();
		for (int i = 0; i < len; i++) {
			CoreUserVisit uservisit = new CoreUserVisit();
			Object[] obj = (Object[]) list.get(i);
			uservisit.setVisitUserid(Integer.parseInt(obj[0].toString()));
			uservisit.setUserid(Integer.parseInt(obj[2].toString()));
			uservisit.setVisitTime((Timestamp)obj[3]);
			result.add(uservisit);
		}
		return result;
	}
}
