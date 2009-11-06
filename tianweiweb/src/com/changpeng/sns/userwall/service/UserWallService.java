package com.changpeng.sns.userwall.service;

import java.sql.Timestamp;
import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.sns.userwall.model.SnsUserWall;

public class UserWallService extends BasicService {
	Logger log=Logger.getLogger(UserWallService.class);
	
	/**
	 * 增加留言、留言回复
	 * @param parentid    0  留言 id 回复
	 */
	@Transactional
	public void addUserWall(int userid,int destuserid,String content, String ip,
			int parentid, boolean secrect, boolean anonymous) {
		// 对留言消息进行入表
		SnsUserWall userwall = new SnsUserWall();
		userwall.setUserid(destuserid);
//		userwall.setUserRole(destuserrole);
		userwall.setReplyUserid(userid);
//		userwall.setReplyUserRole(userrole);
		userwall.setContent(content);
		userwall.setCreateIp(ip);
		userwall.setCreateTime(new Timestamp(System.currentTimeMillis()));
		if(anonymous){
			userwall.setFlag((short)2);
		}else if(secrect){
			userwall.setFlag((short)1);
		}else{
			userwall.setFlag((short)0);
		}
		userwall.setParentId(parentid);
		
		basicDAO.save(userwall);
		
		String addmsgcntsql="update core_user_personal set count_comrep_unread=count_comrep_unread+1 where userid="+userid;
		basicDAO.executeSql(addmsgcntsql);
	}

	/**
     * 删除某一个系统消息
     * @param sysmessid系统消息ID
     */
    public void deleteUserWall(int id,int userid){
    	String sql="delete from sns_user_wall where id="+id+" and userid="+userid;
    	this.basicDAO.executeSql(sql);
//    	SnsUserWall userwall=(SnsUserWall)this.get(SnsUserWall.class, id);
//
//    	if(userwall.getUserid().intValue()==userid||userwall.getReplyUserid().intValue()==userid){
//    		this.delete(userwall);
//    		//this.basicDAO.getSessionFactory().getCurrentSession().flush();
//    	}
    }
    /**
     * 清空留言板
     * @param userid 用户ID
     */
    public int clearUserWall(int userid){
    	String hql="delete from SnsUserWall where userid =" + userid;
    	int cn=basicDAO.execute(hql);
    	return cn;
    }
    
	/**
	 * 显示 一条留言的回复
	 * @param msgid  留言id
	 */
	public List getUserWallReply(int msgid) {
		String query = "from SnsUserWall uw where uw.parentId=? order by id desc";
		List list = null;
		Object[] obj;
		obj = new Object[] { msgid };
		list = basicDAO.findByQuery(query, obj);

		return list;
	}

	/**
	 * 分页显示留言
	 * 
	 */
	public PaginationSupport getUserWallList(int userid, int pageSize, int pageNo) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SnsUserWall.class);
		detachedCriteria.add(Restrictions.eq("userid", userid));
		detachedCriteria.add(Restrictions.eq("parentId", 0));
		detachedCriteria.addOrder(Order.desc("createTime"));
		PaginationSupport page = findPageOnPageNo(detachedCriteria, pageSize,pageNo);

		return page;
	}

	public int getMsgCount(int userid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(SnsUserWall.class);
		criteria.add(Restrictions.eq("userid", userid));
		criteria.add(Restrictions.eq("parentId", 0));
		return this.getCountByCriteria(criteria);
	}
	public void setRepCount2Zero(int userid,short userrole){
		String addmsgcntsql="update core_user_personal set count_comrep_unread=0 where userid="+userid+" and desrole="+userrole;
		basicDAO.executeSql(addmsgcntsql);
	}
	
}

