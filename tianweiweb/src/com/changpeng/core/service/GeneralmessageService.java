package com.changpeng.core.service;

import java.sql.Timestamp;
import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.util.HtmlUtil;
import com.changpeng.core.message.model.CoreInnerMsg;
import com.changpeng.core.message.model.CoreInnerMsgDest;
import com.changpeng.core.user.model.CoreUserPersonal;


public class GeneralmessageService extends BasicService {
	Logger log = Logger.getLogger(GeneralmessageService.class);
	
	/**
	 * 发送短消息,消息不带标题
	 * 
	 * @param srcuserid发送人ID
	 * @param desid接收人
	 * @param content内容
	 * @param isgroupsend是否群发
	 */
	public void saveShortMessage(int srcuserid, int[] desid,String content,boolean isgroupsend) {
		saveShortMessage(srcuserid, desid,0,"", content,isgroupsend);
	}
	/**
	 * 发送短消息，消息带有标题
	 * 
	 * @param srcuserid发送人ID
	 * @param desid接收人
	 * @param content内容
	 * @param title 标题
	 * @param isgroupsend是否群发
	 */
	@Transactional
	public void saveShortMessage(int srcuserid, int[] desid,int parentid,String title, String content,boolean isgroupsend) {		
		//新增站内消息
		CoreInnerMsg innermsg = new CoreInnerMsg();
		
		innermsg.setTitle(title);
		innermsg.setContent(HtmlUtil.HtmlTransform(content));
		innermsg.setSendUserid(srcuserid);
//		innermsg.setSendUserRole(srcrole);
		innermsg.setFlag(isgroupsend?(short)1:(short)0);
		innermsg.setParentId(parentid);
		innermsg.setSendTime(new Timestamp(System.currentTimeMillis()));
		basicDAO.save(innermsg);
		
		for (int i = 0; i < desid.length; i++) {
			//新增发送对象
			CoreInnerMsgDest dest=new CoreInnerMsgDest();
			dest.setMsgId(innermsg.getId());
			dest.setDestUserid(desid[i]);
//			dest.setDestUserRole(desrole[i]);
			basicDAO.save(dest);
			//更新用户短消息数量
			if(srcuserid!=desid[i]){
				addMsgCount(desid[i]);
			}
		}
		
	}

	/**
	 * 回复短消息, 具有一定复杂性，业务逻辑上如何实现 删除等
	 * 
	 * @param content 内容
	 * @param parentid话题ID
	 * @param srcuserid 发送人
	 * @param srcrike 发送人角色
	 */
	public void saveReplyMessage(String content, int parentid,int srcuserid) {
		boolean canreply=false;
		int[] dest=null;
		List destids=getMsgDests(parentid);
		if(destids!=null&&destids.size()>1){
			dest=new int[destids.size()-1];
			int i=0;
			for(Object o:destids){
				if(((Integer)o).intValue()==srcuserid){
					canreply=true;
				}else{
					dest[i++]=((Integer)o).intValue();
				}
			}
		}
		if(canreply&&dest!=null&&dest.length>0){
			saveShortMessage(srcuserid,dest,parentid,"",content,false);
		}
	}
	
	private void addMsgCount(int userid){
		String addmsgcntsql="update core_user_personal set count_msg_unread=count_msg_unread+1 where userid="+userid;
		basicDAO.executeSql(addmsgcntsql);
	}
	/**
	 * 获取短消息
	 * 
	 * @param userid
	 * @param pageSize
	 * @param pageNo
	 * @param type 1,接收的消息  2,发送的消息
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public PaginationSupport getShorMessage(int userid,int type,int pageSize,int pageNo) {
		//查询用户的所有msgid
		String query = "select msgId from CoreInnerMsgDest as cdu where cdu.destUserid=? order by msgId desc";
		Object[] obj = new Object[] { new Integer(userid) };
		List msgidlist = basicDAO.findByQuery(query, obj);

		
		//分页查询 用户的消息
		DetachedCriteria criteria=DetachedCriteria.forClass(CoreInnerMsg.class);
		
		if(msgidlist==null||msgidlist.size()==0){
			msgidlist.add(0);
		}
		criteria.add(Restrictions.in("id", msgidlist));
		if (type == 1) {
			criteria.add(Restrictions.not(Restrictions.eq("sendUserid", userid)));
		} else if (type == 2) {
			criteria.add(Restrictions.eq("sendUserid", userid));
//			criteria.add(Restrictions.eq("sendUserRole", userrole));
		}
		criteria.addOrder(Order.desc("id"));
		PaginationSupport page=this.findPageOnPageNo(criteria, pageSize, pageNo);
		
		// 如果用户的未读短消息数量不为零的话，清零
		if(type==1){
			CoreUserPersonal userpersonal=(CoreUserPersonal) basicDAO.get(CoreUserPersonal.class, userid);
			if(userpersonal!=null&&userpersonal.getCountMsgUnread()!=0){
				userpersonal.setCountMsgUnread((short)0);
				basicDAO.save(userpersonal);
			}
		}
		return page;
	}
	
	/**
	 * 获取发送对象列表
	 * @param msgid
	 */
	public List getMsgDests(int msgid){
		String hql="select destUserid from CoreInnerMsgDest where msgId="+msgid;
		return basicDAO.findByQuery(hql);
	}
	/**
	 * 清除发件箱
	 * @param userid
	 * @param userrole
	 */
	public void clearSendBox(int userid) {
		String sql="delete d from core_inner_msg_dest d,core_inner_msg m " +
				"where d.msg_id=m.id and d.dest_userid="+userid+
				" and m.send_userid="+userid;
		basicDAO.executeSql(sql);
	}
	/**
	 * 清除收件箱
	 * @param userid
	 * @param userrole
	 */
	public void clearReceiveBox(int userid) {
		String sql="delete d from core_inner_msg_dest d,core_inner_msg m " +
				"where d.msg_id=m.id and d.dest_userid="+userid+
				" and m.send_userid!="+userid;
		basicDAO.executeSql(sql);
	}
	/**
	 * 删除消息
	 * 
	 * @param userid
	 * @param userrole
	 * @param msgid消息ID
	 */
	public void deleteShortMessage(int userid,int msgid) {
		String sql="delete from core_inner_msg_dest " +
				"where dest_userid="+userid+" and msg_id="+msgid;
		basicDAO.executeSql(sql);
	}
}
