import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Import.java
 */

/**
 * <pre>
 * </pre>
 * 
 * @author 华锋 2008-7-29 下午12:46:03
 * 
 */
public class Import {

	private static String url = "jdbc:mysql://localhost:3306/pxxt_nanning";
	private static String user = "pxxt_nanning";
	private static String pass = "pxxt_nanning";
	private static String driver = "com.mysql.jdbc.Driver";

	private static Map groupmap = new HashMap();
	static {
		try {
			Class.forName(driver);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Connection con = DriverManager.getConnection(url, user, pass);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select groupid,groupname from sys_group");
		while (rs.next()) {
			groupmap.put(rs.getString("groupname"), rs.getString("groupid"));
		}

//		java.io.BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\shiwusuo.txt"), "utf-8"));
		
		java.io.BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\geren.txt"), "utf-8"));
		
		String line = "";

//		java.io.PrintWriter log = new java.io.PrintWriter("c:\\shiwusuo.sql");
		
		java.io.PrintWriter log1 = new java.io.PrintWriter("c:\\geren.sql");
		
		String password = com.changpeng.common.util.MD5.md5("123456");
		while ((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line, ",");
			String name = st.nextToken().trim();

			String gender = st.nextToken().trim();
			String groupname = st.nextToken().trim();
			Object groupid = groupmap.get(groupname);
			String lawerno=st.nextToken();
			
			
			String sql = "insert into sys_user(groupid,loginname,username,password,status,delflag,comments,gender,lawerno,roleid) values(" + groupid + ",'" + lawerno
					+ "','" + name + "','" + password + "',0,false,'20080729导入','"+gender+"','"+lawerno+"',1);";
			
			
			log1.println(sql);
			System.out.println(name + "===" + sql);

		}
		log1.flush();
		log1.close();

	}

}
