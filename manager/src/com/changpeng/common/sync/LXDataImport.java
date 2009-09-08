/**
 * 
 */
package com.changpeng.common.sync;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.common.sync.website.TopmCorporation;
import com.changpeng.common.util.Chinese2Pinyin;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUnionparams;

/**
 * @author 华锋 Jul 11, 2009-12:33:37 PM
 * 
 */
public class LXDataImport {

	/**
	 * 将所有律协的数据加入到3个数据库 这个事情，第一次就做了。
	 */
	private static Map syncAllLXOrg(Connection con) throws SQLException {

		String sql = "select * from v_guild_info ";
		ResultSet rs = null;
		Statement stmt = null;
		Map map = new HashMap();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				map.put(rs.getString("id"), rs.getString("name"));
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return map;
	}

	private static void addPxxt(String id, String name) {
		if (!id.equals("0408")) {
			int gxlxid = 22;
			try {
				BasicService basicService = (BasicService) Globals.getMainBean("basicService");
				SysGroup group = new SysGroup();
				group.setAddress("");
				group.setComments("律管平台同步");
				group.setContacter(null);
				group.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				group.setCreatetype(3);
				group.setCreateuser("律管平台同步");
				group.setDelflag(false);
				group.setDirectgroup(gxlxid);
				group.setFax("");
				group.setGroupenname(Chinese2Pinyin.to2pinyin(name));
				group.setGrouplevel(2);
				group.setGroupname(name);
				group.setGrouptype(2);
				group.setParentid(gxlxid);
				group.setPhone("");
				group.setSystemno("-1" + id);
				group.setUsercnts(0);
				basicService.save(group);

				com.changpeng.models.SysUnionparams params = new SysUnionparams();
				params.setDabiaofen(40f);
				params.setGroupid(group.getGroupid());
				params.setHavelocal(true);
				params.setNianshen("12-31");
				params.setSysGroup(group);
				params.setSysname(null);
				params.setTopbarpic(null);
				basicService.save(params);

				System.out.println("律协数据新增到培训系统成功:" + group.getSystemno() + "," + group.getGroupname());
			} catch (Exception e) {
				System.out.println("律协新增到培训系统失败:" + e);
			}
		}
	}

	private static void addOa(String id, String name, Connection con) {
		int parentid = 10;
		if (!id.equals("0408")) {
			try {
				PreparedStatement stmt = null;

				String sql = "insert into tsys_department(DEPARTMENTNAME,DESCRIPTION,"
						+ "displayname,TYPE,STATUSID,PHONE,LINKMAN,ADDRESS,CREATETIME,PARENTID,SYSTEMNUM) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?)";
				String systemnum = "-1" + id;

				stmt = con.prepareStatement(sql);
				stmt.setString(1, name);
				stmt.setString(2, "律管平台同步");
				stmt.setString(3, name);
				stmt.setInt(4, 0);// 机构类型 0 律协，1 事务所
				stmt.setInt(5, (short) 1);
				stmt.setString(6, "");
				stmt.setString(7, "");
				stmt.setString(8, null);
				stmt.setTimestamp(9, new java.sql.Timestamp(System.currentTimeMillis()));
				stmt.setInt(10, parentid); //
				stmt.setString(11, systemnum); // systemnum
				stmt.executeUpdate();
				stmt.close();
				System.out.println("律协新增到OA成功：" + id + "," + name);

			} catch (Exception e) {
				System.out.println("培训系统部门同步异常 : getOrgId:" + id + "=>" + e);
				e.printStackTrace();
			}
		}
	}

	private static void addWeb(String id, String name, Connection con) {
		long parentId = 101;
		TopmCorporation group = new TopmCorporation();
		try {
			if (!id.equals("0408")) {
				group.setCorporationid(Long.parseLong("-1" + id));
				group.setDescription("律管平台同步");
				group.setCorpaddr("");
				group.setPostcode("");
				group.setLicenceno("");
				group.setRegisterdate(new java.sql.Timestamp(System.currentTimeMillis()));
				// group.setRegfund(org.getEnrollCapital());
				group.setContactertel(null);
				group.setTypeid(1);
				group.setCorpnameshort(null);
				// group.setStatusid(org.getCurStatus())
				group.setCreatedate(new java.sql.Timestamp(System.currentTimeMillis()));
				group.setConfirmdate(group.getCreatedate());
				group.setContacterfax(null);
				group.setCorporationname(name);
				group.setParentid(parentId);
				group.setContactertel(null);
				group.insert(con);
				System.out.println("律协新增到网站成功：" + group.getCorporationid() + "," + group.getCorporationname());
			}
		} catch (Exception e) {
			System.out.println("律协新增到网站失败：" + e);
		}
	}

	// 将律协的数据导入到网站，oa以及培训系统
	public static void main(String args[]) {

		Connection lgptCon = null;
		Connection oaCon = null;
		Connection webCon = null;
		try {
			lgptCon = com.changpeng.common.sync.DBUtils.getOracleCon();
			webCon = com.changpeng.common.sync.DBUtils.getWebsiteCon();
			oaCon = com.changpeng.common.sync.DBUtils.getOACon();
			Map map = syncAllLXOrg(lgptCon);

			Iterator iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String id = iterator.next().toString();
				String name = map.get(id).toString();
				// addWeb(id, name, webCon);
				 addPxxt(id, name);
//				addOa(id, name, oaCon);
			}

		} catch (Exception e) {
			System.out.println("同步失败:" + e);

		} finally {
			com.changpeng.common.sync.DBUtils.closeConnection(lgptCon);
			com.changpeng.common.sync.DBUtils.closeConnection(oaCon);
			com.changpeng.common.sync.DBUtils.closeConnection(webCon);
		}
	}
}
