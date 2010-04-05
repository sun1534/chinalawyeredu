import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CopyOfTest {
	public static void main(String args[]) throws Exception {
		System.out.println(com.changpeng.common.util.MD5.md5("123456"));
	}
	public static void main1(String args[]) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = java.sql.DriverManager.getConnection(
				"jdbc:mysql://211.154.157.174:3306/saaspxxt?characterEncoding=utf-8", "root", "password");

		java.io.BufferedReader br = new java.io.BufferedReader(new InputStreamReader(new java.io.FileInputStream(
				"d:\\henan.csv")));
		
		java.io.PrintWriter log=new java.io.PrintWriter(new java.io.FileOutputStream("d:\\henan.sql",true),true);
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] s = line.split(",");

			String name = s[0].trim();
			String no = s[1].trim();
			int groupid = update(con, name);
			if (groupid == 0){
			System.out.println(name+","+no);
			}else{
				log.println("update sys_user set loginname='"+no+"' where groupid="+groupid+" and roleid=1;");
				log.println("update sys_group set groupenname='"+no+"' where groupid="+groupid+";");
			}

		}
		log.flush();
		con.close();
		log.close();

		br.close();

	}

	public static int update(Connection con, String name) throws Exception {
		String sql = "select groupid from sys_group where groupname='" + name + "'";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		int s = 0;
		if (rs.next())
			s = rs.getInt("groupid");
		rs.close();
		stmt.close();
		return s;
	}

}
