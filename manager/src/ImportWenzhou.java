import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
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
public class ImportWenzhou {
	private static Map grouplist = new HashMap();
	private static Map lawyerslist = new HashMap();
	private static java.io.PrintWriter logsql;
	private static java.io.PrintWriter loglog;

	private static Connection con = null;
	static String url = "jdbc:mysql://211.154.157.174:3306/saaspxxt?characterEncoding=utf-8";
	static String user = "saaspxxt";
	static String pass = "saaspxxt";

	private static int getGroups(Connection con) throws Exception {
		String sql = "select groupid,groupname from sys_group where parentid=11002221";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next())
				grouplist.put(rs.getString("groupname"), rs.getInt("groupid"));
			return 0;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	private static int getLawyers(Connection con) throws Exception {
		String sql = "select lawyerid,lawyername from lawyers where directunion=11002221";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next())
				lawyerslist.put(rs.getString("lawyername"), rs.getInt("lawyerid"));
			return 0;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		logsql = new java.io.PrintWriter(new java.io.FileOutputStream("d:\\sql.sql", true), true);
		loglog = new java.io.PrintWriter(new java.io.FileOutputStream("d:\\log.sql", true), true);


		con = java.sql.DriverManager.getConnection(url, user, pass);
		getGroups(con);
		getLawyers(con);
		// System.out.println(grouplist);
		// System.out.println(lawyerslist);
		// importGroups(args[0]);
		importGroups(null);
		con.close();
		logsql.flush();
		loglog.flush();
		logsql.close();
		loglog.close();
	}

	public static void importGroups(String rootdir) throws Exception {
		// TODO Auto-generated method stub

		File rootfile = new File("d:\\公证员照片");
		File[] dirs = rootfile.listFiles();
		for (File dir : dirs) {
			String name = dir.getName();
			// System.out.println(name + "==" + grouplist.get(name));
			File[] files = dir.listFiles();
			for (File file : files) {
				String tname = file.getName().toLowerCase().trim();
				String filename = tname.replace(" 2合伙律师", "").replace("律师", "").replace(".jpg", "").replace(".bmp", "")
						.replace("副主任", "").replace("（主任）", "").replace("副本", "").replace("_缩大缩小", "").replace("_缩小大小",
								"").trim();
				if (filename.indexOf("李轶成") != -1) {
					filename = "李轶成";
				} else if (filename.indexOf("002王雨恩") != -1) {
					filename = "王雨恩";
				} else if (filename.startsWith("dsc")) {
					filename = filename.substring(8);
				}
				if (lawyerslist.get(filename) != null) {
					String ext = tname.substring(tname.lastIndexOf(".") + 1);
					String id = lawyerslist.get(filename).toString();
					String sql = "update lawyers set photoname='" + tname + "',photo='11002221/" + id + "." + ext
							+ "' where lawyerid=" + id + ";";
					logsql.println(sql);
					System.out.println(sql);
					File dest = new 
					File("d:/11002221/" + id + "." + ext);
					org.apache.commons.io.FileUtils.copyFile(file, dest);
				}else{
					loglog.println(name+"中的图片"+tname+"在系统中找不到对应的律师");
				}

			}
		}

	}
}