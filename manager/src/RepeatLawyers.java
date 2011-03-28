import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */

/**
 * 导入河南的除郑州和省直所外的其他事务所数据
 * 
 * @author 华锋 Aug 22, 2009-1:11:33 PM
 * 
 */
public class RepeatLawyers {

	private static PrintWriter logugroup;
	private static PrintWriter logugroups;
	private static PrintWriter log;

	private static Connection con = null;
	static String url = "jdbc:mysql://211.154.157.174:3306/pxxt_shenzhen?characterEncoding=utf-8";
	static String user = "root";
	static String pass = "password";

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = java.sql.DriverManager.getConnection(url, user, pass);
		logugroup = new PrintWriter(new FileOutputStream("group.sql", true), true);
		logugroups = new PrintWriter(new FileOutputStream("groupsame.sql", true), true);
		log = new PrintWriter(new FileOutputStream("log.txt", true), true);

		// repeat();
		 delrepeat();
//		updateerrorrepeat();
		con.close();
	}

	public static void delrepeat() throws Exception {
		String sql = "select userid,lessonid from lawyerlessonxf where lessonid is not null group by userid,lessonid having count(*)>1";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int userid = rs.getInt("userid");
			int lessonid = rs.getInt("lessonid");

			String sql1 = "select * from lawyerlessonxf where userid='" + userid + "' and lessonid=" + lessonid;

			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			int i = 0;
			while (rs1.next()) {

				if (i == 0) {
					int xfid = rs1.getInt("xfid");
					String sss = "delete from lawyerlessonxf where xfid=" + xfid + ";";
					System.out.println(sss);
				}
				i++;
			}

			rs1.close();
			stmt1.close();

		}
		rs.close();
		stmt.close();
	}

	public static void repeat() throws Exception {
		String sql = "SELECT username,groupid FROM sys_user where roleid=1 and groupid!=-1 GROUP BY username,groupid having count(username)>1";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String username = rs.getString("username");
			int groupid = rs.getInt("groupid");
			String sql1 = "select * from sys_user_20110325 where groupid=" + groupid+" and username='" + username + "'"
					+ " order  by username,userid";
			System.out.println(sql1);

			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);

			int oldid = Integer.MAX_VALUE;
			int newid = 0;
			String cardno = "";
			String carddate = "";
			while (rs1.next()) {
				int id = rs1.getInt("userid");

				if (id > newid) {
					newid = id;
				}
				if (id < oldid) {
					oldid = id;
				}

				String _cardno = rs1.getString("cardno");
				String _carddate = rs1.getString("carddate");
				String lawerno = rs1.getString("lawerno");
				// 14403199010391933
				// 19020908212506

				// if (lawerno.startsWith("1902")) {// 删除
				// oldid = id;
				// } else if (lawerno.startsWith("144")) {
				// newid = id;
				// }
				if (_cardno != null && !_cardno.equals(""))
					cardno = _cardno;
				if (_carddate != null && !_carddate.equals(""))
					carddate = _carddate;

			}
			if (newid != 0 && oldid != 0) {

				String xf = "update lawyerlessonxf set userid=" + newid + " where userid=" + oldid + ";";
				String user = "update sys_user set cardno='" + cardno + "',carddate='" + carddate + "' where userid="
						+ newid + ";";
				String jf = "update jifenbudeng set userid=" + newid + " where userid=" + oldid + ";";
				String lf = "update lxnetrecs set userid=" + newid + " where userid=" + oldid + ";";
				String dele = "delete from sys_user where userid=" + oldid + ";";

				// System.out.println(xf);
				// System.out.println(jf);
				// System.out.println(lf);
				// System.out.println(dele);
				// logugroup.println(user);
				// logugroup.println(xf);
				// logugroup.println(jf);
				// logugroup.println(lf);
				logugroup.println(dele);

			} else {
				System.out.println("oldid=" + oldid + "====newid===" + newid);
			}

			rs1.close();
			stmt1.close();

		}
		logugroup.flush();
		logugroup.close();
		rs.close();
		stmt.close();
		con.close();

	}

	public static void updateerrorrepeat() throws Exception {
		String sql = "SELECT lawid,lawname,entid,lawno,peopleno FROM test.law ";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		int jj = 0;
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		List list3 = new ArrayList();
		List list4 = new ArrayList();
		List list5 = new ArrayList();
		while (rs.next()) {
			int newid = rs.getInt("lawid");
			String username = rs.getString("lawname");
			int groupid = rs.getInt("entid");
			String certno = rs.getString("peopleno");
			String lawno = rs.getString("lawno");
			String sql1 = "select * from pxxt_shenzhen.sys_user where groupid="
					+ groupid+" and  username='" + username + "'";
			System.out.println(sql1 + "=>" + (jj++));

			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);

			int oldid = 0;
			while (rs1.next()) {
				oldid = rs1.getInt("userid");
			}
			if (oldid != 0 && oldid != newid) {

				String xf = "update lawyerlessonxf set userid=" + newid + " where userid=" + oldid + ";";

				String jf = "update jifenbudeng set userid=" + newid + " where userid=" + oldid + ";";
				String lf = "update lxnetrecs set userid=" + newid + " where userid=" + oldid + ";";
				String dele = "update sys_user set userid=" + newid + ",lawerno='" + lawno + "',certno='" + certno
						+ "' where userid=" + oldid + ";";

				// System.out.println(xf);
				// System.out.println(jf);
				// System.out.println(lf);
				// System.out.println(dele);
				// logugroup.println(user);
//				logugroup.println(xf);
//				logugroup.println(jf);
//				logugroup.println(lf);
				logugroup.println(dele);

//				list1.add(xf);
//				list2.add(jf);
//				list3.add(lf);
				list4.add(dele);

			} else if (oldid == 0) {
				// 新增律师数据

				String sql11 = "insert into sys_user(userid,groupid,loginname,username,comments,createuser,createtime,lawerno,certno)"
						+ "values("
						+ newid
						+ ","
						+ groupid
						+ ",'"
						+ lawno
						+ "','"
						+ username
						+ "','20110325比对后新增','lhf',now(),'" + lawno + "','" + certno + "');";
				list5.add(sql11);
				logugroup.println(sql11);
				System.out.println(sql11);
			} else if (oldid == newid) {
				logugroups.println("id====" + newid);
			}

			rs1.close();
			stmt1.close();

		}
		logugroup.flush();
		logugroup.close();
		logugroups.flush();
		logugroups.close();

		executeSql(con, list1);
		executeSql(con, list2);
		executeSql(con, list3);
		executeSql(con, list4);
		executeSql(con, list5);
		
		log.flush();
		log.close();

		rs.close();
		stmt.close();
		con.close();

	}

	private static int executeSql(Connection con, List<String> sqls) throws Exception {
		Statement stmt = null;
		int result = 0;
		try {
			int len = sqls.size();
			log.println("要执行的SQL个数为:" + len);
			long now = System.currentTimeMillis();
//			List<String> nowsqls = new ArrayList<String>();
			if (len > 0) {
				stmt = con.createStatement();
				int i = 1;
				for (String sql : sqls) {
					stmt.addBatch(sql);
//					nowsqls.add(sql);
					if ((i++) % 1 == 0) {
						try {

							int s[] = stmt.executeBatch();
							stmt.clearBatch();
							result += s.length;
							log.println("执行300个的时间为:" + (System.currentTimeMillis() - now));
							System.out.println("执行300个的时间为:" + (System.currentTimeMillis() - now));
							now = System.currentTimeMillis();
						} catch (Exception e) {
							System.out.println("错误的SQL如下:" + e);
							log.println("错误的SQL如下:" + e);
//							for (int ii = 0; ii < nowsqls.size(); ii++) {
//								log.println("错误SQL:" + nowsqls.get(ii));
//							}

						}
//						nowsqls.clear();
					}
				}
				try {
					int s[] = stmt.executeBatch();
					log.println("执行最后" + s.length + "个的时间为:" + (System.currentTimeMillis() - now));
					System.out.println("执行最后" + s.length + "个的时间为:" + (System.currentTimeMillis() - now));
					stmt.clearBatch();
					result += s.length;
				} catch (Exception e) {
					System.out.println("最后错误的SQL如下:" + e);
					log.println("错误的SQL如下:" + e);
//					for (int ii = 0; ii < nowsqls.size(); ii++) {
//						log.println("错误SQL:" + nowsqls.get(ii));
//					}
				}
//				nowsqls.clear();
			}
		} finally {

		}
		return result;
	}

}