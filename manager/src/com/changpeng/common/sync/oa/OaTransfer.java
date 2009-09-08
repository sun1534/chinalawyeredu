/**
 * 
 */
package com.changpeng.common.sync.oa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.sync.lgpt.GuildUserInfo;
import com.changpeng.common.sync.lgpt.LgptDAO;
import com.changpeng.common.sync.lgpt.OrgOfficeInfo;
import com.changpeng.common.sync.lgpt.OrgUserInfo;
import com.changpeng.common.sync.lgpt.PersonInfo;

/**
 * @author 华锋 Jul 11, 2009-4:37:09 PM
 * 
 */
public class OaTransfer {

	private static Log LOG = LogFactory.getLog(OaTransfer.class);

	/**
	 * 判断管理员是否存在
	 * 
	 * @param con
	 * @param systemnum
	 * @return
	 * @throws SQLException
	 */
	private static boolean isExistUsers(Connection con, String systemnum) throws SQLException {
		String sql = "select count(*) as cnt from tsys_user where SYSTEMNUM='" + systemnum + "'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt("cnt") > 0 ? true : false;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	/**
	 * 判断事务所是否存在
	 * 
	 * @param con
	 * @param systemnum
	 * @return
	 * @throws SQLException
	 */
	private static int isExistDepts(Connection con, String systemnum) throws SQLException {
		String sql = "select departmentid from tsys_department where SYSTEMNUM='" + systemnum + "'";
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
			result = rs.getInt("departmentid");

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return result;
	}

	private static void deleteUser(Connection con, String systemnum) throws SQLException {
		String sql = "delete from tsys_user where SYSTEMNUM='" + systemnum + "'";
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			stmt.execute(sql);
		} finally {

			if (stmt != null)
				stmt.close();
		}
	}

	private static void deleteDept(Connection con, String systemnum) throws SQLException {
		String sql = "delete from tsys_department where SYSTEMNUM='" + systemnum + "'";
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			stmt.execute(sql);
		} finally {

			if (stmt != null)
				stmt.close();
		}
	}

	public static void syncLawyers(PersonInfo person, Connection con) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String systemnum = "9" + person.getPersonCode();
//			int deptid = getDeparentid(con, "9" + person.getOrgId());
			String flag = person.getDataType();
			int deptid = isExistDepts(con, "9" + person.getOrgId());
			if (deptid==0 && (flag.equals("inserted") || flag.equals("updated"))) {
				LOG.info("OA律师对应事务所不存在,ORGID:::(9)+" + person.getOrgId());
				return;
			}
			boolean isExist = isExistUsers(con, systemnum);

			if (flag != null && flag.equalsIgnoreCase("inserted") || flag.equalsIgnoreCase("updated")) {
				String sql = "";
				if (isExist) {
					sql = "update tsys_user set username=?,loginname=?,CREATETIME=?,password=?,email=?,phone=?,mobile=?,statusid=?,description=?,isadmin=?,isleader=?,userno=?,bqqno=?,"
							+ "msnmail=?,positionname=?,image=?,style=?,departmentid=?,usertype=? where systemnum=?";
				} else {
					sql = "insert into tsys_user(USERNAME,LOGINNAME,CREATETIME,PASSWORD,"
							+ "EMAIL,PHONE,MOBILE,STATUSID,DESCRIPTION,ISADMIN,ISLEADER,USERNO,BQQNO,"
							+ "MSNMAIL,positionname,IMAGE,STYLE,DEPARTMENTID,usertype,SYSTEMNUM) "
							+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				}

				stmt = con.prepareStatement(sql);
				stmt.setString(1, person.getPersonName());
				stmt.setString(2, person.getCertificateNo());
				stmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
//				stmt.setString(4, person.getIdentityCard());
				stmt.setString(4, com.changpeng.common.util.MD5.md5(person.getIdentityCard()));
				
				stmt.setString(5, person.getEmail());
				stmt.setString(6, person.getMobilePhone());
				stmt.setString(7, person.getMobilePhone());
				stmt.setInt(8, 1);
				stmt.setString(9, "律管平台同步");
				stmt.setBoolean(10, false);
				stmt.setBoolean(11, person.getMasterFlag().equals("1")?true:false);
				stmt.setString(12, person.getIdentityCard());
				stmt.setString(13, person.getCertificateNo());
				stmt.setString(14, "");
				stmt.setString(15, "");
				String image="";
				if(person.getFilename()!=null&&!person.getFilename().equals("")&&!person.getFilename().equalsIgnoreCase("null")){
					image=LgptDAO.LOGOPATH+ person.getFilename();
				}
				stmt.setString(16, image);
				
				stmt.setInt(17, 1);
				stmt.setInt(18, deptid);
				
				stmt.setString(19, person.getRoleCode());
				stmt.setString(20, systemnum);
				

				stmt.executeUpdate();
			} else if (flag != null && flag.equals("deleted")) {
				if (!isExist)
					deleteUser(con, systemnum);
				else
					LOG.warn("OA删除律师,但是对应的律师不存在:" + systemnum);

			} else {
				LOG.warn("ExchangeFlag错误:::" + flag);
			}
			LOG.info("OA律师同步成功: StreamId" + person.getOrgId());
		} catch (SQLException e) {
			LOG.error("OA律师同步异常: StreamId" + person.getOrgId() + "=>" + e);
			e.printStackTrace();throw e;
		} finally {
			if (stmt != null)
				stmt.close();

		}
	}

	public static void syncOrgs(OrgOfficeInfo org, Connection con) throws SQLException {
		PreparedStatement stmt = null;
		try {

			String systemnum = "9" + org.getOrgId();

			int parentid = getDeparentid(con, "-1" + org.getGuildId());

			String sql = "";
			int isExist = isExistDepts(con, systemnum);
			if (isExist!=0) {
				sql = "update tsys_department set DEPARTMENTNAME=?,DESCRIPTION=?,displayname=?,TYPE=?,STATUSID=?,PHONE=?,LINKMAN=?,ADDRESS=?,CREATETIME=?,PARENTID=? where SYSTEMNUM=?";
			} else {
				sql = "insert into tsys_department(DEPARTMENTNAME,DESCRIPTION,"
						+ "displayname,TYPE,STATUSID,PHONE,LINKMAN,ADDRESS,CREATETIME,PARENTID,SYSTEMNUM) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?)";
			}

			stmt = con.prepareStatement(sql);
			stmt.setString(1, org.getOrgName());
			stmt.setString(2, "律管平台同步");
			stmt.setString(3, org.getOrgName());
			stmt.setInt(4, 1);// 机构类型 0 律协，1 事务所
			stmt.setInt(5, (short) 1);
			stmt.setString(6, "");
			stmt.setString(7, "");
			stmt.setString(8, org.getAddress());
			stmt.setTimestamp(9, new java.sql.Timestamp(System.currentTimeMillis()));
			stmt.setInt(10, parentid); //
			stmt.setString(11, systemnum); // systemnum

			String flag = org.getDataType();
			if (flag != null && flag.equalsIgnoreCase("inserted") || flag.equalsIgnoreCase("updated")) {
				stmt.executeUpdate();
			} else if (flag != null && flag.equals("deleted")) {
				if (isExist>0)
					deleteDept(con, systemnum);
				else
					LOG.warn("删除事务所,但是对应的事务所不存在:" + systemnum);

			} else {
				LOG.warn("ExchangeFlag错误:::" + flag);
			}
			LOG.info("OA部门同步成功 : getOrgId:" + org.getOrgId() + "=>" + flag);
		} catch (SQLException e) {
			LOG.error("OA部门同步异常 : getOrgId:" + org.getOrgId() + "=>" + e);
			e.printStackTrace();
			throw e;
		} finally {
			if (stmt != null)
				stmt.close();

		}
	}

	/**
	 * 
	 */
	public static void syncLXManagers(GuildUserInfo info, Connection con) throws SQLException {
		PreparedStatement stmt = null;
		//
		// String sql = "insert into
		// tsys_user(USERNAME,LOGINNAME,CREATETIME,PASSWORD,"
		// +
		// "EMAIL,PHONE,MOBILE,STATUSID,DESCRIPTION,ISADMIN,ISLEADER,USERNO,BQQNO,"
		// + "MSNMAIL,positionname,IMAGE,STYLE,DEPARTMENTID,SYSTEMNUM) "
		// + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			// String systemnum = "-1" + info.getUserId();
			// int deptid = getDeparentid(con, "-1" + info.getDepatId());

//			int deptid = getDeparentid(con, "-1" + info.getDepatId());
			int deptid = isExistDepts(con, "-1" + info.getDepatId());
			String flag = info.getDataType();
			
			if (deptid==0 && (flag.equals("inserted") || flag.equals("updated"))) {
				LOG.info("律协管理员对应律协不存在,ORGID:::(-1)+" + info.getDepatId());
				return;
			}
			String systemnum = "-1" + info.getUserId();

			boolean isExist = isExistUsers(con, systemnum);
			if (flag != null && flag.equalsIgnoreCase("inserted") || flag.equalsIgnoreCase("updated")) {
				String sql = "";

				if (isExist) {
					sql = "update tsys_user set username=?,loginname=?,CREATETIME=?,password=?,email=?,phone=?,mobile=?,statusid=?,description=?,isadmin=?,isleader=?,userno=?,bqqno=?,"
							+ "msnmail=?,positionname=?,image=?,style=?,departmentid=? where systemnum=?";
				} else {
					sql = "insert into tsys_user(USERNAME,LOGINNAME,CREATETIME,PASSWORD,"
							+ "EMAIL,PHONE,MOBILE,STATUSID,DESCRIPTION,ISADMIN,ISLEADER,USERNO,BQQNO,"
							+ "MSNMAIL,positionname,IMAGE,STYLE,DEPARTMENTID,SYSTEMNUM) "
							+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				}

				stmt = con.prepareStatement(sql);
				stmt.setString(1, info.getUserName());
				stmt.setString(2, info.getLoginName());
				stmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
				stmt.setString(4, info.getPassword());
				stmt.setString(5, "");
				stmt.setString(6, "");
				stmt.setString(7, "");
				stmt.setInt(8, 1);
				stmt.setString(9, "律管平台同步");
				stmt.setBoolean(10, true);
				stmt.setBoolean(11, true);
				stmt.setString(12, "");
				stmt.setString(13, "");
				stmt.setString(14, "");
				stmt.setString(15, "");
				stmt.setString(16, "");
				stmt.setInt(17, 1);
				stmt.setInt(18, deptid);
				stmt.setString(19, systemnum);

				stmt.executeUpdate();
			} else if (flag != null && flag.equals("deleted")) {
				if (!isExist)
					deleteUser(con, systemnum);
				else
					LOG.warn("删除律协管理员,但是对应的律协管理员不存在:" + systemnum);

			} else {
				LOG.warn("ExchangeFlag错误:::" + flag);
			}
			LOG.info("OA律协管理员同步成功 : getUserId:" + info.getUserId() + "=>" + flag);
		} catch (SQLException e) {
			LOG.error("OA律协管理员同步异常 : getUserId:" + info.getUserId() + "=>" + e);
			e.printStackTrace();throw e;
		} finally {
			if (stmt != null)
				stmt.close();

		}
	}

	public static void syncOrgManagers(OrgUserInfo info, Connection con) throws SQLException {
		PreparedStatement stmt = null;

		// String sql = "insert into
		// tsys_user(USERNAME,LOGINNAME,CREATETIME,PASSWORD,"
		// +
		// "EMAIL,PHONE,MOBILE,STATUSID,DESCRIPTION,ISADMIN,ISLEADER,USERNO,BQQNO,"
		// + "MSNMAIL,positionname,IMAGE,STYLE,DEPARTMENTID,SYSTEMNUM) "
		// + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			String systemnum = "8" + info.getUserId();

			String flag = info.getDataType();
			int deptid = isExistDepts(con,"9" + info.getOrgId());
			if (deptid==0 && (flag.equals("inserted") || flag.equals("updated"))) {
				LOG.info("事务所管理员对应事务所不存在,ORGID:::(9)+" + info.getOrgId());
				return;
			}

			boolean isExist = isExistUsers(con, systemnum);
			if (flag != null && flag.equalsIgnoreCase("inserted") || flag.equalsIgnoreCase("updated")) {
				String sql = "";

				if (isExist) {
					sql = "update tsys_user set username=?,loginname=?,CREATETIME=?,password=?,email=?,phone=?,mobile=?,statusid=?,description=?,isadmin=?,isleader=?,userno=?,bqqno=?,"
							+ "msnmail=?,positionname=?,image=?,style=?,departmentid=? where systemnum=?";
				} else {
					sql = "insert into tsys_user(USERNAME,LOGINNAME,CREATETIME,PASSWORD,"
							+ "EMAIL,PHONE,MOBILE,STATUSID,DESCRIPTION,ISADMIN,ISLEADER,USERNO,BQQNO,"
							+ "MSNMAIL,positionname,IMAGE,STYLE,DEPARTMENTID,SYSTEMNUM) "
							+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				}

				stmt = con.prepareStatement(sql);
				stmt.setString(1, info.getUserName());
				stmt.setString(2, info.getUserName());
				stmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
				stmt.setString(4, info.getPassword());
				stmt.setString(5, "");
				stmt.setString(6, "");
				stmt.setString(7, "");
				stmt.setInt(8, 1);
				stmt.setString(9, "律管平台同步");
				stmt.setBoolean(10, false);
				stmt.setBoolean(11, true);
				stmt.setString(12, "");
				stmt.setString(13, "");
				stmt.setString(14, "");
				stmt.setString(15, "");
				stmt.setString(16, "");
				stmt.setInt(17, 1);
				stmt.setInt(18, deptid);
				stmt.setString(19, systemnum);

				stmt.executeUpdate();
			} else if (flag != null && flag.equals("deleted")) {
				if (!isExist)
					deleteUser(con, systemnum);
				else
					LOG.warn("删除事务所管理员,但是对应的事务所管理员不存在:" + systemnum);

			} else {
				LOG.warn("ExchangeFlag错误:::" + flag);
			}
			LOG.error("OA事务所管理员同步异常 : getUserId:" + info.getUserId() + "=>" + flag);
		} catch (SQLException e) {
			LOG.error("OA事务所管理员同步异常 : getUserId:" + info.getUserId() + "=>" + e);
			e.printStackTrace();throw e;
		} finally {
			if (stmt != null)
				stmt.close();

		}
	}

	private static int getDeparentid(Connection con, String systemnum) throws SQLException {

		java.sql.Statement stmt = null;

		String sql = "select DEPARTMENTID from tsys_department where SYSTEMNUM='" + systemnum + "'";

		int rtval = 0;
		java.sql.ResultSet rst = null;
		try {
			stmt = con.createStatement();
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				rtval = rst.getInt(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (rst != null)
				rst.close();
			if (stmt != null)
				stmt.close();

		}
		return rtval;
	}
}
