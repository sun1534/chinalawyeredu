import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NewCustomer.java
 */

/**
 * 
 * 删除重复的tusr_address数据
 * 
 * @author 华锋 Nov 27, 201010:36:58 PM
 * 
 */
public class DeleteRepeatAddress {

	private static String user = "jds";
	private static String password = "jds";
	private static String url = "jdbc:oracle:thin:@localhost:1521:ora10";
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConn() throws Exception {
		return java.sql.DriverManager.getConnection(url, user, password);
	}

	private static Connection con = null;

	public static void main(String args[]) throws Exception {
		Connection con = null;
		List list = new ArrayList();
		try {
			con = getConn();
			con.setAutoCommit(false);

			String sql = "select * from tusr_address group by md5val having count(*)>1 ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(rs.getString("md5val"));
			}
			rs.close();
			stmt.close();

			int len = list.size();
			Statement stmt1 = con.createStatement();
			long now = System.currentTimeMillis();
			long totalnow = System.currentTimeMillis();
			System.out.println("重复的个数为:::" + list.size());
			java.io.PrintWriter log = new java.io.PrintWriter(new java.io.FileOutputStream("d:/delete.sql", true), true);
			for (int i = 1; i <= len; i++) {

				String md5val = list.get(i - 1).toString();
				int addressid = getUsrAddress(con, md5val);

				String delsql = "delete from tusr_address where addressid!=" + addressid + " and md5val='" + md5val
						+ "'";
				log.println(delsql);
				stmt1.addBatch(delsql);
				if ((i) % 100 == 0) {
					int s[] = stmt1.executeBatch();
					stmt1.clearBatch();
					System.out.println("处理" + s.length + "的时间为:" + (System.currentTimeMillis() - now));
					// System.out.println("处理" + i + "的时间为:" +
					// (System.currentTimeMillis() - now));
					now = System.currentTimeMillis();
				}
			}

			int s[] = stmt1.executeBatch();
			System.out.println("最后处理" + s.length + "的时间为:" + (System.currentTimeMillis() - now));

			con.commit();
			con.setAutoCommit(true);

			rs.close();
			stmt.close();
			stmt1.close();
			log.flush();
			log.close();

			System.out.println("总计时间为:" + (System.currentTimeMillis() - totalnow));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		con.close();
	}

	public static int getUsrAddress(Connection con, String md5val) throws SQLException {
		String sql = "select addressid from tusr_address where md5val=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int s = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, md5val);
			rs = pstmt.executeQuery();
			if (rs.next())
				s = rs.getInt("addressid");
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
		return s;
	}

}
