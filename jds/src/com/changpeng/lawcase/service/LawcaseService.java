/**
 * 
 */
package com.changpeng.lawcase.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.changpeng.lawcase.model.TlawAssignHistory;
import com.changpeng.lawcase.model.TlawCaseJixiao;
import com.changpeng.lawcase.model.TlawCaselog;
import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawOperlog;
import com.sxit.system.model.TsysUser;

/**
 * @author 华锋 Oct 26, 2009-9:52:37 PM
 * 
 */
public class LawcaseService {

	/**
	 * 更新这个case某个阶段的时间
	 * 
	 * @param session
	 * @param caseid
	 * @param stage
	 * @param date
	 */
	public static void updateStageDate(Session session, long caseid, String stage, String date) {
		String sql = "update tlaw_stagedate set " + stage + "='" + date + "' where caseid=" + caseid;
		session.createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 根据userid得到这个人所在的职位id
	 * 
	 * @param session
	 * @param userid
	 * @return
	 */
//	public static List getPositionsById(Session session, long userid) {
//
//		String sql = "select positionid from tsys_department_position where userid=" + userid;
//		List list = session.createSQLQuery(sql).list();
//		List _list = new ArrayList();
//		for (int i = 0; list != null && i < list.size(); i++) {
//			_list.add(Integer.parseInt(list.get(i).toString()));
//		}
//		return _list;
//	}
	/**
	 * 根据userid得到这个人所在的角色id
	 * 
	 * @param session
	 * @param userid
	 * @return
	 */
	public static List getRolesById(Session session, long userid) {

		String sql = "select roleid from tsys_user_role where userid=" + userid;
		List list = session.createSQLQuery(sql).list();
		List _list = new ArrayList();
		for (int i = 0; list != null && i < list.size(); i++) {
			_list.add(Integer.parseInt(list.get(i).toString()));
		}
		return _list;
	}

	/**
	 * 判断这个人在这个点，对这个案件是否已经有了这个绩效了
	 * 
	 * @param session
	 * @param userid
	 * @param actionid
	 * @param caseid
	 * @return
	 */
	public static TlawCaseJixiao getUserActionJixiao(Session session, long userid, int actionid, long caseid,
			int jixiaoid) {
		String hql = "from TlawCaseJixiao a where a.caseid=" + caseid + " and a.userid=" + userid + " and a.actionid="
				+ actionid + " and a.jixiaoid=" + jixiaoid;
		List list = session.createQuery(hql).list();
		if (list == null || list.size() == 0)
			return null;
		return (TlawCaseJixiao) list.get(0);

	}

	/**
	 * 保存这个人的绩效信息
	 * 
	 * @param session
	 */
	public static void saveJixiao(Session session, TlawCaseJixiao jixiao, TsysUser sysuser) {

		jixiao.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		jixiao.setCreateuserid(sysuser.getUserid());
		jixiao.setCreateusername(sysuser.getUsername());
		TlawCaseJixiao oldjixiao = getUserActionJixiao(session, jixiao.getUserid(), jixiao.getActionid(), jixiao
				.getCaseid(), jixiao.getJixiaoid());
		if (oldjixiao != null) {
			oldjixiao.setCreatetime(jixiao.getCreatetime());
			oldjixiao.setCreateuserid(jixiao.getCreateuserid());
			oldjixiao.setCreateusername(jixiao.getCreateusername());
			// 修改的时候,logtime都不变动了
			// oldjixiao.setLogtime(jixiao.getLogtime());
			oldjixiao.setRemarks(oldjixiao.getRemarks() + ",修改了立案缴费信息,重新生成绩效");
			session.update(oldjixiao);
		} else {
			session.save(jixiao);
			LawcaseService.saveOperlog(session, jixiao.getCaseid(), "记录绩效:" + jixiao.getWhylog(), 8, sysuser);
		}
	}

	/**
	 * 保存承办人所写的案件日志
	 * 
	 * @param session
	 * @param log
	 * @param sysUser
	 */
	public static void saveCaselog(Session session, TlawCaselog log, TsysUser sysUser) {
		log.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		log.setCreateuserid(sysUser.getUserid());
		log.setCreateusername(sysUser.getUsername());
		session.save(log);
	}

	/**
	 * 记录日志
	 * 
	 * @param session
	 * @param log
	 * @param sysuser
	 */
	public static void saveOperlog(Session session, long caseid, String content, int opertype, TsysUser sysuser) {
		TlawOperlog log = new TlawOperlog();
		log.setCaseid(caseid);
		log.setLogcontent(content);
		log.setOpertype(opertype);
		log.setUserid(sysuser.getUserid());
		log.setUsername(sysuser.getUsername());
		log.setLogtime(new java.sql.Timestamp(System.currentTimeMillis()));
		session.save(log);
	}

	/**
	 * caseid的分配历史记录,也就是如果要重新分配人员的话，在这里也要录入进去
	 * 
	 * @param session
	 * @param assignUserid
	 * @param assignedUserid
	 * @param caseid
	 */
	public static void saveAssignHistory(Session session, long caseid, int assigntype, long assignUserid,
			long createuserid) {
		TlawAssignHistory history = new TlawAssignHistory();
		history.setAssigneduserid(assignUserid);
		history.setAssigntime(new java.sql.Timestamp(System.currentTimeMillis()));
		TlawLawcase lawcase = new TlawLawcase();
		lawcase.setCaseid(caseid);
		history.setLawcase(lawcase);
		history.setCreateuserid(assignUserid);
		history.setAssigntype(assigntype);
		String username = com.changpeng.lawcase.util.CommanDatas.USER_ID_NAME.get(assignUserid);
		history.setAssignedusername(username);
		session.save(history);
	}
}
