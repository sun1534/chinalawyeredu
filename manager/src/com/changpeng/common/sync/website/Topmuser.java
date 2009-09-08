/**
 * 
 */
package com.changpeng.common.sync.website;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author 华锋 Jul 11, 2009-5:38:09 PM
 * 
 */
public class Topmuser {

	   long userid;
	    long staffid;
	    int powerid;
	    String username;
	    String password;
	    String loginname;
	    int statusid;
	    Timestamp createdate;
	    String description;
	    String staffname;
	/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}

	public Topmuser() {
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getUserid() {
		return userid;
	}

	public void setStaffid(long staffid) {
		this.staffid = staffid;
	}

	public long getStaffid() {
		return staffid;
	}

	public void setPowerid(int powerid) {
		this.powerid = powerid;
	}

	public int getPowerid() {
		return powerid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public int getStatusid() {
		return statusid;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public String getStaffname() {
		return staffname;
	}

	public void create(Connection con, long userid) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			sql = "select userid,staffid,powerid,username,password,loginname,statusid,createdate,de"
					+ "scription from topmuser where userid=?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, userid);
			rs = stmt.executeQuery();
			if (rs.next()) {
				this.userid = rs.getLong("userid");
				staffid = rs.getLong("staffid");
				powerid = rs.getInt("powerid");
				username = rs.getString("username");
				password = rs.getString("password");
				loginname = rs.getString("loginname");
				statusid = rs.getInt("statusid");
				createdate = rs.getTimestamp("createdate");
				description = rs.getString("description");
			}
			stmt.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	public void create(Connection con, String description) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			sql = "select userid,staffid,powerid,username,password,loginname,statusid,createdate,description from topmuser where description=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, description);
			rs = stmt.executeQuery();
			if (rs.next()) {
				this.userid = rs.getLong("userid");
				staffid = rs.getLong("staffid");
				powerid = rs.getInt("powerid");
				username = rs.getString("username");
				password = rs.getString("password");
				loginname = rs.getString("loginname");
				statusid = rs.getInt("statusid");
				createdate = rs.getTimestamp("createdate");
				description = rs.getString("description");
			}
			stmt.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	public int insert(Connection con) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "";
		int cnt = 0;
		try {
			sql = "insert into topmuser (userid,staffid,powerid,username,loginname,password,statusi"
					+ "d,createdate,description) values (?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, userid);
			stmt.setLong(2, staffid);
			stmt.setInt(3, powerid);
			stmt.setString(4, username);
			stmt.setString(5, loginname);
			stmt.setString(6, password);
			stmt.setInt(7, statusid);
			stmt.setTimestamp(8, createdate);
			stmt.setString(9, description);
			cnt = stmt.executeUpdate();
			stmt.close();
		} finally {
			if (stmt != null) {
				stmt = null;
			}
		}
		return cnt;
	}

	public int update(Connection con) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "";
		int cnt = 0;
		try {
			sql = "update topmuser set staffid=?,powerid=?,username=?,loginname=?,password=?,status"
					+ "id=?,createdate=?,description=? where userid=?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, staffid);
			stmt.setInt(2, powerid);
			stmt.setString(3, username);
			stmt.setString(4, loginname);
			stmt.setString(5, password);
			stmt.setInt(6, statusid);
			stmt.setTimestamp(7, createdate);
			stmt.setString(8, description);
			stmt.setLong(9, userid);
			cnt = stmt.executeUpdate();
			stmt.close();
		} finally {
			if (stmt != null) {
				stmt = null;
			}
		}
		return cnt;
	}

	public static int delete(Connection con, long userid) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "";
		int cnt = 0;
		try {
			sql = "delete from topmuser where userid=?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, userid);
			cnt = stmt.executeUpdate();
			stmt.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return cnt;
	}
}