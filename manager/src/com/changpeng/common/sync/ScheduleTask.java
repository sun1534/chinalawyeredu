package com.changpeng.common.sync;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.common.sync.lgpt.LgptDAO;

/**
 * 
 * <pre>
 * 1、从律管平台的数据库拿律师数据和事务所数据到网站以及培训系统
 * 2、对这个进行更新删除和新增
 * 
 * </pre>
 * 
 * @修改：
 */
public class ScheduleTask extends TimerTask {
	private static Log LOG = LogFactory.getLog(ScheduleTask.class);

	private SyncTask task;
	private com.changpeng.common.sync.lgpt.LgptDAO lgptDao;

	public ScheduleTask() {
		task = new SyncTask();
		lgptDao = new LgptDAO();
	}

	private void syncOa(Connection con) {
		String type = "oa";
		boolean result = false;
		try {

			List orgList = lgptDao.getTempOrgs(con, type);
			List lawyerList = lgptDao.getTempPersons(con, type);
			List orgUsers = lgptDao.getTempOrgUsers(con, type);
			List lxUsers = lgptDao.getTempLxUsers(con, type);

			// List lawyerList = new ArrayList();
			// List orgUsers = new ArrayList();
			// List lxUsers = new ArrayList();

			LOG.info("同步到OA,lawyerList:" + lawyerList.size() + ",orgList=" + orgList.size() + ",orgUsers="
					+ orgUsers.size() + ",lxUsers=" + lxUsers.size());

			if (!(lawyerList.size() == 0 && orgList.size() == 0 && orgUsers.size() == 0 && lxUsers.size() == 0)) {

				result = task.syncOA(lawyerList, orgList, orgUsers, lxUsers);
				LOG.info("本次OA同步最终结果:" + result);
				if (result) {

					con.setAutoCommit(false);

					lgptDao.updateTempOrgs(con, type, orgList);
					lgptDao.updateTempPersons(con, type, lawyerList);
					lgptDao.updateTempLxUsers(con, type, lxUsers);
					lgptDao.updateTempOrgUsers(con, type, orgUsers);

				}
			}

		} catch (Exception e) {
			LOG.error("syncOa::" , e);
			e.printStackTrace();

		} finally {
			try {
				if (result) {
					con.commit();
					con.setAutoCommit(true);
				}
			} catch (SQLException ee) {
				LOG.error("syncOa finally::" + ee);
			}
		}
	}

	private void syncPxxt(Connection con) {
		String type = "pxxt";
		boolean result = false;
		try {

			List orgList = lgptDao.getTempOrgs(con, type);
			List lawyerList = lgptDao.getTempPersons(con, type);
			List orgUsers = lgptDao.getTempOrgUsers(con, type);
			List lxUsers = lgptDao.getTempLxUsers(con, type);

			// List lawyerList=new ArrayList();
			// List orgUsers=new ArrayList();
			// List lxUsers =new ArrayList();

			LOG.info("同步到培训系统,lawyerList:" + lawyerList.size() + ",orgList=" + orgList.size() + ",orgUsers="
					+ orgUsers.size() + ",lxUsers=" + lxUsers.size());

			if (!(lawyerList.size() == 0 && orgList.size() == 0 && orgUsers.size() == 0 && lxUsers.size() == 0)) {

				result = task.syncPxxt(lawyerList, orgList, orgUsers, lxUsers);
				LOG.info("本次培训系统同步最终结果:" + result);
				if (result) {

					con.setAutoCommit(false);

					lgptDao.updateTempOrgs(con, type, orgList);
					lgptDao.updateTempPersons(con, type, lawyerList);
					lgptDao.updateTempLxUsers(con, type, lxUsers);
					lgptDao.updateTempOrgUsers(con, type, orgUsers);

				}
			}

		} catch (SQLException e) {
			LOG.error("syncOa::" + e);

		} finally {
			try {
				if (result) {
					con.commit();
					con.setAutoCommit(true);
				}
			} catch (SQLException ee) {
				LOG.error("syncOa finally::" + ee);
			}
		}
	}

	private void syncWebSite(Connection con) {
		String type = "web";
		boolean result = false;
		try {

			List orgList = lgptDao.getTempOrgs(con, type);
			List lawyerList = lgptDao.getTempPersons(con, type);
			List orgUsers = lgptDao.getTempOrgUsers(con, type);
			List lxUsers = lgptDao.getTempLxUsers(con, type);

			// List lawyerList=new ArrayList();
			// List orgUsers=new ArrayList();
			// List lxUsers =new ArrayList();

			LOG.info("同步到WEB,lawyerList:" + lawyerList.size() + ",orgList=" + orgList.size() + ",orgUsers="
					+ orgUsers.size() + ",lxUsers=" + lxUsers.size());

			if (!(lawyerList.size() == 0 && orgList.size() == 0 && orgUsers.size() == 0 && lxUsers.size() == 0)) {

				result = task.syncWebsite(lawyerList, orgList, orgUsers, lxUsers);
				LOG.info("本次网站同步最终结果:" + result);
				// System.out.println("result=========" + result);
				if (result) {

					con.setAutoCommit(false);

					lgptDao.updateTempOrgs(con, type, orgList);
					lgptDao.updateTempPersons(con, type, lawyerList);
					lgptDao.updateTempLxUsers(con, type, lxUsers);
					lgptDao.updateTempOrgUsers(con, type, orgUsers);

				}
			}

		} catch (Exception e) {
			LOG.error("syncWebSite::", e);
			e.printStackTrace();

		} finally {
			try {
				if (result) {
					con.commit();
					con.setAutoCommit(true);
				}
			} catch (SQLException ee) {
				LOG.error("syncWebSite finally::" + ee);
			}
		}
	}

	public void run() {

		Connection con = null;
		try {

			// service.findBySqlQuery("select count(*) from sys_user");

			String sql = "update lawyers a inner join sys_unionparams b on a.directunion=b.groupid set a.dabiaofen=b.dabiaofen";

			BasicService service = (BasicService) Globals.getMainBean("basicService");
			service.executeSql(sql);
			LOG.info("更新律师的达标分成功...");

			com.changpeng.system.util.CommonDatas.getGroups();
			com.changpeng.system.util.CommonDatas.getUsers();
			com.changpeng.lessons.util.CommonDatas.getAlllessons();

			LOG.info("定时获取用户信息成功");

			con = DBUtils.getOracleCon();

			syncPxxt(con);

//			syncOa(con);

			syncWebSite(con);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			LOG.error("执行定时任务错误:" + e.getMessage());
		} finally {
			DBUtils.closeConnection(con);
			LOG.info("成功关闭到临时数据库的链接");
		}
	}

	public static void main(String[] args) {
		TestTask task = new TestTask();
		task.run();

	}
}