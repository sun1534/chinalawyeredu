/**
 * 
 */
package com.changpeng.common.sync;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.sync.lgpt.GuildUserInfo;
import com.changpeng.common.sync.lgpt.OrgOfficeInfo;
import com.changpeng.common.sync.lgpt.OrgUserInfo;
import com.changpeng.common.sync.lgpt.PersonInfo;

/**
 * @author 华锋 Jul 8, 2009-7:04:09 PM
 * 
 */
public class SyncTask {
	private static Log LOG = LogFactory.getLog(SyncTask.class);

	public boolean syncOA(List lawyerList, List orgList, List orgUsers, List lxUsers) throws Exception {
		throw new Exception();
//		Connection con = null;
//		try {
//			con = DBUtils.getOACon();
//			con.setAutoCommit(false);
//			for (int i = 0; i < orgList.size(); i++) {
//				OrgOfficeInfo org = (OrgOfficeInfo) orgList.get(i);
//				com.changpeng.common.sync.oa.OaTransfer.syncOrgs(org, con);
//			}
//
//			for (int i = 0; i < lawyerList.size(); i++) {
//				PersonInfo person = (PersonInfo) lawyerList.get(i);
//				com.changpeng.common.sync.oa.OaTransfer.syncLawyers(person, con);
//			}
//			for (int i = 0; i < orgUsers.size(); i++) {
//				OrgUserInfo person = (OrgUserInfo) orgUsers.get(i);
//				com.changpeng.common.sync.oa.OaTransfer.syncOrgManagers(person, con);
//			}
//			for (int i = 0; i < lxUsers.size(); i++) {
//				GuildUserInfo person = (GuildUserInfo) lxUsers.get(i);
//				com.changpeng.common.sync.oa.OaTransfer.syncLXManagers(person, con);
//			}
//			con.commit();
//			con.setAutoCommit(true);
//			return true;
//		} catch (Exception e) {
//			LOG.error("网站数据同步失败:::", e);
//			return false;
//		} finally {
//			DBUtils.closeConnection(con);
//			LOG.info("网站数据同步完毕:::");
//		}
	}

	/**
	 * 
	 * @param lawyerList
	 * @param orgList
	 * @param orgUsers
	 * @param lxUsers
	 */
	public boolean syncPxxt(List lawyerList, List orgList, List orgUsers, List lxUsers) {

		try {
			for (int i = 0; i < orgList.size(); i++) {
				OrgOfficeInfo org = (OrgOfficeInfo) orgList.get(i);
				try {
					com.changpeng.common.sync.pxxt.PxxtTransfer.syncOrgs(org);
				} catch (Exception e) {
				}
			}

			for (int i = 0; i < lawyerList.size(); i++) {
				PersonInfo person = (PersonInfo) lawyerList.get(i);
				try {
					com.changpeng.common.sync.pxxt.PxxtTransfer.syncLawyers(person);
				} catch (Exception e) {
				}
			}
			for (int i = 0; i < orgUsers.size(); i++) {
				OrgUserInfo person = (OrgUserInfo) orgUsers.get(i);
				try {
					com.changpeng.common.sync.pxxt.PxxtTransfer.syncOrgManagers(person);
				} catch (Exception e) {
				}
			}
			for (int i = 0; i < lxUsers.size(); i++) {
				GuildUserInfo person = (GuildUserInfo) lxUsers.get(i);
				try {
					com.changpeng.common.sync.pxxt.PxxtTransfer.syncLXManagers(person);
				} catch (Exception e) {
				}
			}
			return true;
		} catch (Exception e) {
			LOG.error("syncPxxt失败::", e);
			return false;
		}

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private int getMaxStaffId() throws SQLException {
		Connection con = null;
		String sql = "select max(staffid) as id from topmstaff";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getWebsiteCon();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				return rs.getInt("id") + 1;
			return (int) (System.currentTimeMillis() / 1000);

		} catch (Exception e) {

			return (int) (System.currentTimeMillis() / 1000);
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			DBUtils.closeConnection(con);
		}
	}

	/**
	 * 同步的都是律师事务所的数据
	 * 
	 * @param lawyerList
	 * @param orgList
	 * @throws Exception
	 */
	public boolean syncWebsite(List lawyerList, List orgList, List orgUsers, List lxUsers) throws Exception {

		Connection con = null;
		try {
			con = DBUtils.getWebsiteCon();
			con.setAutoCommit(false);

			int staffid = getMaxStaffId();
			LOG.info("得到的TOPMSTAFF的最大值为:::" + staffid);
			for (int i = 0; i < orgList.size(); i++) {
				OrgOfficeInfo org = (OrgOfficeInfo) orgList.get(i);
				try {
					com.changpeng.common.sync.website.WebSiteTransfer.syncOrgs(con, org);
				} catch (Exception e) {
				}
			}
			// 同步topmstaff以及trususer二个表的数据
			for (int i = 0; i < lawyerList.size(); i++) {
				PersonInfo person = (PersonInfo) lawyerList.get(i);
				try {
					com.changpeng.common.sync.website.WebSiteTransfer.syncLawyers(con, person, staffid++);
				} catch (Exception e) {
				}
			}
			for (int i = 0; i < orgUsers.size(); i++) {
				OrgUserInfo person = (OrgUserInfo) orgUsers.get(i);
				try {
					com.changpeng.common.sync.website.WebSiteTransfer.syncOrgManagers(con, person, staffid++);
				} catch (Exception e) {
				}
			}
			for (int i = 0; i < lxUsers.size(); i++) {
				GuildUserInfo person = (GuildUserInfo) lxUsers.get(i);
				try {
					com.changpeng.common.sync.website.WebSiteTransfer.syncLXManagers(con, person, staffid++);
				} catch (Exception e) {
				}
				System.out.println("=========" + i);
			}
			con.commit();
			con.setAutoCommit(true);
			return true;
		} catch (Exception e) {
			LOG.error("网站数据同步失败:::", e);
			return false;
		} finally {
			DBUtils.closeConnection(con);
			LOG.info("网站数据同步完毕:::");
		}

	}

}
