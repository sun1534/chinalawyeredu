/**
 * 
 */
package com.changpeng.common.sync.lgpt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 华锋 Jul 8, 2009-6:26:33 PM
 * 
 */
public class LgptDAO {
	private static Log LOG = LogFactory.getLog(LgptDAO.class);

	public  static String LOGOPATH="http://changpeng.3322.org/common/picFile/";
	
	public List getTempLxUsers(Connection con, String type) throws SQLException {
		//		
		String persons_sql = "select * from T_U_GUILD_USER  ";
		if (type != null && !type.equals("")) {
			if (type.equals("oa"))
				persons_sql += " where oa_exchange_flag=0";
			else if (type.equals("web"))
				persons_sql += " where web_exchange_flag=0";
			else if (type.equals("pxxt"))
				persons_sql += " where PXXT_EXCHANGE_FLAG=0";
		}
		persons_sql += " order by stream_id";

		Statement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(persons_sql);
			while (rs.next()) {
				GuildUserInfo info = new GuildUserInfo();
				info.setStreamId(rs.getString("stream_id"));
				info.setLoginName(rs.getString("login_name"));
				info.setPassword(rs.getString("password"));
				info.setUserId(rs.getString("user_id"));
				info.setUserName(rs.getString("user_name"));
				info.setDepatId(rs.getString("depat_id"));
				info.setOaExchangeFlag(rs.getString("OA_EXCHANGE_FLAG"));
				info.setWebExchangeFlag(rs.getString("WEB_EXCHANGE_FLAG"));
				info.setPxxtExchangeFlag(rs.getString("PXXT_EXCHANGE_FLAG"));
				info.setDataType(rs.getString("data_type"));
				list.add(info);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return list;
	}

	public List getTempOrgUsers(Connection con, String type) throws SQLException {
		String persons_sql = "select * from T_U_LAWYER_USER  ";
		if (type != null && !type.equals("")) {
			if (type.equals("oa"))
				persons_sql += " where oa_exchange_flag=0";
			else if (type.equals("web"))
				persons_sql += " where web_exchange_flag=0";
			else if (type.equals("pxxt"))
				persons_sql += " where PXXT_EXCHANGE_FLAG=0";
		}
		persons_sql += " order by stream_id";

		Statement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(persons_sql);
			while (rs.next()) {
				OrgUserInfo info = new OrgUserInfo();
				info.setStreamId(rs.getString("stream_id"));
				info.setCreateDate(rs.getString("create_date"));
				info.setPassword(rs.getString("password"));
				info.setRegisterTime(rs.getDate("register_time"));
				info.setUserId(rs.getString("user_id"));
				info.setUserName(rs.getString("user_name"));
				info.setOrgId(rs.getString("org_id"));
				info.setOaExchangeFlag(rs.getString("OA_EXCHANGE_FLAG"));
				info.setWebExchangeFlag(rs.getString("WEB_EXCHANGE_FLAG"));
				info.setPxxtExchangeFlag(rs.getString("PXXT_EXCHANGE_FLAG"));
				info.setDataType(rs.getString("data_type"));
				list.add(info);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}

		return list;
	}

	public List getTempPersons(Connection con, String type) throws SQLException {
		String persons_sql = "select * from t_person_info  ";
		if (type != null && !type.equals("")) {
			if (type.equals("oa"))
				persons_sql += " where oa_exchange_flag=0";
			else if (type.equals("web"))
				persons_sql += " where web_exchange_flag=0";
			else if (type.equals("pxxt"))
				persons_sql += " where PXXT_EXCHANGE_FLAG=0";
		}
		persons_sql += " order by stream_id";

		Statement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(persons_sql);
			while (rs.next()) {
				PersonInfo info = new PersonInfo();
				info.setStreamId(rs.getString("stream_id"));
				info.setAddress(rs.getString("address"));
				info.setArchivesAddress(rs.getString("ARCHIVES_ADDRESS"));
				info.setArchivesNo(rs.getString("ARCHIVES_NO"));
				info.setBirthday(rs.getDate("BIRTHDAY"));
				info.setBusSpeciality1(rs.getString("BUS_SPECIALITY1"));
				info.setCerStatus(rs.getString("CER_STATUS"));
				info.setCertificateNo(rs.getString("CERTIFICATE_NO"));
				info.setCreateDate(rs.getString("CREATE_DATE"));
				info.setEmail(rs.getString("EMAIL"));
				// info.setExchangeFlag(rs.getString("EXCHANGE_FLAG"));
				info.setGraduateSchool(rs.getString("GRADUATE_SCHOOL"));
				info.setHomeTelephone(rs.getString("HOME_TELEPHONE"));
				info.setIdentityCard(rs.getString("IDENTITY_CARD"));
				info.setIssueDate(rs.getDate("ISSUE_DATE"));
				info.setIssueDepartment(rs.getString("ISSUE_DEPARTMENT"));
				info.setJoinDate(rs.getDate("JOIN_DATE"));
				info.setKnowlegeCode(rs.getString("KNOWLEGE_CODE"));
				info.setLawyerKind(rs.getString("LAWYER_KIND"));
				info.setLawyerType(rs.getString("LAWYER_TYPE"));
				info.setMasterFlag(rs.getString("MASTER_FLAG")==null?"0":rs.getString("MASTER_FLAG"));
				info.setMobilePhone(rs.getString("MOBILE_TELEPHONE"));
				info.setNationId(rs.getString("NATION_ID"));
				info.setOfficeTelephone(rs.getString("OFFICE_TELEPHONE"));
				info.setOldPersonName(rs.getString("OLD_PERSON_NAME"));
				info.setOrgId(rs.getString("ORG_ID"));
				info.setPersonCode(rs.getString("PERSON_CODE"));
				info.setPersonName(rs.getString("PERSON_NAME"));
				info.setPersonStatus(rs.getString("PERSON_STATUS"));
				info.setPoliticsCode(rs.getString("POLITICS_CODE"));
				info.setPost(rs.getString("POST"));
				info.setQIssueDepartment(rs.getString("Q_ISSUE_DEPARTMENT"));
				info.setQualiAdress(rs.getString("QUALI_ADDRESS"));
				info.setQualificationLevel(rs.getString("QUALIFICATION_LEVEL"));
				info.setQualificationNo(rs.getString("QUALIFICATION_NO"));
				info.setQualiIssuteDate(rs.getDate("QUAL_ISSUTE_DATE"));
				info.setReqQualiMethod(rs.getString("REQ_QUAL_METHOD"));
				info.setResidence(rs.getString("RESIDENCE"));
				info.setRoleCode(rs.getString("ROLE_CODE"));
				info.setSex(rs.getString("SEX"));
				info.setSpeciality(rs.getString("SPECIALITY"));
				info.setOaExchangeFlag(rs.getString("OA_EXCHANGE_FLAG"));
				info.setWebExchangeFlag(rs.getString("WEB_EXCHANGE_FLAG"));
				info.setPxxtExchangeFlag(rs.getString("PXXT_EXCHANGE_FLAG"));
				info.setDataType(rs.getString("data_type"));
				info.setFilename(rs.getString("file_name"));
				list.add(info);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}

		return list;
	}

	public List getTempOrgs(Connection con, String type) throws SQLException {
		String orgs_sql = "select * from t_org_info  ";
		if (type != null && !type.equals("")) {
			if (type.equals("oa"))
				orgs_sql += " where oa_exchange_flag=0";
			else if (type.equals("web"))
				orgs_sql += " where web_exchange_flag=0";
			else if (type.equals("pxxt"))
				orgs_sql += " where PXXT_EXCHANGE_FLAG=0";
		}
		orgs_sql += " order by stream_id";

		// System.out.println(orgs_sql);
		Statement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(orgs_sql);
			while (rs.next()) {
				OrgOfficeInfo org = new OrgOfficeInfo();
				org.setStreamId(rs.getString("stream_id"));
				// System.out.println(1);
				org.setAddress(rs.getString("ADDREDS"));
				// System.out.println(2);
				org.setAreage(rs.getString("AREAGE"));
				org.setCertCode(rs.getString("CERT_CODE"));
				org.setCreateDate(rs.getString("CREATE_DATE"));
				org.setCurStatus(rs.getString("CUR_STATUS"));
				org.setEnrollCapital(rs.getString("ENROLL_CAPITAL"));
				org.setOaExchangeFlag(rs.getString("OA_EXCHANGE_FLAG"));
				org.setWebExchangeFlag(rs.getString("WEB_EXCHANGE_FLAG"));
				org.setPxxtExchangeFlag(rs.getString("PXXT_EXCHANGE_FLAG"));
				org.setFax(rs.getString("FAX"));
				org.setFileNo(rs.getString("FILE_NO"));
				org.setFormDate(rs.getDate("FORM_DATE"));
				org.setIssuDate(rs.getDate("ISSUE_DATE"));
				org.setMasterDept(rs.getString("MASTER_DEPT"));
				org.setOfficePhone(rs.getString("OFFICE_PHONE"));
				org.setOrgId(rs.getString("ORG_ID"));
				org.setOrgName(rs.getString("ORG_NAME"));
				org.setOrgType(rs.getString("ORG_TYPE"));
				org.setRegistStatus(rs.getString("REGIST_STATUS"));
				org.setRemark1(rs.getString("REMARK1"));
				org.setZipcode(rs.getString("ZIPCODE"));
				org.setDataType(rs.getString("DATA_TYPE"));
				org.setGuildId(rs.getString("guild_Id"));
				list.add(org);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return list;
	}

	public void deleteTempOrgs(Connection con, List ids) throws SQLException {
		String sql = "delete from t_person_info where stream_id=?";

		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			for (int i = 0; ids != null && i < ids.size(); i++) {
				OrgOfficeInfo info = (OrgOfficeInfo) ids.get(i);
				stmt.setString(1, info.getStreamId());
				stmt.addBatch();
			}
			if (ids != null && ids.size() != 0)
				stmt.executeBatch();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public void updateTempOrgs(Connection con, String type, List ids) throws SQLException {

		String set = "";
		if (type != null && !type.equals("")) {
			if (type.equals("oa"))
				set = " set oa_exchange_flag=1";
			else if (type.equals("web"))
				set = " set web_exchange_flag=1";
			else if (type.equals("pxxt"))
				set = " set PXXT_EXCHANGE_FLAG=1";
		}
		String sql = "update t_org_info " + set + " where stream_id=?";

		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			for (int i = 0; ids != null && i < ids.size(); i++) {
				OrgOfficeInfo info = (OrgOfficeInfo) ids.get(i);
				stmt.setString(1, info.getStreamId());
				stmt.addBatch();
			}
			if (ids != null && ids.size() != 0){
		
				stmt.executeBatch();
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public void updateTempPersons(Connection con, String type, List ids) throws SQLException {
		String set = "";
		if (type != null && !type.equals("")) {
			if (type.equals("oa"))
				set = " set oa_exchange_flag=1";
			else if (type.equals("web"))
				set = " set web_exchange_flag=1";
			else if (type.equals("pxxt"))
				set = " set PXXT_EXCHANGE_FLAG=1";
		}
		String sql = "update t_person_info " + set + " where stream_id=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			for (int i = 0; ids != null && i < ids.size(); i++) {
				PersonInfo info = (PersonInfo) ids.get(i);
				stmt.setString(1, info.getStreamId());
				stmt.addBatch();
			}
			if (ids != null && ids.size() != 0)
				stmt.executeBatch();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public void deleteTempPersons(Connection con, List ids) throws SQLException {
		String sql = "delete from t_person_info where stream_id=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			for (int i = 0; ids != null && i < ids.size(); i++) {
				PersonInfo info = (PersonInfo) ids.get(i);
				stmt.setString(1, info.getStreamId());
				stmt.addBatch();
			}
			if (ids != null && ids.size() != 0)
				stmt.executeBatch();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public void deleteTempOrgUsers(Connection con, List ids) throws SQLException {
		String sql = "delete from T_U_LAWYER_USER where stream_id=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			for (int i = 0; ids != null && i < ids.size(); i++) {
				OrgUserInfo info = (OrgUserInfo) ids.get(i);
				stmt.setString(1, info.getStreamId());
				stmt.addBatch();
			}
			if (ids != null && ids.size() != 0)
				stmt.executeBatch();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public void updateTempOrgUsers(Connection con, String type, List ids) throws SQLException {
		String set = "";
		if (type != null && !type.equals("")) {
			if (type.equals("oa"))
				set = " set oa_exchange_flag=1";
			else if (type.equals("web"))
				set = " set web_exchange_flag=1";
			else if (type.equals("pxxt"))
				set = " set PXXT_EXCHANGE_FLAG=1";
		}
		String sql = "update T_U_LAWYER_USER " + set + " where stream_id=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			for (int i = 0; ids != null && i < ids.size(); i++) {
				OrgUserInfo info = (OrgUserInfo) ids.get(i);
				stmt.setString(1, info.getStreamId());
				stmt.addBatch();
			}

			if (ids != null && ids.size() != 0)

				stmt.executeBatch();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public void deleteTempLxUsers(Connection con, List ids) throws SQLException {
		String sql = "delete from T_U_GUILD_USER where stream_id=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			for (int i = 0; ids != null && i < ids.size(); i++) {
				GuildUserInfo info = (GuildUserInfo) ids.get(i);
				stmt.setString(1, info.getStreamId());
				stmt.addBatch();
			}
			if (ids != null && ids.size() != 0)
				stmt.executeBatch();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	public void updateTempLxUsers(Connection con, String type, List ids) throws SQLException {
		String set = "";
		if (type != null && !type.equals("")) {
			if (type.equals("oa"))
				set = " set oa_exchange_flag=1";
			else if (type.equals("web"))
				set = " set web_exchange_flag=1";
			else if (type.equals("pxxt"))
				set = " set PXXT_EXCHANGE_FLAG=1";
		}
		String sql = "update T_U_GUILD_USER " + set + " where stream_id=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			for (int i = 0; ids != null && i < ids.size(); i++) {
				GuildUserInfo info = (GuildUserInfo) ids.get(i);
				stmt.setString(1, info.getStreamId());
				stmt.addBatch();
		
			}
			if (ids != null && ids.size() != 0)
				stmt.executeBatch();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}
}
