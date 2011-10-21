import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;

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
public class Export {

	private static String user = "ynxxt";
	private static String password = "ynxxt";
	private static String url = "jdbc:oracle:thin:@192.168.33.20:1521:ora10";
	// private static String url = "jdbc:oracle:thin:@58.60.231.4:21521:ora10";
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
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) throws Exception {
		long now1 = System.currentTimeMillis();
		con = getConn();
		System.out.println("成功建立到数据库的链接...");

		String _table = "sms_mt_rec";

//		for (int i = 2; i <= 9; i++) {
//			String table = _table + i;
			String table="log_mark";
			String first = "2011-03-01";
			Date fd = df.parse(first);
			long end = df.parse("2011-10-20").getTime();
			while (fd.getTime() <= end) {
				long now = System.currentTimeMillis();
				String sql = "delete from " + table + " where stat_time<to_date('" + df.format(fd) + "','yyyy-mm-dd')";

				fd = new java.sql.Timestamp(fd.getTime() + 24 * 60 * 60 * 1000);
				java.sql.Statement stmt = con.createStatement();
				int s = stmt.executeUpdate(sql);
				System.out.println(sql + "=>" + s + "条数据:" + (System.currentTimeMillis() - now));
				stmt.close();
			}
//		}

		con.close();
		System.out.println("最后完毕，总计时间为:" + (System.currentTimeMillis() - now1));
	}

	
	public static void main2(String[] args) throws Exception {
		long now1 = System.currentTimeMillis();
		con = getConn();
		System.out.println("成功建立到数据库的链接...");

		String table = "attd_checkin";

		
			String first = "2011-01-01";
			Date fd = df.parse(first);
			long end = df.parse("2011-09-15").getTime();
			while (fd.getTime() <= end) {
				long now = System.currentTimeMillis();
				String sql = "delete from " + table + " where create_time<to_date('" + df.format(fd) + "','yyyy-mm-dd')";

				fd = new java.sql.Timestamp(fd.getTime() + 24 * 60 * 60 * 1000);
				java.sql.Statement stmt = con.createStatement();
				int s = stmt.executeUpdate(sql);
				System.out.println(sql + "=>" + s + "条数据:" + (System.currentTimeMillis() - now));
				stmt.close();
			}
	
		con.close();
		System.out.println("最后完毕，总计时间为:" + (System.currentTimeMillis() - now1));
	}
}