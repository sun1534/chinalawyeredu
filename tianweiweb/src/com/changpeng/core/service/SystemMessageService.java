/**
 * 系统消息
 * 20090515x 
 */

package com.changpeng.core.service;

import java.sql.Timestamp;


import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.core.message.model.CoreSysMsg;
import com.changpeng.core.user.model.CoreUserPersonal;

/**
 * 描述 系统消息业务实现
 * 
 */
public class SystemMessageService extends BasicService {
	Logger log = Logger.getLogger(SystemMessageService.class);

    /**
     * 删除某一个系统消息
     * @param sysmessid系统消息ID
     */
    public void deleteSysMsgById(int sysmessid,int userid){
    	String hql="delete from CoreSysMsg msg where msg.id="+sysmessid+" and msg.destUserid="+userid;
//    	log.debug("hql:"+hql);
    	basicDAO.execute(hql);
    }
    /**
     * 清空所有系统消息
     * @param userid 用户ID
     */
    public int deleteMsgByUserid(int userid){
    	String hql="delete from CoreSysMsg where destUserid =" + userid;
    	int cn=basicDAO.execute(hql);
//    	log.debug("清空系统消息数为："+cn);
    	return cn;
    }
    
    
	/**
	 * 产生系统消息
	 * @param userID接收人
	 * @param userrole接收人角色
	 * @param content内容
	 */
	@Transactional
	public int produceSystemMessage(int userid, String content) {
		CoreSysMsg sysmsg = new CoreSysMsg();
		sysmsg.setDestUserid(userid);
//		sysmsg.setDestUserRole(userrole);
		sysmsg.setContent(content);
		sysmsg.setSendTime(new Timestamp(System.currentTimeMillis()));
		sysmsg.setSendType((short)0);
		basicDAO.save(sysmsg);
		
		String addmsgcntsql="update core_user_personal set count_sysmsg_unread=count_msg_unread+1 where userid="+userid;
		basicDAO.executeSql(addmsgcntsql);
		return sysmsg.getId();
	}
	/**
	 * 产生发送好友请求的系统消息
	 * @param userID接收人
	 * @param userrole接收人角色
	 * @param content内容
	 */
	@Transactional
	public int sendfriendapply(int userid, String content) {
		CoreSysMsg sysmsg = new CoreSysMsg();
		sysmsg.setDestUserid(userid);
//		sysmsg.setDestUserRole(userrole);
		sysmsg.setContent(content);
		sysmsg.setSendTime(new Timestamp(System.currentTimeMillis()));
		sysmsg.setSendType((short)0);
		basicDAO.save(sysmsg);
		sysmsg.setContent(content.replace("_0_", sysmsg.getId()+""));
		basicDAO.update(sysmsg);
		
		String addmsgcntsql="update core_user_personal set count_sysmsg_unread=count_msg_unread+1 where userid="+userid;
		basicDAO.executeSql(addmsgcntsql);
		return sysmsg.getId();
	}
	/**
	 * 更新添加好友系统消息/同意/不同意
	 * @param sysmsgid ID
	 * @param userid 
	 * @param content 
	 */
	public void backSystemMessage(int sysmsgid,int userid, String content){
		String sql="update core_sys_msg set content='"+content+"' where id="+sysmsgid+" and dest_userid="+userid;
		basicDAO.executeSql(sql);
	}
	
	/**
	 * 分页显示系统消息
	 * 
	 * @param userid
	 * @param pageSize
	 * @param pageNo
	 */
	@Transactional
	public PaginationSupport getSystemMessageList(int userid,int pageSize, int pageNo) {
		PaginationSupport pagination = null;
		DetachedCriteria dc = DetachedCriteria.forClass(CoreSysMsg.class,"sys");
		dc.add(Restrictions.eq("sys.destUserid", userid));
		dc.addOrder(Order.desc("sendTime"));
		pagination = this.findPageOnPageNo(dc, pageSize, pageNo);

		//系统消息清0
		CoreUserPersonal userpersonal=(CoreUserPersonal) basicDAO.get(CoreUserPersonal.class, userid);
		if(userpersonal.getCountSysmsgUnread()!=0){
			userpersonal.setCountSysmsgUnread((short)0);
			basicDAO.save(userpersonal);
		}
		return pagination;
	}

	
}
